Release Notes
----
*2012-7-25* `version：0.1.0`

第一个稳定版本。

修改了若干API，使得可扩展性更强，为每个任务分配一个ID，可以通过ID区分不同任务。

重写了Pipeline接口，将抽取结果集包装到ResultItems对象，而不是通用一个Page对象，便于逻辑分离。

增加下载的重试机制，支持gzip，支持自定义UA/cookie。

增加多线程抓取功能，只需在初始化的时候指定线程数即可。

增加jquery形式的CSS Selector API，可以通过`page.getHtml().$("div.body")`形式抽取元素。

完善了文档，架构说明：[webmagic的设计机制及原理-如何开发一个Java爬虫](http://my.oschina.net/flashsword/blog/145796)，Javadoc：[http://code4craft.github.io/webmagic/docs](http://code4craft.github.io/webmagic/docs)。