package us.codecraft.webmagic.pipeline;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.codec.digest.DigestUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.utils.UrlUtils;

import java.io.*;

/**
 * User: cairne
 * Date: 13-6-8
 * Time: 下午9:00
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
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    public FreemarkerPipeline(String template) throws IOException {
        this(template, "/data/temp/webmagic/ftl/");
    }


    @Override
    public void process(Page page, Site site) {
        String domain = site.getDomain();
        domain = UrlUtils.getDomain(domain);
        String path = this.path + "" + domain + "/";
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(path + DigestUtils.md5Hex(page.getUrl().toString()) + ".html"));
            template.process(page.getFields(), printWriter);
            printWriter.close();
        } catch (TemplateException e) {
        } catch (IOException e) {
        }
    }
}
