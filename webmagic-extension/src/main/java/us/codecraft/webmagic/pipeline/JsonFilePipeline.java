package us.codecraft.webmagic.pipeline;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 持久化到文件的接口。
 *
 * @author code4crafter@gmail.com <br>
 *         Date: 13-4-21
 *         Time: 下午6:28
 */
public class JsonFilePipeline implements Pipeline {

    private String path = "/data/webmagic/";

    private Logger logger = Logger.getLogger(getClass());

    /**
     * 新建一个FilePipeline，使用默认保存路径"/data/webmagic/"
     */
    public JsonFilePipeline() {

    }

    /**
     * 新建一个FilePipeline
     *
     * @param path 文件保存路径
     */
    public JsonFilePipeline(String path) {
        this.path = path;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        String path = this.path + "/" + task.getUUID() + "/";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(path + DigestUtils.md5Hex(resultItems.getRequest().getUrl()) + ".json"));
            printWriter.write(JSON.toJSONString(resultItems.getAll()));
            printWriter.close();
        } catch (IOException e) {
            logger.warn("write file error", e);
        }
    }
}
