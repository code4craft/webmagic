package us.codecraft.webmagic.utils;

import org.junit.Assert;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author code4crafter@gmail.com <br>
 * Date: 13-4-21
 * Time: 下午2:22
 */
public class UrlUtilsTest {

    @Test
    public void testFixRelativeUrl() {
        String absoluteUrl = UrlUtils.canonicalizeUrl("aa", "http://www.dianping.com/sh/ss/com");
        assertThat(absoluteUrl).isEqualTo("http://www.dianping.com/sh/ss/aa");

        absoluteUrl = UrlUtils.canonicalizeUrl("../aa", "http://www.dianping.com/sh/ss/com");
        assertThat(absoluteUrl).isEqualTo("http://www.dianping.com/sh/aa");

        absoluteUrl = UrlUtils.canonicalizeUrl("../mshz", "http://www.court.gov.cn/zgcpwsw/zgrmfy/");
        assertThat(absoluteUrl).isEqualTo("http://www.court.gov.cn/zgcpwsw/mshz");

        absoluteUrl = UrlUtils.canonicalizeUrl("..aa", "http://www.dianping.com/sh/ss/com");
        assertThat(absoluteUrl).isEqualTo("http://www.dianping.com/sh/ss/..aa");

        absoluteUrl = UrlUtils.canonicalizeUrl("../../aa", "http://www.dianping.com/sh/ss/com/");
        assertThat(absoluteUrl).isEqualTo("http://www.dianping.com/sh/aa");

        absoluteUrl = UrlUtils.canonicalizeUrl("../../aa", "http://www.dianping.com/sh/ss/com");
        assertThat(absoluteUrl).isEqualTo("http://www.dianping.com/aa");
    }

    @Test
    public void testFixAllRelativeHrefs() {
        String originHtml = "<a href=\"/start\">";
        org.jsoup.nodes.Document replacedHtml = UrlUtils.fixAllRelativeHrefs(originHtml, "http://www.dianping.com/");
        assertThat(replacedHtml.outerHtml()).contains("<a href=\"http://www.dianping.com/start\">");

        originHtml = "<a href=\"/start a\">";
        replacedHtml = UrlUtils.fixAllRelativeHrefs(originHtml, "http://www.dianping.com/");
        assertThat(replacedHtml.outerHtml()).contains("<a href=\"http://www.dianping.com/start a\">");

        originHtml = "<a href='/start a'>";
        replacedHtml = UrlUtils.fixAllRelativeHrefs(originHtml, "http://www.dianping.com/");
        assertThat(replacedHtml.outerHtml()).contains("<a href=\"http://www.dianping.com/start a\">");

        originHtml = "<a href=/start tag>";
        replacedHtml = UrlUtils.fixAllRelativeHrefs(originHtml, "http://www.dianping.com/");
        assertThat(replacedHtml.outerHtml()).contains("<a href=\"http://www.dianping.com/start\" tag>");

        originHtml = "<img src=/a.png>";
        replacedHtml = UrlUtils.fixAllRelativeHrefs(originHtml, "http://www.dianping.com/");
        assertThat(replacedHtml.outerHtml()).contains("<img src=\"http://www.dianping.com/a.png\">");

        originHtml = "<a href=/start tag><img src=/a.png>";
        replacedHtml = UrlUtils.fixAllRelativeHrefs(originHtml, "http://www.dianping.com/");
        assertThat(replacedHtml.outerHtml()).contains("<img src=\"http://www.dianping.com/a.png\">");
        assertThat(replacedHtml.outerHtml()).contains("<a href=\"http://www.dianping.com/start\" tag>");

        originHtml = "<a href=/start tag>";
        replacedHtml = UrlUtils.fixAllRelativeHrefs(originHtml, "http://www.dianping.com/");
        assertThat(replacedHtml.select("a").first().attr("href")).isEqualTo("http://www.dianping.com/start");

        originHtml = "<img src=/a.png>";
        replacedHtml = UrlUtils.fixAllRelativeHrefs(originHtml, "http://www.dianping.com/");
        assertThat(replacedHtml.select("img").first().attr("src")).isEqualTo("http://www.dianping.com/a.png");
    }

    @Test
    public void testGetDomain(){
        String url = "http://www.dianping.com/aa/";
        Assert.assertEquals("www.dianping.com",UrlUtils.getDomain(url));
        url = "www.dianping.com/aa/";
        Assert.assertEquals("www.dianping.com",UrlUtils.getDomain(url));
        url = "http://www.dianping.com";
        Assert.assertEquals("www.dianping.com",UrlUtils.getDomain(url));
    }


}
