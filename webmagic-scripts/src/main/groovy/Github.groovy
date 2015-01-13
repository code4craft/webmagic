Github {
    Site {
        sleepTime 0
        timeOut 100
        retryTimes 3
        userAgent ['a','b','c'].random
    }
    match "https://github.com/\\w+/\\w+" {
        addUrl(url.regex("https://github.com/\\w+/\\w+"))
        return  {
            name: html.xpath("//h1[@class='entry-title public']/strong/a/text()")
            author: html.xpath "https://github\\.com/(\\w+)/.*"
            readme: html.xpath "//div[@id='readme']/tidyText()"
            star : toInt(html.xpath("//div[@id='readme']/tidyText()"))
        }
    }

}
