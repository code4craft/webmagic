package us.codecraft.webmagic.scripts;

import org.junit.Ignore;
import org.junit.Test;
import us.codecraft.webmagic.Spider;

/**
 * @author code4crafter@gmail.com
 * @since 0.4.1
 */
@Ignore
public class ScriptProcessorTest {

    @Test
    public void testJavaScriptProcessor() {
        ScriptProcessor pageProcessor = ScriptProcessorBuilder.custom().language(Language.JavaScript).scriptFromClassPathFile("js/oschina.js").build();
        pageProcessor.getSite().setSleepTime(0);
        Spider.create(pageProcessor).addUrl("http://my.oschina.net/flashsword/blog").setSpawnUrl(false).run();
    }

    @Test
    public void testRubyProcessor() {
        ScriptProcessor pageProcessor = ScriptProcessorBuilder.custom().language(Language.JRuby).scriptFromClassPathFile("ruby/oschina.rb").build();
        pageProcessor.getSite().setSleepTime(0);
        Spider.create(pageProcessor).addUrl("http://my.oschina.net/flashsword/blog").setSpawnUrl(false).run();
    }


    @Test
    public void testPythonProcessor() {
        ScriptProcessor pageProcessor = ScriptProcessorBuilder.custom().language(Language.Jython).scriptFromClassPathFile("python/oschina.py").build();
        pageProcessor.getSite().setSleepTime(0);
        Spider.create(pageProcessor).addUrl("http://my.oschina.net/flashsword/blog").setSpawnUrl(false).run();
    }
}
