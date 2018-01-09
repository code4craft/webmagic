package us.codecraft.webmagic;

import org.junit.Ignore;
import org.junit.Test;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.SimplePageProcessor;
import us.codecraft.webmagic.samples.HuxiuProcessor;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;

/**
 * @author code4crafter@gmail.com <br>
 * Date: 13-4-20
 * Time: 下午7:46
 */
public class SpiderTest {


    @Ignore
    @Test
    public void testSpider() throws InterruptedException {
        Spider me = Spider.create(new HuxiuProcessor()).addPipeline(new FilePipeline());
        me.run();
    }

    @Ignore
    @Test
    public void testGlobalSpider(){
//        PageProcessor pageProcessor = new MeicanProcessor();
//        Spider.me().pipeline(new FilePipeline()).scheduler(new FileCacheQueueScheduler(pageProcessor.getSite(),"/data/temp/webmagic/cache/")).
//                processor(pageProcessor).run();
        SimplePageProcessor pageProcessor2 = new SimplePageProcessor( "http://www.diaoyuweng.com/thread-*-1-1.html");
        System.out.println(pageProcessor2.getSite().getCharset());
        pageProcessor2.getSite().setSleepTime(500);
        Spider.create(pageProcessor2).addUrl("http://www.diaoyuweng.com/home.php?mod=space&uid=88304&do=thread&view=me&type=thread&from=space").addPipeline(new FilePipeline()).scheduler(new FileCacheQueueScheduler("/data/temp/webmagic/cache/")).
                run();


    }

    @Ignore
    @Test
    public void test(){
        System.out.println(System.getProperty("java.io.tmpdir"));
    }


    @Ignore
    @Test
    public void languageSchema() {


        /**
         *
         * _hrefs = regex("<a[^<>]*href=[\"']{1}(/yewu/.*?)[\"']{1}")
         * title = r(""<title>(.*)</title>"")
         * body = x("//dd[@class='w133']")
         *
         * site.domain = "sh.58.com"
         * site.ua=""
         * site.cookie="aa:bb"
         *
         */

        /**
         *
         *
         * if (page == r('') && refer(1) == 1) {
         *
         *      type = _refer(1)
         *      content = _text.t().c()
         *      title = x("asd@asd").r("",1)
         *      body[r(_currentUrl).g(1)] = body[r(_currentUrl).g(1)] + (x("").r("",1,2).c())
         *
         *      body=body[r(_currentUrl).g(1)]
         *      tags[%] = (tags[%] + xpath('')) . r('')
         *
         *      _targetUrls.add('' + x('').r(''))
         *      _sourceUrls.add()
         *      _header.put("","");
         *      _cookie.add("asdsadasdsa");
         *
         *
         * }
         *
         * _cookie.add(_cookie[''])
         *
         * if (page == r('') && refer(1) == 1)
         *  (
         *      _targetUrl = '' + x('') & r('')
         *      _sourceUrl = ''
         *  )
         *
         */

        /**
         * <condition></>
         * <selector>
         *     <fields>
         *
         *     <type>
         *         <selector></selector>
         *         <selector></selector>
         *     </type>
         *         </>
         *     </>
         */

        /**
         *
         * if (model.url('') && model.refer(1) == 1)
         *  (
         *
         *      model.set(type, model.refer(1))
         *      content = t(_html) > c()
         *      title = x(_html, 'asd@asd') > r('',1)
         *      body[r(_currentUrl).g(1)] = body[r(_currentUrl).g(1)] + (x('') > r('',1,2) > c()) | x('')
         *      tags[%] = tags + xpath('') > r('')
         *      model.setTargetUrl();
         *
         *      _targetUrl = '' + x('') & r('')
         *      _sourceUrl = ''
         * )
         *
         * _cookie.add(_cookie[''])
         *
         * if (page == r('') && refer(1) == 1)
         *  (
         *      _targetUrl = '' + x('') & r('')
         *      _sourceUrl = ''
         *  )
         *
         */
    }
}
