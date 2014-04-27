WebMagic in Action
========

WebMagic是一个简单灵活、便于二次开发的爬虫框架。除了可以便捷的实现一个爬虫，WebMagic还提供多线程功能，以及基本的分布式功能。

你可以直接使用WebMagic进行爬虫开发，也可以定制WebMagic以适应复杂项目的需要。

## 1. 在项目中使用WebMagic

WebMagic主要包含两个jar包：`webmagic-core-{version}.jar`和`webmagic-extension-{version}.jar`。在项目中添加这两个包的依赖，即可使用WebMagic。

### 1.1 使用Maven

WebMagic基于Maven进行构建，推荐使用Maven来安装WebMagic。在项目中添加以下坐标即可：

```xml
<dependency>
    <groupId>us.codecraft</groupId>
    <artifactId>webmagic-extension</artifactId>
    <version>0.4.3</version>
</dependency>
```

WebMagic使用slf4j-log4j12作为slf4j的实现.如果你自己定制了slf4j的实现，请在项目中去掉此依赖。

```xml
<dependency>
    <groupId>us.codecraft</groupId>
    <artifactId>webmagic-extension</artifactId>
    <version>0.4.3</version>
    <exclusions>
    <exclusion>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-log4j12</artifactId>
    </exclusion>
</exclusions>
</dependency>
```

### 1.2 不使用Maven

不使用maven的用户，可以下载附带二进制jar包的版本(感谢[oschina](http://www.oschina.net/))：

	git clone http://git.oschina.net/flashsword20/webmagic.git

在**lib**目录下，有项目依赖的所有jar包，直接在IDE里，将这些jar添加到Libraries即可。

![import jars](http://static.oschina.net/uploads/space/2014/0403/143318_gBQE_190591.jpeg)

### 1.3 第一个项目

在你的项目中添加了WebMagic的依赖之后，即可开始第一个爬虫的开发了！我们这里拿一个抓取Github信息的例子：

```java
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class GithubRepoPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
        page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
        if (page.getResultItems().get("name")==null){
            //skip this page
            page.setSkip(true);
        }
        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new GithubRepoPageProcessor()).addUrl("https://github.com/code4craft").thread(5).run();
    }
}
```

点击main方法，选择“运行”，你会发现爬虫已经可以正常工作了！

![runlog](http://static.oschina.net/uploads/space/2014/0403/103741_3Gf5_190591.png)

<div style="page-break-after:always"></div>

## 2.下载和编译源码

WebMagic是一个纯Java项目，如果你熟悉Maven，那么下载并编译源码是非常简单的。如果不熟悉Maven也没关系，这部分会介绍如何在Eclipse里导入这个项目。

### 2.1 下载源码

WebMagic目前有两个仓库：

* [https://github.com/code4craft/webmagic](https://github.com/code4craft/webmagic)

github上的仓库保存最新版本，所有issue、pull request都在这里。大家觉得项目不错的话别忘了去给个star哦！

* [http://git.oschina.net/flashsword20/webmagic](http://git.oschina.net/flashsword20/webmagic)

此仓库包含所有编译好的依赖包，只保存项目的稳定版本，最新版本仍在github上更新。oschina在国内比较稳定，主要作为镜像。

无论在哪个仓库，使用

	git clone https://github.com/code4craft/webmagic.git
	
或者

	git clone http://git.oschina.net/flashsword20/webmagic.git
	
即可下载最新代码。

如果你对git本身使用也不熟悉，建议看看@黄勇的 [从 Git@OSC 下载 Smart 源码](http://my.oschina.net/huangyong/blog/200075)

### 2.2 导入项目

Intellij Idea默认自带Maven支持，import项目时选择Maven项目即可。

#### 2.2.1 使用m2e插件

使用Eclipse的用户，推荐安装m2e插件，安装地址：https://www.eclipse.org/m2e/download/[](https://www.eclipse.org/m2e/download/)

安装后，在File->Import中选择Maven->Existing Maven Projects即可导入项目。

![m2e-import](http://static.oschina.net/uploads/space/2014/0403/104427_eNuc_190591.png)

导入后看到项目选择界面，点击finish即可。

![m2e-import2](http://static.oschina.net/uploads/space/2014/0403/104735_6vwG_190591.png)

#### 2.2.2 使用Maven Eclipse插件

如果没有安装m2e插件，只要你安装了Maven，也是比较好办的。在项目根目录下使用命令：

	mvn eclipse:eclipse
	
生成maven项目结构的eclipse配置文件，然后在File->Import中选择General->Existing Projects into Workspace即可导入项目。

![eclipse-import-1](http://static.oschina.net/uploads/space/2014/0403/100025_DAcy_190591.png)

导入后看到项目选择界面，点击finish即可。

![eclipse-import-2](http://static.oschina.net/uploads/space/2014/0403/100227_73DJ_190591.png)

### 2.3 编译和执行源码

导入成功之后，应该就没有编译错误了！此时你可以运行一下webmagic-core项目中自带的exmaple:"us.codecraft.webmagic.processor.example.GithubRepoPageProcessor"。

同样，看到控制台输出如下，则表示源码编译和执行成功了！

![runlog](http://static.oschina.net/uploads/space/2014/0403/103741_3Gf5_190591.png)

<div style="page-break-after:always"></div>

## 3. 基本的爬虫

### 3.1 实现PageProcessor

在WebMagic里，实现一个基本的爬虫只需要编写一个类，实现`PageProcessor`接口即可。这个类基本上包含了抓取一个网站，你需要写的所有代码。

以之前的`GithubRepoPageProcessor`为例，我将PageProcessor的定制分为三个部分，分别是爬虫的配置、页面元素的抽取和链接的发现。

```java
public class GithubRepoPageProcessor implements PageProcessor {

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        // 部分二：定义如何抽取页面信息，并保存下来
        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
        page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
        if (page.getResultItems().get("name") == null) {
            //skip this page
            page.setSkip(true);
        }
        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));

        // 部分三：从页面发现后续的url地址来抓取
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {

        Spider.create(new GithubRepoPageProcessor())
                //从"https://github.com/code4craft"开始抓
                .addUrl("https://github.com/code4craft")
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
    }
}
```

#### 3.1.1 爬虫的配置

第一部分关于爬虫的配置，包括编码、抓取间隔、超时时间、重试次数等，也包括一些模拟的参数，例如User Agent、cookie，以及代理的设置，我们会在第5章-“爬虫的配置”里进行介绍。在这里我们先简单设置一下：重试次数为3次，抓取间隔为一秒。

#### 3.1.2 页面元素的抽取

第二部分是爬虫的核心部分：对于下载到的Html页面，你如何从中抽取到你想要的信息？WebMagic里主要使用了三种抽取技术：XPath、正则表达式和CSS选择器。

1. XPath

	XPath本来是用于XML中获取元素的一种查询语言，但是用于Html也是比较方便的。例如：

	```java
	page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()")
	```
	这段代码使用了XPath，它的意思是“查找所有class属性为'entry-title public'的h1元素，并找到他的strong子节点的a子节点，并提取a节点的文本信息”。
对应的Html是这样子的：

	![xpath-html](http://static.oschina.net/uploads/space/2014/0404/104607_Aqq8_190591.png)

2. CSS选择器

	CSS选择器是与XPath类似的语言。如果大家做过前端开发，肯定知道$('h1.entry-title')这种写法的含义。客观的说，它比XPath写起来要简单一些，但是如果写复杂一点的抽取规则，就相对要麻烦一点。

3. 正则表达式

	正则表达式则是一种通用的文本抽取语言。
	
	```java
	page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
	```

	这段代码就用到了正则表达式，它表示匹配所有"https://github.com/code4craft/webmagic"这样的链接。

XPath、CSS选择器和正则表达式的具体用法会在第4章“抽取工具详解”中讲到。

#### 3.1.3 链接的发现

有了处理页面的逻辑，我们的爬虫就接近完工了！

但是现在还有一个问题：一个站点的页面是很多的，一开始我们不可能全部列举出来，于是如何发现后续的链接，是一个爬虫不可缺少的一部分。

```java
page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
```

这段代码的分为两部分，`page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all()`用于获取所有满足"(https://github\\.com/\\w+/\\w+)"这个正则表达式的链接，`page.addTargetRequests()`则将这些链接加入到待抓取的队列中去。

### 3.2 使用Selectable的链式API

`Selectable`相关的链式API是WebMagic的一个核心功能。使用Selectable接口，你可以直接完成页面元素的链式抽取，也无需去关心抽取的细节。

在刚才的例子中可以看到，page.getHtml()返回的是一个`Html`对象，它实现了`Selectable`接口。这个接口包含一些重要的方法，我将它分为两类：抽取部分和获取结果部分。

#### 3.2.1 抽取部分API：

| 方法 | 说明 | 示例 |
| ------------ | ------------- | ------------ |
| xpath(String xpath) | 使用XPath选择  | html.xpath("//div[@class='title']") |
| \$(String selector) | 使用Css选择器选择  | html.\$("div.title") |
| \$(String selector,String attr) | 使用Css选择器选择  | html.\$("div.title","text") |
| css(String selector) | 功能同$()，使用Css选择器选择  | html.css("div.title") |
| links() | 选择所有链接  | html.links() |
| regex(String regex) | 使用正则表达式抽取  | html.regex("\<div\>(.\*?)\</div>") |
| regex(String regex,int group) | 使用正则表达式抽取，并指定捕获组  | html.regex("\<div\>(.\*?)\</div>",1) |
| replace(String regex, String replacement) | 替换内容| html.replace("\<script>.\*\</script>","")|

这部分抽取API返回的都是一个`Selectable`接口，意思是说，抽取是支持链式调用的。下面我用一个实例来讲解链式API的使用。

例如，我现在要抓取github上所有的Java项目，这些项目可以在[https://github.com/search?l=Java&p=1&q=stars%3A%3E1&s=stars&type=Repositories](https://github.com/search?l=Java&p=1&q=stars%3A%3E1&s=stars&type=Repositories)搜索结果中看到。

为了避免抓取范围太宽，我指定只从分页部分抓取链接。这个抓取规则是比较复杂的，我会要怎么写呢？

![selectable-chain-ui](http://static.oschina.net/uploads/space/2014/0404/151454_2T01_190591.png)

首先看到页面的html结构是这个样子的：

![selectable-chain](http://static.oschina.net/uploads/space/2014/0404/151632_88Oq_190591.png)

那么我可以先用CSS选择器提取出这个div，然后在取到所有的链接。为了保险起见，我再使用正则表达式限定一下提取出的URL的格式，那么最终的写法是这样子的：

```java
List<String> urls = page.getHtml().css("div.pagination").links().regex(".*/search/\?l=java.*").all();
```

然后，我们可以把这些URL加到抓取列表中去：

```java
List<String> urls = page.getHtml().css("div.pagination").links().regex(".*/search/\?l=java.*").all();
page.addTargetRequests(urls);
```

是不是比较简单？除了发现链接，Selectable的链式抽取还可以完成很多工作。我们会在第9章示例中再讲到。

#### 3.2.2 获取结果的API：

当链式调用结束时，我们一般都想要拿到一个字符串类型的结果。这时候就需要用到获取结果的API了。我们知道，一条抽取规则，无论是XPath、CSS选择器或者正则表达式，总有可能抽取到多条元素。WebMagic对这些进行了统一，你可以通过不同的API获取到一个或者多个元素。

| 方法 | 说明 | 示例 |
| ------------ | ------------- | ------------ |
| get() | 返回一条String类型的结果 | String link= html.links().get()|
| toString() | 功能同get()，返回一条String类型的结果 | String link= html.links().toString()|
| all() | 返回所有抽取结果 | List<String> links= html.links().all()|
| match() | 是否有匹配结果 | if (html.links().match()){ xxx; }|

例如，我们知道页面只会有一条结果，那么可以使用selectable.get()或者selectable.toString()拿到这条结果。

这里selectable.toString()采用了toString()这个接口，是为了在输出以及和一些框架结合的时候，更加方便。因为一般情况下，我们都只需要选择一个元素！

selectable.all()则会获取到所有元素。

好了，到现在为止，在回过头看看3.1中的GithubRepoPageProcessor，可能就觉得更加清晰了吧？指定main方法，已经可以看到抓取结果在控制台输出了。

### 3.3 保存结果

好了，爬虫编写完成，现在我们可能还有一个问题：我如果想把抓取的结果保存下来，要怎么做呢？WebMagic用于保存结果的组件叫做`Pipeline`。例如我们通过“控制台输出结果”这件事也是通过一个内置的Pipeline完成的，它叫做`ConsolePipeline`。那么，我现在想要把结果用Json的格式保存下来，怎么做呢？我只需要将Pipeline的实现换成"JsonFilePipeline"就可以了。

```java
    public static void main(String[] args) {

        Spider.create(new GithubRepoPageProcessor())
                //从"https://github.com/code4craft"开始抓
                .addUrl("https://github.com/code4craft")
                .addPipeline(new JsonFilePipeline("D:\webmagic\"))
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
    }
```

这样子下载下来的文件就会保存在D盘的webmagic目录中了。

通过定制Pipeline，我们还可以实现保存结果到文件、数据库等一系列功能。这个会在第7章“抽取结果的处理”中介绍。

至此为止，我们已经完成了一个基本爬虫的编写，也具有了一些定制功能。

<div style="page-break-after:always"></div>

## 4. 抽取工具详解

### 4.1 XPath

### 4.2 CSS选择器

### 4.3 正则表达式

### 4.4 JsonPath

## 5. 配置爬虫

### 5.1 抓取频率

### 5.2 编码

### 5.3 代理

### 5.4 设置cookie/UA等http头信息

### 5.5 重试机制

### 5.6 多线程

## 6. 爬虫的启动和终止

### 6.1 启动爬虫

### 6.2 终止爬虫

### 6.3 设置执行时间

### 6.4 定期抓取

## 7. 抽取结果的处理

### 7.1 输出到控制台

### 7.2 保存到文件

### 7.3 JSON格式输出

### 7.4 自定义持久化方式(mysql/mongodb…)

## 8. 管理URL

### 8.1 手动添加URL

### 8.2 在URL中保存信息

### 8.3 几种URL管理方式

### 8.4 自己管理爬虫的URL

## 9. 实例

### 9.1 基本的列表+详情页的抓取

### 9.2 抓取动态页面

### 9.3 分页抓取

### 9.4 定期抓取