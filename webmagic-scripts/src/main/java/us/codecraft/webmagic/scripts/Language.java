package us.codecraft.webmagic.scripts;

/**
 * @author code4crafter@gmail.com
 */
public enum Language {

    JavaScript("javascript","js/defines.js",""),

    JRuby("jruby","ruby/defines.rb",""),

    Jython("jython","python/defines.py","");

    private String engineName;

    private String defineFile;

    private String gatherFile;

    Language(String engineName, String defineFile, String gatherFile) {
        this.engineName = engineName;
        this.defineFile = defineFile;
        this.gatherFile = gatherFile;
    }

    public String getEngineName() {
        return engineName;
    }

    public String getDefineFile() {
        return defineFile;
    }

    public String getGatherFile() {
        return gatherFile;
    }
}
