package us.codecraft.webmagic.selector;

import java.util.List;

/**
 * Author: code4crafter@gmail.com
 * Date: 13-4-20
 * Time: 下午8:02
 */
public interface Selector {

    public String select(String text);

    public List<String> selectList(String text);

}
