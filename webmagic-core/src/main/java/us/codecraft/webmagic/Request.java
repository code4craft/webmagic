package us.codecraft.webmagic;

/**
 * Request对象封装了待抓取的url信息。<br/>
 * 在PageProcessor中，Request对象可以通过{@link us.codecraft.webmagic.Page#getRequest()} 获取。<br/>
 * <br/>
 * Request对象包含一个extra属性，可以写入一些必须的上下文，这个特性在某些场合会有用。<br/>
 * <pre>
 *      Example:
 *          抓取<a href="${link}">${linktext}</a>时，希望提取链接link，并保存linktext的信息。
 *      在上一个页面：
 *      public void process(Page page){
 *          Request request = new Request(link,linktext);
 *          page.addTargetRequest(request)
 *      }
 *      在下一个页面：
 *      public void process(Page page){
 *          String linktext =  (String)page.getRequest().getExtra()[0];
 *      }
 * </pre>
 * @author code4crafter@gmail.com <br>
 * Date: 13-4-21
 * Time: 上午11:37
 */
public class Request {

    private String url;

    private Object[] extra;

    /**
     * 构建一个request对象
     * @param url 必须参数，待抓取的url
     * @param extra 额外参数，可以保存一些需要的上下文信息
     */
    public Request(String url, Object... extra) {
        this.url = url;
        this.extra = extra;
    }

    /**
     * 获取预存的对象
     * @return object[] 预存的对象数组
     */
    public Object[] getExtra() {
        return extra;
    }

    /**
     * 获取待抓取的url
     * @return url 待抓取的url
     */
    public String getUrl() {
        return url;
    }

}
