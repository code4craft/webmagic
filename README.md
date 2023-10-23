![logo](http://webmagic.io/images/logo.jpeg)

[Readme in Chinese](https://github.com/code4craft/webmagic/tree/master/README-zh.md)


[![Maven Central](https://maven-badges.herokuapp.com/maven-central/us.codecraft/webmagic-parent/badge.svg?subject=Maven%20Central)](https://maven-badges.herokuapp.com/maven-central/us.codecraft/webmagic-parent/)
[![License](https://img.shields.io/badge/License-Apache%20License%202.0-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)
[![Build Status](https://travis-ci.org/code4craft/webmagic.png?branch=master)](https://travis-ci.org/code4craft/webmagic)

>A scalable crawler framework. It covers the whole lifecycle of crawler: downloading, url management, content extraction and persistent. It can simplify the development of a  specific crawler.

## Features:

* Simple core with high flexibility.
* Simple API for html extracting.
* Annotation with POJO to customize a crawler, no configuration.
* Multi-thread and Distribution support.
* Easy to be integrated.

## Install:
  
Add dependencies to your pom.xml:

```xml
<dependency>
    <groupId>us.codecraft</groupId>
    <artifactId>webmagic-core</artifactId>
    <version>${webmagic.version}</version>
</dependency>
<dependency>
    <groupId>us.codecraft</groupId>
    <artifactId>webmagic-extension</artifactId>
    <version>${webmagic.version}</version>
</dependency>
```
        
WebMagic use slf4j with slf4j-log4j12 implementation. If you customized your slf4j implementation, please exclude slf4j-log4j12.

```xml
<exclusions>
    <exclusion>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-log4j12</artifactId>
    </exclusion>
</exclusions>
```


## Get Started:

### First crawler:

Write a class implements PageProcessor. For example, I wrote a crawler of github repository infomation.

```java
public class GithubRepoPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
        page.putField("name", page.getHtml().xpath("//h1[@class='public']/strong/a/text()").toString());
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

* `page.addTargetRequests(links)`
	
	Add urls for crawling.
    
You can also use annotation way:

```java
@TargetUrl("https://github.com/\\w+/\\w+")
@HelpUrl("https://github.com/\\w+")
public class GithubRepo {

    @ExtractBy(value = "//h1[@class='public']/strong/a/text()", notNull = true)
    private String name;

    @ExtractByUrl("https://github\\.com/(\\w+)/.*")
    private String author;

    @ExtractBy("//div[@id='readme']/tidyText()")
    private String readme;

    public static void main(String[] args) {
        OOSpider.create(Site.me().setSleepTime(1000)
                , new ConsolePageModelPipeline(), GithubRepo.class)
                .addUrl("https://github.com/code4craft").thread(5).run();
    }
}
```
		
### Docs and samples:

Documents: [http://webmagic.io/docs/](http://webmagic.io/docs/)

The architecture of webmagic (refered to [Scrapy](http://scrapy.org/))

![image](http://code4craft.github.io/images/posts/webmagic.png)

There are more examples in `webmagic-samples` package.

### License:

Licensed under [Apache 2.0 license](http://opensource.org/licenses/Apache-2.0)

### Thanks:

To write webmagic, I refered to the projects below :

* **Scrapy**

	A crawler framework in Python.
 
	[http://scrapy.org/](http://scrapy.org/)

* **Spiderman**

	Another crawler framework in Java.
	
	[http://git.oschina.net/l-weiwei/spiderman](http://git.oschina.net/l-weiwei/spiderman)

### Mail-list:

[https://groups.google.com/forum/#!forum/webmagic-java](https://groups.google.com/forum/#!forum/webmagic-java)

[http://list.qq.com/cgi-bin/qf_invite?id=023a01f505246785f77c5a5a9aff4e57ab20fcdde871e988](http://list.qq.com/cgi-bin/qf_invite?id=023a01f505246785f77c5a5a9aff4e57ab20fcdde871e988)

QQ Group: 373225642 542327088

### Related Project

* <a href="https://github.com/gsh199449/spider" target="_blank">Gather Platform</a>
	
	A web console based on WebMagic for Spider configuration and management.

