name= xpath "//h1[@class='entry-title public']/strong/a/text()"
readme = xpath "//div[@id='readme']/tidyText()"
star = xpath "//ul[@class='pagehead-actions']/li[1]//a[@class='social-count js-social-count']/text()"
fork = xpath "//ul[@class='pagehead-actions']/li[2]//a[@class='social-count']/text()"
url=$page.getUrl().toString()

puts name,readme,star,fork,url unless name==nil

urls "(https://github\\.com/\\w+/\\w+)"
urls "(https://github\\.com/\\w+)"