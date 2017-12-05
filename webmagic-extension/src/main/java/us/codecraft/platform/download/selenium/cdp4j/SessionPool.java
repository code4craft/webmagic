package us.codecraft.platform.download.selenium.cdp4j;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;
import org.openqa.selenium.Proxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.platform.download.Pool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Chrome DevTools Protocol 的操作对象线程池
 *
 * @author : 王龙
 * @Description ：
 * @params ：
 * @return ：
 * @Date ：2017/11/23 11:22
 */
public class SessionPool extends Pool<Session> {

    private static Logger logger = LoggerFactory.getLogger(SessionPool.class);

    /**
     * chrome driver protcol 参数配置
     */
    protected List<String> configList = new ArrayList<>();

    private static Launcher launcher = new Launcher();

    private SessionFactory factory;

    /**
     * 构造函数
     *
     * @author 王龙
     * @date 2017年8月18日 下午9:07:20
     */
    public SessionPool() {
        this.factory = getFactory();
        addCloseProcessHook();
    }

    public SessionPool(List<String> configList) {
        this.configList = configList;
        this.factory = getFactory();
        addCloseProcessHook();
    }

    public void setConfigList(List<String> configList) {
        this.configList = configList;
        factory = getFactory();
    }

    /**
     * 设置池容量
     *
     * @param poolSize
     * @author 刘太信
     * @date 2017年8月18日 下午9:08:25
     */
    @Override
    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
        workerQueue = new LinkedBlockingQueue<>(poolSize);
    }

    @Override
    public synchronized Session createNewWorker() {
        Session session = factory.create();
        logger.info("session的id为={}", session.getId());
        return session;
    }

    /**
     * 关闭释放资源，不推荐手动调用，建议使用returnToPool
     *
     * @param session
     * @author 刘太信
     * @date 2017年8月18日 下午9:09:29
     */
    @Override
    public void close(Session session) {
        workingCount.decrementAndGet();
        if (workerQueue.contains(session)) {
            workerQueue.remove(session);
        }
        session.close();
    }

    @Override
    public void setProxy(Proxy proxy) {
        Iterator<String> iterator = configList.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().contains("--proxy-server=")) {
                iterator.remove();
            }
        }
        configList.add("--proxy-server=\"" + proxy + "\"");
        factory = getFactory();
    }

    /**
     * 系统退出自动关闭，eclipse的强制关闭、linux的kill进程命令无法触发此方法，需手动关闭进程
     *
     * @author 刘太信
     * @date 2017年8月18日 下午9:10:00
     */
    protected void addCloseProcessHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                logger.info("关闭session进程");
                shutdown();
            }
        }));
    }


    /**
     * 获取浏览器session工厂
     *
     * @return
     * @description
     * @author 王龙
     * @date 2017年10月10日 上午10:50:35
     */
//    private SessionFactory getFactory() {
//        return getFactory(false);
//    }

    /**
     * 获取浏览器session工厂
     *
     * @return
     * @description
     * @author 王龙
     * @date 2017年10月10日 上午10:50:35
     */
//    private SessionFactory getFactory(boolean isHeadless) {
//        SessionFactory factory = null;
//        if (isHeadless) {
//            // 代理且无窗口
//            factory = launcher.launch(Arrays.asList("--headless", "--disable-gpu"));
//            logger.info("创建sessionFactory：无代理IP，无视图窗口");
//        } else {
//            // 代理测试，但有窗口
//            factory = launcher.launch();
//            logger.info("创建sessionFactory：无代理IP，有视图窗口");
//        }
//        return factory;
//    }

    /**
     * 获取浏览器session工厂
     *
     * @param proxy
     * @return
     * @description
     * @author 王龙
     * @date 2017年10月10日 上午10:52:07
     */
//    private SessionFactory getFactory(String proxy) {
//        return getFactory(proxy, false);
//    }

    /**
     * 获取浏览器session工厂
     *
     * @return
     * @description
     * @author 王龙
     * @date 2017年10月10日 上午10:52:07
     */
//    private SessionFactory getFactory(String proxy, boolean isHeadless) {
//        SessionFactory factory = null;
//        if (StringUtils.isNotEmpty(proxy)) {
//            if (isHeadless) {
//                // 代理且无窗口
//                factory = launcher
//                        .launch(Arrays.asList("--headless", "--disable-gpu", "--proxy-server=\"http=" + proxy + "\""));
//                logger.info("创建sessionFactory：有代理IP【{}】，无视图窗口", proxy);
//            } else {
//                // 代理测试，但有窗口
//                factory = launcher.launch(Arrays.asList("--proxy-server=\"http=" + proxy + "\""));
//                logger.info("创建sessionFactory：有代理IP【{}】，有视图窗口", proxy);
//            }
//        } else {
//            factory = getFactory(isHeadless);
//        }
//        return factory;
//    }
    private SessionFactory getFactory() {
        if (null != factory) {
            factory.close();
        }
        factory = launcher.launch(configList);
        return factory;
    }

}
