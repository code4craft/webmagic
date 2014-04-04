WebMagic文档2.0版
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

## 3. 基本的爬虫

### 3.1 实现PageProcessor

在WebMagic里，实现一个基本的爬虫只需要编写一个类，实现`PageProcessor`接口。这个类包含了抓取一个网站所需要的所有定制化信息。以之前的`GithubRepoPageProcessor`为例：

```java
public class GithubRepoPageProcessor implements PageProcessor {

    // 抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        // 以下部分定义了如何抽取页面信息，并保存下来
        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
        page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
        if (page.getResultItems().get("name") == null) {
            //skip this page
            page.setSkip(true);
        }
        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));

        // 这一步从页面发现后续的url地址来抓取
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


### 3.2 抽取内容(xpath, regex, css selector, jsonpath)

### 3.3 发现链接

### 3.4 处理多个页面

## 4. 使用注解

### 4.1 抽取内容(xpath, regex, css selector, jsonpath)

### 4.2 发现链接

### 4.3 处理多个页面

### 4.4 在POJO中实现复杂逻辑

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

## 7. 管理URL

### 7.1 手动添加URL

### 7.2 在URL中保存信息

### 7.3 几种URL管理方式

### 7.4 自己管理爬虫的URL

## 8. 抽取结果的处理

### 8.1 输出到控制台

### 8.2 保存到文件

### 8.3 JSON格式输出

### 8.4 自定义持久化方式(mysql/mongodb…)

## 9. 实例

### 9.1 基本的列表+详情页的抓取

### 9.2 抓取动态页面

### 9.3 分页抓取

### 9.4 定期抓取