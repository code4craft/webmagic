package us.codecraft.webmagic.pipeline;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.model.HasKey;
import us.codecraft.webmagic.model.PageModelPipeline;
import us.codecraft.webmagic.utils.FilePersistentBase;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * JSON格式持久化到文件的接口。<br>
 * 如果持久化的文件名是乱码，请再运行的环境变量里加上LANG=zh_CN.UTF-8。<br>
 *
 * @author code4crafter@gmail.com <br>
 *         Date: 13-4-21
 *         Time: 下午6:28
 */
public class JsonFilePageModelPipeline extends FilePersistentBase implements PageModelPipeline {

    private Logger logger = Logger.getLogger(getClass());

    /**
     * 新建一个JsonFilePageModelPipeline，使用默认保存路径"/data/webmagic/"
     */
    public JsonFilePageModelPipeline() {
        setPath("/data/webmagic/");
    }

    /**
     * 新建一个JsonFilePageModelPipeline
     *
     * @param path 文件保存路径
     */
    public JsonFilePageModelPipeline(String path) {
        setPath(path);
    }

    @Override
    public void process(Object o, Task task) {
        String path = this.path + "/" + task.getUUID() + "/";
        try {
            String filename;
            if (o instanceof HasKey) {
                filename = path + ((HasKey)o).key() + ".json";
            } else {
                filename = path + DigestUtils.md5Hex(ToStringBuilder.reflectionToString(o)) + ".json";
            }
            PrintWriter printWriter = new PrintWriter(new FileWriter(getFile(filename)));
            printWriter.write(JSON.toJSONString(o));
            printWriter.close();
        } catch (IOException e) {
            logger.warn("write file error", e);
        }
    }
}
