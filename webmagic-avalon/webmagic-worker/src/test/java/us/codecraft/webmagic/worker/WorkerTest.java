package us.codecraft.webmagic.worker;

import org.junit.Test;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author code4crafter@gmail.com
 */
public class WorkerTest {

    @Test
    public void testWorkerAsSpiderContains() throws Exception {
        PageProcessor pageProcessor = mock(PageProcessor.class);
        Site site = mock(Site.class);
        when(pageProcessor.getSite()).thenReturn(site);
        when(site.getDomain()).thenReturn("codecraft.us");
        Worker worker = new Worker();
        Spider spider = Spider.create(pageProcessor);
        worker.addSpider(spider);
        assertThat(worker.getSpider("codecraft.us")).isEqualTo(spider);
    }
}
