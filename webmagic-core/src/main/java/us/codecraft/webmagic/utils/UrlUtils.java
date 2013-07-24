package us.codecraft.webmagic.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author code4crafter@gmail.com <br>
 * Date: 13-4-21
 * Time: 下午1:52
 */
public class UrlUtils {

    private static Pattern relativePathPattern = Pattern.compile("^([\\.]+)/");

    /**
     * 将url想对地址转化为绝对地址
     * @param url url地址
     * @param refer url地址来自哪个页面
     * @return
     */
    public static String canonicalizeUrl(String url, String refer) {
        if (StringUtils.isBlank(url) || StringUtils.isBlank(refer)) {
            return url;
        }
        if (url.startsWith("http") || url.startsWith("ftp") || url.startsWith("mailto") || url.startsWith("javascript:")) {
            return url;
        }
        if (StringUtils.startsWith(url, "/")) {
            String host = getHost(refer);
            return host + url;
        } else if (!StringUtils.startsWith(url, ".")) {
            refer = reversePath(refer, 1);
            return refer + "/" + url;
        } else {
            Matcher matcher = relativePathPattern.matcher(url);
            if (matcher.find()) {
                int reverseDepth = matcher.group(1).length();
                refer = reversePath(refer, reverseDepth);
                String substring = StringUtils.substring(url, matcher.end());
                return refer + "/" + substring;
            } else {
                refer = reversePath(refer, 1);
                return refer + "/" + url;
            }
        }
    }

    public static String reversePath(String url, int depth) {
        int i = StringUtils.lastOrdinalIndexOf(url, "/", depth);
        if (i < 10) {
            url = getHost(url);
        } else {
            url = StringUtils.substring(url, 0, i);
        }
        return url;
    }

    public static String getHost(String url) {
        String host = url;
        int i = StringUtils.ordinalIndexOf(url, "/", 3);
        if (i > 0) {
            host = StringUtils.substring(url, 0, i);
        }
        return host;
    }

    private static Pattern patternForProtocal = Pattern.compile("[\\w]+://");

    public static String removeProtocol(String url) {
        return patternForProtocal.matcher(url).replaceAll("");
    }

    public static String getDomain(String url) {
        String domain = removeProtocol(url);
        int i = StringUtils.indexOf(domain, "/", 1);
        if (i > 0) {
            domain = StringUtils.substring(domain, 0, i);
        }
        return domain;
    }

    private static Pattern patternForHref = Pattern.compile("(<a[^<>]*href=)[\"']{0,1}([^\"'<>\\s]*)[\"']{0,1}", Pattern.CASE_INSENSITIVE);

    public static String fixAllRelativeHrefs(String html, String url) {
        StringBuilder stringBuilder = new StringBuilder();
        Matcher matcher = patternForHref.matcher(html);
        int lastEnd = 0;
        while (matcher.find()) {
            stringBuilder.append(StringUtils.substring(html, lastEnd, matcher.start()));
            stringBuilder.append(matcher.group(1));
            stringBuilder.append("\"").append(canonicalizeUrl(matcher.group(2), url)).append("\"");
            lastEnd = matcher.end();
        }
        stringBuilder.append(StringUtils.substring(html, lastEnd));
        return stringBuilder.toString();
    }

    private static final Pattern patternForCharset = Pattern.compile("charset=([^\\s;]*)");

    public static String getCharset(String contentType) {
        Matcher matcher = patternForCharset.matcher(contentType);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return null;
        }
    }

}
