package us.codecraft.webmagic.configurable;

import us.codecraft.webmagic.selector.JsonPathSelector;
import us.codecraft.webmagic.selector.Selector;

import static us.codecraft.webmagic.selector.Selectors.*;

/**
 * @author code4crafter@gmail.com
 * @date 14-4-5
 */
public class ExtractRule {

    private String fieldName;

    private ExpressionType expressionType;

    private String expressionValue;

    private String[] expressionParams;

    private boolean multi = false;

    private volatile Selector selector;

    private boolean notNull = false;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public ExpressionType getExpressionType() {
        return expressionType;
    }

    public void setExpressionType(ExpressionType expressionType) {
        this.expressionType = expressionType;
    }

    public String getExpressionValue() {
        return expressionValue;
    }

    public void setExpressionValue(String expressionValue) {
        this.expressionValue = expressionValue;
    }

    public String[] getExpressionParams() {
        return expressionParams;
    }

    public void setExpressionParams(String[] expressionParams) {
        this.expressionParams = expressionParams;
    }

    public boolean isMulti() {
        return multi;
    }

    public void setMulti(boolean multi) {
        this.multi = multi;
    }

    public Selector getSelector() {
        if (selector == null) {
            synchronized (this) {
                if (selector == null) {
                    selector = compileSelector();
                }
            }
        }
        return selector;
    }

    private Selector compileSelector() {
        switch (expressionType) {
            case Css:
                if (expressionParams.length >= 1) {
                    return $(expressionValue, expressionParams[0]);
                } else {
                    return $(expressionValue);
                }
            case XPath:
                return xpath(expressionValue);
            case Regex:
                if (expressionParams.length >= 1) {
                    return regex(expressionValue, Integer.parseInt(expressionParams[0]));
                } else {
                    return regex(expressionValue);
                }
            case JsonPath:
                return new JsonPathSelector(expressionValue);
            default:
                return xpath(expressionValue);
        }
    }

    public void setSelector(Selector selector) {
        this.selector = selector;
    }

    public boolean isNotNull() {
        return notNull;
    }

    public void setNotNull(boolean notNull) {
        this.notNull = notNull;
    }
}
