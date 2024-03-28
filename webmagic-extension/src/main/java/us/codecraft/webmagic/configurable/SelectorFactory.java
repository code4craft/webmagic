package us.codecraft.webmagic.configurable;

import us.codecraft.webmagic.selector.JsonPathSelector;
import us.codecraft.webmagic.selector.Selector;

import static us.codecraft.webmagic.selector.Selectors.*;
public interface SelectorFactory {
    Selector compileSelector(String expressionValue, String[] expressionParams);
}

class CssSelectorFactory implements SelectorFactory {
    @Override
    public Selector compileSelector(String expressionValue, String[] expressionParams) {
        if (expressionParams.length >= 1) {
            return $(expressionValue, expressionParams[0]);
        } else {
            return $(expressionValue);
        }
    }
}

class XPathSelectorFactory implements SelectorFactory {
    @Override
    public Selector compileSelector(String expressionValue, String[] expressionParams) {
        return xpath(expressionValue);
    }
}

class RegexSelectorFactory implements SelectorFactory {
    @Override
    public Selector compileSelector(String expressionValue, String[] expressionParams) {
        if (expressionParams.length >= 1) {
            return regex(expressionValue, Integer.parseInt(expressionParams[0]));
        } else {
            return regex(expressionValue);
        }
    }
}

class JsonPathSelectorFactory implements SelectorFactory {
    @Override
    public Selector compileSelector(String expressionValue, String[] expressionParams) {
        return new JsonPathSelector(expressionValue);
    }
}

class SelectorCompiler {
    private final SelectorFactory selectorFactory;

    public SelectorCompiler(SelectorFactory selectorFactory) {
        this.selectorFactory = selectorFactory;
    }

    public Selector compileSelector(String expressionValue, String[] expressionParams) {
        return selectorFactory.compileSelector(expressionValue, expressionParams);
    }
}