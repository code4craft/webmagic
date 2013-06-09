webmagic
---------
####*一个网络爬虫工具包*

webmagic的发起源于工作中的需要，其定位是帮助开发者更便捷的开发一个垂直的网络爬虫。webmagic可以便捷的使用xpath和正则表达式进行链接和内容的提取，对于有Java和xpath或者正则基础的开发者，只需编写少量代码即可完成一个定制爬虫。

###哲学###

* Write Less, Do more.

	webmagic是一个开发者的工具包，它的目标是让开发者可以通过更少的代码，实现一个高质量的爬虫。webmagic内部还集成了一些常见的垂直性爬虫的功能，例如针对页面正文的Readability技术，可以直接对页面的正文进行智能提取。
	
	以下是爬取oschina博客的一段代码：
	
		Spider.me().processor(new SimplePageProcessor("http://my.oschina.net/", "http://my.oschina.net/*/blog/*")).run();

* 简单可用

	webmagic的功能覆盖整个爬虫的生命周期(链接提取、页面下载、内容抽取、持久化)，是一个完整的爬虫框架。但是与其他Full-Stack的框架不同，webmagic只引入少量约定，大部分功能都通过简单的API调用完成，目的是尽量降低开发者的学习成本。webmagic以jar包的形式存在，并且不依赖任何框架，在程序可以随处进行调用。

* 灵活性

	参考scrapy的设计，webmagic将爬虫的扩展点分为processor、schedular、downloader、pipeline三个模块，可以通过扩展这些接口实现强大的扩展功能。如可以通过多个Spider实现多线程抓取；可以通过扩展schedular实现断点续传乃至于分布式爬虫；可以通过扩展pipeline实现业务可定制的持久化功能。
	
------

###Get Started
	
webmagic定制的核心是PageProcessor接口。一个最简单的webmagic爬虫例子是这样的：

	Spider.me().processor(new SimplePageProcessor("http://my.oschina.net/", "http://my.oschina.net/*/blog/*")).run();
	
其中SimplePageProcessor实现如下：

    public class SimplePageProcessor implements PageProcessor {

        private String urlPattern;

        private static final String UA = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31";

        private Site site;

        public SimplePageProcessor(String startUrl, String urlPattern) {
            this.site = Site.me().setStartUrl(startUrl).
                    setDomain(UrlUtils.getDomain(startUrl)).setUserAgent(UA);
            this.urlPattern = "("+urlPattern.replace(".","\\.").replace("*","[^\"'#]*")+")";

        }

        @Override
        public void process(Page page) {
            List<String> requests = page.getHtml().as().rs(urlPattern).toStrings();
            page.addTargetRequests(requests);
            page.putField("title", page.getHtml().x("//title"));
            page.putField("content", page.getHtml().sc());
        }

        @Override
        public Site getSite() {
            return site;
        }
    }

### 示例

可参考作者博客[使用webmagic抓取页面并保存为wordpress文件](http://my.oschina.net/flashsword/blog/136846)

