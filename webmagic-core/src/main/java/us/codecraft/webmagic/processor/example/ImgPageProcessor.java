package us.codecraft.webmagic.processor.example;

import org.apache.commons.collections.CollectionUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 这部分我们直接通过GithubRepoPageProcessor这个例子来介绍PageProcessor的编写方式。
 * 我将PageProcessor的定制分为三个部分，分别是爬虫的配置、页面元素的抽取和链接的发现。
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.3.2
 */
public class ImgPageProcessor implements PageProcessor {
    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    @Override
    public void process(Page page) {
        // 部分二：定义如何抽取页面信息，并保存下来
        String imgName = page.getHtml().xpath("//div[@class='content-pic']/a/img//@alt").toString();
        String imgUrl = page.getHtml().xpath("//div[@class='content-pic']/a/img//@src").toString();
        String urlpre = "";
        URL url = null;
        try {
            url = new URL(page.getUrl().toString());
            System.out.println("");
            urlpre = url.getProtocol()+"://" + url.getHost() +"/"+ url.getPath().split("/")[1];
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if(null!=imgUrl && ""!= imgUrl && ""!=imgName){
            try {
                DownImgUtil.downLoadFromUrl(imgUrl, imgName,"");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        // 部分三：从页面发现后续的url地址来抓取
        /**
         * page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all()用于获取所有满足"(https:/ /github\.com/\w+/\w+)"
         * 这个正则表达式的链接，page.addTargetRequests()则将这些链接加入到待抓取的队列中去。
         */
        List<String> urls = page.getHtml().links().xpath("/html/body/div[6]/div[3]//@href").all();
        List<String> handledUrls = new ArrayList<String>();
        if(CollectionUtils.isNotEmpty(urls)) {
            for(String temp : urls) {
                handledUrls.add(urlpre+temp);
            }
        }
        page.addTargetRequests(handledUrls);
        }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        //https://blog.csdn.net/bbc2005/article/details/80890829
        //https://www.cnblogs.com/sunny08/p/8038440.html
       // System.setProperty("javax.net.debug", "all"); //打印网络连接握手信息
        Spider.create(new ImgPageProcessor()).addUrl("http://www.mm131.com/xinggan/4170.html").thread(1).run();
    }
}
