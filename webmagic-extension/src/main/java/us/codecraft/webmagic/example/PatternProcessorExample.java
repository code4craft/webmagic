package us.codecraft.webmagic.example;

import org.apache.log4j.Logger;
import us.codecraft.webmagic.*;
import us.codecraft.webmagic.handler.CompositePageProcessor;
import us.codecraft.webmagic.handler.CompositePipeline;
import us.codecraft.webmagic.handler.PatternProcessor;
import us.codecraft.webmagic.handler.RequestMatcher;

/**
 * Created with IntelliJ IDEA.
 * User: Sebastian MA
 * Date: April 04, 2014
 * Time: 21:23
 */
public class PatternProcessorExample {

    private static Logger log = Logger.getLogger(PatternProcessorExample.class);

    public static void main(String... args) {

        // define a patternProcessor which handles only "http://item.jd.com/.*"
        PatternProcessor githubRepoProcessor = new PatternProcessor("https://github\\.com/[\\w\\-]+/[\\w\\-]+") {

            @Override
            public RequestMatcher.MatchOther processPage(Page page) {
                page.putField("reponame", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
                return RequestMatcher.MatchOther.YES;
            }

            @Override
            public RequestMatcher.MatchOther processResult(ResultItems resultItems, Task task) {
                log.info("Extracting from repo" + resultItems.getRequest());
                System.out.println("Repo name: "+resultItems.get("reponame"));
                return RequestMatcher.MatchOther.YES;
            }
        };

        PatternProcessor githubUserProcessor = new PatternProcessor("https://github\\.com/[\\w\\-]+") {

            @Override
            public RequestMatcher.MatchOther processPage(Page page) {
                log.info("Extracting from " + page.getUrl());
                page.addTargetRequests(page.getHtml().links().regex("https://github\\.com/[\\w\\-]+/[\\w\\-]+").all());
                page.addTargetRequests(page.getHtml().links().regex("https://github\\.com/[\\w\\-]+").all());
                page.putField("username", page.getHtml().xpath("//span[@class='vcard-fullname']/text()").toString());
                return RequestMatcher.MatchOther.YES;
            }

            @Override
            public RequestMatcher.MatchOther processResult(ResultItems resultItems, Task task) {
                System.out.println("User name: "+resultItems.get("username"));
                return RequestMatcher.MatchOther.YES;
            }
        };

        CompositePageProcessor pageProcessor = new CompositePageProcessor(Site.me().setDomain("github.com").setRetryTimes(3));
        CompositePipeline pipeline = new CompositePipeline();

        pageProcessor.setSubPageProcessors(githubRepoProcessor, githubUserProcessor);
        pipeline.setSubPipeline(githubRepoProcessor, githubUserProcessor);

        Spider.create(pageProcessor).addUrl("https://github.com/code4craft").thread(5).addPipeline(pipeline).runAsync();
    }

}
