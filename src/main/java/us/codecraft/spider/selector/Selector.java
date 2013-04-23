package us.codecraft.spider.selector;

import java.util.List;

/**
 * User: cairne
 * Date: 13-4-20
 * Time: 下午8:02
 */
public interface Selector {

    public String select(String text);

    public List<String> selectList(String text);

}
