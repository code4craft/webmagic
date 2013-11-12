package us.codecraft.webmagic.scripts;

import com.google.common.collect.Sets;
import org.apache.commons.cli.*;
import us.codecraft.webmagic.Spider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author code4crafter@gmail.com
 * @since 0.4.1
 */
public class ScriptConsole {

    private static class Params {
        Language language = Language.JavaScript;
        String scriptFileName;
        List<String> urls;
        int thread = 1;
        int sleepTime = 1000;
        private static Map<Language, Set<String>> alias = new HashMap<Language, Set<String>>();

        static {
            alias.put(Language.JavaScript, Sets.<String>newHashSet("js", "javascript", "JavaScript", "JS"));
            alias.put(Language.JRuby, Sets.<String>newHashSet("ruby", "jruby", "Ruby", "JRuby"));
        }

        public void setLanguagefromArg(String arg) {
            for (Map.Entry<Language, Set<String>> languageSetEntry : alias.entrySet()) {
                if (languageSetEntry.getValue().contains(arg)) {
                    this.language = languageSetEntry.getKey();
                    return;
                }
            }
        }

        private Language getLanguage() {
            return language;
        }

        private void setLanguage(Language language) {
            this.language = language;
        }

        private String getScriptFileName() {
            return scriptFileName;
        }

        private void setScriptFileName(String scriptFileName) {
            this.scriptFileName = scriptFileName;
        }

        private List<String> getUrls() {
            return urls;
        }

        private void setUrls(List<String> urls) {
            this.urls = urls;
        }

        private int getThread() {
            return thread;
        }

        private void setThread(int thread) {
            this.thread = thread;
        }

        private int getSleepTime() {
            return sleepTime;
        }

        private void setSleepTime(int sleepTime) {
            this.sleepTime = sleepTime;
        }
    }

    public static void main(String[] args) {
        Params params = parseCommand(args);
        startSpider(params);
    }

    private static void startSpider(Params params) {
        ScriptProcessor pageProcessor = ScriptProcessorBuilder.custom()
                .language(params.getLanguage()).scriptFromFile(params.getScriptFileName()).build();
        pageProcessor.getSite().setSleepTime(params.getSleepTime());
        Spider spider = Spider.create(pageProcessor).thread(params.getThread());
        if (params.getUrls() == null || params.getUrls().size() == 0) {
            System.err.println("Need at least one argument");
            System.out.println("Usage: java -jar webmagic.jar [-l language] -f script file [-t threadnum] [-s sleep time] url1 [url2 url3]");
            System.exit(-1);
        }
        for (String url : params.getUrls()) {
            spider.addUrl(url);
        }
        spider.run();
    }

    private static Params parseCommand(String[] args) {
        try {
            Options options = new Options();
            options.addOption(new Option("l", true, "language"));
            options.addOption(new Option("t", true, "thread"));
            options.addOption(new Option("f", true, "script file"));
            options.addOption(new Option("s", true, "sleep time"));
            CommandLineParser commandLineParser = new PosixParser();
            CommandLine commandLine = commandLineParser.parse(options, args);
            return readOptions(commandLine);
        } catch (Exception e) {
            e.printStackTrace();
            exit();
            return null;
        }
    }

    private static void exit() {
        System.err.println("Format error");
        System.out.println("Usage: java -jar webmagic.jar [-l language] -f script file [-t threadnum] [-s sleep time] url1 [url2 url3]");
        System.exit(-1);
    }

    private static Params readOptions(CommandLine commandLine) {
        Params params = new Params();
        if (commandLine.hasOption("l")) {
            String language = commandLine.getOptionValue("l");
            params.setLanguagefromArg(language);
        }
        if (commandLine.hasOption("f")) {
            String scriptFilename = commandLine.getOptionValue("f");
            params.setScriptFileName(scriptFilename);
        } else {
            exit();
        }
        if (commandLine.hasOption("s")) {
            Integer sleepTime = Integer.parseInt(commandLine.getOptionValue("s"));
            params.setSleepTime(sleepTime);
        }
        if (commandLine.hasOption("t")) {
            Integer thread = Integer.parseInt(commandLine.getOptionValue("t"));
            params.setThread(thread);
        }
        params.setUrls(commandLine.getArgList());
        return params;
    }
}
