
import us.codecraft.webmagic.Page
import us.codecraft.webmagic.Site
import us.codecraft.webmagic.Spider
import us.codecraft.webmagic.processor.PageProcessor
import us.codecraft.webmagic.processor.example.GithubRepoPageProcessor

/**
 *
 * @author code4crafter@gmail.com
 * Date: 2017/5/31
 * Time: 下午11:33
 *
 */
class GithubRepoPageProcessor : PageProcessor {

    private val site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000)

    override fun process(page: Page) {
        page.addTargetRequests(page.html.links().regex("(https://github\\.com/[\\w\\-]+/[\\w\\-]+)").all())
        page.addTargetRequests(page.html.links().regex("(https://github\\.com/[\\w\\-])").all())
        page.putField("author", page.url.regex("https://github\\.com/(\\w+)/.*").toString())
        page.putField("name", page.html.xpath("//h1[@class='public']/strong/a/text()").toString())
        if (page.resultItems.get<Any>("name") == null) {
            //skip this page
            page.setSkip(true)
        }
        page.putField("readme", page.html.xpath("//div[@id='readme']/tidyText()"))
    }

    override fun getSite(): Site {
        return site
    }

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            Spider.create(GithubRepoPageProcessor()).addUrl("https://github.com/code4craft").thread(5).run()
        }
    }
}
