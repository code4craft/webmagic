package us.codecraft.webmagic.scripts.languages;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

import us.codecraft.webmagic.Page;

public class Javascript extends Language {
    public Javascript() {
        super("javascript","js/defines.js","");
    }

    public void process(ScriptEngine engine, String defines, String script, Page page) throws ScriptException {
        engine.eval(defines + "\n" + script, engine.getContext());
    }
}