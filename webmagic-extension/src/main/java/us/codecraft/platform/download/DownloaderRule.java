package us.codecraft.platform.download;


import lombok.Getter;
import lombok.Setter;
import us.codecraft.platform.contants.ProviderType;
import us.codecraft.platform.download.proxy.provider.AdslHttpProvider;
import us.codecraft.platform.download.proxy.provider.HttpProvider;
import us.codecraft.platform.download.trigger.PageTrigger;

import java.util.List;
import java.util.regex.Pattern;

/**
 * downloader正则接口
 *
 * @author 王龙
 * @date 2017年10月30日 下午2:19:08
 */

@Getter
@Setter
public class DownloaderRule {

    private String ruleExpression;

    /**
     * downloader内置最大线程数量
     */
    private Integer threadNum = 3;

    /**
     * @return ：
     * @author : 王龙
     * @Description ： 用户自定义的错误识别码
     * @params ：
     * @Date ：2017/11/30 17:46
     */
    private List<String> errorTitleList;

    private ProviderType providerType;

    /**
     * 默认使用adls服务
     */
    private HttpProvider httpProvider = new AdslHttpProvider();

    private int downloaderType;

    /**
     * 获取Pattern对象
     *
     * @return
     * @description
     * @author 王龙
     * @date 2017年10月30日 下午2:19:08
     */
    public Pattern getPattern() {
        return Pattern.compile(this.ruleExpression);
    }

    public PageTrigger getPageTrigger() {
        if (null != errorTitleList) {
            return new PageTrigger(errorTitleList);
        } else {
            return new PageTrigger();
        }
    }
}
