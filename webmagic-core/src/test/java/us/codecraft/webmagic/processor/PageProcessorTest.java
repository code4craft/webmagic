package us.codecraft.webmagic.processor;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;

public class PageProcessorTest {

    @Test
    public void testGetSite() {
        Site actualSite = new PageProcessor() {

            @Override
            public void process(Page page) {
            }

        }.getSite();

        assertEquals(Site.me(), actualSite);

        actualSite = new PageProcessor() {

            @Override
            public void process(Page page) {
            }

            @Override
			public Site getSite() {
                return Site.me().setTimeOut(123);
            };

        }.getSite();

        assertEquals(Site.me().setTimeOut(123), actualSite);
    }

}
