package us.codecraft.forger;

import us.codecraft.forger.property.Inject;
import us.codecraft.forger.property.format.Formatter;

import java.util.List;
import java.util.Map;

/**
 * @author code4crafter@gmail.com
 */
public class Bar {

    @Inject("bar")
    private String bar;

    @Inject
    private List<String> values;

    @Formatter(value = "", subClazz = Integer.class)
    @Inject
    private Map<String, Integer> idMap;

    public String getBar() {
        return bar;
    }

    public void setBar(String bar) {
        this.bar = bar;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public Map<String, Integer> getIdMap() {
        return idMap;
    }

    public void setIdMap(Map<String, Integer> idMap) {
        this.idMap = idMap;
    }
}
