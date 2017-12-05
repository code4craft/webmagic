package us.codecraft.platform.download;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.platform.contants.DownloaderType;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.downloader.HttpClientDownloader;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * 混合下载器，解封spider只能设置一个downloader的弊端，根据url进行动态切换下载器下载内容
 *
 * @author 王龙
 * @date 2017年10月31日 上午10:51:28
 */
public class CompositeDownloader implements Downloader {
    public Logger logger = LoggerFactory.getLogger(CompositeDownloader.class);
    /**
     * 子downloader集合
     */
    private List<SubDownloader> subDownloaders;

    private int threadNum = 1;
    /**
     * 内置downloader
     */
    HttpClientDownloader downloader = new HttpClientDownloader();

    private List<DownloaderRule> ruleList;

    public CompositeDownloader(List<DownloaderRule> ruleList) {
        this.ruleList = ruleList;
    }

    @Override
    public Page download(Request request, Task task) {
        Page page = Page.fail();
        for (SubDownloader subDownloader : subDownloaders) {
            if (subDownloader.match(request)) {
                page = subDownloader.download(request, task);
                return page;
            }
        }
        // 无配置或未找到匹配时使用内置ttpClientDownloader
        page = downloader.download(request, task);
        return page;
    }

    private List<SubDownloader> getPageDownloader() {
        subDownloaders = new ArrayList<>();
        ruleList.forEach(rule -> {
            if (null == rule.getThreadNum()) {
                rule.setThreadNum(threadNum);
            }
            SubDownloader subDownloader = null;
            String className = null;
            try {
                className = DownloaderType.getClassName(rule.getDownloaderType());
                Class<?> aClass = Class.forName(className);
                Constructor constructor = aClass.getConstructor(DownloaderRule.class);
                subDownloader = (SubDownloader) constructor.newInstance(rule);
            } catch (ClassNotFoundException e) {
                logger.error("{}", className, e);
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                logger.error("clazz：{},构造失败", className, e);
            }
            subDownloaders.add(subDownloader);
        });
        return subDownloaders;
    }

    /**
     * 优先级大于DownloaderRule,如果设置值将统一设置所有SubDownloader的线程数，建议不使用
     */
    @Override
    @Deprecated
    public void setThread(int threadNum) {
        this.threadNum = threadNum;
        getPageDownloader();
    }
}
