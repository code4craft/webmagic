package us.codecraft.webmagic.pipeline;

import org.apache.commons.codec.digest.DigestUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.selector.Selectable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author code4crafter@gmail.com <br>
 * Date: 13-4-21
 * Time: 下午6:28
 */
public class FilePipeline implements Pipeline {

    private String path = "/data/temp/webmagic/";

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
            PrintWriter printWriter = new PrintWriter(new FileWriter(path + DigestUtils.md5Hex(page.getUrl().toString()) + ".html"));
            printWriter.println("url:\t" + page.getUrl());
            for (Map.Entry<String, Selectable> entry : page.getFields().entrySet()) {
                printWriter.println(entry.getKey() + ":\t" + entry.getValue().toStrings());
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
