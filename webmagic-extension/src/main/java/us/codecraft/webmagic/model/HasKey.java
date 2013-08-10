package us.codecraft.webmagic.model;

/**
 * 标志一个Model的key。<br>
 * 实现了这个接口的Model在输出时会使用getKey()作为标志(例如JsonFilePageModelPipeline中持久化的文件名)。<br>
 * 如果持久化的文件名是乱码，请再运行的环境变量里加上LANG=zh_CN.UTF-8 。<br>
 *
 * @author code4crafter@gmail.com <br>
 *         Date: 13-8-10 <br>
 *         Time: 上午7:39 <br>
 */
public interface HasKey {

    /**
     * 在输出时会使用key作为标志(例如JsonFilePageModelPipeline中持久化的文件名)。
     *
     * @return key
     */
    public String key();
}
