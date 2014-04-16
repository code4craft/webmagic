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
public class SpiderMonitor implements SpiderMonitorMBean {

    private List<SpiderStatus> spiderStatuses = new ArrayList<SpiderStatus>();

    @Override
    public List<SpiderStatus> getSpiders() {
        return spiderStatuses;
    }

    @Override
    public SpiderStatus getSpider() {
        return spiderStatuses.get(0);
    }

    public void register(Spider spider) {
        MonitorSpiderListener monitorSpiderListener = new MonitorSpiderListener();
        if (spider.getSpiderListeners() == null) {
            List<SpiderListener> spiderListeners = new ArrayList<SpiderListener>();
            spiderListeners.add(monitorSpiderListener);
            spider.setSpiderListeners(spiderListeners);
        } else {
            spider.getSpiderListeners().add(monitorSpiderListener);
        }
        spiderStatuses.add(new SpiderStatus(spider, monitorSpiderListener));

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


    public static void main(String[] args) throws MalformedObjectNameException,
            NullPointerException, InstanceAlreadyExistsException,
            MBeanRegistrationException, NotCompliantMBeanException, IOException {

        int rmiPort = 1099;
        SpiderMonitor spiderMonitor = new SpiderMonitor();
        String jmxServerName = "TestJMXServer";

        Spider oschinaSpider = Spider.create(new OschinaBlogPageProcessor()).addUrl("http://my.oschina.net/flashsword/blog").thread(2);

        spiderMonitor.register(oschinaSpider);

        Spider githubSpider = Spider.create(new GithubRepoPageProcessor()).addUrl("https://github.com/code4craft");

        spiderMonitor.register(githubSpider);

        // jdkfolder/bin/rmiregistry.exe 9999
        Registry registry = LocateRegistry.createRegistry(rmiPort);

        MBeanServer mbs = MBeanServerFactory.createMBeanServer(jmxServerName);
        //MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

        ObjectName objName = new ObjectName(jmxServerName + ":name=" + "HelloWorld");
        mbs.registerMBean(spiderMonitor, objName);

        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:" + rmiPort + "/" + jmxServerName);
        System.out.println("JMXServiceURL: " + url.toString());
        JMXConnectorServer jmxConnServer = JMXConnectorServerFactory.newJMXConnectorServer(url, null, mbs);
        jmxConnServer.start();

        for (SpiderStatus spiderStatuse : spiderMonitor.spiderStatuses) {
            objName = new ObjectName(jmxServerName + ":name=" + spiderStatuse.getName());
            mbs.registerMBean(spiderStatuse, objName);
        }


    }

}
