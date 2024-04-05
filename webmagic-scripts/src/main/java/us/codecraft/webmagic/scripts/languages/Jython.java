package us.codecraft.webmagic.scripts.languages;

import java.util.Iterator;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

import org.python.core.PyDictionary;

import us.codecraft.webmagic.Page;

public class Jython extends Language {
    public Jython() {
        super("jython","python/defines.py","");
    }

    public void process(ScriptEngine engine, String defines, String script, Page page) throws ScriptException {
        engine.eval(defines + "\n" + script, engine.getContext());
        PyDictionary oJython = (PyDictionary) engine.get("result");
        Iterator it = oJython.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            page.getResultItems().put(pairs.getKey().toString(), pairs.getValue());
        }
    }
}