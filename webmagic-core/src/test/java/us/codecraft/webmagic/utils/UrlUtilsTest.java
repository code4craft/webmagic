package us.codecraft.webmagic.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author code4crafter@gmail.com <br>
 * Date: 13-4-21
 * Time: 下午2:22
 */
public class UrlUtilsTest {

    @Test
    public void testFixRelativeUrl() {
        String fixrelativeurl = UrlUtils.canonicalizeUrl("aa", "http://www.dianping.com/sh/ss/com");
        System.out.println("fix: " + fixrelativeurl);
        Assert.assertEquals("http://www.dianping.com/sh/ss/aa", fixrelativeurl);

        fixrelativeurl = UrlUtils.canonicalizeUrl("../aa", "http://www.dianping.com/sh/ss/com");
        Assert.assertEquals("http://www.dianping.com/sh/aa", fixrelativeurl);

        fixrelativeurl = UrlUtils.canonicalizeUrl("..aa", "http://www.dianping.com/sh/ss/com");
        Assert.assertEquals("http://www.dianping.com/sh/ss/..aa", fixrelativeurl);
        fixrelativeurl = UrlUtils.canonicalizeUrl("../../aa", "http://www.dianping.com/sh/ss/com/");
        Assert.assertEquals("http://www.dianping.com/sh/aa", fixrelativeurl);
        fixrelativeurl = UrlUtils.canonicalizeUrl("../../aa", "http://www.dianping.com/sh/ss/com");
        Assert.assertEquals("http://www.dianping.com/aa", fixrelativeurl);
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
