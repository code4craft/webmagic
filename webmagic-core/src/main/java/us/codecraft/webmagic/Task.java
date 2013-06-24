package us.codecraft.webmagic;

/**
 * 抓取任务的抽象接口。<br>
 * @author code4crafter@gmail.com <br>
 * Date: 13-6-18
 * Time: 下午2:57
 */
public interface Task {

    /**
     * 返回唯一标志该任务的字符串，以区分不同任务。
     * @return uuid
     */
    public String getUUID();

    /**
     * 返回任务抓取的站点信息
     * @return site
     */
    public Site getSite();

}
