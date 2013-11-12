package us.codecraft.webmagic.jruby;

import org.apache.commons.io.IOUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author code4crafter@gmail.com
 */
public class RubyScriptProcessor implements PageProcessor {

    private ScriptEngine rubyEngine;

    private String defines;

    private String script;

    public RubyScriptProcessor(String filename){
        ScriptEngineManager manager = new ScriptEngineManager();
        rubyEngine = manager.getEngineByName("jruby");
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("ruby/defines.rb");
        try {
            defines = IOUtils.toString(resourceAsStream);
            resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(filename);
            script = IOUtils.toString(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void process(Page page) {
        ScriptContext context = rubyEngine.getContext();
        context.setAttribute("page", page, ScriptContext.ENGINE_SCOPE);
        try {
            rubyEngine.eval(defines+script, context);
        } catch (ScriptException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Site getSite() {
        return Site.me();
    }

    public static void main(String[] args) {
        Spider.create(new RubyScriptProcessor("ruby/oschina.rb")).addUrl("http://my.oschina.net/flashsword/blog").run();
    }
}
