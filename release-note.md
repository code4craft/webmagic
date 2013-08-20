Release Notes
----
*2012-8-20* `version：0.2.1`

ComboExtractor support for annotation.

Request priority support (using `PriorityScheduler`).

Complete some I18n work (comments and documents).

More convenient extractor API:

* Add attribute name select for CSSSelector.
* Group of regex selector can be specified.
* Add OrSelector.
* Add Selectors, import static Selectors.* for fluent API such as:
		
		or(regex("<title>(.*)</title>"), xpath("//title"), $("title")).select(s);
* Add JsonPathSelector for Json parse.
		
*2012-8-9* `version：0.2.0`

此次更新的主题是"方便"(之前的主题是"灵活")。

增加了webmagic-extension模块。

增加了注解方式支持，可以通过POJO+注解的方式编写一个爬虫，更符合Java开发习惯。以下是抓取一个博客的完整代码：

    @TargetUrl("http://my.oschina.net/flashsword/blog/\\d+")
    public class OschinaBlog {

        @ExtractBy("//title")
        private String title;

        @ExtractBy(value = "div.BlogContent",type = ExtractBy.Type.Css)
        private String content;

        @ExtractBy(value = "//div[@class='BlogTags']/a/text()", multi = true)
        private List<String> tags;

        public static void main(String[] args) {
            OOSpider.create(Site.me().addStartUrl("http://my.oschina.net/flashsword/blog"),
            new ConsolePageModelPipeline(), OschinaBlog.class)
            .scheduler(new RedisScheduler("127.0.0.1")).thread(5).run();
        }

    }

增加一个Spider.test(url)方法，用于开发爬虫时进行调试。

增加基于redis的分布式支持。

增加XPath2.0语法支持(webmagic-saxon模块)。

增加基于Selenium的浏览器渲染支持，用于抓取动态加载内容(webmagic-selenium模块)。

修复了不支持https的bug。

补充了文档：[webmagic-0.2.0用户手册](http://code4craft.github.io/webmagic/)。

*2012-7-25* `version：0.1.0`

第一个稳定版本。

修改了若干API，使得可扩展性更强，为每个任务分配一个ID，可以通过ID区分不同任务。

重写了Pipeline接口，将抽取结果集包装到ResultItems对象，而不是通用一个Page对象，便于逻辑分离。

增加下载的重试机制，支持gzip，支持自定义UA/cookie。

增加多线程抓取功能，只需在初始化的时候指定线程数即可。

增加jquery形式的CSS Selector API，可以通过`page.getHtml().$("div.body")`形式抽取元素。

完善了文档，架构说明：[webmagic的设计机制及原理-如何开发一个Java爬虫](http://my.oschina.net/flashsword/blog/145796)，Javadoc：[http://code4craft.github.io/webmagic/docs](http://code4craft.github.io/webmagic/docs)。