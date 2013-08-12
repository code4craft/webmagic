package us.codecraft.webmagic.selector;

import com.jayway.jsonpath.JsonPath;

import java.util.ArrayList;
import java.util.List;

/**
 * @author code4crafter@gmail.com <br>
 *         Date: 13-8-12 <br>
 *         Time: 下午12:54 <br>
 */
public class JsonPathSelector implements Selector {

    private String jsonPathStr;

    private JsonPath jsonPath;

    public JsonPathSelector(String jsonPathStr) {
        this.jsonPathStr = jsonPathStr;
        this.jsonPath = JsonPath.compile(jsonPathStr);
    }

    @Override
    public String select(String text) {
        Object object = jsonPath.read(text);
        if (object == null) {
            return null;
        }
        if (object instanceof List) {
            List list = (List) object;
            if (list != null && list.size() > 0) {
                return list.iterator().next().toString();
            }
        }
        return object.toString();
    }

    @Override
    public List<String> selectList(String text) {
        List<String> list = new ArrayList<String>();
        Object object = jsonPath.read(text);
        if (object == null) {
            return list;
        }
        if (object instanceof List) {
            return (List<String>)object;
        } else {
            list.add(object.toString());
        }
        return list;
    }
}
