package us.codecraft.webmagic.scripts;


import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import org.apache.commons.io.IOUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scripts.languages.Language;

/**
 * @author code4crafter@gmail.com
 * @since 0.4.1
 */
public class ScriptProcessor implements PageProcessor {

    private ScriptEnginePool enginePool;

    private String defines;

    private String script;

    private final Language language;

    private Site site = Site.me();

    public ScriptProcessor(Language language, String script, int threadNum) {
        if (language == null || script == null) {
            throw new IllegalArgumentException("language and script must not be null!");
        }
        this.language = language;
        enginePool = new ScriptEnginePool(language, threadNum);
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(language.getDefineFile());
        try {
            defines = IOUtils.toString(resourceAsStream, Charset.defaultCharset());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        this.script = script;
    }

    @Override
    public void process(Page page) {
        ScriptEngine engine = enginePool.getEngine();
        try {
            ScriptContext context = engine.getContext();
            context.setAttribute("page", page, ScriptContext.ENGINE_SCOPE);
            context.setAttribute("config", site, ScriptContext.ENGINE_SCOPE);
            try {
                this.language.process(engine, defines, script, page);
            } catch (ScriptException e) {
                e.printStackTrace();
            }
        } finally {
            enginePool.release(engine);
        }
    }


    @Override
    public Site getSite() {
        return site;
    }

}
