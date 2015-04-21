package us.codecraft.webmagic.handler;

/**
 * @author code4crafer@gmail.com
 */
public abstract class PatternProcessor extends PatternRequestMatcher implements SubPipeline, SubPageProcessor {
    /**
     * @param pattern url pattern to handle
     */
    public PatternProcessor(String pattern) {
        super(pattern);
    }
}
