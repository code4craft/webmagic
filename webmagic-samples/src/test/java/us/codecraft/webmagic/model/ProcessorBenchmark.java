package us.codecraft.webmagic.model;

import org.junit.Ignore;
import org.junit.Test;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.samples.OschinaBlog;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.PlainText;

/**
 * @author code4crafter@gmail.com
 */
public class ProcessorBenchmark {

    @Ignore
    @Test
    public void test() {
        ModelPageProcessor modelPageProcessor = ModelPageProcessor.create(Site.me(), OschinaBlog.class);
        Page page = new Page();
        page.setRequest(new Request("http://my.oschina.net/flashsword/blog"));
        page.setUrl(new PlainText("http://my.oschina.net/flashsword/blog"));
        page.setHtml(new Html(html));
        long time = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            modelPageProcessor.process(page);
        }
        System.out.println(System.currentTimeMillis() - time);
        time = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            modelPageProcessor.process(page);
        }
        System.out.println(System.currentTimeMillis() - time);
    }

    private String html = "\n" +
            "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
            "<html lang='zh-CN' xml:lang='zh-CN' xmlns='http://www.w3.org/1999/xhtml'>\n" +
            "<head>\n" +
            "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\n" +
            "  <meta http-equiv=\"Content-Language\" content=\"zh-CN\"/>\n" +
            "  <meta name=\"robots\" content=\"index, follow\" />\n" +
            "  <link rel=\"shortcut icon\" type=\"image/x-icon\" href=\"/img/favicon.ico\" />\n" +
            "  <title>Jsoup代码解读之八-防御XSS攻击 -  黄亿华的个人页面 - 开源中国社区</title>\n" +
            "    <meta name=\"Keywords\" content=\"Jsoup,XSS,OO\"/>\n" +
            "      <meta name=\"Description\" content=\"Jsoup代码解读之八-防御XSS攻击：![hacker][1] ## 防御XSS攻击的一般原理 cleaner是Jsoup的重要功能之一，我们常用它来进行富文本输入中的...\"/>\n" +
            "    <link rel=\"stylesheet/less\" href=\"http://my.oschina.net/flashsword/styles.less?ver=20130608&date=20130524070359\" type=\"text/css\" media=\"screen\" />\n" +
            "  <link rel=\"stylesheet\" href=\"/js/2012/poshytip/tip-yellowsimple/tip-yellowsimple.css\" type=\"text/css\" />\n" +
            "  <link rel=\"stylesheet\" type=\"text/css\" href=\"/js/2011/fancybox/jquery.fancybox-1.3.4.css\" media=\"screen\" />\n" +
            "  <script type=\"text/javascript\" src=\"/js/2012/jquery-1.7.1.min.js\"></script>\n" +
            "  <script type=\"text/javascript\" src=\"/js/2012/jquery.form.js\"></script>\n" +
            "  <script type=\"text/javascript\" src=\"/js/2011/fancybox/jquery.fancybox-1.3.4.pack.js\"></script>\n" +
            "  <script type=\"text/javascript\" src=\"/js/2012/poshytip/jquery.poshytip.min.js\"></script>\n" +
            "  <script type=\"text/javascript\" src=\"/js/2011/oschina.js?ver=20121007\"></script>\n" +
            "  <script type=\"text/javascript\" src=\"/js/2012/less-1.3.0.min.js\"></script>\n" +
            "  <script type=\"text/javascript\" src=\"/js/scrolltopcontrol.js\"></script>\n" +
            "  <script type='text/javascript' src='/js/jquery/jquery.atwho.js'></script>\n" +
            "  <link rel=\"stylesheet\" type=\"text/css\" href=\"/js/jquery/jquery.atwho.css\" />\n" +
            "  <link rel=\"alternate\" type=\"application/rss+xml\" title=\"黄亿华最新博客\" href=\"http://my.oschina.net/flashsword/rss\" />\n" +
            "  <link rel=\"EditURI\" type=\"application/rsd+xml\" title=\"RSD\" href=\"http://my.oschina.net/action/xmlrpc/rsd?space=190591\" />\n" +
            "  <link rel=\"wlwmanifest\" type=\"application/wlwmanifest+xml\" href=\"http://my.oschina.net/action/xmlrpc/wlwmanifest?space=190591\" /> \n" +
            "  <style type=\"text/css\">\n" +
            "    body,table,input,textarea,select {font-family:Verdana,sans-serif,宋体;}\t\n" +
            "  </style>\n" +
            "  <script type=\"text/javascript\">\n" +
            "  \tscrolltotop.offset(100,165);\n" +
            "\tscrolltotop.init();\n" +
            "  </script>\n" +
            "</head>\n" +
            "<body>\n" +
            "<div id=\"OSC_Screen\">\n" +
            "\t<div id='OSC_Banner'>\n" +
            "\t\t<div id=\"OSC_Logo\">\n" +
            "        \t<a href=\"http://www.oschina.net/\" title=\"开源中国社区首页\">开源中国社区</a>\n" +
            "        </div>\n" +
            "        <div id='OSC_Slogon'>开源项目发现、使用和交流平台</div>\n" +
            "\t\t        <div id=\"OSC_Channels\">\n" +
            "        \t<ul>\n" +
            "        \t<li><a href=\"http://www.oschina.net/project\" class='software'>项目</a></li>\n" +
            "        \t<li><a href=\"http://www.oschina.net/question\" class='question'>讨论</a></li>\n" +
            "        \t<li><a href=\"http://www.oschina.net/code/list\" class='code'>代码</a></li>\n" +
            "        \t<li><a href=\"http://www.oschina.net/news\" class='news'>资讯</a></li>\n" +
            "        \t<li><a href=\"http://www.oschina.net/translate\" class='translate'>翻译</a></li>\n" +
            "        \t<li><a href=\"http://www.oschina.net/blog\" class='blog'>博客</a></li>\n" +
            "        \t<li><a href=\"http://www.oschina.net/android\" class='android'>Android</a></li>\n" +
            "        \t<li><a href=\"http://www.oschina.net/job\" class='job'>招聘</a></li>\n" +
            "        \t</ul>\n" +
            "        </div>\n" +
            "        <div class='clear'></div>\n" +
            "\t</div>\n" +
            "\t<div id=\"OSC_Topbar\">\n" +
            "\t\t<div id=\"VisitorInfo\">\n" +
            "\t\t当前访客身份：\n" +
            "\t\t\t\t黄亿华 [ <a href=\"/action/user/logout?session=6db40e6e2d1061998068&goto_page=http%3A%2F%2Fmy.oschina.net%2Fflashsword\">退出</a> ]\n" +
            "\t\t\t\t<span id=\"OSC_Notification\">\t\t\t\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"http://my.oschina.net/flashsword/admin/inbox\" class=\"msgbox\" title=\"进入我的留言箱\">你有<em>0</em>新留言</a>\t\t\t\n" +
            "\t\t\t\t\t\t\t\t\t\t\t</span>\n" +
            "\t\t</div>\n" +
            "\t\t<div id=\"SearchBar\">\n" +
            "    \t\t<form action=\"http://www.oschina.net/search\">\n" +
            "\t\t\t\t<input type='hidden' name='user' value='190591'/>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"ipt f_l\">\n" +
            "    \t\t\t<input type='text' id='txt_q' name='q' class='SERACH' value='在 26755 款开源软件中搜索' onblur=\"(this.value=='')?this.value='在 26755 款开源软件中搜索':this.value\" onfocus=\"if(this.value=='在 26755 款开源软件中搜索'){this.value='';};this.select();\"/>\n" +
            "\t\t\t\t</span>\n" +
            "\t\t\t\t                <div class=\"search-by selectbox\">\n" +
            "    \t\t\t\t<span class=\"hide\">\n" +
            "    \t\t\t\t<select name='scope'>\t\t\t\t\t\n" +
            "                        <option value='project' selected>软件</option>\n" +
            "                        <option value='code'>代码</option>\n" +
            "                        <option value='bbs'>讨论区</option>\n" +
            "                        <option value='news'>新闻</option>\n" +
            "                        <option value='blog'>博客</option>\n" +
            "    \t\t\t\t</select>\n" +
            "    \t\t\t\t</span>\n" +
            "                  <div class=\"search_on\" id=\"search-item\"><span class=\"text\">软件</span></div>\n" +
            "                  <ul class=\"search_list\">\n" +
            "                     <li class=\"search-item\"><a href=\"#1\">软件</a></li>\n" +
            "                     <li><a href=\"#2\">代码</a></li>\n" +
            "                     <li><a href=\"#3\">讨论区</a></li>\n" +
            "                     <li><a href=\"#4\">新闻</a></li>\n" +
            "                     <li><a href=\"#5\">博客</a></li>\n" +
            "                  </ul>\n" +
            "                </div>\n" +
            "\t\t\t\t<input type='submit' value='搜索' class='bnt f_r'/>\t\t\t\n" +
            "    \t\t</form>\n" +
            "\t\t</div>\n" +
            "\t\t<div class='clear'></div>\n" +
            "\t</div>\n" +
            "\t<div id=\"OSC_Content\">\t\n" +
            "\n" +
            "<div id='SpaceLeft'>\n" +
            "<div class='Owner'>\n" +
            "\t\t<a href='http://my.oschina.net/flashsword/admin/user-settings?tab=3' title='切换空间风格' class='ThemeSetting'>切换风格</a>    <a href=\"http://my.oschina.net/flashsword\" class='Img'><img src=\"http://static.oschina.net/uploads/user/95/190591_100.jpg?t=1347254905000\" align=\"absmiddle\" alt=\"黄亿华\" title=\"黄亿华\" class=\"LargePortrait\"/></a>\n" +
            "    <span class='U'>\n" +
            "        <a href=\"http://my.oschina.net/flashsword\" class='Name' title='男'>黄亿华</a>\n" +
            "\t\t<span class='opts'>\n" +
            "\t\t\t<img src=\"/img/2012/men.png\" align='absmiddle' title='男'/>\n" +
            "        \t\t\t<a href=\"http://my.oschina.net/flashsword/admin/profile\">修改资料</a>\n" +
            "\t\t\t<a href=\"http://my.oschina.net/flashsword/admin/portrait\">更换头像</a>\n" +
            "        \t\t</span>\n" +
            "    </span>\n" +
            "    <div class='clear'></div>\n" +
            "    <div class='stat'>\n" +
            "    \t<a href=\"http://my.oschina.net/flashsword/fellow\">关注(43)</a>\n" +
            "    \t<a href=\"http://my.oschina.net/flashsword/fans\">粉丝(98)</a>\n" +
            "    \t<a href=\"http://www.oschina.net/question/3307_20931\" title=\"查看OSCHINA积分规则\">积分(173)</a>\n" +
            "    </div>\n" +
            "</div><style>\n" +
            "#MyResume textarea {width:170px;height:60px;font-size:9pt;}\n" +
            "</style>\n" +
            "<div class='Resume' id='MyResume'>\n" +
            "码农一枚<br>实用主义者<br>抵制重复造轮子，却造了不少轮子<br>http://codecraft.us</div>\n" +
            "<script type=\"text/javascript\" src=\"/js/2012/jquery.editinplace.js\"></script>\n" +
            "<script type=\"text/javascript\">\n" +
            "$(\"#MyResume\").editInPlace({\n" +
            "    url: \"/action/profile/update_user_signature?user_code=tzm9Wg2YoU8SkJaTIjHQkahStiXQNyymUGXFOQgN\",\n" +
            "\tbg_over: \"none\",\n" +
            "\tbg_out: \"none\",\n" +
            "    field_type: \"textarea\",\n" +
            "\tvalue_required: \"true\",\n" +
            "\terror: function(){\n" +
            "\t\talert(\"修改个人简介失败\");\n" +
            "\t}\n" +
            "});\n" +
            "</script>\n" +
            "\n" +
            "<div class='Opts clearfix'>\n" +
            "\t<a href=\"http://my.oschina.net/flashsword/admin/new-blog\" class='a1 blog'><i>.</i><span>发表博文</span></a>\n" +
            "\t<a href=\"http://my.oschina.net/flashsword/admin\" class='a2 admin'><i>.</i><span>空间管理</span></a>\n" +
            "</div><div class=\"Mod\" id=\"BlogCatalogs\">\n" +
            "  <strong><a href=\"http://my.oschina.net/flashsword/admin/blog-catalogs\" class=\"more\">管理&raquo;</a> 博客分类</strong>\n" +
            "  <ul>\n" +
            "\t\t\t<li class='draft'><a href=\"http://my.oschina.net/flashsword/admin/drafts\">草稿箱</a><span>(4)</span></li>\n" +
            "\t    \t<li><a href=\"http://my.oschina.net/flashsword/blog?catalog=371362\">webmagic</a><span>(16)</span></li>\n" +
            "\t\t<li><a href=\"http://my.oschina.net/flashsword/blog?catalog=380473\">分布式消息系统</a><span>(5)</span></li>\n" +
            "\t\t<li><a href=\"http://my.oschina.net/flashsword/blog?catalog=285504\">探耽求究</a><span>(5)</span></li>\n" +
            "\t\t<li><a href=\"http://my.oschina.net/flashsword/blog?catalog=368513\">BlackHoleJ</a><span>(21)</span></li>\n" +
            "\t\t<li><a href=\"http://my.oschina.net/flashsword/blog?catalog=368514\">Intellij</a><span>(4)</span></li>\n" +
            "\t\t<li><a href=\"http://my.oschina.net/flashsword/blog?catalog=112331\">工作日志</a><span>(7)</span></li>\n" +
            "\t\t<li><a href=\"http://my.oschina.net/flashsword/blog?catalog=112332\">日常记录</a><span>(4)</span></li>\n" +
            "\t\t<li><a href=\"http://my.oschina.net/flashsword/blog?catalog=261044\">codecraft</a><span>(1)</span></li>\n" +
            "\t\t<li><a href=\"http://my.oschina.net/flashsword/blog?catalog=279271\">开发日记</a><span>(3)</span></li>\n" +
            "\t  </ul>\n" +
            "</div><div class=\"Mod\" id=\"HotBlogs\">\n" +
            "  <strong>阅读排行</strong>\n" +
            "  <ol>\n" +
            "\t\t\t<li><a href=\"http://my.oschina.net/flashsword/blog/145796\">1. webmagic的设计机制及原理-如何开发一个Java爬虫</a></li>\n" +
            "\t\t\t\t<li><a href=\"http://my.oschina.net/flashsword/blog/143028\">2. monkeysocks开发日志--TCP协议分析及架构规划</a></li>\n" +
            "\t\t\t\t<li><a href=\"http://my.oschina.net/flashsword/blog/156638\">3. 【整理】国内一些大公司的开源项目</a></li>\n" +
            "\t\t\t\t<li><a href=\"http://my.oschina.net/flashsword/blog/110276\">4. BlackHole开发日志--防止DNS污染</a></li>\n" +
            "\t\t\t\t<li><a href=\"http://my.oschina.net/flashsword/blog/158200\">5. Jsoup代码解读之八-防御XSS攻击</a></li>\n" +
            "\t\t\t\t<li><a href=\"http://my.oschina.net/flashsword/blog/123505\">6. IntelliJ IDEA使用心得</a></li>\n" +
            "\t\t\t\t<li><a href=\"http://my.oschina.net/flashsword/blog/80037\">7. 关于HTTP keep-alive的实验</a></li>\n" +
            "\t\t\t\t<li><a href=\"http://my.oschina.net/flashsword/blog/152263\">8. 分布式消息系统研究报告之Kafka</a></li>\n" +
            "\t\t  </ol>\n" +
            "</div>\n" +
            "<div class=\"Mod\" id=\"BlogReplies\">\n" +
            "  <strong><a href=\"http://my.oschina.net/flashsword/admin/blog-comments\" class=\"more\">管理&raquo;</a> 最新评论</strong>  \n" +
            "      <ul>\n" +
            "\t\t<li>\n" +
            "\t\t<a href=\"http://my.oschina.net/flashsword\">@黄亿华</a>：引用来自“lidongyang”的评论 引用来自“黄亿华...\n" +
            "\t\t<a href=\"/action/tweet/go?obj=275640366&type=18&user=190591\">查看&raquo;</a>\n" +
            "\t</li>\n" +
            "\t\t<li>\n" +
            "\t\t<a href=\"http://my.oschina.net/lidongyang\">@lidongyang</a>：引用来自“黄亿华”的评论 引用来自“lidongyan...\n" +
            "\t\t<a href=\"/action/tweet/go?obj=275640301&type=18&user=723383\">查看&raquo;</a>\n" +
            "\t</li>\n" +
            "\t\t<li>\n" +
            "\t\t<a href=\"http://my.oschina.net/flashsword\">@黄亿华</a>：引用来自“lidongyang”的评论 引用来自“黄亿华...\n" +
            "\t\t<a href=\"/action/tweet/go?obj=275638563&type=18&user=190591\">查看&raquo;</a>\n" +
            "\t</li>\n" +
            "\t\t<li>\n" +
            "\t\t<a href=\"http://my.oschina.net/lidongyang\">@lidongyang</a>：引用来自“黄亿华”的评论 引用来自“lidongyan...\n" +
            "\t\t<a href=\"/action/tweet/go?obj=275638070&type=18&user=723383\">查看&raquo;</a>\n" +
            "\t</li>\n" +
            "\t\t<li>\n" +
            "\t\t<a href=\"http://my.oschina.net/flashsword\">@黄亿华</a>：引用来自“searchjack”的评论 不是好的就会被认...\n" +
            "\t\t<a href=\"/action/tweet/go?obj=275617319&type=18&user=190591\">查看&raquo;</a>\n" +
            "\t</li>\n" +
            "\t\t<li>\n" +
            "\t\t<a href=\"http://my.oschina.net/searchjack\">@searchjack</a>：不是好的就会被认可， 干自己的， 到时候， 单干\n" +
            "\t\t<a href=\"/action/tweet/go?obj=275617235&type=18&user=234880\">查看&raquo;</a>\n" +
            "\t</li>\n" +
            "\t\t<li>\n" +
            "\t\t<a href=\"http://my.oschina.net/searchjack\">@searchjack</a>：极好的工具，\n" +
            "\t\t<a href=\"/action/tweet/go?obj=275616963&type=18&user=234880\">查看&raquo;</a>\n" +
            "\t</li>\n" +
            "\t\t<li>\n" +
            "\t\t<a href=\"http://my.oschina.net/flashsword\">@黄亿华</a>：引用来自“静风流云”的评论 貌似，OSC也是类似处...\n" +
            "\t\t<a href=\"/action/tweet/go?obj=275599170&type=18&user=190591\">查看&raquo;</a>\n" +
            "\t</li>\n" +
            "\t\t<li>\n" +
            "\t\t<a href=\"http://my.oschina.net/rox\">@静风流云</a>：貌似，OSC也是类似处理的。\n" +
            "\t\t<a href=\"/action/tweet/go?obj=275599137&type=18&user=180\">查看&raquo;</a>\n" +
            "\t</li>\n" +
            "\t\t<li>\n" +
            "\t\t<a href=\"http://my.oschina.net/flashsword\">@黄亿华</a>：引用来自“仪山湖”的评论 最近要写个爬虫，看了...\n" +
            "\t\t<a href=\"/action/tweet/go?obj=275570030&type=18&user=190591\">查看&raquo;</a>\n" +
            "\t</li>\n" +
            "\t  </ul>\n" +
            "  </div>\n" +
            "<div class='Mod' id='Stat'>\n" +
            "<strong>访客统计</strong>\n" +
            "<ul>\n" +
            "\t<li><label>今日访问：</label>6 (<a href=\"http://my.oschina.net/flashsword/visitors\">查看最新访客&raquo;</a>)</li>\n" +
            "    <li><label>昨日访问：</label>284</li>\n" +
            "    <li><label>本周访问：</label>817</li>\n" +
            "    <li><label>本月访问：</label>1888</li>\n" +
            "    <li><label>所有访问：</label>16453</li>\n" +
            "</ul>\n" +
            "</div></div>\n" +
            "\n" +
            "<div class='SpaceList'>\n" +
            "\t<div class='TopBar'>\n" +
            "    \t<div class='NavPath'>\t\t\n" +
            "    \t\t<a href='http://my.oschina.net/flashsword'>空间</a> &raquo; <a href='http://my.oschina.net/flashsword/blog'>博客</a>\t\t\t\n" +
            "\t\t\t&raquo; <a href=\"http://my.oschina.net/flashsword/blog?catalog=371362\">webmagic</a>\n" +
            "\t\t\t&raquo; 博客正文\n" +
            "    \t</div>\n" +
            "\t</div>\n" +
            "\t\n" +
            "    \t<div class='BlogEntity'>\t\t\n" +
            "      <div class='BlogTitle'>\n" +
            "        <h1><img src='/img/space/b1.gif' align='absmiddle'/> Jsoup代码解读之八-防御XSS攻击</h1>\n" +
            "        <div class='BlogStat'>\n" +
            "    \t\t    \t\t    \t\t<span class='admin'>\n" +
            "    \t\t\t<a href=\"http://my.oschina.net/flashsword/admin/edit-blog?blog=158200\">编辑</a>&nbsp;|&nbsp;<a href=\"javascript:delete_blog(158200)\">删除</a>\n" +
            "    \t\t</span>\n" +
            "\t\t\t    \t\t    \t\t发表于3天前(2013-08-31 08:24) , \n" +
            "    \t\t已有<strong>1628</strong>次阅读 ，共<strong><a href=\"#comments\">3</a></strong>个评论\n" +
            "    \t\t\t\t\t，共 <strong>79</strong> 人收藏此文    \t</div> \n" +
            "      </div>\n" +
            "\t  \t            <div class=\"BlogAnchor\">\n" +
            "            <p>目录：[ <strong><a href=\"#\" id=\"AnchorContentToggle\" title=\"收起\">-</a></strong> ]</p>\n" +
            "            <div class=\"AnchorContent\" id=\"AnchorContent\"><li class='osc_h2'><a href='#OSC_h2_1'>防御XSS攻击的一般原理</a></li><li class='osc_h2'><a href='#OSC_h2_2'>Cleaner与Whitelist</a></li><li class='osc_h2'><a href='#OSC_h2_3'>结束语</a></li></div>\n" +
            "    \t  </div>\n" +
            "          <script>\n" +
            "\t\t  \t$(function(){\n" +
            "\t\t\t\t$(\"#AnchorContentToggle\").click(function(){\n" +
            "\t\t\t\t\tvar text = $(this).html();\n" +
            "\t\t\t\t\tif(text==\"-\"){\n" +
            "\t\t\t\t\t\t$(this).html(\"+\");\n" +
            "\t\t\t\t\t\t$(this).attr({\"title\":\"展开\"});\n" +
            "\t\t\t\t\t}else{\n" +
            "\t\t\t\t\t\t$(this).html(\"-\");\n" +
            "\t\t\t\t\t\t$(this).attr({\"title\":\"收起\"});\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t\t$(\"#AnchorContent\").toggle();\n" +
            "\t\t\t\t});\n" +
            "\t\t\t});\n" +
            "\t\t  </script>\n" +
            "\t  \t  <div class='BlogContent'><p><img src=\"http://static.oschina.net/uploads/space/2013/0831/071752_RBZc_190591.png\" /></p> \n" +
            "<span id=\"OSC_h2_1\"></span>\n" +
            "<h2>防御XSS攻击的一般原理</h2> \n" +
            "<p>cleaner是Jsoup的重要功能之一，我们常用它来进行富文本输入中的XSS防御。</p> \n" +
            "<p>我们知道，XSS攻击的一般方式是，通过在页面输入中嵌入一段恶意脚本，对输出时的DOM结构进行修改，从而达到执行这段脚本的目的。对于纯文本输入，过滤/转义HTML特殊字符<code>&lt;</code>,<code>&gt;</code>,<code>&quot;</code>,<code>'</code>是行之有效的办法，但是如果本身用户输入的就是一段HTML文本(例如博客文章)，这种方式就不太有效了。这个时候，就是Jsoup大显身手的时候了。</p> \n" +
            "<p>在前面，我们已经知道了，Jsoup里怎么将HTML变成一棵DOM树，怎么对DOM树进行遍历，怎么对DOM文档进行输出，那么其实cleaner的实现方式，也能猜出大概了。使用Jsoup进行XSS防御，大致分为三个步骤:</p> \n" +
            "<ol> \n" +
            " <li><p>将HTML解析为DOM树</p> <p>这一步可以过滤掉一些企图搞破坏的非闭合标签、非正常语法等。例如一些输入，会尝试用<code>&lt;/textarea&gt;</code>闭合当前Tag，然后写入攻击脚本。而根据前面对Jsoup的parser的分析，这种时候，这些非闭合标签会被当做错误并丢弃。</p></li> \n" +
            " <li><p>过滤高风险标签/属性/属性值</p> <p>高风险标签是指<code>&lt;script&gt;</code>以及类似标签，对属性/属性值进行过滤是因为某些属性值里也可以写入javascript脚本，例如<code>onclick='alert(&quot;xss!&quot;)'</code>。</p></li> \n" +
            " <li><p>重新将DOM树输出为HTML文本</p> <p>DOM树的输出，在前面(Jsoup代码解读之三)已经提到过了。</p></li> \n" +
            "</ol> \n" +
            "<span id=\"OSC_h2_2\"></span>\n" +
            "<h2>Cleaner与Whitelist</h2> \n" +
            "<p>对于上述的两个步骤，1、3都已经分别在parser和输出中完成，现在只剩下步骤 2：过滤高风险标签等。</p> \n" +
            "<p>Jsoup给出的答案是白名单。下面是<code>Whitelist</code>的部分代码。</p> \n" +
            "<pre class=\"brush: java\">public class Whitelist {\n" +
            "    private Set&lt;TagName&gt; tagNames; // tags allowed, lower case. e.g. [p, br, span]\n" +
            "    private Map&lt;TagName, Set&lt;AttributeKey&gt;&gt; attributes; // tag -&gt; attribute[]. allowed attributes [href] for a tag.\n" +
            "    private Map&lt;TagName, Map&lt;AttributeKey, AttributeValue&gt;&gt; enforcedAttributes; // always set these attribute values\n" +
            "    private Map&lt;TagName, Map&lt;AttributeKey, Set&lt;Protocol&gt;&gt;&gt; protocols; // allowed URL protocols for attributes\n" +
            "    private boolean preserveRelativeLinks; // option to preserve relative links\n" +
            "}</pre> \n" +
            "<p>这里定义了标签名/属性名/属性值的白名单。</p> \n" +
            "<p>而<code>Cleaner</code>是过滤的执行者。不出所料，Cleaner内部定义了<code>CleaningVisitor</code>来进行标签的过滤。CleaningVisitor的过滤过程并不改变原始DOM树的值，而是将符合条件的属性，加入到<code>Element destination</code>里去。</p> \n" +
            "<pre class=\"brush: java\">private final class CleaningVisitor implements NodeVisitor {\n" +
            "    private int numDiscarded = 0;\n" +
            "    private final Element root;\n" +
            "    private Element destination; // current element to append nodes to\n" +
            "\n" +
            "    private CleaningVisitor(Element root, Element destination) {\n" +
            "        this.root = root;\n" +
            "        this.destination = destination;\n" +
            "    }\n" +
            "\n" +
            "    public void head(Node source, int depth) {\n" +
            "        if (source instanceof Element) {\n" +
            "            Element sourceEl = (Element) source;\n" +
            "\n" +
            "            if (whitelist.isSafeTag(sourceEl.tagName())) { // safe, clone and copy safe attrs\n" +
            "                ElementMeta meta = createSafeElement(sourceEl);\n" +
            "                Element destChild = meta.el;\n" +
            "                destination.appendChild(destChild);\n" +
            "\n" +
            "                numDiscarded += meta.numAttribsDiscarded;\n" +
            "                destination = destChild;\n" +
            "            } else if (source != root) { // not a safe tag, so don't add. don't count root against discarded.\n" +
            "                numDiscarded++;\n" +
            "            }\n" +
            "        } else if (source instanceof TextNode) {\n" +
            "            TextNode sourceText = (TextNode) source;\n" +
            "            TextNode destText = new TextNode(sourceText.getWholeText(), source.baseUri());\n" +
            "            destination.appendChild(destText);\n" +
            "        } else { // else, we don't care about comments, xml proc instructions, etc\n" +
            "            numDiscarded++;\n" +
            "        }\n" +
            "    }\n" +
            "\n" +
            "    public void tail(Node source, int depth) {\n" +
            "        if (source instanceof Element &amp;&amp; whitelist.isSafeTag(source.nodeName())) {\n" +
            "            destination = destination.parent(); // would have descended, so pop destination stack\n" +
            "        }\n" +
            "    }\n" +
            "}</pre> \n" +
            "<span id=\"OSC_h2_3\"></span>\n" +
            "<h2>结束语</h2> \n" +
            "<p>至此，Jsoup的全部模块都已经写完了。Jsoup源码并不多，只有14000多行，但是实现非常精巧，在读代码的过程中，除了相关知识，还验证几个很重要的思想：</p> \n" +
            "<ul> \n" +
            " <li><p>最好的代码抽象，是对现实概念的映射。</p> <p>这句话在看《代码大全》的时候印象很深刻。在Jsoup里，只要有相关知识，每个类的作用都能第一时间明白其作用。</p></li> \n" +
            " <li><p>不要过度抽象</p> <p>在Jsoup里，只用到了两个接口，一个是<code>NodeVisitor</code>，一个是<code>Connection</code>，其他都是用抽象类或者直接用实现类代替。记得有次面试的时候被问到我们开发中每逢一个功能，都要先定义一个接口的做法是否必要？现在的答案是没有必要，过度的抽象反而会降低代码质量。</p> <p>另外，Jsoup的代码内聚性都很高，每个类的功能基本都定义在类的内部，这是一个典型的充血模型。同时有大量的facade使用，而避免了Factory、Configure等类的出现，个人感觉这点是非常好的。</p></li> \n" +
            "</ul> \n" +
            "<p>最后继续贴上Jsoup解读系列的github地址：<a href=\"https://github.com/code4craft/jsoup-learning/\" rel=\"nofollow\">https://github.com/code4craft/jsoup-learning/</a></p></div>\n" +
            "      \t  \t  \n" +
            "      \t\n" +
            "\t        <div class='BlogTags'>\n" +
            "    \t<strong>关键字：</strong>\n" +
            "    \t    \t<a href=\"http://www.oschina.net/search?scope=blog&q=Jsoup\" class=\"tag\">Jsoup</a>\n" +
            "    \t    \t<a href=\"http://www.oschina.net/search?scope=blog&q=XSS\" class=\"tag\">XSS</a>\n" +
            "    \t    \t<a href=\"http://www.oschina.net/search?scope=blog&q=OO\" class=\"tag\">OO</a>\n" +
            "    \t    \t      </div>\n" +
            "\t  \t  \n" +
            "      <div class='BlogCopyright'>\t\t\n" +
            "\t  \t\t声明：OSCHINA 博客文章版权属于作者，受法律保护。未经作者同意不得转载。\n" +
            "\t  \t  </div>\n" +
            "\n" +
            "      <div class='BlogLinks'>\n" +
            "    \t<ul>\n" +
            "                <li class='prev'><a href=\"http://my.oschina.net/flashsword/blog/158171\" title=\"上一篇：Jsoup代码解读之七-实现一个CSS Selector\">&laquo; Jsoup代码解读之七-实现一个CSS Selector</a></li>            \t</ul>\n" +
            "\t\t      </div>\n" +
            "\t</div>\n" +
            "\n" +
            "\t<style type='text/css'>\n" +
            "\t#BlogShare strong{float:left;padding-top:10px;font-size:11pt;color:#444;}\n" +
            "\t#BlogShare a.share_sina{float:left;width:32px;height:32px;background:url('/img/icon01.gif') center no-repeat;}\n" +
            "\t#BlogShare a.share_qq{float:left;width:32px;height:32px;margin-left: 10px;background:url('/img/icon02.gif') center no-repeat;}\n" +
            "\t</style>\n" +
            "\t<div class='BlogShare'>\n" +
            "\t\n" +
            "\t<span id='BlogShare'>\n" +
            "\t\t<strong>分享到： </strong>\n" +
            "\t\t<a class=\"share_sina\" title=\"分享到新浪微博\" href=\"javascript:void((function(s,d,e,r,l,p,t,z,c){var%20f='http://v.t.sina.com.cn/share/share.php?appkey=858381728',u=z||d.location,p=['&url=',e(u),'&title=',e(t||d.title),'&source=',e(r),'&sourceUrl=',e(l),'&content=',c||'gb2312','&pic=',e(p||'')].join('');function%20a(){if(!window.open([f,p].join(''),'mb',['toolbar=0,status=0,resizable=1,width=440,height=430,left=',(s.width-440)/2,',top=',(s.height-430)/2].join('')))u.href=[f,p].join('');};if(/Firefox/.test(navigator.userAgent))setTimeout(a,0);else%20a();})(screen,document,encodeURIComponent,'','','','Jsoup代码解读之八-防御XSS攻击: 防御XSS攻击的一般原理 cleaner是Jsoup的重要功能之一，我们常用它来进行富文本输入中的XSS防御。 我们知道，XSS攻击的一般方式是，通过在页面输入中嵌入一段恶意脚本，...','','utf-8'));\"></a>\n" +
            "\t\t<a class=\"share_qq\" title=\"分享到腾讯微博\" href=\"javascript:(function(){window.open('http://v.t.qq.com/share/share.php?url='+encodeURIComponent(document.location)+'&amp;appkey=96f54f97c4de46e393c4835a266207f4&amp;site=&amp;title='+encodeURIComponent(document.title)+encodeURIComponent(': 防御XSS攻击的一般原理 cleaner是Jsoup的重要功能之一，我们常用它来进行富文本输入中的XSS防御。 我们知道，XSS攻击的一般方式是，通过在页面输入中嵌入一段恶意脚本，...'),'', 'width=450, height=400, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, location=yes, resizable=no, status=no');}())\"></a></span>\n" +
            "\t<span id='BlogVote'>\n" +
            "    <a href=\"javascript:vote(158200)\">顶</a><span>已有 <em id='vote_count'>0</em>人顶</span>\n" +
            "\t</span>\n" +
            "\t</div>\n" +
            "\t\t\n" +
            "</div>\n" +
            "<div class='SpaceList' style='margin-top:20px;'>\n" +
            "<div class='BlogComments'>\n" +
            "    <h2><a name=\"comments\"></a>共有 3 条网友评论</h2>\n" +
            "\t\t\t<ul id=\"BlogComments\">\n" +
            "\t\t\t\t\t\t<li id='cmt_158200_180_275599137'>\n" +
            "\t<table class='ostable'><tr>\n" +
            "\t<td class='portrait'>\n" +
            "\t\t<a href=\"http://my.oschina.net/rox\" target=\"_blank\"><img src=\"http://static.oschina.net/uploads/user/0/180_50.jpg?t=1367919013000\" align=\"absmiddle\" alt=\"静风流云\" title=\"静风流云\" class=\"SmallPortrait\" user=\"180\"/></a>\t\t\t\n" +
            "\t</td>\n" +
            "\t<td class='body'>\n" +
            "\t\t<div class='title'>\n" +
            "\t\t\t1楼：<a href=\"http://my.oschina.net/rox\" target=\"_blank\" name=\"rpl_275599137\">静风流云</a> 发表于 2013-09-01 08:34    \t\t\t\n" +
            "        \t        \t  <a href=\"javascript:delete_c(158200,180,275599137)\">删除</a>\n" +
            "\t\t\t\t\t\t\t\t\t  <a href=\"javascript:ReplyInline(158200,180,275599137)\">回复此评论</a>\n" +
            "\t\t\t\t\t</div>\n" +
            "\t\t<div class='post'\">貌似，OSC也是类似处理的。</div>\n" +
            "\t\t<div id='inline_reply_of_158200_180_275599137' class='inline_reply'></div>\n" +
            "    </td>\n" +
            "\t</tr></table>\n" +
            "</li>\t\t\t\t\t<li id='cmt_158200_190591_275599170'>\n" +
            "\t<table class='ostable'><tr>\n" +
            "\t<td class='portrait'>\n" +
            "\t\t<a href=\"http://my.oschina.net/flashsword\" target=\"_blank\"><img src=\"http://static.oschina.net/uploads/user/95/190591_50.jpg?t=1347254905000\" align=\"absmiddle\" alt=\"黄亿华\" title=\"黄亿华\" class=\"SmallPortrait\" user=\"190591\"/></a>\t\t\t\n" +
            "\t</td>\n" +
            "\t<td class='body'>\n" +
            "\t\t<div class='title'>\n" +
            "\t\t\t2楼：<a href=\"http://my.oschina.net/flashsword\" target=\"_blank\" name=\"rpl_275599170\">黄亿华</a> 发表于 2013-09-01 08:37    \t\t\t\n" +
            "        \t        \t  <a href=\"javascript:delete_c(158200,190591,275599170)\">删除</a>\n" +
            "\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t<div class='post'\"><div class=ref><h4>引用来自“静风流云”的评论</h4><p>貌似，OSC也是类似处理的。</p></div>OSC就是使用Jsoup做解析的，见这里：<a href='http://www.oschina.net/p/jsoup' rel='nofollow' target='_blank'>http://www.oschina.net/p/jsoup</a></div>\n" +
            "\t\t<div id='inline_reply_of_158200_190591_275599170' class='inline_reply'></div>\n" +
            "    </td>\n" +
            "\t</tr></table>\n" +
            "</li>\t\t\t\t\t<li id='cmt_158200_234880_275616963'>\n" +
            "\t<table class='ostable'><tr>\n" +
            "\t<td class='portrait'>\n" +
            "\t\t<a href=\"http://my.oschina.net/searchjack\" target=\"_blank\"><img src=\"http://static.oschina.net/uploads/user/117/234880_50.jpg?t=1362718646000\" align=\"absmiddle\" alt=\"searchjack\" title=\"searchjack\" class=\"SmallPortrait\" user=\"234880\"/></a>\t\t\t\n" +
            "\t</td>\n" +
            "\t<td class='body'>\n" +
            "\t\t<div class='title'>\n" +
            "\t\t\t3楼：<a href=\"http://my.oschina.net/searchjack\" target=\"_blank\" name=\"rpl_275616963\">searchjack</a> 发表于 2013-09-02 09:20    \t\t\t\n" +
            "        \t        \t  <a href=\"javascript:delete_c(158200,234880,275616963)\">删除</a>\n" +
            "\t\t\t\t\t\t\t\t\t  <a href=\"javascript:ReplyInline(158200,234880,275616963)\">回复此评论</a>\n" +
            "\t\t\t\t\t</div>\n" +
            "\t\t<div class='post'\">极好的工具，</div>\n" +
            "\t\t<div id='inline_reply_of_158200_234880_275616963' class='inline_reply'></div>\n" +
            "    </td>\n" +
            "\t</tr></table>\n" +
            "</li>\t\t\t\t</ul>\n" +
            "</div>\n" +
            "\t</div>\n" +
            "\n" +
            "<div id='inline_reply_editor' style='display:none;'>\n" +
            "<div class=\"BlogCommentForm\">\n" +
            "\t<form id=\"form_inline_comment\" action=\"/action/blog/add_comment?blog=158200\" method=\"POST\">\n" +
            "\t  <input type='hidden' id='inline_reply_id' name='reply_id' value=''/>          \n" +
            "      <textarea name=\"content\" style=\"width:550px;height:60px;\" onkeydown=\"if((event.metaKey || event.ctrlKey)&&event.keyCode==13){$('#form_inline_comment').submit();}\"></textarea><br>\n" +
            "\t  <input type=\"submit\" value=\"回复\" id=\"btn_comment\" class=\"SUBMIT\"/> \n" +
            "\t  <input type=\"button\" value=\"关闭\" class=\"SUBMIT\" id='btn_close_inline_reply'/> 文明上网，理性发言\n" +
            "    </form>\n" +
            "</div>\n" +
            "</div>\n" +
            "<div class='SpaceList' style='margin-top:20px;'>\n" +
            "  <a name=\"comments\" id=\"postform\"></a>\n" +
            "    <div class=\"BlogCommentForm\">\n" +
            "    <form id=\"form_comment\" action=\"/action/blog/add_comment?blog=158200\" method=\"POST\">          \n" +
            "      <textarea id='ta_post_content' name=\"content\" style=\"width:550px;height:100px;\" onkeydown=\"if((event.metaKey || event.ctrlKey)&&event.keyCode==13){$('#form_comment').submit();}\"></textarea><br>\n" +
            "\t  <input type=\"submit\" value=\"发表评论\" id=\"btn_comment\" class=\"SUBMIT\" /> \n" +
            "\t  <img id=\"submiting\" style=\"display:none\" src=\"/img/loading.gif\" align=\"absmiddle\"/>\n" +
            "\t  <span id='cmt_tip'>文明上网，理性发言</span>\n" +
            "    </form>\n" +
            "\t<a href=\"#\" class=\"more\">回到页首</a>&nbsp;|&nbsp;<a href=\"#comments\" class=\"more\">回到评论列表</a>\n" +
            "  </div>\n" +
            "  </div>\n" +
            "\t\n" +
            "<div id=\"RelativeBlogs\">\n" +
            "\t<strong><a id='btn_close'>关闭</a>相关文章阅读</strong>\n" +
            "\t<ul>\n" +
            "\t\t\t<li>\n" +
            "\t\t<span class='date'>2012/04/04</span>\n" +
            "\t\t<a href=\"http://my.oschina.net/soitravel/blog/52366\" title=\"oo原则\">oo原则</a>\n" +
            "\t</li>\n" +
            "\t\t\t\t<li>\n" +
            "\t\t<span class='date'>2012/09/03</span>\n" +
            "\t\t<a href=\"http://my.oschina.net/wangfree/blog/76273\" title=\"XSS跨站脚本攻击\">XSS跨站脚本攻击</a>\n" +
            "\t</li>\n" +
            "\t\t\t\t<li>\n" +
            "\t\t<span class='date'>2012/10/10</span>\n" +
            "\t\t<a href=\"http://my.oschina.net/samshuai/blog/82382\" title=\"《蟋蟀的xss淫荡教程之如何劫持OSC用户账号》\">《蟋蟀的xss淫荡教程之如何劫持OSC...</a>\n" +
            "\t</li>\n" +
            "\t\t\t\t<li>\n" +
            "\t\t<span class='date'>2013/06/08</span>\n" +
            "\t\t<a href=\"http://my.oschina.net/tdoly/blog/136632\" title=\"[Security]XSS一直是个棘手的问题\">[Security]XSS一直是个棘手的问题...</a>\n" +
            "\t</li>\n" +
            "\t\t\t\t<li>\n" +
            "\t\t<span class='date'>2013/01/05</span>\n" +
            "\t\t<a href=\"http://my.oschina.net/sharephper/blog/100107\" title=\"xss攻击\">xss攻击</a>\n" +
            "\t</li>\n" +
            "\t\t\t</ul>\n" +
            "</div>\n" +
            "<script type=\"text/javascript\" src=\"/action/visit/blog?id=158200\" defer=\"defer\"></script>\n" +
            "<script type=\"text/javascript\" src=\"/js/syntax-highlighter-2.1.382/scripts/brush.js\"></script>\n" +
            "<link type=\"text/css\" rel=\"stylesheet\" href=\"/js/syntax-highlighter-2.1.382/styles/shCore.css\"/>\n" +
            "<link type=\"text/css\" rel=\"stylesheet\" href=\"/js/syntax-highlighter-2.1.382/styles/shThemeDefault.css\"/>\n" +
            "<script type='text/javascript'><!--\n" +
            "$(document).ready(function(){\n" +
            "\tSyntaxHighlighter.config.clipboardSwf = '/js/syntax-highlighter-2.1.382/scripts/clipboard.swf';\n" +
            "\tSyntaxHighlighter.all();\n" +
            "});\n" +
            "//-->\n" +
            "</script>\n" +
            "<!--[if lt IE 7]>\n" +
            "<script type=\"text/javascript\" src=\"/js/minmax.js\"></script>\n" +
            "<![endif]-->\n" +
            "<script type='text/javascript'>\n" +
            "<!--\n" +
            "var posting = false;\n" +
            "var upprev_closed = false;\n" +
            "var upprev_hidden = true;\n" +
            "\n" +
            "$(document).ready(function(){\n" +
            "    $('.BlogContent img').css('cursor','pointer');\n" +
            "    jQuery.each($('.BlogContent img'),function(idx,v){\n" +
            "    \t$(v).wrap(\"<a href='\"+$(this).attr('src')+\"' target='_blank'></a>\");\n" +
            "    });\n" +
            "\t$('#form_comment').ajaxForm({\n" +
            "\t\tdataType: 'json',\n" +
            "\t\tbforeSubmit: function(){\n" +
            "\t\t\tposting = true;\n" +
            "\t\t},\n" +
            "\t\tsuccess: function(json) {\n" +
            "        \tif(json.msg){\n" +
            "\t\t\t\t///alert(json.msg);\n" +
            "\t\t\t\t$('#cmt_tip').html(\"<span style='color:#C00;'>\"+json.msg+\"</span>\");\n" +
            "\t\t\t\t$('#ta_post_content').focus();\t\t\t\t\n" +
            "\t\t\t}else{\n" +
            "\t\t\t\tvar url = \"http://my.oschina.net/flashsword/blog_post?_cmt_blog=\"+json.blog+\"&_cmt_user=\"+json.user+\"&_cmt_id=\"+json.id;\t\t\t\t\n" +
            "        \t\tjQuery.get(url, function(data){\n" +
            "    \t\t\t\t$('.BlogComments .NoData').hide();\n" +
            "        \t\t\t$('ul#BlogComments').append(data);\n" +
            "        \t\t\t$('#form_comment').resetForm();\n" +
            "        \t\t}); \n" +
            "\t\t\t}\n" +
            "\t\t}\n" +
            "\t});\n" +
            "\n" +
            "    var at_datas = [];\n" +
            "    $('img.SmallPortrait').each(function(){\n" +
            "        var name = $(this).attr('alt');\n" +
            "        if(jQuery.inArray(name, at_datas) < 0 && name != '黄亿华')\n" +
            "            at_datas.push(name);\n" +
            "    });\n" +
            "    $(\"#form_comment textarea\").atWho(\"@\", {data: at_datas});\n" +
            "\n" +
            "\t$(\"#submiting\").ajaxStart(function(){\n" +
            "\t   if(posting){\n" +
            "    \t   $('#btn_submit').attr(\"disabled\",\"disabled\");\n" +
            "           $(this).show();\n" +
            "\t   }\n" +
            "    });\n" +
            "\t$(\"#submiting\").ajaxComplete(function(event,request, settings){\n" +
            "\t   if(posting){\n" +
            "           $(this).hide();\n" +
            "    \t   $('#btn_submit').attr(\"disabled\",\"\");\n" +
            "\t   }\n" +
            "\t   posting = false;\n" +
            "    }); \n" +
            "\t\n" +
            "    $(window).scroll(function() {\n" +
            "        var lastScreen;\n" +
            "        if ($(\"#postform\").length > 0)\n" +
            "            lastScreen = getScrollY() + $(window).height() < $(\"#postform\").offset().top * 1 ? false : true;\n" +
            "        else\n" +
            "            lastScreen = getScrollY() + $(window).height() < $(document).height() * 1 ? false : true;\n" +
            "        if (lastScreen && !upprev_closed) {\n" +
            "            $(\"#RelativeBlogs\").stop().animate({right:\"0px\"});\n" +
            "            upprev_hidden = false;\n" +
            "        }\n" +
            "        else if (upprev_closed && getScrollY() == 0) {\n" +
            "            upprev_closed = false;\n" +
            "        }\n" +
            "        else if (!upprev_hidden) {\n" +
            "            upprev_hidden = true;\n" +
            "            $(\"#RelativeBlogs\").stop().animate({right:\"-400px\"});\n" +
            "        }\n" +
            "    });\n" +
            "    $(\"#RelativeBlogs #btn_close\").click(function() {\n" +
            "        $(\"#RelativeBlogs\").stop().animate({right:\"-400px\"});\n" +
            "        upprev_closed = true;\n" +
            "        upprev_hidden = true;\n" +
            "    });\n" +
            "});\n" +
            "function delete_c(nid,uid,cid){\n" +
            "  if(confirm(\"您确认要删除此篇评论？\")){\n" +
            "    var args = \"cmt=\"+cid+\"#\"+uid+\"#\"+nid;\n" +
            "    ajax_post(\"/action/blog/delete_blog_comments?space=190591\",args,function(){$(\"#cmt_\"+nid+\"_\"+uid+\"_\"+cid).fadeOut();});\n" +
            "  }\n" +
            "}\n" +
            "function ReplyInline(blog,user,reply){\n" +
            "\t$('.inline_reply').empty();\n" +
            "\tvar div_id = '#inline_reply_of_'+blog+'_'+user+'_'+reply;\n" +
            "\t$('#inline_reply_id').val(user+'_'+reply);\n" +
            "\t$(div_id).html($('#inline_reply_editor').html());\n" +
            "\t$('#txt_focus').focus();\n" +
            "\t$('#btn_close_inline_reply').click(function(){\n" +
            "\t\t$(div_id).empty();\n" +
            "\t});\n" +
            "\t$('#form_inline_comment').ajaxForm({\n" +
            "\t\tdataType: 'json',\n" +
            "    \tsuccess: function(json) {\n" +
            "        \tif(json.msg){\n" +
            "        \t\talert(json.msg);\n" +
            "        \t}\n" +
            "        \telse if(json.id){\n" +
            "    \t\t\tlocation.reload();\n" +
            "        \t}\n" +
            "    \t}\n" +
            "\t});\n" +
            "}\n" +
            "function edit_catalogs(qid){\n" +
            "\tpopup(\"/set-catalogs?parent=1&type=3&id=\"+qid);\n" +
            "}\n" +
            "function vote(blogid){\n" +
            "\t\tajax_post(\"/action/blog/vote\",\"id=\"+blogid+\"&user=190591\",function(result){\n" +
            "\t\tvar json = eval('('+result+')');\n" +
            "\t\tif(json.vote)\n" +
            "\t\t\t$('#vote_count').html(json.vote);\n" +
            "\t\telse if(json.error == 1)\n" +
            "\t\t\talert(json.msg);\n" +
            "\t\telse\n" +
            "\t\t\talert(json.msg);\n" +
            "\t});\n" +
            "\t}\n" +
            "function toggle_recomm(blogid){\n" +
            "\tajax_post(\"/action/blog/toggle_recomm\",\"id=\"+blogid,function(html){\n" +
            "\t\tif(html == '-1')\n" +
            "\t\t\talert(\"文章不存在\");\n" +
            "\t\telse if(html == 0){\n" +
            "\t\t\t$('#lnk_recomm_'+blogid).removeClass('recommend');\n" +
            "\t\t\t$('#lnk_recomm_'+blogid).text(\"未推荐\");\n" +
            "\t\t}\n" +
            "\t\telse if(html == 1){\n" +
            "\t\t\t$('#lnk_recomm_'+blogid).addClass('recommend');\n" +
            "\t\t\t$('#lnk_recomm_'+blogid).text(\"已推荐\");\n" +
            "\t\t}\n" +
            "\t});\n" +
            "}\n" +
            "//-->\n" +
            "</script></div>\n" +
            "\t<div class='clear'></div>\n" +
            "\t<div id=\"OSC_Footer\"><style>\n" +
            ".oscapp {text-align:left; width:220px;}\n" +
            ".oscapp span {float:left;width:140px;}\n" +
            ".oscapp a {float:left;text-indent:-9999em;width:16px;margin-left:8px;}\n" +
            ".oscapp a.android {background:url('/img/android.gif') no-repeat left center;}\n" +
            ".oscapp a.iphone {background:url('/img/iphone.gif') no-repeat left center;}\n" +
            ".oscapp a.wp7 {background:url('/img/wp7.gif') no-repeat left center;}\n" +
            "</style>\n" +
            "<table width='100%'><tr>\n" +
            "<td align='left'>&copy; 开源中国(OsChina.NET) | <a href=\"http://www.oschina.net/home/about\">关于我们</a> | <a href=\"mailto:oschina.net@gmail.com\">广告联系</a> | <a href=\"http://weibo.com/oschina2010\" target=\"_blank\">@新浪微博</a> | <a href=\"http://m.oschina.net/\">开源中国手机版</a> | <a href='http://www.miitbeian.gov.cn/' target='_blank' style='color:#737573;text-decoration:none;'>粤ICP备12009483号-3</a></td>\n" +
            "<td class='oscapp'>\n" +
            "\t<span>开源中国手机客户端：</span>\n" +
            "\t<a href=\"http://www.oschina.net/app\" class='android' title='Android客户端'>Android</a>\n" +
            "\t<a href=\"http://www.oschina.net/app\" class='iphone' title='iPhone 客户端'>iPhone</a>\n" +
            "\t<a href=\"http://www.oschina.net/app\" class='wp7' title='Windows Phone 客户端'>WP7</a>\n" +
            "</td>\n" +
            "</tr>\n" +
            "</table>\n" +
            "<script type='text/javascript'>\n" +
            "<!--\n" +
            "if (top.location != self.location)top.location=self.location;\n" +
            "//-->\n" +
            "</script></div>\n" +
            "</div>\n" +
            "</body>\n" +
            "\n" +
            "<script type=\"text/javascript\" src=\"/action/visit/space?id=190591\"></script>\n" +
            "<script type='text/javascript'>\n" +
            "<!--\n" +
            "$(document).ready(function() {\n" +
            "\n" +
            "\tSelectStyle(\"#search-item\",\".search_list\");\n" +
            "\t$('.Tweet .photo img').live(\"click\",function(){\n" +
            "\t\tvar T=$(this);\n" +
            "\t\tvar t=this;\n" +
            "\t\tvar bigImg = T.attr('bi');\n" +
            "\t\tvar smallImg = T.attr('si');\n" +
            "\t\tvar src = T.attr('src');\n" +
            "\t\tvar newsrc = (bigImg == src)?smallImg:bigImg;\n" +
            "\t\tvar imgId = T.attr('id');\n" +
            "\t\tif(newsrc == bigImg){\n" +
            "    \t\tvar loading=$('<img alt=\"loading\" src=\"/img/loading.gif\"/>');\n" +
            "\t\t\tvar top = T.position().top+T.height()/2-8;\n" +
            "\t\t\tvar left = T.position().left+T.width()/2-8;\n" +
            "\t\t\tloading.css({\n" +
            "\t\t\t\t'position':'absolute',\n" +
            "\t\t\t\t'z-index':999,\n" +
            "\t\t\t\t'top':top,\n" +
            "\t\t\t\t'left':left\n" +
            "\t\t\t});\n" +
            "    \t\tT.before(loading);\n" +
            "\t\t\tvar tImg=new Image();\n" +
            "\t\t\ttImg.src=newsrc;\n" +
            "\t\t\ttImg.onload=function(){afterImgLoad(T,loading,imgId,newsrc,bigImg);};\n" +
            "\t\t}\n" +
            "\t\telse{\n" +
            "\t\t\tT.attr(\"src\",newsrc);\n" +
            "\t\t\t$('#img_menu_'+imgId).remove();\n" +
            "\t\t}\n" +
            "\t\treturn false;\n" +
            "\t});\n" +
            "\t\n" +
            "\t$(\".tweet_thumb_wrapper\").mouseenter(function(){\n" +
            "\t\t$(this).find(\".tweet_play_video\").css(\"opacity\",1);\n" +
            "\t}).mouseleave(function(){\n" +
            "\t\t$(this).find(\".tweet_play_video\").css(\"opacity\",0.7);\n" +
            "\t});\n" +
            "\n" +
            "    $(\"#TForm textarea\").atWho(\"@\", function(query, callback){\n" +
            "        jQuery.ajax({\n" +
            "            type:'POST',\n" +
            "            url:\"/action/tweet/at_suggest\",\n" +
            "            data:{'q':query},\n" +
            "            dataType:'json',\n" +
            "            success:function(json){\n" +
            "                callback(json);\n" +
            "            }\n" +
            "        });\n" +
            "    });\n" +
            "\t\n" +
            "\ttoggle_tweet_video = function(id){\n" +
            "\t\t$(\"#tweet_video_thumb_\"+id).toggle();\n" +
            "\t\tvar video = $(\"#tweet_video_\"+id).toggle();\n" +
            "\t\tvideo.siblings(\".tweet_video_operation,.tweet_thumb_wrapper\").toggle();\n" +
            "\t};\n" +
            "\t\n" +
            "\tfunction afterImgLoad(T,loading,imgId,url,bigImg){\n" +
            "\t\tvar lnks = \"<div id='img_menu_\"+imgId+\"' class='ImgMenu'>\";\n" +
            "\t\tlnks += \"<a href='#' onclick='$(\\\"#\"+imgId+\"\\\").click();return false;'>收起</a>\";\n" +
            "\t\tlnks += \"<a href='\"+bigImg+\"' target='_blank'>查看原图</a></div>\";\t\t\t\n" +
            "\t\tloading.remove();\n" +
            "\t\tT.attr(\"src\",url);\n" +
            "\t\tT.before(lnks);\n" +
            "\t}\n" +
            "});\n" +
            "\n" +
            "function set_fellow_memo(fid,fname){\n" +
            "\tpopup(\"/action/ajax/set_fellow_memo\",\"friend=\"+fid+\"&name=\"+fname);\n" +
            "}\n" +
            "\n" +
            "function deleteMsgs(uid, fid, fname){\n" +
            "\tif(!confirm(\"你确认要清除与‘\"+fname+\"’的所有留言信息吗？\"))\n" +
            "\t\treturn ;\n" +
            "\tvar args = \"user=\"+uid+\"&friend=\"+fid;\n" +
            "\tajax_post(\"/action/msg/delete_user\",args,function(html){\n" +
            "\t\tif(html.length > 0)\n" +
            "\t\t\talert(html);\n" +
            "\t\telse{\n" +
            "\t\t\t$('#Msg_'+fid).fadeOut();\n" +
            "\t\t}\n" +
            "\t});\n" +
            "}\n" +
            "\n" +
            "function follow_user(uid, uname){\n" +
            "\tjust_follow(uid, uname,'190591'); //oschina.js\n" +
            "}\n" +
            "\n" +
            "function unfollow_user(uid, uname){\n" +
            "\tif(confirm(\"确定不再关注\" + uname + \"了吗？\"))\n" +
            "\tjust_unfollow(uid,'190591',function(){\n" +
            "\t\talert('已取消对 ' + uname + ' 的关注');\n" +
            "\t});\n" +
            "}\n" +
            "\n" +
            "function tweet_reply(logid){\n" +
            "\tvar r = $('#LogReply_'+logid);\n" +
            "\tif(!r.is(\":hidden\")){\n" +
            "\t\tclose_tweet_reply(logid);\n" +
            "\t\treturn ;\n" +
            "\t}\n" +
            "\tr.html(\"<div class='TweetRplsWrapper'><span class='loading'>正在加载评论，请稍候...</span></div>\")\n" +
            "\tr.show();\n" +
            "\tr.load(\"http://my.oschina.net/flashsword/tweet-rpls?log=\"+logid,function(){\n" +
            "\t\t$('#edt_tweet_post_'+logid).focus();\n" +
            "        var at_datas = [];\n" +
            "        $(this).find(\"img.SmallPortrait\").each(function(){\n" +
            "            var name = $(this).attr('alt');\n" +
            "            if(jQuery.inArray(name, at_datas) < 0 && name != '黄亿华')\n" +
            "                at_datas.push(name);\n" +
            "        });\n" +
            "        $(this).find(\"input.TXT_TweetRpl_Text\").atWho(\"@\", {data: at_datas});\n" +
            "        $('#TweetReplyForm_'+logid).ajaxForm({\n" +
            "        \tdataType: 'json',\n" +
            "\t\t\tbeforeSubmit: function(arr, form, options){\n" +
            "\t\t\t\t$('#BTN_TweetReply_'+logid).attr('disabled','disabled');\n" +
            "\t\t\t},\n" +
            "            success: function(json) {\n" +
            "            \tif(json.msg){\n" +
            "        \t\t\talert(json.msg);\n" +
            "            \t}else if(json.log){\n" +
            "\t\t\t\t\t$('#log_reply_count_'+logid).text(json.reply_count);\n" +
            "        \t\t\t//插入新评论\t\t\t\t\t\n" +
            "\t\t\t\t\tajax_get(\"/action/ajax/get_tweet_reply?id=\" + json.log,true,function(html){\n" +
            "\t\t\t\t\t\t$('#LogReply_'+logid+' ul').prepend(html);\n" +
            "\t\t\t\t\t});\n" +
            "\t\t\t\t\t$('#edt_tweet_post_'+logid).val('');\n" +
            "            \t}\n" +
            "\t\t\t\t$('#BTN_TweetReply_'+logid).removeAttr('disabled');\n" +
            "            }\n" +
            "        });\n" +
            "\t});\n" +
            "}\n" +
            "function close_tweet_reply(logid){\n" +
            "\t$('#LogReply_'+logid).empty();\n" +
            "\t$('#LogReply_'+logid).hide();\n" +
            "\t$('#Logs .userlogs li').removeClass('hover');\n" +
            "}\n" +
            "function reply_rtweet(logid, rid, toname){\n" +
            "\tvar edtPost = $('#edt_tweet_post_' + logid);\n" +
            "\tvar old_v = edtPost.val();\n" +
            "\tif(old_v.length > 0)\n" +
            "\t\tedtPost.val(old_v + ',@'+toname+' ');\n" +
            "\telse\n" +
            "\t\tedtPost.val('回复 @'+toname+' : ');\n" +
            "\tedtPost.focus();\n" +
            "\tedtPost.caretPos(edtPost.val().length); }\n" +
            "function delete_tweet(logid){\n" +
            "\tif(confirm(\"确认要删除这条信息吗？\"))\n" +
            "\tajax_post(\"/action/tweet/delete?log=\"+logid+\"&user=190591\",\"\",function(html){\n" +
            "\t\tif(html.length==0){\n" +
            "\t\t\tvar elem = $('#LI_'+logid);\n" +
            "\t\t\tif(elem.length > 0)\n" +
            "\t\t\t\t$('#LI_'+logid).fadeOut();\n" +
            "\t\t\telse\n" +
            "\t\t\t\tlocation.reload();\n" +
            "\t\t}\n" +
            "\t});\n" +
            "}\n" +
            "function delete_tweet_reply(logid){\n" +
            "\tif(confirm(\"确认要删除这条评论吗？\"))\n" +
            "\tajax_post(\"/action/tweet/delete_reply?id=\"+logid+\"&user=190591\",\"\",function(html){\n" +
            "\t\tif(html.length==0)\n" +
            "\t\t\t$('#TweetReply_'+logid).fadeOut();\n" +
            "\t});\n" +
            "}\n" +
            "\n" +
            "function delete_blog(blog_id){\n" +
            "    if(!confirm(\"文章删除后无法恢复，请确认是否删除此篇文章？\")) return;\n" +
            "    ajax_post(\"/action/blog/delete?id=\"+blog_id+\"&user=190591&user_code=tzm9Wg2YoU8SkJaTIjHQkahStiXQNyymUGXFOQgN\",\"\",function(html){\n" +
            "    \tlocation.href=\"http://my.oschina.net/flashsword/blog\";\n" +
            "    });\n" +
            "}\n" +
            "\n" +
            "function SelectStyle(on,option){\n" +
            "\tvar currentSort = $(on).attr('id');\n" +
            "\tvar currentText = $(option+\" li.\"+currentSort+\" a\").html();\n" +
            "\t$(on + \" .text\").html(currentText);\n" +
            "\t$(on + \" .text\").hover(function(){\n" +
            "\t\t$(this).addClass(\"hover\")\n" +
            "\t},function(){\n" +
            "\t\t$(this).removeClass(\"hover\")\n" +
            "\t});\n" +
            "\t$(option+\" li a\").each(function(index){\n" +
            "\t\t$(this).click(function(){\n" +
            "\t\t\tthishtml = $(this).html();\n" +
            "\t\t\t$(on + \" .text\").removeClass(\"on\").html(thishtml);\t\t\n" +
            "\t\t\t$(\".selectbox select \").find(\"option\").removeAttr('selected').eq(index).attr(\"selected\",\"selected\");\t\n" +
            "\t\t\t$(option).hide()\n" +
            "\t\t\treturn false;\n" +
            "\t\t});\n" +
            "\t\t\n" +
            "\t});\t\t\n" +
            "\t\n" +
            "\t$(\".selectbox\").click(function(){\t\t\n" +
            "\t\t$(option).toggle();\n" +
            "\t\t$(on + \" .text\").toggleClass(\"on\");\t\t\n" +
            "\t\treturn false;\n" +
            "\t});\n" +
            "\t$(document).click(function(){\n" +
            "\t\t$(option).hide();\t\n" +
            "\t\t$(on + \" .text\").removeClass(\"on\");\n" +
            "\t});\n" +
            "\t$(document).trigger('click');\n" +
            "\n" +
            "}\n" +
            "\n" +
            "//-->\n" +
            "</script>\n" +
            "</html>\n" +
            "\n" +
            "<!-- Generated by OsChina.NET (init:0[ms],page:83[ms],ip:58.241.37.50) -->";
}
