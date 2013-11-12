package us.codecraft.webmagic.scripts;

import org.apache.commons.io.IOUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author code4crafter@gmail.com
 * @since 0.4.1
 */
public class ScriptProcessor implements PageProcessor {

    private ScriptEngine engine;

    private String defines;

    private String script;

    private final Language language;

    private Site site = Site.me();

    public ScriptProcessor(Language language, String script) {
        if (language == null || script == null) {
            throw new IllegalArgumentException("language and script must not be null!");
        }
        this.language = language;
        ScriptEngineManager manager = new ScriptEngineManager();
        engine = manager.getEngineByName(language.getEngineName());
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
    }

    @Override
    public Site getSite() {
        return site;
    }

}
