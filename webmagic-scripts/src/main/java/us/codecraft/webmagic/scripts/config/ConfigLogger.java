package us.codecraft.webmagic.scripts.config;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Logger;
import org.slf4j.LoggerFactory;

public class ConfigLogger {
    /**
     * Log the config parameter. If the counter is less than the number of available
     * options then it means that the user entered an option
     * 
     * @param value The config string
     */
    public static void configLogger(String value) {
        List<Pair<String, Level>> options = List.of(
            Pair.of("debug", Level.DEBUG),
            Pair.of("info", Level.INFO),
            Pair.of("warn", Level.WARN),
            Pair.of("trace", Level.TRACE),
            Pair.of("off", Level.OFF),
            Pair.of("error", Level.ERROR));
        Pair<String, Level> option = options.get(0);
        int i = 1;
        while (i < options.size() && !option.getLeft().equalsIgnoreCase(value))
            option = options.get(i++);
        if (i < options.size()) {
            Logger rootLogger = (Logger) LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
            rootLogger.setLevel(option.getRight());
        }
    }
}
