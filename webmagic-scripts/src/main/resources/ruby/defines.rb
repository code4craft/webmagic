def xpath str
  $page.getHtml().xpath(str).toString()
end
def css str
  $page.getHtml().css(str).toString()
end
def urls str
  links = $page.getHtml().links().regex(str).all();
  $page.addTargetRequests(links);
end

