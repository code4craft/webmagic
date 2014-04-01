package us.codecraft.forger.property;

import java.util.List;
import java.util.Map;

/**
 * @author code4crafter@gmail.com
 */
public enum PropertyType {

    PropertyString,PropertyMap,PropertyList;

    public static PropertyType from(Class clazz){
        if (Map.class.isAssignableFrom(clazz)){
            return PropertyMap;
        }
        if (List.class.isAssignableFrom(clazz)){
            return PropertyList;
        }
        return PropertyString;
    }

}
