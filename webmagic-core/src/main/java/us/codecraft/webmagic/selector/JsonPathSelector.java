package us.codecraft.webmagic.selector;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSON;
import com.jayway.jsonpath.JsonPath;

/**
 * JsonPath selector.<br>
 * Used to extract content from JSON.<br>
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.2.1
 */
public class JsonPathSelector implements Selector {

    private final String jsonPathStr;

    private final JsonPath jsonPath;

    public JsonPathSelector(String jsonPathStr) {
        this.jsonPathStr = jsonPathStr;
        this.jsonPath = JsonPath.compile(this.jsonPathStr);
    }

    @SuppressWarnings("unused")
    public String getJsonPathStr() {
        return jsonPathStr;
    }

    @Override
    public String select(String text) {
        Object object = jsonPath.read(text);
        if (object == null) {
            return null;
        }
        if (object instanceof List) {
            List<?> list = (List<?>) object;
            if (list.size() > 0) {
                return toString(list.iterator().next());
            }
        }
        return object.toString();
    }

    private String toString(Object object) {
        if (object instanceof Map) {
            return JSON.toJSONString(object);
        } else {
            return String.valueOf(object);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<String> selectList(String text) {
        List<String> list = new ArrayList<>();
        Object object = jsonPath.read(text);
        if (object == null) {
            return list;
        }
        if (object instanceof List) {
            List<Object> items = (List<Object>) object;
            for (Object item : items) {
                list.add(toString(item));
            }
        } else {
            list.add(toString(object));
        }
        return list;
    }
}
