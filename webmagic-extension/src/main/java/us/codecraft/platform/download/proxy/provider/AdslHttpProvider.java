package us.codecraft.platform.download.proxy.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.platform.utils.HttpProviderUtils;
import us.codecraft.webmagic.proxy.Proxy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ADSL代理
 *
 * @author 王龙
 * @date 2017年11月1日 下午3:00:14
 */
public class AdslHttpProvider implements HttpProvider {
    public static Logger logger = LoggerFactory.getLogger(AdslHttpProvider.class);
    private ReentrantLock reentrantLock = new ReentrantLock();
    private Condition changgeCondition = reentrantLock.newCondition();
    private AtomicBoolean changeStatus = new AtomicBoolean(false);

    /**
     * 切换代理
     *
     * @return
     * @author : 王龙
     * @date 2017年8月15日 下午6:09:17
     */
    @Override
    public Proxy change() {
        logger.info("开始切换ADSL");
        String oldIP = HttpProviderUtils.getIPV4();
        logger.debug("切换前IP:{}", oldIP);
        String newIP = "";
        int times = 0;
        do {
            restartAdsl();
            newIP = HttpProviderUtils.getIPV4();
            if (times != 0) {
                logger.warn("切换IP失败，重试第{}次", ++times);
            }
        } while (newIP.equals(oldIP) && times <= tryTime);
        logger.debug("切换后IP:{}", newIP);
        logger.info("ADSL切换结束");
        return null;
    }

    private void restartAdsl() {
        logger.warn("切换adsl------ing");
        String adslStop = "sudo adsl-stop";
        String stop = executeCommand(adslStop);
        logger.warn("断开ADSL{}", stop);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String adslStart = "sudo adsl-start";
        String start = executeCommand(adslStart);
        logger.warn("连接ADSL{}", start);
    }

    /**
     * 执行shell命令
     *
     * @param command
     * @return
     */
    private String executeCommand(String command) {
        StringBuffer output = new StringBuffer();
        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    @Override
    public AtomicBoolean getChangeStatus() {
        return changeStatus;
    }

    @Override
    public ReentrantLock getReentrantLock() {
        return reentrantLock;
    }

    @Override
    public Condition getChangeCondition() {
        return changgeCondition;
    }
}
