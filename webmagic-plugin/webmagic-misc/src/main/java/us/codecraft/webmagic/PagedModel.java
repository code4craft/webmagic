package us.codecraft.webmagic;

import java.util.Collection;

/**
 * @author code4crafter@gmail.com <br>
 * @date: 13-8-4 <br>
 * Time: 下午5:18 <br>
 */
public interface PagedModel {

    public String getPageKey();

    public Collection<String> getOtherPages();

    public String getPage();

    public PagedModel combine(PagedModel pagedModel);

}
