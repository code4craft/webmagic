package us.codecraft.webmagic.selector;

import java.util.List;

/**
 * Selectable text.<br>
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.1.0
 */
public interface Selectable {

    /**
     * select list with xpath
     *
     * @param xpath xpath
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
     * select list with css selector
     *
     * @param selector css selector expression
     * @param attrName attribute name of css selector
     * @return new Selectable after extract
     */
    public Selectable $(String selector, String attrName);

    /**
     * select list with css selector
     *
     * @param selector css selector expression
     * @return new Selectable after extract
     */
    public Selectable css(String selector);

    /**
     * select list with css selector
     *
     * @param selector css selector expression
     * @param attrName attribute name of css selector
     * @return new Selectable after extract
     */
    public Selectable css(String selector, String attrName);

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
     * select list with regex, default group is group 1
     *
     * @param regex regex
     * @return new Selectable after extract
     */
    public Selectable regex(String regex);

    /**
     * select list with regex
     *
     * @param regex regex
     * @param group group
     * @return new Selectable after extract
     */
    public Selectable regex(String regex, int group);

    /**
     * replace with regex
     *
     * @param regex regex
     * @param replacement replacement
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
     * single string result
     *
     * @return single string result
     */
    public String get();

    /**
     * if result exist for select
     *
     * @return true if result exist
     */
    public boolean match();

    /**
     * multi string result
     *
     * @return multi string result
     */
    public List<String> all();

    /**
     * extract by JSON Path expression
     *
     * @param jsonPath jsonPath
     * @return result
     */
    public Selectable jsonPath(String jsonPath);

    /**
     * extract by custom selector
     *
     * @param selector selector
     * @return result
     */
    public Selectable select(Selector selector);

    /**
     * extract by custom selector
     *
     * @param selector selector
     * @return result
     */
    public Selectable selectList(Selector selector);

    /**
     * get all nodes
     * @return result
     */
    public List<Selectable> nodes();
}
