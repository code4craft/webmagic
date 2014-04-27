package us.codecraft.forger;

import us.codecraft.forger.property.Property;
import us.codecraft.forger.property.PropertyLoader;

import java.util.List;
import java.util.Map;

/**
 * @author code4crafter@gmail.com
 */
public class Forger<T> {

    private final Class<T> clazz;

    private final PropertyLoader propertyLoader;

    public Forger(Class<T> clazz,PropertyLoader propertyLoader) {
        this.clazz = clazz;
        this.propertyLoader = propertyLoader;
    }

    public T forge(Map<String, Object> properties) throws IllegalAccessException, InstantiationException {
        T t = clazz.newInstance();
        propertyLoader.load(t, properties);
        return t;
    }

    public List<Property> getPropertyNames() {
        return propertyLoader.getProperties(clazz);
    }

    public Class<T> getClazz() {
        return clazz;
    }
}
