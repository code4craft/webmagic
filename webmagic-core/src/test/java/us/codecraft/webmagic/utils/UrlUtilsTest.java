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
        String fixrelativeurl = UrlUtils.fixRelativeUrl("aa", "http://www.dianping.com/sh/ss/com");
        System.out.println("fix: " + fixrelativeurl);
        Assert.assertEquals("http://www.dianping.com/sh/ss/aa", fixrelativeurl);

        fixrelativeurl = UrlUtils.fixRelativeUrl("../aa", "http://www.dianping.com/sh/ss/com");
        Assert.assertEquals("http://www.dianping.com/sh/aa", fixrelativeurl);

        fixrelativeurl = UrlUtils.fixRelativeUrl("..../aa", "http://www.dianping.com/sh/ss/com");
        Assert.assertEquals("http://www.dianping.com/aa", fixrelativeurl);
        fixrelativeurl = UrlUtils.fixRelativeUrl(".../aa", "http://www.dianping.com/sh/ss/com");
        Assert.assertEquals("http://www.dianping.com/aa", fixrelativeurl);
        fixrelativeurl = UrlUtils.fixRelativeUrl("..aa", "http://www.dianping.com/sh/ss/com");
        Assert.assertEquals("http://www.dianping.com/sh/ss/..aa", fixrelativeurl);
//        fixrelativeurl = fixrelativeurl("/aa", "http://www.dianping.com");
//        System.out.println("fix: " + fixrelativeurl);
//        fixrelativeurl = fixrelativeurl("/aa", "http://www.dianping.com/");
//        System.out.println("fix: " + fixrelativeurl);
    }

    @Test
    public void testFixRelativeHtml(){
        String html = "\n" +
                "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "<head>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
                "<title>虎嗅网</title>\n" +
                "\n" +
                "<meta name=\"keywords\" content=\"科技资讯 商业评论 明星公司动态 宏观趋势 精选 有料 干货 有用 细节 内幕\" />\n" +
                "<meta name=\"description\" id=\"description\" content=\"虎嗅网是一个有视角的、个性化商业资讯与交流平台，核心关注对象是包括公众公司与创业型企业在内的一系列明星公司。 \" />\n" +
                "<meta name=\"MSSmartTagsPreventParsing\" content=\"True\" />\n" +
                "<meta http-equiv=\"MSThemeCompatible\" content=\"Yes\" />\n" +
                "<base href=\"http://www.huxiu.com/\" /><link rel=\"stylesheet\" type=\"text/css\" href=\"data/cache/style_3_common.css?GDG\" /><script type=\"text/javascript\">var count_article_id='', STYLEID = '3', STATICURL = 'static/', IMGDIR = 'static/image/common', VERHASH = 'GDG', charset = 'utf-8', discuz_uid = '0', cookiepre = '9YoY_2132_', cookiedomain = '', cookiepath = '/', showusercard = '1', attackevasive = '0', disallowfloat = '', creditnotice = '', defaultstyle = '', REPORTURL = 'aHR0cDovL3d3dy5odXhpdS5jb20v', SITEURL = 'http://www.huxiu.com/', JSPATH = 'static/js/';</script>\n" +
                "<script src=\"static/js/common.js?GDG\" type=\"text/javascript\"></script>\n" +
                "<script src=\"static/js/jquery-1.7.2.min.js\" type=\"text/javascript\"></script>\n" +
                "<meta name=\"application-name\" content=\"虎嗅网\" />\n" +
                "<meta name=\"msapplication-tooltip\" content=\"虎嗅网\" />\n" +
                "<meta name=\"msapplication-task\" content=\"name=首页;action-uri=http://www.huxiu.com/portal.php;icon-uri=http://www.huxiu.com/static/image/common/portal.ico\" /><meta name=\"msapplication-task\" content=\"name=论坛;action-uri=http://www.huxiu.com/forum.php;icon-uri=http://www.huxiu.com/static/image/common/bbs.ico\" />\n" +
                "<meta name=\"msapplication-task\" content=\"name=;action-uri=http://www.huxiu.com/home.php;icon-uri=http://www.huxiu.com/static/image/common/home.ico\" /><script src=\"static/js/portal.js?GDG\" type=\"text/javascript\"></script>\n" +
                "<!--[if lt IE 7]>\n" +
                "<script src=\"/template/youliao/image/png_min.js\" type=\"text/javascript\"></script>\n" +
                "<script type=\"text/javascript\">\n" +
                "DD_belatedPNG.fix('#hd, img');\n" +
                "</script>\n" +
                "<![endif]-->\n" +
                "<script src=\"http://cbjs.baidu.com/js/m.js\" type=\"text/javascript\"></script>\n" +
                "</head>\n" +
                "\n" +
                "<body id=\"nv_portal\" class=\"pg_index \" onkeydown=\"if(event.keyCode==27) return false;\">\n" +
                "\n" +
                "<div id=\"append_parent\"></div><div id=\"ajaxwaitid\"></div>\n" +
                "\t\t\t\n" +
                "\t\n" +
                "<div id=\"hd\">\n" +
                "<div class=\"w-900 hdc cl\"><h2><a href=\"./\" title=\"虎嗅网\"><img src=\"template/youliao/image/logo.png\" alt=\"虎嗅网\" border=\"0\" /></a></h2>\n" +
                "<!--广告位-->\n" +
                "<div class=\"top-ad z\" style=\"padding-left: 20px;width: 480px;padding-top: 4px;\"><div><script type=\"text/javascript\">BAIDU_CLB_fillSlot(\"652074\");</script></div></div>\n" +
                "<div class=\"youce y\">\n" +
                "<div class=\"y\">\n" +
                "<a class=\"xu_subscribe\" href=\"home.php?mod=spacecp&amp;ac=profile&amp;op=info\" >订阅<span >虎嗅</span></a>\n" +
                "<a class=\"xu_rss\" href=\"/rss/\">RSS</a>\n" +
                "</div>\n" +
                "<script src=\"static/js/logging.js?GDG\" type=\"text/javascript\"></script>\n" +
                "<div class=\"no-login\">\n" +
                "<span class=\"no-login-newHtml\"><a href=\"member.php?mod=logging&amp;action=login\" onclick=\"showWindow('login', this.href)\">登录</a><a href=\"member.php?mod=register\" class=\"xi2 xw1\">注册</a></span>\n" +
                "</div>\n" +
                "\n" +
                "</div>\n" +
                "</div>\n" +
                "\n" +
                "<div class=\"nv-wrap\">\n" +
                "<div id=\"nv\" class=\"w-900\">\n" +
                "<div id=\"scbar\" class=\"cl\">\n" +
                "<form id=\"scbar_form\" method=\"post\" autocomplete=\"off\" onsubmit=\"searchFocus($('scbar_txt'))\" action=\"search.php?searchsubmit=yes\" target=\"_blank\">\n" +
                "<input type=\"hidden\" name=\"mod\" id=\"scbar_mod\" value=\"search\" />\n" +
                "<input type=\"hidden\" name=\"formhash\" value=\"e0edf9ab\" />\n" +
                "<input type=\"hidden\" name=\"srchtype\" value=\"title\" />\n" +
                "<input type=\"hidden\" name=\"srhfid\" value=\"0\" />\n" +
                "<input type=\"hidden\" name=\"srhlocality\" value=\"portal::index\" />\n" +
                "<table cellspacing=\"0\" cellpadding=\"0\">\n" +
                "<tr>\n" +
                "<td class=\"scbar_txt_td\"><input type=\"text\" name=\"srchtxt\" id=\"scbar_txt\" value=\"\" autocomplete=\"off\" /></td>\n" +
                "<td class=\"scbar_btn_td\"><input type=\"image\" src=\"template/youliao/image/search_icon.png\" /></td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "</form>\n" +
                "</div>\n" +
                "<ul id=\"scbar_type_menu\" class=\"p_pop\" style=\"display: none;\"><li><a href=\"javascript:;\" rel=\"article\" class=\"curtype\">文章</a></li><li><a href=\"javascript:;\" rel=\"forum\" >帖子</a></li><li><a href=\"javascript:;\" rel=\"user\">用户</a></li></ul>\n" +
                "<script type=\"text/javascript\">\n" +
                "//initSearchmenu('scbar', '');\n" +
                "</script>\n" +
                "<ul><li id=\"mn_N6666\" ><a href=\"/\" hidefocus=\"true\"  >首页</a></li><li id=\"mn_P1\" ><a href=\"http://www.huxiu.com/focus/\" hidefocus=\"true\"  >看点</a></li><li id=\"mn_P6\" ><a href=\"http://www.huxiu.com/books/\" hidefocus=\"true\"  >读点</a></li><li id=\"mn_P4\" ><a href=\"http://www.huxiu.com/opinions/\" hidefocus=\"true\"  >观点</a></li><li id=\"mn_Ncd44\" ><a href=\"/tags\" hidefocus=\"true\"  >标签</a></li><li id=\"mn_N40f8\" ><a href=\"/contribute\" hidefocus=\"true\"  >投稿</a></li><li ><a hidefocus=\"true\" href=\"/space/uid/0.html\">我的虎嗅</a></li>\n" +
                "</ul>\n" +
                "</div>\n" +
                "<div id=\"mu\" class=\"cl\">\n" +
                "</div></div>\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "<div id=\"wp\" class=\"wp\">\n" +
                "<div class=\"v2cl\"></div>\n" +
                "<div class=\"topad\"><div><script type=\"text/javascript\">BAIDU_CLB_fillSlot(\"468015\");</script></div></div>\n" +
                "<div class=\"y v2right\">\n" +
                "    <div>\n" +
                "                <div class=\"index-datu\">\n" +
                "    <div>\n" +
                "        <h1><a href=\"article/13358/1.html\" target=\"_blank\">震后48小时，互联网公司行动启示录</a></h1>\n" +
                "        <h2>在公益产品开发上，互联网合作开放共享一面应得体现，商业竞争一面则应被冲淡</h2>\n" +
                "        <a href=\"article/13358/1.html\" target=\"_blank\"><img src=\"http://u.img.huxiu.com/block/82/824bfa087ea84225ffe344accd915af2.jpg\" height=\"360px\" width=\"640px\" alt=\"震后48小时，互联网公司行动启示录\" title=\"震后48小时，互联网公司行动启示录\"/></a>\n" +
                "    </div>\n" +
                "    <div class=\"v2cl\"></div>\n" +
                "    <div class=\"other\"><a href=\"article/13358/1.html#comment\" target=\"_blank\"><span class=\"pinglun\">评论(32)</span></a><span class=\"new-tag\"> <a href=/tags/2263 target=\"_blank\" title=\"头条\" >头条</a> <a href=/tags/3314 target=\"_blank\" title=\"公益\" >公益</a></span></div>\n" +
                "</div>            </div>\n" +
                "    <div class=\"article-list\">\n" +
                "                <div class=\"article-box\">\n" +
                "    <div class=\"z article-box-img\"><a href=\"article/13396/1.html\" target=\"_blank\"><img class=\"a-img\" src=\"http://u.img.huxiu.com/portal/201304/22/184247zw90in17xxldwll3.jpg.thumb.jpg\" width=\"220\" height=\"146\" title=\"李经纬逝世，围绕他展开的一个时代和三个男人\" alt=\"李经纬逝世，围绕他展开的一个时代和三个男人\"/></a></div>\n" +
                "    <div class=\"y t-sub2\">\n" +
                "        <ul class=\"summary-ul\">\n" +
                "            <li><h2 class=\"t-h\"><a href=\"article/13396/1.html\" target=\"_blank\">李经纬逝世，围绕他展开的一个时代和三个男人</a></h2></li>\n" +
                "            <li><a href=\"huxiu\" target=\"_blank\">虎嗅</a>&nbsp;发表于 2013-04-22 18:42</li>\n" +
                "            <li class=\"summary\">时代的问题时代自会判定，但难心想象，李经纬的晚年会多么地心灰意冷，抑郁难拔</li>\n" +
                "        </ul>\n" +
                "        <ul class=\"comment\">\n" +
                "            <li><span class=\"pinglun\"><a href=\"article/13396/1.html#comment\" target=\"_blank\">评论(35)</a></span><span class=\"new-tag\"> <a href=/tags/3073 target=\"_blank\" title=\"零售\" >零售</a> <a href=/tags/3337 target=\"_blank\" title=\"突发\" >突发</a></span></li>\n" +
                "        </ul>\n" +
                "    </div>\n" +
                "    <div class=\"clear\"></div>\n" +
                "</div>                <div class=\"article-box\">\n" +
                "    <div class=\"z article-box-img\"><a href=\"article/13395/1.html\" target=\"_blank\"><img class=\"a-img\" src=\"http://u.img.huxiu.com/portal/201304/22/181141yyqz5c5zps5y4cy7.jpg.thumb.jpg\" width=\"220\" height=\"146\" title=\"今日嗅评：一切都应在灾难面前握手言和\" alt=\"今日嗅评：一切都应在灾难面前握手言和\"/></a></div>\n" +
                "    <div class=\"y t-sub2\">\n" +
                "        <ul class=\"summary-ul\">\n" +
                "            <li><h2 class=\"t-h\"><a href=\"article/13395/1.html\" target=\"_blank\">今日嗅评：一切都应在灾难面前握手言和</a></h2></li>\n" +
                "            <li><a href=\"huxiu\" target=\"_blank\">虎嗅</a>&nbsp;发表于 2013-04-22 18:09</li>\n" +
                "            <li class=\"summary\">互联网公司在灾难面前可以放下竞争和利益，协作应对灾难，是大家都愿意看到的事情，也很感激响应虎嗅的每一家公司。</li>\n" +
                "        </ul>\n" +
                "        <ul class=\"comment\">\n" +
                "            <li><span class=\"pinglun\"><a href=\"article/13395/1.html#comment\" target=\"_blank\">评论(3)</a></span><span class=\"new-tag\"> <a href=/tags/384 target=\"_blank\" title=\"虎动\" >虎动</a> <a href=/tags/3017 target=\"_blank\" title=\"评论\" >评论</a></span></li>\n" +
                "        </ul>\n" +
                "    </div>\n" +
                "    <div class=\"clear\"></div>\n" +
                "</div>                <div class=\"article-box\">\n" +
                "    <div class=\"z article-box-img\"><a href=\"article/13384/1.html\" target=\"_blank\"><img class=\"a-img\" src=\"http://u.img.huxiu.com/portal/201304/22/144550f885jwr6foji5a58.jpg.thumb.jpg\" width=\"220\" height=\"146\" title=\"娜拉出走以后怎么办？读《从理想主义到经验主义》，向自由致敬\" alt=\"娜拉出走以后怎么办？读《从理想主义到经验主义》，向自由致敬\"/></a></div>\n" +
                "    <div class=\"y t-sub2\">\n" +
                "        <ul class=\"summary-ul\">\n" +
                "            <li><h2 class=\"t-h\"><a href=\"article/13384/1.html\" target=\"_blank\">娜拉出走以后怎么办？读《从理想主义到经验主义》，向自由致敬</a></h2></li>\n" +
                "            <li><a href=\"huxiu\" target=\"_blank\">虎嗅</a>&nbsp;发表于 2013-04-22 18:00</li>\n" +
                "            <li class=\"summary\">这不是一本为发表所写的著作，而是作者应他兄弟的要求断断继继写下来的笔记。时间是从1973年到1974年作者逝世前为止。</li>\n" +
                "        </ul>\n" +
                "        <ul class=\"comment\">\n" +
                "            <li><span class=\"pinglun\"><a href=\"article/13384/1.html#comment\" target=\"_blank\">评论(2)</a></span><span class=\"new-tag\"> <a href=/tags/3051 target=\"_blank\" title=\"人文社科\" >人文社科</a></span></li>\n" +
                "        </ul>\n" +
                "    </div>\n" +
                "    <div class=\"clear\"></div>\n" +
                "</div>                <div class=\"article-box\">\n" +
                "    <div class=\"z article-box-img\"><a href=\"article/13392/1.html\" target=\"_blank\"><img class=\"a-img\" src=\"http://u.img.huxiu.com/portal/201304/22/171425fkfzh5gi6biofbbv.jpg.thumb.jpg\" width=\"220\" height=\"146\" title=\"【每日移动观察】Kindle手机可期？\" alt=\"【每日移动观察】Kindle手机可期？\"/></a></div>\n" +
                "    <div class=\"y t-sub2\">\n" +
                "        <ul class=\"summary-ul\">\n" +
                "            <li><h2 class=\"t-h\"><a href=\"article/13392/1.html\" target=\"_blank\">【每日移动观察】Kindle手机可期？</a></h2></li>\n" +
                "            <li><a href=\"huxiu\" target=\"_blank\">虎嗅</a>&nbsp;发表于 2013-04-22 17:18</li>\n" +
                "            <li class=\"summary\">这部传说中的Kindle手机，媒体与分析师们都为它设定好了特征：1、低价。2、良好的内容体验。</li>\n" +
                "        </ul>\n" +
                "        <ul class=\"comment\">\n" +
                "            <li><span class=\"pinglun\"><a href=\"article/13392/1.html#comment\" target=\"_blank\">评论(0)</a></span><span class=\"new-tag\"> <a href=/tags/66 target=\"_blank\" title=\"亚马逊\" >亚马逊</a> <a href=/tags/2938 target=\"_blank\" title=\"每日移动观察\" >每日移动观察</a></span></li>\n" +
                "        </ul>\n" +
                "    </div>\n" +
                "    <div class=\"clear\"></div>\n" +
                "</div>                <div class=\"article-box\">\n" +
                "    <div class=\"z article-box-img\"><a href=\"article/13356/1.html\" target=\"_blank\"><img class=\"a-img\" src=\"http://u.img.huxiu.com/portal/201304/22/072314k33a6sr6e39bsae6.jpg.thumb.jpg\" width=\"220\" height=\"146\" title=\"地震后，为什么手机不通微信通？\" alt=\"地震后，为什么手机不通微信通？\"/></a></div>\n" +
                "    <div class=\"y t-sub2\">\n" +
                "        <ul class=\"summary-ul\">\n" +
                "            <li><h2 class=\"t-h\"><a href=\"article/13356/1.html\" target=\"_blank\">地震后，为什么手机不通微信通？</a></h2></li>\n" +
                "            <li><a href=\"http://tech.sina.com.cn/i/2013-04-22/06018264122.shtml\" target=\"_blank\">北京晨报</a>&nbsp;发表于 2013-04-22 07:23</li>\n" +
                "            <li class=\"summary\">微信的工作原理是分组交换的业务模式。它经过压缩处理，占用的通道可宽可窄，信息可以一站站推送，有传输空间时再送出。在同等网络条件下，微信占用的网络资源要小得多</li>\n" +
                "        </ul>\n" +
                "        <ul class=\"comment\">\n" +
                "            <li><span class=\"pinglun\"><a href=\"article/13356/1.html#comment\" target=\"_blank\">评论(40)</a></span><span class=\"new-tag\"> <a href=/tags/68 target=\"_blank\" title=\"微信\" >微信</a> <a href=/tags/2995 target=\"_blank\" title=\"运营商\" >运营商</a> <a href=/tags/3337 target=\"_blank\" title=\"突发\" >突发</a></span></li>\n" +
                "        </ul>\n" +
                "    </div>\n" +
                "    <div class=\"clear\"></div>\n" +
                "</div>                <div class=\"article-box\">\n" +
                "    <div class=\"z article-box-img\"><a href=\"article/13387/1.html\" target=\"_blank\"><img class=\"a-img\" src=\"http://u.img.huxiu.com/block/46/46ac4f12ca3a95e40a0c41685eda90f9.jpg\" width=\"220\" height=\"146\" title=\"施密特：国家是具有垄断地位的服务提供商\" alt=\"施密特：国家是具有垄断地位的服务提供商\"/></a></div>\n" +
                "    <div class=\"y t-sub2\">\n" +
                "        <ul class=\"summary-ul\">\n" +
                "            <li><h2 class=\"t-h\"><a href=\"article/13387/1.html\" target=\"_blank\">施密特：国家是具有垄断地位的服务提供商</a></h2></li>\n" +
                "            <li><a href=\"http://www.guardian.co.uk/technology/2013/apr/20/eric-schmidt-google?CMP=twt_fd\" target=\"_blank\">Guardian</a>&nbsp;发表于 2013-04-22 15:51</li>\n" +
                "            <li class=\"summary\">国家提供了统一的规则。国家会制定实体政策和虚拟政策，这种二元性——网络空间实施一种战略，实体空间又部署另外一种战略——是可能的。</li>\n" +
                "        </ul>\n" +
                "        <ul class=\"comment\">\n" +
                "            <li><span class=\"pinglun\"><a href=\"article/13387/1.html#comment\" target=\"_blank\">评论(4)</a></span><span class=\"new-tag\"> <a href=/tags/83 target=\"_blank\" title=\"谷歌\" >谷歌</a></span></li>\n" +
                "        </ul>\n" +
                "    </div>\n" +
                "    <div class=\"clear\"></div>\n" +
                "</div>                <div class=\"article-box\">\n" +
                "    <div class=\"z article-box-img\"><a href=\"article/13374/1.html\" target=\"_blank\"><img class=\"a-img\" src=\"http://u.img.huxiu.com/block/fe/fe15130f2b49e47e57430f5b41f95b61.jpg\" width=\"220\" height=\"146\" title=\"库克位子成疑。这就是华尔街\" alt=\"库克位子成疑。这就是华尔街\"/></a></div>\n" +
                "    <div class=\"y t-sub2\">\n" +
                "        <ul class=\"summary-ul\">\n" +
                "            <li><h2 class=\"t-h\"><a href=\"article/13374/1.html\" target=\"_blank\">库克位子成疑。这就是华尔街</a></h2></li>\n" +
                "            <li><a href=\"space/uid/20189/1.html\" target=\"_blank\">Hotashang</a>&nbsp;发表于 2013-04-22 14:23</li>\n" +
                "            <li class=\"summary\">华尔街就是这样的一个地方，在长期投资预期与短视利润之间纠结。而作为公司的CEO，也会经常在这种长期投资预期与短视利润之间摇摆</li>\n" +
                "        </ul>\n" +
                "        <ul class=\"comment\">\n" +
                "            <li><span class=\"pinglun\"><a href=\"article/13374/1.html#comment\" target=\"_blank\">评论(13)</a></span><span class=\"new-tag\"> <a href=/tags/54 target=\"_blank\" title=\"苹果\" >苹果</a> <a href=/tags/357 target=\"_blank\" title=\"投稿\" >投稿</a> <a href=/tags/706 target=\"_blank\" title=\"资本市场\" >资本市场</a> <a href=/tags/1927 target=\"_blank\" title=\"戴尔\" >戴尔</a></span></li>\n" +
                "        </ul>\n" +
                "    </div>\n" +
                "    <div class=\"clear\"></div>\n" +
                "</div>                <div class=\"article-box\">\n" +
                "    <div class=\"z article-box-img\"><a href=\"article/13373/1.html\" target=\"_blank\"><img class=\"a-img\" src=\"http://u.img.huxiu.com/block/06/06f13611eaef06b194f25d35ec2c3abe.jpg\" width=\"220\" height=\"146\" title=\"跨国公司全球声誉排名：苹果跌破前十，宝马高居榜首\" alt=\"跨国公司全球声誉排名：苹果跌破前十，宝马高居榜首\"/></a></div>\n" +
                "    <div class=\"y t-sub2\">\n" +
                "        <ul class=\"summary-ul\">\n" +
                "            <li><h2 class=\"t-h\"><a href=\"article/13373/1.html\" target=\"_blank\">跨国公司全球声誉排名：苹果跌破前十，宝马高居榜首</a></h2></li>\n" +
                "            <li><a href=\"http://www.reputationinstitute.com/thought-leadership/global-reptrak-100\" target=\"_blank\">Reputation Institute</a>&nbsp;发表于 2013-04-22 12:33</li>\n" +
                "            <li class=\"summary\">宝马是唯一在所有七项评比标准中都进入前五名的公司。53%的首席高管认为公司声誉会提高销售和营收，63%的首席高管预期声誉管理在未来两到三年会成为公司的优先工作内容。</li>\n" +
                "        </ul>\n" +
                "        <ul class=\"comment\">\n" +
                "            <li><span class=\"pinglun\"><a href=\"article/13373/1.html#comment\" target=\"_blank\">评论(11)</a></span><span class=\"new-tag\"> <a href=/tags/54 target=\"_blank\" title=\"苹果\" >苹果</a> <a href=/tags/83 target=\"_blank\" title=\"谷歌\" >谷歌</a></span></li>\n" +
                "        </ul>\n" +
                "    </div>\n" +
                "    <div class=\"clear\"></div>\n" +
                "</div>                <div class=\"article-box\">\n" +
                "    <div class=\"z article-box-img\"><a href=\"article/13324/1.html\" target=\"_blank\"><img class=\"a-img\" src=\"http://u.img.huxiu.com/portal/201304/22/115622c98dppnb4tz8h9ow.jpg.thumb.jpg\" width=\"220\" height=\"146\" title=\"我为什么说生鲜电商是个伪命题？\" alt=\"我为什么说生鲜电商是个伪命题？\"/></a></div>\n" +
                "    <div class=\"y t-sub2\">\n" +
                "        <ul class=\"summary-ul\">\n" +
                "            <li><h2 class=\"t-h\"><a href=\"article/13324/1.html\" target=\"_blank\">我为什么说生鲜电商是个伪命题？</a></h2></li>\n" +
                "            <li><a href=\"space/uid/46140/1.html\" target=\"_blank\">独自等待</a>&nbsp;发表于 2013-04-22 11:58</li>\n" +
                "            <li class=\"summary\">我也一直很期待这个领域能有一个大师兄，既牵着马，又挑着担，还能吓退妖怪，完成阿什顿伊顿般十项全能然后华丽转身，但，仅仅也就是期待罢了</li>\n" +
                "        </ul>\n" +
                "        <ul class=\"comment\">\n" +
                "            <li><span class=\"pinglun\"><a href=\"article/13324/1.html#comment\" target=\"_blank\">评论(41)</a></span><span class=\"new-tag\"> <a href=/tags/73 target=\"_blank\" title=\"电子商务\" >电子商务</a> <a href=/tags/357 target=\"_blank\" title=\"投稿\" >投稿</a></span></li>\n" +
                "        </ul>\n" +
                "    </div>\n" +
                "    <div class=\"clear\"></div>\n" +
                "</div>                <div class=\"article-box\">\n" +
                "    <div class=\"z article-box-img\"><a href=\"article/13371/1.html\" target=\"_blank\"><img class=\"a-img\" src=\"http://u.img.huxiu.com/portal/201304/22/1152590z1x25xrz5bne2x1.jpg.thumb.jpg\" width=\"220\" height=\"146\" title=\"怎么估算雅安芦山县地震的经济影响？422.6亿元\" alt=\"怎么估算雅安芦山县地震的经济影响？422.6亿元\"/></a></div>\n" +
                "    <div class=\"y t-sub2\">\n" +
                "        <ul class=\"summary-ul\">\n" +
                "            <li><h2 class=\"t-h\"><a href=\"article/13371/1.html\" target=\"_blank\">怎么估算雅安芦山县地震的经济影响？422.6亿元</a></h2></li>\n" +
                "            <li><a href=\"http://www.ftchinese.com/story/001050056?page=1\" target=\"_blank\">FT中文网</a>&nbsp;发表于 2013-04-22 11:58</li>\n" +
                "            <li class=\"summary\">参照汶川地震损失的5%计算，按照比较法的原则估计，雅安地震造成的直接经济损失大致为422.6亿，这也是国内近年来事件损失最大的一次自然灾害</li>\n" +
                "        </ul>\n" +
                "        <ul class=\"comment\">\n" +
                "            <li><span class=\"pinglun\"><a href=\"article/13371/1.html#comment\" target=\"_blank\">评论(5)</a></span><span class=\"new-tag\"> <a href=/tags/370 target=\"_blank\" title=\"宏观\" >宏观</a> <a href=/tags/3337 target=\"_blank\" title=\"突发\" >突发</a></span></li>\n" +
                "        </ul>\n" +
                "    </div>\n" +
                "    <div class=\"clear\"></div>\n" +
                "</div>                <div class=\"article-box\">\n" +
                "    <div class=\"z article-box-img\"><a href=\"article/13369/1.html\" target=\"_blank\"><img class=\"a-img\" src=\"http://u.img.huxiu.com/portal/201304/22/11252934ishlz6z93dvslt.jpg.thumb.jpg\" width=\"220\" height=\"146\" title=\"8个月，2500亿美元，蒂姆・库克一露面股票就跌\" alt=\"8个月，2500亿美元，蒂姆・库克一露面股票就跌\"/></a></div>\n" +
                "    <div class=\"y t-sub2\">\n" +
                "        <ul class=\"summary-ul\">\n" +
                "            <li><h2 class=\"t-h\"><a href=\"article/13369/1.html\" target=\"_blank\">8个月，2500亿美元，蒂姆・库克一露面股票就跌</a></h2></li>\n" +
                "            <li><a href=\"space/uid/19/1.html\" target=\"_blank\">虎嗅</a>&nbsp;发表于 2013-04-22 11:29</li>\n" +
                "            <li class=\"summary\">苹果是散户投资者最喜欢的个股，分析师指出苹果的增长陷入停顿才是大问题。市场在用自己的方式和蒂姆・库克进行沟通，每次他公开发表观点，股价就下跌，无一幸免。</li>\n" +
                "        </ul>\n" +
                "        <ul class=\"comment\">\n" +
                "            <li><span class=\"pinglun\"><a href=\"article/13369/1.html#comment\" target=\"_blank\">评论(17)</a></span><span class=\"new-tag\"> <a href=/tags/54 target=\"_blank\" title=\"苹果\" >苹果</a></span></li>\n" +
                "        </ul>\n" +
                "    </div>\n" +
                "    <div class=\"clear\"></div>\n" +
                "</div>                <div class=\"article-box\">\n" +
                "    <div class=\"z article-box-img\"><a href=\"article/13364/1.html\" target=\"_blank\"><img class=\"a-img\" src=\"http://u.img.huxiu.com/portal/201304/22/100033z5t5yy3bdxl5zzbc.jpg.thumb.jpg\" width=\"220\" height=\"146\" title=\"为什么19楼你学不会？\" alt=\"为什么19楼你学不会？\"/></a></div>\n" +
                "    <div class=\"y t-sub2\">\n" +
                "        <ul class=\"summary-ul\">\n" +
                "            <li><h2 class=\"t-h\"><a href=\"article/13364/1.html\" target=\"_blank\">为什么19楼你学不会？</a></h2></li>\n" +
                "            <li><a href=\"space/uid/23595/1.html\" target=\"_blank\">周宁</a>&nbsp;发表于 2013-04-22 10:06</li>\n" +
                "            <li class=\"summary\">①一句报网互动不能解决问题；②产品必须要有自身特色；③不要随意去学别人的定位；④19楼的盈利模式不好学；⑤19楼的团队你学不会！</li>\n" +
                "        </ul>\n" +
                "        <ul class=\"comment\">\n" +
                "            <li><span class=\"pinglun\"><a href=\"article/13364/1.html#comment\" target=\"_blank\">评论(40)</a></span><span class=\"new-tag\"> <a href=/tags/357 target=\"_blank\" title=\"投稿\" >投稿</a> <a href=/tags/3003 target=\"_blank\" title=\"地方网站\" >地方网站</a> <a href=/tags/3339 target=\"_blank\" title=\"19楼\" >19楼</a></span></li>\n" +
                "        </ul>\n" +
                "    </div>\n" +
                "    <div class=\"clear\"></div>\n" +
                "</div>                <div class=\"article-box\">\n" +
                "    <div class=\"z article-box-img\"><a href=\"article/13362/1.html\" target=\"_blank\"><img class=\"a-img\" src=\"http://u.img.huxiu.com/portal/201304/22/093630nne20a4plyrdnlye.jpg.thumb.jpg\" width=\"220\" height=\"146\" title=\"iCar？很难指望了！可是苹果对汽车仍有野望\" alt=\"iCar？很难指望了！可是苹果对汽车仍有野望\"/></a></div>\n" +
                "    <div class=\"y t-sub2\">\n" +
                "        <ul class=\"summary-ul\">\n" +
                "            <li><h2 class=\"t-h\"><a href=\"article/13362/1.html\" target=\"_blank\">iCar？很难指望了！可是苹果对汽车仍有野望</a></h2></li>\n" +
                "            <li><a href=\"http://blog.laptopmag.com/apple-ios-car-technology\" target=\"_blank\">laptopmag.com</a>&nbsp;发表于 2013-04-22 09:43</li>\n" +
                "            <li class=\"summary\">iCar成为现实的希望越来越渺茫，后乔布斯时代的苹果公司正努力将Siri等技术融入现有的汽车产品中去</li>\n" +
                "        </ul>\n" +
                "        <ul class=\"comment\">\n" +
                "            <li><span class=\"pinglun\"><a href=\"article/13362/1.html#comment\" target=\"_blank\">评论(4)</a></span><span class=\"new-tag\"> <a href=/tags/54 target=\"_blank\" title=\"苹果\" >苹果</a> <a href=/tags/3338 target=\"_blank\" title=\"智能汽车\" >智能汽车</a></span></li>\n" +
                "        </ul>\n" +
                "    </div>\n" +
                "    <div class=\"clear\"></div>\n" +
                "</div>                <div class=\"article-box\">\n" +
                "    <div class=\"z article-box-img\"><a href=\"article/13361/1.html\" target=\"_blank\"><img class=\"a-img\" src=\"http://u.img.huxiu.com/portal/201304/22/093439nvnux4p35k4ao0ll.jpg.thumb.jpg\" width=\"220\" height=\"146\" title=\"网络时代，重大突发新闻报道为何一错再错？\" alt=\"网络时代，重大突发新闻报道为何一错再错？\"/></a></div>\n" +
                "    <div class=\"y t-sub2\">\n" +
                "        <ul class=\"summary-ul\">\n" +
                "            <li><h2 class=\"t-h\"><a href=\"article/13361/1.html\" target=\"_blank\">网络时代，重大突发新闻报道为何一错再错？</a></h2></li>\n" +
                "            <li><a href=\"http://qz.com/76668/boston-marathon-and-the-media/\" target=\"_blank\">QUARTZ</a>&nbsp;发表于 2013-04-22 09:39</li>\n" +
                "            <li class=\"summary\">抢先发布，而不关心是否属实，媒体啊、媒体！在一场公众眼球的“盛宴”中，对那些真正重要的问题选择集体漠视。争夺注意力有那么重要吗？有吗？</li>\n" +
                "        </ul>\n" +
                "        <ul class=\"comment\">\n" +
                "            <li><span class=\"pinglun\"><a href=\"article/13361/1.html#comment\" target=\"_blank\">评论(8)</a></span><span class=\"new-tag\"> <a href=/tags/376 target=\"_blank\" title=\"新媒体\" >新媒体</a></span></li>\n" +
                "        </ul>\n" +
                "    </div>\n" +
                "    <div class=\"clear\"></div>\n" +
                "</div>                <div class=\"article-box\">\n" +
                "    <div class=\"z article-box-img\"><a href=\"article/13357/1.html\" target=\"_blank\"><img class=\"a-img\" src=\"http://u.img.huxiu.com/portal/201304/22/080407uwo6z1zslo16mouu.jpg.thumb.jpg\" width=\"220\" height=\"146\" title=\"亚马逊首位数据挖掘负责人往事：开发出亚马逊最赚钱项目\" alt=\"亚马逊首位数据挖掘负责人往事：开发出亚马逊最赚钱项目\"/></a></div>\n" +
                "    <div class=\"y t-sub2\">\n" +
                "        <ul class=\"summary-ul\">\n" +
                "            <li><h2 class=\"t-h\"><a href=\"article/13357/1.html\" target=\"_blank\">亚马逊首位数据挖掘负责人往事：开发出亚马逊最赚钱项目</a></h2></li>\n" +
                "            <li><a href=\"http://www.businessinsider.com/amazon-ceo-changed-a-mans-life-forever-2013-4\" target=\"_blank\">Business Insider</a>&nbsp;发表于 2013-04-22 07:46</li>\n" +
                "            <li class=\"summary\">杰夫・贝索斯多次向这位软件工程师伸出橄榄枝，终于将其招至帐下，也成功研发出亚马逊有史以来最赚钱的项目</li>\n" +
                "        </ul>\n" +
                "        <ul class=\"comment\">\n" +
                "            <li><span class=\"pinglun\"><a href=\"article/13357/1.html#comment\" target=\"_blank\">评论(31)</a></span><span class=\"new-tag\"> <a href=/tags/66 target=\"_blank\" title=\"亚马逊\" >亚马逊</a></span></li>\n" +
                "        </ul>\n" +
                "    </div>\n" +
                "    <div class=\"clear\"></div>\n" +
                "</div>                <div class=\"article-box\">\n" +
                "    <div class=\"z article-box-img\"><a href=\"article/13319/1.html\" target=\"_blank\"><img class=\"a-img\" src=\"http://u.img.huxiu.com/portal/201304/20/161529cps9necojcyaoooo.jpg.thumb.jpg\" width=\"220\" height=\"146\" title=\"马化腾与马云为何将数百万捐款，都放入壹基金参与雅安救助？\" alt=\"马化腾与马云为何将数百万捐款，都放入壹基金参与雅安救助？\"/></a></div>\n" +
                "    <div class=\"y t-sub2\">\n" +
                "        <ul class=\"summary-ul\">\n" +
                "            <li><h2 class=\"t-h\"><a href=\"article/13319/1.html\" target=\"_blank\">马化腾与马云为何将数百万捐款，都放入壹基金参与雅安救助？</a></h2></li>\n" +
                "            <li><a href=\"huxiu\" target=\"_blank\">虎嗅</a>&nbsp;发表于 2013-04-20 16:18</li>\n" +
                "            <li class=\"summary\">在@中国红十字会总会的倡议微博下，却是数万声的“滚”</li>\n" +
                "        </ul>\n" +
                "        <ul class=\"comment\">\n" +
                "            <li><span class=\"pinglun\"><a href=\"article/13319/1.html#comment\" target=\"_blank\">评论(141)</a></span><span class=\"new-tag\"> <a href=/tags/3314 target=\"_blank\" title=\"公益\" >公益</a> <a href=/tags/3337 target=\"_blank\" title=\"突发\" >突发</a></span></li>\n" +
                "        </ul>\n" +
                "    </div>\n" +
                "    <div class=\"clear\"></div>\n" +
                "</div>                <div class=\"article-box\">\n" +
                "    <div class=\"z article-box-img\"><a href=\"article/13305/1.html\" target=\"_blank\"><img class=\"a-img\" src=\"http://u.img.huxiu.com/portal/201304/20/072931iocyv6zcimckgz60.jpg.thumb.jpg\" width=\"220\" height=\"146\" title=\"这16人从科技出发，在时代中留下烙印\" alt=\"这16人从科技出发，在时代中留下烙印\"/></a></div>\n" +
                "    <div class=\"y t-sub2\">\n" +
                "        <ul class=\"summary-ul\">\n" +
                "            <li><h2 class=\"t-h\"><a href=\"article/13305/1.html\" target=\"_blank\">这16人从科技出发，在时代中留下烙印</a></h2></li>\n" +
                "            <li><a href=\"http://time100.time.com/2013/04/18/time-100/slide/all/#ixzz2QpmFwxzo\" target=\"_blank\">TIME.com</a>&nbsp;发表于 2013-04-20 07:27</li>\n" +
                "            <li class=\"summary\">这些人从科技出发，影响了“时代”，就像逝去的乔布斯曾说的，“在宇宙中留下一道烙印”。无一不具备冒险与探索精神。</li>\n" +
                "        </ul>\n" +
                "        <ul class=\"comment\">\n" +
                "            <li><span class=\"pinglun\"><a href=\"article/13305/1.html#comment\" target=\"_blank\">评论(13)</a></span><span class=\"new-tag\"> <a href=/tags/208 target=\"_blank\" title=\"任正非\" >任正非</a> <a href=/tags/248 target=\"_blank\" title=\"李开复\" >李开复</a> <a href=/tags/2263 target=\"_blank\" title=\"头条\" >头条</a></span></li>\n" +
                "        </ul>\n" +
                "    </div>\n" +
                "    <div class=\"clear\"></div>\n" +
                "</div>            </div>\n" +
                "    <a href=\"/focus/?more=1\" ><div class=\"index-more\">更多</div></a>\n" +
                "</div>\n" +
                "<div class=\"z v2left\"><div><script type=\"text/javascript\">BAIDU_CLB_fillSlot(\"543498\");</script></div>    <div class=\"index-opinions mod_title_w\"><a href=\"/opinions/\"  target=\"_blank\">观点</a></div>\n" +
                "    <div class=\"left-list\">\n" +
                "                <div class=\"left-box\">\n" +
                "    <ul>\n" +
                "        <li class=\"box-title\">\n" +
                "            <div class=\"z pingluntouxiang\"><a class=\"cl\" href=\"space/uid/10025/1.html\" target=\"_blank\"><img src=\"http://user.huxiu.com/auth/data/avatar/000/01/00/25_avatar_small.jpg\" onerror=\"this.onerror=null;this.src='http://user.huxiu.com/auth/images/noavatar_small.gif'\" /></a></div>\n" +
                "            <div class=\"y left-title\">\n" +
                "                <h2><a href=\"/article/13380/1.html\" target=\"_blank\">产品情感化设计的两个层面</a></h2>\n" +
                "                <h3 class=\"author-names\"><a href=\"space/uid/10025/1.html\" target=\"_blank\">云瑞</a></h3>\n" +
                "                <span>2013-04-22</span>\n" +
                "            </div>\n" +
                "        </li>\n" +
                "        <li class=\"left-summary\">现在的网站文案已经越来越有人情味了。例如提示文案不是“你的账号密码错误”而是“密码不对哦”，文案中增加了语气词。文案内容的情感化也会增加用户的接受程度</li>\n" +
                "        <li class=\"left-comment\"><a href=\"/article/13380/1.html#comment\" target=\"_blank\"><span class=\"pinglun\">评论(0)</span></a><span class=\"new-tag\"> <a href=/tags/329 target=\"_blank\" title=\"产品\" >产品</a> <a href=/tags/357 target=\"_blank\" title=\"投稿\" >投稿</a></span></li>\n" +
                "    </ul>\n" +
                "</div>                <div class=\"left-box\">\n" +
                "    <ul>\n" +
                "        <li class=\"box-title\">\n" +
                "            <div class=\"z pingluntouxiang\"><a class=\"cl\" href=\"space/uid/55564/1.html\" target=\"_blank\"><img src=\"http://user.huxiu.com/auth/data/avatar/000/05/55/64_avatar_small.jpg\" onerror=\"this.onerror=null;this.src='http://user.huxiu.com/auth/images/noavatar_small.gif'\" /></a></div>\n" +
                "            <div class=\"y left-title\">\n" +
                "                <h2><a href=\"/article/13376/1.html\" target=\"_blank\">从雅安地震看企业如何做灾难救助微博</a></h2>\n" +
                "                <h3 class=\"author-names\"><a href=\"space/uid/55564/1.html\" target=\"_blank\">uglyruby</a></h3>\n" +
                "                <span>2013-04-22</span>\n" +
                "            </div>\n" +
                "        </li>\n" +
                "        <li class=\"left-summary\">不做秀、不食言、不攀比，是总的原则；另外还有三个考虑：通过微博树立什么样的企业形象？怎么调动员工参与微博发布？怎么选择合作伙伴？</li>\n" +
                "        <li class=\"left-comment\"><a href=\"/article/13376/1.html#comment\" target=\"_blank\"><span class=\"pinglun\">评论(0)</span></a><span class=\"new-tag\"> <a href=/tags/55 target=\"_blank\" title=\"微博\" >微博</a> <a href=/tags/357 target=\"_blank\" title=\"投稿\" >投稿</a></span></li>\n" +
                "    </ul>\n" +
                "</div>                <div class=\"left-box\">\n" +
                "    <ul>\n" +
                "        <li class=\"box-title\">\n" +
                "            <div class=\"z pingluntouxiang\"><a class=\"cl\" href=\"space/uid/34236/1.html\" target=\"_blank\"><img src=\"http://user.huxiu.com/auth/data/avatar/000/03/42/36_avatar_small.jpg\" onerror=\"this.onerror=null;this.src='http://user.huxiu.com/auth/images/noavatar_small.gif'\" /></a></div>\n" +
                "            <div class=\"y left-title\">\n" +
                "                <h2><a href=\"/article/13352/1.html\" target=\"_blank\">无线淘宝2013可能这样新布局，来共享移动社交电商利益</a></h2>\n" +
                "                <h3 class=\"author-names\"><a href=\"space/uid/34236/1.html\" target=\"_blank\">品途网</a></h3>\n" +
                "                <span>2013-04-22</span>\n" +
                "            </div>\n" +
                "        </li>\n" +
                "        <li class=\"left-summary\">在3.0时代，每个卖家必须更注重店铺的差异化、个性化，做有特色的店铺。在运营店铺的过程中，要带上感情、资讯、消费文化等附加价值的东西</li>\n" +
                "        <li class=\"left-comment\"><a href=\"/article/13352/1.html#comment\" target=\"_blank\"><span class=\"pinglun\">评论(1)</span></a><span class=\"new-tag\"> <a href=/tags/36 target=\"_blank\" title=\"阿里巴巴\" >阿里巴巴</a> <a href=/tags/73 target=\"_blank\" title=\"电子商务\" >电子商务</a> <a href=/tags/92 target=\"_blank\" title=\"淘宝\" >淘宝</a> <a href=/tags/357 target=\"_blank\" title=\"投稿\" >投稿</a> <a href=/tags/637 target=\"_blank\" title=\"O2O\" >O2O</a></span></li>\n" +
                "    </ul>\n" +
                "</div>                <div class=\"left-box\">\n" +
                "    <ul>\n" +
                "        <li class=\"box-title\">\n" +
                "            <div class=\"z pingluntouxiang\"><a class=\"cl\" href=\"space/uid/19830/1.html\" target=\"_blank\"><img src=\"http://user.huxiu.com/auth/data/avatar/000/01/98/30_avatar_small.jpg\" onerror=\"this.onerror=null;this.src='http://user.huxiu.com/auth/images/noavatar_small.gif'\" /></a></div>\n" +
                "            <div class=\"y left-title\">\n" +
                "                <h2><a href=\"/article/13365/1.html\" target=\"_blank\">这一次救灾，互联网好样的！</a></h2>\n" +
                "                <h3 class=\"author-names\"><a href=\"space/uid/19830/1.html\" target=\"_blank\">葛甲</a></h3>\n" +
                "                <span>2013-04-22</span>\n" +
                "            </div>\n" +
                "        </li>\n" +
                "        <li class=\"left-summary\">本次四川雅安地震之后，网上谣言少了，辟谣的多了；传谣的少了，不信谣的多了；阴谋论少了，正能量多了；博眼球的企业少了，做实事的企业多了</li>\n" +
                "        <li class=\"left-comment\"><a href=\"/article/13365/1.html#comment\" target=\"_blank\"><span class=\"pinglun\">评论(4)</span></a><span class=\"new-tag\"> <a href=/tags/3314 target=\"_blank\" title=\"公益\" >公益</a></span></li>\n" +
                "    </ul>\n" +
                "</div>                <div class=\"left-box\">\n" +
                "    <ul>\n" +
                "        <li class=\"box-title\">\n" +
                "            <div class=\"z pingluntouxiang\"><a class=\"cl\" href=\"space/uid/82139/1.html\" target=\"_blank\"><img src=\"http://user.huxiu.com/auth/data/avatar/000/08/21/39_avatar_small.jpg\" onerror=\"this.onerror=null;this.src='http://user.huxiu.com/auth/images/noavatar_small.gif'\" /></a></div>\n" +
                "            <div class=\"y left-title\">\n" +
                "                <h2><a href=\"/article/13344/1.html\" target=\"_blank\">微信公众平台逼淘宝在无线端走得更快</a></h2>\n" +
                "                <h3 class=\"author-names\"><a href=\"space/uid/82139/1.html\" target=\"_blank\">捣乱帝</a></h3>\n" +
                "                <span>2013-04-22</span>\n" +
                "            </div>\n" +
                "        </li>\n" +
                "        <li class=\"left-summary\">淘宝无线加入“微淘”，与微信公众平台类似，卖家自营账号，通过账号运营转化订单；天猫无线采用全新订阅模式，由用户自主选择订阅账号</li>\n" +
                "        <li class=\"left-comment\"><a href=\"/article/13344/1.html#comment\" target=\"_blank\"><span class=\"pinglun\">评论(0)</span></a><span class=\"new-tag\"> <a href=/tags/68 target=\"_blank\" title=\"微信\" >微信</a> <a href=/tags/357 target=\"_blank\" title=\"投稿\" >投稿</a> <a href=/tags/3154 target=\"_blank\" title=\"淘宝网\" >淘宝网</a></span></li>\n" +
                "    </ul>\n" +
                "</div>                <div class=\"left-box\">\n" +
                "    <ul>\n" +
                "        <li class=\"box-title\">\n" +
                "            <div class=\"z pingluntouxiang\"><a class=\"cl\" href=\"space/uid/26088/1.html\" target=\"_blank\"><img src=\"http://user.huxiu.com/auth/data/avatar/000/02/60/88_avatar_small.jpg\" onerror=\"this.onerror=null;this.src='http://user.huxiu.com/auth/images/noavatar_small.gif'\" /></a></div>\n" +
                "            <div class=\"y left-title\">\n" +
                "                <h2><a href=\"/article/13343/1.html\" target=\"_blank\">除了寻人，网络公司还能做什么</a></h2>\n" +
                "                <h3 class=\"author-names\"><a href=\"space/uid/26088/1.html\" target=\"_blank\">mktreview</a></h3>\n" +
                "                <span>2013-04-21</span>\n" +
                "            </div>\n" +
                "        </li>\n" +
                "        <li class=\"left-summary\">寻人不是不好，但如果成了一种形式大于内容、资源不是最优化配置的方式，那还不如打破陈旧思维看看有没有更创新的方式</li>\n" +
                "        <li class=\"left-comment\"><a href=\"/article/13343/1.html#comment\" target=\"_blank\"><span class=\"pinglun\">评论(4)</span></a><span class=\"new-tag\"> <a href=/tags/357 target=\"_blank\" title=\"投稿\" >投稿</a> <a href=/tags/3314 target=\"_blank\" title=\"公益\" >公益</a></span></li>\n" +
                "    </ul>\n" +
                "</div>                <div class=\"left-box\">\n" +
                "    <ul>\n" +
                "        <li class=\"box-title\">\n" +
                "            <div class=\"z pingluntouxiang\"><a class=\"cl\" href=\"space/uid/0/1.html\" target=\"_blank\"><img src=\"http://user.huxiu.com/auth/data/avatar/000/00/00/00_avatar_small.jpg\" onerror=\"this.onerror=null;this.src='http://user.huxiu.com/auth/images/noavatar_small.gif'\" /></a></div>\n" +
                "            <div class=\"y left-title\">\n" +
                "                <h2><a href=\"/article/13338/1.html\" target=\"_blank\">关于地震和救灾的常见误区</a></h2>\n" +
                "                <h3 class=\"author-names\"><a href=\"space/uid/0/1.html\" target=\"_blank\">左志坚</a></h3>\n" +
                "                <span>2013-04-21</span>\n" +
                "            </div>\n" +
                "        </li>\n" +
                "        <li class=\"left-summary\">这一次，又是举国沸腾，但一些认知误区仍然存在。我想就这五年来的观察，做一些简单的总结，希望对关心灾区的朋友有些帮助和启发</li>\n" +
                "        <li class=\"left-comment\"><a href=\"/article/13338/1.html#comment\" target=\"_blank\"><span class=\"pinglun\">评论(8)</span></a><span class=\"new-tag\"> <a href=/tags/3314 target=\"_blank\" title=\"公益\" >公益</a></span></li>\n" +
                "    </ul>\n" +
                "</div>                <div class=\"left-box\">\n" +
                "    <ul>\n" +
                "        <li class=\"box-title\">\n" +
                "            <div class=\"z pingluntouxiang\"><a class=\"cl\" href=\"space/uid/13441/1.html\" target=\"_blank\"><img src=\"http://user.huxiu.com/auth/data/avatar/000/01/34/41_avatar_small.jpg\" onerror=\"this.onerror=null;this.src='http://user.huxiu.com/auth/images/noavatar_small.gif'\" /></a></div>\n" +
                "            <div class=\"y left-title\">\n" +
                "                <h2><a href=\"/article/13260/1.html\" target=\"_blank\">升级还是迎战：电商价格战中的哈姆雷特式问题</a></h2>\n" +
                "                <h3 class=\"author-names\"><a href=\"space/uid/13441/1.html\" target=\"_blank\">微笑前行11</a></h3>\n" +
                "                <span>2013-04-20</span>\n" +
                "            </div>\n" +
                "        </li>\n" +
                "        <li class=\"left-summary\">SNS和移动互联网是电子商务的两翼，在与电商的融合中二者才能找到赢利模式</li>\n" +
                "        <li class=\"left-comment\"><a href=\"/article/13260/1.html#comment\" target=\"_blank\"><span class=\"pinglun\">评论(8)</span></a><span class=\"new-tag\"> <a href=/tags/24 target=\"_blank\" title=\"京东商城\" >京东商城</a> <a href=/tags/73 target=\"_blank\" title=\"电子商务\" >电子商务</a> <a href=/tags/357 target=\"_blank\" title=\"投稿\" >投稿</a></span></li>\n" +
                "    </ul>\n" +
                "</div>            </div>\n" +
                "        <div>\t<div class=\"leftad\">\n" +
                "\t\t<script type=\"text/javascript\">BAIDU_CLB_fillSlot(\"468024\");</script>\n" +
                "\t</div>\n" +
                "\t<div class=\"tuiguang\">\n" +
                "\t\t<div class=\"weixin z\">\n" +
                "\t\t\t<div class=\"z\"><img src=\"/static/image/weixin2.jpg\" width=\"80\" height=\"80\" alt=\"官方微信\" title=\"微信扫描二维码,获得每日精选资讯\"/></div>\n" +
                "\t\t\t<div class=\"y\">\n" +
                "\t\t\t\t<ul>\n" +
                "\t\t\t\t\t<li><span>官方微信</span></li>\n" +
                "\t\t\t\t\t<li>微信扫描二维码,<br />获得每日精选资讯</li>\n" +
                "\t\t\t\t</ul>\n" +
                "\t\t\t</div>\n" +
                "\t\t</div>\n" +
                "\t\t<div class=\"weibo y\">\n" +
                "\t\t\t<ul>\n" +
                "\t\t\t\t<li><span>官方微博</span></li>\n" +
                "\t\t\t\t<li><iframe width=\"63\" height=\"24\" frameborder=\"0\" allowtransparency=\"true\" marginwidth=\"0\" marginheight=\"0\" scrolling=\"no\" border=\"0\" src=\"http://widget.weibo.com/relationship/followbutton.php?language=zh_cn&width=63&height=24&uid=2357213493&style=1&btn=light&dpc=1\"></iframe></li>\n" +
                "\t\t\t\t<li><iframe src=\"http://follow.v.t.qq.com/index.php?c=follow&a=quick&name=ihuxiu&style=5&t=1337138902861\" frameborder=\"0\" scrolling=\"auto\" width=\"65\" height=\"24\" marginwidth=\"0\" marginheight=\"0\" allowtransparency=\"true\"></iframe></li>\n" +
                "\t\t\t</ul>\n" +
                "\t\t</div>\n" +
                "\t</div></div>    <div class=\"index-comment mod_title_w\"><a href=\"#\" target=\"_blank\">最佳评论</a></div>\n" +
                "    <div class=\"left-list\">\n" +
                "        <div class=\"left-box\">\n" +
                "    <div class=\"z shouyepinglun\"><a href=\"http://weibo.com/u/3183014995\" title=\"新浪微博用户\" target=\"_blank\"><img src=\"http://tp1.sinaimg.cn/3183014995/50/0/1\" alt=\"viola603\"></a></div>\n" +
                "    <div class=\"y box-content\">\n" +
                "        <ul>\n" +
                "            <li><a href=\"http://weibo.com/u/3183014995\" title=\"新浪微博用户\">viola603</a></a></li>\n" +
                "            <li><a href=\"article/13357/1.html#g_pid234878\" target=\"_blank\">未来世界就是虚拟世界和现实世界的无缝交融。现实世界怎么挣钱，虚拟世界就怎么挣钱。根本的商业道理，从古至今到未来，没有实质的变化。只是现在的虚拟世界还非常不成熟，巴菲特也就按兵不动，也许下一代巴菲特会看到革命性的变化。 ...</a></li>\n" +
                "        </ul>\n" +
                "    </div>\n" +
                "    <div class=\"v2cl\"></div>\n" +
                "    <div class=\"originaltext\"><a href=\"article/13357/1.html#comment\" target=\"_blank\"><span>原文</span>亚马逊首位数据挖掘负责人往事：开发出亚马逊最赚钱项目</a></div>\n" +
                "</div>\n" +
                "                <div class=\"left-box\">\n" +
                "    <div class=\"z shouyepinglun\"> <a href=\"space/uid/23376/1.html\" title=\"略懂，略懂。。\" target=\"_blank\"><img src=\"http://user.huxiu.com/auth/avatar.php?uid=23376&size=small\" alt=\"dzlee\"></a></div>\n" +
                "    <div class=\"y box-content\">\n" +
                "        <ul>\n" +
                "            <li> <a href=\"space/uid/23376/1.html\" title=\"略懂，略懂。。\">dzlee</a></a></li>\n" +
                "            <li><a href=\"article/13324/1.html#g_pid234769\" target=\"_blank\">回PC端了，说几句。\n" +
                "1、电子商务。你对电子商务的理解，不敢苟同。说穿了，电子只是一种手段，归根结底是一种商务的形式。在电子商务之前，也有电话销售，也有电视直销，还有目录销售。比如电视直销，按照作者的说法，电视以拍摄、主持人为核心，而后者则注重研发，供应链，营销，产品，售后。电视直销 ...</a></li>\n" +
                "        </ul>\n" +
                "    </div>\n" +
                "    <div class=\"v2cl\"></div>\n" +
                "    <div class=\"originaltext\"><a href=\"article/13324/1.html#comment\" target=\"_blank\"><span>原文</span>我为什么说生鲜电商是个伪命题？</a></div>\n" +
                "</div>\n" +
                "                <div class=\"left-box\">\n" +
                "    <div class=\"z shouyepinglun\"> <a href=\"space/uid/23531/1.html\" title=\"介绍你妹！\" target=\"_blank\"><img src=\"http://user.huxiu.com/auth/avatar.php?uid=23531&size=small\" alt=\"学生炒饭\"></a></div>\n" +
                "    <div class=\"y box-content\">\n" +
                "        <ul>\n" +
                "            <li> <a href=\"space/uid/23531/1.html\" title=\"介绍你妹！\">学生炒饭</a></a></li>\n" +
                "            <li><a href=\"article/13358/1.html#g_pid234895\" target=\"_blank\">在微博半死不活之际，这场灾难可能正好救了微博一命，让唱衰者得以重新审视微博的社会价值。虽然微博里面还是有各种虚假信息，但是瑕不掩瑜。在信息的普及范围、传播速度、由此引起的社会参与度方面无人能及。 ...</a></li>\n" +
                "        </ul>\n" +
                "    </div>\n" +
                "    <div class=\"v2cl\"></div>\n" +
                "    <div class=\"originaltext\"><a href=\"article/13358/1.html#comment\" target=\"_blank\"><span>原文</span>震后48小时，互联网公司行动启示录</a></div>\n" +
                "</div>\n" +
                "                <div class=\"left-box\">\n" +
                "    <div class=\"z shouyepinglun\"> <a href=\"space/uid/23690/1.html\" title=\"专业零售者\" target=\"_blank\"><img src=\"http://user.huxiu.com/auth/avatar.php?uid=23690&size=small\" alt=\"wqwqwq191\"></a></div>\n" +
                "    <div class=\"y box-content\">\n" +
                "        <ul>\n" +
                "            <li> <a href=\"space/uid/23690/1.html\" title=\"专业零售者\">wqwqwq191</a></a></li>\n" +
                "            <li><a href=\"article/13358/1.html#g_pid234689\" target=\"_blank\">果然是互联网公司，第一时间，抓住了时间的概念！他们平时第一时间响应顾客需求，并迎合好管理好客户的时间，今天他们也能第一时间响应特殊事件！这就是新公司的价值，带来的最重要的时间价值！虎嗅的表现也不错，这样的分类介绍和总结非常缜密而不繁杂，同时专业性和大局观让他有号召力！赞！ ...</a></li>\n" +
                "        </ul>\n" +
                "    </div>\n" +
                "    <div class=\"v2cl\"></div>\n" +
                "    <div class=\"originaltext\"><a href=\"article/13358/1.html#comment\" target=\"_blank\"><span>原文</span>震后48小时，互联网公司行动启示录</a></div>\n" +
                "</div>\n" +
                "                <div class=\"left-box\">\n" +
                "    <div class=\"z shouyepinglun\"> <a href=\"space/uid/104033/1.html\" title=\"IT学生 智能科学与技术\" target=\"_blank\"><img src=\"http://user.huxiu.com/auth/avatar.php?uid=104033&size=small\" alt=\"MXRUSH\"></a></div>\n" +
                "    <div class=\"y box-content\">\n" +
                "        <ul>\n" +
                "            <li> <a href=\"space/uid/104033/1.html\" title=\"IT学生 智能科学与技术\">MXRUSH</a></a></li>\n" +
                "            <li><a href=\"article/13361/1.html#g_pid234690\" target=\"_blank\">从波士顿爆炸案美国媒体的反应，来看一下中国媒体的反应，可以对比一下：\n" +
                "一、实时：这是互联网时代的一大利好，无论发生什么重大事件，互联网都会第一时间站出来，将这些消息告诉普通大众。美国媒体的反应如上所提，中国的反应也差不多，在互联网时代，中美在信息的实时性方面差距一直在缩小，这一点 ...</a></li>\n" +
                "        </ul>\n" +
                "    </div>\n" +
                "    <div class=\"v2cl\"></div>\n" +
                "    <div class=\"originaltext\"><a href=\"article/13361/1.html#comment\" target=\"_blank\"><span>原文</span>网络时代，重大突发新闻报道为何一错再错？</a></div>\n" +
                "</div>\n" +
                "        \n" +
                "    </div>\n" +
                "</div>\n" +
                "<script type=\"text/javascript\" charset=\"utf-8\">\n" +
                "var jQ = jQuery.noConflict();\n" +
                "jQ(function(){\n" +
                "jQ('#wp').before('<div class=\"idx-tags\" style=\"width:960px;height:0;margin:0 auto;\"><div class=\"idx-tags-box\"><a href=\"http://www.huxiu.com/newtag.php\"></a></div></div>')\n" +
                "})\n" +
                "</script>\n" +
                "<div class=\"alllinks\"><ul>\n" +
                "<li class=\"li-top\"><a href=\"http://www.huxiu.com/links\">合作伙伴</a></li>\n" +
                "        <li><a href=\"http://www.aliyun.com/\" title=\"阿里云\" target=\"_blank\">阿里云</a></li>\n" +
                "        <li><a href=\"http://xianguo.com/hot\" title=\"鲜果\" target=\"_blank\">鲜果</a></li>\n" +
                "        <li><a href=\"http://www.iceo.com.cn\" title=\"中国企业家\" target=\"_blank\">中国企业家</a></li>\n" +
                "        <li><a href=\"http://www.gemag.com.cn/\" title=\"环球企业家\" target=\"_blank\">环球企业家</a></li>\n" +
                "        <li><a href=\"http://www.pedaily.cn/\" title=\"投资界\" target=\"_blank\">投资界</a></li>\n" +
                "        <li><a href=\"http://www.mediadreamworks.net\" title=\"传媒梦工场\" target=\"_blank\">传媒梦工场</a></li>\n" +
                "        <li><a href=\"http://cn.msn.com\" title=\"MSN中文网\" target=\"_blank\">MSN中文网</a></li>\n" +
                "        <li><a href=\"http://cn.yahoo.com/\" title=\"中国雅虎\" target=\"_blank\">中国雅虎</a></li>\n" +
                "        <li><a href=\"http://android.eoe.cn\" title=\"Android开发者社区\" target=\"_blank\">Android开发者社区</a></li>\n" +
                "        <li><a href=\"http://www.cnbeta.com\" title=\"cnbeta\" target=\"_blank\">cnbeta</a></li>\n" +
                "        <li><a href=\"http://www.chinaventure.com.cn/\" title=\"投资中国网\" target=\"_blank\">投资中国网</a></li>\n" +
                "        <li><a href=\"https://www.upyun.com/?md=huxiu\" title=\"又拍云存储\" target=\"_blank\">又拍云存储</a></li>\n" +
                "        <li><a href=\"http://www.prnasia.com/blog/ \" title=\"美通说传播\" target=\"_blank\">美通说传播</a></li>\n" +
                "        <li><a href=\"http://www.itchaguan.com/\" title=\"IT茶馆\" target=\"_blank\">IT茶馆</a></li>\n" +
                "        <li><a href=\"http://www.wshang.com/ \" title=\"网商在线\" target=\"_blank\">网商在线</a></li>\n" +
                "        <li><a href=\"http://www.ebusinessreview.cn/\" title=\"商业评论网\" target=\"_blank\">商业评论网</a></li>\n" +
                "        <li><a href=\"http://techorange.com/\" title=\"TechOrange\" target=\"_blank\">TechOrange</a></li>\n" +
                "        <li><a href=\"http://www.ittime.com.cn/\" title=\"IT时代周刊\" target=\"_blank\">IT时代周刊</a></li>\n" +
                "        </ul>\n" +
                "<ul>\n" +
                "<li class=\"li-top\"><a href=\"http://www.huxiu.com/links\">内容合作伙伴</a></li>\n" +
                "        <li><a href=\"http://www.caijing.com.cn\" title=\"财经网\" target=\"_blank\">财经网</a></li>\n" +
                "        <li><a href=\"http://www.donews.com/\" title=\"DoNews\" target=\"_blank\">DoNews</a></li>\n" +
                "        <li><a href=\"http://finance.ifeng.com/\" title=\"凤凰财经\" target=\"_blank\">凤凰财经</a></li>\n" +
                "        <li><a href=\"http://www.xcf.cn/\" title=\"新财富\" target=\"_blank\">新财富</a></li>\n" +
                "        <li><a href=\"http://www.eoe.cn\" title=\"eoe移动开发者社区\" target=\"_blank\">eoe移动开发者社区</a></li>\n" +
                "        <li><a href=\"http://www.iheima.com/\" title=\"i黑马\" target=\"_blank\">i黑马</a></li>\n" +
                "        <li><a href=\"http://tech.163.com/\" title=\"网易科技\" target=\"_blank\">网易科技</a></li>\n" +
                "        <li><a href=\"http://tech.sina.com.cn/\" title=\"新浪科技\" target=\"_blank\">新浪科技</a></li>\n" +
                "        <li><a href=\"http://it.sohu.com\" title=\"搜狐IT\" target=\"_blank\">搜狐IT</a></li>\n" +
                "        <li><a href=\"http://www.chuangyejia.com/\" title=\"创业家\" target=\"_blank\">创业家</a></li>\n" +
                "        <li><a href=\"http://www.cyzone.cn/\" title=\"创业邦\" target=\"_blank\">创业邦</a></li>\n" +
                "        <li><a href=\"http://finance.qq.com/\" title=\"腾讯财经\" target=\"_blank\">腾讯财经</a></li>\n" +
                "        <li><a href=\"http://www.forbeschina.com/ \" title=\"福布斯中文网\" target=\"_blank\">福布斯中文网</a></li>\n" +
                "        <li><a href=\"http://i.wshang.com/\" title=\"天下网商\" target=\"_blank\">天下网商</a></li>\n" +
                "        <li><a href=\"http://www.techweb.com.cn/\" title=\"TechWeb\" target=\"_blank\">TechWeb</a></li>\n" +
                "        <li><a href=\"http://www.leiphone.com/\" title=\"雷锋网\" target=\"_blank\">雷锋网</a></li>\n" +
                "        <li><a href=\"http://chuangye.sina.com.cn\" title=\"新浪创业\" target=\"_blank\">新浪创业</a></li>\n" +
                "        <li><a href=\"http://tech.hexun.com/\" title=\"和讯科技\" target=\"_blank\">和讯科技</a></li>\n" +
                "        <li><a href=\"http://www.pintu360.com\" title=\"品途O2O\" target=\"_blank\">品途O2O</a></li>\n" +
                "        <li><a href=\"http://www.geekpark.net/\" title=\"极客公园\" target=\"_blank\">极客公园</a></li>\n" +
                "    </ul>\n" +
                "</div>\t</div>\n" +
                "\n" +
                "\n" +
                "<div id=\"ft\" >\n" +
                "<div class=\"ft-c\">\n" +
                "<p><a href=\"http://www.huxiu.com/about\" >关于我们</a><span class=\"pipe\">|</span><a href=\"http://www.huxiu.com/joinus\" >加入我们</a><span class=\"pipe\">|</span><a href=\"http://www.huxiu.com/contact\" >广告及服务</a><span class=\"pipe\">|</span><a href=\"http://www.huxiu.com/help\" >常见问题解答</a><span class=\"pipe\">|</span><a href=\"javascript:;\" onclick=\"showWindow('miscreport', 'misc.php?mod=report&amp;url='+REPORTURL);return false;\">提交建议</a>\n" +
                "\n" +
                "</p>\n" +
                "<p>Copyright &copy; <a href=\"http://www.huxiu.com\">虎嗅网</a>\n" +
                "( <a href=\"http://www.miitbeian.gov.cn/\" target=\"_blank\">京ICP备12013432</a> )</p>\n" +
                "</div>\n" +
                "<div class=\"hide\"><!-- baidu -->\n" +
                "<script type=\"text/javascript\">\n" +
                "var _bdhmProtocol = ((\"https:\" == document.location.protocol) ? \" https://\" : \" http://\");\n" +
                "document.write(unescape(\"%3Cscript src='\" + _bdhmProtocol + \"hm.baidu.com/h.js%3F324368ef52596457d064ca5db8c6618e' type='text/javascript'%3E%3C/script%3E\"));\n" +
                "</script>\n" +
                "<!-- baidu -->&nbsp;<a href=\"http://discuz.qq.com/service/security\" target=\"_blank\" title=\"防水墙保卫网站远离侵害\"><img src=\"static/image/common/security.png\"></a></div></div>\n" +
                "\n" +
                "<span id=\"scrolltop\" onclick=\"window.scrollTo('0','0')\">回顶部</span>\n" +
                "<script type=\"text/javascript\">_attachEvent(window, 'scroll', function(){showTopLink();});</script>\n" +
                "\t\t\t<div id=\"discuz_tips\" style=\"display:none;\"></div>\n" +
                "\t\t\t<script type=\"text/javascript\">\n" +
                "\t\t\t\tvar discuzSId = '9059476';\n" +
                "\t\t\t\tvar discuzVersion = 'X2.5';\n" +
                "\t\t\t\tvar discuzRelease = '20120901';\n" +
                "\t\t\t\tvar discuzApi = '0.6';\n" +
                "\t\t\t\tvar discuzIsFounder = '';\n" +
                "\t\t\t\tvar discuzFixbug = '25000002';\n" +
                "\t\t\t\tvar discuzAdminId = '0';\n" +
                "\t\t\t\tvar discuzOpenId = '';\n" +
                "\t\t\t\tvar discuzUid = '0';\n" +
                "\t\t\t\tvar discuzGroupId = '7';\n" +
                "\t\t\t\tvar ts = '1366634279';\n" +
                "\t\t\t\tvar sig = '6fb5fd6c1ade1456116585d4915d8578';\n" +
                "\t\t\t\tvar discuzTipsCVersion = '2';\n" +
                "\t\t\t</script>\n" +
                "\t\t\t<script src=\"http://discuz.gtimg.cn/cloud/scripts/discuz_tips.js?v=1\" type=\"text/javascript\" charset=\"UTF-8\"></script></body>\n" +
                "</html>\n";
        String newHtml = UrlUtils.fixAllRelativeHrefs(html, "http://www.huxiu.com/");
        String text = "<a class=\"xu_subscribe\" href=\"home.php?mod=spacecp&amp;ac=profile&amp;op=info\" >订阅<span >虎嗅</span></a>";
        Assert.assertTrue(html.contains("<a href=\"article"));
        Assert.assertFalse(newHtml.contains("<a href=\"article"));
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
