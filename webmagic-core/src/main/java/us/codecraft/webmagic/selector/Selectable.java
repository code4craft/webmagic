package us.codecraft.webmagic.selector;

import java.util.List;

/**
 * User: cairne
 * Date: 13-4-20
 * Time: 下午7:51
 */
public interface Selectable {

    /**
     * select with xpath
     *
     * @param xpath
     * @return new Selectable after extract
     */
    public Selectable x(String xpath);

    /**
     * select list with xpath
     *
     * @param xpath
     * @return new Selectable after extract
     */
    public Selectable xs(String xpath);

    /**
     * select smart content with ReadAbility algorithm
     *
     * @return content
     */
    public Selectable sc();

    /**
     * select a link
     *
     * @return
     */
    public Selectable a();

    /**
     * select all links
     *
     * @return
     */
    public Selectable as();


    /**
     * select with regex
     *
     * @param regex
     * @return new Selectable after extract
     */
    public Selectable r(String regex);

    /**
     * select list with regex
     *
     * @param regex
     * @return new Selectable after extract
     */
    public Selectable rs(String regex);

    /**
     * replace with regex
     *
     * @param regex
     * @param replacement
     * @return new Selectable after extract
     */
    public Selectable rp(String regex, String replacement);

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
