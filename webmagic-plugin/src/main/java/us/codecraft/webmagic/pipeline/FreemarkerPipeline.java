package us.codecraft.webmagic.pipeline;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.codec.digest.DigestUtils;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author code4crafter@gmail.com <br>
 *         Date: 13-6-8
 *         Time: 下午9:00
 */
public class FreemarkerPipeline implements Pipeline {

    private Configuration configuration;

    private Template template;

    private String path = "/data/temp/webmagic/ftl/";

    public FreemarkerPipeline(String template, String path) throws IOException {
        configuration = new Configuration();
        configuration.setDirectoryForTemplateLoading(new File(this.getClass().getClassLoader().getResource("ftl/").getFile()));
        this.template = configuration.getTemplate(template);
        this.path = path;
        new File(path);
    }

    public FreemarkerPipeline(String template) throws IOException {
        this(template, "/data/temp/webmagic/ftl/");
    }


    @Override
    public void process(ResultItems resultItems, Task task) {
        if (resultItems.isSkip()) {
            return;
        }
        String path = this.path + "" + task.getUUID() + "/";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(path + DigestUtils.md5Hex(resultItems.getRequest().getUrl()) + ".html"));
            template.process(resultItems.getAll(), printWriter);
            printWriter.close();
        } catch (TemplateException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
