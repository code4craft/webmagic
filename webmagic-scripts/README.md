webmagic-scripts
======
## 目标：
使得可以用简单脚本的方式编写爬虫，从而为一些常用场景提供可流通的脚本。如果已经有人写好了脚本，那么你直接使用就可以了！

## 实例:
例如：我需要抓github的仓库数据，可以这样写一个脚本(javascript)：

```javascript
var name=xpath("//h1[@class='entry-title public']/strong/a/text()")
var readme=xpath("//div[@id='readme']/tidyText()")
var star=xpath("//ul[@class='pagehead-actions']/li[1]//a[@class='social-count js-social-count']/text()")
var fork=xpath("//ul[@class='pagehead-actions']/li[2]//a[@class='social-count']/text()")
var url=page.getUrl().toString()
if (name!=null){
    println(name)
    println(readme)
    println(star)
    println(url)
}

urls("(https://github\\.com/\\w+/\\w+)")
urls("(https://github\\.com/\\w+)")
```

然后使用webmagic加载并启动它，无需下载依赖、编写代码、执行的过程。目前已经有控制台版本，请下载[http://code4craft.qiniudn.com/webmagic-console.tar.gz](http://code4craft.qiniudn.com/webmagic-console.tar.gz)。

解压后，使用以下命令执行：
	
	java -jar -Dfile.encoding='utf-8' webmagic-console.jar -f 脚本文件名 [-l 语言，默认是javascript] [-t 线程数] [-s 抓取间隔，毫秒] url1 url2 …

例如，对于github这个脚本，我可以这样执行：

	java -jar -Dfile.encoding='utf-8' webmagic-console.jar -f github.js -t 2 -s 0 https://github.com/code4craft

目前这部分使用Java的ScriptEngine机制完成。

## 语言:

选用javascript是因为用户面比较广。目前还支持ruby语言，选用ruby是因为ruby的语法编写DSL更简洁：

```ruby
name= xpath "//h1[@class='entry-title public']/strong/a/text()"
readme = xpath "//div[@id='readme']/tidyText()"
star = xpath "//ul[@class='pagehead-actions']/li[1]//a[@class='social-count js-social-count']/text()"
fork = xpath "//ul[@class='pagehead-actions']/li[2]//a[@class='social-count']/text()"
url=$page.getUrl().toString()

puts name,readme,star,fork,url unless name==nil

urls "(https://github\\.com/\\w+/\\w+)"
urls "(https://github\\.com/\\w+)"
```

多语言通过参数-l区分，例如执行这个ruby脚本需要：

	java -jar -Dfile.encoding='utf-8' webmagic-console.jar -f github.rb -t2 -s0 -l ruby https://github.com/code4craft

这个功能目前仍在实验阶段。欢迎大家积极参与并提出意见。