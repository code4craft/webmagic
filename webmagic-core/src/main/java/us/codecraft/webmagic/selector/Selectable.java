package us.codecraft.webmagic.selector;

import java.util.List;

/**
 * 可进行抽取的文本。<br>
 * @author code4crafter@gmail.com <br>
 * Date: 13-4-20
 * Time: 下午7:51
 */
public interface Selectable {

    /**
     * select list with xpath
     *
     * @param xpath
     * @return new Selectable after extract
     */
    public Selectable xpath(String xpath);

    /**
     * select list with css selector
     *
     * @param selector css selector expression
     * @return new Selectable after extract
     */
    public Selectable $(String selector);

    /**
     * select smart content with ReadAbility algorithm
     *
     * @return content
     */
    public Selectable smartContent();

    /**
     * select all links
     *
     * @return all links
     */
    public Selectable links();

    /**
     * select list with regex
     *
     * @param regex
     * @return new Selectable after extract
     */
    public Selectable regex(String regex);

    /**
     * replace with regex
     *
     * @param regex
     * @param replacement
     * @return new Selectable after extract
     */
    public Selectable replace(String regex, String replacement);

    /**
     * single string result
     *
     * @return single string result
     */
    public String toString();

    /**
     * multi string result
     *
     * @return multi string result
     */
    public List<String> toStrings();
}
