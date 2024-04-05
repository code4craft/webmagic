package us.codecraft.webmagic.scripts.config;

import java.util.List;

import org.apache.commons.cli.CommandLine;

import lombok.Getter;
import us.codecraft.webmagic.scripts.Params;

public abstract class CommandLineOption {
    @Getter
    char option;

    public CommandLineOption(char option) {
        this.option = option;
    }

    protected abstract void addParamOption(Params params, CommandLine commandLine);

    public void addParamOptionIfInCommandLine(Params params, CommandLine commandLine) {
        if (commandLine.hasOption(this.option))
            this.addParamOption(params, commandLine);
    }

    public static List<CommandLineOption> getAllOptions() {
        return List.of(new OptionL(), new OptionF(), new OptionS(), new OptionT(), new OptionG());
    }
}

class OptionL extends CommandLineOption {
    public OptionL() {
        super('l');
    }

    protected void addParamOption(Params params, CommandLine commandLine) {
        String language = commandLine.getOptionValue("l");
        params.setLanguagefromArg(language);
    }
}

class OptionF extends CommandLineOption {
    public OptionF() {
        super('f');
    }

    protected void addParamOption(Params params, CommandLine commandLine) {
        String scriptFilename = commandLine.getOptionValue("f");
        params.setScriptFileName(scriptFilename);
    }
}

class OptionS extends CommandLineOption {
    public OptionS() {
        super('s');
    }

    protected void addParamOption(Params params, CommandLine commandLine) {
        Integer sleepTime = Integer.parseInt(commandLine.getOptionValue("s"));
        params.setSleepTime(sleepTime);
    }
}

class OptionT extends CommandLineOption {
    public OptionT() {
        super('t');
    }

    protected void addParamOption(Params params, CommandLine commandLine) {
        Integer thread = Integer.parseInt(commandLine.getOptionValue("t"));
        params.setThread(thread);
    }
}

class OptionG extends CommandLineOption {
    public OptionG() {
        super('g');
    }

    protected void addParamOption(Params params, CommandLine commandLine) {
        ConfigLogger.configLogger(commandLine.getOptionValue("g"));
    }
}