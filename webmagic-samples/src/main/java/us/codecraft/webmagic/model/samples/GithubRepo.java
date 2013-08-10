package us.codecraft.webmagic.model.samples;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * @author code4crafter@gmail.com <br>
 *         Date: 13-8-10 <br>
 *         Time: 下午6:37 <br>
 */
@TargetUrl("https://github.com/code4craft/*")
public class GithubRepo {

    @ExtractBy("//h1[@class='entry-title']/strong/a/text()")
    private String name;

    public static void main(String[] args) {
        OOSpider.create(Site.me().addStartUrl("https://github.com/code4craft/"), new ConsolePageModelPipeline(), GithubRepo.class).run();
    }
}
