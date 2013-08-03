package us.codecraft.webmagic;

import java.util.HashMap;
import java.util.Map;

/**
 * 保存抽取结果的类，由PageProcessor处理得到，传递给{@link us.codecraft.webmagic.pipeline.Pipeline}进行持久化。<br>
 * @author code4crafter@gmail.com <br>
 * @date: 13-7-25 <br>
 * Time: 下午12:20 <br>
 */
public class ResultItems {

    private Map<String, Object> fields = new HashMap<String, Object>();

    private Request request;

    private boolean skip;

    public <T> T get(String key) {
        Object o = fields.get(key);
        if (o == null) {
            return null;
        }
        return (T) fields.get(key);
    }

    public  Map<String, Object> getAll() {
        return fields;
    }

    public <T> ResultItems put(String key, T value) {
        fields.put(key, value);
        return this;
    }

    public Request getRequest() {
        return request;
    }

    public ResultItems setRequest(Request request) {
        this.request = request;
        return this;
    }

    /**
     * 是否忽略这个页面，用于pipeline来判断是否对这个页面进行处理
     * @return 是否忽略 true 忽略
     */
    public boolean isSkip() {
        return skip;
    }


    /**
     * 设置是否忽略这个页面，用于pipeline来判断是否对这个页面进行处理
     * @param skip
     * @return this
     */
    public ResultItems setSkip(boolean skip) {
        this.skip = skip;
        return this;
    }
}
