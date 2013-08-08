webmagic使用手册
------
>webmagic是一个开源的Java垂直爬虫框架，目标是简化爬虫的开发流程，让开发者专注于逻辑功能的开发。webmagic的核心非常简单，但是覆盖爬虫的整个流程，也是很好的学习爬虫开发的材料。

>web爬虫是一种技术，webmagic致力于将这种技术的实现成本降低，但是出于对资源提供者的尊重，webmagic不会做反封锁的事情，包括：验证码破解、代理切换、自动登录、抓取静态资源等。

>作者黄亿华([code4crafter@gmail.com](code4crafter@gmail.com))目前就职于大众点评，曾经在前公司进行过一年的垂直爬虫的开发。webmagic就是为了解决爬虫开发的一些重复劳动而产生的框架。有使用不便或者问题，欢迎在github[提交issue](https://github.com/code4craft/webmagic/issues)，或者在[oschina讨论模块](http://www.oschina.net/question)提问。

>webmagic的架构和设计参考了以下两个项目，感谢以下两个项目的作者：

>python爬虫 **scrapy** [https://github.com/scrapy/scrapy](https://github.com/scrapy/scrapy)

>Java爬虫 **Spiderman** [https://gitcafe.com/laiweiwei/Spiderman](https://gitcafe.com/laiweiwei/Spiderman)

---------

## 快速开始

### 使用maven

webmagic使用maven管理依赖，你可以直接下载webmagic源码进行编译：

	git clone https://github.com/code4craft/webmagic.git
	mvn clean install

安装后，在项目中添加对应的依赖即可使用webmagic：

		<dependency>
            <groupId>us.codecraft</groupId>
            <artifactId>webmagic-core</artifactId>
            <version>0.2.0</version>
        </dependency>
		<dependency>
            <groupId>us.codecraft</groupId>
            <artifactId>webmagic-extension</artifactId>
            <version>0.2.0</version>
        </dependency>

#### 项目结构
	
webmagic主要包括两个包：

* **webmagic-core**
	
	webmagic核心部分，只包含爬虫基本模块和基本抽取器。webmagic-core的目标是成为网页爬虫的一个教科书般的实现。
	
* **webmagic-extension**
	
	webmagic的扩展模块，提供一些更方便的编写爬虫的工具。包括注解格式定义爬虫、JSON、分布式等支持。
	
webmagic还包含两个可用的扩展包，因为这两个包都依赖了比较重量级的工具，所以从主要包中抽离出来：

* **webmagic-saxon**

	webmagic与Saxon结合的模块。Saxon是一个XPath、XSLT的解析工具，webmagic依赖Saxon来进行XPath2.0语法解析支持。

* **webmagic-selenium**

	webmagic与Selenium结合的模块。Selenium是一个模拟浏览器进行页面渲染的工具，webmagic依赖Selenium进行动态页面的抓取。
	
在项目中，你可以根据需要依赖不同的包。

### 不使用maven

不使用maven的用户，可以下载这个二进制打包版本(感谢[oschina](http://www.oschina.net/))：

	git clone http://git.oschina.net/flashsword20/webmagic-bin.git

在`bin/lib`目录下，有项目依赖的所有jar包，直接在IDE里import即可。

### 第一个爬虫

#### 定制PageProcessor

PageProcessor是webmagic-core的一部分，定制一个PageProcessor即可实现自己的爬虫逻辑。以下是抓取osc博客的一段代码：

    public class OschinaBlogPageProcesser implements PageProcessor {

        private Site site = Site.me().setDomain("my.oschina.net")
           .addStartUrl("http://my.oschina.net/flashsword/blog");

        @Override
        public void process(Page page) {
            List<String> links = page.getHtml().links().regex("http://my\\.oschina\\.net/flashsword/blog/\\d+").all();
            page.addTargetRequests(links);
            page.putField("title", page.getHtml().xpath("//div[@class='BlogEntity']/div[@class='BlogTitle']/h1").toString());
            page.putField("content", page.getHtml().$("div.content").toString());
            page.putField("tags",page.getHtml().xpath("//div[@class='BlogTags']/a/text()").all());
        }

        @Override
        public Site getSite() {
            return site;

        }

        public static void main(String[] args) {
            Spider.create(new OschinaBlogPageProcesser())
                 .pipeline(new ConsolePipeline()).run();
        }
    }

这里通过page.addTargetRequests()方法来增加要抓取的URL，并通过page.putField()来保存抽取结果。page.getHtml().xpath()则是按照某个规则对结果进行抽取，这里抽取支持链式调用。调用结束后，toString()表示转化为单个String，all()则转化为一个String列表。

Spider是爬虫的入口类。Pipeline是结果输出和持久化的接口，这里ConsolePipeline表示结果输出到控制台。

执行这个main方法，即可在控制台看到抓取结果。webmagic默认有3秒抓取间隔，请耐心等待。

#### 使用注解

webmagic-extension包括了注解方式编写爬虫的方法，只需基于一个POJO增加注解即可完成一个爬虫。以下仍然是抓取oschina博客的一段代码，功能与OschinaBlogPageProcesser完全相同：

	@TargetUrl("http://my.oschina.net/flashsword/blog/\\d+")
	public class OschinaBlog {

	    @ExtractBy("//title")
	    private String title;

	    @ExtractBy(value = "div.BlogContent",type = ExtractBy.Type.Css)
	    private String content;

	    @ExtractBy(value = "//div[@class='BlogTags']/a/text()", multi = true)
	    private List<String> tags;

	    public static void main(String[] args) {
	        OOSpider.create(
	        	Site.me().addStartUrl("http://my.oschina.net/flashsword/blog"),
				new ConsolePageModelPipeline(), OschinaBlog.class).run();
	    }
	}

这个例子定义了一个Model类，Model类的字段'title'、'content'、'tags'均为要抽取的属性。这个类在Pipeline里是可以复用的。

关于注解的使用方式，在后面会专门讲到。

------

## 核心架构解析

webmagic-core是爬虫的核心框架，只包括一个爬虫各功能模块的核心功能。webmagic-core的目标是成为网页爬虫的一个教科书般的实现。

此节部分内容摘自作者的博文
[webmagic的设计机制及原理-如何开发一个Java爬虫](http://my.oschina.net/flashsword/blog/145796)。

### webmagic-core的模块划分

webmagic-core参考了scrapy的模块划分，分为Spider(整个爬虫的调度框架)、Downloader(页面下载)、PageProcessor(链接提取和页面分析)、Scheduler(URL管理)、Pipeline(离线分析和持久化)几部分。只不过scrapy通过middleware实现扩展，而webmagic则通过定义这几个接口，并将其不同的实现注入主框架类Spider来实现扩展。

![image](http://code4craft.github.io/images/posts/webmagic.png)

#### Spider类(核心调度)

Spider是爬虫的入口类，Spider的接口调用采用了链式的API设计，其他功能全部通过接口注入Spider实现，下面是启动一个比较复杂的Spider的例子。

    <!-- lang: java -->
    Spider.create(sinaBlogProcessor)
	.scheduler(new FileCacheQueueScheduler("/data/temp/webmagic/cache/"))
	.pipeline(new FilePipeline())
	.thread(10).run();	


Spider的核心处理流程非常简单，代码如下：

    <!-- lang: java -->
    private void processRequest(Request request) {
        Page page = downloader.download(request, this);
        if (page == null) {
            sleep(site.getSleepTime());
            return;
        }
        pageProcessor.process(page);
        addRequest(page);
        for (Pipeline pipeline : pipelines) {
            pipeline.process(page, this);
        }
        sleep(site.getSleepTime());
    }

#### Downloader(页面下载)

大部分爬虫都是通过模拟http请求，接收并分析响应来完成。这方面，JDK自带的**HttpURLConnection**可以满足最简单的需要，而**Apache HttpClient**(4.0后整合到HttpCompenent项目中)则是开发复杂爬虫的不二之选。它支持自定义HTTP头(对于爬虫比较有用的就是User-agent、cookie等)、自动redirect、连接复用、cookie保留、设置代理等诸多强大的功能。

webmagic使用了HttpClient 4.2，并封装到了**HttpClientDownloader**。学习HttpClient的使用对于构建高性能爬虫是非常有帮助的，官方的[Tutorial](http://hc.apache.org/httpcomponents-client-ga/tutorial/html/)就是很好的学习资料。目前webmagic对HttpClient的使用仍在初步阶段，不过对于一般抓取任务，已经够用了。

对于一些Javascript动态加载的网页，仅仅使用http模拟下载工具，并不能取到页面的内容。这方面的思路有两种：一种是抽丝剥茧，分析js的逻辑，再用爬虫去重现它(比如在网页中提取关键数据，再用这些数据去构造Ajax请求，最后直接从响应体获取想要的数据)；
另一种就是：内置一个浏览器，直接获取最后加载完的页面。这方面，js可以使用**PhantomJS**，它内部集成了webkit。而Java可以使用**Selenium**，这是一个非常强大的浏览器模拟工具。webmagic-selenium包中整合了Selenium到`SeleniumDownloader`，可以直接进行动态加载页面的抓取。

#### PageProcessor(页面分析及链接抽取)

页面分析可以说是垂直爬虫最复杂的一部分，在webmagic里，PageProcessor是定制爬虫的核心。通过编写一个实现PageProcessor接口的类，就可以定制一个自己的爬虫。

**Selector**是webmagic为了简化页面抽取开发的独立模块，是webmagic的主要着力点。这里整合了CSS Selector、XPath和正则表达式，并可以进行链式的抽取，很容易就实现强大的功能。即使你使用自己开发的爬虫工具，webmagic的Selector仍然值得一试。

例如，我已经下载了一个页面，现在要抽取某个区域的所有包含"blog"的链接，我可以这样写：
		
    <!-- lang: java -->
    //content是用别的爬虫工具抽取到的正文
    String content = "blabla";
    List<String> links = Html.create(content)
    .$("div.title")  //css 选择，Java里虽然很少有$符号出现，不过貌似$作为方法名是合法的
    .xpath("//@href")  //提取链接
    .regex(".*blog.*") //正则匹配过滤
    .all(); //转换为string列表


另外，webmagic的抓取链接需要显示的调用`Page.addTargetRequests()`去添加，这也是为了灵活性考虑的(很多时候，下一步的URL不是单纯的页面href链接，可能会根据页面模块进行抽取，甚至可能是自己拼凑出来的)。

webmagic包括一个对于页面正文的自动抽取的功能**SmartContentSelector**。相信用过Evernote Clearly都会对其自动抽取正文的技术印象深刻。这个技术又叫**Readability**。当然webmagic对Readability的实现还比较粗略，但是仍有一些学习价值。

基于Saxon，webmagic提供了XPath2.0语法的支持。XPath2.0语法支持内部函数、逻辑控制等，是一门完整的语言，如果你熟悉语法，倒是不妨一试(需要引入webmagic-saxon包)。

#### Scheduler(URL管理)


URL管理的问题可大可小。对于小规模的抓取，URL管理是很简单的。我们只需要将待抓取URL和未抓取URL分开保存，并进行去重即可。使用JDK内置的集合类型Set、List或者Queue都可以满足需要。如果我们要进行多线程抓取，则可以选择线程安全的容器，例如LinkedBlockingQueue以及ConcurrentHashMap。

因为小规模的URL管理非常简单，很多框架都并不将其抽象为一个模块，而是直接融入到代码中。但是实际上，抽象出Scheduler模块，会使得框架的解耦程度上升一个档次，并非常容易进行横向扩展，这也是我从scrapy中学到的。

在webmagic的设计中，除了Scheduler模块，其他的处理-从下载、解析到持久化，每个任务都是互相独立的，因此可以通过多个Spider共用一个Scheduler来进行扩展。排除去重的因素，URL管理天生就是一个队列，我们可以很方便的用分布式的队列工具去扩展它，也可以基于mysql、redis或者mongodb这样的存储工具来构造一个队列，这样构建一个多线程乃至分布式的爬虫就轻而易举了。

URL去重也是一个比较复杂的问题。如果数据量较少，则使用hash的方式就能很好解决。数据量较大的情况下，可以使用Bloom Filter或者更复杂的方式。

webmagic目前有两个Scheduler的实现，**QueueScheduler**是一个简单的内存队列，速度较快，并且是线程安全的，**FileCacheQueueScheduler**则是一个文件队列，它可以用于耗时较长的下载任务，在任务中途停止后，下次执行仍然从中止的URL开始继续爬取。

webmagic有一个基于redis的Scheduler实现**RedisScheduler**。通过使用同一台redis服务器存储URL，webmagic可以很容易的在多机部署，从而达到分布式爬虫的效果。


#### Pipeline-离线处理和持久化


Pipeline其实也是容易被忽略的一部分。大家都知道持久化的重要性，但是很多框架都选择直接在页面抽取的时候将持久化一起完成，例如crawer4j。但是Pipeline真正的好处是，将页面的在线分析和离线处理拆分开来，可以在一些线程里进行下载，另一些线程里进行处理和持久化。

你可以扩展Pipeline来实现抽取结果的持久化，将其保存到你想要保存的地方-本地文件、数据库、mongodb等等。Pipeline的处理目前还是在线的，但是修改为离线的也并不困难。

webmagic目前只支持控制台输出和文件持久化，但是持久化到数据库也是很容易的。这里不妨看一下[webmagic结合JFinal持久化到数据库的一段代码](http://www.oschina.net/code/snippet_190591_23456)。因为JFinal目前还不支持maven，所以并没有放到webmagic-samples里来。

------

## 注解模块

webmagic-extension包括注解模块。为什么会有注解方式？

因为PageProcessor的方式灵活、强大，但是没有解决两个问题：

* 对于一个站点，如果想抓取多种格式的URL，那么必须在PageProcesser中写判断逻辑，代码难以管理。
* 抓取结果没有对应Model，并不符合Java程序开发习惯，与一些框架也无法很好整合。

注解的核心是Model类，本身是一个POJO，这个Model类用于传递、保存页面最终抓取结果数据。注解方式直接将抽取与数据绑定，以便于编写和维护。

注解方式其实也是通过一个PageProcessor的实现--ModelPageProcessor完成，因此对webmagic-core代码没有任何影响。

注解部分包括以下内容：

* ### TargetUrl

	"TargetUrl"表示这个Model对应要抓取的URL，它包含两层意思：符合这个条件的URL会被加入抓取队列；符合这个条件的URL会被这个Model抓取。TargetUrl可以`sourceRegion`指定提取URL的区域(仅支持XPath)。
	
	TargetUrl使用了正则表达式，匹配 "http://my.oschina.net/flashsword/blog/150039" 格式的URL。webmagic对正则表达式进行了修改，"."仅表示字符"."而不代表任意字符，而"\*"则代表了".\*"，例如"http://\*.oschina.net/\*"代表了oschina所有的二级域名下的URL。
	
	与TargetUrl相似的还有`HelpUrl`，HelpUrl表示：仅仅抓取该URL用作链接提取，并不对它进行内容抽取。例如博客正文页对应TargetUrl，而列表页则对应HelpUrl。

* ### ExtractBy	

	* #### 用于字段

		"ExtractBy"可用于类以及字段。用于字段时，定义了字段抽取的规则。抽取的规则默认使用[**XPath**](http://www.w3school.com.cn/xpath/)，也可以选择使用CSS Selector、正则表达式(通过设置type)。
	
		ExtractBy还有几个扩展属性。`multi`表示是否抽取列表，当然，设置为multi时，你需要一个List字段去容纳它。`notnull`则表示，此字段不允许为null，若为null则放弃整个对象。

	* #### 用于类	
		"ExtractBy"用于类时，则限定了字段抽取的区域。用于类时仍支持multi，multi则表示一个页面可以抽取到多个对象。

	* #### ExtractByRaw & ExtractByUrl
	
		在类使用"ExtractBy"修饰后，字段的"ExtractBy"使用的是其抽取的结果，如果仍然想要抽取原HTML，可以使用"ExtractByRaw"。与此类似的还有"ExtractByUrl"，表示从URL重抽取信息。ExtractByUrl只支持正则表达式。

	* #### ExtractBy2 ExtractBy3	
		
		"ExtractBy"、"ExtractByRaw"支持链式抽取，通过增加注解"ExtractBy2"、"ExtractBy3"实现。
		
* ### AfterExtractor

	AfterExtractor接口是对注解方式抽取能力不足的补充。实现AfterExtractor接口后，会在**使用注解方式填充完字段后**调用`afterProcess()`方法，在这个方法中可以直接访问已抽取的字段、补充需要抽取的字段，甚至做一些简单的输出和持久化操作(并不是很建议这么做)。这部分可以参考[webmagic结合JFinal持久化到数据库的一段代码](http://www.oschina.net/code/snippet_190591_23456)。

* ### OOSpider
	OOSpider是注解式爬虫的入口，这里调用`create()`方法将OschinaBlog这个类加入到爬虫的抽取中，这里是可以传入多个类的，OOSpider会根据TargetUrl调用不同的Model进行解析。

* ### PageModelPipeline
	可以通过定义PageModelPipeline来选择结果输出方式。这里new ConsolePageModelPipeline()是PageModelPipeline的一个实现，会将结果输出到控制台。
	
* ### 分页
	处理单项数据分页(例如单条新闻多个页面)是爬虫一个比较头疼的问题。webmagic有一个对于分页的实现，通过实现`PagedModel`接口即可。webmagic-samples里有一个抓取网易新闻的类：`us.codecraft.webmagic.model.samples.News163`。关于分页，这里有一篇对于webmagic分页实现的详细说明的文章[关于爬虫实现分页的一些思考](http://my.oschina.net/flashsword/blog/150039)。
	目前分页功能还没有分布式实现。

--------
	
## 分布式
	webmagic-extension中，通过redis来管理URL，达到分布式的效果。具体实现方式只有一个类：`us.codecraft.webmagic.scheduler.RedisScheduler`。
	

	

