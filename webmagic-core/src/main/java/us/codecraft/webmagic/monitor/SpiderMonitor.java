package us.codecraft.webmagic.monitor;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.example.GithubRepoPageProcessor;
import us.codecraft.webmagic.processor.example.OschinaBlogPageProcessor;

import javax.management.*;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author code4crafer@gmail.com
 * @since 0.5.0
 */
public class SpiderMonitor {

    private List<SpiderStatusMBean> spiderStatuses = new ArrayList<SpiderStatusMBean>();

    public List<SpiderStatusMBean> getSpiders() {
        return spiderStatuses;
    }

    public SpiderStatusMBean getSpider() {
        return spiderStatuses.get(0);
    }

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

    protected SpiderStatusMBean getSpiderStatusMBean(Spider spider, MonitorSpiderListener monitorSpiderListener) {
        return new SpiderStatus(spider, monitorSpiderListener);
    }

    public static SpiderMonitor create(){
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


    public void jmxStart() throws IOException, JMException {
        jmxStart(14721);
    }

    public void jmxStart(int rmiPort) throws IOException, JMException {
        String jmxServerName = "WebMagic";
        // jdkfolder/bin/rmiregistry.exe 9999
        Registry registry = LocateRegistry.createRegistry(rmiPort);

        MBeanServer mbs = MBeanServerFactory.createMBeanServer(jmxServerName);
        //MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

        ObjectName objName;

        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:" + rmiPort + "/" + jmxServerName);
        System.out.println("JMXServiceURL: " + url.toString());
        JMXConnectorServer jmxConnServer = JMXConnectorServerFactory.newJMXConnectorServer(url, null, mbs);
        jmxConnServer.start();

        for (SpiderStatusMBean spiderStatus : spiderStatuses) {
            objName = new ObjectName(jmxServerName + ":name=" + spiderStatus.getName());
            mbs.registerMBean(spiderStatus, objName);
        }
    }


    public static void main(String[] args) throws JMException,
            NullPointerException,
            IOException {

        Spider oschinaSpider = Spider.create(new OschinaBlogPageProcessor())
                .addUrl("http://my.oschina.net/flashsword/blog").thread(2);
        Spider githubSpider = Spider.create(new GithubRepoPageProcessor())
                .addUrl("https://github.com/code4craft");

        SpiderMonitor spiderMonitor = new SpiderMonitor();
        spiderMonitor.register(oschinaSpider, githubSpider);
        spiderMonitor.jmxStart();

    }

}
