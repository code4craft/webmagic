package us.codecraft.webmagic.samples.scheduler;

import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.PriorityScheduler;

import java.util.List;

import static us.codecraft.webmagic.selector.Selectors.regex;
import static us.codecraft.webmagic.selector.Selectors.xpath;

/**
 * @author code4crafter@gmail.com
 */
public class ZipCodePageProcessor implements PageProcessor {

    private Site site = Site.me().setCharset("gb2312").setSleepTime(0).addStartUrl("http://www.ip138.com/post/");

    @Override
    public void process(Page page) {
        if (page.getUrl().toString().equals("http://www.ip138.com/post/")) {
            processCountry(page);
        } else if (page.getUrl().regex("http://www\\.ip138\\.com/post/\\w+[/]?$").toString() != null) {
            processProvince(page);
        } else {
            processDistrict(page);
        }

    }

    private void processCountry(Page page) {
        List<String> provinces = page.getHtml().xpath("//*[@id=\"newAlexa\"]/table/tbody/tr/td").all();
        for (String province : provinces) {
            String link = xpath("//@href").select(province);
            String title = xpath("/text()").select(province);
            Request request = new Request(link).setPriority(0).putExtra("province", title);
            page.addTargetRequest(request);
        }
    }

    private void processProvince(Page page) {
        //这里仅靠xpath没法精准定位，所以使用正则作为筛选，不符合正则的会被过滤掉
        List<String> districts = page.getHtml().xpath("//body/table/tbody/tr/td").regex(".*http://www\\.ip138\\.com/post/\\w+/\\w+.*").all();
        for (String district : districts) {
            String link = xpath("//@href").select(district);
            String title = xpath("/text()").select(district);
            Request request = new Request(link).setPriority(1).putExtra("province", page.getRequest().getExtra("province")).putExtra("district", title);
            page.addTargetRequest(request);
        }
    }

    private void processDistrict(Page page) {
        String province = page.getRequest().getExtra("province").toString();
        String district = page.getRequest().getExtra("district").toString();
        List<String> counties = page.getHtml().xpath("//body/table/tbody/tr").regex(".*<td>\\d+</td>.*").all();
        String regex = "<td[^<>]*>([^<>]+)</td><td[^<>]*>([^<>]+)</td><td[^<>]*>([^<>]+)</td><td[^<>]*>([^<>]+)</td>";
        for (String county : counties) {
            String county0 = regex(regex, 1).select(county);
            String county1 = regex(regex, 2).select(county);
            String zipCode = regex(regex, 3).select(county);
            page.putField("result", StringUtils.join(new String[]{province, district,
                    county0, county1, zipCode}, "\t"));
        }
        List<String> links = page.getHtml().links().regex("http://www\\.ip138\\.com/post/\\w+/\\w+").all();
        for (String link : links) {
            page.addTargetRequest(new Request(link).setPriority(2).putExtra("province", province).putExtra("district", district));
        }

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new ZipCodePageProcessor()).scheduler(new PriorityScheduler()).run();
    }
}
