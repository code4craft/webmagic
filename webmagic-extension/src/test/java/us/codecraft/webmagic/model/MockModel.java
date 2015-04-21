package us.codecraft.webmagic.model;

import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * @author code4crafer@gmail.com
 */
@TargetUrl(value = "http://webmagic.io/post/\\d+",sourceRegion = "//li[@class='post']")
@HelpUrl(value = "http://webmagic.io/list/\\d+",sourceRegion = "//li[@class='list']")
public class MockModel {

}
