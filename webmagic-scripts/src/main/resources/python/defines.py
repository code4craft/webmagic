def xpath(str):
   return page.getHtml().xpath(str).toString()

def css(str):
  return page.getHtml().css(str).toString()

def urls(str):
  links=page.getHtml().links().regex(str).all()
  page.addTargetRequests(links);

def tomap(key,value):
  return "hello world"

