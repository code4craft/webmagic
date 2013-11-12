package us.codecraft.webmagic.javascript;

import org.apache.commons.io.IOUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scripts.ScriptProcessor;
import us.codecraft.webmagic.scripts.ScriptProcessorBuilder;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author code4crafter@gmail.com
 * @since 0.4.1
 */
public class JsScriptProcessor implements PageProcessor {

    private ScriptEngine engine;

    private String defines;

    private String script;

    JsScriptProcessor(String script) throws IOException {
        ScriptEngineManager manager = new ScriptEngineManager();
        engine = manager.getEngineByName("javascript");
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("js/defines.js");
        defines = IOUtils.toString(resourceAsStream);
        this.script = script;
    }

    public static JsScriptProcessor fromFile(String fileName) {
        try {
            InputStream resourceAsStream = new FileInputStream(fileName);
            String script = IOUtils.toString(resourceAsStream);
            return new JsScriptProcessor(script);
        } catch (IOException e) {
            //wrap IOException because I prefer a runtime exception...
            throw new IllegalArgumentException(e);
        }
    }

    public static JsScriptProcessor fromClassPathFile(String fileName) {
        try {
            InputStream resourceAsStream = JsScriptProcessor.class.getClassLoader().getResourceAsStream(fileName);
            String script = IOUtils.toString(resourceAsStream);
            return new JsScriptProcessor(script);
        } catch (IOException e) {
            //wrap IOException because I prefer a runtime exception...
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void process(Page page) {
        ScriptContext context = engine.getContext();
        context.setAttribute("page", page, ScriptContext.ENGINE_SCOPE);
        try {
            engine.eval(defines + script, context);
        } catch (ScriptException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Site getSite() {
        return Site.me();
    }

    public static void main(String[] args) {
        ScriptProcessor pageProcessor = ScriptProcessorBuilder.custom().scriptFromClassPathFile("js/oschina.js").build();
        Spider.create(pageProcessor).addUrl("http://my.oschina.net/flashsword/blog").run();
    }
}
