package us.codecraft.webmagic.scripts.languages;

import java.util.Iterator;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

import org.jruby.RubyHash;

import us.codecraft.webmagic.Page;

public class JRuby extends Language {
    public JRuby() {
        super("jruby","ruby/defines.rb","");
    }

    public void process(ScriptEngine engine, String defines, String script, Page page) throws ScriptException {
        RubyHash oRuby = (RubyHash) engine.eval(defines + "\n" + script, engine.getContext());
        Iterator itruby = oRuby.entrySet().iterator();
        while (itruby.hasNext()) {
            Map.Entry pairs = (Map.Entry) itruby.next();
            page.getResultItems().put(pairs.getKey().toString(), pairs.getValue());
        }
    }
} 