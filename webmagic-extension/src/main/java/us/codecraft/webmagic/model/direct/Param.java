package us.codecraft.webmagic.model.direct;

import java.util.LinkedHashMap;

/**
 * @author code4crafter@gmail.com
 */
public class Param extends LinkedHashMap<String,Object>{

    @Override
    public Param put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
