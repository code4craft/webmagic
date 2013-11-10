package us.codecraft.webmagic.js;

import org.junit.Test;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author code4crafter@gmail.com
 */
public class TestJsCall {

    @Test
    public void test() throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine rubyEngine = manager.getEngineByName("javascript");
        ScriptContext context = rubyEngine.getContext();

        context.setAttribute("a", "sad", ScriptContext.ENGINE_SCOPE);
//        rubyEngine.eval("", context);
        rubyEngine.eval("print(a)", context);
    }
}
