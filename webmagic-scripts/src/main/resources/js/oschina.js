var result = {
    title: $("div.BlogTitle h1"),
    content: $("div.BlogContent")
}
var config = {
    ua: '',
    sleepTime : 20
}
urls("http://my\\.oschina\\.net/flashsword/blog/\\d+")