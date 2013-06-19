package us.codecraft.webmagic.selector;

import java.util.List;

/**
 * @author code4crafter@gmail.com <br>
 * Date: 13-4-20
 * Time: 下午8:02
 */
interface Selector {

    public String select(String text);

    public List<String> selectList(String text);

}
