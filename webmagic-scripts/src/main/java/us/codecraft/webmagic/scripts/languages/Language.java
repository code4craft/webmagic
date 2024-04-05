package us.codecraft.webmagic.scripts.languages;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import us.codecraft.webmagic.Page;

/**
 * @author FrancoisGib
 */
public abstract class Language {
    public Language(String engineName, String defineFile, String gatherFile) {
        this.engineName = engineName;
        this.defineFile = defineFile;
        this.gatherFile = gatherFile;
    }

    private String engineName;

    private String defineFile;

    private String gatherFile;

    public String getEngineName() {
        return engineName;
    }

    public String getDefineFile() {
        return defineFile;
    }

    public String getGatherFile() {
        return gatherFile;
    }

    public abstract void process(ScriptEngine engine, String defines, String script, Page page) throws ScriptException;
}
