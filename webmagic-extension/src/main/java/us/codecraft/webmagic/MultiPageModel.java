package us.codecraft.webmagic;

import java.util.Collection;

/**
 * 实现此接口以进行支持爬虫分页抓取。<br>
 * @author code4crafter@gmail.com <br>
 * Date: 13-8-4 <br>
 * Time: 下午5:18 <br>
 */
public interface MultiPageModel {

    public String getPageKey();

    public Collection<String> getOtherPages();

    public String getPage();

    public MultiPageModel combine(MultiPageModel multiPageModel);

}
