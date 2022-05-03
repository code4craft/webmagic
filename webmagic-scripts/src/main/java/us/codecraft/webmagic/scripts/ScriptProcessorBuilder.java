package us.codecraft.webmagic.scripts;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import org.apache.commons.io.IOUtils;


/**
 * @author code4crafter@gmail.com
 * @since 0.4.1
 */
public class ScriptProcessorBuilder {

    private static final Language DefaultLanguage = Language.JavaScript;

    private Language language = DefaultLanguage;

    private String script;

    private int threadNum = 1;

    private ScriptProcessorBuilder() {
    }

    public static ScriptProcessorBuilder custom() {
        return new ScriptProcessorBuilder();
    }

    public ScriptProcessorBuilder language(Language language) {
        this.language = language;
        return this;
    }

    public ScriptProcessorBuilder scriptFromFile(String fileName) {
        try {
            InputStream resourceAsStream = new FileInputStream(fileName);
            this.script = IOUtils.toString(resourceAsStream, Charset.defaultCharset());
        } catch (IOException e) {
            //wrap IOException because I prefer a runtime exception...
            throw new IllegalArgumentException(e);
        }
        return this;
    }

    public ScriptProcessorBuilder scriptFromClassPathFile(String fileName) {
        try {
            InputStream resourceAsStream = ScriptProcessor.class.getClassLoader().getResourceAsStream(fileName);
            this.script = IOUtils.toString(resourceAsStream, Charset.defaultCharset());
        } catch (IOException e) {
            //wrap IOException because I prefer a runtime exception...
            throw new IllegalArgumentException(e);
        }
        return this;
    }

    public ScriptProcessorBuilder script(String script) {
        this.script = script;
        return this;
    }

    public ScriptProcessorBuilder thread(int threadNum) {
        this.threadNum = threadNum;
        return this;
    }

    public ScriptProcessor build(){
        return new ScriptProcessor(language,script,threadNum);
    }

}
