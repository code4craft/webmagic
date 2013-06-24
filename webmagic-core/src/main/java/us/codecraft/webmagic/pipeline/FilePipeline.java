package us.codecraft.webmagic.pipeline;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author code4crafter@gmail.com <br>
 * Date: 13-4-21
 * Time: 下午6:28
 */
public class FilePipeline implements Pipeline {

    private String path = "/data/temp/webmagic/";

    private Logger logger = Logger.getLogger(getClass());

    public FilePipeline() {

    }

    public FilePipeline(String path) {
        this.path = path;
    }

    @Override
    public void process(Page page, Task task) {
        String path = this.path + "/" + task.getUUID() + "/";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(path + DigestUtils.md5Hex(page.getUrl().toString())));
            printWriter.println("url:\t" + page.getUrl());
            printWriter.println("html:\t" + page.getHtml());
            printWriter.close();
        } catch (IOException e) {
            logger.warn("write file error",e);
        }
    }
}
