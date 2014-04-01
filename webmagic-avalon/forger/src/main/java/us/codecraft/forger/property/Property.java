package us.codecraft.forger.property;

import us.codecraft.forger.property.format.ObjectFormatter;

import java.lang.reflect.Field;

/**
 * @author code4crafter@gmail.com
 */
public class Property {

    private String name;

    private PropertyType type;

    private Field field;

    private ObjectFormatter objectFormatter;

    public ObjectFormatter getObjectFormatter() {
        return objectFormatter;
    }

    public Property setObjectFormatter(ObjectFormatter objectFormatter) {
        this.objectFormatter = objectFormatter;
        return this;
    }

    public String getName() {
        return name;
    }

    public Property setName(String name) {
        this.name = name;
        return this;
    }

    public PropertyType getType() {
        return type;
    }

    public Property setType(PropertyType type) {
        this.type = type;
        return this;
    }

    public Field getField() {
        return field;
    }

    public Property setField(Field field) {
        this.field = field;
        return this;
    }

    public static Property fromField(Field field) {
        return new Property().setName(field.getName()).setType(PropertyType.from(field.getType())).setField(field);
    }

}
