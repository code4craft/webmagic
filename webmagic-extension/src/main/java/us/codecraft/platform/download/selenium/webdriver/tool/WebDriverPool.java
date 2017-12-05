package us.codecraft.platform.download.selenium.webdriver.tool;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.platform.download.Pool;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/**
 * webDrive池，管理RemoteWebDriver实现类对象，避免重复创建driver<br/>
 * <b>使用webdriver池的理由:</b>
 * webDrive使用完毕就关闭进程时，程序会耗费大量时间进行创建进程关闭进程。故采用页面使用完就访问webdrive池进行复用
 * <b>注意2：</b>
 * 当采用webdrive池时，直接关闭进程如：eclipse的强制关闭、linux的kill进程命令都无法触发addShutdownHook钩子进行进程关闭
 * ，此时需要手动清除driver进程。
 * </ol>
 * </ul>
 *
 * @author 刘太信
 * @date 2017年7月29日 下午7:50:50
 */
public class WebDriverPool<T> extends Pool<RemoteWebDriver> {
    private static Logger logger = LoggerFactory.getLogger(WebDriverPool.class);
    private Class<T> clazz;

    /**
     * 页面完全加载超时时间
     */
    private Integer pageLoadTimeout = 30;
    /**
     * 识别对象的超时时间
     */
    private Integer implicitlyWait = 30;
    /**
     * 异步脚本的超时时间
     */
    private Integer scriptTimeout = 30;

    /**
     * webdriver全局配置
     */
    protected DesiredCapabilities caps;

    /**
     * 构造函数
     *
     * @author 刘太信
     * @date 2017年8月18日 下午9:07:20
     */
    private WebDriverPool() {
        addCloseProcessHook();
    }

    public WebDriverPool(int poolSize, DesiredCapabilities caps, Class<T> clazz) {
        this();
        this.caps = caps;
        this.clazz = clazz;
        setPoolSize(poolSize);
    }

    public WebDriverPool(DesiredCapabilities caps, Class<T> clazz) {
        this();
        this.caps = caps;
        this.clazz = clazz;
        setPoolSize(this.poolSize);
    }

    public DesiredCapabilities getCaps() {
        return this.caps;
    }

    @Override
    public RemoteWebDriver createNewWorker(){
        RemoteWebDriver worker=null;
        try {
            Constructor<T> constructor = (Constructor<T>) clazz.getConstructor(Capabilities.class);
            worker = (RemoteWebDriver) constructor.newInstance(caps);
            worker.setLogLevel(Level.OFF);
            worker.manage().timeouts().pageLoadTimeout(null == pageLoadTimeout ? 60 : pageLoadTimeout, TimeUnit.SECONDS);
            worker.manage().timeouts().implicitlyWait(null == implicitlyWait ? 60 : pageLoadTimeout, TimeUnit.SECONDS);
            worker.manage().timeouts().setScriptTimeout(null == scriptTimeout ? 60 : pageLoadTimeout, TimeUnit.SECONDS);
            logger.info("初始化新driver:{}", worker.getSessionId());
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            logger.error("clazz：{},构造失败,{}", clazz.getName(), e);
        }
        return worker;
    }



    /**
     * 关闭释放资源，不推荐手动调用，建议使用returnToPool
     *
     * @param webDriver
     * @author 刘太信
     * @date 2017年8月18日 下午9:09:29
     */
    @Override
    public void close(RemoteWebDriver webDriver) {
        workingCount.decrementAndGet();
        if (workerQueue.contains(webDriver)) {
            workerQueue.remove(webDriver);
        }
        logger.info("关闭worker：{}",webDriver.getSessionId());
        webDriver.close();
        webDriver.quit();
    }

    @Override
    public void setProxy(Proxy proxy) {
        caps.setCapability(CapabilityType.PROXY, proxy);
    }

    @Override
    public void setPoolSize(int poolSize) {
        this.workerQueue = new LinkedBlockingQueue<>(poolSize);
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
                logger.info("关闭WebDriver进程");
                shutdown();
            }
        }));
    }
}
