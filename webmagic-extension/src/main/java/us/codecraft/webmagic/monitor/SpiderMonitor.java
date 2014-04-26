package us.codecraft.webmagic.monitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.SpiderListener;
import us.codecraft.webmagic.utils.Experimental;
import us.codecraft.webmagic.utils.IPUtils;

import javax.management.JMException;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author code4crafer@gmail.com
 * @since 0.5.0
 */
@Experimental
public class SpiderMonitor {

    private enum Type {
        Server, Client, Local;
    }

    private static AtomicInteger serialNumber = new AtomicInteger();

    private AtomicBoolean started = new AtomicBoolean(false);

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final int DEFAULT_SERVER_PORT = 14721;

    private static final String DEFAULT_SERVER_HOST = "localhost";

    private int serverPort;

    private String serverHost;

    private Type type = Type.Local;

    private List<SpiderStatusMXBean> spiderStatuses = new ArrayList<SpiderStatusMXBean>();

    public List<SpiderStatusMXBean> getSpiders() {
        return spiderStatuses;
    }

    public SpiderStatusMXBean getSpider() {
        return spiderStatuses.get(0);
    }

    /**
     * Register spider for monitor.
     *
     * @param spiders
     * @return
     */
    public SpiderMonitor register(Spider... spiders) {
        for (Spider spider : spiders) {
            MonitorSpiderListener monitorSpiderListener = new MonitorSpiderListener();
            if (spider.getSpiderListeners() == null) {
                List<SpiderListener> spiderListeners = new ArrayList<SpiderListener>();
                spiderListeners.add(monitorSpiderListener);
                spider.setSpiderListeners(spiderListeners);
            } else {
                spider.getSpiderListeners().add(monitorSpiderListener);
            }
            spiderStatuses.add(getSpiderStatusMBean(spider, monitorSpiderListener));
        }
        return this;
    }

    protected SpiderStatusMXBean getSpiderStatusMBean(Spider spider, MonitorSpiderListener monitorSpiderListener) {
        return new SpiderStatus(spider, monitorSpiderListener);
    }

    public static SpiderMonitor create() {
        return new SpiderMonitor();
    }

    public class MonitorSpiderListener implements SpiderListener {

        private final AtomicInteger successCount = new AtomicInteger(0);

        private final AtomicInteger errorCount = new AtomicInteger(0);

        private List<String> errorUrls = Collections.synchronizedList(new ArrayList<String>());

        @Override
        public void onSuccess(Request request) {
            successCount.incrementAndGet();
        }

        @Override
        public void onError(Request request) {
            errorUrls.add(request.getUrl());
            errorCount.incrementAndGet();
        }

        public AtomicInteger getSuccessCount() {
            return successCount;
        }

        public AtomicInteger getErrorCount() {
            return errorCount;
        }

        public List<String> getErrorUrls() {
            return errorUrls;
        }
    }


    /**
     * Start monitor as server mode.
     *
     * @param port
     * @return
     * @throws IOException
     * @throws JMException
     */
    public SpiderMonitor server(int port) throws IOException, JMException {
        try {
            Registry registry = LocateRegistry.createRegistry(port);
        } catch (ExportException e) {
            logger.warn("Start server fail, maybe the address is in using.", e);
        }
        serverPort = port;
        serverHost = "localhost";
        type = Type.Server;
        return this;
    }

    /**
     * Start monitor as server mode.
     *
     * @return
     * @throws IOException
     * @throws JMException
     */
    public SpiderMonitor server() throws IOException, JMException {
        return server(DEFAULT_SERVER_PORT);
    }

    /**
     * Local mode: the monitor will be bound to the JVM instance.<br></br>
     * Use jconsole to check your application.
     *
     * @return
     */
    public SpiderMonitor local() {
        this.type = Type.Local;
        return this;
    }


    /**
     * Start monitor as client mode.
     *
     * @param serverHost
     * @param serverPort
     * @return
     * @throws IOException
     * @throws JMException
     */
    public SpiderMonitor client(String serverHost, int serverPort) throws IOException, JMException {
        type = Type.Client;
        this.serverHost = serverHost;
        this.serverPort = serverPort;
        return this;
    }

    /**
     * Start monitor as client mode.
     *
     * @return
     * @throws IOException
     * @throws JMException
     */
    public SpiderMonitor client() throws IOException, JMException {
        return client(DEFAULT_SERVER_HOST, DEFAULT_SERVER_PORT);
    }

    public SpiderMonitor jmxStart() throws IOException, JMException {
        return jmxStart("localhost", DEFAULT_SERVER_PORT);
    }

    public SpiderMonitor jmxStart(String jndiServer, int rmiPort) throws IOException, JMException {
        if (!started.compareAndSet(false, true)) {
            logger.error("Monitor has already started!");
            return this;
        }
        String jmxServerName = "WebMagic-" + IPUtils.getFirstNoLoopbackIPAddresses() + "-" + serialNumber.incrementAndGet();

        // start JNDI
        MBeanServer localServer = ManagementFactory.getPlatformMBeanServer();

        ObjectName objName;

        if (type != Type.Local) {
            JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://" + jndiServer + ":" + rmiPort + "/" + jmxServerName);
            System.out.println("JMXServiceURL: " + url.toString());
            System.out.println("Please replace localhost of your ip if you want to connect it in remote server.");
            JMXConnectorServer jmxConnServer = JMXConnectorServerFactory.newJMXConnectorServer(url, null, localServer);
            jmxConnServer.start();
            objName = new ObjectName(jmxServerName + ":name=WebMagicMonitor");
            localServer.registerMBean(jmxConnServer, objName);
        }

        for (SpiderStatusMXBean spiderStatus : spiderStatuses) {
            objName = new ObjectName(jmxServerName + ":name=" + spiderStatus.getName());
            localServer.registerMBean(spiderStatus, objName);
        }

        return this;
    }

}
