function $(str){
    return page.getHtml().$(str).toString();
}
function xpath(str){
    return page.getHtml().xpath(str).toString();
}
function urls(str){
    links = page.getHtml().links().regex(str).all();
    page.addTargetRequests(links);
}
