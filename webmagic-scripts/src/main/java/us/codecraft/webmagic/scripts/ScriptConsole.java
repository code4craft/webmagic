package us.codecraft.webmagic.scripts;

import org.apache.commons.cli.*;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.scripts.config.CommandLineOption;
import us.codecraft.webmagic.utils.WMCollections;

import java.util.List;

/**
 * @author code4crafter@gmail.com / FrancoisGib
 * @since 0.4.1
 */
public class ScriptConsole {
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
        List<CommandLineOption> options = CommandLineOption.getAllOptions();
        for (CommandLineOption option : options)
            option.addParamOptionIfInCommandLine(params, commandLine);
        return params;
    }
}