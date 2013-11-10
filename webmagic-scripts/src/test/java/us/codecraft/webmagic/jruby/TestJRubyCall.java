package us.codecraft.webmagic.jruby;

import org.junit.Test;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author code4crafter@gmail.com
 */
public class TestJRubyCall {

    @Test
    public void test() throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine rubyEngine = manager.getEngineByName("jruby");
        ScriptContext context = rubyEngine.getContext();

        context.setAttribute("a", "sad", ScriptContext.ENGINE_SCOPE);
//        rubyEngine.eval("", context);
        rubyEngine.eval("b=1; puts b", context);
    }
}
