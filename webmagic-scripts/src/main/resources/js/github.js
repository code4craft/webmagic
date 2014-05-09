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