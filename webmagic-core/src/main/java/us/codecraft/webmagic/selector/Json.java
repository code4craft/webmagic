package us.codecraft.webmagic.selector;

import com.alibaba.fastjson.JSON;
import us.codecraft.xsoup.XTokenQueue;

import java.util.List;

/**
 * parse json
 * @author code4crafter@gmail.com
 * @since 0.5.0
 */
public class Json extends PlainText {

    public Json(List<String> strings) {
        super(strings);
    }

    public Json(String text) {
        super(text);
    }

    /**
     * remove padding for JSONP
     * @param padding
     * @return
     */
    public Json removePadding(String padding) {
        String text = getFirstSourceText();
        XTokenQueue tokenQueue = new XTokenQueue(text);
        tokenQueue.consumeWhitespace();
        tokenQueue.consume(padding);
        tokenQueue.consumeWhitespace();
        String chompBalanced = tokenQueue.chompBalancedNotInQuotes('(', ')');
        return new Json(chompBalanced);
    }

    public <T> T toObject(Class<T> clazz) {
        if (getFirstSourceText() == null) {
            return null;
        }
        return JSON.parseObject(getFirstSourceText(), clazz);
    }

    public <T> List<T> toList(Class<T> clazz) {
        if (getFirstSourceText() == null) {
            return null;
        }
        return JSON.parseArray(getFirstSourceText(), clazz);
    }

    @Override
    public Selectable jsonPath(String jsonPath) {
        JsonPathSelector jsonPathSelector = new JsonPathSelector(jsonPath);
        return selectList(jsonPathSelector,getSourceTexts());
    }
}
