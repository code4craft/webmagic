package us.codecraft.forger.property.format;

/**
 * @author code4crafter@gmail.com
 */
public class ObjectFormatterWithParams<T> implements ObjectFormatter<T> {

    private TypeFormatter<T> typeFormatter;

    private String[] params;

    public TypeFormatter getTypeFormatter() {
        return typeFormatter;
    }

    public ObjectFormatterWithParams<T> setTypeFormatter(TypeFormatter typeFormatter) {
        this.typeFormatter = typeFormatter;
        return this;
    }

    public String[] getParams() {
        return params;
    }

    public ObjectFormatterWithParams setParams(String[] params) {
        this.params = params;
        return this;
    }

    @Override
    public T format(String text) {
        return typeFormatter.format(text, params);
    }
}
