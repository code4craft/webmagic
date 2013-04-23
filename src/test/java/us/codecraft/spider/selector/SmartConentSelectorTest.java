package us.codecraft.spider.selector;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

/**
 * User: cairne
 * Date: 13-4-21
 * Time: 上午10:35
 */
public class SmartConentSelectorTest {

    @Test
    public void test() throws IOException {
        String text ="\n" +
                "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\"\n" +
                "    \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\" id=\"sixapart-standard\">\n" +
                "<head>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                "<meta name=\"generator\" content=\"Movable Type  5.2.2\" />\n" +
                "<link rel=\"stylesheet\" href=\"http://www.ruanyifeng.com/blog/styles.css\" type=\"text/css\" />\n" +
                "<link rel=\"start\" href=\"http://www.ruanyifeng.com/blog/\" title=\"Home\" />\n" +
                "<link rel=\"alternate\" type=\"application/atom+xml\" title=\"Recent Entries\" href=\"http://feeds.feedburner.com/ruanyifeng\" />\n" +
                "<script type=\"text/javascript\" src=\"http://www.ruanyifeng.com/blog/mt.js\"></script>\n" +
                "<!--\n" +
                "<rdf:RDF xmlns=\"http://web.resource.org/cc/\"\n" +
                "         xmlns:dc=\"http://purl.org/dc/elements/1.1/\"\n" +
                "         xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\">\n" +
                "<Work rdf:about=\"http://www.ruanyifeng.com/blog/\">\n" +
                "<dc:title>阮一峰的网络日志</dc:title>\n" +
                "<dc:description>Ruan YiFeng&apos;s Blog</dc:description>\n" +
                "<license rdf:resource=\"http://creativecommons.org/licenses/by-nc-nd/3.0/\" />\n" +
                "</Work>\n" +
                "<License rdf:about=\"http://creativecommons.org/licenses/by-nc-nd/3.0/\">\n" +
                "</License>\n" +
                "</rdf:RDF>\n" +
                "-->\n" +
                "\n" +
                "\n" +
                "    \n" +
                "    <link rel=\"prev bookmark\" href=\"http://www.ruanyifeng.com/blog/2010/04/kangbashi.html\" title=\"&quot;草原新城&quot;康巴什\" />\n" +
                "    <link rel=\"next bookmark\" href=\"http://www.ruanyifeng.com/blog/2010/04/musician_survival_manual_in_the_information_age.html\" title=\"网络时代的音乐家生存指南\" />\n" +
                "    \n" +
                "    <title>全文Feed的终极解决方案 - 阮一峰的网络日志</title>\n" +
                "</head>\n" +
                "<body id=\"scrapbook\" class=\"mt-entry-archive one-column\" onload=\"individualArchivesOnLoad(commenter_name)\">\n" +
                "    <div id=\"container\">\n" +
                "        <div id=\"container-inner\">\n" +
                "\n" +
                "\n" +
                "            <div id=\"header\">\n" +
                "    <div id=\"header-inner\">\n" +
                "        <div id=\"header-content\">\n" +
                "\n" +
                "\n" +
                "            <div id=\"header-name\">阮一峰的网络日志 <span id=\"site_location\"> » <a href=\"http://www.ruanyifeng.com/blog/\" accesskey=\"1\">首页</a></span><span id=\"site_archive\"> » <a href=\"http://www.ruanyifeng.com/blog/archives.html\">档案</a></span>\n" +
                "</div>\n" +
                "\n" +
                "<div id=\"google_search\">\n" +
                "<!-- SiteSearch Google -->\n" +
                "<form action=\"http://www.ruanyifeng.com/blog/search.html\" id=\"cse-search-box\">\n" +
                "      <div>\n" +
                "        <input type=\"hidden\" name=\"cx\" value=\"016304377626642577906:b_e9skaywzq\" />\n" +
                "    <input type=\"hidden\" name=\"cof\" value=\"FORID:11\" />\n" +
                "    <input type=\"hidden\" name=\"ie\" value=\"UTF-8\" />\n" +
                "        <input type=\"text\" name=\"q\" size=\"20\" class=\"searchbox\" id=\"sbi\" value=\"\"/>\n" +
                "        <input type=\"image\" src=\"/static/themes/theme_scrapbook/images/top_search_submit.gif\" class=\"searchbox_submit\" value=\"\" alt=\"搜索\" name=\"sa\"/>\n" +
                "      </div>\n" +
                "    </form>\n" +
                "<!-- SiteSearch Google -->\n" +
                "</div>\n" +
                "<div id=\"feed_icon\">\n" +
                "<a href=\"/feed.html\" title=\"订阅Feed\">\n" +
                "<img src=\"http://www.feedburner.com/fb/images/pub/feed-icon32x32.png\" alt=\"\" style=\"border: 0pt none;\" />\n" +
                "</a></div>\n" +
                "\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "\n" +
                "            <div id=\"content\">\n" +
                "                <div id=\"content-inner\">\n" +
                "\n" +
                "\n" +
                "                    <div id=\"alpha\">\n" +
                "                        <div id=\"alpha-inner\">\n" +
                "\n" +
                "\n" +
                "                            <div id=\"entry-1377\" class=\"entry-asset asset hentry\">\n" +
                "                                <div class=\"asset-header\">\n" +
                "<div class=\"asset-nav entry-nav\">\n" +
                "\n" +
                "<div class=\"entry-location\">\n" +
                "<ul>\n" +
                "<li>上一篇：<a href=\"http://www.ruanyifeng.com/blog/2010/04/kangbashi.html\" title=\"&quot;草原新城&quot;康巴什\">&quot;草原新城&</a></li>\n" +
                "<li>下一篇：<a href=\"http://www.ruanyifeng.com/blog/2010/04/musician_survival_manual_in_the_information_age.html\" title=\"网络时代的音乐家生存指南\">网络时代的音乐家生存指</a></li>\n" +
                "</ul>\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "\n" +
                "    \n" +
                "                                    <div class=\"entry-categories\">\n" +
                "                                        <p>分类<span class=\"delimiter\">：</span></p>\n" +
                "                                        <ul>\n" +
                "                                            <li><a href=\"http://www.ruanyifeng.com/blog/it/\" rel=\"tag\">IT技术</a></li>\n" +
                "                                        </ul>\n" +
                "                                    </div>\n" +
                "    \n" +
                "\n" +
                "\n" +
                "\n" +
                "                                            \n" +
                "</div>\n" +
                "                                </div>\n" +
                "<article class=\"hentry\">\n" +
                "                                    <h1 id=\"page-title\" class=\"asset-name entry-title\">全文Feed的终极解决方案</h1>\n" +
                "                                            <div id=\"share_button\" style=\"float:right;padding-right:2em;padding-top:1em;\"></div>\n" +
                "                                    <div class=\"asset-meta\">\n" +
                "                                        \n" +
                "\n" +
                "                                            <p class=\"vcard author\">作者： <a class=\"fn url\" href=\"http://www.ruanyifeng.com\">阮一峰</a></p>\n" +
                "\n" +
                "<p>日期： <a href=\"http://www.ruanyifeng.com/blog/2010/04/\"><abbr class=\"published\" title=\"2010-04-17T15:24:49+08:00\">2010年4月17日</abbr></a></p>\n" +
                "\n" +
                "\n" +
                "\n" +
                "                                    </div>\n" +
                "                                \n" +
                "                                <div class=\"asset-content entry-content\" id=\"main-content\">\n" +
                "\n" +
                "                                    <!-- div class=\"asset-body\" -->\n" +
                "                                        <p>正如我们都知道的，全文Feed最有用。</p>\n" +
                "                                    <!-- /div -->\n" +
                "\n" +
                "\n" +
                "                                    <!-- div id=\"more\" class=\"asset-more\" -->\n" +
                "                                        <p>但是，世界上的大部分Feed，都是摘要Feed，甚至是标题Feed。我们只好自己动手，制作全文Feed。</p>\n" +
                "\n" +
                "<p>传统的制作方法非常麻烦，需要针对不同的网站，编写不同的内容提取规则。要是有一个傻瓜型的\"全文Feed生成器\"，把摘要Feed往里面一扔，全文Feed就自动生成了，那该多好。</p>\n" +
                "\n" +
                "<p><a href=\"http://fivefilters.org/content-only/\">FiveFilters.org</a>提供的生成器，大概最接近于这种要求。</p>\n" +
                "\n" +
                "<p><a href=\"http://fivefilters.org/content-only/\"><img src=\"http://image.beekka.com/blog/201004/hp2010041701.png\" /></a></p>\n" +
                "\n" +
                "<p>举例来说，网易的社会新闻Feed（<a href=\"http://news.163.com/special/00011K6L/rss_sh.xml\">http://news.163.com/special/00011K6L/rss_sh.xml</a>）是一个摘要Feed。</p>\n" +
                "\n" +
                "<p><img src=\"http://image.beekka.com/blog/201004/hp2010041702.png\" /></p>\n" +
                "\n" +
                "<p>我们把这个网址，送进FiveFilters.org，点击\"Create Feed\"按钮，全文Feed就自动产生了！（<a href=\"http://fivefilters.org/content-only/makefulltextfeed.php?url=http%3A%2F%2Fnews.163.com%2Fspecial%2F00011K6L%2Frss_sh.xml&key=&max=4&submit=Create+Feed\">查看效果</a>）</p>\n" +
                "\n" +
                "<p>但是，这个生成器并不是百用百灵，比如新浪的Feed（<a href=\"http://rss.sina.com.cn/news/society/focus15.xml\">http://rss.sina.com.cn/news/society/focus15.xml</a>）就无法抓取全文。</p>\n" +
                "\n" +
                "<p>好在今年3月份，它开源了。作者<a href=\"http://www.keyvan.net\">Keyvan Minoukadeh</a>将所有代码都公开了，所以如果遇到不能生效的Feed，现在我们就可以修改源码了。因此理论上，几乎所有的摘要Feed都可以自动转成全文Feed了。</p>\n" +
                "\n" +
                "<p>源码存放在<a href=\"https://code.launchpad.net/~keyvan-k1m/fivefilters/content-only\">launchpad.net</a>上，需要安装<a href=\"http://bazaar.canonical.com/en/\">Bazaar</a>的客户端才能下载。我为大家提供方便，把它们压缩成一个zip文件，<a href=\"http://www.ruanyifeng.com/blog/2010/04/full-text-rss.zip\">点击下载</a>（1.0版，217KB）。</p>\n" +
                "\n" +
                "<p>下载后，上传到支持PHP 5.2的虚拟主机上，就可以直接使用。使用的时候，需要将cache子目录设为可写（权限777）。在config-sample.php文件中，可以查看设置选项，修改默认值后，将文件名改为config.php，就会生效。（不修改亦可，config文件并不是必需的。）</p>\n" +
                "\n" +
                "<p>这个程序的核心是readability.php文件，它负责判断当前网页中，那一部分属于页面的主要内容，然后将其抓取出来。实现原理照搬了arc90的<a href=\"http://lab.arc90.com/experiments/readability/\">ReadAbility脚本</a>。简单说，思路是这样的：1）检查页面中所有p元素的父容器；2）根据相关特征，为每一个父容器计算一个特征值；3）特征值最大的容器，就是放置主要内容的容器。</p>\n" +
                "\n" +
                "<p>具体实现请阅读代码，源码写得非常清晰，而且有详细的注释。如果遇到不能抓取全文的Feed，你就要自己修改readability.php，增加相应的规则。比如，在我提供下载的代码中，我就设置了新浪网的规则，新浪网的全文Feed就能自动生成了。</p>\n" +
                "\n" +
                "<p>这个程序使用的是<a href=\"http://en.wikipedia.org/wiki/Affero_General_Public_License\">AGPL许可证</a>，这就是说你可以自由地使用、修改、发布这个程序，但是只要你向他人提供基于这个程序的服务，你就必须公开源码。</p>\n" +
                "\n" +
                "<p>作者Keyvan Minoukadeh允诺，只要使用者向他捐款200美元，就发布2.0版。如果你喜欢这个程序，建议向他<a href=\"http://www.chipin.com/contribute/id/17c02430b8031b97\">捐款</a>。</p>\n" +
                "\n" +
                "<p>P.S.</p>\n" +
                "\n" +
                "<p>这几天，我还发现了一个非常优秀的开源相册软件<a href=\"http://www.zenphoto.org\">ZenPhoto</a>，也推荐使用。</p>\n" +
                "\n" +
                "<p><strong>UPDATE（2010.6.3）</strong></p>\n" +
                "\n" +
                "<p>Full TEXT RSS 1.5版<a href=\"http://www.ruanyifeng.com/blog/2010/04/full-text-rss_v1.5.zip\">下载</a>（283KB）</p>\n" +
                "\n" +
                "<p><strong>UPDATE（2010.11.10）</strong></p>\n" +
                "\n" +
                "<p>Full TEXT RSS 2.1版<a href=\"http://www.ruanyifeng.com/blog/2010/04/full-text-rss_v2.1.rar\">下载</a>（362KB）</p>\n" +
                "\n" +
                "<p>（完）</p>\n" +
                "                                    <!-- /div -->\n" +
                "\n" +
                "                                </div>\n" +
                "<script type=\"text/javascript\" src=\"/newwindow.js\"></script>\n" +
                "                                <div class=\"asset-footer\">\n" +
                "\n" +
                "<h3>文档信息</h3>\n" +
                "<ul>\n" +
                "<li>版权声明：自由转载-非商用-非衍生-保持署名 | <a href=\"http://creativecommons.org/licenses/by-nc-nd/3.0/deed.zh\">Creative Commons BY-NC-ND 3.0</a></li>\n" +
                "<li>原文网址：<a href=\"http://www.ruanyifeng.com/blog/2010/04/the_solution_of_full_text_feed.html\">http://www.ruanyifeng.com/blog/2010/04/the_solution_of_full_text_feed.html</a></li>\n" +
                "<li>最后修改时间：2012年6月11日 06:58</li>\n" +
                "<li>付费支持：<a href=\"https://me.alipay.com/ruanyf\" target=\"_blank\"><img src=\"http://www.ruanyifeng.com/blog/images/rmb_32.png\" title=\"人民币\" alt=\"人民币 - 支付宝\" style=\"border:none;vertical-align:middle;\" /></a> | <a href=\"https://www.paypal.com/cgi-bin/webscr?cmd=_xclick&business=yifeng.ruan@gmail.com&currency_code=USD&amount=0.99&return=http://www.ruanyifeng.com/thank.html&item_name=Ruan%20YiFeng's%20Blog&undefined_quantity=1&no_note=0\" target=\"_blank\"><img src=\"http://www.ruanyifeng.com/blog/images/dollar_32.png\"  alt=\"美元 - paypal\" title=\"美元\" style=\"border:none;vertical-align:middle;\" /></a> </li>\n" +
                "</ul>\n" +
                "                                </div>\n" +
                "</article>\n" +
                "                            </div>\n" +
                "   <div id=\"gegz1\">\n" +
                "   <p style=\"text-align:center;\"><a href=\"http://www.hi-vps.com\" target=\"_blank\"><img src=\"http://www.ruanyifeng.com/blog/images/ad_hivps.jpg\"  style=\"border:none;\"/></a></p>\n" +
                "</div>\n" +
                "<div id=\"related_entries\">\n" +
                "<h2>相关文章</h2>\n" +
                "<ul>\n" +
                "\n" +
                "<li><strong>2012.12.21: <a href=\"http://www.ruanyifeng.com/blog/2012/12/asynchronous＿javascript.html\">Javascript异步编程的4种方法</a></strong>\n" +
                "\n" +
                "                           <div class=\"entry-body\">\n" +
                "                              你可能知道，Javascript语言的执行环境是\"单线程\"（single thread）。\n" +
                "                           </div>\n" +
                "\n" +
                "</li>\n" +
                "\n" +
                " \n" +
                "<li><strong>2012.12.14: <a href=\"http://www.ruanyifeng.com/blog/2012/12/obama_fundraising_website.html\">奥巴马筹款网站的制作过程</a></strong>\n" +
                "\n" +
                "                           <div class=\"entry-body\">\n" +
                "                              1.\n" +
                "\n" +
                "Kyle Rush是一个网站工程师。\n" +
                "                           </div>\n" +
                "\n" +
                "</li>\n" +
                "\n" +
                " \n" +
                "</ul>\n" +
                "</div>\n" +
                "\n" +
                "<div id=\"entry_utility\">\n" +
                "<h2>功能链接</h2>\n" +
                "<ul>\n" +
                "<li><strong>前一篇：</strong><a href=\"http://www.ruanyifeng.com/blog/2010/04/kangbashi.html\">\"草原新城\"康巴什</a> </li>\n" +
                "<li><strong>后一篇：</strong><a href=\"http://www.ruanyifeng.com/blog/2010/04/musician_survival_manual_in_the_information_age.html\">网络时代的音乐家生存指南</a></li>\n" +
                "<li><strong>更多内容请访问：</strong><span class=\"homepage\"><a href=\"http://www.ruanyifeng.com/blog/\">首页</a></span>  » <a href=\"http://www.ruanyifeng.com/blog/archives.html\">档案</a>  » \n" +
                "<a href=\"http://www.ruanyifeng.com/blog/it/\">IT技术</a> \n" +
                "</li>\n" +
                "\n" +
                "<li>\n" +
                "<!-- SiteSearch Google -->\n" +
                "<form method=\"get\" action=\"/blog/search.html\" target=\"_top\" name=\"google_search\">\n" +
                "<input type=\"hidden\" name=\"domains\" value=\"www.ruanyifeng.com\"></input>\n" +
                "<strong>站内搜索：</strong><input type=\"text\" name=\"q\" style=\"width:250px;\"  maxlength=\"255\" value=\"网站\"></input>\n" +
                "<input type=\"submit\" name=\"sa\" value=\"GO！\"></input>\n" +
                "<span style=\"display:none;\"><input type=\"radio\" name=\"sitesearch\" value=\"\"></input>\n" +
                "<font size=\"-1\" color=\"#000000\">Web</font>\n" +
                "<input type=\"radio\" name=\"sitesearch\" value=\"www.ruanyifeng.com\" checked=\"checked\"></input>\n" +
                "<font size=\"-1\" color=\"#000000\">www.ruanyifeng.com</font>\n" +
                "</span>\n" +
                "\n" +
                "<input type=\"hidden\" name=\"cx\" value=\"016304377626642577906:b_e9skaywzq\" />\n" +
                "<input type=\"hidden\" name=\"cof\" value=\"FORID:11\" />\n" +
                "<input type=\"hidden\" name=\"ie\" value=\"UTF-8\" />\n" +
                "</form>\n" +
                "<!-- SiteSearch Google -->\n" +
                "</li>\n" +
                "<li><strong>Feed订阅：</strong>  <a href=\"http://feeds.feedburner.com/ruanyifeng\" target=\"_blank\"><img src=\"http://www.ruanyifeng.com/blog/images/rss.png\" height=\"16\" width=\"16\"/></a></li>\n" +
                "</ul>\n" +
                "</div>\n" +
                "\n" +
                "<div id=\"googlead1\">\n" +
                "<h2>广告<a href=\"/ads.html\">（购买广告位）</a></h2>\n" +
                "<div id=\"googlead1-inner\">\n" +
                "<a href=\"http://www.ushan.cn/?act=suit\" target=\"_blank\"><image src=\"http://www.ruanyifeng.com/blog/images/ad_ushan_600.jpg\" style=\"border:none;\" /></a>\n" +
                "\n" +
                "<div id=\"googlead1-left\">\n" +
                "</div>\n" +
                "<div id=\"googlead1-right\">\n" +
                "\n" +
                "\n" +
                "</div>\n" +
                "</div>\n" +
                "</div>\n" +
                "\n" +
                "                    \n" +
                "\n" +
                "                    <div id=\"comments\" class=\"comments\">\n" +
                "\n" +
                "\n" +
                "    \n" +
                "    \n" +
                "        \n" +
                "    <h2 class=\"comments-header\">留言（23条）</h2>\n" +
                "\n" +
                "    <div id=\"comments-content\" class=\"comments-content\" style=\"clear: left;\">\n" +
                "        \n" +
                "        <div id=\"comment-204889\" class=\"comment\">\n" +
                "    <div  class=\"inner\">\n" +
                "        <div class=\"comment-header\">\n" +
                "            <div class=\"asset-meta\">\n" +
                "<p>\n" +
                "                <span class=\"byline\">\n" +
                "                    \n" +
                "\n" +
                "                    <span class=\"vcard author\">zp</span>\n" +
                "\n" +
                " 说：\n" +
                "                </span>\n" +
                "</p>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"comment-content\" id=\"comment-quote-204889\">\n" +
                "            <p>能不能介绍些Movable Type的文章，我比较喜欢它的静态页面，国内关于它的资料好像还不多。特别是MT5出来后，多页面功能可能会让刚接触的人晕头转向。</p>\n" +
                "        </div>\n" +
                "<div class=\"comment-footer\">\n" +
                "<div class=\"comment-footer-inner\">\n" +
                "<p>\n" +
                "                   <a href=\"http://www.ruanyifeng.com/blog/2010/04/the_solution_of_full_text_feed.html#comment-204889\"><abbr class=\"published\" title=\"2010-04-17T16:27:05+08:00\">2010年4月17日 16:27</abbr></a>\n" +
                " | <a href=\"http://www.ruanyifeng.com/blog/user/zp.html\" title=\"zp的全部留言\">档案</a> \n" +
                " | <a href=\"#comment-text\" title=\"引用zp的这条留言\" onclick=\"return CommentQuote('comment-quote-204889','zp');\">引用</a>\n" +
                "</p>\n" +
                "</div>\n" +
                "</div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "        \n" +
                "    \n" +
                "        \n" +
                "        <div id=\"comment-204890\" class=\"comment\">\n" +
                "    <div  class=\"inner\">\n" +
                "        <div class=\"comment-header\">\n" +
                "            <div class=\"asset-meta\">\n" +
                "<p>\n" +
                "                <span class=\"byline\">\n" +
                "                    \n" +
                "\n" +
                "                    <span class=\"vcard author\"><a title=\"http://huodian.blogspot.com/\" href=\"http://huodian.blogspot.com/\" target=\"_blank\" rel=\"nofollow\">火点</a></span>\n" +
                "\n" +
                " 说：\n" +
                "                </span>\n" +
                "</p>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"comment-content\" id=\"comment-quote-204890\">\n" +
                "            <p>很好，谢谢作者，只是赶到花了大量的时间在新闻上似乎有点不利于思考。</p>\n" +
                "\n" +
                "<p>用一个图书管理软件（BLM）整理了大学期间看过的书，仅有180本左右，汗颜，这就是我的大学……</p>\n" +
                "\n" +
                "<p>现在参加工作了，好在业余时间还算充裕，希望可以多读一些书。</p>\n" +
                "        </div>\n" +
                "<div class=\"comment-footer\">\n" +
                "<div class=\"comment-footer-inner\">\n" +
                "<p>\n" +
                "                   <a href=\"http://www.ruanyifeng.com/blog/2010/04/the_solution_of_full_text_feed.html#comment-204890\"><abbr class=\"published\" title=\"2010-04-17T19:08:03+08:00\">2010年4月17日 19:08</abbr></a>\n" +
                " | <a href=\"http://www.ruanyifeng.com/blog/user/火点.html\" title=\"火点的全部留言\">档案</a> \n" +
                " | <a href=\"#comment-text\" title=\"引用火点的这条留言\" onclick=\"return CommentQuote('comment-quote-204890','火点');\">引用</a>\n" +
                "</p>\n" +
                "</div>\n" +
                "</div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "        \n" +
                "    \n" +
                "        \n" +
                "        <div id=\"comment-204891\" class=\"comment\">\n" +
                "    <div  class=\"inner\">\n" +
                "        <div class=\"comment-header\">\n" +
                "            <div class=\"asset-meta\">\n" +
                "<p>\n" +
                "                <span class=\"byline\">\n" +
                "                    \n" +
                "\n" +
                "                    <span class=\"vcard author\"><a title=\"http://www.eluanstone.cn\" href=\"http://www.eluanstone.cn\" target=\"_blank\" rel=\"nofollow\">AlbertDiao</a></span>\n" +
                "\n" +
                " 说：\n" +
                "                </span>\n" +
                "</p>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"comment-content\" id=\"comment-quote-204891\">\n" +
                "            <p>如果是手机RSS的话，摘要Feed比较好。一般浏览摘要，感兴趣的点进全文，这样比较节省流量。</p>\n" +
                "        </div>\n" +
                "<div class=\"comment-footer\">\n" +
                "<div class=\"comment-footer-inner\">\n" +
                "<p>\n" +
                "                   <a href=\"http://www.ruanyifeng.com/blog/2010/04/the_solution_of_full_text_feed.html#comment-204891\"><abbr class=\"published\" title=\"2010-04-17T19:26:22+08:00\">2010年4月17日 19:26</abbr></a>\n" +
                " | <a href=\"http://www.ruanyifeng.com/blog/user/AlbertDiao.html\" title=\"AlbertDiao的全部留言\">档案</a> \n" +
                " | <a href=\"#comment-text\" title=\"引用AlbertDiao的这条留言\" onclick=\"return CommentQuote('comment-quote-204891','AlbertDiao');\">引用</a>\n" +
                "</p>\n" +
                "</div>\n" +
                "</div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "        \n" +
                "    \n" +
                "        \n" +
                "        <div id=\"comment-204896\" class=\"comment\">\n" +
                "    <div  class=\"inner\">\n" +
                "        <div class=\"comment-header\">\n" +
                "            <div class=\"asset-meta\">\n" +
                "<p>\n" +
                "                <span class=\"byline\">\n" +
                "                    \n" +
                "\n" +
                "                    <span class=\"vcard author\"><a title=\"http://yeahcao.blog.hexun.com\" href=\"http://yeahcao.blog.hexun.com\" target=\"_blank\" rel=\"nofollow\">野草博客</a></span>\n" +
                "\n" +
                " 说：\n" +
                "                </span>\n" +
                "</p>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"comment-content\" id=\"comment-quote-204896\">\n" +
                "            <p>嗯，野草一直在用他：）</p>\n" +
                "        </div>\n" +
                "<div class=\"comment-footer\">\n" +
                "<div class=\"comment-footer-inner\">\n" +
                "<p>\n" +
                "                   <a href=\"http://www.ruanyifeng.com/blog/2010/04/the_solution_of_full_text_feed.html#comment-204896\"><abbr class=\"published\" title=\"2010-04-17T22:27:14+08:00\">2010年4月17日 22:27</abbr></a>\n" +
                " | <a href=\"http://www.ruanyifeng.com/blog/user/野草博客.html\" title=\"野草博客的全部留言\">档案</a> \n" +
                " | <a href=\"#comment-text\" title=\"引用野草博客的这条留言\" onclick=\"return CommentQuote('comment-quote-204896','野草博客');\">引用</a>\n" +
                "</p>\n" +
                "</div>\n" +
                "</div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "        \n" +
                "    \n" +
                "        \n" +
                "        <div id=\"comment-204897\" class=\"comment\">\n" +
                "    <div  class=\"inner\">\n" +
                "        <div class=\"comment-header\">\n" +
                "            <div class=\"asset-meta\">\n" +
                "<p>\n" +
                "                <span class=\"byline\">\n" +
                "                    \n" +
                "\n" +
                "                    <span class=\"vcard author\">Ruan YiFeng</span>\n" +
                "\n" +
                " 说：\n" +
                "                </span>\n" +
                "</p>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"comment-content\" id=\"comment-quote-204897\">\n" +
                "            <blockquote>\n" +
                "<pre>引用AlbertDiao的发言：</pre>\n" +
                "\n" +
                "<p>如果是手机RSS的话，摘要Feed比较好。一般浏览摘要，感兴趣的点进全文，这样比较节省流量。</p>\n" +
                "\n" +
                "</blockquote>\n" +
                "\n" +
                "<p>流量会越来越便宜，真正昂贵的是你的时间。所以还是全文Feed好。</p>\n" +
                "\n" +
                "<blockquote>\n" +
                "<pre>引用zp的发言：</pre>\n" +
                "\n" +
                "<p>能不能介绍些Movable Type的文章。</p>\n" +
                "\n" +
                "</blockquote>\n" +
                "\n" +
                "<p>我有这个打算，但是文章不太好写，还需要准备。</p>\n" +
                "        </div>\n" +
                "<div class=\"comment-footer\">\n" +
                "<div class=\"comment-footer-inner\">\n" +
                "<p>\n" +
                "                   <a href=\"http://www.ruanyifeng.com/blog/2010/04/the_solution_of_full_text_feed.html#comment-204897\"><abbr class=\"published\" title=\"2010-04-17T22:31:35+08:00\">2010年4月17日 22:31</abbr></a>\n" +
                " | <a href=\"http://www.ruanyifeng.com/blog/user/Ruan YiFeng.html\" title=\"Ruan YiFeng的全部留言\">档案</a> \n" +
                " | <a href=\"#comment-text\" title=\"引用Ruan YiFeng的这条留言\" onclick=\"return CommentQuote('comment-quote-204897','Ruan YiFeng');\">引用</a>\n" +
                "</p>\n" +
                "</div>\n" +
                "</div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "        \n" +
                "    \n" +
                "        \n" +
                "        <div id=\"comment-204899\" class=\"comment\">\n" +
                "    <div  class=\"inner\">\n" +
                "        <div class=\"comment-header\">\n" +
                "            <div class=\"asset-meta\">\n" +
                "<p>\n" +
                "                <span class=\"byline\">\n" +
                "                    \n" +
                "\n" +
                "                    <span class=\"vcard author\">luops</span>\n" +
                "\n" +
                " 说：\n" +
                "                </span>\n" +
                "</p>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"comment-content\" id=\"comment-quote-204899\">\n" +
                "            <p>昨晚测试了此订阅<br />\n" +
                " 同时我也保留了原订阅。<br />\n" +
                "今天发现，同样订阅了163新闻的情况下<br />\n" +
                "全文订阅比官方订阅少了很多新闻<br />\n" +
                "不知其他童靴有没有这样子情况</p>\n" +
                "        </div>\n" +
                "<div class=\"comment-footer\">\n" +
                "<div class=\"comment-footer-inner\">\n" +
                "<p>\n" +
                "                   <a href=\"http://www.ruanyifeng.com/blog/2010/04/the_solution_of_full_text_feed.html#comment-204899\"><abbr class=\"published\" title=\"2010-04-18T09:39:37+08:00\">2010年4月18日 09:39</abbr></a>\n" +
                " | <a href=\"http://www.ruanyifeng.com/blog/user/luops.html\" title=\"luops的全部留言\">档案</a> \n" +
                " | <a href=\"#comment-text\" title=\"引用luops的这条留言\" onclick=\"return CommentQuote('comment-quote-204899','luops');\">引用</a>\n" +
                "</p>\n" +
                "</div>\n" +
                "</div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "        \n" +
                "    \n" +
                "        \n" +
                "        <div id=\"comment-204900\" class=\"comment\">\n" +
                "    <div  class=\"inner\">\n" +
                "        <div class=\"comment-header\">\n" +
                "            <div class=\"asset-meta\">\n" +
                "<p>\n" +
                "                <span class=\"byline\">\n" +
                "                    \n" +
                "\n" +
                "                    <span class=\"vcard author\">鲜为人志</span>\n" +
                "\n" +
                " 说：\n" +
                "                </span>\n" +
                "</p>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"comment-content\" id=\"comment-quote-204900\">\n" +
                "            <p>呵呵～ 这样都可以啊～</p>\n" +
                "        </div>\n" +
                "<div class=\"comment-footer\">\n" +
                "<div class=\"comment-footer-inner\">\n" +
                "<p>\n" +
                "                   <a href=\"http://www.ruanyifeng.com/blog/2010/04/the_solution_of_full_text_feed.html#comment-204900\"><abbr class=\"published\" title=\"2010-04-18T09:53:31+08:00\">2010年4月18日 09:53</abbr></a>\n" +
                " | <a href=\"http://www.ruanyifeng.com/blog/user/鲜为人志.html\" title=\"鲜为人志的全部留言\">档案</a> \n" +
                " | <a href=\"#comment-text\" title=\"引用鲜为人志的这条留言\" onclick=\"return CommentQuote('comment-quote-204900','鲜为人志');\">引用</a>\n" +
                "</p>\n" +
                "</div>\n" +
                "</div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "        \n" +
                "    \n" +
                "        \n" +
                "        <div id=\"comment-204901\" class=\"comment\">\n" +
                "    <div  class=\"inner\">\n" +
                "        <div class=\"comment-header\">\n" +
                "            <div class=\"asset-meta\">\n" +
                "<p>\n" +
                "                <span class=\"byline\">\n" +
                "                    \n" +
                "\n" +
                "                    <span class=\"vcard author\">roy_hu</span>\n" +
                "\n" +
                " 说：\n" +
                "                </span>\n" +
                "</p>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"comment-content\" id=\"comment-quote-204901\">\n" +
                "            <blockquote>\n" +
                "<pre>引用AlbertDiao的发言：</pre>\n" +
                "\n" +
                "<p>如果是手机RSS的话，摘要Feed比较好。一般浏览摘要，感兴趣的点进全文，这样比较节省流量。</p>\n" +
                "\n" +
                "</blockquote>\n" +
                "\n" +
                "<p>我更喜欢全文博客，因为在手机上看Google Reader，自动都排好了版，而看全文的时候需要浏览器排版，没有Google Reader那样专门设计给手机的看着舒服。</p>\n" +
                "        </div>\n" +
                "<div class=\"comment-footer\">\n" +
                "<div class=\"comment-footer-inner\">\n" +
                "<p>\n" +
                "                   <a href=\"http://www.ruanyifeng.com/blog/2010/04/the_solution_of_full_text_feed.html#comment-204901\"><abbr class=\"published\" title=\"2010-04-18T10:13:36+08:00\">2010年4月18日 10:13</abbr></a>\n" +
                " | <a href=\"http://www.ruanyifeng.com/blog/user/roy_hu.html\" title=\"roy_hu的全部留言\">档案</a> \n" +
                " | <a href=\"#comment-text\" title=\"引用roy_hu的这条留言\" onclick=\"return CommentQuote('comment-quote-204901','roy_hu');\">引用</a>\n" +
                "</p>\n" +
                "</div>\n" +
                "</div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "        \n" +
                "    \n" +
                "        \n" +
                "        <div id=\"comment-204902\" class=\"comment\">\n" +
                "    <div  class=\"inner\">\n" +
                "        <div class=\"comment-header\">\n" +
                "            <div class=\"asset-meta\">\n" +
                "<p>\n" +
                "                <span class=\"byline\">\n" +
                "                    \n" +
                "\n" +
                "                    <span class=\"vcard author\">Jack</span>\n" +
                "\n" +
                " 说：\n" +
                "                </span>\n" +
                "</p>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"comment-content\" id=\"comment-quote-204902\">\n" +
                "            <p>另外，也可以用YAHOO PIPE 和YQL来抓取全文。这样除了可以把非全文的FEED变成全文输出外，还可以处理根本没有FEED输出的网页。（不过有很多网页需要处理一下GB2312和UNICODE转换。).而且这样还有一个最大的好处,就是不用建立自己的服务器。</p>\n" +
                "\n" +
                "<p><br />\n" +
                "下面两个FEED 就是用这种办法生成的。<br />\n" +
                "<a href=\"http://feeds.feedburner.com/wenxuecity_news\" rel=\"nofollow\">http://feeds.feedburner.com/wenxuecity_news</a></p>\n" +
                "\n" +
                "<p><a href=\"http://feeds.feedburner.com/boxun_headline\" rel=\"nofollow\">http://feeds.feedburner.com/boxun_headline</a></p>\n" +
                "\n" +
                "<p>可以用GOOGLE READER 来读取它们。也不失为一种间接翻越G/F/W 的办法。<br />\n" +
                "</p>\n" +
                "        </div>\n" +
                "<div class=\"comment-footer\">\n" +
                "<div class=\"comment-footer-inner\">\n" +
                "<p>\n" +
                "                   <a href=\"http://www.ruanyifeng.com/blog/2010/04/the_solution_of_full_text_feed.html#comment-204902\"><abbr class=\"published\" title=\"2010-04-18T11:49:57+08:00\">2010年4月18日 11:49</abbr></a>\n" +
                " | <a href=\"http://www.ruanyifeng.com/blog/user/Jack.html\" title=\"Jack的全部留言\">档案</a> \n" +
                " | <a href=\"#comment-text\" title=\"引用Jack的这条留言\" onclick=\"return CommentQuote('comment-quote-204902','Jack');\">引用</a>\n" +
                "</p>\n" +
                "</div>\n" +
                "</div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "        \n" +
                "    \n" +
                "        \n" +
                "        <div id=\"comment-204903\" class=\"comment\">\n" +
                "    <div  class=\"inner\">\n" +
                "        <div class=\"comment-header\">\n" +
                "            <div class=\"asset-meta\">\n" +
                "<p>\n" +
                "                <span class=\"byline\">\n" +
                "                    \n" +
                "\n" +
                "                    <span class=\"vcard author\">Ruan YiFeng</span>\n" +
                "\n" +
                " 说：\n" +
                "                </span>\n" +
                "</p>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"comment-content\" id=\"comment-quote-204903\">\n" +
                "            <blockquote>\n" +
                "<pre>引用luops的发言：</pre>\n" +
                "\n" +
                "<p>全文订阅比官方订阅少了很多新闻</p>\n" +
                "\n" +
                "</blockquote>\n" +
                "\n" +
                "<p>全文Feed默认只有4个条目，下载代码后，你可以自己修改这个值。</p>\n" +
                "        </div>\n" +
                "<div class=\"comment-footer\">\n" +
                "<div class=\"comment-footer-inner\">\n" +
                "<p>\n" +
                "                   <a href=\"http://www.ruanyifeng.com/blog/2010/04/the_solution_of_full_text_feed.html#comment-204903\"><abbr class=\"published\" title=\"2010-04-18T17:42:49+08:00\">2010年4月18日 17:42</abbr></a>\n" +
                " | <a href=\"http://www.ruanyifeng.com/blog/user/Ruan YiFeng.html\" title=\"Ruan YiFeng的全部留言\">档案</a> \n" +
                " | <a href=\"#comment-text\" title=\"引用Ruan YiFeng的这条留言\" onclick=\"return CommentQuote('comment-quote-204903','Ruan YiFeng');\">引用</a>\n" +
                "</p>\n" +
                "</div>\n" +
                "</div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "        \n" +
                "    \n" +
                "        \n" +
                "        <div id=\"comment-204915\" class=\"comment\">\n" +
                "    <div  class=\"inner\">\n" +
                "        <div class=\"comment-header\">\n" +
                "            <div class=\"asset-meta\">\n" +
                "<p>\n" +
                "                <span class=\"byline\">\n" +
                "                    \n" +
                "\n" +
                "                    <span class=\"vcard author\"><a title=\"http://没有，汗~~~\" href=\"http://没有，汗~~~\" target=\"_blank\" rel=\"nofollow\">坏坏鼠</a></span>\n" +
                "\n" +
                " 说：\n" +
                "                </span>\n" +
                "</p>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"comment-content\" id=\"comment-quote-204915\">\n" +
                "            <p>不懂编程只会用GR的文科生飘过~~~<br />\n" +
                "ps:阮老师的这篇文章GR里也只是显示标题，所以漂洋过海地过来了（牛博编辑的那个频道，已经将你的博客订阅了呵O(∩_∩)O）~~</p>\n" +
                "        </div>\n" +
                "<div class=\"comment-footer\">\n" +
                "<div class=\"comment-footer-inner\">\n" +
                "<p>\n" +
                "                   <a href=\"http://www.ruanyifeng.com/blog/2010/04/the_solution_of_full_text_feed.html#comment-204915\"><abbr class=\"published\" title=\"2010-04-19T00:36:02+08:00\">2010年4月19日 00:36</abbr></a>\n" +
                " | <a href=\"http://www.ruanyifeng.com/blog/user/坏坏鼠.html\" title=\"坏坏鼠的全部留言\">档案</a> \n" +
                " | <a href=\"#comment-text\" title=\"引用坏坏鼠的这条留言\" onclick=\"return CommentQuote('comment-quote-204915','坏坏鼠');\">引用</a>\n" +
                "</p>\n" +
                "</div>\n" +
                "</div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "        \n" +
                "    \n" +
                "        \n" +
                "        <div id=\"comment-204919\" class=\"comment\">\n" +
                "    <div  class=\"inner\">\n" +
                "        <div class=\"comment-header\">\n" +
                "            <div class=\"asset-meta\">\n" +
                "<p>\n" +
                "                <span class=\"byline\">\n" +
                "                    \n" +
                "\n" +
                "                    <span class=\"vcard author\">111</span>\n" +
                "\n" +
                " 说：\n" +
                "                </span>\n" +
                "</p>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"comment-content\" id=\"comment-quote-204919\">\n" +
                "            <blockquote>\n" +
                "<pre>引用luops的发言：</pre>\n" +
                "\n" +
                "<p>全文订阅比官方订阅少了很多新闻</p>\n" +
                "\n" +
                "</blockquote>\n" +
                "\n" +
                "<p><br />\n" +
                "是这样的，丢失了好多，时效性好差<br />\n" +
                "</p>\n" +
                "        </div>\n" +
                "<div class=\"comment-footer\">\n" +
                "<div class=\"comment-footer-inner\">\n" +
                "<p>\n" +
                "                   <a href=\"http://www.ruanyifeng.com/blog/2010/04/the_solution_of_full_text_feed.html#comment-204919\"><abbr class=\"published\" title=\"2010-04-19T10:25:01+08:00\">2010年4月19日 10:25</abbr></a>\n" +
                " | <a href=\"http://www.ruanyifeng.com/blog/user/111.html\" title=\"111的全部留言\">档案</a> \n" +
                " | <a href=\"#comment-text\" title=\"引用111的这条留言\" onclick=\"return CommentQuote('comment-quote-204919','111');\">引用</a>\n" +
                "</p>\n" +
                "</div>\n" +
                "</div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "        \n" +
                "    \n" +
                "        \n" +
                "        <div id=\"comment-204921\" class=\"comment\">\n" +
                "    <div  class=\"inner\">\n" +
                "        <div class=\"comment-header\">\n" +
                "            <div class=\"asset-meta\">\n" +
                "<p>\n" +
                "                <span class=\"byline\">\n" +
                "                    \n" +
                "\n" +
                "                    <span class=\"vcard author\"><a title=\"http://www.cnblogs.com/kuber\" href=\"http://www.cnblogs.com/kuber\" target=\"_blank\" rel=\"nofollow\">kuber</a></span>\n" +
                "\n" +
                " 说：\n" +
                "                </span>\n" +
                "</p>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"comment-content\" id=\"comment-quote-204921\">\n" +
                "            <p>想请教一下你怎么修改规则来全文输出新浪网rss的, 我也碰到几个Feed,缺省的配置不能正确处理.<br />\n" +
                "另外我建议设立一个地方大家可以交流一下脚本不能处理的feed,以及修改的方法, 这样各人不用重复浪费时间了.</p>\n" +
                "        </div>\n" +
                "<div class=\"comment-footer\">\n" +
                "<div class=\"comment-footer-inner\">\n" +
                "<p>\n" +
                "                   <a href=\"http://www.ruanyifeng.com/blog/2010/04/the_solution_of_full_text_feed.html#comment-204921\"><abbr class=\"published\" title=\"2010-04-19T10:57:07+08:00\">2010年4月19日 10:57</abbr></a>\n" +
                " | <a href=\"http://www.ruanyifeng.com/blog/user/kuber.html\" title=\"kuber的全部留言\">档案</a> \n" +
                " | <a href=\"#comment-text\" title=\"引用kuber的这条留言\" onclick=\"return CommentQuote('comment-quote-204921','kuber');\">引用</a>\n" +
                "</p>\n" +
                "</div>\n" +
                "</div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "        \n" +
                "    \n" +
                "        \n" +
                "        <div id=\"comment-204922\" class=\"comment\">\n" +
                "    <div  class=\"inner\">\n" +
                "        <div class=\"comment-header\">\n" +
                "            <div class=\"asset-meta\">\n" +
                "<p>\n" +
                "                <span class=\"byline\">\n" +
                "                    \n" +
                "\n" +
                "                    <span class=\"vcard author\">111</span>\n" +
                "\n" +
                " 说：\n" +
                "                </span>\n" +
                "</p>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"comment-content\" id=\"comment-quote-204922\">\n" +
                "            <p>下载了lz的代码，发布到网站上，功能可用了。rss数量自己设置就好。</p>\n" +
                "\n" +
                "<p>杯具的是网站只有内网地址，gr不认生成的feed地址。</p>\n" +
                "\n" +
                "<p>只能CS订阅，不喜。</p>\n" +
                "        </div>\n" +
                "<div class=\"comment-footer\">\n" +
                "<div class=\"comment-footer-inner\">\n" +
                "<p>\n" +
                "                   <a href=\"http://www.ruanyifeng.com/blog/2010/04/the_solution_of_full_text_feed.html#comment-204922\"><abbr class=\"published\" title=\"2010-04-19T11:23:25+08:00\">2010年4月19日 11:23</abbr></a>\n" +
                " | <a href=\"http://www.ruanyifeng.com/blog/user/111.html\" title=\"111的全部留言\">档案</a> \n" +
                " | <a href=\"#comment-text\" title=\"引用111的这条留言\" onclick=\"return CommentQuote('comment-quote-204922','111');\">引用</a>\n" +
                "</p>\n" +
                "</div>\n" +
                "</div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "        \n" +
                "    \n" +
                "        \n" +
                "        <div id=\"comment-204923\" class=\"comment\">\n" +
                "    <div  class=\"inner\">\n" +
                "        <div class=\"comment-header\">\n" +
                "            <div class=\"asset-meta\">\n" +
                "<p>\n" +
                "                <span class=\"byline\">\n" +
                "                    \n" +
                "\n" +
                "                    <span class=\"vcard author\">lietlie</span>\n" +
                "\n" +
                " 说：\n" +
                "                </span>\n" +
                "</p>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"comment-content\" id=\"comment-quote-204923\">\n" +
                "            <p><a href=\"http://mrss.dokoda.jp/\" rel=\"nofollow\">http://mrss.dokoda.jp/</a><br />\n" +
                "虽然是小鬼子的网站，但是是我找到的能够全文Feed最好的在线工具了，和LZ推荐的网站相比，可以输出所有项目，而没有4条目的限制，当然也不必自己搭建服务器，日文内容很简单，如果使用的是FF或Chrome浏览器还可以利用Google的自动翻译功能将大致内容翻译为中文（FF利用Google Toolbar）——其实即使不翻译一样很容易使用。</p>\n" +
                "        </div>\n" +
                "<div class=\"comment-footer\">\n" +
                "<div class=\"comment-footer-inner\">\n" +
                "<p>\n" +
                "                   <a href=\"http://www.ruanyifeng.com/blog/2010/04/the_solution_of_full_text_feed.html#comment-204923\"><abbr class=\"published\" title=\"2010-04-19T12:31:20+08:00\">2010年4月19日 12:31</abbr></a>\n" +
                " | <a href=\"http://www.ruanyifeng.com/blog/user/lietlie.html\" title=\"lietlie的全部留言\">档案</a> \n" +
                " | <a href=\"#comment-text\" title=\"引用lietlie的这条留言\" onclick=\"return CommentQuote('comment-quote-204923','lietlie');\">引用</a>\n" +
                "</p>\n" +
                "</div>\n" +
                "</div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "        \n" +
                "    \n" +
                "        \n" +
                "        <div id=\"comment-204925\" class=\"comment\">\n" +
                "    <div  class=\"inner\">\n" +
                "        <div class=\"comment-header\">\n" +
                "            <div class=\"asset-meta\">\n" +
                "<p>\n" +
                "                <span class=\"byline\">\n" +
                "                    \n" +
                "\n" +
                "                    <span class=\"vcard author\">Ruan YiFeng</span>\n" +
                "\n" +
                " 说：\n" +
                "                </span>\n" +
                "</p>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"comment-content\" id=\"comment-quote-204925\">\n" +
                "            <blockquote>\n" +
                "<pre>引用kuber的发言：</pre>\n" +
                "\n" +
                "<p>想请教一下你怎么修改规则来全文输出新浪网rss的, 我也碰到几个Feed,缺省的配置不能正确处理.</p>\n" +
                "\n" +
                "</blockquote>\n" +
                "\n" +
                "<p>新浪的内容容器，有一个比较怪的ID名。只要搜索这个字符串，就能提取内容了。</p>\n" +
                "\n" +
                "<p>最终，你还是需要读readability.php的代码，只要读懂了，我觉得任何页面都能提取。<br />\n" +
                "</p>\n" +
                "        </div>\n" +
                "<div class=\"comment-footer\">\n" +
                "<div class=\"comment-footer-inner\">\n" +
                "<p>\n" +
                "                   <a href=\"http://www.ruanyifeng.com/blog/2010/04/the_solution_of_full_text_feed.html#comment-204925\"><abbr class=\"published\" title=\"2010-04-19T12:58:12+08:00\">2010年4月19日 12:58</abbr></a>\n" +
                " | <a href=\"http://www.ruanyifeng.com/blog/user/Ruan YiFeng.html\" title=\"Ruan YiFeng的全部留言\">档案</a> \n" +
                " | <a href=\"#comment-text\" title=\"引用Ruan YiFeng的这条留言\" onclick=\"return CommentQuote('comment-quote-204925','Ruan YiFeng');\">引用</a>\n" +
                "</p>\n" +
                "</div>\n" +
                "</div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "        \n" +
                "    \n" +
                "        \n" +
                "        <div id=\"comment-204927\" class=\"comment\">\n" +
                "    <div  class=\"inner\">\n" +
                "        <div class=\"comment-header\">\n" +
                "            <div class=\"asset-meta\">\n" +
                "<p>\n" +
                "                <span class=\"byline\">\n" +
                "                    \n" +
                "\n" +
                "                    <span class=\"vcard author\"><a title=\"http://www.shimuuu.com\" href=\"http://www.shimuuu.com\" target=\"_blank\" rel=\"nofollow\">诗沐</a></span>\n" +
                "\n" +
                " 说：\n" +
                "                </span>\n" +
                "</p>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"comment-content\" id=\"comment-quote-204927\">\n" +
                "            <p>哇 源码写得相当清爽啊～注释习惯很棒</p>\n" +
                "        </div>\n" +
                "<div class=\"comment-footer\">\n" +
                "<div class=\"comment-footer-inner\">\n" +
                "<p>\n" +
                "                   <a href=\"http://www.ruanyifeng.com/blog/2010/04/the_solution_of_full_text_feed.html#comment-204927\"><abbr class=\"published\" title=\"2010-04-19T15:02:32+08:00\">2010年4月19日 15:02</abbr></a>\n" +
                " | <a href=\"http://www.ruanyifeng.com/blog/user/诗沐.html\" title=\"诗沐的全部留言\">档案</a> \n" +
                " | <a href=\"#comment-text\" title=\"引用诗沐的这条留言\" onclick=\"return CommentQuote('comment-quote-204927','诗沐');\">引用</a>\n" +
                "</p>\n" +
                "</div>\n" +
                "</div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "        \n" +
                "    \n" +
                "        \n" +
                "        <div id=\"comment-204930\" class=\"comment\">\n" +
                "    <div  class=\"inner\">\n" +
                "        <div class=\"comment-header\">\n" +
                "            <div class=\"asset-meta\">\n" +
                "<p>\n" +
                "                <span class=\"byline\">\n" +
                "                    \n" +
                "\n" +
                "                    <span class=\"vcard author\">xangd</span>\n" +
                "\n" +
                " 说：\n" +
                "                </span>\n" +
                "</p>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"comment-content\" id=\"comment-quote-204930\">\n" +
                "            <p>有人在appspot上部署了一个python的port<br />\n" +
                "<a href=\"http://andrewtrusty.appspot.com/readability/\" rel=\"nofollow\">http://andrewtrusty.appspot.com/readability/</a><br />\n" +
                "这个没有4篇post的限制</p>\n" +
                "        </div>\n" +
                "<div class=\"comment-footer\">\n" +
                "<div class=\"comment-footer-inner\">\n" +
                "<p>\n" +
                "                   <a href=\"http://www.ruanyifeng.com/blog/2010/04/the_solution_of_full_text_feed.html#comment-204930\"><abbr class=\"published\" title=\"2010-04-19T17:12:27+08:00\">2010年4月19日 17:12</abbr></a>\n" +
                " | <a href=\"http://www.ruanyifeng.com/blog/user/xangd.html\" title=\"xangd的全部留言\">档案</a> \n" +
                " | <a href=\"#comment-text\" title=\"引用xangd的这条留言\" onclick=\"return CommentQuote('comment-quote-204930','xangd');\">引用</a>\n" +
                "</p>\n" +
                "</div>\n" +
                "</div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "        \n" +
                "    \n" +
                "        \n" +
                "        <div id=\"comment-204949\" class=\"comment\">\n" +
                "    <div  class=\"inner\">\n" +
                "        <div class=\"comment-header\">\n" +
                "            <div class=\"asset-meta\">\n" +
                "<p>\n" +
                "                <span class=\"byline\">\n" +
                "                    \n" +
                "\n" +
                "                    <span class=\"vcard author\">neotrue</span>\n" +
                "\n" +
                " 说：\n" +
                "                </span>\n" +
                "</p>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"comment-content\" id=\"comment-quote-204949\">\n" +
                "            <p>很好用，谢谢！</p>\n" +
                "        </div>\n" +
                "<div class=\"comment-footer\">\n" +
                "<div class=\"comment-footer-inner\">\n" +
                "<p>\n" +
                "                   <a href=\"http://www.ruanyifeng.com/blog/2010/04/the_solution_of_full_text_feed.html#comment-204949\"><abbr class=\"published\" title=\"2010-04-20T23:01:10+08:00\">2010年4月20日 23:01</abbr></a>\n" +
                " | <a href=\"http://www.ruanyifeng.com/blog/user/neotrue.html\" title=\"neotrue的全部留言\">档案</a> \n" +
                " | <a href=\"#comment-text\" title=\"引用neotrue的这条留言\" onclick=\"return CommentQuote('comment-quote-204949','neotrue');\">引用</a>\n" +
                "</p>\n" +
                "</div>\n" +
                "</div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "        \n" +
                "    \n" +
                "        \n" +
                "        <div id=\"comment-205898\" class=\"comment\">\n" +
                "    <div  class=\"inner\">\n" +
                "        <div class=\"comment-header\">\n" +
                "            <div class=\"asset-meta\">\n" +
                "<p>\n" +
                "                <span class=\"byline\">\n" +
                "                    \n" +
                "\n" +
                "                    <span class=\"vcard author\">harvey</span>\n" +
                "\n" +
                " 说：\n" +
                "                </span>\n" +
                "</p>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"comment-content\" id=\"comment-quote-205898\">\n" +
                "            <p>博主，作者把1.5版本放出来了，<br />\n" +
                "可否再麻烦你打包一下，我bazzar一直不成功</p>\n" +
                "        </div>\n" +
                "<div class=\"comment-footer\">\n" +
                "<div class=\"comment-footer-inner\">\n" +
                "<p>\n" +
                "                   <a href=\"http://www.ruanyifeng.com/blog/2010/04/the_solution_of_full_text_feed.html#comment-205898\"><abbr class=\"published\" title=\"2010-06-01T19:04:09+08:00\">2010年6月 1日 19:04</abbr></a>\n" +
                " | <a href=\"http://www.ruanyifeng.com/blog/user/harvey.html\" title=\"harvey的全部留言\">档案</a> \n" +
                " | <a href=\"#comment-text\" title=\"引用harvey的这条留言\" onclick=\"return CommentQuote('comment-quote-205898','harvey');\">引用</a>\n" +
                "</p>\n" +
                "</div>\n" +
                "</div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "        \n" +
                "    \n" +
                "        \n" +
                "        <div id=\"comment-205934\" class=\"comment\">\n" +
                "    <div  class=\"inner\">\n" +
                "        <div class=\"comment-header\">\n" +
                "            <div class=\"asset-meta\">\n" +
                "<p>\n" +
                "                <span class=\"byline\">\n" +
                "                    \n" +
                "\n" +
                "                    <span class=\"vcard author\">Ruan YiFeng</span>\n" +
                "\n" +
                " 说：\n" +
                "                </span>\n" +
                "</p>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"comment-content\" id=\"comment-quote-205934\">\n" +
                "            <blockquote>\n" +
                "<pre>引用harvey的发言：</pre>\n" +
                "\n" +
                "<p>博主，作者把1.5版本放出来了，<br />\n" +
                "可否再麻烦你打包一下，我bazzar一直不成功</p>\n" +
                "\n" +
                "</blockquote>\n" +
                "\n" +
                "<p>已经加上去了，:-)</p>\n" +
                "        </div>\n" +
                "<div class=\"comment-footer\">\n" +
                "<div class=\"comment-footer-inner\">\n" +
                "<p>\n" +
                "                   <a href=\"http://www.ruanyifeng.com/blog/2010/04/the_solution_of_full_text_feed.html#comment-205934\"><abbr class=\"published\" title=\"2010-06-03T13:04:34+08:00\">2010年6月 3日 13:04</abbr></a>\n" +
                " | <a href=\"http://www.ruanyifeng.com/blog/user/Ruan YiFeng.html\" title=\"Ruan YiFeng的全部留言\">档案</a> \n" +
                " | <a href=\"#comment-text\" title=\"引用Ruan YiFeng的这条留言\" onclick=\"return CommentQuote('comment-quote-205934','Ruan YiFeng');\">引用</a>\n" +
                "</p>\n" +
                "</div>\n" +
                "</div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "        \n" +
                "    \n" +
                "        \n" +
                "        <div id=\"comment-207117\" class=\"comment\">\n" +
                "    <div  class=\"inner\">\n" +
                "        <div class=\"comment-header\">\n" +
                "            <div class=\"asset-meta\">\n" +
                "<p>\n" +
                "                <span class=\"byline\">\n" +
                "                    \n" +
                "\n" +
                "                    <span class=\"vcard author\"><a title=\"http://sina.com\" href=\"http://sina.com\" target=\"_blank\" rel=\"nofollow\">张治国</a></span>\n" +
                "\n" +
                " 说：\n" +
                "                </span>\n" +
                "</p>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"comment-content\" id=\"comment-quote-207117\">\n" +
                "            <p>博主，全文Feed默认只有4个条目，下载代码后，修改哪段代码可以改变这个值啊，config-sample.PHP中的数值吗？我是新手，希望博主指点一下，谢谢。</p>\n" +
                "        </div>\n" +
                "<div class=\"comment-footer\">\n" +
                "<div class=\"comment-footer-inner\">\n" +
                "<p>\n" +
                "                   <a href=\"http://www.ruanyifeng.com/blog/2010/04/the_solution_of_full_text_feed.html#comment-207117\"><abbr class=\"published\" title=\"2010-08-05T01:12:23+08:00\">2010年8月 5日 01:12</abbr></a>\n" +
                " | <a href=\"http://www.ruanyifeng.com/blog/user/张治国.html\" title=\"张治国的全部留言\">档案</a> \n" +
                " | <a href=\"#comment-text\" title=\"引用张治国的这条留言\" onclick=\"return CommentQuote('comment-quote-207117','张治国');\">引用</a>\n" +
                "</p>\n" +
                "</div>\n" +
                "</div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "        \n" +
                "    \n" +
                "        \n" +
                "        <div id=\"comment-210873\" class=\"comment\">\n" +
                "    <div id=\"comment-last\" class=\"inner\">\n" +
                "        <div class=\"comment-header\">\n" +
                "            <div class=\"asset-meta\">\n" +
                "<p>\n" +
                "                <span class=\"byline\">\n" +
                "                    \n" +
                "\n" +
                "                    <span class=\"vcard author\">felix</span>\n" +
                "\n" +
                " 说：\n" +
                "                </span>\n" +
                "</p>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"comment-content\" id=\"comment-quote-210873\">\n" +
                "            <p>看不懂readability，不知道博主能否提供一下过滤页面上的干扰字符的方法<br />\n" +
                "</p>\n" +
                "        </div>\n" +
                "<div class=\"comment-footer\">\n" +
                "<div class=\"comment-footer-inner\">\n" +
                "<p>\n" +
                "                   <a href=\"http://www.ruanyifeng.com/blog/2010/04/the_solution_of_full_text_feed.html#comment-210873\"><abbr class=\"published\" title=\"2011-01-12T12:28:44+08:00\">2011年1月12日 12:28</abbr></a>\n" +
                " | <a href=\"http://www.ruanyifeng.com/blog/user/felix.html\" title=\"felix的全部留言\">档案</a> \n" +
                " | <a href=\"#comment-text\" title=\"引用felix的这条留言\" onclick=\"return CommentQuote('comment-quote-210873','felix');\">引用</a>\n" +
                "</p>\n" +
                "</div>\n" +
                "</div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "        \n" +
                "    </div>\n" +
                "        \n" +
                "    \n" +
                "\n" +
                "\n" +
                "    \n" +
                "    \n" +
                "    <div class=\"comments-open\" id=\"comments-open\">\n" +
                "        <h2 class=\"comments-open-header\">我要发表看法</h2>\n" +
                "        <div class=\"comments-open-content\">\n" +
                "\n" +
                "        \n" +
                "            <div id=\"comment-greeting\"></div>\n" +
                "\n" +
                "            <form method=\"post\" action=\"http://www.ruanyifeng.com/cgi-bin/mtos/mt-comments.cgi\" name=\"comments_form\" id=\"comments-form\" onsubmit=\"return pleaseWait();\">\n" +
                "                <input type=\"hidden\" name=\"static\" value=\"1\" />\n" +
                "                <input type=\"hidden\" name=\"entry_id\" value=\"1377\" />\n" +
                "                <input type=\"hidden\" name=\"__lang\" value=\"en\" />\n" +
                "                <input type=\"hidden\" name=\"parent_id\" value=\"\" id=\"comment-parent-id\" />\n" +
                "                <input type=\"hidden\" name=\"armor\" value=\"1\" />\n" +
                "                <input type=\"hidden\" name=\"preview\" value=\"\" />\n" +
                "                <input type=\"hidden\" name=\"sid\" value=\"\" />\n" +
                "                <div id=\"comments-open-data\">\n" +
                "            <div id=\"comments-open-text\">\n" +
                "                    <p><label for=\"comment-text\">您的留言\n" +
                "                    （HTML标签部分可用）</label></p>\n" +
                "                    <p><textarea id=\"comment-text\" name=\"text\" rows=\"10\" cols=\"50\"></textarea></p>\n" +
                "                </div>\n" +
                "                    <div id=\"comment-form-name\">\n" +
                "                        <p><label for=\"comment-author\">您的大名：</label></p>\n" +
                "                        <p><input id=\"comment-author\" name=\"author\" size=\"30\" value=\"\" />  <span class=\"hint\"> &laquo;-必填</span></p>\n" +
                "                    </div>\n" +
                "                    <div id=\"comment-form-email\">\n" +
                "                        <p><label for=\"comment-email\">电子邮件：</label></p>\n" +
                "                        <p><input id=\"comment-email\" name=\"email\" size=\"30\" value=\"\" />  <span class=\"hint\"> &laquo;-必填，不公开</span></p>\n" +
                "                    </div>\n" +
                "                    <div id=\"comment-form-url\">\n" +
                "                        <p><label for=\"comment-url\">个人网址：</label></p>\n" +
                "                        <p><input id=\"comment-url\" name=\"url\" size=\"30\" value=\"\" />  <span class=\"hint\"> &laquo;-我信任你，不会填写广告链接</span></p>\n" +
                "                    </div>\n" +
                "                    <div id=\"comment-form-remember-me\">\n" +
                "                        <p>\n" +
                "                        <label for=\"comment-bake-cookie\">记住个人信息？</label><input type=\"checkbox\" id=\"comment-bake-cookie\" name=\"bakecookie\" onclick=\"!this.checked?forgetMe(document.comments_form):rememberMe(document.comments_form)\" value=\"1\" accesskey=\"r\" /></p>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "                    <div id=\"comment-form-reply\" style=\"display:none\">\n" +
                "                    <input type=\"checkbox\" id=\"comment-reply\" name=\"comment_reply\" value=\"\" onclick=\"mtSetCommentParentID()\" />\n" +
                "                    <label for=\"comment-reply\" id=\"comment-reply-label\"></label>\n" +
                "                </div>\n" +
                "                <div id=\"comments-open-captcha\"></div>\n" +
                "                <div id=\"comments-open-footer\">\n" +
                "<div id=\"wait\" style=\"display:none;\">\n" +
                "<p><strong>正在发表您的评论，请稍候</strong></p>\n" +
                "<p>\n" +
                "<input type=\"text\" name=\"percent\" size=\"3\" style=\"font-family:Arial; color:black;text-align:center; border-width:medium; border-style:none;\">           \n" +
                "<input type=\"text\" name=\"chart\" size=\"46\" style=\"font-family:Arial;font-weight:bolder; color:black; padding:0px; border-style:none;\">\n" +
                "</p>\n" +
                "</div>\n" +
                "\n" +
                "                    <p><input type=\"submit\" accesskey=\"s\" name=\"post\" id=\"comment-submit\" value=\"发表\" />  <span class=\"hint\"> &laquo;- 点击按钮</span></p>\n" +
                "                </div>\n" +
                "            </form>\n" +
                "\n" +
                "\n" +
                "        </div>\n" +
                "    </div>\n" +
                "\n" +
                "    \n" +
                "\n" +
                "\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "\n" +
                "                </div>\n" +
                "            </div>\n" +
                "\n" +
                "\n" +
                "            <div id=\"footer\">\n" +
                "    <div id=\"footer-inner\">\n" +
                "        <div id=\"footer-content\">\n" +
                "<p><a href=\"/contact.html\">联系方式</a> | ruanyifeng.com 2003 - 2012\n" +
                "<!--WEBBOT bot=\"Script\" startspan PREVIEW=\"Site Meter\" -->\n" +
                "<script type=\"text/javascript\" language=\"JavaScript\">var site=\"sm3bomoocom\"</script>\n" +
                "<script type=\"text/javascript\" language=\"JavaScript1.2\" src=\"http://sm3.sitemeter.com/js/counter.js?site=sm3bomoocom\">\n" +
                "</script>\n" +
                "<noscript>\n" +
                "<a href=\"http://sm3.sitemeter.com/stats.asp?site=sm3bomoocom\" target=\"_top\">\n" +
                "<img src=\"http://sm3.sitemeter.com/meter.asp?site=sm3bomoocom\" alt=\"Site Meter\" border=0></a>\n" +
                "</noscript>\n" +
                "<!-- Copyright (c)2002 Site Meter -->\n" +
                "</p>\n" +
                "\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "<div id=\"share_button_proto\" style=\"display:none;\"><a class=\"bshareDiv\" href=\"http://www.bshare.cn/share\">分享按钮</a><script type=\"text/javascript\" charset=\"utf-8\" src=\"http://static.bshare.cn/b/buttonLite.js#uuid=15e016b4-0028-44f1-a40d-a3c9d9c13c28&style=10&bgcolor=#fff&bp=facebook,twitter,sinaminiblog,qqmb,douban,feixin,renren,kaixin001,fanfou,instapaper,delicious,email&ssc=false\"></script>                                       \n" +
                "<script type=\"text/javascript\" charset=\"utf-8\">\n" +
                "bShare.addEntry({\n" +
                "    title: document.getElementById(\"page-title\").innerHTML,\n" +
                "url:window.location.href\n" +
                "});\n" +
                "</script></div>\n" +
                "<script>\n" +
                "document.getElementById(\"share_button\").innerHTML=document.getElementById(\"share_button_proto\").innerHTML;\n" +
                "</script>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
        Html html = new Html(text);
        Selectable sc = html.sc();
        System.out.println(sc);
    }

    @Test
    public void test2(){
                String text = "\n" +
                        "\n" +
                        "\n" +
                        "<!DOCTYPE html>\n" +
                        "<html lang=\"zh-CN\" class=\"ua-mac ua-webkit book-new-nav\">\n" +
                        "<head>\n" +
                        "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
                        "  <title>地球上最后的夜晚 (豆瓣)</title>\n" +
                        "  \n" +
                        "\n" +
                        "\n" +
                        "  \n" +
                        "<script>!function(f){var h=function(o,n,m){var k=new Date(),j,l;n=n||30;m=m||\"/\";k.setTime(k.getTime()+(n*24*60*60*1000));j=\"; expires=\"+k.toGMTString();for(l in o){f.cookie=l+\"=\"+o[l]+j+\"; path=\"+m}},d=function(m){var l=m+\"=\",o,n,j,k=f.cookie.split(\";\");for(n=0,j=k.length;n<j;n++){o=k[n].replace(/^\\s+|\\s+$/g,\"\");if(o.indexOf(l)==0){return o.substring(l.length,o.length).replace(/\\\"/g,\"\")}}return null},e=f.write,b={\"douban.com\":1,\"douban.fm\":1,\"google.com\":1,\"google.cn\":1,\"googleapis.com\":1,\"gmaptiles.co.kr\":1,\"gstatic.com\":1,\"gstatic.cn\":1,\"google-analytics.com\":1,\"googleadservices.com\":1},a=function(l,k){var j=new Image();j.onload=function(){};j.src=\"http://www.douban.com/j/except_report?kind=ra022&reason=\"+encodeURIComponent(l)+\"&environment=\"+encodeURIComponent(k)},i=function(k){try{e.call(f,k)}catch(j){e(k)}},c=/<script.*?src\\=[\"']?([^\"'\\s>]+)/ig,g=/http:\\/\\/(.+?)\\.([^\\/]+).+/i;f.writeln=f.write=function(k){var j=c.exec(k),l;if(!j){i(k);return}l=g.exec(j[1]);if(!l){i(k);return}if(b[l[2]]){i(k);return}if(d(\"hj\")===\"tqs\"){return}a(j[1],location.href);h({hj:\"tqs\"},1);setTimeout(function(){location.replace(location.href)},50)}}(document);</script>\n" +
                        "\n" +
                        "  \n" +
                        "  <meta http-equiv=\"Pragma\" content=\"no-cache\">\n" +
                        "  <meta http-equiv=\"Expires\" content=\"Sun, 6 Mar 2005 01:00:00 GMT\">\n" +
                        "  \n" +
                        "<meta http-equiv=\"mobile-agent\" content=\"format=xhtml; url=http://m.douban.com/book/subject/20501761/\">\n" +
                        "<meta name=\"keywords\" content=\"地球上最后的夜晚,[智利] 罗贝托·波拉尼奥,上海人民出版社,2013-4-1,简介,作者,书评,论坛,推荐,二手\">\n" +
                        "<meta name=\"description\" content=\"图书地球上最后的夜晚 介绍、书评、论坛及推荐 \">\n" +
                        "\n" +
                        "  <script>var _head_start = new Date();</script>\n" +
                        "  \n" +
                        "  <link href=\"http://img3.douban.com/css/book/packed_master5149446613.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
                        "\n" +
                        "  <style type=\"text/css\"></style>\n" +
                        "  <script src=\"http://img3.douban.com/js/book/lib/jquery/packed_jquery7529595477.js\"></script>\n" +
                        "  <script src=\"http://img3.douban.com/js/book/packed_master2876199327.js\"></script>\n" +
                        "  \n" +
                        "\n" +
                        "  \n" +
                        "  <link rel=\"stylesheet\" href=\"http://img3.douban.com/css/book/packed_subject354916977.css\">\n" +
                        "  <script src=\"http://img3.douban.com/js/lib/packed_jquery.snippet3033262609.js\"></script>\n" +
                        "  <script src=\"http://img3.douban.com/js/ui/packed_dialog3992230463.js\"></script>\n" +
                        "    <link rel=\"alternate\" href=\"http://book.douban.com/feed/subject/20501761/reviews\" type=\"application/rss+xml\" title=\"RSS\">\n" +
                        "  <style type=\"text/css\"> h2 {color: #007722;} </style>\n" +
                        "\n" +
                        "  <script>  </script>\n" +
                        "  <link rel=\"stylesheet\" href=\"http://img3.douban.com/misc/mixed_static/2cdd7afb27b53949.css\">\n" +
                        "\n" +
                        "  <link rel=\"shortcut icon\" href=\"http://img3.douban.com/favicon.ico\" type=\"image/x-icon\">\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "  \n" +
                        "    <script type=\"text/javascript\">var _body_start = new Date();</script>\n" +
                        "    \n" +
                        "  \n" +
                        "  \n" +
                        "  \n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "<div id=\"db-global-nav\" class=\"global-nav\">\n" +
                        "  <div class=\"bd\">\n" +
                        "    \n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "<div class=\"top-nav-info\">\n" +
                        "    <span class=\"perf-metric\"><!-- _performtips_ --></span>\n" +
                        "    \n" +
                        "     <ul>\n" +
                        "       <li><a href=\"http://www.douban.com/doumail/\">豆邮<em>(3)</em></a></li>\n" +
                        "       <li class=\"nav-user-account\">\n" +
                        "       <a target=\"_blank\" href=\"http://www.douban.com/accounts/\" class=\"bn-more\"><span>黄亿华的帐号</span><span class=\"arrow\"></span></a>\n" +
                        "       <div class=\"more-items\">\n" +
                        "      <table cellpadding=\"0\" cellspacing=\"0\">\n" +
                        "            <tr><td><a href=\"http://www.douban.com/people/flashsword20/\">我的豆瓣</a></td></tr>\n" +
                        "            <tr><td><a target=\"_blank\" href=\"http://www.douban.com/accounts/\">我的帐号</a></td></tr>\n" +
                        "            <tr><td><a href=\"http://www.douban.com/accounts/logout?source=book&amp;ck=OFto\">退出</a></td></tr>\n" +
                        "         </table>\n" +
                        "       </div>\n" +
                        "\n" +
                        "       </li>\n" +
                        "     </ul>\n" +
                        "</div>\n" +
                        "\n" +
                        "\n" +
                        "    <div class=\"top-nav-reminder\" >\n" +
                        "        <a href=\"http://www.douban.com/notification/\" class=\"lnk-remind\">提醒\n" +
                        "        </a>\n" +
                        "    <div id=\"top-nav-notimenu\" class=\"more-items\">\n" +
                        "      <div class=\"bd\">\n" +
                        "          <p>加载中...</p>\n" +
                        "      </div>\n" +
                        "    </div>\n" +
                        "    </div>\n" +
                        "    \n" +
                        "\n" +
                        "    <div class=\"global-nav-items\">\n" +
                        "      <ul>\n" +
                        "          \n" +
                        "            \n" +
                        "            <li>\n" +
                        "              <a href=\"http://www.douban.com/\">豆瓣</a>\n" +
                        "            </li>\n" +
                        "          \n" +
                        "            \n" +
                        "            <li class=\"on\">\n" +
                        "              <a href=\"http://book.douban.com/\">读书</a>\n" +
                        "            </li>\n" +
                        "          \n" +
                        "            \n" +
                        "            <li>\n" +
                        "              <a href=\"http://movie.douban.com/\">电影</a>\n" +
                        "            </li>\n" +
                        "          \n" +
                        "            \n" +
                        "            <li>\n" +
                        "              <a href=\"http://music.douban.com/\">音乐</a>\n" +
                        "            </li>\n" +
                        "          \n" +
                        "            \n" +
                        "            <li>\n" +
                        "              <a href=\"http://www.douban.com/location/\">同城</a>\n" +
                        "            </li>\n" +
                        "          \n" +
                        "            \n" +
                        "            <li>\n" +
                        "              <a href=\"http://www.douban.com/group/\">小组</a>\n" +
                        "            </li>\n" +
                        "          \n" +
                        "            \n" +
                        "            <li>\n" +
                        "              <a href=\"http://read.douban.com/?dcs=top-nav&amp;dcm=douban\" target=\"_blank\">阅读</a>\n" +
                        "            </li>\n" +
                        "          \n" +
                        "            \n" +
                        "            <li>\n" +
                        "              <a href=\"http://douban.fm/\" target=\"_blank\">豆瓣FM</a>\n" +
                        "            </li>\n" +
                        "          \n" +
                        "            <li>\n" +
                        "              <a href=\"#more\" class=\"bn-more\"><span>更多</span></a>\n" +
                        "              <div class=\"more-items\">\n" +
                        "                <table cellpadding=\"0\" cellspacing=\"0\">\n" +
                        "                    \n" +
                        "                    <tr><td><a href=\"http://9.douban.com\" target=\"_blank\">九点</a></td></tr>\n" +
                        "                    \n" +
                        "                    <tr><td><a href=\"http://alphatown.com\" target=\"_blank\">阿尔法城</a></td></tr>\n" +
                        "                    \n" +
                        "                    <tr><td><a href=\"http://www.douban.com/mobile/\" target=\"_blank\">移动应用</a></td></tr>\n" +
                        "                </table>\n" +
                        "              </div>\n" +
                        "            </li>\n" +
                        "      </ul>\n" +
                        "    </div>\n" +
                        "  </div>\n" +
                        "</div>\n" +
                        "\n" +
                        "\n" +
                        "  \n" +
                        "    \n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "<div id=\"db-nav-book\" class=\"nav\">\n" +
                        "  <div class=\"nav-wrap\">\n" +
                        "  <div class=\"nav-primary\">\n" +
                        "    <div class=\"nav-logo\">\n" +
                        "      <a href=\"http://book.douban.com\">豆瓣读书</a>\n" +
                        "    </div>\n" +
                        "    <div class=\"nav-search\">\n" +
                        "      <form action=\"http://book.douban.com/subject_search\" method=\"get\">\n" +
                        "        <fieldset>\n" +
                        "          <legend>搜索：</legend>\n" +
                        "          <label for=\"inp-query\">书名、作者、ISBN</label>\n" +
                        "          <div class=\"inp\"><input id=\"inp-query\" name=\"search_text\" size=\"22\" maxlength=\"60\" value=\"\"></div>\n" +
                        "          <div class=\"inp-btn\"><input type=\"submit\" value=\"搜索\"></div>\n" +
                        "          <input type=\"hidden\" name=\"cat\" value=\"1001\" />\n" +
                        "        </fieldset>\n" +
                        "      </form>\n" +
                        "    </div>\n" +
                        "\n" +
                        "  </div>\n" +
                        "  </div>\n" +
                        "  <div class=\"nav-secondary\">\n" +
                        "    <ul class=\"nav-items\">\n" +
                        "        <li class=\"nav-item-first\"><a href=\"http://book.douban.com/mine\">我读</a></li>\n" +
                        "        <li><a href=\"http://book.douban.com/updates\">动态</a></li>\n" +
                        "        <li><a href=\"http://book.douban.com/recommended\">豆瓣猜</a></li>\n" +
                        "      <li\n" +
                        "      ><a href=\"http://book.douban.com/tag/\">分类浏览</a></li>\n" +
                        "      <li><a href=\"http://book.douban.com/writers/\">作者</a></li>\n" +
                        "      \n" +
                        "      <li><a href=\"http://read.douban.com?dcs=book-nav&amp;dcm=douban\" target=\"_blank\">阅读</a></li>\n" +
                        "      \n" +
                        "      <li class=\"book-cart\"><a href=\"http://book.douban.com/cart\">购书单</a></li>\n" +
                        "    </ul>\n" +
                        "  </div>\n" +
                        "</div>\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "  \n" +
                        "  <script type=\"text/template\" id=\"suggest-item\">\n" +
                        "    <li class=\"item\"><a href=\"#\">{{= item}}</a></li>\n" +
                        "  </script>\n" +
                        "  \n" +
                        "  \n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "    <div id=\"wrapper\">\n" +
                        "        \n" +
                        "<h1>\n" +
                        "    <span property=\"v:itemreviewed\">地球上最后的夜晚</span>\n" +
                        "    <div class=\"clear\"></div>\n" +
                        "</h1>\n" +
                        "\n" +
                        "        \n" +
                        "  <div id=\"content\">\n" +
                        "    \n" +
                        "    <div class=\"grid-16-8 clearfix\">\n" +
                        "      \n" +
                        "      <div class=\"article\">\n" +
                        "<div class=\"indent\">\n" +
                        "  <div class=\"subjectwrap clearfix\">\n" +
                        "    \n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "<div class=\"subject clearfix\">\n" +
                        "<div id=\"mainpic\" class=\"\">\n" +
                        "\n" +
                        "  \n" +
                        "\n" +
                        "  <a class=\"nbg\" \n" +
                        "      href=\"http://img3.douban.com/lpic/s26258459.jpg\" title=\"&lt;bound method Book.seo_title of &lt;Book(20501761, ), title: 地球上最后的夜晚, uid: 7208112029, cat_id: 1001&gt;&gt;\">\n" +
                        "    <img src=\"http://img3.douban.com/mpic/s26258459.jpg\" title=\"点击看大图\" alt=\"地球上最后的夜晚\" rel=\"v:photo\">\n" +
                        "  </a>\n" +
                        "\n" +
                        "    <br>\n" +
                        "      <p class=\"gact\">\n" +
                        "        <a href=\"http://book.douban.com/subject/20501761/edit\">更新描述或封面</a>\n" +
                        "      </p>\n" +
                        "\n" +
                        "\n" +
                        "</div>\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "<div id=\"info\" class=\"\">\n" +
                        "\n" +
                        "\n" +
                        "    \n" +
                        "    \n" +
                        "  \n" +
                        "\n" +
                        "    \n" +
                        "    \n" +
                        "  \n" +
                        "    <span class=\"pl\">原作名:</span> Last Evenings on Earth<br/>\n" +
                        "\n" +
                        "    \n" +
                        "    \n" +
                        "  \n" +
                        "    <span>\n" +
                        "      <span class=\"pl\"> 作者</span>: \n" +
                        "        \n" +
                        "        <a href=\"/search/%5B%E6%99%BA%E5%88%A9%5D%20%E7%BD%97%E8%B4%9D%E6%89%98%C2%B7%E6%B3%A2%E6%8B%89%E5%B0%BC%E5%A5%A5\">[智利] 罗贝托·波拉尼奥</a>\n" +
                        "    </span><br/>\n" +
                        "\n" +
                        "    \n" +
                        "    \n" +
                        "  \n" +
                        "    <span>\n" +
                        "      <span class=\"pl\"> 译者</span>: \n" +
                        "        \n" +
                        "        <a href=\"/search/%E8%B5%B5%E5%BE%B7%E6%98%8E\">赵德明</a>\n" +
                        "    </span><br/>\n" +
                        "\n" +
                        "    \n" +
                        "    \n" +
                        "  \n" +
                        "    <span class=\"pl\">出版社:</span> 上海人民出版社<br/>\n" +
                        "\n" +
                        "    \n" +
                        "    \n" +
                        "  \n" +
                        "    <span class=\"pl\">出版年:</span> 2013-4-1<br/>\n" +
                        "\n" +
                        "    \n" +
                        "    \n" +
                        "  \n" +
                        "    <span class=\"pl\">页数:</span> 288<br/>\n" +
                        "\n" +
                        "    \n" +
                        "    \n" +
                        "  \n" +
                        "    <span class=\"pl\">定价:</span> 45.00元<br/>\n" +
                        "\n" +
                        "    \n" +
                        "    \n" +
                        "  \n" +
                        "\n" +
                        "    \n" +
                        "    \n" +
                        "  \n" +
                        "    <span class=\"pl\">丛书:</span>&nbsp;<a href=\"http://book.douban.com/series/16633\">罗贝托·波拉尼奥作品系列</a><br>\n" +
                        "\n" +
                        "    \n" +
                        "    \n" +
                        "  \n" +
                        "    \n" +
                        "      \n" +
                        "      <span class=\"pl\">ISBN:</span> 9787208112025<br/>\n" +
                        "\n" +
                        "\n" +
                        "</div>\n" +
                        "\n" +
                        "</div>\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "    \n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "<div id=\"interest_sectl\" class=\"\">\n" +
                        "  <div class=\"rating_wrap\" rel=\"v:rating\">\n" +
                        "    <p class=\"rating_self clearfix\" typeof=\"v:Rating\">\n" +
                        "      <span class=\"ll bigstar45\"></span>\n" +
                        "\n" +
                        "\n" +
                        "      <strong class=\"ll rating_num \" property=\"v:average\">\n" +
                        "      8.4\n" +
                        "      </strong>\n" +
                        "\n" +
                        "      <span property=\"v:best\" content=\"10.0\"></span>\n" +
                        "    </p>\n" +
                        "    <p class=\"rating_self font_normal clearbox\">\n" +
                        "    (\n" +
                        "    <span class=\"\">\n" +
                        "      <a href=\"collections\"><span property=\"v:votes\">11</span>人评价</a>\n" +
                        "    </span>\n" +
                        "    )\n" +
                        "  </p>\n" +
                        "    \n" +
                        "    \n" +
                        "<span class=\"stars5 starstop\" title=\"力荐\"></span>\n" +
                        "\n" +
                        "    \n" +
                        "<div class=\"power\" style=\"width:50px\"></div>\n" +
                        "\n" +
                        "    45.5%<br>\n" +
                        "    \n" +
                        "    \n" +
                        "<span class=\"stars4 starstop\" title=\"推荐\"></span>\n" +
                        "\n" +
                        "    \n" +
                        "<div class=\"power\" style=\"width:10px\"></div>\n" +
                        "\n" +
                        "    9.1%<br>\n" +
                        "    \n" +
                        "    \n" +
                        "<span class=\"stars3 starstop\" title=\"还行\"></span>\n" +
                        "\n" +
                        "    \n" +
                        "<div class=\"power\" style=\"width:20px\"></div>\n" +
                        "\n" +
                        "    18.2%<br>\n" +
                        "    \n" +
                        "    \n" +
                        "<span class=\"stars2 starstop\" title=\"较差\"></span>\n" +
                        "\n" +
                        "    \n" +
                        "<div class=\"power\" style=\"width:20px\"></div>\n" +
                        "\n" +
                        "    18.2%<br>\n" +
                        "    \n" +
                        "    \n" +
                        "<span class=\"stars1 starstop\" title=\"很差\"></span>\n" +
                        "\n" +
                        "    \n" +
                        "<div class=\"power\" style=\"width:10px\"></div>\n" +
                        "\n" +
                        "    9.1%<br>\n" +
                        "</div>\n" +
                        "</div>\n" +
                        "\n" +
                        "\n" +
                        "  </div>\n" +
                        "  \n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "  \n" +
                        "    \n" +
                        "    <div id=\"interest_sect_level\" class=\"clearfix\">\n" +
                        "        <a href=\"http://book.douban.com/subject/20501761/?interest=wish&amp;ck=OFto\" rel=\"nofollow\" class=\"collect_btn colbutt ll\" name=\"pbtn-20501761-wish\">\n" +
                        "          <span>想读</span>\n" +
                        "        </a>\n" +
                        "        <a href=\"http://book.douban.com/subject/20501761/?interest=do&amp;ck=OFto\" rel=\"nofollow\" class=\"collect_btn colbutt ll\" name=\"pbtn-20501761-do\">\n" +
                        "          <span>在读</span>\n" +
                        "        </a>\n" +
                        "        <a href=\"http://book.douban.com/subject/20501761/?interest=collect&amp;ck=OFto\" rel=\"nofollow\" class=\"collect_btn colbutt ll\" name=\"pbtn-20501761-collect\">\n" +
                        "          <span>读过</span>\n" +
                        "        </a>\n" +
                        "      <div class=\"ll j a_stars\">\n" +
                        "        \n" +
                        "\n" +
                        "<span class=\"j a_stars\">\n" +
                        "  <span class=\"rate_stars\">\n" +
                        "    评价: \n" +
                        "    <span id=\"rating\">\n" +
                        "      <span id=\"stars\"><a href=\"javascript:void(0)\" class=\"j a_collect_btn\" name=\"pbtn-20501761-collect-1\"><img src=\"http://img3.douban.com/pics/nst.gif\" id=\"star1\" /></a><a href=\"javascript:void(0)\" class=\"j a_collect_btn\" name=\"pbtn-20501761-collect-2\"><img src=\"http://img3.douban.com/pics/nst.gif\" id=\"star2\" /></a><a href=\"javascript:void(0)\" class=\"j a_collect_btn\" name=\"pbtn-20501761-collect-3\"><img src=\"http://img3.douban.com/pics/nst.gif\" id=\"star3\" /></a><a href=\"javascript:void(0)\" class=\"j a_collect_btn\" name=\"pbtn-20501761-collect-4\"><img src=\"http://img3.douban.com/pics/nst.gif\" id=\"star4\" /></a><a href=\"javascript:void(0)\" class=\"j a_collect_btn\" name=\"pbtn-20501761-collect-5\"><img src=\"http://img3.douban.com/pics/nst.gif\" id=\"star5\" /></a>      </span>\n" +
                        "      <span id=\"rateword\" class=\"pl\"></span>\n" +
                        "      <input id=\"n_rating\" type=\"hidden\" value=\"\"\n" +
                        "          />\n" +
                        "    </span>\n" +
                        "  </span>\n" +
                        "</span>\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "      </div>\n" +
                        "    </div>\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "  \n" +
                        "  <div class=\"gtleft\">\n" +
                        "    <ul class=\"ul_subject_menu bicelink color_gray pt6 clearfix\">\n" +
                        "        <li>\n" +
                        "          <img src=\"http://img3.douban.com/pics/add-review.gif\" />&nbsp;<a href=\"http://book.douban.com/annotation/write?sid=20501761\" rel=\"nofollow\">写笔记</a>\n" +
                        "        </li>\n" +
                        "\n" +
                        "        <li>\n" +
                        "          <img src=\"http://img3.douban.com/pics/add-review.gif\" />&nbsp;<a href=\"http://book.douban.com/subject/20501761/new_review\" rel=\"nofollow\">写书评</a>\n" +
                        "        </li>\n" +
                        "\n" +
                        "      <li>\n" +
                        "\n" +
                        "  <span class=\"rr\">\n" +
                        "  \n" +
                        "\n" +
                        "    <img src=\"http://img3.douban.com/pics/add-cart.gif\"/>\n" +
                        "      <a name=\"20501761\" class=\"j a_add2cart\" href=\"javascript:;\">加入购书单</a>\n" +
                        "  <span class=\"hidden\">已在<a href=\"http://book.douban.com/cart\">购书单</a></span>\n" +
                        "</span><br class=\"clearfix\" />\n" +
                        "</li>\n" +
                        "\n" +
                        "        <li>\n" +
                        "          \n" +
                        "\n" +
                        "\n" +
                        "<div class=\"doulist-add-btn\">\n" +
                        "    \n" +
                        "\n" +
                        "    \n" +
                        "\n" +
                        "    \n" +
                        "\n" +
                        "    <a href=\"http://www.douban.com/people/flashsword20/doulists/all?add=20501761&amp;cat=1001\" data-id=\"20501761\" data-cate=\"1001\" data-catename=\"图书\" class=\"lnk-doulist-add\"><i></i>添加到豆列</a>\n" +
                        "</div>\n" +
                        "\n" +
                        "        </li>\n" +
                        "\n" +
                        "        \n" +
                        "        \n" +
                        "    \n" +
                        "    <li class=\"rec\" id=\"C-20501761\">\n" +
                        "        <a href= \"#\" data-url=\"http://book.douban.com/subject/20501761/\" data-desc=\"\" data-title=\"书籍《地球上最后的夜晚》 (来自豆瓣) \" data-pic=\"http://img3.douban.com/lpic/s26258459.jpg\" class=\"bn-sharing \">分享到</a> &nbsp;&nbsp;\n" +
                        "    </li>\n" +
                        "    <script>\n" +
                        "    var cache_url = cache_url || {};\n" +
                        "    (function(u){ if(cache_url[u]){ return; } cache_url[u] = true; Do(function(){$.getScript(u);}); })('http://img3.douban.com/js/lib/packed_sharebutton8478494727.js');\n" +
                        "    </script>\n" +
                        "\n" +
                        "    </ul>\n" +
                        "  </div>\n" +
                        "\n" +
                        "\n" +
                        "    \n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "    <style type=\"text/css\">\n" +
                        "      .book-share-dialog{z-index:98}.book-share-dialog div.bd{padding:0}.book-share .form-bd{position:relative;padding:15px 15px 16px 15px}.book-share-with-media .form-bd{height:136px}.book-share-no-media .form-bd{height:140px}.book-share-dialog .share-text{height:115px;position:relative;z-index:1;background:transparent}.book-share-with-media .share-text{float:right;_display:inline;width:355px;margin-right:8px}.book-share-dialog .mentioned-highlighter{height:115px;padding:3px;color:white}.book-share-dialog .mentioned-highlighter b{color:#d2e1f3}.book-share-with-media .mentioned-highlighter{width:355px;top:16px;left:140px}.book-share-no-media .mentioned-highlighter{width:484px;top:16px;left:16px}.book-share-no-media .share-text{float:none;_display:block;font-size:14px;width:484px;margin-right:0}.book-share .form-ft{background:#e9eef2;height:25px;padding-top:10px;padding-bottom:10px}.book-share .form-ft-inner{height:25px;padding-left:15px;padding-right:15px}.book-share-dialog .dialog-only-text{text-align:center;font-size:14px;line-height:1.5;padding-top:30px;padding-bottom:30px;color:#0c7823}.book-share-dialog .ll{float:left;display:inline}.book-share-dialog .share-label{width:auto;display:inline;float:none}.book-share-dialog .leading-label{_vertical-align:-2px}.book-share-dialog .media{float:left;_display:inline}.book-share-dialog #sync-setting{_vertical-align:-5px;margin-left:10px}.book-share-dialog .server-error{position:absolute;bottom:0;*bottom:8px}.book-share-no-media .server-error{left:16px;bottom:5px}.book-share-with-media .server-error{left:140px}.book-share-dialog .avail-num-indicator{color:#aaa;font-weight:800;position:absolute;right:22px;bottom:8px}.book-share-dialog .error{color:red}.book-share-dialog .bottom-setting{width:432px}.book-share-dialog .input-checkbox{vertical-align:-2px;_vertical-align:-1px}.book-share-dialog #sync-setting img{_vertical-align:2px}.suggest-overlay{z-index:2000}\n" +
                        "      .suggestions-list li{position:relative;left:0;top:0;margin-bottom:7px;height:35px}.suggestions-list li .user-thumb{display:inline-block;*display:inline;float:left;margin:2px 5px 0 0;vertical-align:top}.suggestions-list li .user-thumb img{width:25px;height:25px}.suggestions-list li .user-name-info{display:inline-block;*display:inline;line-height:1.4em}.suggestions-list li .user-name-info .user-profile-link{color:#333;font-weight:800}.suggestions-list li .user-name-info .user-profile-link:hover{color:#4b8dc5}.suggestions-list li .user-name-info p{color:#999}.suggestions-list li .user-name-info b{color:#4b8dc5;font-weight:normal;cursor:pointer}.suggestions-list li .user-name-info b:hover{text-decoration:underline}.suggestions-list li .dismiss{position:absolute}.suggestions-list li .dismiss{color:#aaa;margin:0 0 0 10px;top:0;right:0}.suggestions-list li .dismiss:hover{color:#333;text-decoration:none}.suggest-overlay{position:absolute;z-index:99;width:auto;background:#fff;border:1px solid #c5c7d2;-moz-border-radius:3px;-webkit-border-radius:3px;border-radius:3px}.suggest-overlay .bd{min-width:220px;line-height:1;background:#fafafa;color:#b3b3b3;padding:5px;-moz-border-radius:3px;-webkit-border-radius:3px;border-radius:3px}.suggest-overlay ul{color:#333;padding:3px 0;min-width:214px}.suggest-overlay li{cursor:pointer;padding:3px 7px}.suggest-overlay li b{font-weight:bold}.suggest-overlay li span{color:#999}.suggest-overlay img{margin-right:5px;width:20px;height:20px;vertical-align:middle}.suggest-overlay .on{background:#e9f0f8}.mentioned-highlighter{font:14px/20px \"Helvetica Neue\",Helvetica,Arial,sans-serif;position:absolute;left:4px;top:4px;font-size:14px;height:60px;width:98.5%;overflow:hidden;background:#fff;white-space:pre-wrap;word-wrap:break-word;color:transparent}.mentioned-highlighter b{font-weight:normal;background-color:#d2e1f3;color:transparent;-moz-border-radius:2px;-webkit-border-radius:2px;border-radius:2px}\n" +
                        "    </style>\n" +
                        "    <script src=\"http://img3.douban.com/js/lib/packed_mustache5518868182.js\" type=\"text/javascript\"></script>\n" +
                        "    <script src=\"http://img3.douban.com/js/lib/packed_textarea-mention1109633934.js\" type=\"text/javascript\"></script>\n" +
                        "    <script src=\"http://img3.douban.com/js/book/packed_share1689383174.js\" type=\"text/javascript\"></script>\n" +
                        "\n" +
                        "<div class=\"rec-sec\">\n" +
                        "\n" +
                        "  <span class=\"rec\">\n" +
                        "  \n" +
                        "\n" +
                        "  <script id=\"book-share\" type=\"text/x-html-snippet\">\n" +
                        "      \n" +
                        "  \n" +
                        "\n" +
                        "  \n" +
                        "  \n" +
                        "  <form class=\"book-share book-share-with-media\" action=\"/j/subject/20501761/share\" method=\"POST\"><div style=\"display:none;\"><input type=\"hidden\" name=\"ck\" value=\"OFto\"/></div>\n" +
                        "    <div class=\"clearfix form-bd\">\n" +
                        "        <img class=\"media\" src=\"http://img3.douban.com/mpic/s26258459.jpg\"\n" +
                        "          width=\"108px\" height=\"136px\" />\n" +
                        "\n" +
                        "      <textarea name=\"text\" class=\"share-text\" cols=\"40\" placeholder=\"写点什么吧\"\n" +
                        "        data-mention-api=\"https://api.douban.com/shuo/in/complete?alt=xd&amp;callback=?\"></textarea>\n" +
                        "\n" +
                        "      <input type=\"hidden\" name=\"book_comment\" value=\"推荐图书《地球上最后的夜晚》\">\n" +
                        "      <input type=\"hidden\" name=\"comment_title\" value=\"推荐图书《地球上最后的夜晚》。\">\n" +
                        "      <p class=\"error server-error\">&nbsp;</p>\n" +
                        "      <span class=\"avail-num-indicator\">128</span>\n" +
                        "      <div class=\"mentioned-highlighter\"></div>\n" +
                        "    </div>\n" +
                        "    <div class=\"form-ft\">\n" +
                        "      <div class=\"form-ft-inner\">\n" +
                        "        \n" +
                        "\n" +
                        "\n" +
                        "<style>\n" +
                        ".bottom-setting{width:440px;*width:400px;float:left}\n" +
                        "#sync-setting:hover {background:none; color:#336699;}\n" +
                        "#sync-setting img{vertical-align:baseline;*vertical-align:middle;opacity:.5;filter:alpha(opacity=50);display:inline-block;width:10px;height:10px;*display:inline;zoom:1;position:relative;top:1px;margin-left:5px;}\n" +
                        "#sync-setting:hover img{opacity:.8;background:none;filter:alpha(opacity=80);}\n" +
                        "#sync-setting img{width:10px;height:10px;}\n" +
                        ".book-sns .private{float:none;}\n" +
                        ".share-label{margin:8px;vertical-align:middle;*vertical-align:middle;}\n" +
                        "</style>\n" +
                        "\n" +
                        "<div class=\"bottom-setting\">\n" +
                        "    <div class=\"ll\">\n" +
                        "        <span class=\"pl\">分享到</span>\n" +
                        "            <input name=\"share-shuo\" type=\"hidden\" value=\"on\" />\n" +
                        "\n" +
                        "        </label> \n" +
                        "    </div>\n" +
                        "\n" +
                        "      <a id=\"sync-setting\" href=\"http://book.douban.com/settings/sync\" target=\"_blank\"\n" +
                        "        class=\"pl share-label\"><img src=\"http://img3.douban.com/pics/fm/home/share_g.png\" \n" +
                        "          alt=\"去绑定新浪、腾讯等微博\" />去绑定新浪、腾讯等微博</a>\n" +
                        "</div>\n" +
                        "\n" +
                        "        <span class=\"bn-flat\">\n" +
                        "          <input type=\"submit\" value=\"确定\" />\n" +
                        "        </span>\n" +
                        "      </div>\n" +
                        "    </div>\n" +
                        "  </form>\n" +
                        "\n" +
                        "\n" +
                        "  </script>\n" +
                        "  \n" +
                        "<script id=\"suggest-mention-tmpl\" type=\"text/mustache-template\">\n" +
                        "  <ul>\n" +
                        "    {{#users}}\n" +
                        "    <li id=\"{{uid}}\"><img src=\"{{avatar}}\"\n" +
                        "      >{{{username}}}&nbsp;<span>({{{uid}}})</span></li>\n" +
                        "    {{/users}}\n" +
                        "  </ul>\n" +
                        "</script>\n" +
                        "\n" +
                        "\n" +
                        "    <a href=\"#\" class=\"lnk-sharing\" data-share-dialog=\"#book-share\"\n" +
                        "      data-dialog-title=\"推荐图书\">推荐</a>\n" +
                        "  </span>\n" +
                        "\n" +
                        "</div>\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "<script>\n" +
                        "  //bind events for collection button.\n" +
                        "  $('.collect_btn', '#interest_sect_level').each(function(){\n" +
                        "      Douban.init_collect_btn(this);\n" +
                        "  });\n" +
                        "</script>\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "</div>\n" +
                        "\n" +
                        "<br clear=\"all\">\n" +
                        "<div id=\"collect_form_20501761\"></div>\n" +
                        "<div class=\"related_info\">\n" +
                        "  \n" +
                        "  \n" +
                        "  \n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "  \n" +
                        "\n" +
                        "  <h2 class=\"\">\n" +
                        "\n" +
                        "    内容简介\n" +
                        "      &nbsp;&middot;&nbsp;&middot;&nbsp;&middot;&nbsp;&middot;&nbsp;&middot;&nbsp;&middot;\n" +
                        "\n" +
                        "  </h2>\n" +
                        "\n" +
                        "\n" +
                        "<div class=\"indent\" id=\"link-report\">\n" +
                        "  \n" +
                        "    <style type=\"text/css\" media=\"screen\">\n" +
                        ".intro p{text-indent:2em;}\n" +
                        "</style>\n" +
                        "<div class=\"intro\">\n" +
                        "    <p>◎《2666》作者波拉尼奥首部短篇小说集</p>    <p>◎令人着迷、苦寻的答案。挫败，但并非失败的旅程。</p>    <p>◎《2666》屡获2012年各项读书大奖：深圳读书月年度十大好书；《中华读书报》年度十大好书，是惟一入选的外国小说；《中国图书商报》年度影响力图书；入围新浪读书年度十大好书等。</p>    <p>◎波拉尼奥在短篇小说领域展现出来的纯熟技巧，堪与卡夫卡和博尔赫斯媲美。</p>    <p>——————————————————————</p>    <p>《地球上最后的夜晚》由十四个故事组成，大部分故事的主人公是“B”：一个智利流亡者，在南美和欧洲漫无目的地游荡，串起了他同时代的其他人的故事，几乎都是在流亡生活中理想破灭的一代人，如何挣扎于边缘，困于梦魇。这些人犹如在一场梦中，在不同的故事中不断改换着形象、名字或背景。评论家们普遍认为，波拉尼奥在短篇小说领域展现出来的纯熟技巧，堪与卡夫卡和博尔赫斯媲美。</p></div>\n" +
                        "\n" +
                        "    \n" +
                        "<style>\n" +
                        "    #link-report .report { text-align: right; font-size: 12px; visibility: hidden; }\n" +
                        "    #link-report .report a { color: #BBB; }\n" +
                        "    #link-report .report a:hover { color: #FFF; background-color: #BBB; }\n" +
                        "</style>\n" +
                        "<script>\n" +
                        "    Do = (typeof Do === 'undefined')? $ : Do;\n" +
                        "    Do(function(){\n" +
                        "    $(\"body\").delegate(\"#link-report\", 'mouseenter mouseleave', function(e){\n" +
                        "      switch (e.type) {\n" +
                        "        case \"mouseenter\":\n" +
                        "          $(this).find(\".report\").css('visibility', 'visible');\n" +
                        "          break;\n" +
                        "        case \"mouseleave\":\n" +
                        "          $(this).find(\".report\").css('visibility', 'hidden');\n" +
                        "          break;\n" +
                        "      }\n" +
                        "    });\n" +
                        "    $(\"#link-report\").delegate(\".report a\", 'click', function(e){\n" +
                        "        e.preventDefault();\n" +
                        "        var auditUrl = \"http://www.douban.com/misc/audit_report?url=\",\n" +
                        "        opt = \"\";\n" +
                        "        var obj = $(e.target).closest('#link-report');\n" +
                        "        var id = obj.length != 0 ? obj.data(\"id\") : undefined;\n" +
                        "        var params = (opt&&id) ? '?'.concat(opt, '=', id) : '';\n" +
                        "        var url = auditUrl.concat(\"http://book.douban.com/book/subject/20501761/\", params);\n" +
                        "        window.location.href = url;\n" +
                        "    });\n" +
                        "\n" +
                        "    $(\"#link-report\").append('<div class=\"report\"><a rel=\"nofollow\" href=\"#\">举报</a></div>');\n" +
                        "  });\n" +
                        "</script>\n" +
                        "\n" +
                        "</div>\n" +
                        "\n" +
                        "  \n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "    \n" +
                        "  \n" +
                        "\n" +
                        "  <h2 class=\"\">\n" +
                        "\n" +
                        "    作者简介\n" +
                        "      &nbsp;&middot;&nbsp;&middot;&nbsp;&middot;&nbsp;&middot;&nbsp;&middot;&nbsp;&middot;\n" +
                        "\n" +
                        "  </h2>\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "      <div class=\"indent\">\n" +
                        "          \n" +
                        "            <span class=\"short\">\n" +
                        "                <style type=\"text/css\" media=\"screen\">\n" +
                        ".intro p{text-indent:2em;}\n" +
                        "</style>\n" +
                        "<div class=\"intro\">\n" +
                        "    <p>罗贝托•波拉尼奥（Roberto Bolaño，1953—2003）出生于智利，父亲是卡车司机和业余拳击手，母亲在学校教授数学和统计学。1968年全家移居墨西哥。1973年波拉尼奥再次回到智利投身社会主义革命却遭到逮捕，差点被杀害。逃回墨西哥后他和好友推动了融合超现实主义、达达主义以及街头剧场的“现实以下主义”（Infrarealism）运动，意图激发拉丁美洲年轻人对生活与文学的热爱。1977年他前往欧洲，最后在西班牙波拉瓦海岸结婚定居。2003年因为肝脏功能损坏，等不到器官移植而在巴塞罗那去世，年仅五十岁。</p>    <p>波拉尼奥四十岁才开始写小说，作品数量却十分惊人，身后留下十部小说、四部短篇小说集以及三部诗集。1998年出版的《荒野侦探》在拉美文坛引起的轰动，不亚于三十年前《百年孤独》出版时的盛况。而其身后出版的《2666》更是引发欧美舆论压倒性好评，均致以...</p><p><a href=\"javascript:void(0)\" class=\"j a_show_full\">(展开全部)</a></p></div>\n" +
                        "\n" +
                        "            </span>\n" +
                        "            <span class=\"all hidden\">\n" +
                        "                <style type=\"text/css\" media=\"screen\">\n" +
                        ".intro p{text-indent:2em;}\n" +
                        "</style>\n" +
                        "<div class=\"intro\">\n" +
                        "    <p>罗贝托•波拉尼奥（Roberto Bolaño，1953—2003）出生于智利，父亲是卡车司机和业余拳击手，母亲在学校教授数学和统计学。1968年全家移居墨西哥。1973年波拉尼奥再次回到智利投身社会主义革命却遭到逮捕，差点被杀害。逃回墨西哥后他和好友推动了融合超现实主义、达达主义以及街头剧场的“现实以下主义”（Infrarealism）运动，意图激发拉丁美洲年轻人对生活与文学的热爱。1977年他前往欧洲，最后在西班牙波拉瓦海岸结婚定居。2003年因为肝脏功能损坏，等不到器官移植而在巴塞罗那去世，年仅五十岁。</p>    <p>波拉尼奥四十岁才开始写小说，作品数量却十分惊人，身后留下十部小说、四部短篇小说集以及三部诗集。1998年出版的《荒野侦探》在拉美文坛引起的轰动，不亚于三十年前《百年孤独》出版时的盛况。而其身后出版的《2666》更是引发欧美舆论压倒性好评，均致以杰作、伟大、里程碑、天才等等赞誉。苏珊•桑塔格、约翰•班维尔、科尔姆•托宾、斯蒂芬•金等众多作家对波拉尼奥赞赏有加，更有评论认为此书的出版自此将作者带至塞万提斯，斯特恩，梅尔维尔，普鲁斯特，穆齐尔与品钦的同一队列。</p></div>\n" +
                        "\n" +
                        "            </span>\n" +
                        "      </div>\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "  \n" +
                        "\n" +
                        "  \n" +
                        "    \n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "  \n" +
                        "\n" +
                        "  <h2 class=\"\">\n" +
                        "\n" +
                        "    目录\n" +
                        "      &nbsp;&middot;&nbsp;&middot;&nbsp;&middot;&nbsp;&middot;&nbsp;&middot;&nbsp;&middot;\n" +
                        "\n" +
                        "  </h2>\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "<div class=\"indent\" id=\"dir_20501761_short\">\n" +
                        "        圣西尼……………………………………3<br/>\n" +
                        "        亨利·西蒙·勒普兰斯…………………… 27<br/>\n" +
                        "        恩里克·马丁……………………………39<br/>\n" +
                        "        一件文学奇事…………………… ……59<br/>\n" +
                        "        通话…………………… ………………75<br/>\n" +
                        "        毛毛虫…………………………………83<br/>\n" +
                        "    · · · · · ·\n" +
                        "    (<a href=\"javascript:$('#dir_20501761_short').hide();$('#dir_20501761_full').show();$.get('/j/subject/j_dir_count',{id:20501761});void(0);\">更多</a>)\n" +
                        "</div>\n" +
                        "\n" +
                        "<div class=\"indent\" id=\"dir_20501761_full\" style=\"display:none\">\n" +
                        "        圣西尼……………………………………3<br/>\n" +
                        "        亨利·西蒙·勒普兰斯…………………… 27<br/>\n" +
                        "        恩里克·马丁……………………………39<br/>\n" +
                        "        一件文学奇事…………………… ……59<br/>\n" +
                        "        通话…………………… ………………75<br/>\n" +
                        "        毛毛虫…………………………………83<br/>\n" +
                        "        安妮·穆尔的生平 ……………………101<br/>\n" +
                        "        “小眼”席尔瓦 ………………………139<br/>\n" +
                        "        戈麦斯帕拉西奥 ……………………159<br/>\n" +
                        "        地球上最后的夜晚………………… 173<br/>\n" +
                        "        1978 年的几天………………………205<br/>\n" +
                        "        在法国和比利时闲逛…………………225<br/>\n" +
                        "        牙科医生…………………… ………245<br/>\n" +
                        "        邀舞卡……………………………… 273<br/>\n" +
                        "     · · · · · ·     (<a href=\"javascript:$('#dir_20501761_full').hide();$('#dir_20501761_short').show();void(0);\">收起</a>)\n" +
                        "</div>\n" +
                        "\n" +
                        "\n" +
                        "    \n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "  \n" +
                        "\n" +
                        "  <h2 class=\"\">\n" +
                        "\n" +
                        "    &#34;地球上最后的夜晚&#34;试读\n" +
                        "      &nbsp;&middot;&nbsp;&middot;&nbsp;&middot;&nbsp;&middot;&nbsp;&middot;&nbsp;&middot;\n" +
                        "\n" +
                        "  </h2>\n" +
                        "\n" +
                        "\n" +
                        "<div class=\"indent\">\n" +
                        "<p>情况是这样的：B 和B 父去阿卡普尔科度假。一大早，清晨六点，父子俩就要出发。那天夜里，B 睡在父亲家里。没梦，或者就算有梦，一睁眼也忘了。听见父亲在卫生间。向窗外望去，一片漆黑。B 不开灯，穿衣裳。等走出卧室的时候，父亲已经在桌旁看前一天的体育报纸了。早饭已经做好了。咖啡，牧场煎蛋。B 问候父亲后，走进卫生间。\n" +
                        "B 父的汽车是1970 年的福特野马。六点半，父子俩上车，开..</p>\n" +
                        "<ul class=\"col2-list clearfix\">\n" +
                        "\n" +
                        "<li>\n" +
                        "    <a href=\"http://book.douban.com/reading/25695704/\">地球上最后的夜晚</a>\n" +
                        "</li>\n" +
                        "</ul>\n" +
                        "<div align=\"right\">  · · · · · ·    (<a href=\"http://book.douban.com/subject/20501761/reading/\">查看全部试读</a>)</div>\n" +
                        "</div>\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "  \n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "<div id=\"db-tags-section\" class=\"blank20\">\n" +
                        "  \n" +
                        "  \n" +
                        "\n" +
                        "  <h2 class=\"\">\n" +
                        "\n" +
                        "    豆瓣成员常用的标签(共38个)\n" +
                        "      &nbsp;&middot;&nbsp;&middot;&nbsp;&middot;&nbsp;&middot;&nbsp;&middot;&nbsp;&middot;\n" +
                        "\n" +
                        "  </h2>\n" +
                        "\n" +
                        "\n" +
                        "  <div class=\"indent\">      <a class=\"\" href=\"http://book.douban.com/tag/%E7%BD%97%E8%B4%9D%E6%89%98-%E6%B3%A2%E6%8B%89%E5%B0%BC%E5%A5%A5\">罗贝托-波拉尼奥</a>(68) &nbsp;      <a class=\"\" href=\"http://book.douban.com/tag/%E6%8B%89%E7%BE%8E%E6%96%87%E5%AD%A6\">拉美文学</a>(35) &nbsp;      <a class=\"\" href=\"http://book.douban.com/tag/%E5%A4%96%E5%9B%BD%E6%96%87%E5%AD%A6\">外国文学</a>(24) &nbsp;      <a class=\"\" href=\"http://book.douban.com/tag/%E5%B0%8F%E8%AF%B4\">小说</a>(22) &nbsp;      <a class=\"\" href=\"http://book.douban.com/tag/%E6%99%BA%E5%88%A9%E6%96%87%E5%AD%A6\">智利文学</a>(14) &nbsp;      <a class=\"\" href=\"http://book.douban.com/tag/%E6%B3%A2%E6%8B%89%E5%B0%BC%E5%A5%A5\">波拉尼奥</a>(10) &nbsp;      <a class=\"\" href=\"http://book.douban.com/tag/%E6%99%BA%E5%88%A9\">智利</a>(10) &nbsp;      <a class=\"\" href=\"http://book.douban.com/tag/%E5%B0%8F%E8%AF%B4%E9%9B%86\">小说集</a>(10) &nbsp;  </div>\n" +
                        "</div>\n" +
                        "\n" +
                        "\n" +
                        "  \n" +
                        "\n" +
                        "\n" +
                        "<div class=\"subject_show block5\">\n" +
                        "<h2>丛书信息</h2>\n" +
                        "<div>\n" +
                        "　　<a href=\"http://book.douban.com/series/16633\">罗贝托·波拉尼奥作品系列 (共6册)</a>,\n" +
                        "这套丛书还有\n" +
                        "《2666》,《荒野侦探》,《2666》,《荒野侦探》,《护身符》。</div>\n" +
                        "</div>\n" +
                        "<script>\n" +
                        "$(function(){$(\".knnlike a\").click(function(){return moreurl(this,{'from':'knnlike'})})})\n" +
                        "</script>\n" +
                        "\n" +
                        "  \n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "  \n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "  <div id=\"reviews\" class=\"ugc-mod\">\n" +
                        "        <h2 class=\"clearfix\">\n" +
                        "          书评&nbsp;&nbsp;· · · · · ·&nbsp;\n" +
                        "        </h2>\n" +
                        "        \n" +
                        "      <a class=\"redbutt rr\" href=\"http://book.douban.com/subject/20501761/new_review\" rel=\"nofollow\"><span>我来评论这本书</span></a>\n" +
                        "\n" +
                        "\n" +
                        "      <ul id=\"comment_tab\" class=\"inline-tabs\">\n" +
                        "        <li id=\"t_0\" class=\"on\">\n" +
                        "          <a href=\"javascript:void(0)\" onclick=\"return switch_tab('t_0')\">热门评论</a>\n" +
                        "        </li>\n" +
                        "        <li id=\"t_1\">\n" +
                        "          <a href=\"javascript:void(0)\" onclick=\"return switch_tab('t_1')\">最新评论</a>\n" +
                        "        </li>\n" +
                        "      </ul>\n" +
                        "\n" +
                        "      \n" +
                        "\n" +
                        "      <div id=\"wt_0\" class=\"\">\n" +
                        "          \n" +
                        "    \n" +
                        "        <div class='ctsh'>\n" +
                        "    <div class=\"tlst\">\n" +
                        "        <div class=\"ilst\">\n" +
                        "                \n" +
                        "    <a href=\"http://book.douban.com/people/4364899/\" title=\"DeadKennedy\"><img class=\"pil\" src=\"http://img3.douban.com/icon/user_normal.jpg\" alt=\"DeadKennedy\"/></a>\n" +
                        "\n" +
                        "        </div>\n" +
                        "        <div class=\"nlst\"><h3>\n" +
                        "            <div id=\"tb-5304911\" class=\"rr\"><a id=\"af-5304911\" href=\"http://book.douban.com/review/5304911/\" class=\"j a_unfolder\" style=\"background:none;\">\n" +
                        "            <img src=\"http://img3.douban.com/pics/blank.gif\" class=\"bn-arrow\" width=\"48\" height=\"19\" alt=\"&gt;\" title=\"展开全文\"/></a>\n" +
                        "            <a id=\"au-5304911\" href=\"javascript:void(0);\" class=\"j a_folder\" style=\"background:none;display:none\">\n" +
                        "            <img src=\"http://img3.douban.com/pics/blank.gif\" class=\"bn-arrow\" width=\"48\" height=\"19\" alt=\"&lt;\" title=\"缩进全文\"/></a>\n" +
                        "            </div>\n" +
                        "            <a title=\"信仰的挽歌\" class=\"\" href=\"http://book.douban.com/review/5304911/\">信仰的挽歌</a>\n" +
                        "        </h3></div>\n" +
                        "        <div class=\"clst\">\n" +
                        "            <span class=\"ll user\">\n" +
                        "                <span class=\"starb\">                    <a href=\"http://book.douban.com/people/4364899/\" class=\"\">DeadKennedy</a>                    &nbsp;                </span>\n" +
                        "                <span class=\"allstar50\" title=\"力荐\"></span>\n" +
                        "            </span>\n" +
                        "            <br /><br />\n" +
                        "            <div id='review_5304911_short' class=\"review-short\">\n" +
                        "                    <span class=\"\">Elegy to Faith\n" +
                        "\n" +
                        "\n" +
                        "波拉诺难得的短篇集。\n" +
                        "\n" +
                        "\n" +
                        "比之长篇，波拉诺的短篇是其能力的代表。他的长篇像话剧台词，冗长，精彩，让人迷失其中，在读过大概三百页之后似乎明白一些他在说什么。而他的短篇则像电台DJ的串词，明了，信息丰富，基本是波拉诺的自传和自白。很多篇目就是作家自身经历的镜像。是一些关于动荡，个人自由，劳动，知识份子，流放和坚持的故事。纽约时报评论这本书为“流放民谣”。\n" +
                        "\n" +
                        "\n" +
                        "比如写自身经历的：......</span>\n" +
                        "\n" +
                        "                    <br/><br/><div class=\"pl clearfix\">\n" +
                        "                    <span class=\"fleft\">\n" +
                        "                        <span class=\"\">2012-02-14 13:53 &nbsp; &nbsp;</span>\n" +
                        "                        <span class=\"\">2/2有用</span>\n" +
                        "                    </span>\n" +
                        "                    \n" +
                        "                    <span class=\"works-link\">来自&nbsp;<a onclick=\"moreurl(this, {from:'works'})\" href=\"http://book.douban.com/subject/2583733/\">New Directions2007版</a></span>\n" +
                        "\n" +
                        "                    </div>\n" +
                        "            </div>\n" +
                        "            <div id=\"review_5304911_full\" style=\"display:none\"></div>\n" +
                        "        </div>\n" +
                        "    </div>\n" +
                        "        </div>\n" +
                        "    <div class=\"clear\"></div>\n" +
                        "    \n" +
                        "\n" +
                        "\n" +
                        "      </div>\n" +
                        "\n" +
                        "      <div id=\"wt_1\" class=\"hidden\">\n" +
                        "          \n" +
                        "    \n" +
                        "        <div class='ctsh'>\n" +
                        "    <div class=\"tlst\">\n" +
                        "        <div class=\"ilst\">\n" +
                        "                \n" +
                        "    <a href=\"http://book.douban.com/people/4364899/\" title=\"DeadKennedy\"><img class=\"pil\" src=\"http://img3.douban.com/icon/user_normal.jpg\" alt=\"DeadKennedy\"/></a>\n" +
                        "\n" +
                        "        </div>\n" +
                        "        <div class=\"nlst\"><h3>\n" +
                        "            <div id=\"tb-5304911\" class=\"rr\"><a id=\"af-5304911\" href=\"http://book.douban.com/review/5304911/\" class=\"j a_unfolder\" style=\"background:none;\">\n" +
                        "            <img src=\"http://img3.douban.com/pics/blank.gif\" class=\"bn-arrow\" width=\"48\" height=\"19\" alt=\"&gt;\" title=\"展开全文\"/></a>\n" +
                        "            <a id=\"au-5304911\" href=\"javascript:void(0);\" class=\"j a_folder\" style=\"background:none;display:none\">\n" +
                        "            <img src=\"http://img3.douban.com/pics/blank.gif\" class=\"bn-arrow\" width=\"48\" height=\"19\" alt=\"&lt;\" title=\"缩进全文\"/></a>\n" +
                        "            </div>\n" +
                        "            <a title=\"信仰的挽歌\" class=\"\" href=\"http://book.douban.com/review/5304911/\">信仰的挽歌</a>\n" +
                        "        </h3></div>\n" +
                        "        <div class=\"clst\">\n" +
                        "            <span class=\"ll user\">\n" +
                        "                <span class=\"starb\">                    <a href=\"http://book.douban.com/people/4364899/\" class=\"\">DeadKennedy</a>                    &nbsp;                </span>\n" +
                        "                <span class=\"allstar50\" title=\"力荐\"></span>\n" +
                        "            </span>\n" +
                        "            <br /><br />\n" +
                        "            <div id='review_5304911_short' class=\"review-short\">\n" +
                        "                    <span class=\"\">Elegy to Faith\n" +
                        "\n" +
                        "\n" +
                        "波拉诺难得的短篇集。\n" +
                        "\n" +
                        "\n" +
                        "比之长篇，波拉诺的短篇是其能力的代表。他的长篇像话剧台词，冗长，精彩，让人迷失其中，在读过大概三百页之后似乎明白一些他在说什么。而他的短篇则像电台DJ的串词，明了，信息丰富，基本是波拉诺的自传和自白。很多篇目就是作家自身经历的镜像。是一些关于动荡，个人自由，劳动，知识份子，流放和坚持的故事。纽约时报评论这本书为“流放民谣”。\n" +
                        "\n" +
                        "\n" +
                        "比如写自身经历的：......</span>\n" +
                        "\n" +
                        "                    <br/><br/><div class=\"pl clearfix\">\n" +
                        "                    <span class=\"fleft\">\n" +
                        "                        <span class=\"\">2012-02-14 13:53 &nbsp; &nbsp;</span>\n" +
                        "                        <span class=\"\">2/2有用</span>\n" +
                        "                    </span>\n" +
                        "                    \n" +
                        "                    <span class=\"works-link\">来自&nbsp;<a onclick=\"moreurl(this, {from:'works'})\" href=\"http://book.douban.com/subject/2583733/\">New Directions2007版</a></span>\n" +
                        "\n" +
                        "                    </div>\n" +
                        "            </div>\n" +
                        "            <div id=\"review_5304911_full\" style=\"display:none\"></div>\n" +
                        "        </div>\n" +
                        "    </div>\n" +
                        "        </div>\n" +
                        "    <div class=\"clear\"></div>\n" +
                        "    \n" +
                        "\n" +
                        "\n" +
                        "      </div>\n" +
                        "\n" +
                        "      <script type=\"text/javascript\">\n" +
                        "          function switch_tab(tab_id){\n" +
                        "              hide_tab_id = $(\"#comment_tab .on\")[0].id;\n" +
                        "              if (hide_tab_id==tab_id) {\n" +
                        "                  return false;\n" +
                        "              }\n" +
                        "              $(\"#\"+tab_id).addClass(\"on\");\n" +
                        "              $(\"#\"+hide_tab_id).removeClass(\"on\");\n" +
                        "              $(\"#w\"+hide_tab_id).hide();\n" +
                        "              $(\"#w\"+tab_id).show();\n" +
                        "          }\n" +
                        "\n" +
                        "        $('.ctsh').delegate('.clst', 'hover', function(e) {\n" +
                        "          $(this).find('.report')[e.type === 'mouseenter' ? 'show' : 'hide']()\n" +
                        "        })\n" +
                        "      </script>\n" +
                        "  </div>\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "  \n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "<div class=\"ugc-mod reading-notes\">\n" +
                        "  <div class=\"hd\">\n" +
                        "  </div>\n" +
                        "  \n" +
                        "\n" +
                        "\n" +
                        "</div>\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "<script type=\"text/javascript\">\n" +
                        "  $(document).ready(function(){\n" +
                        "    var TEMPL_ADD_COL = '<a href=\"\" id=\"n-{NOTE_ID}\" class=\"colbutt ll add-col\"><span>收藏</span></a>',\n" +
                        "      TEMPL_DEL_COL = '<span class=\"pl\">已收藏 &gt;<a href=\"\" id=\"n-{NOTE_ID}\" class=\"del-col\">取消收藏</a></span>';\n" +
                        "\n" +
                        "    $('body').delegate('.add-col', 'click', function(e){\n" +
                        "      e.preventDefault();\n" +
                        "      var nnid = $(this).attr('id').split('-')[1],\n" +
                        "        bn_add = $(this);\n" +
                        "      $.post_withck(\"/j/book/annotation_collect\",{nid:nnid},function(){\n" +
                        "        var a = $(TEMPL_DEL_COL.replace('{NOTE_ID}', nnid));\n" +
                        "        bn_add.before(a);\n" +
                        "        bn_add.remove();\n" +
                        "      })\n" +
                        "    });\n" +
                        "\n" +
                        "    $('body').delegate('.del-col', 'click', function(e){\n" +
                        "      e.preventDefault();\n" +
                        "      var nnid = $(this).attr('id').split('-')[1],\n" +
                        "        bn_del = $(this).parent();\n" +
                        "      $.post_withck(\"/j/book/annotation_uncollect\", {nid: nnid}, function() {\n" +
                        "        var a = $(TEMPL_ADD_COL.replace('{NOTE_ID}', nnid));\n" +
                        "        bn_del.before(a);\n" +
                        "        bn_del.remove();\n" +
                        "      })\n" +
                        "    });\n" +
                        "\n" +
                        "    $(\"pre.source\").each(function(){\n" +
                        "      var cn = $(this).attr('class').split(' ');\n" +
                        "      l = cn[1];\n" +
                        "      s = 'rand01';\n" +
                        "      n = cn[2];\n" +
                        "      $(this).snippet(n,{style: s, showNum: l});\n" +
                        "    });\n" +
                        "\n" +
                        "    var annotationMod = $('.reading-notes .bd')\n" +
                        "      , annotationTabs = annotationMod.find('.inline-tabs li')\n" +
                        "      , annotationTabLinks = annotationTabs.find('a')\n" +
                        "      , annotationTabContents = annotationMod.find('ul.comments');\n" +
                        "\n" +
                        "    annotationTabLinks.click(function(e){\n" +
                        "      e.preventDefault();\n" +
                        "      var el = $(this)\n" +
                        "        , kind = el.attr('id');\n" +
                        "\n" +
                        "      annotationTabs.removeClass('on');\n" +
                        "      el.parent().addClass('on');\n" +
                        "      annotationTabContents.hide();\n" +
                        "      annotationTabContents.filter('.' + kind).show();\n" +
                        "    });\n" +
                        "  });\n" +
                        "</script>\n" +
                        "\n" +
                        "<script type=\"text/x-mathjax-config\">\n" +
                        "MathJax.Hub.Config({\n" +
                        "\tjax: [\"input/TeX\", \"output/HTML-CSS\"],\n" +
                        "    extensions: [\"tex2jax.js\",\"TeX/AMSmath.js\",\"TeX/AMSsymbols.js\",\"TeX/noUndefined.js\"],\n" +
                        "    tex2jax: {\n" +
                        "\t\tinlineMath: [ [\"($\", \"$)\"], ['\\\\(','\\\\)'] ],\n" +
                        "\t\tdisplayMath: [ [\"($$\",\"$$)\"], ['\\\\[','\\\\]']],\n" +
                        "\t\tskipTags: [\"script\",\"noscript\",\"style\",\"textarea\"],\n" +
                        "\t\tprocessEscapes: true,\n" +
                        "\t\tprocessEnvironments: true,\n" +
                        "\t\tpreview: \"TeX\"\n" +
                        "\t},\n" +
                        "\tshowProcessingMessages: false\n" +
                        "  });\n" +
                        "</script>\n" +
                        "\n" +
                        "\n" +
                        "  \n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "<div id=\"db-discussion-section\" class=\"indent ugc-mod\">\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "        \n" +
                        "  \n" +
                        "\n" +
                        "  <h2 class=\"\">\n" +
                        "\n" +
                        "    论坛\n" +
                        "      &nbsp;&middot;&nbsp;&middot;&nbsp;&middot;&nbsp;&middot;&nbsp;&middot;&nbsp;&middot;\n" +
                        "\n" +
                        "  </h2>\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "        \n" +
                        "<table class=\"olt\"><tr><td><td><td><td></tr>\n" +
                        "        \n" +
                        "        <tr class=\"\">\n" +
                        "            <td class=\"pl\"><a href=\"http://book.douban.com/subject/20501761/discussion/52421904/\" title=\"平装or精装？孔亚雷or赵德明？\" class=\"\">平装or精装？孔亚雷or赵德明？</a></td>\n" +
                        "            <td class=\"pl\">来自<a href=\"http://book.douban.com/people/59305218/\" class=\"\">Nihilum</a></td>\n" +
                        "            <td class=\"pl\">5 回应</td>\n" +
                        "            <td class=\"pl\">2013-04-21</td>\n" +
                        "        </tr>\n" +
                        "        \n" +
                        "        <tr class=\"\">\n" +
                        "            <td class=\"pl\"><a href=\"http://book.douban.com/subject/20501761/discussion/52548182/\" title=\"書到底出了沒啊？\" class=\"\">書到底出了沒啊？</a></td>\n" +
                        "            <td class=\"pl\">来自<a href=\"http://book.douban.com/people/cheng911002/\" class=\"\">阿城1991</a></td>\n" +
                        "            <td class=\"pl\">14 回应</td>\n" +
                        "            <td class=\"pl\">2013-04-13</td>\n" +
                        "        </tr>\n" +
                        "        \n" +
                        "        <tr class=\"\">\n" +
                        "            <td class=\"pl\"><a href=\"http://book.douban.com/subject/20501761/discussion/52910043/\" title=\"不是翻译问题，是根本看不懂\" class=\"\">不是翻译问题，是根本看不懂</a></td>\n" +
                        "            <td class=\"pl\">来自<a href=\"http://book.douban.com/people/49027770/\" class=\"\">呆呆双鱼女</a></td>\n" +
                        "            <td class=\"pl\">1 回应</td>\n" +
                        "            <td class=\"pl\">2013-04-20</td>\n" +
                        "        </tr>\n" +
                        "</table>\n" +
                        "\n" +
                        "\n" +
                        "            <p class=\"pl\" align=\"right\">&gt;\n" +
                        "                <a href=\"http://book.douban.com/subject/20501761/discussion/create\" rel=\"nofollow\">在这本书的论坛里发言</a>\n" +
                        "            </p>\n" +
                        "\n" +
                        "\n" +
                        "</div>\n" +
                        "\n" +
                        "\n" +
                        "</div>\n" +
                        "<script type=\"text/javascript\">\n" +
                        "$(function(){if($.browser.msie && $.browser.version == 6.0){\n" +
                        "    var maxWidth = parseInt($('#info').css('max-width'));\n" +
                        "    if($('#info').width() > maxWidth)\n" +
                        "        $('#info').width(maxWidth)\n" +
                        "}});\n" +
                        "</script>\n" +
                        "</div>\n" +
                        "      <div class=\"aside\">\n" +
                        "        \n" +
                        "  \n" +
                        "  \n" +
                        "  \n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "    \n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "<style type=\"text/css\" media=\"screen\">\n" +
                        "  .add2cartContainer{line-height:24px;vertical-align:bottom}.add2cartContainer .add2cart{margin-right:0;display:inline-block}#buyinfo-report{display:none}#buyinfo-report .lnk-close-report{float:right;margin-top:-30px;line-height:14px}#buyinfo-report .item{margin-bottom:10px}#buyinfo-report .item input{vertical-align:text-bottom;*vertical-align:middle}#buyinfo-report .item label{margin:0 5px 0 2px}#buyinfo-report .item-submit .bn-flat{margin-right:10px}#buyinfo-report .item-price input{width:220px;border:1px solid #ccc;padding:4px}#buyinfo-report form{margin:5px 0 10px}#bi-report-btn{float:right;margin:2px 0;line-height:14px;display:none}.bi-vendor-report{color:#aaa}.bi-vendor-report-form{display:none;color:#111;margin:0 5px;line-height:25px}.gray_ad .ebook-tag{margin-left:8px;padding:0 6px;line-height:20px;font-size:11px;border:1px solid #e6e6e2;background:#f1f1ed;display:inline-block;*display:inline;*zoom:1;vertical-align:top}\n" +
                        "</style>\n" +
                        "\n" +
                        "\n" +
                        "<div class=\"gray_ad\" id=\"buyinfo\">\n" +
                        "\n" +
                        "    <div id=\"buyinfo-printed\">\n" +
                        "      <h2 class=\"\">\n" +
                        "        在哪儿买这本书?\n" +
                        "      </h2>\n" +
                        "      <ul class=\"bs noline more-after \">\n" +
                        "          \n" +
                        "          <li class=\"\">\n" +
                        "          <a target=\"_blank\" href=\"http://www.douban.com/link2?type=bkbuy&amp;subject=20501761&amp;vendor=joyo&amp;price=3510&amp;lowest=3200&amp;pre=0&amp;cntvendor=2&amp;pos=1&amp;srcsubj=20501761&amp;srcpage=subject&amp;url=http%3A%2F%2Fwww.amazon.cn%2Fmn%2FdetailApp%2Fref%3Dasc_df_B00BN4P5AM595446%2F%3Fasin%3DB00BN4P5AM%26tag%3Ddouban-23%26creative%3D2384%26creativeASIN%3DB00BN4P5AM%26linkCode%3Dasn\" class=\"\">\n" +
                        "              亚马逊\n" +
                        "            <span class=\"buylink-price\">\n" +
                        "              ( <span class=\"\">RMB 35.10</span> )\n" +
                        "            </span>\n" +
                        "            </a>\n" +
                        "\n" +
                        "\n" +
                        "          \n" +
                        "\n" +
                        "          </li>\n" +
                        "          \n" +
                        "          <li class=\"\">\n" +
                        "          <a target=\"_blank\" href=\"http://www.douban.com/link2?type=bkbuy&amp;subject=20501761&amp;vendor=beifa&amp;price=3200&amp;lowest=3200&amp;pre=0&amp;cntvendor=2&amp;pos=2&amp;srcsubj=20501761&amp;srcpage=subject&amp;url=http%3A%2F%2Fbook.beifabook.com%2FProduct%2FBookDetail.aspx%3FPlucode%3D720811202%26extra%3D3524321_s20501761\" class=\"\">\n" +
                        "              北发图书网\n" +
                        "            <span class=\"buylink-price\">\n" +
                        "              ( <span class=\"\">RMB 32.00</span> )\n" +
                        "            </span>\n" +
                        "            </a>\n" +
                        "\n" +
                        "\n" +
                        "          \n" +
                        "\n" +
                        "          </li>\n" +
                        "\n" +
                        "        <li class=\"\">\n" +
                        "            <a href=\"http://book.douban.com/subject/20501761/buylinks\" class=\"\">查看2家网店价格\n" +
                        "                ( RMB 32.00 起 )\n" +
                        "            </a>\n" +
                        "        </li>\n" +
                        "      </ul>\n" +
                        "    </div>\n" +
                        "    \n" +
                        "  <div class=\"add2cartContainer ft\n" +
                        "    \">\n" +
                        "    \n" +
                        "  <span class=\"add2cartWidget rr\">\n" +
                        "      \n" +
                        "      <a href=\"javascript:;\" class=\"j colbutt a_add2cart add2cart\"\n" +
                        "        name=\"20501761\">\n" +
                        "        <span>加入购书单</span></a>\n" +
                        "      <span class=\"color_gary book-in-cart hidden\"\n" +
                        "        >\n" +
                        "          已在购书单&nbsp;\n" +
                        "          <a href=\"http://book.douban.com/cart\">查看</a>\n" +
                        "        <a class=\"delete-cart-item\" rel=\"20501761\" href=\"http://book.douban.com/cart\">删除</a>\n" +
                        "      </span>\n" +
                        "  </span>\n" +
                        "  \n" +
                        "\n" +
                        "    <span class=\"pl add2cartTip\">多本比价，批量购买</span>\n" +
                        "  </div>\n" +
                        "\n" +
                        "</div>\n" +
                        "\n" +
                        "<style class=\"text/css\">\n" +
                        "  .presale-indicator{display:inline-block;*display:inline;*zoom:1;width:24px;height:15px;line-height:15px;background:url(http://img3.douban.com/pics/book/cart/presale_text.gif) no-repeat;text-indent:-9999px;vertical-align:middle;*vertical-align:0;_vertical-align:2px;margin-left:.5em}\n" +
                        "</style>\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "  \n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "  <div id=\"dale_book_subject_top_right\"></div>\n" +
                        "  <div id=\"dale_book_subject_top_middle\"></div>\n" +
                        "  \n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "  \n" +
                        "  \n" +
                        "\n" +
                        "  <h2 class=\"\">\n" +
                        "\n" +
                        "    这本书的其他版本 \n" +
                        "      &nbsp;&middot;&nbsp;&middot;&nbsp;&middot;&nbsp;&middot;&nbsp;&middot;&nbsp;&middot;\n" +
                        "      <span class=\"pl\">&nbsp;(\n" +
                        "          <a href=\"http://book.douban.com/works/1037601\">全部3</a>\n" +
                        "        ) </span>\n" +
                        "\n" +
                        "  </h2>\n" +
                        "\n" +
                        "\n" +
                        "  <div class=\"indent\">\n" +
                        "    <ul>\n" +
                        "        <li class=\"mb8 pl\">\n" +
                        "          <a href=\"http://book.douban.com/subject/2583733/\">New Directions版</a>\n" +
                        "          2007-4-30 / 5人读过 / 有售\n" +
                        "        </li>\n" +
                        "        <li class=\"mb8 pl\">\n" +
                        "          <a href=\"http://book.douban.com/subject/6764011/\">未知出版社版</a>\n" +
                        "          2008-5 / 有售\n" +
                        "        </li>\n" +
                        "    </ul>\n" +
                        "  </div>\n" +
                        "\n" +
                        "\n" +
                        "  \n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "      \n" +
                        "  \n" +
                        "\n" +
                        "  <h2 class=\"\">\n" +
                        "\n" +
                        "    以下豆列推荐\n" +
                        "      &nbsp;&middot;&nbsp;&middot;&nbsp;&middot;&nbsp;&middot;&nbsp;&middot;&nbsp;&middot;\n" +
                        "      <span class=\"pl\">&nbsp;(\n" +
                        "          <a href=\"http://book.douban.com/subject/20501761/doulists\">全部</a>\n" +
                        "        ) </span>\n" +
                        "\n" +
                        "  </h2>\n" +
                        "\n" +
                        "\n" +
                        "    <div id=\"db-doulist-section\" class=\"indent\">\n" +
                        "      <ul class=\"bs\">\n" +
                        "            <li><a href=\"http://book.douban.com/doulist/1877048/\">2013年3.1～4.30大陆推出的外国文学新书</a>\n" +
                        "                <span class=\"pl\">(Onetti)</span>\n" +
                        "            </li>\n" +
                        "            <li><a href=\"http://book.douban.com/doulist/1158147/\">中国翻译出版的拉美文学作品（2000-）</a>\n" +
                        "                <span class=\"pl\">(地球的小孩)</span>\n" +
                        "            </li>\n" +
                        "            <li><a href=\"http://book.douban.com/doulist/1734159/\">罗贝托·波拉尼奥 年表顺序作品大全</a>\n" +
                        "                <span class=\"pl\">(王多功)</span>\n" +
                        "            </li>\n" +
                        "            <li><a href=\"http://book.douban.com/doulist/1759375/\">2013年购书目录（一）</a>\n" +
                        "                <span class=\"pl\">(思郁)</span>\n" +
                        "            </li>\n" +
                        "            <li><a href=\"http://book.douban.com/doulist/1379223/\">“我阅读是为了让光阴流逝使我心安”</a>\n" +
                        "                <span class=\"pl\">(胡桃家子)</span>\n" +
                        "            </li>\n" +
                        "      </ul>\n" +
                        "    </div>\n" +
                        "\n" +
                        "\n" +
                        "  \n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "  <h2>谁读这本书?</h2>\n" +
                        "  <div class=\"indent\" id=\"collector\">\n" +
                        "\n" +
                        "    \n" +
                        "\n" +
                        "    \n" +
                        "    <div class=\"ll\"><a href=\"http://book.douban.com/people/stardiviner/\"><img src=\"http://img3.douban.com/icon/u34279740-3.jpg\" class=\"pil\" alt=\"小K\" /></a></div>\n" +
                        "    <div style=\"padding-left:60px\"><a href=\"http://book.douban.com/people/stardiviner/\">小K</a><br/>\n" +
                        "      <div class=\"pl ll\">          13分钟前          想读      </div>\n" +
                        "\n" +
                        "      <br/>\n" +
                        "\n" +
                        "\n" +
                        "    </div>\n" +
                        "    <div class=\"clear\"></div><br/>\n" +
                        "    <div class=\"ul\" style=\"margin-bottom:12px;\"></div>\n" +
                        "    \n" +
                        "    <div class=\"ll\"><a href=\"http://book.douban.com/people/52470092/\"><img src=\"http://img3.douban.com/icon/u52470092-9.jpg\" class=\"pil\" alt=\"杰森辛普森\" /></a></div>\n" +
                        "    <div style=\"padding-left:60px\"><a href=\"http://book.douban.com/people/52470092/\">杰森辛普森</a><br/>\n" +
                        "      <div class=\"pl ll\">          28分钟前          想读      </div>\n" +
                        "\n" +
                        "      <br/>\n" +
                        "\n" +
                        "\n" +
                        "    </div>\n" +
                        "    <div class=\"clear\"></div><br/>\n" +
                        "    <div class=\"ul\" style=\"margin-bottom:12px;\"></div>\n" +
                        "    \n" +
                        "    <div class=\"ll\"><a href=\"http://book.douban.com/people/71252474/\"><img src=\"http://img3.douban.com/icon/u71252474-3.jpg\" class=\"pil\" alt=\"Aby\" /></a></div>\n" +
                        "    <div style=\"padding-left:60px\"><a href=\"http://book.douban.com/people/71252474/\">Aby</a><br/>\n" +
                        "      <div class=\"pl ll\">          37分钟前          想读      </div>\n" +
                        "\n" +
                        "      <br/>\n" +
                        "\n" +
                        "        <span class=\"pl\">tags:对人生的诠释</span>\n" +
                        "\n" +
                        "    </div>\n" +
                        "    <div class=\"clear\"></div><br/>\n" +
                        "    <div class=\"ul\" style=\"margin-bottom:12px;\"></div>\n" +
                        "    \n" +
                        "    <div class=\"ll\"><a href=\"http://book.douban.com/people/44057759/\"><img src=\"http://img3.douban.com/icon/u44057759-28.jpg\" class=\"pil\" alt=\"老男孩\" /></a></div>\n" +
                        "    <div style=\"padding-left:60px\"><a href=\"http://book.douban.com/people/44057759/\">老男孩</a><br/>\n" +
                        "      <div class=\"pl ll\">          1小时前          想读      </div>\n" +
                        "\n" +
                        "      <br/>\n" +
                        "\n" +
                        "\n" +
                        "    </div>\n" +
                        "    <div class=\"clear\"></div><br/>\n" +
                        "    <div class=\"ul\" style=\"margin-bottom:12px;\"></div>\n" +
                        "\n" +
                        "\n" +
                        "        <p class=\"pl\">&gt; <a href=\"http://book.douban.com/subject/20501761/doings\">5人在读</a></p>\n" +
                        "        <p class=\"pl\">&gt; <a href=\"http://book.douban.com/subject/20501761/collections\">12人读过</a></p>\n" +
                        "        <p class=\"pl\">&gt; <a href=\"http://book.douban.com/subject/20501761/wishes\">658人想读</a></p>\n" +
                        "\n" +
                        "  </div>\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "    \n" +
                        "\n" +
                        "    \n" +
                        "  \n" +
                        "\n" +
                        "  <h2 class=\"\">\n" +
                        "\n" +
                        "    喜欢这本书的人常去的小组\n" +
                        "      &nbsp;&middot;&nbsp;&middot;&nbsp;&middot;&nbsp;&middot;&nbsp;&middot;&nbsp;&middot;\n" +
                        "\n" +
                        "  </h2>\n" +
                        "\n" +
                        "\n" +
                        "    \n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "    \n" +
                        "    <dl class=\"ob\">\n" +
                        "        <dt class=\"\"><a  href=\"http://www.douban.com/group/35832/\"><img src=\"http://img3.douban.com/icon/g35832-1.jpg\" class=\"m_sub_img\" alt=\"托马斯·品钦\"/></a></dt>\n" +
                        "    \n" +
                        "    <dd><a  href=\"http://www.douban.com/group/35832/\" class=\"\">托马斯·品钦</a> <span>(711)</span>\n" +
                        "    </dd>\n" +
                        "    </dl>\n" +
                        "    \n" +
                        "    <dl class=\"ob\">\n" +
                        "        <dt class=\"\"><a  href=\"http://www.douban.com/group/377070/\"><img src=\"http://img3.douban.com/icon/g377070-2.jpg\" class=\"m_sub_img\" alt=\"短经典\"/></a></dt>\n" +
                        "    \n" +
                        "    <dd><a  href=\"http://www.douban.com/group/377070/\" class=\"\">短经典</a> <span>(787)</span>\n" +
                        "    </dd>\n" +
                        "    </dl>\n" +
                        "    \n" +
                        "    <dl class=\"ob\">\n" +
                        "        <dt class=\"\"><a  href=\"http://www.douban.com/group/BrunoSchulz/\"><img src=\"http://img3.douban.com/icon/g107435-2.jpg\" class=\"m_sub_img\" alt=\"寻找:布鲁诺.舒尔茨\"/></a></dt>\n" +
                        "    \n" +
                        "    <dd><a  href=\"http://www.douban.com/group/BrunoSchulz/\" class=\"\">寻找:布鲁诺.舒尔茨</a> <span>(466)</span>\n" +
                        "    </dd>\n" +
                        "    </dl>\n" +
                        "    \n" +
                        "    <dl class=\"ob\">\n" +
                        "        <dt class=\"\"><a  href=\"http://www.douban.com/group/17957/\"><img src=\"http://img3.douban.com/icon/g17957-2.jpg\" class=\"m_sub_img\" alt=\"胡安·鲁尔福\"/></a></dt>\n" +
                        "    \n" +
                        "    <dd><a  href=\"http://www.douban.com/group/17957/\" class=\"\">胡安·鲁尔福</a> <span>(613)</span>\n" +
                        "    </dd>\n" +
                        "    </dl>\n" +
                        "    \n" +
                        "    <dl class=\"ob\">\n" +
                        "        <dt class=\"\"><a  href=\"http://www.douban.com/group/29633/\"><img src=\"http://img3.douban.com/icon/g29633-1.jpg\" class=\"m_sub_img\" alt=\"V.S.奈保尔\"/></a></dt>\n" +
                        "    \n" +
                        "    <dd><a  href=\"http://www.douban.com/group/29633/\" class=\"\">V.S.奈保尔</a> <span>(445)</span>\n" +
                        "    </dd>\n" +
                        "    </dl>\n" +
                        "    \n" +
                        "    <dl class=\"ob\">\n" +
                        "        <dt class=\"\"><a  href=\"http://www.douban.com/group/173408/\"><img src=\"http://img3.douban.com/icon/g173408-3.jpg\" class=\"m_sub_img\" alt=\"胡利奥·科塔萨尔\"/></a></dt>\n" +
                        "    \n" +
                        "    <dd><a  href=\"http://www.douban.com/group/173408/\" class=\"\">胡利奥·科塔萨尔</a> <span>(1053)</span>\n" +
                        "    </dd>\n" +
                        "    </dl>\n" +
                        "    \n" +
                        "    <dl class=\"ob\">\n" +
                        "        <dt class=\"\"><a  href=\"http://www.douban.com/group/234212/\"><img src=\"http://img3.douban.com/icon/g234212-4.jpg\" class=\"m_sub_img\" alt=\"中国当代书籍装帧摭评\"/></a></dt>\n" +
                        "    \n" +
                        "    <dd><a  href=\"http://www.douban.com/group/234212/\" class=\"\">中国当代书籍装帧摭评</a> <span>(1373)</span>\n" +
                        "    </dd>\n" +
                        "    </dl>\n" +
                        "    \n" +
                        "    <dl class=\"ob\">\n" +
                        "        <dt class=\"\"><a  href=\"http://www.douban.com/group/communaute/\"><img src=\"http://img3.douban.com/icon/g122538-6.jpg\" class=\"m_sub_img\" alt=\"泼先生\"/></a></dt>\n" +
                        "    \n" +
                        "    <dd><a  href=\"http://www.douban.com/group/communaute/\" class=\"\">泼先生</a> <span>(485)</span>\n" +
                        "    </dd>\n" +
                        "    </dl>\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "  \n" +
                        "\n" +
                        "    <br clear=\"all\"/>\n" +
                        "    <p class=\"pl\"> &gt; <a class=\"\" href=\"http://book.douban.com/subject/20501761/group_collectors\">收藏这本书的1个小组</a> </p>\n" +
                        "  <div class=\"clear\"></div>\n" +
                        "  <p class=\"pl\">&gt; \n" +
                        "    <a href=\"http://www.douban.com/people/flashsword20/groups?collect=20501761\"> 加到我的小组收藏里 </a>\n" +
                        "  </p>\n" +
                        "  <br/>\n" +
                        "  <div class=\"clear\"></div>\n" +
                        "\n" +
                        "  \n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "  \n" +
                        "\n" +
                        "  <h2>二手市场</h2>\n" +
                        "  <div class=\"indent\">\n" +
                        "    <ul class=\"bs\">\n" +
                        "    <li class=\"\">\n" +
                        "          <a href='http://book.douban.com/subject/20501761/new_offer' class=\"rr\">&gt; 点这儿转让</a>\n" +
                        "\n" +
                        "        有658人想读,手里有一本闲着?\n" +
                        "      </li>\n" +
                        "    </ul>\n" +
                        "  </div>\n" +
                        "\n" +
                        "  \n" +
                        "<p class=\"pl\">订阅关于地球上最后的夜晚的评论: <br/><span class=\"feed\">\n" +
                        "    <a href=\"http://book.douban.com/feed/subject/20501761/reviews\"> feed: rss 2.0</a></span></p>\n" +
                        "\n" +
                        "  \n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "<script type=\"text/javascript\">\n" +
                        "    (function (global) {\n" +
                        "        var newNode = global.document.createElement('script'),\n" +
                        "            existingNode = global.document.getElementsByTagName('script')[0],\n" +
                        "            adSource = 'http://erebor.douban.com/',\n" +
                        "            userId = '3524321',\n" +
                        "            browserId = 'eSjH+2HoDdk',\n" +
                        "            ipAddress = '115.174.146.245',\n" +
                        "            criteria = '3:/subject/20501761/',\n" +
                        "            preview = '',\n" +
                        "            debug = false,\n" +
                        "            adSlots = ['dale_book_subject_top_right', 'dale_book_subject_top_middle', 'dale_book_subject_middle_right'];\n" +
                        "\n" +
                        "        global.DoubanAdRequest = {src: adSource, uid: userId, bid: browserId, ip: ipAddress, crtr: criteria, prv: preview, debug: debug};\n" +
                        "        global.DoubanAdSlots = (global.DoubanAdSlots || []).concat(adSlots);\n" +
                        "\n" +
                        "        newNode.setAttribute('type', 'text/javascript');\n" +
                        "        newNode.setAttribute('src', 'http://img3.douban.com/js/packed_ad1227872440.js');\n" +
                        "        newNode.setAttribute('async', true);\n" +
                        "        existingNode.parentNode.insertBefore(newNode, existingNode);\n" +
                        "    })(this);\n" +
                        "</script>\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "      </div>\n" +
                        "      <div class=\"extra\">\n" +
                        "        \n" +
                        "      </div>\n" +
                        "    </div>\n" +
                        "  </div>\n" +
                        "\n" +
                        "        \n" +
                        "<div id=\"footer\">\n" +
                        "\n" +
                        "\n" +
                        "<span id=\"icp\" class=\"fleft gray-link\">\n" +
                        "    &copy; 2005－2013 douban.com, all rights reserved\n" +
                        "</span>\n" +
                        "\n" +
                        "<span class=\"fright\">\n" +
                        "    <a href=\"http://www.douban.com/about\">关于豆瓣</a>\n" +
                        "    · <a href=\"http://www.douban.com/jobs\">在豆瓣工作</a>\n" +
                        "    · <a href=\"http://www.douban.com/about?topic=contactus\">联系我们</a>\n" +
                        "    · <a href=\"http://www.douban.com/about?policy=disclaimer\">免责声明</a>\n" +
                        "    \n" +
                        "    · <a href=\"http://book.douban.com/help/\">帮助中心</a>\n" +
                        "    · <a href=\"http://developers.douban.com/\" target=\"_blank\">开发者</a>\n" +
                        "    · <a href=\"http://book.douban.com/library_invitation\">图书馆合作</a>\n" +
                        "    · <a href=\"http://www.douban.com/mobile/read\">手机读书</a>\n" +
                        "    · <a href=\"http://www.douban.com/partner/\">豆瓣广告</a>\n" +
                        "</span>\n" +
                        "\n" +
                        "\n" +
                        "</div>\n" +
                        "\n" +
                        "    </div>\n" +
                        "    <script type=\"text/javascript\">\n" +
                        "    Do(function() {\n" +
                        "      // 不再提醒\n" +
                        "      Douban.init_delete_reply_notify=function(b){var a=function(g){g.preventDefault();var c=$(g.target);var h=c[0].href.split(\"#\")[1];$.get(\"/j/accounts/remove_notify?id=\"+h);var d=c.closest(\".item-req\");if($.contains($(\".top-nav-reminder\")[0],d[0])){d=d.parent();var f=d.siblings().length;d.fadeOut(function(){d.remove()});if(f===0){d.closest(\".bd\").find(\".no-new-notis\").show()}}else{d.fadeOut()}};if(b.type===\"click\"){a(b)}else{$(b).click(a)}};\n" +
                        "      Douban.init_discard_notify=function(b){var a=function(i){i.preventDefault();var c=\"/j/notification/discard\";var f=$(i.target);var d=f[0].name;$.post_withck(c,{id:d},function(e){},\"json\");var g=f.closest(\".item-req\");if($.contains($(\".top-nav-reminder\")[0],g[0])){g=g.parent();var h=g.siblings().length;g.fadeOut(function(){g.remove()});if(h===0){g.closest(\".bd\").find(\".no-new-notis\").show()}}else{g.fadeOut()}};if(b.type===\"click\"){a(b)}else{$(b).click(a)}};\n" +
                        "      var notimenu = $('#top-nav-notimenu');\n" +
                        "      notimenu.bind('moreitem:show', function() {\n" +
                        "        $.ajax({\n" +
                        "          url: 'http://www.douban.com/j/notification/nav_pop',\n" +
                        "          data: { ck: get_cookie('ck'),\n" +
                        "                  k: '3524321:0bb4a68227c8e361ea696a8429044c2d19dbe3f7'\n" +
                        "                },\n" +
                        "          dataType: 'jsonp',\n" +
                        "          success: function(e) {\n" +
                        "            if (e.r) {\n" +
                        "              return;\n" +
                        "            }\n" +
                        "            notimenu.html(e.s);\n" +
                        "            if (e.n === 0) {\n" +
                        "              $('#db-global-nav .top-nav-reminder .num').remove();\n" +
                        "            } else {\n" +
                        "              $('#db-global-nav .top-nav-reminder .num span').html(e.n);\n" +
                        "            }\n" +
                        "            if (window.load_event_monitor) {\n" +
                        "              load_event_monitor($('#db-global-nav'));\n" +
                        "            }\n" +
                        "          }\n" +
                        "        });\n" +
                        "      });\n" +
                        "    });\n" +
                        "    </script><script type=\"text/javascript\" src=\"http://img3.douban.com/misc/mixed_static/7270fdcd799302b0.js\"></script>\n" +
                        "    <!-- mako -->\n" +
                        "    \n" +
                        "    \n" +
                        "  \n" +
                        "<script type=\"text/javascript\">\n" +
                        "var setMethodWithNs = function(namespace) {\n" +
                        "  var ns = namespace ? namespace + '.' : ''\n" +
                        "    , fn = function(string) {\n" +
                        "        if(!ns) {return string}\n" +
                        "        return ns + string\n" +
                        "      }\n" +
                        "  return fn\n" +
                        "}\n" +
                        "\n" +
                        "var gaWithNamespace = function(fn, namespace) {\n" +
                        "  var method = setMethodWithNs(namespace)\n" +
                        "  fn.call(this, method)\n" +
                        "}\n" +
                        "\n" +
                        "var _gaq = _gaq || []\n" +
                        "  , accounts = [\n" +
                        "      { id: 'UA-7019765-1', namespace: 'douban' }\n" +
                        "    , { id: 'UA-7019765-16', namespace: '' }\n" +
                        "    ]\n" +
                        "  , gaInit = function(account) {\n" +
                        "      gaWithNamespace(function(method) {\n" +
                        "        gaInitFn.call(this, method, account)\n" +
                        "      }, account.namespace)\n" +
                        "    }\n" +
                        "  , gaInitFn = function(method, account) {\n" +
                        "      _gaq.push([method('_setAccount'), account.id])\n" +
                        "\n" +
                        "      \n" +
                        "  _gaq.push([method('_addOrganic'), 'google', 'q'])\n" +
                        "  _gaq.push([method('_addOrganic'), 'baidu', 'wd'])\n" +
                        "  _gaq.push([method('_addOrganic'), 'soso', 'w'])\n" +
                        "  _gaq.push([method('_addOrganic'), 'youdao', 'q'])\n" +
                        "  _gaq.push([method('_addOrganic'), 'so.360.cn', 'q'])\n" +
                        "  _gaq.push([method('_addOrganic'), 'sogou', 'query'])\n" +
                        "  if (account.namespace) {\n" +
                        "    _gaq.push([method('_addIgnoredOrganic'), '豆瓣'])\n" +
                        "    _gaq.push([method('_addIgnoredOrganic'), 'douban'])\n" +
                        "    _gaq.push([method('_addIgnoredOrganic'), '豆瓣网'])\n" +
                        "    _gaq.push([method('_addIgnoredOrganic'), 'www.douban.com'])\n" +
                        "  }\n" +
                        "\n" +
                        "      if (account.namespace === 'douban') {\n" +
                        "        _gaq.push([method('_setDomainName'), '.douban.com'])\n" +
                        "      }\n" +
                        "\n" +
                        "        _gaq.push([method('_setCustomVar'), 1, 'responsive_view_mode', 'desktop', 3])\n" +
                        "\n" +
                        "        _gaq.push([method('_setCustomVar'), 2, 'login_status', '1', 2]);\n" +
                        "\n" +
                        "      _gaq.push([method('_trackPageview')])\n" +
                        "    }\n" +
                        "\n" +
                        "for(var i = 0, l = accounts.length; i < l; i++) {\n" +
                        "  var account = accounts[i]\n" +
                        "  gaInit(account)\n" +
                        "}\n" +
                        "\n" +
                        "\n" +
                        ";(function() {\n" +
                        "    var ga = document.createElement('script');\n" +
                        "    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';\n" +
                        "    ga.setAttribute('async', 'true');\n" +
                        "    document.documentElement.firstChild.appendChild(ga);\n" +
                        "})()\n" +
                        "</script>\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "    <!-- dis17-->\n" +
                        "\n" +
                        "<script>var _check_hijack = function () {\n" +
                        "            var _sig = \"eSjH+2Ho\", _login = true, bid = get_cookie('bid');\n" +
                        "            if (location.protocol != \"file:\" && (typeof(bid) != \"string\" && _login || typeof(bid) == \"string\" && bid.substring(0,8) != _sig)) {\n" +
                        "                location.href+=(/\\?/.test(location.href)?\"&\":\"?\") + \"_r=\" + Math.random().toString(16).substring(2);\n" +
                        "            }};\n" +
                        "            if (typeof(Do) != 'undefined') Do(_check_hijack);\n" +
                        "            else if (typeof(get_cookie) != 'undefined') _check_hijack();\n" +
                        "            </script>\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "</body>\n" +
                        "</html>\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n";

        Html html = new Html(text);
        System.out.println(html.sc());

    }
}
