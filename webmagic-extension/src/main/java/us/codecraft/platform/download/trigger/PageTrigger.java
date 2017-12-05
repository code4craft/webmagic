package us.codecraft.platform.download.trigger;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.utils.HttpConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * 页面结果处理器
 *
 * @author :王龙
 * @Description
 * @Data 2017/11/15 14:40
 * @Modified By：
 */
public class PageTrigger {
    Logger logger = LoggerFactory.getLogger(PageTrigger.class);

    /**
     * @return ：
     * @author : 王龙
     * @Description ： 用户自定义的错误识别码
     * @params ：
     * @Date ：2017/11/30 17:46
     */
    private List<String> errorTitleList;

    public PageTrigger() {
        this.errorTitleList = new ArrayList<>();
    }

    public void setErrorTitleList(List<String> errorTitleList) {
        this.errorTitleList = errorTitleList;
    }

    public PageTrigger(List<String> errorTitleList) {
        this.errorTitleList = errorTitleList;
    }

    /**
     * isNeedChangeProxy
     *
     * @return ：
     * @author : 王龙
     * @Description ：是否需要切换通信服务
     * @params ：Page downloader后返回值
     * @Date ：2017/11/15 14:45
     */
    public boolean isNeedChangeProxy(Page page) {
        int statusCode = page.getStatusCode();
        String title = "";
        try {
            title = page.getHtml().getDocument().title();
        } catch (Exception e) {
            logger.info("页面获取title错误,{}", page.getStatusCode());
            return true;
        }

        if (statusCode == HttpConstant.StatusCode.CODE_200 && page.isDownloadSuccess()) {
            for (String errorTitle : errorTitleList) {
                if (StringUtils.isBlank(title) || title.contains(errorTitle)) {
                    logger.info("页面title={}", title);
                    return true;
                }
            }
            return false;
        } else {
            return true;
        }
    }
}
