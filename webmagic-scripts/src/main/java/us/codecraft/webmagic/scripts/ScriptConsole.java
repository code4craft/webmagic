package us.codecraft.webmagic.scripts;

import org.apache.commons.cli.*;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.utils.WMCollections;

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
            alias.put(Language.JavaScript, WMCollections.<String>newHashSet("js", "javascript", "JavaScript", "JS"));
            alias.put(Language.JRuby, WMCollections.<String>newHashSet("ruby", "jruby", "Ruby", "JRuby"));
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
                .language(params.getLanguage()).scriptFromFile(params.getScriptFileName()).thread(params.getThread()).build();
        pageProcessor.getSite().setSleepTime(params.getSleepTime());
        pageProcessor.getSite().setRetryTimes(3);
        pageProcessor.getSite().setAcceptStatCode(WMCollections.<Integer>newHashSet(200, 404,403, 500,502));
        Spider spider = Spider.create(pageProcessor).thread(params.getThread());
        spider.clearPipeline().addPipeline(new Pipeline() {
            @Override
            public void process(ResultItems resultItems, Task task) {

            }
        });
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
            options.addOption(new Option("l", "language", true, "language"));
            options.addOption(new Option("t", "thread", true, "thread"));
            options.addOption(new Option("f", "file", true, "script file"));
            options.addOption(new Option("i", "input", true, "input file"));
            options.addOption(new Option("s", "sleep", true, "sleep time"));
            options.addOption(new Option("g", "logger", true, "sleep time"));
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
        if (commandLine.hasOption("g")) {
            configLogger(commandLine.getOptionValue("g"));
        }
        params.setUrls(commandLine.getArgList());
        return params;
    }

    private static void configLogger(String value) {
        Logger rootLogger = Logger.getRootLogger();
        if ("debug".equalsIgnoreCase(value)) {
            rootLogger.setLevel(Level.DEBUG);
        } else if ("info".equalsIgnoreCase(value)) {
            rootLogger.setLevel(Level.INFO);
        } else if ("warn".equalsIgnoreCase(value)) {
            rootLogger.setLevel(Level.WARN);
        } else if ("trace".equalsIgnoreCase(value)) {
            rootLogger.setLevel(Level.TRACE);
        } else if ("off".equalsIgnoreCase(value)) {
            rootLogger.setLevel(Level.OFF);
        } else if ("error".equalsIgnoreCase(value)) {
            rootLogger.setLevel(Level.ERROR);
        }
    }
}
