WebMagic文档2.0版
========

WebMagic是一个简单灵活、便于二次开发的爬虫框架。除了可以便捷的实现一个爬虫，WebMagic还提供多线程功能，以及基本的分布式功能。

你可以直接使用WebMagic进行爬虫开发，也可以定制WebMagic以适应复杂项目的需要。

## 1. 安装和使用

WebMagic包含两个主要的jar包：`webmagic-core-{version}.jar`和`webmagic-extension-{version}.jar`。在项目中添加这两个包的依赖，即可使用WebMagic。

### 1.1 使用Maven

WebMagic基于Maven进行构建，推荐使用Maven来安装WebMagic。在项目中添加以下坐标即可：

```xml
<dependency>
    <groupId>us.codecraft</groupId>
    <artifactId>webmagic-extension</artifactId>
    <version>0.4.3</version>
</dependency>
```

WebMagic 使用slf4j-log4j12作为slf4j的实现.如果你自己定制了slf4j的实现，请在项目中去掉此依赖。

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

在**lib**目录下，有项目依赖的所有jar包，直接在IDE里import即可。

### 1.3 从源码安装

## 2. 基本的爬虫

### 2.1 抽取内容(xpath, regex, css selector, jsonpath)

### 2.2 发现链接

### 2.3 处理多个页面

## 3. 使用注解

### 3.1 抽取内容(xpath, regex, css selector, jsonpath)

### 3.2 发现链接

### 3.3 处理多个页面

### 3.4 在POJO中实现复杂逻辑

## 4. 配置爬虫

### 4.1 抓取频率

### 4.2 编码

### 4.3 代理

### 4.4 设置cookie/UA等http头信息

### 4.5 重试机制

### 4.6 多线程

## 5. 爬虫的启动和终止

### 5.1 启动爬虫

### 5.2 终止爬虫

### 5.3 设置执行时间

### 5.4 定期抓取

## 6. 管理URL

### 6.1 手动添加URL

### 6.2 在URL中保存信息

### 6.3 几种URL管理方式

### 6.4 自己管理爬虫的URL

## 7. 抽取结果的处理

### 7.1 输出到控制台

### 7.2 保存到文件

### 7.3 JSON格式输出

### 7.4 自定义持久化方式(mysql/mongodb…)

## 8. 实例

### 8.1 基本的列表+详情页的抓取

### 8.2 抓取动态页面

### 8.3 分页抓取

### 8.4 定期抓取