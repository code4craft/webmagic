package us.codecraft.webmagic.scripts;

import org.apache.commons.io.IOUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.io.IOException;
import java.io.InputStream;

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
            defines = IOUtils.toString(resourceAsStream);
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
                engine.eval(defines + "\n" + script, context);
//            switch (language) {
//                case JavaScript:
//                    NativeObject o = (NativeObject) engine.get("result");
//                    if (o != null) {
//                        for (Map.Entry<Object, Object> objectObjectEntry : o.entrySet()) {
//                            page.getResultItems().put(objectObjectEntry.getKey().toString(), objectObjectEntry.getValue());
//                        }
//                    }
//                    break;
//                case JRuby:
//                    Object o1 = engine.get("result");
//                    break;
//            }
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
