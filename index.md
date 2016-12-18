WebMagic
=====================

>简单灵活的爬虫框架。

WebMagic是一个简单灵活的爬虫框架。基于WebMagic，你可以快速开发出一个高效、易维护的爬虫。

## 特性：

* 简单的API，可快速上手
* 模块化的结构，可轻松扩展
* 提供多线程和分布式支持

一个示例：

```java
public class GithubRepoPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/[\\w\\-]+/[\\w\\-]+)").all());
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/[\\w\\-])").all());
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

## 下载：

添加Maven依赖：

```xml
<dependency>
    <groupId>us.codecraft</groupId>
    <artifactId>webmagic-core</artifactId>
    <version>0.6.0</version>
</dependency>
<dependency>
    <groupId>us.codecraft</groupId>
    <artifactId>webmagic-extension</artifactId>
    <version>0.6.0</version>
</dependency>
```

## 文档：

* 中文: [http://webmagic.io/docs/zh/](http://webmagic.io/docs/zh/)
* English: [http://webmagic.io/docs/en](http://webmagic.io/docs/en)

## 源码：

* [https://git.oschina.net/flashsword20/webmagic](https://git.oschina.net/flashsword20/webmagic)
* [https://github.com/code4craft/webmagic](https://github.com/code4craft/webmagic)

## 讨论：

* bug反馈及建议：[https://github.com/code4craft/webmagic/issues](https://github.com/code4craft/webmagic/issues)
* qq群：373225642

## 支持我们

* 如果你已经使用过WebMagic，欢迎参加[用户调查](https://jinshuju.net/f/d5CNTi)。