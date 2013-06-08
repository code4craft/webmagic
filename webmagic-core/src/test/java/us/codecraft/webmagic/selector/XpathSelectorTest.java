package us.codecraft.webmagic.selector;

import org.junit.Assert;
import org.junit.Test;

/**
 * User: cairne
 * Date: 13-4-21
 * Time: 上午10:06
 */
public class XpathSelectorTest {

    String huxiuHtml = "\n" +
            "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
            "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
            "<head>\n" +
            "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
            "<title>产品情感化设计的两个层面-观点-@虎嗅网</title>\n" +
            "\n" +
            "<meta name=\"keywords\" content=\"产品情感化设计的两个层面\" />\n" +
            "<meta name=\"description\" id=\"description\" content=\"现在的网站文案已经越来越有人情味了。例如提示文案不是“你的账号密码错误”而是“密码不对哦”，文案中增加了语气词。文案内容的情感化也会增加用户的接受程度 \" />\n" +
            "<meta name=\"MSSmartTagsPreventParsing\" content=\"True\" />\n" +
            "<meta http-equiv=\"MSThemeCompatible\" content=\"Yes\" />\n" +
            "<base href=\"http://www.huxiu.com/\" /><link rel=\"stylesheet\" type=\"text/css\" href=\"data/cache/style_3_common.css?GDG\" /><link rel=\"stylesheet\" type=\"text/css\" href=\"data/cache/style_3_portal_view.css?GDG\" /><script type=\"text/javascript\">var count_article_id='13380', STYLEID = '3', STATICURL = 'static/', IMGDIR = 'static/image/common', VERHASH = 'GDG', charset = 'utf-8', discuz_uid = '0', cookiepre = 'HUXIU_016a_', cookiedomain = '.huxiu.com', cookiepath = '/', showusercard = '1', attackevasive = '0', disallowfloat = '', creditnotice = '', defaultstyle = '', REPORTURL = 'aHR0cDovL3d3dy5odXhpdS5jb20vYXJ0aWNsZS8xMzM4MC8xLmh0bWw=', SITEURL = 'http://www.huxiu.com/', JSPATH = 'static/js/';</script>\n" +
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
            "<body id=\"nv_portal\" class=\"pg_view \" onkeydown=\"if(event.keyCode==27) return false;\">\n" +
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
            "<span class=\"no-login-s\"><a href=\"member.php?mod=logging&amp;action=login\" onclick=\"showWindow('login', this.href)\">登录</a><a href=\"member.php?mod=register\" class=\"xi2 xw1\">注册</a></span>\n" +
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
            "<input type=\"hidden\" name=\"formhash\" value=\"48030d35\" />\n" +
            "<input type=\"hidden\" name=\"srchtype\" value=\"title\" />\n" +
            "<input type=\"hidden\" name=\"srhfid\" value=\"0\" />\n" +
            "<input type=\"hidden\" name=\"srhlocality\" value=\"portal::view\" />\n" +
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
            "<ul><li id=\"mn_N6666\" ><a href=\"/\" hidefocus=\"true\"  >首页</a></li><li id=\"mn_P1\" ><a href=\"http://www.huxiu.com/focus/\" hidefocus=\"true\"  >看点</a></li><li id=\"mn_P6\" ><a href=\"http://www.huxiu.com/books/\" hidefocus=\"true\"  >读点</a></li><li class=\"a\" id=\"mn_P4\" ><a href=\"http://www.huxiu.com/opinions/\" hidefocus=\"true\"  >观点</a></li><li id=\"mn_Ncd44\" ><a href=\"/tags\" hidefocus=\"true\"  >标签</a></li><li id=\"mn_N40f8\" ><a href=\"/contribute\" hidefocus=\"true\"  >投稿</a></li><li ><a hidefocus=\"true\" href=\"/space/uid/0.html\">我的虎嗅</a></li>\n" +
            "</ul>\n" +
            "</div>\n" +
            "<div id=\"mu\" class=\"cl\">\n" +
            "</div></div>\n" +
            "</div>\n" +
            "\n" +
            "\n" +
            "<div id=\"wp\" class=\"wp\">\n" +
            "<!--[name]!portalcategory_viewtplname![/name]-->\n" +
            "\n" +
            "<script src=\"static/js/forum_viewthread.js?GDG\" type=\"text/javascript\"></script>\n" +
            "<script type=\"text/javascript\">zoomstatus = parseInt(1), imagemaxwidth = 600, aimgcount = new Array();</script>\n" +
            "<script src=\"/template/youliao/image/tongji.js\" type=\"text/javascript\"></script>\n" +
            "\n" +
            "<style id=\"diy_style\" type=\"text/css\"></style>\n" +
            "<div class=\"wp\">\n" +
            "    <!--[diy=diy1]-->\n" +
            "    <div id=\"diy1\" class=\"area\"></div>\n" +
            "    <!--[/diy]-->\n" +
            "</div>\n" +
            "\n" +
            "\n" +
            "<div class=\"w-920 blog-idx\">\n" +
            "    <div class=\"w-long z ie6-shousi\">\n" +
            "        <div class=\"neirong\">\n" +
            "            <!--startprint1-->\n" +
            "            <div class=\"h view-h\">\n" +
            "\n" +
            "                <h1 class=\"ph xs5\">产品情感化设计的两个层面  </h1>\n" +
            "\n" +
            "                <div class=\"other\">\n" +
            "                    <div class=\"z\">\n" +
            "                                                <span class=\"author-name\">2013-4-22 16:10</span>\n" +
            "                        <span class=\"author-name\">\n" +
            "                        \t<a href=\"article/13380/1.html#comment\">评论(<span class=\"rep-num\">0</span>)</a>\n" +
            "                        </span>\n" +
            "<span class=\"new-tag\"><a target=\"_blank\" href=\"/tags/329\">产品</a>\n" +
            "<a target=\"_blank\" href=\"/tags/357\">投稿</a>\n" +
            "</span>\n" +
            "                    </div>\n" +
            "                    <div class=\"y i-ico v-font-s\"><big class=\"xs3\"><a href=\"javascript:SetFont(18)\">大</a></big><span class=\"xs2\"><a href=\"javascript:SetFont(16)\">中</a></span>\n" +
            "                        <small class=\"xs1\"><a href=\"javascript:SetFont(14)\">小</a></small>\n" +
            "                        <span class=\"i-print\"><a href=\"javascript:preview(1)\"><i class=\"i-ico\" id=\"i_print\"></i></a></span></div>\n" +
            "                    <div class=\"cl\"></div>\n" +
            "                </div>\n" +
            "\n" +
            "            </div>\n" +
            "            <div class=\"d\">\n" +
            "\n" +
            "                <!--[diy=diycontenttop]-->\n" +
            "                <div id=\"diycontenttop\" class=\"area\"></div>\n" +
            "                <!--[/diy]-->\n" +
            "                <table cellpadding=\"0\" cellspacing=\"0\" class=\"vwtb\">\n" +
            "                    <tr>\n" +
            "                        <td id=\"article_content\">\n" +
            "                                                                                    <span class=\"span-img\"><img src=\"http://u.img.huxiu.com/portal/201304/22/161540vff8jv8vmv9mvd9r.jpg\" alt=\"\"></span>\n" +
            "                                                                                    用户之所以选择一款产品，首要的一点在于产品的功能或内容满足了用户。而随着产品的发展，同类型的产品基础功能都大致相同，产品之间的竞争越来越难在功能层面拉开差距。现在产品人员也更加开始在用户体验上下功夫了，而对用户体验的不断追求也就上升到了情感层面。<div><br></div><div><div>谈起产品情感化设计，可以拿手机通讯录中添加联系人头像来举例子，单就这个功能点而言，最基础的只要用户能够添加联系人的头像即可，而如果在这个功能上添加用户情感化的元素后，就可以在用户的头像展示上给予更大空间，让用户能够更大的发挥自己的个性。我们也发现新浪微博和开心网个人主页的设计也都增加了个人封面的展示。产品情感化对于功能本身是没有影响的，而情感因素后，产品对用户还会更有吸引力。短期来看，个性化和给用户更大的发挥空间是产品情感化设计的两个很重要的方向。</div><div><br></div><div>产品的情感化设计有两个不同的做法：<b>一个是在已有功能上进行扩</b>展，如上文所提到的通讯录中上传头像的功能，是对用户表达欲的满足，用户情感的单向表达；<b>另一种做法则是做一个完全情感化的产品</b>，用户情感的双向表达，是用户之间情感内容的交流，产品扮演的只是桥梁作用，例如小恩爱、抬杠这样的产品。其实所有涉及到用户互动性的产品对于情感化的拓展空间都很大，但是与普通社交不同的是，产品的情感化在于人与人之间更深层次的交流。在我个人看来，社交网站中的发状态功能已经仅仅是用户表达的工具，极少含有感情因素，但是像Facebook推出的暗恋功能却是一个情感化产品，产品的情感化不仅在于让用户将自己的情感寄予到产品中，而且产品要想具有情感化很重要的一点在于产品本身能够起到挖掘用户情感的作用。</div><div><br></div><div><span style=\"line-height: 1.8em;\">前面所提到的两种做法区别在于，前者是基于已有需求而进行的情感化设计，而后者则是完全情感化的产品，就成功率来讲，显然是前者更大一些。本身有需求的产品对于产品的情感化发展不仅奠定了基础，而且也烘托了氛围，做好了铺垫。如果是做一个完全情感化的产品，失败的可能性很大。当产品的功能满足了用户的情感表达，那就意味着产品可以满足用户的需求，而当产品本身所扮演的角色无法成为用户的寄托，那么产品就会面临失败。可想而知，情感化的产品肯定属于UGC类型，对于用户内容的质量要求会比较高，当技术水平不够高、功能操作不够便捷的时候，自然就提高了使用门槛。而且这种类型的产品对于氛围的烘托本身就会有相对高的要求。</span></div><div><br></div><div><span style=\"line-height: 1.8em;\">如果单从功能角度去衡量，用户情感的单向表达属于功能层面，而用户情感的双向表达属于内容层面。除此之外，产品情感化还有文案和产品风格上的表现。</span></div><div><br></div><div><span style=\"line-height: 1.8em;\">你是一个资深网虫，或许你也有所感觉，现在的网站文案已经越来越有人情味了。例如提示文案不是“你的账号密码错误”而是“密码不对哦”，文案中增加了语气词。这只是其中的一种表达方式，除此之外，你会看到产品设计中的很多引导方式也更有趣味性，文案内容的情感化也会增加用户的接受程度。</span></div><div><br></div><div>最近自己在使用产品中也有个很大的感触，就是产品风格对用户的吸引，同样是天气类应用，功能上相差无几，但是不同的风格却可以吸引不同的受众。有的是大众普通的风格，有的是小清新风格，有的是卡通风格等等，可以理解为用户对不同风格产品的选择背后的原因就是用户个人情感的不同，而用户的这种情感不能改变只能顺从。</div><div><br></div><div>更深层次的讲，产品情感化的关键在于产品功能与用户情感的承接，满足人们情感的诉求。从心理学上讲人的本性有很多，例如表达欲、攀比心理，但从人的本性和产品的情感化进行匹配，会有太多的点，在这里就不一一例举了，大家可以在产品的使用过程中逐渐感受。而之所以要选择利用人性情感的哪一点来设计产品就要根据具体的产品目标来衡量了。</div><div><br></div><div><span style=\"line-height: 1.8em;\"><font face=\"楷体\">文章来源：<a href=\"http://blog.sina.com.cn/laoan6688\">马虎眼</a> &nbsp; &nbsp;作者微信账号：mahuyan</font></span></div></div><div style=\"font-size:12px;text-indent:0\"><br><br>本文由\n" +
            "<a href=\"space/uid/10025/1.html\">云瑞</a>\n" +
            "授权<a href=\"http://www.huxiu.com\">虎嗅网</a>发表，并经虎嗅网编辑。转载此文章须经作者同意，并请附上出处(<a href=\"http://www.huxiu.com\">虎嗅网</a>)及本页链接。<br />原文链接<a href=\"http://www.huxiu.com/article/13380/1.html\">http://www.huxiu.com/article/13380/1.html</a>\n" +
            "</div>\n" +
            "                        </td>\n" +
            "                    </tr>\n" +
            "                </table>\n" +
            "                \n" +
            "                <!--[diy=diycontentbottom]-->\n" +
            "                <div id=\"diycontentbottom\" class=\"area\"></div>\n" +
            "                <!--[/diy]-->\n" +
            "\n" +
            "                <script src=\"static/js/home.js?GDG\" type=\"text/javascript\"></script>\n" +
            "\n" +
            "\n" +
            "                                <!--[diy=diycontentclickbottom]-->\n" +
            "                <div id=\"diycontentclickbottom\" class=\"area\"></div>\n" +
            "                <!--[/diy]-->\n" +
            "            </div>\n" +
            "            <!--endprint1-->            <div class=\"neir_weixin\"><a href=\"http://www.huxiu.com/weixin.html\" target=\"_blank\"></a></div>\n" +
            "<div class=\"o cl ptm pbm brd-b b-glfx\">    <div class=\"z\">\n" +
            "        <div class=\"z pre_share1\">\n" +
            "            <a onclick=\"return false;\" href=\"/tools.php?mod=share\">分享(<span id=\"tool_hxshare_nums\">0</span>)：</a>\n" +
            "        </div>\n" +
            "        <div class=\"z share-box\" id=\"share_box\">\n" +
            "            <ul>\n" +
            "                                <li><a class=\"hxs_tsina\" href=\"javascript:;\" onclick=\"hxshare_send('hxs_tsina');\" title=\"分享到新浪微博\"></a>\n" +
            "                </li>\n" +
            "                                <li><a class=\"hxs_tqq\" href=\"javascript:;\" onclick=\"hxshare_send('hxs_tqq');\" title=\"分享到腾讯微博\"></a>\n" +
            "                </li>\n" +
            "                                <li><a class=\"hxs_qzone\" href=\"javascript:;\" onclick=\"hxshare_send('hxs_qzone');\" title=\"分享到QQ空间\"></a>\n" +
            "                </li>\n" +
            "                                <li><a class=\"hxs_renren\" href=\"javascript:;\" onclick=\"hxshare_send('hxs_renren');\" title=\"分享到人人网\"></a>\n" +
            "                </li>\n" +
            "                                <li><a class=\"hxs_linkedin\" href=\"javascript:;\" onclick=\"hxshare_send('hxs_linkedin');\" title=\"分享到linkedin\"></a>\n" +
            "                </li>\n" +
            "                                <li><a class=\"hxs_t163\" href=\"javascript:;\" onclick=\"hxshare_send('hxs_t163');\" title=\"分享到网易微博\"></a>\n" +
            "                </li>\n" +
            "                                <li><a class=\"hxs_tomail\" href=\"javascript:;\" onclick=\"hxshare_send('hxs_tomail');\" title=\"分享到邮件分享\"></a>\n" +
            "                </li>\n" +
            "                                <style>\n" +
            "                </style>\n" +
            "                <li class=\"tools-qrcode\"><a href=\"javascript:;\" title=\"点击查看本页二维码\"></a><div id=\"qrcode_box\"></div></li>\n" +
            "            </ul>\n" +
            "            <script language=\"javascript\">\n" +
            "                function preg_quote(str) {\n" +
            "                    return str.replace(/([\"'])/g, \"`\");\n" +
            "                }\n" +
            "                function hxshare_send(des) {\n" +
            "                    var HX_U = encodeURIComponent(document.location.href),\n" +
            "                            HX_T = encodeURIComponent(preg_quote(document.title)),\n" +
            "                            HX_C = encodeURIComponent(preg_quote(document.getElementById(\"description\").content));\n" +
            "                    var hxs_url = \"/tools.php?mod=share\";\n" +
            "                    var hxs_url = hxs_url + '&des=' + des + '&from_url=' + HX_U + '&title=' + HX_T + '&description=' + HX_C;\n" +
            "                    //window.location.href = tmp_url;\n" +
            "                    window.open(hxs_url);\n" +
            "                }\n" +
            "                jQ('.tools-qrcode').live('click',function(){\n" +
            "                \tif(jQ('.tools-qrcode').css('overflow')=='hidden'){\n" +
            "                \t\tif(jQ('#qrcode_box img').length=='0'){\n" +
            "                \tvar local_url = window.location.href;\n" +
            "                \tvar qrcode_url = '/qr/index.php?data='+local_url;\n" +
            "                \tjQ.get(qrcode_url,function(data){\n" +
            "                \t\tjQ('#qrcode_box').html(data).css({'top':'-130px'});\n" +
            "                \t\tjQ('.tools-qrcode').css({'overflow':'visible'});\n" +
            "                \t})\n" +
            "                \t}else{\n" +
            "                jQ('#qrcode_box').css({'top':'-130px'});\n" +
            "                jQ('.tools-qrcode').css({'overflow':'visible'});\n" +
            "                }\n" +
            "                \t}else {\n" +
            "                jQ('#qrcode_box').css({'top':'-160px'});\n" +
            "                jQ('.tools-qrcode').css({'overflow':'hidden'});\n" +
            "                \t}\n" +
            "                })\n" +
            "            </script>\n" +
            "        </div>\n" +
            "    </div>        <a href=\"home.php?mod=spacecp&amp;ac=favorite&amp;type=article&amp;id=13380&amp;handlekey=favoritearticlehk_13380\"\n" +
            "       id=\"a_favorite\" onclick=\"showWindow(this.id, this.href, 'get', 0);\" class=\"oshr2 ofav\">收藏</a>\n" +
            "    \n" +
            "    <script type=\"text/javascript\" id=\"hxshare_js\"></script>\n" +
            "    <script type=\"text/javascript\">\n" +
            "        document.getElementById(\"hxshare_js\").src = \"static/js/hxshare_num.js?cdnversion=\" + new Date().getHours();\n" +
            "    </script>\n" +
            "\n" +
            "\n" +
            "                        <a href=\"home.php?mod=spacecp&amp;ac=click&amp;op=add&amp;clickid=1&amp;idtype=aid&amp;id=13380&amp;hash=0abc4055d360ebe63037445e8b37c905&amp;handlekey=clickhandle\"\n" +
            "       id=\"click_aid_13380_1\"\n" +
            "       onclick=\"showWindow(this.id, this.href);doane(event);\" class=\"oshr2\">没劲            </a>\n" +
            "                    <a href=\"home.php?mod=spacecp&amp;ac=click&amp;op=add&amp;clickid=2&amp;idtype=aid&amp;id=13380&amp;hash=0abc4055d360ebe63037445e8b37c905&amp;handlekey=clickhandle\"\n" +
            "       id=\"click_aid_13380_2\"\n" +
            "       onclick=\"showWindow(this.id, this.href);doane(event);\" class=\"oshr2\">喜欢            </a>\n" +
            "        </div>            \t\t                                    \n" +
            "<div class=\"neirong-pl\">\n" +
            "    <div class=\"comment-lg cl\"><span class=\"z\"><a href=\"comment/13380/1.html\">全部评论(<span class=\"rep-num\">0</span>)</a></span></div>\n" +
            "    <div id=\"comment\" class=\"comment-pl-new\">\n" +
            "        <div id=\"comment_ul\">\n" +
            "            <div class=\"pl-login\">\n" +
            "<p>参与讨论，请先<a href=\"member.php?mod=logging&amp;action=login\" onclick=\"showWindow('login', this.href)\">登录</a>|<a href=\"member.php?mod=register\">注册</a></p>\n" +
            "<p class=\"p3\">\n" +
            "<a rel=\"nofollow\" target=\"_top\" href=\"http://www.huxiu.com/connect.php?mod=login&amp;op=init&amp;referer=http%3A%2F%2Fwww.huxiu.com%2Farticle%2F13380%2F1.html&amp;statfrom=login\"><img class=\"vm\" src=\"static/image/common/qq_login.gif\"></a>\n" +
            "<a alt=\"一步搞定\" rel=\"nofollow\" href=\"xwb.php?m=xwbAuth.login\"><img class=\"vm\" onerror=\"this.onerror=null;this.src='static/image/common/none.gif'\" src=\"xwb/images/bgimg/sina_login_btn.png\"></a>\n" +
            "</p>\n" +
            "</div>\n" +
            "\n" +
            "\n" +
            "\n" +
            "<script src=\"/template/youliao/image/jquery.caret.js\" type=\"text/javascript\"></script>\n" +
            "<script src=\"/template/youliao/image/jquery.atwho.js\" type=\"text/javascript\"></script>\n" +
            "<script src=\"/template/youliao/image/autoresize.min.js\" type=\"text/javascript\"></script>\n" +
            "<script src=\"/template/youliao/image/hx_j.js\" type=\"text/javascript\"></script>\n" +
            "<script src=\"/template/youliao/image/hx_lottery.js\" type=\"text/javascript\"></script>                    </div>\n" +
            "    </div>\n" +
            "    </div>\n" +
            "\n" +
            "            \n" +
            "            <!--[diy=diycontentcomment]-->\n" +
            "            <div id=\"diycontentcomment\" class=\"area\"></div>\n" +
            "            <!--[/diy]-->\n" +
            "        </div>\n" +
            "    </div>\n" +
            "    <div class=\"w-short2 y\">\n" +
            "\t\t\t<div class=\"box-style one-author\">\n" +
            "<h2>作者：<span class=\"author\"><a href=\"space/uid/10025/1.html\">云瑞</a></span></h2>\n" +
            "<a href=\"space/uid/10025/1.html\" class=\"a-img\"><img src=\"http://user.huxiu.com/auth/data/avatar/000/01/00/25_avatar_middle.jpg\" /></a>\n" +
            "<div class=\"author-other\">\n" +
            "</div>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"box-style author-article\">\n" +
            "<div class=\"author-yijuha\">\n" +
            "<dl>\n" +
            "<dt>个人签名</dt>\n" +
            "<dd>人人都爱互联网</dd>\n" +
            "</dl>\n" +
            "</div>\n" +
            "\t\t\t\t\n" +
            "<h3>作者其他文章</h3>\n" +
            "<ul><li><a href=\"article/11016/1.html\">从Google+、Facebook、Twitter、 Youtube看，设计怎么回归产品本身</a></li>\n" +
            "<li><a href=\"article/10291/1.html\">利用认知盈余创业需要考虑的几个因素</a></li>\n" +
            "<li><a href=\"article/9811/1.html\">关于游戏社区的一些思考</a></li>\n" +
            "<li><a href=\"article/8242/1.html\">产品设计：抄什么，不抄什么</a></li>\n" +
            "<li><a href=\"article/5553/1.html\">啪啪应走社交路线</a></li>\n" +
            "</ul>\n" +
            " \n" +
            "<a href=\"space/uid/10025/1.html\" class=\"a-more\">更多文章</a>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"box-style other-imglist other-author\">\n" +
            "<h3 class=\"t-h2\">您不能错过的作者</h3>\n" +
            "<ul><li>\n" +
            "<p class=\"p1\"><a href=\"space/uid/19830/1.html\" title=\"葛甲\" yijuhua=\"新闻网站主编，互联网分析师\"><img src=\"http://user.huxiu.com/auth/avatar.php?uid=19830&size=small\" alt=\"葛甲\"></a></p>\n" +
            "<p class=\"p2\"><a href=\"space/uid/19830/1.html\">葛甲</a></p>\n" +
            "</li>\n" +
            "<li>\n" +
            "<p class=\"p1\"><a href=\"space/uid/21531/1.html\" title=\"吴澍\" yijuhua=\"微博：@吴澍Roger 不想当记者的销售，不是好分析师。混迹于虎嗅网，关注互联网、移动互联网大小事。求报道请Email：wwshsh@gmail.com\"><img src=\"http://user.huxiu.com/auth/avatar.php?uid=21531&size=small\" alt=\"吴澍\"></a></p>\n" +
            "<p class=\"p2\"><a href=\"space/uid/21531/1.html\">吴澍</a></p>\n" +
            "</li>\n" +
            "<li>\n" +
            "<p class=\"p1\"><a href=\"space/uid/20707/1.html\" title=\"知乎精选\" yijuhua=\"精彩问答，发现更大的世界\"><img src=\"http://user.huxiu.com/auth/avatar.php?uid=20707&size=small\" alt=\"知乎精选\"></a></p>\n" +
            "<p class=\"p2\"><a href=\"space/uid/20707/1.html\">知乎精选</a></p>\n" +
            "</li>\n" +
            "<li>\n" +
            "<p class=\"p1\"><a href=\"space/uid/16169/1.html\" title=\"译言\" yijuhua=\"发现 翻译 阅读中文之外的互联网精华\"><img src=\"http://user.huxiu.com/auth/avatar.php?uid=16169&size=small\" alt=\"译言\"></a></p>\n" +
            "<p class=\"p2\"><a href=\"space/uid/16169/1.html\">译言</a></p>\n" +
            "</li>\n" +
            "<li>\n" +
            "<p class=\"p1\"><a href=\"space/uid/9/1.html\" title=\"潘乱\" yijuhua=\"虎嗅网编辑，微博@潘乱兄\"><img src=\"http://user.huxiu.com/auth/avatar.php?uid=9&size=small\" alt=\"潘乱\"></a></p>\n" +
            "<p class=\"p2\"><a href=\"space/uid/9/1.html\">潘乱</a></p>\n" +
            "</li>\n" +
            "<li>\n" +
            "<p class=\"p1\"><a href=\"space/uid/37627/1.html\" title=\"王云辉\" yijuhua=\"《财经国家周刊》科技工作室主任\"><img src=\"http://user.huxiu.com/auth/avatar.php?uid=37627&size=small\" alt=\"王云辉\"></a></p>\n" +
            "<p class=\"p2\"><a href=\"space/uid/37627/1.html\">王云辉</a></p>\n" +
            "</li>\n" +
            "<li>\n" +
            "<p class=\"p1\"><a href=\"space/uid/29318/1.html\" title=\"阑夕\" yijuhua=\"社交媒体从业者 | 微博：weibo.com/foxshuo\"><img src=\"http://user.huxiu.com/auth/avatar.php?uid=29318&size=small\" alt=\"阑夕\"></a></p>\n" +
            "<p class=\"p2\"><a href=\"space/uid/29318/1.html\">阑夕</a></p>\n" +
            "</li>\n" +
            "<li>\n" +
            "<p class=\"p1\"><a href=\"space/uid/28969/1.html\" title=\"胡晓东\" yijuhua=\"多看科技副总裁，一个好奇的人\"><img src=\"http://user.huxiu.com/auth/avatar.php?uid=28969&size=small\" alt=\"胡晓东\"></a></p>\n" +
            "<p class=\"p2\"><a href=\"space/uid/28969/1.html\">胡晓东</a></p>\n" +
            "</li>\n" +
            "<li>\n" +
            "<p class=\"p1\"><a href=\"space/uid/10473/1.html\" title=\"阳淼\" yijuhua=\"身无彩凤双飞翼，胸有猛虎嗅蔷薇\"><img src=\"http://user.huxiu.com/auth/avatar.php?uid=10473&size=small\" alt=\"阳淼\"></a></p>\n" +
            "<p class=\"p2\"><a href=\"space/uid/10473/1.html\">阳淼</a></p>\n" +
            "</li>\n" +
            "<li>\n" +
            "<p class=\"p1\"><a href=\"space/uid/10246/1.html\" title=\"魏武挥\" yijuhua=\"新媒体观察者,上海交通大学媒体与设计学院,教师\"><img src=\"http://user.huxiu.com/auth/avatar.php?uid=10246&size=small\" alt=\"魏武挥\"></a></p>\n" +
            "<p class=\"p2\"><a href=\"space/uid/10246/1.html\">魏武挥</a></p>\n" +
            "</li>\n" +
            "<li>\n" +
            "<p class=\"p1\"><a href=\"space/uid/21041/1.html\" title=\"高低买个皮夹克\" yijuhua=\"我點個喜歡，你感受一下。\"><img src=\"http://user.huxiu.com/auth/avatar.php?uid=21041&size=small\" alt=\"高低买个皮夹克\"></a></p>\n" +
            "<p class=\"p2\"><a href=\"space/uid/21041/1.html\">高低买个皮夹克</a></p>\n" +
            "</li>\n" +
            "<li>\n" +
            "<p class=\"p1\"><a href=\"space/uid/26907/1.html\" title=\"潘越飞\" yijuhua=\"喜欢的东西千奇百怪 浙报集团传媒梦工场人\"><img src=\"http://user.huxiu.com/auth/avatar.php?uid=26907&size=small\" alt=\"潘越飞\"></a></p>\n" +
            "<p class=\"p2\"><a href=\"space/uid/26907/1.html\">潘越飞</a></p>\n" +
            "</li>\n" +
            "\t\n" +
            "</ul>\n" +
            "</div>\n" +
            "\n" +
            "            <!-- baifendian -->\t\t\n" +
            "<div id=\"bfd_vav\" style=\"margin-left:15px;\"></div>\n" +
            "<!-- baifendian -->\n" +
            "\n" +
            "    </div>\n" +
            "    <div class=\"cl\"></div>\n" +
            "\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"wp mtn\">\n" +
            "    <!--[diy=diy3]-->\n" +
            "    <div id=\"diy3\" class=\"area\"></div>\n" +
            "    <!--[/diy]-->\n" +
            "</div>\n" +
            "<input type=\"hidden\" id=\"portalview\" value=\"1\">\n" +
            "\n" +
            "<!-- baifendian start -->\n" +
            "<script type=\"text/javascript\">\n" +
            "var bfd_script = document.createElement('script');\n" +
            "bfd_script.setAttribute('type',\"text/javascript\");\n" +
            "bfd_script.setAttribute('charset',\"utf-8\");\n" +
            "bfd_script.setAttribute('src',\"http://static.baifendian.com/service/Trial_Chuxiu/bfd-service-min.js\");\n" +
            "document.getElementsByTagName('head')[0].appendChild(bfd_script);\n" +
            "</script>\n" +
            "<!-- baifengdian end -->\t</div>\n" +
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
            "\t\t\t\tvar ts = '1366674171';\n" +
            "\t\t\t\tvar sig = 'c6a3f857e7fa50dadbca6d91ee7236f7';\n" +
            "\t\t\t\tvar discuzTipsCVersion = '2';\n" +
            "\t\t\t</script>\n" +
            "\t\t\t<script src=\"http://discuz.gtimg.cn/cloud/scripts/discuz_tips.js?v=1\" type=\"text/javascript\" charset=\"UTF-8\"></script><script src=\"http://stat.huxiu.com/0/13380.js?uid=0&aid=13380&p=0\" type=\"text/javascript\"></script></body>\n" +
            "</html>\n";

    String blogHtml = "\n" +
            "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
            "<html lang='zh-CN' xml:lang='zh-CN' xmlns='http://www.w3.org/1999/xhtml'>\n" +
            "<head>\n" +
            "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\n" +
            "  <meta http-equiv=\"Content-Language\" content=\"zh-CN\"/>\n" +
            "  <meta name=\"robots\" content=\"index, follow\" />\n" +
            "  <link rel=\"shortcut icon\" type=\"image/x-icon\" href=\"/img/favicon.ico\" />\n" +
            "  <title>一个基于Python装饰器的用户输入验证设计方案 -  SamChi的个人空间 - 开源中国社区</title>\n" +
            "      <meta name=\"Description\" content=\"一个基于Python装饰器的用户输入验证设计方案：情景 最近初学Python, 语法大概熟悉了之后就开始拿web.py做点小东西，web.py非常轻量，用起来感觉很舒服...\"/>\n" +
            "    <link rel=\"stylesheet/less\" href=\"http://my.oschina.net/chihz/styles.less?ver=20120913\" type=\"text/css\" media=\"screen\" />\n" +
            "  <link rel=\"stylesheet\" href=\"/js/2012/poshytip/tip-yellowsimple/tip-yellowsimple.css\" type=\"text/css\" />\n" +
            "  <link rel=\"stylesheet\" type=\"text/css\" href=\"/js/2011/fancybox/jquery.fancybox-1.3.4.css\" media=\"screen\" />\n" +
            "  <script type=\"text/javascript\" src=\"/js/2012/jquery.form.js\"></script>\n" +
            "  <script type=\"text/javascript\" src=\"/js/2012/jquery-1.7.1.min.js\"></script>\n" +
            "  <script type=\"text/javascript\" src=\"/js/2011/fancybox/jquery.fancybox-1.3.4.pack.js\"></script>\n" +
            "  <script type=\"text/javascript\" src=\"/js/2012/poshytip/jquery.poshytip.min.js\"></script>\n" +
            "  <script type=\"text/javascript\" src=\"/js/2011/oschina.js?ver=20121007\"></script>\n" +
            "  <script type=\"text/javascript\" src=\"/js/2012/less-1.3.0.min.js\"></script>\n" +
            "  <script type=\"text/javascript\" src=\"/js/scrolltopcontrol.js\"></script>\n" +
            "  <script type='text/javascript' src='/js/jquery/jquery.atwho.js'></script>\n" +
            "  <link rel=\"stylesheet\" type=\"text/css\" href=\"/js/jquery/jquery.atwho.css\" />\n" +
            "  <link rel=\"alternate\" type=\"application/rss+xml\" title=\"SamChi最新博客\" href=\"http://my.oschina.net/chihz/rss\" />\n" +
            "  <link rel=\"EditURI\" type=\"application/rsd+xml\" title=\"RSD\" href=\"http://my.oschina.net/action/xmlrpc/rsd?space=98039\" />\n" +
            "  <link rel=\"wlwmanifest\" type=\"application/wlwmanifest+xml\" href=\"http://my.oschina.net/action/xmlrpc/wlwmanifest?space=98039\" /> \n" +
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
            "        \t\t<div id='OSC_Slogon'>JetBrains 开发工具全场3折，<a href='http://www.oschina.net/shop/jetbrains' target='_blank'>详情&raquo;</a></div>\n" +
            "        <div id=\"OSC_Channels\">\n" +
            "        \t<ul>\n" +
            "        \t<li><a href=\"http://www.oschina.net/project\" class='software'>软件</a></li>\n" +
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
            "\t\t\t\t黄亿华 [ <a href=\"http://my.oschina.net/flashsword\">我的空间</a> | <a href=\"/action/user/logout?session=6db40e6e2d1061998068&goto_page=http%3A%2F%2Fmy.oschina.net%2Fchihz\">退出</a> ]\n" +
            "\t\t\t\t<span id=\"OSC_Notification\">\t\t\t\n" +
            "\t\t\t\t\t\t<a href=\"http://my.oschina.net/flashsword/admin/inbox\" class=\"msgbox\" title=\"进入我的留言箱\">你有<em>0</em>新留言</a>\t\t\t\n" +
            "\t\t\t\t\t\t\t\t</span>\n" +
            "\t\t</div>\n" +
            "\t\t<div id=\"SearchBar\">\n" +
            "    \t\t<form action=\"http://www.oschina.net/search\">\n" +
            "\t\t\t\t<input type='hidden' name='user' value='98039'/>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"ipt f_l\">\n" +
            "    \t\t\t<input type='text' id='txt_q' name='q' class='SERACH' value='在 25208 款开源软件中搜索' onblur=\"(this.value=='')?this.value='在 25208 款开源软件中搜索':this.value\" onfocus=\"if(this.value=='在 25208 款开源软件中搜索'){this.value='';};this.select();\"/>\n" +
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
            "\t    <a href=\"http://my.oschina.net/chihz\" class='Img'><img src=\"http://static.oschina.net/uploads/user/49/98039_100.jpg?t=1347253975000\" align=\"absmiddle\" alt=\"SamChi\" title=\"SamChi\" class=\"LargePortrait\"/></a>\n" +
            "    <span class='U'>\n" +
            "        <a href=\"http://my.oschina.net/chihz\" class='Name' title='男'>SamChi</a>\n" +
            "\t\t<span class='opts'>\n" +
            "\t\t\t<img src=\"/img/2012/men.png\" align='absmiddle' title='男'/>\n" +
            "        \t\t\t<script type='text/javascript'>\n" +
            "var nologin = \"<a href='javascript:_follow_nologin()' title='成为TA的粉丝'>关注此人</a>\"\n" +
            "var follow0 = \"<a href='javascript:_follow_user(98039,true)' title='成为TA的粉丝'>关注此人</a>\";\n" +
            "var follow1 = \"已关注 | <a href='javascript:_follow_user(98039,false)' title='取消关注后，此人的更新信息将不会出现在你的首页'>取消</a>\";\n" +
            "var follow2 = \"互相关注 | <a href='javascript:_follow_user(98039,false)' title='取消关注后，此人的更新信息将不会出现在你的首页'>取消</a>\";\n" +
            "var follow4 = \"<a href='javascript:_follow_user(98039,true)' title='成为TA的粉丝'>+关注此人</a>\";\n" +
            "\n" +
            "document.write(\"<span id='_follow_98039_action_'>\");\n" +
            "\t\t\tdocument.write(follow2);\n" +
            "\tdocument.write(\"</span>\");\n" +
            "\n" +
            "function _follow_nologin(){\n" +
            "\tif(confirm('必须登录后才能关注，是否现在登录？')){\n" +
            "\t\tlocation.href=\"http://www.oschina.net/home/login?goto_page=\"+location.href;\n" +
            "\t}\n" +
            "}\n" +
            "\n" +
            "function _follow_user(uid, follow){\n" +
            "\tvar action = follow?\"/action/user/follow\":\"/action/user/unfollow\";\n" +
            "\tajax_post(action,\"id=\"+uid+\"&user=190591\",function(result){\n" +
            "\t\tvar act = $('#_follow_98039_action_');\n" +
            "\t\tif(result == '0')\n" +
            "\t\t\t_follow_nologin();\n" +
            "\t\telse if(result == '1')\n" +
            "\t\t\tact.html(follow0);\n" +
            "\t\telse if(result == '2')\n" +
            "\t\t\tact.html(follow1);\n" +
            "\t\telse if(result == '3')\n" +
            "\t\t\tact.html(follow2);\n" +
            "\t\telse if(result == '4')\n" +
            "\t\t\tact.html(follow4);\n" +
            "\t});\n" +
            "}\n" +
            "</script>\t\t\t\t</span>\n" +
            "    </span>\n" +
            "    <div class='clear'></div>\n" +
            "    <div class='stat'>\n" +
            "    \t<a href=\"http://my.oschina.net/chihz/fellow\">关注(21)</a>\n" +
            "    \t<a href=\"http://my.oschina.net/chihz/fans\">粉丝(52)</a>\n" +
            "    \t<a href=\"http://www.oschina.net/question/3307_20931\" title=\"查看OSCHINA积分规则\">积分(37)</a>\n" +
            "    </div>\n" +
            "</div><style>\n" +
            "#MyResume textarea {width:170px;height:60px;font-size:9pt;}\n" +
            "</style>\n" +
            "<div class='Resume' id='MyResume'>\n" +
            "这个人很懒，啥也没写</div>\n" +
            "\n" +
            "<div class='Opts clearfix'>\n" +
            "\t<a href=\"javascript:sendmsg(98039, 0)\" class='a1 sendmsg'><i>.</i><span>发送留言</span></a>\n" +
            "\t<a href=\"http://www.oschina.net/question/ask?user=98039\" class='a2 ask'><i>.</i><span>请教问题</span></a>\n" +
            "</div><div class=\"Mod\" id=\"BlogCatalogs\">\n" +
            "  <strong> 博客分类</strong>\n" +
            "  <ul>\n" +
            "\t    \t<li><a href=\"http://my.oschina.net/chihz/blog?catalog=162794\">Java</a><span>(5)</span></li>\n" +
            "\t\t<li><a href=\"http://my.oschina.net/chihz/blog?catalog=243637\">Linux</a><span>(8)</span></li>\n" +
            "\t\t<li><a href=\"http://my.oschina.net/chihz/blog?catalog=230143\">行业杂谈</a><span>(2)</span></li>\n" +
            "\t\t<li><a href=\"http://my.oschina.net/chihz/blog?catalog=226001\">Flash技术</a><span>(2)</span></li>\n" +
            "\t\t<li><a href=\"http://my.oschina.net/chihz/blog?catalog=180478\">Web开发</a><span>(1)</span></li>\n" +
            "\t\t<li><a href=\"http://my.oschina.net/chihz/blog?catalog=178141\">生活</a><span>(2)</span></li>\n" +
            "\t\t<li><a href=\"http://my.oschina.net/chihz/blog?catalog=173386\">Lucene</a><span>(1)</span></li>\n" +
            "\t\t<li><a href=\"http://my.oschina.net/chihz/blog?catalog=172860\">程序员漫画</a><span>(2)</span></li>\n" +
            "\t\t<li><a href=\"http://my.oschina.net/chihz/blog?catalog=319174\">Python</a><span>(3)</span></li>\n" +
            "\t\t<li><a href=\"http://my.oschina.net/chihz/blog?catalog=170099\">并发编程</a><span>(1)</span></li>\n" +
            "\t  </ul>\n" +
            "</div><div class=\"Mod\" id=\"HotBlogs\">\n" +
            "  <strong>阅读排行</strong>\n" +
            "  <ol>\n" +
            "\t\t\t<li><a href=\"http://my.oschina.net/chihz/blog/96101\">1. 玩Linux笔记(2) —— 神奇的curl工具</a></li>\n" +
            "\t\t\t\t<li><a href=\"http://my.oschina.net/chihz/blog/77557\">2. Java程序员的ActionScript3学习笔记(一)</a></li>\n" +
            "\t\t\t\t<li><a href=\"http://my.oschina.net/chihz/blog/56256\">3. 对几个通用的Java hashCode重写方案的一些思考和探讨</a></li>\n" +
            "\t\t\t\t<li><a href=\"http://my.oschina.net/chihz/blog/58057\">4. Http协议中的各种长度限制总结</a></li>\n" +
            "\t\t\t\t<li><a href=\"http://my.oschina.net/chihz/blog/55658\">5. 基于Java的日志分析工具——LogAnalyzerFramework，小轮子一个</a></li>\n" +
            "\t\t\t\t<li><a href=\"http://my.oschina.net/chihz/blog/52913\">6. Java通过Guava库来简单实现函数式的编码风格</a></li>\n" +
            "\t\t\t\t<li><a href=\"http://my.oschina.net/chihz/blog/76173\">7. 让Spring BeanPropertyRowMapper支持枚举类型</a></li>\n" +
            "\t\t\t\t<li><a href=\"http://my.oschina.net/chihz/blog/55600\">8. 几则程序员励志漫画，原创哦，哈哈</a></li>\n" +
            "\t\t  </ol>\n" +
            "</div>\n" +
            "<div class=\"Mod\" id=\"BlogReplies\">\n" +
            "  <strong> 最新评论</strong>  \n" +
            "      <ul>\n" +
            "\t\t<li>\n" +
            "\t\t<a href=\"http://my.oschina.net/wensiqun\">@其斤君羊</a>：说的很对 做什么事情都得从身边做起 更何况创业 ...\n" +
            "\t\t<a href=\"/action/tweet/go?obj=272980924&type=18&user=238125\">查看&raquo;</a>\n" +
            "\t</li>\n" +
            "\t\t<li>\n" +
            "\t\t<a href=\"http://my.oschina.net/techstan\">@techstan</a>：不错\n" +
            "\t\t<a href=\"/action/tweet/go?obj=272973457&type=18&user=244335\">查看&raquo;</a>\n" +
            "\t</li>\n" +
            "\t\t<li>\n" +
            "\t\t<a href=\"http://my.oschina.net/moooofly\">@摩云飞</a>：谢谢博主的总结，很有价值\n" +
            "\t\t<a href=\"/action/tweet/go?obj=272923714&type=18&user=617889\">查看&raquo;</a>\n" +
            "\t</li>\n" +
            "\t\t<li>\n" +
            "\t\t<a href=\"http://my.oschina.net/u/566875\">@尚楠</a>：正在学Python，谢了\n" +
            "\t\t<a href=\"/action/tweet/go?obj=272922563&type=18&user=566875\">查看&raquo;</a>\n" +
            "\t</li>\n" +
            "\t\t<li>\n" +
            "\t\t<a href=\"http://my.oschina.net/knightuniverse\">@knightuniverse</a>：其实我觉得，很多时候，不论是做项目还是做产品，...\n" +
            "\t\t<a href=\"/action/tweet/go?obj=272775378&type=18&user=1015990\">查看&raquo;</a>\n" +
            "\t</li>\n" +
            "\t\t<li>\n" +
            "\t\t<a href=\"http://my.oschina.net/u/855506\">@moyun</a>：顶一个\n" +
            "\t\t<a href=\"/action/tweet/go?obj=272770753&type=18&user=855506\">查看&raquo;</a>\n" +
            "\t</li>\n" +
            "\t\t<li>\n" +
            "\t\t<a href=\"http://my.oschina.net/chihz\">@SamChi</a>：引用来自“Martinium”的评论 alert('I am admi...\n" +
            "\t\t<a href=\"/action/tweet/go?obj=271165670&type=18&user=98039\">查看&raquo;</a>\n" +
            "\t</li>\n" +
            "\t\t<li>\n" +
            "\t\t<a href=\"http://my.oschina.net/u/271920\">@Martinium</a>：alert('I am admin, bitch!'); 这句话亮了。...\n" +
            "\t\t<a href=\"/action/tweet/go?obj=271162255&type=18&user=271920\">查看&raquo;</a>\n" +
            "\t</li>\n" +
            "\t\t<li>\n" +
            "\t\t<a href=\"http://my.oschina.net/singledu\">@Ben</a>：引用来自“ExtremeTalk”的评论 引用来自“Ben”...\n" +
            "\t\t<a href=\"/action/tweet/go?obj=271145611&type=18&user=584808\">查看&raquo;</a>\n" +
            "\t</li>\n" +
            "\t\t<li>\n" +
            "\t\t<a href=\"http://my.oschina.net/u/168138\">@ExtremeTalk</a>：引用来自“Ben”的评论 引用来自“ExtremeTalk”...\n" +
            "\t\t<a href=\"/action/tweet/go?obj=271144794&type=18&user=168138\">查看&raquo;</a>\n" +
            "\t</li>\n" +
            "\t  </ul>\n" +
            "  </div><div class='Mod' id='Stat'>\n" +
            "<strong>访客统计</strong>\n" +
            "<ul>\n" +
            "\t<li><label>今日访问：</label>3</li>\n" +
            "    <li><label>昨日访问：</label>33</li>\n" +
            "    <li><label>本周访问：</label>36</li>\n" +
            "    <li><label>本月访问：</label>842</li>\n" +
            "    <li><label>所有访问：</label>13706</li>\n" +
            "</ul>\n" +
            "</div></div>\n" +
            "\n" +
            "\n" +
            "<div class='SpaceList'>\n" +
            "\t<div class='TopBar'>\n" +
            "    \t<div class='NavPath'>\t\t\n" +
            "    \t\t<a href='http://my.oschina.net/chihz/home'>空间</a> &raquo; <a href='http://my.oschina.net/chihz/blog'>博客</a>\t\t\t\n" +
            "\t\t\t&raquo; <a href=\"http://my.oschina.net/chihz/blog?catalog=319174\">Python</a>\n" +
            "\t\t\t&raquo; 博客正文\n" +
            "    \t</div>\n" +
            "\t</div>\n" +
            "\t\n" +
            "    \t<div class='BlogEntity'>\t\t\n" +
            "      <div class='BlogTitle'>\n" +
            "        <h1><img src='/img/space/b1.gif' align='absmiddle'/> 一个基于Python装饰器的用户输入验证设计方案</h1>\n" +
            "        <div class='BlogStat'>\n" +
            "    \t\t    \t\t    \t    <span class='admin'>\n" +
            "\t\t\t\t<style>\n" +
            "#favor_form{width:200px;}\n" +
            "#favor_form p {color:#666;}\n" +
            "#favor_form form{height:60px;width:200px;}\n" +
            "#favor_form form ._favor_input{display:block;margin:2px 0;width:199px;}\n" +
            "#favor_form form ._favor_button{float:left;padding:2px 5px;}\n" +
            ".favor_ok {text-align:center;font-size:10.5pt;width:199px;height:60px;margin-top:10px;}\n" +
            "#TagsSwitcher{cursor:pointer;float:right;margin-top:10px;}\n" +
            "#MyTags{display:none;width:199px;}\n" +
            "#MyTags a.tag {float:left; background-color: #E0EAF1;border-bottom: 1px solid #3E6D8E;border-right: 1px solid #7F9FB6;color: #3E6D8E;font-size: 8pt;line-height: 16px;margin: 2px 2px 2px 0;padding: 2px 4px;text-decoration: none;white-space: nowrap;}\n" +
            "</style>\n" +
            "<script type='text/javascript'>\n" +
            "var favor_add = \"<a href='javascript:add_to_favor(122897,3)' id='favor_trigger' title='添加到收藏夹'>我要收藏</a>\";\n" +
            "var favor_del = \"<a href='javascript:delete_favor(122897,3)' id='favor_trigger' style='color:#a00;'>取消收藏</a>\";\n" +
            "var favor_ok = \"<p class='favor_ok'>已成功添加到收藏夹<br/><br/> <a href='http://my.oschina.net/flashsword/favorites?type=3'>我的收藏夹</a> | <a href='javascript:close_favor()'>关闭</a></p>\";\n" +
            "function delete_favor(obi_id, obj_type){\n" +
            "\t$('#attention_it').html(favor_add);\n" +
            "\t$(\"#p_attention_count\").load(\"/action/favorite/cancel?type=\"+obj_type+\"&id=\"+obi_id, {user: '190591'});\n" +
            "}\n" +
            "function add_to_favor(obj_id, obj_type){\n" +
            "    var dlg_favor = \"<div id='favor_form'><p>多个标签使用逗号(,)隔开，最多三个</p><form action='/action/favorite/add?user=190591' height='60px' width='200px' method='POST'>\";\n" +
            "\tdlg_favor += \"<input type='hidden' name='id' value='\"+obj_id+\"'/>\";\n" +
            "\tdlg_favor += \"<input type='hidden' name='type' value='\"+obj_type+\"'/>\";\n" +
            "\tdlg_favor += \"<input type='text' name='tags' size='25' class='_favor_input' id='_favor_tags'/>\";\n" +
            "\tdlg_favor += \"<input type='submit' value='收藏' class='_favor_button'/><input type='button' value='取消' onclick='close_favor();' class='_favor_button'/><a id='TagsSwitcher' state='off'>选取标签↓</a></form>\";\n" +
            "\tdlg_favor += \"<div id='MyTags' ></div></div>\";\n" +
            "    $('#favor_trigger').poshytip('destroy');\n" +
            "    $('#favor_trigger').poshytip({\n" +
            "    \tclassName: 'tip-yellowsimple',\n" +
            "    \tcontent: dlg_favor,\n" +
            "    \tshowOn: 'none',\n" +
            "    \talignTo: 'target',\t\n" +
            "\t\talignX: 'inner-right',\n" +
            "\t\talignY: 'bottom',\n" +
            "\t\toffsetX: 0,\n" +
            "\t\toffsetY: 5\n" +
            "    });\n" +
            "    $('#favor_trigger').poshytip('show');\n" +
            "\t$('#_favor_tags').focus();\n" +
            "\t$('#favor_form form').bind('form-pre-serialize', function(event,form,options,veto){\n" +
            "    \tvar tags= $('#_favor_tags')\n" +
            "\t\tvar tagValue= tags.val();\n" +
            "\t\t//处理tags前后包含的空格\n" +
            "\t\tvar finalTags = new Array();\n" +
            "\t\tvar tagIdx = 0;\n" +
            "\t\tvar arr = tagValue.split(',');\n" +
            "\t\tfor(var i=0;i<arr.length;i++){\n" +
            "\t\t\tvar tag = arr[i];\n" +
            "\t\t\ttag = trim(tag);\n" +
            "\t\t\tif(tag.length>0){\n" +
            "\t\t\t\tfinalTags[finalTags.length]=tag;\n" +
            "\t\t\t}\n" +
            "\t\t}\n" +
            "\t\tvar d = \"\";\n" +
            "\t\tfor(var idx=0;idx<finalTags.length;idx++){\n" +
            "\t\t\td+=finalTags[idx];\n" +
            "\t\t\tif(idx<finalTags.length-1)\n" +
            "\t\t\t\td+=\",\";\n" +
            "\t\t}\n" +
            "\t\ttags.val(d);\n" +
            "\t});\n" +
            "\n" +
            "\t$('#favor_form form').ajaxForm({\n" +
            "\t\tsuccess: function(html) {\n" +
            "\t\t\t$('#favor_trigger').html('取消收藏');\n" +
            "\t\t\t$('#favor_trigger').attr('href','javascript:delete_favor(122897,3)');\n" +
            "\t\t\t$('#p_attention_count').html(html);\n" +
            "\t\t\t$('#favor_form').html(favor_ok);\n" +
            "\t\t\tsetTimeout(\"close_favor()\",3000);\n" +
            "\t\t}\n" +
            "\t});\n" +
            "\t$(\"#TagsSwitcher\").one(\"click\",function(){\n" +
            "\t\t//加载标签数据\n" +
            "\t\t$(\"#MyTags\").load('/action/ajax/get_my_tags');\n" +
            "       \t$(\"#MyTags\").toggle();\n" +
            "\t\t$(this).html(\"收起标签↑\");\n" +
            "        $(this).attr(\"state\",'on');\n" +
            "        $(this).click(function(){\n" +
            "        \t$(\"#MyTags\").toggle();\n" +
            "        \tvar state = $(this).attr(\"state\");\n" +
            "        \tif(state=='off'){\n" +
            "        \t\t$(this).html(\"收起标签↑\");\n" +
            "        \t\t$(this).attr(\"state\",'on');\n" +
            "        \t}else{\n" +
            "        \t\t$(this).html(\"选取标签↓\");\n" +
            "        \t\t$(this).attr(\"state\",'off');\n" +
            "        \t}\n" +
            "        });\n" +
            "\t});\n" +
            "\n" +
            "}\n" +
            "function close_favor(elem_id){\n" +
            "    $('#favor_trigger').poshytip('destroy');\n" +
            "}\n" +
            "function setTag(tv){\n" +
            "\tvar t = $(\"._favor_input\");\n" +
            "\tvar value = t.val();\n" +
            "\tif(value!=\"\")\n" +
            "\t\tt.val(value+\",\"+tv);\n" +
            "\telse\n" +
            "\t\tt.val(tv)\n" +
            "}\n" +
            "function ltrim(str){\n" +
            "    var whitespace = new String(\" \\t\\n\\r\");\n" +
            "    var s = new String(str);\n" +
            "    if (whitespace.indexOf(s.charAt(0)) != -1){\n" +
            "        var j=0, i = s.length;\n" +
            "        while (j < i && whitespace.indexOf(s.charAt(j)) != -1){\n" +
            "            j++;\n" +
            "        }\n" +
            "        s = s.substring(j, i);\n" +
            "    }\n" +
            "    return s;\n" +
            "}\n" +
            "\n" +
            "function rtrim(str){\n" +
            "    var whitespace = new String(\" \\t\\n\\r\");\n" +
            "    var s = new String(str);\n" +
            "    if (whitespace.indexOf(s.charAt(s.length-1)) != -1){\n" +
            "        var i = s.length - 1;\n" +
            "        while (i >= 0 && whitespace.indexOf(s.charAt(i)) != -1){\n" +
            "            i--;\n" +
            "        }\n" +
            "        s = s.substring(0, i+1);\n" +
            "    }\n" +
            "    return s;\n" +
            "}\n" +
            " \n" +
            "function trim(str){\n" +
            "    return rtrim(ltrim(str));\n" +
            "}\n" +
            "</script>\n" +
            "<em id='p_attention_count'>8</em>人收藏此文章,\n" +
            "<span id='attention_it'>\n" +
            "\t<script type='text/javascript'>document.write(favor_add);</script>\n" +
            "</span>\t\t\t</span>\n" +
            "    \t\t    \t\t发表于7天前(2013-04-15 16:46) , \n" +
            "    \t\t已有<strong>127</strong>次阅读 ，共<strong><a href=\"#comments\">0</a></strong>个评论\n" +
            "    \t\t    \t</div> \n" +
            "      </div>\n" +
            "\t  <div class='BlogContent'><h2> 情景 </h2> \n" +
            "<p> 最近初学Python, 语法大概熟悉了之后就开始拿web.py做点小东西，web.py非常轻量，用起来感觉很舒服。但不过无论什么语言或者框架，web开发中有一个最大烦人之处就是表单验证，web.py提供了web.form来进行表单验证的统一处理，这个东西虽然用起来很简单，但是感觉还是不太合心意，首先这套验证机制跟web.py框架耦合的程度太高，而自己的架构是这样的，业务逻辑跟web逻辑完全分离，web仅仅是交互形式的一种，即使添加客户端C/S形式的服务或者是向开发者提供API，业务逻辑也是完全可用，不需要修改，这样对用户输入的验证是属于业务逻辑这一块，不应该跟web表单耦合在一起；另外感觉web.py这套东西还是有些简单，只支持每个表单的正则验证和最后表单提交的整体验证，而很多时候可能需要对用户进行丰富的错误提示，比如针对用户名的错误会具体到是不能为空还是长度错误或者格式错误等, 这个用web.py的form验证就感觉很别扭了。于是就决定自己设计一个用户输入的验证方案。 </p> \n" +
            "<h2> 设计 </h2> \n" +
            "<p> web项目的开发多数都是遵循这么一个结构的设计，即DAO-&gt;Service-&gt;Controller-&gt;View, 按我前面说的，对用户的输入验证应是发生在Service这一层上，这一层的设计是接受用户输入的参数，然后进行验证处理，再进行业务相关的计算，最后输入结果。每个Service接口都应该返回一个结果，我一般都会把这个结果的内容抽象成一个一致类型的对象: </p> \n" +
            "<pre class=\"brush:python; toolbar: true; auto-links: false;\">class Result(object):\n" +
            "    \n" +
            "    u''' 操作结果抽象 '''\n" +
            "    \n" +
            "    def __init__(self, code, value=None):\n" +
            "       &nbsp;self.code = code   #操作结果代号\n" +
            "        self.value = value #操作结果值\n" +
            "        \n" +
            "    def __str__(self):\n" +
            "        return &quot;operation result, code: %s, value: %s&quot; % (self.code, self.value)</pre> \n" +
            "<p> 这个结果对象包含两个属性，一个是操作结果的代码，一个是操作的值，举个例子，比如用户注册的接口，如果注册成功，那么就会返回一个这样的Result对象，code属性是'success', value属性是新注册用户分配的ID，如果用户名已经被占用，那么code属性就是'username_exised', value属性的值是None。客户端拿到code属性的值可以做响应的处理，如果是直接面向最终用户的web应用，那么就会去找到这个code对应的错误信息来展示给用户，所有的错误信息我是组织在一个单独的Python模块中(opresult.py): </p> \n" +
            "<pre class=\"brush:python; toolbar: true; auto-links: false;\">reg = {\n" +
            "       'success':u'注册成功',\n" +
            "       'username_empty':u'用户名不得为空',\n" +
            "       'username_format':u'用户名必须只能有数字、字母下划线组成',\n" +
            "       'username_length':u'用户名长度必须在5到10个字符之间',\n" +
            "       'username_existed':u'用户名已经存在',\n" +
            "       'password_empty':u'密码不得为空',\n" +
            "       'repassword_error':u'两次密码输入不一致',       \n" +
            "       }</pre> reg是注册的接口名称，这样客户端通过接口名称和code就可以获取对应的提示。 \n" +
            "<p> 由此，用户输入验证就是要把接口参数同这些code联系起来。对于参数验证，Python有天生的语言优势，那就是装饰器。一开始就想到了使用装饰器来描述参数验证需求，但这个装饰器需要哪些信息？怎么个形式?这个得从表单验证的需求开始看起，个人总结表单验证大抵不过这些判断条件： </p> \n" +
            "<p> 1. 是否允许为空 </p> \n" +
            "<p> 2. 长度限制:比如密码的长度一般会不允许少于多少位 </p> \n" +
            "<p> 3. 格式限制:比如Email地址,需要正则判断 </p> \n" +
            "<p> 4. 逻辑限制：比如注册时判断用户名是否已经存在 </p> \n" +
            "<p> 初步根据这些判断条件设计出这么一个方案: </p> \n" +
            "<pre class=\"brush:python; toolbar: true; auto-links: false;\">@checkarg(username={'allow_empty':False, \n" +
            "                    'regex':r'^[a-zA-Z\\d_]+$',\n" +
            "                    'min-length':5, 'max-length':10, \n" +
            "                    'check_logic':[check_username_usable]},\n" +
            "          password={'allow_empty':False,'regex':r'.{6,}'},\n" +
            "          repassword={'allow-empty':False, 'check_logic':\n" +
            "                      [(lambda **kw:(kw['password'] == kw['repassword'], &quot;repassword_error&quot;))]})\n" +
            "def reg(username, password, repassword):\n" +
            "    ....</pre> \n" +
            "<p> 每一个参数使用一个字典来描述验证信息, allow_empty是表示是否为空，regex为验证的正则表达式，min-length和max-length用来描述长度，check_logic用来配置其他的验证逻辑。然后如何把这些验证结果同code进行匹配呢？最开始是在这个验证信息的字典中有一项'code':{'allow_empty':'username_empty'}通过这样的形式去匹配错误提示，但是感觉这样整的这个参数太复杂了（感觉现在已经挺复杂了- -b）,于是决定这个地方使用约定优于配置的形式，code的值为'参数名_错误类型'的形式，比如allow_empty如果验证了为空，那么会自动返回名为username_empty的code,如果是一些额外的处理逻辑呢？没法做约定，怎么办？那么就约定这些检测函数返回一个元组，第一个元素为一个bool值，表示成功失败，第二个参数为code,表示失败原因,比如判断两次密码是否输入一致的那个lambda: </p> \n" +
            "<pre class=\"brush:python; toolbar: true; auto-links: false;\">lambda **kw:(kw['password'] == kw['repassword'], &quot;repassword_error&quot;</pre> \n" +
            "<p> 嗯，大体就是这样的一个设计。 </p> \n" +
            "<h2> 实现 </h2> \n" +
            "<p> 根据上面的设计，把最终的装饰器实现了出来, 逻辑比较简单，关于装饰器设计的一些细节可以参阅Python参考手册: </p> \n" +
            "<pre class=\"brush:python; toolbar: true; auto-links: false;\">regex_cache = {}\n" +
            "     \n" +
            "def checkarg(**args):\n" +
            "    \n" +
            "    u'''参数检测装饰器'''\n" +
            "    \n" +
            "    def _checkarg(function):\n" +
            "        \n" +
            "        def __checkarg(**func_kw):\n" +
            "            for key in func_kw:\n" +
            "                if key in args:\n" +
            "                    \n" +
            "                    #要验证的值\n" +
            "                    value = func_kw[key]\n" +
            "                    \n" +
            "                    #验证规则\n" +
            "                    valid_rules = args[key]\n" +
            "                    \n" +
            "                    #检测空\n" +
            "                    allow_empty = valid_rules.get('allow_empty')\n" +
            "                    if not allow_empty:\n" +
            "                        if not value or not value.strip():\n" +
            "                            return Result(key + &quot;_empty&quot;)\n" +
            "                    elif not value:\n" +
            "                        #如果是空的并且忽略空检测,那么下面的就不需要检查了\n" +
            "                        continue;\n" +
            "                    \n" +
            "                    #检测长度\n" +
            "                    if 'min-length' in valid_rules:\n" +
            "                        min_length = valid_rules['min-length']\n" +
            "                        if min_length &gt; len(value):\n" +
            "                            return Result(key + &quot;_length&quot;)\n" +
            "                        \n" +
            "                    if 'max-length' in valid_rules:\n" +
            "                        max_length = valid_rules['max-length']\n" +
            "                        if max_length &lt; len(value):\n" +
            "                            return Result(key + &quot;_length&quot;)\n" +
            "                    \n" +
            "                    #检测正则\n" +
            "                    if 'regex' in valid_rules:\n" +
            "                        #获取编译后的正则\n" +
            "                        regex = valid_rules['regex']\n" +
            "                        regexcmp = regex_cache.get(regex)\n" +
            "                        if not regexcmp:\n" +
            "                            regexcmp = re.compile(regex)\n" +
            "                            regex_cache[regex] = regexcmp\n" +
            "                        if not regexcmp.search(value):\n" +
            "                            return Result(key + &quot;_format&quot;)\n" +
            "                    \n" +
            "                    #检测其他逻辑\n" +
            "                    check_logics = valid_rules.get('check_logic')\n" +
            "                    if check_logics:\n" +
            "                        for logic in check_logics:\n" +
            "                            result, code = logic(**func_kw)\n" +
            "                            if not result:\n" +
            "                                return Result(code)\n" +
            "                                \n" +
            "            function(**func_kw)\n" +
            "        return __checkarg\n" +
            "                            \n" +
            "    return _checkarg</pre></div>\n" +
            "\t  \t  \n" +
            "            <div style='margin:10px 0 0 0;'>\n" +
            "\t\t<script type=\"text/javascript\" src=\"/js/ad/blog.js\"></script>\n" +
            "      </div>\n" +
            "\t  \t\n" +
            "\t  \t  \n" +
            "      <div class='BlogCopyright'>\t\t\n" +
            "\t  \t\t声明：OSCHINA 博客文章版权属于作者，受法律保护。未经作者同意不得转载。\n" +
            "\t  \t  </div>\n" +
            "\n" +
            "      <div class='BlogLinks'>\n" +
            "    \t<ul>\n" +
            "                <li class='prev'><a href=\"http://my.oschina.net/chihz/blog/119245\" title=\"上一篇：淘金、牛仔裤和创业\">&laquo; 淘金、牛仔裤和创业</a></li>        <li class='next'><a href=\"http://my.oschina.net/chihz/blog/123437\" title=\"下一篇：Python2.x和3.x主要差异总结\">Python2.x和3.x主要差异总结 &raquo;</a></li>    \t</ul>\n" +
            "\t\t\t\t<p style='margin:10px 0 0 0;font-weight:bold;color:#A00;text-decoration:none;'>开源中国-程序员在线工具：<a href=\"http://www.ostools.net/apidocs\" style=\"text-decoration:none;\">API文档大全(120+)</a> <a href=\"http://runjs.cn\" style=\"text-decoration:none;\" target='_blank'>JS在线编辑演示</a> <a href=\"http://www.ostools.net/qr\" style=\"text-decoration:none;\" target='_blank'>二维码</a>  <a href=\"http://www.ostools.net\" style=\"text-decoration:none;\" target='_blank'>更多>></a>\n" +
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
            "\t\t<a class=\"share_sina\" title=\"分享到新浪微博\" href=\"javascript:void((function(s,d,e,r,l,p,t,z,c){var%20f='http://v.t.sina.com.cn/share/share.php?appkey=858381728',u=z||d.location,p=['&url=',e(u),'&title=',e(t||d.title),'&source=',e(r),'&sourceUrl=',e(l),'&content=',c||'gb2312','&pic=',e(p||'')].join('');function%20a(){if(!window.open([f,p].join(''),'mb',['toolbar=0,status=0,resizable=1,width=440,height=430,left=',(s.width-440)/2,',top=',(s.height-430)/2].join('')))u.href=[f,p].join('');};if(/Firefox/.test(navigator.userAgent))setTimeout(a,0);else%20a();})(screen,document,encodeURIComponent,'','','','一个基于Python装饰器的用户输入验证设计方案: 情景 最近初学Python, 语法大概熟悉了之后就开始拿web.py做点小东西，web.py非常轻量，用起来感觉很舒服。但不过无论什么语言或者框架，web开发中有一个最大烦人之处就是...','','utf-8'));\"></a>\n" +
            "\t\t<a class=\"share_qq\" title=\"分享到腾讯微博\" href=\"javascript:(function(){window.open('http://v.t.qq.com/share/share.php?url='+encodeURIComponent(document.location)+'&amp;appkey=96f54f97c4de46e393c4835a266207f4&amp;site=&amp;title='+encodeURIComponent(document.title)+encodeURIComponent(': 情景 最近初学Python, 语法大概熟悉了之后就开始拿web.py做点小东西，web.py非常轻量，用起来感觉很舒服。但不过无论什么语言或者框架，web开发中有一个最大烦人之处就是...'),'', 'width=450, height=400, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, location=yes, resizable=no, status=no');}())\"></a></span>\n" +
            "\t<span id='BlogVote'>\n" +
            "    <a href=\"javascript:vote(122897)\">顶</a><span>已有 <em id='vote_count'>0</em>人顶</span>\n" +
            "\t</span>\n" +
            "\t</div>\n" +
            "\t\t\n" +
            "</div>\n" +
            "<div class='SpaceList' style='margin-top:20px;'>\n" +
            "<div class='BlogComments'>\n" +
            "    <h2><a name=\"comments\"></a>共有 0 条网友评论</h2>\n" +
            "\t\t\t<p class='NoData'>尚无网友评论</p>\n" +
            "\t\t<ul id=\"BlogComments\">\n" +
            "\t\t</ul>\n" +
            "</div>\n" +
            "\t</div>\n" +
            "\n" +
            "<div id='inline_reply_editor' style='display:none;'>\n" +
            "<div class=\"BlogCommentForm\">\n" +
            "\t<form id=\"form_inline_comment\" action=\"/action/blog/add_comment?blog=122897\" method=\"POST\">\n" +
            "\t  <input type='hidden' id='inline_reply_id' name='reply_id' value=''/>          \n" +
            "      <textarea name=\"content\" style=\"width:550px;height:60px;\" onkeydown=\"if((event.metaKey || event.ctrlKey)&&event.keyCode==13){$('#form_inline_comment').submit();}\"></textarea><br/>\n" +
            "\t  <input type=\"submit\" value=\"回复\" id=\"btn_comment\" class=\"SUBMIT\"/> \n" +
            "\t  <input type=\"button\" value=\"关闭\" class=\"SUBMIT\" id='btn_close_inline_reply'/> 文明上网，理性发言\n" +
            "    </form>\n" +
            "</div>\n" +
            "</div>\n" +
            "\n" +
            "<div class='SpaceList' style='margin-top:20px;'>\n" +
            "  <a name=\"comments\" id=\"postform\"></a>\n" +
            "    <div class=\"BlogCommentForm\">\n" +
            "    <form id=\"form_comment\" action=\"/action/blog/add_comment?blog=122897\" method=\"POST\">          \n" +
            "      <textarea id='ta_post_content' name=\"content\" style=\"width:550px;height:100px;\" onkeydown=\"if((event.metaKey || event.ctrlKey)&&event.keyCode==13){$('#form_comment').submit();}\"></textarea><br/>\n" +
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
            "\t\t<span class='date'>2013/03/17</span>\n" +
            "\t\t<a href=\"http://my.oschina.net/huangxinzhu/blog/114373\" title=\"python学习之装饰器\">python学习之装饰器</a>\n" +
            "\t</li>\n" +
            "\t\t\t\t<li>\n" +
            "\t\t<span class='date'>2012/05/10</span>\n" +
            "\t\t<a href=\"http://my.oschina.net/hebianxizao/blog/56836\" title=\"装饰器\">装饰器</a>\n" +
            "\t</li>\n" +
            "\t\t\t\t<li>\n" +
            "\t\t<span class='date'>2012/03/25</span>\n" +
            "\t\t<a href=\"http://my.oschina.net/returnspace/blog/50911\" title=\"装饰器模式\">装饰器模式</a>\n" +
            "\t</li>\n" +
            "\t\t\t\t<li>\n" +
            "\t\t<span class='date'>2010/07/03</span>\n" +
            "\t\t<a href=\"http://my.oschina.net/zengsai/blog/5911\" title=\"Go语言中的装饰模式\">Go语言中的装饰模式</a>\n" +
            "\t</li>\n" +
            "\t\t\t\t<li>\n" +
            "\t\t<span class='date'>2012/09/15</span>\n" +
            "\t\t<a href=\"http://my.oschina.net/pljhonglu/blog/78669\" title=\"初学设计模式——装饰模式\">初学设计模式——装饰模式</a>\n" +
            "\t</li>\n" +
            "\t\t\t</ul>\n" +
            "</div>\n" +
            "<script type=\"text/javascript\" src=\"/action/visit/blog?id=122897\" defer=\"defer\"></script>\n" +
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
            "\t\t\t\tvar url = \"http://my.oschina.net/chihz/blog_post?_cmt_blog=\"+json.blog+\"&_cmt_user=\"+json.user+\"&_cmt_id=\"+json.id;\t\t\t\t\n" +
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
            "    ajax_post(\"/action/blog/delete_blog_comments?space=98039\",args,function(){$(\"#cmt_\"+nid+\"_\"+uid+\"_\"+cid).fadeOut();});\n" +
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
            "<script type=\"text/javascript\" src=\"/action/visit/space?id=98039\"></script>\n" +
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
            "\tr.load(\"http://my.oschina.net/chihz/tweet-rpls?log=\"+logid,function(){\n" +
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
            "}\n" +
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
            "    \tlocation.href=\"http://my.oschina.net/chihz/blog\";\n" +
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
            "<!-- Generated by OsChina.NET (init:0[ms],page:120[ms],ip:60.55.11.227) -->";

    String html = "\n" +
            "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
            "<html lang='zh-CN' xml:lang='zh-CN' xmlns='http://www.w3.org/1999/xhtml'>\n" +
            "<head>\n" +
            "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\n" +
            "  <link rel=\"shortcut icon\" type=\"image/x-icon\" href=\"/img/favicon.ico\" />\n" +
            "  <title>再次吐槽easyui - 开源中国 OSChina.NET</title>\n" +
            "      <link rel=\"stylesheet\" href=\"/css/style2013.css?ver=20130411\" type=\"text/css\" media=\"screen\" />\n" +
            "  <link rel=\"stylesheet\" href=\"/css/channel.css?date=20130324_2\" type=\"text/css\" media=\"screen\" />\n" +
            "  <link rel=\"stylesheet\" type=\"text/css\" href=\"/js/2011/fancybox/jquery.fancybox-1.3.4.css\" media=\"screen\" />\n" +
            "  <link rel=\"alternate\" type=\"application/rss+xml\" title=\"最新代码分享列表\" href=\"http://www.oschina.net/code/rss\" />\n" +
            "  <link rel=\"alternate\" type=\"application/rss+xml\" title=\"开源中国 - 源码列表\" href=\"http://www.oschina.net/code/source_rss\" />\n" +
            "  <link rel=\"alternate\" type=\"application/rss+xml\" title=\"最新问题列表\" href=\"http://www.oschina.net/question/rss\" />\n" +
            "  <script type=\"text/javascript\" src=\"/js/2012/jquery-1.7.1.min.js\"></script>\n" +
            "  <script type=\"text/javascript\" src=\"/js/2012/jquery.form.js\"></script>\n" +
            "  <script type=\"text/javascript\" src=\"/js/2011/fancybox/jquery.fancybox-fixed.js?20130327\"></script>\n" +
            "      <link rel=\"stylesheet\" href=\"/js/poshytip/tip-yellowsimple/tip-yellowsimple.css\" type=\"text/css\" />\n" +
            "  <script type=\"text/javascript\" src=\"/js/poshytip/jquery.poshytip.min.js\"></script>\n" +
            "    <script type=\"text/javascript\">\n" +
            "  \tg_msg = {\n" +
            "};\n" +
            "\n" +
            "g_user = {\n" +
            "\tid:190591,\n" +
            "\tname:'黄亿华',\n" +
            "\tlogin:true,\n" +
            "\tbportrait:'<img src=\"http://static.oschina.net/uploads/user/95/190591_50.jpg?t=1347254905000\" align=\"absmiddle\" alt=\"黄亿华\" title=\"黄亿华\" class=\"SmallPortrait\" user=\"190591\"/>'\n" +
            "};  </script>\n" +
            "    <script type=\"text/javascript\" src=\"/js/2011/oschina.js?ver=20121007\"></script>\n" +
            "  <script type=\"text/javascript\" src=\"/js/utils.js\"></script>\n" +
            "  <script type=\"text/javascript\" src=\"/js/channel.js\"></script>\n" +
            "      <style type=\"text/css\">\n" +
            "    body,table,input,textarea,select {font-family:Verdana,sans-serif,宋体;}  \n" +
            "  </style>\n" +
            "  </head>\n" +
            "<body>\n" +
            "<div id='OSC_NavTop'>\n" +
            "\t<div class=\"wp998\">\n" +
            "        <div id=\"OSC_Channels\">\n" +
            "        \t<ul>\n" +
            "        \t<li class=\"item\"><a href=\"http://www.oschina.net/\" class='home'>首页</a></li>        \t<li class=\"item\"><a href=\"http://www.oschina.net/project\" class='project'>开源软件</a></li>\n" +
            "        \t<li class=\"item control_select\">\n" +
            "\t\t\t\t<a href=\"http://www.oschina.net/question\" class='question hl'>讨论区</a>\t\t\t\t\n" +
            "\t\t\t\t<ul class=\"cs_content\">\t\t\t\t\t\n" +
            "                \t<li><a href=\"http://www.oschina.net/question?catalog=1\"> 技术问答 &raquo; </a></li>\n" +
            "                \t<li><a href=\"http://www.oschina.net/question?catalog=2\"> 技术分享 &raquo; </a></li>\n" +
            "                \t<li><a href=\"http://www.oschina.net/question?catalog=3\"> IT大杂烩 &raquo; </a></li>\n" +
            "                \t<li><a href=\"http://www.oschina.net/question?catalog=100\"> 职业生涯 &raquo; </a></li>\n" +
            "                \t<li><a href=\"http://www.oschina.net/question?catalog=4\"> 站务/建议 &raquo; </a></li>\n" +
            "                \t<li><a href=\"http://www.oschina.net/alipay\"> 支付宝专区 &raquo; </a></li>\n" +
            "\t\t\t\t</ul>\n" +
            "\t\t\t</li>\n" +
            "        \t<li class=\"item\"><a href=\"http://www.oschina.net/code/list\" class='code'>代码分享</a></li>\n" +
            "        \t        \t<li class=\"item\"><a href=\"http://www.oschina.net/blog\" class='blog'>博客</a></li>\n" +
            "        \t<li class=\"item\"><a href=\"http://www.oschina.net/translate\" class='tran'>翻译</a></li>\n" +
            "            <li class=\"item\"><a href=\"http://www.oschina.net/news\" class='news'>资讯</a></li>\n" +
            "        \t<li class=\"item control_select\">\n" +
            "\t\t\t\t<a href=\"http://www.oschina.net/android\" class='mobile'>移动开发</a>\n" +
            "\t\t\t\t<ul class=\"cs_content cs_mobile\">\n" +
            "                \t<li class=\"android_\"><a href=\"http://www.oschina.net/android\">Android开发专区</a></li>\n" +
            "                \t<li class=\"ios_\"><a href=\"http://www.oschina.net/ios/home\">iOS开发专区</a></li>\n" +
            "                \t<li class=\"ios_\"><a href=\"http://www.oschina.net/ios/codingList\">iOS代码库</a></li>\n" +
            "                \t<li class=\"wp7_\"><a href=\"http://www.oschina.net/wp7\">WP7开发专区</a></li>\n" +
            "\t\t\t\t</ul>\n" +
            "\t\t\t</li>\n" +
            "        \t<li class=\"item t_job\"><a href=\"http://www.oschina.net/job\" class='job'>招聘</a></li>\n" +
            "        \t</ul>\n" +
            "        </div>\n" +
            "\t\t<div id=\"OSC_Userbar\">\n" +
            "                \t\t    \t\t<em>黄亿华</em>,您好 \n" +
            "\t\t\t<span class=\"control_select\">\n" +
            "\t\t\t\t<a href=\"http://my.oschina.net/flashsword\" id=\"MySpace\" title=\"我的空间\">我的空间</a>\n" +
            "\t\t\t\t<ul class=\"cs_content cs_myspace\">\n" +
            "                \t<li class='msg_'><a href='http://www.oschina.net/home/go?page=admin%2Finbox'>站内留言</a></li>\n" +
            "                \t<li class='discuss_'><a href='http://my.oschina.net/flashsword/?ft=bbs&scope=2&showme=1'>我的讨论记录</a></li>\n" +
            "                \t<li class='code_'><a href='http://www.oschina.net/code/list_by_user?id=190591'>我分享的代码</a></li>\n" +
            "                \t<li class='blog_'><a href='http://www.oschina.net/home/go?page=blog'>我的博客</a></li>\n" +
            "                \t<li class='friends_'><a href='http://www.oschina.net/home/go?page=fellow'>我关注的人</a></li>\n" +
            "                \t<li class='favorites_'><a href='http://www.oschina.net/home/go?page=favorites'>我的收藏夹</a></li>\n" +
            "                \t<li class='profile_'><a href='http://www.oschina.net/home/go?page=admin%2Fprofile'>个人资料修改</a></li>\n" +
            "                \t<li class='portrait_'><a href='http://www.oschina.net/home/go?page=admin%2Fportrait'>更改我的头像</a></li>\n" +
            "\t\t\t\t</ul>\n" +
            "\t\t\t</span>&nbsp;|&nbsp;\n" +
            "\t\t\t<a href=\"http://www.oschina.net/home/go?page=admin%2Fnew-project\">添加软件</a>&nbsp;|&nbsp;<a href=\"http://www.oschina.net/home/go?page=admin%2Fnew-release\">投递新闻</a>&nbsp;|&nbsp;<a href=\"/action/user/logout?session=6db40e6e2d1061998068&goto_page=http%3A%2F%2Fwww.oschina.net%2Fquestion%2F818848_107307\">退出</a>\n" +
            "    \t\t\t\t</div>\n" +
            "\t\t<div class='clear'></div>\n" +
            "\t</div>\n" +
            "</div>\n" +
            "<div id='OSC_Banner'><div class=\"wp998\"><a href='http://www.oschina.net/' class='Logo' title='OSChina 开源中国'>开源中国</a>\n" +
            "<h1><a href='/question'>讨论区</a></h1>\n" +
            "<dl>\n" +
            "\t<dt>当前位置：</dt>\n" +
            "\t<dd>\n" +
            "\t\t\t\t\t        \t\t<a href=\"/question\">讨论区</a>&nbsp;&raquo;\n" +
            "        \t\t<a href=\"/question?catalog=1\">技术问答</a>\t\t\t\t\t\t\t\t&raquo;&nbsp;<a href=\"/p/jquery+easyui\">EasyUI</a>\n" +
            "\t\t\t\t\t\t\t\t\t\t</dd>\n" +
            "</dl>\n" +
            "<form action='http://www.oschina.net/search' class='search'>\n" +
            "\t<input type='hidden' name='scope' value='bbs'/>\n" +
            "\t<input id='channel_q' type='text' name='q' value='' placeholder='资讯、软件、分享、代码、博客' class='TXT'/>\n" +
            "    <button type='submit' class='BTN'>搜 索</button>\n" +
            "</form>\n" +
            "<div class='clear'></div></div></div>\n" +
            "<div id=\"OSC_Screen\">\n" +
            "\t<div id=\"OSC_Content\" class='CenterDiv'>\n" +
            "<script type=\"text/javascript\" src=\"/js/scrolltopcontrol.js\"></script>\n" +
            "<script type=\"text/javascript\">\n" +
            "  \tscrolltotop.offset(100,120);\n" +
            "  \tscrolltotop.init();\n" +
            "\t$(function(){\n" +
            "\t\t$('a.ShowUserOutline img.SmallPortrait').poshytip({\n" +
            "\t\t\tclassName: 'tip-yellowsimple',\n" +
            "\t\t\talignTo: 'target',\n" +
            "\t\t\talignX: 'right',\n" +
            "\t\t\talignY: 'inner-top',\n" +
            "\t\t\toffsetX: 5,\n" +
            "\t\t\toffsetY: 0,\n" +
            "\t\t\tfade: false,\n" +
            "\t\t\tslide: false,\n" +
            "\t\t\tcontent: function(updateCallback) {\n" +
            "\t\t\t\tajax_get(\"/action/ajax/get_user_outline?id=\"+$(this).attr('user'),false,function(html){\n" +
            "\t\t\t\t\tupdateCallback(html);\n" +
            "\t\t\t\t});\n" +
            "\t\t\t\treturn \"<div style='height:100px;'>Loading...</div>\";\n" +
            "\t\t\t}\n" +
            "\t\t});\n" +
            "\t});\n" +
            "\tfunction add_to_favorite(pid,concern_it){\n" +
            "\t\t\tif(concern_it){\n" +
            "\t\t\t$(\"#p_attention_count\").load(\"/action/favorite/add?mailnotify=true&type=2&id=\"+pid, {user: '190591'});\n" +
            "\t\t\t$('#attention_it').html('<a href=\"javascript:add_to_favorite('+pid+',false)\">取消</a>');\t\n" +
            "\t\t}\n" +
            "\t\telse{\n" +
            "\t\t\t$(\"#p_attention_count\").load(\"/action/favorite/cancel?type=2&id=\"+pid, {user: '190591'});\n" +
            "\t\t\t$('#attention_it').html('<a href=\"javascript:add_to_favorite('+pid+',true)\">收藏</a>');\n" +
            "\t\t}\n" +
            "\t\t}\n" +
            "</script>\n" +
            "\n" +
            "\n" +
            "\n" +
            "<div class='Question'>\n" +
            "\t\n" +
            "\t<div class='Body'>\n" +
            "\t<div class='Title'>\n" +
            "\t\t<div class='Asker'><a href=\"http://my.oschina.net/u/818848\" class=\"ShowUserOutline\" target=\"_blank\"><img src=\"http://static.oschina.net/uploads/user/409/818848_50.jpg?t=1357353541000\" align=\"absmiddle\" alt=\"午后冬日\" title=\"午后冬日\" class=\"SmallPortrait\" user=\"818848\"/></a></div>\n" +
            "\t\t<div class='QTitle'>\n" +
            "\t\t\t<h1><a href=\"http://www.oschina.net/question/818848_107307\" hidefocus=\"true\" name='top'>再次吐槽easyui</a></h1>\n" +
            "\t\t\t<div class='stat'>\n" +
            "\t\t\t\t<a href=\"http://my.oschina.net/u/818848\" target=\"_blank\">午后冬日</a>\n" +
            "\t\t\t\t发表于 2013-4-21 02:28 13小时前,\n" +
            "\t\t\t\t<a href='#answers' class='answer_count'>3</a>回/289阅,\n" +
            "\t\t\t\t最后回答: 4小时前\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t</div>\n" +
            "\t\t\n" +
            "\t\t<div class='clear'></div>\n" +
            "\t</div>\n" +
            "\t\t    \t    \t\t\t\t\t\n" +
            "\t\t<p style='color:#A00;font-weight:bold;margin:10px 0 0 3px;'>Java、PHP、Ruby、iOS、Python 等 JetBrains 开发工具低至 99  元（3折），<a href='http://www.oschina.net/shop/jetbrains' target='_blank'>详情&raquo;</a></p>\n" +
            "\t\t<div class='Content'>\n" +
            "\t\t\t\t\t\t<div class='detail'>刚用到easyui treegrid组件，发现这货第一次加载时候并没有传默认参数，展开某一列时候才传递id:xx的参数。这样和后台总是疙里疙瘩，像没事就拌嘴的两口子，查网上都遇到相同问题，最好解决方案就是通过 \n" +
            "<span style=\"font-family:Arial, Helvetica, 'Nimbus Sans L', sans-serif;font-size:14px;line-height:normal;background-color:#FFFFFF;\">onBeforeExpand事件来扩展，自行解决。看到官方例子中简洁的代码，感觉easyui耍流氓了，真搞不懂为何要这样实现</span><div class='clear'></div></div>\n" +
            "\t\t\t\t\t\t<div class='Tags'>\n" +
            "\t\t\t\t<strong>标签：</strong>\t\t\t\t\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<a href='http://www.oschina.net/question/tag/jquery+easyui' class='tag project' title='jQuery的UI组件 EasyUI'>EasyUI</a> \t\t\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t<div class='SameQuestions'>\n" +
            "\t\t\t<span id='RQuestionAction'>\n" +
            "\t\t\t\t\t\t\t\t\t<a href=\"javascript:ask_too(107307,true)\" class='rndbutton'><span>我想问同样的问题</span></a>\n" +
            "\t\t\t\t\t\t</span>\n" +
            "\t\t\t共<em id='c_asker_count'>0</em>个人想要问同样的问题\n" +
            "\t\t\t\t\t\t<a href=\"javascript:make_question_more_detail(107307)\">补充话题说明&raquo;</a>\n" +
            "\t\t\t</div>\n" +
            "\t\t\t\t\t\t<div class='EditLogs'>\n" +
            "\t<ul></ul>\n" +
            "</div>\t\t</div>\n" +
            "\t\t<style type='text/css'>\n" +
            "\t\t#favor_form{width:200px;}\n" +
            "        #favor_form p {color:#666;}\n" +
            "        #favor_form form{height:60px;width:200px;}\n" +
            "        #favor_form form ._favor_input{display:block;margin:2px 0;width:199px;}\n" +
            "        #favor_form form ._favor_button{float:left;padding:2px 5px;}\n" +
            "        .favor_ok {text-align:center;font-size:10.5pt;width:199px;height:60px;margin-top:10px;}\n" +
            "        #TagsSwitcher{cursor:pointer;float:right;margin-top:10px;}\n" +
            "        #MyTags{display:none;width:199px;}\n" +
            "        #MyTags a.tag {float:left; background-color: #E0EAF1;border-bottom: 1px solid #3E6D8E;border-right: 1px solid #7F9FB6;color: #3E6D8E;font-size: 8pt;line-height: 16px;margin: 2px 2px 2px 0;padding: 2px 4px;text-decoration: none;white-space: nowrap;}\n" +
            "\t\t.osc_promotion{ position: relative; display: inline-block; padding: 10px; margin: 10px 0; border: 1px solid #ccc;}\n" +
            "        .osc_promotion .c{ position: absolute; right: -1px; top: -1px;}\n" +
            "\t\t.ask_toolbar {float:right;list-style: none; font-size: 12px; color: #333; height: 28px;_padding-top: 5px; overflow: hidden;margin:20px 0 10px 0;}\n" +
            "        .ask_toolbar div{ float: left; margin-left: 5px; background: url(\"/img/ask-icon.gif\") no-repeat; padding: 6px 0 6px 15px; padding-left: 15px; height: 16px;}\n" +
            "        .ask_toolbar a{ height: 16px; color: #333; text-decoration: none; display:inline-block; zoom:1; vertical-align: middle; }\n" +
            "        .ask_share{width: 89px;vertical-align: bottom; line-height: 15px; _line-height: 14px;}\n" +
            "        .ask_share a{background: url(\"/img/ask-icon.gif\"); width: 16px; }\n" +
            "        a.ask_share_sina{ background: url(\"/img/ask-icon.gif\") 0 -40px no-repeat; margin-left: 5px;  }\n" +
            "        a.ask_share_tencent{background-position: 0 -70px; margin-left: 5px; }\n" +
            "        .ask_toolbar em{ height: 28px; line-height:28px; width: 14px; display: inline-block; float: left; background: url(\"/img/ask-icon.gif\") top right;}\n" +
            "        .ask_collect a,.ask_report a, .ask_vote a, .ask_collected a{padding-left: 20px; line-height: 15px; }\n" +
            "        .ask_collect a{ background: url(\"/img/ask-icon.gif\") 0 -131px no-repeat; }\n" +
            "        .ask_collected a{ background: url(\"/img/ask-icon.gif\") 0 -100px no-repeat; }\n" +
            "        div.ask_collect_count{ background: url(\"/img/ask-icon.gif\") 0 -309px no-repeat; font-weight: bold; font-size: 14px; margin-left: 0; height: 16px;line-height: 16px;}\n" +
            "        .ask_report a{ background: url(\"/img/ask-icon.gif\") 0 -160px no-repeat;}\n" +
            "        em.ask_collect_count_r{background-position: 59px -309px;}\n" +
            "        .ask_vote a{background: url(\"/img/ask-icon.gif\");}\n" +
            "        \t\ta.ask_vote_up{background-position: 3px -190px;}\n" +
            "        a.ask_vote_down{background-position: 0 -280px;}\n" +
            "        a.ask_vote_uped {background-position: 3px -190px;}\n" +
            "        \t\ta.ask_vote_downed {background-position: 0 -280px;}\n" +
            "        .ask_vote span{ display: inline-block; margin: 0 10px; font-weight: bold; font-size: 14px; vertical-align: middle; margin-bottom: 2px; line-height: 16px;}\n" +
            "\t\tspan.vote-down-count{margin:0 3px;}\n" +
            "\t\tspan.vote-up-count{margin:0 3px;}\n" +
            "\t\t</style>\n" +
            "\t\t<div class='clear'></div>\n" +
            "        \n" +
            "\t\t\t\t<div class=\"ask_toolbar\">\n" +
            "\t\t\t<div class=\"ask_share\"><b>分享到</b> <a class=\"ask_share_sina\" title=\"分享到新浪微博\"  href=\"javascript:void((function(s,d,e,r,l,p,t,z,c){var%20f='http://v.t.sina.com.cn/share/share.php?appkey=858381728',u=z||d.location,p=['&url=',e(u),'&title=',e(t||d.title),'&source=',e(r),'&sourceUrl=',e(l),'&content=',c||'gb2312','&pic=',e(p||'')].join('');function%20a(){if(!window.open([f,p].join(''),'mb',['toolbar=0,status=0,resizable=1,width=440,height=430,left=',(s.width-440)/2,',top=',(s.height-430)/2].join('')))u.href=[f,p].join('');};if(/Firefox/.test(navigator.userAgent))setTimeout(a,0);else%20a();})(screen,document,encodeURIComponent,'','','','再次吐槽easyui: 刚用到easyui treegrid组件，发现这货第一次加载时候并没有传默认参数，展开某一列时候才传递id:xx的参数。这样和后台总是疙里疙瘩，像没事就拌嘴的两口子，查网上都遇到...','','utf-8'));\"></a><a class=\"ask_share_tencent\" title=\"分享到腾讯微博\"  href=\"javascript:(function(){window.open('http://v.t.qq.com/share/share.php?url='+encodeURIComponent(document.location)+'&amp;appkey=96f54f97c4de46e393c4835a266207f4&amp;site=&amp;title='+encodeURIComponent(document.title)+encodeURIComponent(': 刚用到easyui treegrid组件，发现这货第一次加载时候并没有传默认参数，展开某一列时候才传递id:xx的参数。这样和后台总是疙里疙瘩，像没事就拌嘴的两口子，查网上都遇到...'),'', 'width=450, height=400, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, location=yes, resizable=no, status=no');}())\"></a></div><em></em>\n" +
            "\t\t\t<div class=\"ask_collect\"><a title=\"收藏此话题\" id=\"favor_trigger\" href=\"javascript:;\">收藏</a></div><em></em>\n" +
            "\t\t\t<div class=\"ask_collect_count\" id=\"p_favor_count\">1</div><em class=\"ask_collect_count_r\"></em>\n" +
            "\t\t\t<div class=\"ask_report\"><a href=\"javascript:report('http://www.oschina.net/question/818848_107307',107307,2)\">举报</a></div><em></em>\n" +
            "\t\t\t<div class='ask_vote' id='Vote'>\n" +
            "\t\t\t\t\t\t\t\t        \t\t\t<a id=\"vote_down\" class=\"ask_vote_down\" href=\"javascript:;\" title=\"踩：这问题不知道在说什么，或者没什么用\">踩</a>\n" +
            "\t\t\t\t\t\t\t\t<span class='vote-down-count'>0</span>\n" +
            "\t\t\t\t|\n" +
            "\t\t\t\t\t\t\t\t        \t\t\t<a id=\"vote_up\" class=\"ask_vote_up\" href=\"javascript:;\" title=\"顶：这问题很有用或者很清晰明了\">顶</a>\n" +
            "\t\t\t\t\t\t\t\t<span class='vote-up-count'>0</span>\n" +
            "\t\t\t</div>\n" +
            "\t\t\t<em></em>\n" +
            "\t\t</div>\n" +
            "\t\t<div class='clear'></div>\n" +
            "\t\t\t\t\t\t        <div class='QuestionReplies'>\n" +
            "\t\t\t\n" +
            "        \t<h2>\t\t\t\n" +
            "\t\t\t\t<span class='sort'>\n" +
            "\t\t\t\t\t<a href=\"http://www.oschina.net/question/818848_107307#answers\" class='current'>按评价排序</a>&nbsp;|\n" +
            "\t\t\t\t\t<a href=\"?sort=time#answers\">显示最新答案</a>&nbsp;|&nbsp;<a href=\"#top\" style='padding-left:0;'>回页面顶部</a>\n" +
            "\t\t\t\t</span>\n" +
            "\t\t\t\t<a name='answers'></a>共有<em class='answer_count'>3</em>个答案 <a href=\"#answerform\" class='answer'>我要回答&raquo;</a>\n" +
            "\t\t\t</h2>\n" +
            "\t\t\t        \t<ul class='list'><li class='Answer' id='answer_467005'>\n" +
            "\t<div class='user'><a href=\"http://my.oschina.net/u/224858\" class=\"ShowUserOutline\" name=\"AnchorAnswer467005\" target=\"_blank\"><img src=\"http://static.oschina.net/uploads/user/112/224858_50.jpg\" align=\"absmiddle\" alt=\"布谷鸟\" title=\"布谷鸟\" class=\"SmallPortrait\" user=\"224858\"/></a></div>\n" +
            "\t<div class='body'>\n" +
            "\t\t<div class='time'><a href=\"http://my.oschina.net/u/224858\" target=\"_blank\">布谷鸟</a> 回答于 2013-04-21 09:28 </div>\t\t\n" +
            "    \t<div class='opts'>\n" +
            "\t\t\t    \t\t    \t\t<a href=\"javascript:report('http://www.oschina.net/question/818848_107307#AnchorAnswer467005',467005,17)\">举报</a>\n" +
            "    \t</div>\n" +
            "\t\t<div class='clear'></div>\n" +
            "\t\t<div class='detail'>对话框、日期控件和选项卡效果还不错，树状菜单没得zTree好用，建议楼主不要全部效果都依赖于此框架，有些easyui实现不好的地方可以换其它的插件实现嘛，反正我现在再也不用诸如ext和easyui之类的东西了，感觉好肥</div>\n" +
            "\t</div>\n" +
            "\t<div class='clear'></div>\n" +
            "\t<div class='replies' id='PostReplies_467005'><strong>--- 共有 1 条评论 --- </strong>\n" +
            "<ul>\n" +
            "\t\t<li id='PostReply_467044'>\n" +
            "\t\t<a href=\"http://my.oschina.net/u/818848\" class='p' name='AnchorAnswer467044'><img src=\"http://static.oschina.net/uploads/user/409/818848_50.jpg?t=1357353541000\" align=\"absmiddle\" alt=\"午后冬日\" title=\"午后冬日\" class=\"SmallPortrait\" user=\"818848\"/></a>\n" +
            "\t\t<span class='b'>\n" +
            "\t\t<span class='c'>前端水平实在有限，自己搞的总是感觉不伦不类，只能用这些框架，再集成其它插件，切换主题时风格又不一致。</span>\n" +
            "\t\t<span class='t'>(4小时前 by 午后冬日)</span>\n" +
            "\t\t<span class='opts'><a href=\"javascript:reply_to_post(467005,818848)\">回复</a></span>\n" +
            "\t\t</span>\n" +
            "\t\t<div class='clear'></div>\n" +
            "\t</li>\n" +
            "\t</ul>\n" +
            "<div class='PagerLinks'>\n" +
            "</div>\n" +
            "</div>\n" +
            "\t<div class='votes'>\t\t\t\t\t\t<a id='a_post_voteup_467005' href=\"javascript:vote_answer(467005,true,true)\" title=\"这是一个好答案，能解决问题\">有帮助</a><em id='post_voteup_467005'>(1)</em>&nbsp;|\n" +
            "\t\t<a id='a_post_votedown_467005' href=\"javascript:vote_answer(467005,false,true)\" title=\"这答案无法解决问题，或者模糊不清\">没帮助</a><em id='post_votedown_467005'>(0)</em>&nbsp;|\n" +
            "\t\t<a href=\"javascript:reply_to_post(467005, 0)\">评论</a><em>(1)</em>&nbsp;|\n" +
            "    \t<a href=\"/question/answer?question=107307&amp;answer=467005\">引用此答案</a>\t</div>\n" +
            "</li><li class='Answer' id='answer_467039'>\n" +
            "\t<div class='user'><a href=\"http://my.oschina.net/rox\" class=\"ShowUserOutline\" name=\"AnchorAnswer467039\" target=\"_blank\"><img src=\"http://static.oschina.net/uploads/user/0/180_50.jpg\" align=\"absmiddle\" alt=\"静风流云\" title=\"静风流云\" class=\"SmallPortrait\" user=\"180\"/></a></div>\n" +
            "\t<div class='body'>\n" +
            "\t\t<div class='time'><a href=\"http://my.oschina.net/rox\" target=\"_blank\">静风流云</a> 回答于 2013-04-21 11:08 </div>\t\t\n" +
            "    \t<div class='opts'>\n" +
            "\t\t\t    \t\t    \t\t<a href=\"javascript:report('http://www.oschina.net/question/818848_107307#AnchorAnswer467039',467039,17)\">举报</a>\n" +
            "    \t</div>\n" +
            "\t\t<div class='clear'></div>\n" +
            "\t\t<div class='detail'><p> 没办法，原来项目也是因为客户特殊的需求，对layout选型的时候，犹豫了好久，最终放弃了。<br /> 幸亏来了一个厉害的前端，解决问题，够用就好。 </p></div>\n" +
            "\t</div>\n" +
            "\t<div class='clear'></div>\n" +
            "\t<div class='replies' id='PostReplies_467039'><strong>--- 共有 1 条评论 --- </strong>\n" +
            "<ul>\n" +
            "\t\t<li id='PostReply_467046'>\n" +
            "\t\t<a href=\"http://my.oschina.net/u/818848\" class='p' name='AnchorAnswer467046'><img src=\"http://static.oschina.net/uploads/user/409/818848_50.jpg?t=1357353541000\" align=\"absmiddle\" alt=\"午后冬日\" title=\"午后冬日\" class=\"SmallPortrait\" user=\"818848\"/></a>\n" +
            "\t\t<span class='b'>\n" +
            "\t\t<span class='c'>我也是犹豫了好久，看过很多前端框架，总是不太满意。个人开发前台后台数据库全部要自己搞定，郁闷ing</span>\n" +
            "\t\t<span class='t'>(4小时前 by 午后冬日)</span>\n" +
            "\t\t<span class='opts'><a href=\"javascript:reply_to_post(467039,818848)\">回复</a></span>\n" +
            "\t\t</span>\n" +
            "\t\t<div class='clear'></div>\n" +
            "\t</li>\n" +
            "\t</ul>\n" +
            "<div class='PagerLinks'>\n" +
            "</div>\n" +
            "</div>\n" +
            "\t<div class='votes'>\t\t\t\t\t\t<a id='a_post_voteup_467039' href=\"javascript:vote_answer(467039,true,true)\" title=\"这是一个好答案，能解决问题\">有帮助</a><em id='post_voteup_467039'>(0)</em>&nbsp;|\n" +
            "\t\t<a id='a_post_votedown_467039' href=\"javascript:vote_answer(467039,false,true)\" title=\"这答案无法解决问题，或者模糊不清\">没帮助</a><em id='post_votedown_467039'>(0)</em>&nbsp;|\n" +
            "\t\t<a href=\"javascript:reply_to_post(467039, 0)\">评论</a><em>(1)</em>&nbsp;|\n" +
            "    \t<a href=\"/question/answer?question=107307&amp;answer=467039\">引用此答案</a>\t</div>\n" +
            "</li><li class='Answer' id='answer_467051'>\n" +
            "\t<div class='user'><a href=\"http://my.oschina.net/u/224858\" class=\"ShowUserOutline\" name=\"AnchorAnswer467051\" target=\"_blank\"><img src=\"http://static.oschina.net/uploads/user/112/224858_50.jpg\" align=\"absmiddle\" alt=\"布谷鸟\" title=\"布谷鸟\" class=\"SmallPortrait\" user=\"224858\"/></a></div>\n" +
            "\t<div class='body'>\n" +
            "\t\t<div class='time'><a href=\"http://my.oschina.net/u/224858\" target=\"_blank\">布谷鸟</a> 回答于 2013-04-21 11:29 </div>\t\t\n" +
            "    \t<div class='opts'>\n" +
            "\t\t\t    \t\t    \t\t<a href=\"javascript:report('http://www.oschina.net/question/818848_107307#AnchorAnswer467051',467051,17)\">举报</a>\n" +
            "    \t</div>\n" +
            "\t\t<div class='clear'></div>\n" +
            "\t\t<div class='detail'><div class=\"ref\"><h4>引用来自“布谷鸟”的答案</h4><div class=ref_body>对话框、日期控件和选项卡效果还不错，树状菜单没得zTree好用，建议楼主不要全部效果都依赖于此框架，有些easyui实现不好的地方可以换其它的插件实现嘛，反正我现在再也不用诸如ext和easyui之类的东西了，感觉好肥</div></div><div class=a_body>前后端你一个人搞啊？那确实很麻烦。面面俱到的话，工作量很大。但是如果需要实现的功能不是很多，而时间也不紧迫的话，事情干起来也还不错。如非必须，建议逐步弃用这些前端框架，在一些比较能够提升体验的地方选用一些适当的插件即可，如此也不再需要担心风格的问题，你看osc后台截图，界面那叫一个丑，用得方便顺手就够了</div></div>\n" +
            "\t</div>\n" +
            "\t<div class='clear'></div>\n" +
            "\t<div class='replies' id='PostReplies_467051'></div>\n" +
            "\t<div class='votes'>\t\t\t\t\t\t<a id='a_post_voteup_467051' href=\"javascript:vote_answer(467051,true,true)\" title=\"这是一个好答案，能解决问题\">有帮助</a><em id='post_voteup_467051'>(0)</em>&nbsp;|\n" +
            "\t\t<a id='a_post_votedown_467051' href=\"javascript:vote_answer(467051,false,true)\" title=\"这答案无法解决问题，或者模糊不清\">没帮助</a><em id='post_votedown_467051'>(0)</em>&nbsp;|\n" +
            "\t\t<a href=\"javascript:reply_to_post(467051, 0)\">评论</a><em>(0)</em>&nbsp;|\n" +
            "    \t<a href=\"/question/answer?question=107307&amp;answer=467051\">引用此答案</a>\t</div>\n" +
            "</li></ul>\n" +
            "\t\t\t\t        </div> \n" +
            "\t\t<div class='AnswerForm'>\n" +
            "\t\t\t<div class='user'><a href=\"http://my.oschina.net/flashsword\" name=\"answerform\"><img src=\"http://static.oschina.net/uploads/user/95/190591_50.jpg?t=1347254905000\" align=\"absmiddle\" alt=\"黄亿华\" title=\"黄亿华\" class=\"SmallPortrait\" user=\"190591\"/></a></div>\n" +
            "\t\t\t<form id='form_answer' action=\"/action/question/answer?question=107307\" method=\"post\">\n" +
            "\t\t\t\t<input type='hidden' name='user' value='190591'/>\n" +
            "\t\t\t\t<textarea id='txt_answner' name='body' style='width:560px;height:160px;'></textarea>\n" +
            "\t\t\t\t<input type='submit' value=' 我要回答 ' id=\"FormSubmitButton\" class='rndbutton'/>\n" +
            "\t\t\t\t<span id='form_msg' style='display:none;'></span>\n" +
            "\t\t\t\t<br/><br/>\n" +
            "\t\t\t\t<a href=\"#answers\">回答案顶部</a>&nbsp;|&nbsp;<a href=\"#top\">回页面顶部</a>\n" +
            "\t\t\t</form>\n" +
            "\t\t\t<div class='clear'></div>\n" +
            "\t\t\t<script>\t\t\t\n" +
            "            $('#form_answer').ajaxForm({\n" +
            "            \tdataType: 'json',\n" +
            "        \t\tbeforeSerialize: function($form, options) { \n" +
            "        \t\t\teditor.sync();           \n" +
            "                },\n" +
            "        \t\tbeforeSubmit: function(){\n" +
            "        \t\t\t$('#FormSubmitButton').attr('disabled','disabled');\n" +
            "        \t\t\t$('#form_msg').html(\"<span class='ajax_processing'>正在提交答案，请稍候...</span>\");\t\n" +
            "        \t\t\t$('#form_msg').show();\t\n" +
            "        \t\t},\n" +
            "                success: function(json) {\n" +
            "        \t\t\t$('#FormSubmitButton').removeAttr('disabled');\n" +
            "            \t\tif(json.msg){\n" +
            "        \t\t\t\t$('#form_msg').html(\"<span class='error_msg'>\"+json.msg+\"</span>\");\n" +
            "        \t\t\t\t$('#form_msg').show();\n" +
            "        \t\t\t}\n" +
            "            \t\telse if(json.id){\n" +
            "            \t\t\tajax_get(\"/question/show_answer?_answer_id=\"+json.id, true, function(data){\n" +
            "        \t\t\t\t\t            \t\t\t\t$('.QuestionReplies ul.list').append(data);\n" +
            "        \t\t\t\t\t        \t\t\t\t\teditor.html('');\n" +
            "        \t\t\t\t\t$('.answer_count').html(json.answer_count);\n" +
            "            \t\t\t}); \t\t\t\t\n" +
            "        \t\t\t\t$('#form_msg').hide();\n" +
            "            \t\t}\n" +
            "                }\n" +
            "            });\n" +
            "\t\t\t</script>\n" +
            "\t\t</div>\n" +
            "\t</div>\t\n" +
            "\t<script type=\"text/javascript\" src=\"/js/syntax-highlighter-2.1.382/scripts/brush.js\"></script>\n" +
            "<link type=\"text/css\" rel=\"stylesheet\" href=\"/js/syntax-highlighter-2.1.382/styles/shCore.css\"/>\n" +
            "<link type=\"text/css\" rel=\"stylesheet\" href=\"/js/syntax-highlighter-2.1.382/styles/shThemeDefault.css\"/>\n" +
            "<script type='text/javascript'><!--\n" +
            "$(document).ready(function(){\n" +
            "\tSyntaxHighlighter.config.clipboardSwf = '/js/syntax-highlighter-2.1.382/scripts/clipboard.swf';\n" +
            "\tSyntaxHighlighter.all();\n" +
            "});\n" +
            "//-->\n" +
            "</script>\n" +
            "\t<div class='QuestionRelations'>\n" +
            "\t <div style='text-align:center;margin-bottom:10px;'>\n" +
            "    \t<a href=\"http://www.oschina.net/action/visit/ad?id=1033\" target=\"_blank\" title=\"JPush——极光推送\"><img src=\"http://static.oschina.net/uploads/space/2013/0319/103739_17pH_179699.jpg\"/></a>\n" +
            "\t</div>\n" +
            "\t\t<div id='QuestionWizard'>\n" +
            "\t\t\t有什么技术问题吗？\n" +
            "\t\t\t<a href='/question/ask' class='rndbutton'><span>我要提问</span></a>\n" +
            "\t\t\t<div class='clear'></div>\n" +
            "\t\t</div>\n" +
            "\t\t\n" +
            "\t\t\t\t\t\t<div id='OtherQuestionsOfUser' class='Qlist'>\n" +
            "\t\t\t<strong><a href=\"http://my.oschina.net/u/818848/?ft=bbs&scope=2&showme=1\" class=\"more\">全部(29)...</a><em>午后冬日</em>的其他问题</strong>\n" +
            "\t\t\t<ul>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<li><a href=\"http://www.oschina.net/question/818848_106829\" title=\"是jsf还是ajax框架，这是个问题\">是jsf还是ajax框架，这是个问题</a><span class='date'>(4回/194阅,4天前)</span></li>\t\t\t\t\t\t<li><a href=\"http://www.oschina.net/question/818848_106805\" title=\"关于ireport的问题\">关于ireport的问题</a><span class='date'>(0回/4阅,4天前)</span></li>\t\t\t\t\t\t<li><a href=\"http://www.oschina.net/question/818848_106539\" title=\"关于JasperReports的问题\">关于JasperReports的问题</a><span class='date'>(2回/47阅,6天前)</span></li>\t\t\t\t\t\t<li><a href=\"http://www.oschina.net/question/818848_105917\" title=\"IDEA代码编辑窗口能不能上下分屏\">IDEA代码编辑窗口能不能上下分屏</a><span class='date'>(2回/53阅,10天前)</span></li>\t\t\t\t\t\t</ul>\n" +
            "\t\t</div>\n" +
            "\t\t\t\t<div style='text-align:center;margin-top:20px;'>\n" +
            "\t\t<script type=\"text/javascript\" src=\"/js/ad/question.js\"></script>\n" +
            "\t\t</div>\n" +
            "\t\t\n" +
            "\t\t<div id='Similarity' class='Qlist'>\n" +
            "\t\t\t<strong>类似的话题</strong>\n" +
            "\t\t\t<ul>\n" +
            "        \t\t\t\t<li><a href=\"http://www.oschina.net/question/267632_49688\" title=\"jQuery easyUI 分页(Pagination)用法\">jQuery easyUI 分页(Pagination)用法</a><span class='date'>(2回/1228阅,1年前)</span></li>\t\t\t\t\t\t<li><a href=\"http://www.oschina.net/question/138848_49846\" title=\"谨慎使用EasyUI\">谨慎使用EasyUI</a><span class='date'>(1回/1361阅,1年前)</span></li>\t\t\t\t\t\t<li><a href=\"http://www.oschina.net/question/205548_31992\" title=\"easyui datagird 初始化加载俩次\">easyui datagird 初始化加载俩次</a><span class='date'>(6回/690阅,1年前)</span></li>\t\t\t\t\t\t<li><a href=\"http://www.oschina.net/question/84535_32061\" title=\"打算使用easyui,求源码,求建议~\">打算使用easyui,求源码,求建议~</a><span class='date'>(17回/4105阅,1年前)</span></li>\t\t\t\t\t\t<li><a href=\"http://www.oschina.net/question/201422_32682\" title=\"jquery easyui form 有没好的设计\">jquery easyui form 有没好的设计</a><span class='date'>(2回/812阅,1年前)</span></li>\t\t\t\t\t\t<li><a href=\"http://www.oschina.net/question/183509_32885\" title=\"jeasyui 中combobox的onselect事件怎么做下拉框的级联\">jeasyui 中combobox的onselect事件怎么做下拉框的级联</a><span class='date'>(1回/741阅,1年前)</span></li>\t\t\t\t\t\t<li><a href=\"http://www.oschina.net/question/161511_36411\" title=\"easyui  treegrid行编辑 效率慢怎么解决?\">easyui  treegrid行编辑 效率慢怎么解决?</a><span class='date'>(1回/671阅,1年前)</span></li>\t\t\t\t\t\t<li><a href=\"http://www.oschina.net/question/166022_24392\" title=\"ComboGrid怎么动态绑定ds呢？\">ComboGrid怎么动态绑定ds呢？</a><span class='date'>(2回/427阅,1年前)</span></li>\t\t\t\t\t\t<li><a href=\"http://www.oschina.net/question/146658_24974\" title=\"easyui中怎么显示一个list的数据？\">easyui中怎么显示一个list的数据？</a><span class='date'>(1回/594阅,1年前)</span></li>\t\t\t\t\t\t<li><a href=\"http://www.oschina.net/question/97507_26165\" title=\"jquery easyui 組件無法顯示\">jquery easyui 組件無法顯示</a><span class='date'>(2回/671阅,1年前)</span></li>\t\t\t\t\t\t<li><a href=\"http://www.oschina.net/question/46586_16818\" title=\"关于jQuery EasyUI Form的问题\">关于jQuery EasyUI Form的问题</a><span class='date'>(2回/1103阅,2年前)</span></li>\t\t\t\t\t\t<li><a href=\"http://www.oschina.net/question/59256_17359\" title=\"jquery easyUI \">jquery easyUI </a><span class='date'>(1回/557阅,2年前)</span></li>\t\t\t\t\t\t<li><a href=\"http://www.oschina.net/question/188775_77569\" title=\"easyui中有没有点击datagrid的一个单元格 ，就使这个单元格变成可编辑的办法呢 \">easyui中有没有点击datagrid的一个单元格 ，就使这个单元格变成可编辑的办法呢 </a><span class='date'>(1回/890阅,5个月前)</span></li>\t\t\t\t\t\t<li><a href=\"http://www.oschina.net/question/256315_79207\" title=\"jQuery easyui：点击tree控件后无法获取node属性\">jQuery easyui：点击tree控件后无法获取node属性</a><span class='date'>(3回/533阅,5个月前)</span></li>\t\t\t\t\t\t<li><a href=\"http://www.oschina.net/question/580112_79227\" title=\"EasyUI 中tab标签的选项卡 置于左边是怎么实现的\">EasyUI 中tab标签的选项卡 置于左边是怎么实现的</a><span class='date'>(5回/426阅,5个月前)</span></li>\t\t\t\t\t\t<li><a href=\"http://www.oschina.net/question/868642_79423\" title=\"easyui无法跳转到 指定action   各位帮忙看看哪里有问题么？\">easyui无法跳转到 指定action   各位帮忙看看哪里有问题么？</a><span class='date'>(2回/162阅,5个月前)</span></li>\t\t\t\t\t\t</ul>\n" +
            "\t\t</div>\n" +
            "\t</div>\n" +
            "\t<div class='clear'></div>   \n" +
            "</div>\n" +
            "<script type='text/javascript' src='/js/ke/kindeditor-min.js?v=4.1.4' charset='utf-8'></script>\n" +
            "<script type='text/javascript'>\n" +
            "<!--\n" +
            "var editor;\n" +
            "KindEditor.ready(function(K) {\n" +
            "    editor = K.create('#txt_answner', {\n" +
            "\t\tthemeType : 'oschina',\n" +
            "\t\tresizeType : 1,\n" +
            "\t\turlType: 'domain',\n" +
            "\t\tshadowMode : false,\n" +
            "\t\tallowPreviewEmoticons : false,\n" +
            "\t\tallowImageUpload : true,\n" +
            "\t\tallowFlashUpload : false,\n" +
            "\t\tcssPath : '/css/ke-oschina.css',\n" +
            "\t\tuploadJson : '/action/blog/upload_img',\n" +
            "\t\tafterCreate : function(){\n" +
            "\t\t\t/*\n" +
            "\t\t\tK.ctrl(this.edit.iframe.get(0).contentWindow.document, 13, function() {\n" +
            "\t\t\t\t$(\"#txt_answner\").parent().submit();\n" +
            "\t\t\t});\n" +
            "\t\t\t*/\n" +
            "\t\t\t$(this.edit.iframe.get(0).contentWindow.document).keydown(function(e) {\n" +
            "\t\t\t\tif ((e.ctrlKey || e.metaKey) && e.which == 13 && !e.shiftKey && !e.altKey){\n" +
            "\t\t\t\t\t$(\"#txt_answner\").parent().submit();\n" +
            "\t\t\t\t}\n" +
            "\t\t\t});\n" +
            "\t\t},\n" +
            "\t\tafterChange : function() {\n" +
            "\t\t\tthis.sync();\n" +
            "\t\t},\n" +
            "\t\titems : ['bold', 'italic', 'underline', 'strikethrough', 'removeformat','|','insertorderedlist', 'insertunorderedlist', \n" +
            "\t\t\t\t 'forecolor', 'hilitecolor', 'fontname', 'fontsize',  '|', 'link', 'unlink', 'emoticons', \n" +
            "\t\t\t\t 'shcode', 'image', 'flash', 'quote', '|', 'source','about'],\n" +
            "\t\thtmlTags:\n" +
            "\t\t{\n" +
            "\t\t\tscript : ['src'],\n" +
            "            font : ['color', 'size', 'face', '.background-color'],\n" +
            "            span : [\n" +
            "                    '.color', '.background-color', '.font-size', '.font-family', '.background',\n" +
            "                    '.font-weight', '.font-style', '.text-decoration', '.vertical-align', '.line-height'\n" +
            "            ],\n" +
            "            div : [\n" +
            "                    'class', 'align', '.border', '.margin', '.padding', '.text-align', '.color',\n" +
            "                    '.background-color', '.font-size', '.font-family', '.font-weight', '.background',\n" +
            "                    '.font-style', '.text-decoration', '.vertical-align', '.margin-left'\n" +
            "            ],\n" +
            "            table: [\n" +
            "                    'border', 'cellspacing', 'cellpadding', 'width', 'height', 'align', 'bordercolor',\n" +
            "                    '.padding', '.margin', '.border', 'bgcolor', '.text-align', '.color', '.background-color',\n" +
            "                    '.font-size', '.font-family', '.font-weight', '.font-style', '.text-decoration', '.background',\n" +
            "                    '.width', '.height', '.border-collapse'\n" +
            "            ],\n" +
            "            'td,th': [\n" +
            "                    'align', 'valign', 'width', 'height', 'colspan', 'rowspan', 'bgcolor',\n" +
            "                    '.text-align', '.color', '.background-color', '.font-size', '.font-family', '.font-weight',\n" +
            "                    '.font-style', '.text-decoration', '.vertical-align', '.background', '.border'\n" +
            "            ],\n" +
            "            a : ['href', 'target', 'name'],\n" +
            "            embed : ['src', 'width', 'height', 'type', 'loop', 'autostart', 'quality', '.width', '.height', 'align', 'allowscriptaccess'],\n" +
            "            img : ['src', 'width', 'height', 'border', 'alt', 'title', 'align', '.width', '.height', '.border'],\n" +
            "            'p,ol,ul,li,blockquote,h1,h2,h3,h4,h5,h6' : [\n" +
            "                    'align', '.text-align', '.color', '.background-color', '.font-size', '.font-family', '.background',\n" +
            "                    '.font-weight', '.font-style', '.text-decoration', '.vertical-align', '.text-indent', '.margin-left'\n" +
            "            ],\n" +
            "            pre : ['class'],\n" +
            "            hr : ['class', '.page-break-after'],\n" +
            "            'br,tbody,tr,strong,b,sub,sup,em,i,u,strike,s,del' : []\n" +
            "\t\t}\n" +
            "    });\n" +
            "});\n" +
            "//-->\n" +
            "</script>\n" +
            "<!--[if lt IE 7]>\n" +
            "<script type=\"text/javascript\" src=\"/js/minmax.js\"></script>\n" +
            "<![endif]-->\n" +
            "<script type=\"text/javascript\" src=\"/action/visit/question?id=107307\"></script>\n" +
            "<script type='text/javascript'>\n" +
            "<!--\n" +
            "$(document).ready(function() {\n" +
            "\t$('.Answer .replies li').hover(\n" +
            "\t\tfunction(){$(this).addClass('hover');},\n" +
            "\t\tfunction(){$(this).removeClass('hover');}\n" +
            "\t);\t\n" +
            "\t\n" +
            "    $('.detail img').css('cursor','pointer');\n" +
            "    jQuery.each($('.detail img'),function(idx,v){\n" +
            "    \t$(v).wrap(\"<a href='\"+$(this).attr('src')+\"' target='_blank'></a>\");\n" +
            "    });\n" +
            "\t\n" +
            "\t$('#c').bind('mouseover mouseout',function(){\n" +
            "\t\t$('#c_on').toggle();\n" +
            "\t\t$('#c_off').toggle();\n" +
            "\t});\n" +
            "\t\n" +
            "\t$('#favor_trigger').click(function(){\n" +
            "\t\t\tadd_to_favor(107307,2);\n" +
            "\t\t});\n" +
            "});\n" +
            "function ask_too(qid, ask){\n" +
            "\tajax_post(\"/action/question/ask_too?id=\"+qid+\"&ask=\"+ask,\"\",function(html){\n" +
            "\t\tjson = eval('('+html+')');\n" +
            "\t\tif(json.asker_count >= 0){\n" +
            "\t\t\t$('#c_asker_count').html(json.asker_count);\n" +
            "\t\t\tif(json.ask_mode)\n" +
            "\t\t\t\t$('#RQuestionAction').html(\"<span class='rect'>已问同一问题 | <a href='javascript:ask_too(107307,false)'>取消？</a></span>\");\n" +
            "\t\t\telse\n" +
            "\t\t\t\t$('#RQuestionAction').html(\"<a href='javascript:ask_too(107307,true)' class='rndbutton'><span>我想问同样的问题</span></a>\");\t\t\t\n" +
            "\t\t}\n" +
            "\t\telse{\n" +
            "\t\t\t$('#RQuestionAction').poshytip({\n" +
            "\t\t\t\tclassName: 'tip-yellowsimple',\n" +
            "\t\t\t\tcontent: json.msg,\n" +
            "\t\t\t\tshowOn: 'none',\n" +
            "\t\t\t\talignTo: 'target',\n" +
            "\t\t\t\talignX: 'center',\n" +
            "\t\t\t\talignY: 'top',\n" +
            "\t\t\t\toffsetY: 6\n" +
            "\t\t\t});\n" +
            "\t\t\t$('#RQuestionAction').poshytip('show');\n" +
            "\t\t\tvar t = setTimeout(function(){\n" +
            "\t\t\t\tclearTimeout(t);\n" +
            "\t\t\t\t$('#RQuestionAction').poshytip('destroy');\n" +
            "\t\t\t},4000);\n" +
            "\t\t}\n" +
            "\t});\n" +
            "}\n" +
            "function delete_q(qid){\n" +
            "\tif(!confirm(\"您确认要删除此问题吗，删除的数据不可恢复？\"))\n" +
            "\t\treturn ;\n" +
            "\t\tajax_post(\"/action/question/delete?id=\"+qid+\"&hash=-500641190\",\"\",function(html){\n" +
            "\t\tif(html.length>0)\n" +
            "\t\t\talert(html);\n" +
            "\t\telse{\n" +
            "\t\t\tlocation.href = \"/question\";\n" +
            "\t\t}\n" +
            "\t});\n" +
            "}\n" +
            "function edit_answer(aid){\n" +
            "\tlocation.href=\"/question/edit_answer?id=\"+aid;\n" +
            "}\n" +
            "function delete_answer(aid,hash){\n" +
            "\tif(!confirm(\"您确认要删除此答案吗，删除的数据不可恢复？\"))\n" +
            "\t\treturn ;\n" +
            "\tajax_post(\"/action/question/delete_answer?id=\"+aid+\"&hash=\"+hash,\"\",function(html){\n" +
            "\t\tif(html.length>0)\n" +
            "\t\t\talert(html);\n" +
            "\t\telse{\n" +
            "\t\t\t$('#answer_'+aid).fadeOut();\n" +
            "\t\t}\n" +
            "\t});\n" +
            "}\n" +
            "function delete_post_reply(aid,hash){\n" +
            "\tif(!confirm(\"您确认要删除此评论吗，删除的数据不可恢复？\"))\n" +
            "\t\treturn ;\n" +
            "\tajax_post(\"/action/question/delete_answer?id=\"+aid+\"&hash=\"+hash,\"\",function(html){\n" +
            "\t\tif(html.length>0)\n" +
            "\t\t\talert(html);\n" +
            "\t\telse{\n" +
            "\t\t\t$('#PostReply_'+aid).fadeOut();\n" +
            "\t\t}\n" +
            "\t});\n" +
            "}\n" +
            "function delete_q_rpl(qid, rid){\n" +
            "\tif(!confirm(\"删除补充说明会被扣威望值，是否继续？\"))\n" +
            "\t\treturn ;\n" +
            "\tajax_post(\"/action/question/delete_detail?id=\"+rid,\"\",function(html){\n" +
            "\t\tif(html.length>0)\n" +
            "\t\t\talert(html);\n" +
            "\t\telse\n" +
            "\t\t\t$(\"#q_reply_\"+rid).fadeOut();\n" +
            "\t});\n" +
            "}\n" +
            "function close_tip(tid){$('#'+tid).poshytip('destroy');}\n" +
            "//答案投票\n" +
            "function vote_answer(qid, vote_up, need_confirm){\n" +
            "\tif(need_confirm && !vote_up){\n" +
            "\t\tif(!$('#a_post_votedown_' + qid).hasClass('bold')){\n" +
            "\t\t\tvar vote_down_confirm_msg = \"<p>此操作将会扣掉你1个积分，是否继续？</p><p style='margin-top:10px;'><a href='javascript:vote_answer(\"+qid+\",false,false)' class='rbtn' style='margin:0 10px 0 50px;'><span>确定</span></a><a href=\\\"javascript:close_tip('a_post_votedown_\" + qid +\"')\\\" class='rbtn'><span>取消</span></a></p>\";\t\t\t\n" +
            "\t\t\t$('#a_post_votedown_' + qid).poshytip({\n" +
            "\t\t\t\tclassName: 'tip-yellowsimple',\n" +
            "\t\t\t\tcontent: vote_down_confirm_msg,\n" +
            "\t\t\t\tshowOn: 'none',\n" +
            "\t\t\t\tslide: false,\n" +
            "\t\t\t\tfade: false,\n" +
            "\t\t\t\talignTo: 'target',\n" +
            "\t\t\t\talignX: 'center',\n" +
            "\t\t\t\toffsetY: 8\n" +
            "\t\t\t});\n" +
            "\t\t\t$('#a_post_votedown_' + qid).poshytip('show');\n" +
            "\t\t\treturn;\n" +
            "\t\t}\n" +
            "\t}\n" +
            "\tif(!need_confirm){\n" +
            "\t\t$('#a_post_votedown_' + qid).poshytip('destroy');\n" +
            "\t}\n" +
            "\tajax_post(\"/action/question/vote_answer?id=\"+qid+\"&vote=\"+vote_up+\"&user=190591\",\"\",function(data){\n" +
            "\t\tjson = eval('('+data+')');\n" +
            "\t\tif(json.msg){\n" +
            "\t\t\tvar aid = vote_up?\"a_post_voteup_\":\"a_post_votedown_\";\n" +
            "\t\t\taid += qid;\n" +
            "\t\t\t$('#'+aid).poshytip({\n" +
            "\t\t\t\tclassName: 'tip-yellowsimple',\n" +
            "\t\t\t\tcontent: json.msg,\n" +
            "\t\t\t\tshowOn: 'none',\n" +
            "\t\t\t\talignTo: 'target',\n" +
            "\t\t\t\talignX: 'center',\n" +
            "\t\t\t\talignY: 'top',\n" +
            "\t\t\t\toffsetY: 6\n" +
            "\t\t\t});\n" +
            "\t\t\t$('#'+aid).poshytip('show');\n" +
            "\t\t\tvar t = setTimeout(function(){\n" +
            "\t\t\t\tclearTimeout(t);\n" +
            "\t\t\t\t$('#'+aid).poshytip('destroy');\n" +
            "\t\t\t},2000);\n" +
            "\t\t\t//jQuery.fancybox(\"<div class='error_box'>\"+json.msg+\"</div>\");\n" +
            "\t\t}\n" +
            "\t\telse{\n" +
            "\t\t\tif(vote_up){\n" +
            "\t\t\t\t$('#post_voteup_'+qid).html('('+json.vote+')');\n" +
            "\t\t\t\t$('#a_post_voteup_'+qid).toggleClass('bold');\n" +
            "\t\t\t}\n" +
            "\t\t\telse{\n" +
            "\t\t\t\t$('#post_votedown_'+qid).html('('+json.vote+')');\n" +
            "\t\t\t\t$('#a_post_votedown_'+qid).toggleClass('bold');\n" +
            "\t\t\t}\n" +
            "\t\t}\n" +
            "\t});\n" +
            "}\n" +
            "//问题投票 \n" +
            "$('#vote_up').click(function(){\n" +
            "\tif(this.clickTimeout){\n" +
            "        // 双击\n" +
            "        clearTimeout(this.clickTimeout);\n" +
            "        this.clickTimeout = null;\n" +
            "\t\talert(\"不用那么费劲啦，点击一下就够了:)\");\n" +
            "    }\n" +
            "    else{\n" +
            "        // 单击\n" +
            "        var elem = this;\n" +
            "        this.clickTimeout = setTimeout(function(){\n" +
            "            // 执行点击动作\n" +
            "            elem.clickTimeout = null;\n" +
            "\t\t\tvote_question(107307,true, true);\n" +
            "        }, 250);\n" +
            "    }\n" +
            "    //阻止链接onclick时的默认行为\n" +
            "    return false;\n" +
            "});\n" +
            "$('#vote_down').click(function(){\n" +
            "\tvote_question(107307,false, true);\n" +
            "\t/*\n" +
            "\tif(this.clickTimeout){\n" +
            "        // 双击\n" +
            "        clearTimeout(this.clickTimeout);\n" +
            "        this.clickTimeout = null;\n" +
            "\t\talert(\"不用那么费劲啦，点击一下就够了:)\");\n" +
            "    }\n" +
            "    else{\n" +
            "        // 单击\n" +
            "        var elem = this;\n" +
            "        this.clickTimeout = setTimeout(function(){\n" +
            "            // 执行点击动作\n" +
            "            elem.clickTimeout = null;\n" +
            "\t\t\tvote_question(107307,false, true);\n" +
            "        }, 250);\n" +
            "    }\n" +
            "    //阻止链接onclick时的默认行为\n" +
            "\t*/\n" +
            "    return false;\n" +
            "});\n" +
            "function vote_question(qid, vote_up, need_confirm){\n" +
            "\tif(need_confirm && !vote_up){\n" +
            "\t\tif($('#Vote #vote_down').hasClass('ask_vote_down')){\n" +
            "\t\t\tvar vote_down_confirm_msg = \"<p>踩问题将会扣掉你1个积分，是否继续？</p><p style='margin-top:10px;'><a href='javascript:vote_question(107307,false,false)' class='rbtn' style='margin-right:10px;'><span>确定</span></a><a href=\\\"javascript:close_tip('vote_down')\\\" class='rbtn'><span>取消</span></a></p>\";\n" +
            "\t\t\t$('#Vote #vote_down').poshytip({\n" +
            "\t\t\t\tclassName: 'tip-yellowsimple',\n" +
            "\t\t\t\tcontent: vote_down_confirm_msg,\n" +
            "\t\t\t\tslide: false,\n" +
            "\t\t\t\tfade: false,\n" +
            "\t\t\t\tshowOn: 'none',\n" +
            "\t\t\t\talignTo: 'target',\n" +
            "\t\t\t\talignX: 'inner-right',\n" +
            "\t\t\t\talignY: 'bottom',\n" +
            "\t\t\t\toffsetX: -30,\n" +
            "\t\t\t\toffsetY: 15\n" +
            "\t\t\t});\n" +
            "\t\t\t$('#Vote #vote_down').poshytip('show');\n" +
            "\t\t\treturn;\n" +
            "\t\t}\n" +
            "\t}\n" +
            "\tif(!need_confirm){\n" +
            "\t\t$('#Vote #vote_down').poshytip('destroy');\n" +
            "\t}\n" +
            "\tajax_post(\"/action/question/vote?user=190591&id=\"+qid+\"&vote=\"+vote_up,\"\",function(data){\n" +
            "\t\tjson = eval('('+data+')');\n" +
            "\t\tif(json.msg){\n" +
            "\t\t\tvar aid = vote_up?\"vote_up\":\"vote_down\";\n" +
            "\t\t\t$('#'+aid).poshytip({\n" +
            "\t\t\t\tclassName: 'tip-yellowsimple',\n" +
            "\t\t\t\tcontent: json.msg,\n" +
            "\t\t\t\tshowOn: 'none',\n" +
            "\t\t\t\talignTo: 'target',\n" +
            "\t\t\t\talignX: 'inner-right',\n" +
            "\t\t\t\talignY: 'bottom',\n" +
            "\t\t\t\toffsetX: 5,\n" +
            "\t\t\t\toffsetX: -35\n" +
            "\t\t\t});\n" +
            "\t\t\t$('#'+aid).poshytip('show');\n" +
            "\t\t\tvar t = setTimeout(function(){\n" +
            "\t\t\t\tclearTimeout(t);\n" +
            "\t\t\t\t$('#'+aid).poshytip('destroy');\n" +
            "\t\t\t},2000);\n" +
            "\t\t}\n" +
            "\t\telse{\n" +
            "\t\t\t\t\t\tif(vote_up){\n" +
            "\t\t\t\t$('#Vote .vote-up-count').html(json.vote_up);\n" +
            "\t\t\t\t$('#Vote #vote_up').toggleClass('ask_vote_up');\n" +
            "\t\t\t\t$('#Vote #vote_up').toggleClass('ask_vote_uped');\n" +
            "\t\t\t}\n" +
            "\t\t\telse{\n" +
            "\t\t\t\t$('#Vote .vote-down-count').html(json.vote_down);\n" +
            "\t\t\t\t$('#Vote #vote_down').toggleClass('ask_vote_down');\n" +
            "\t\t\t\t$('#Vote #vote_down').toggleClass('ask_vote_downed');\n" +
            "\t\t\t}\n" +
            "\t\t}\n" +
            "\t});\n" +
            "}\n" +
            "//评论答案\n" +
            "function reply_to_post(postid,uid){\n" +
            "\tpopup(\"/action/ajax/reply_to_post?id=\" + postid + \"&refer=\"+uid);\n" +
            "\t}\n" +
            "\n" +
            "function show_rp_next(postid,current,total){\n" +
            "\tif(current < total){\n" +
            "\t\tvar next_page = current + 1;\n" +
            "    \tvar url = \"/question/post_replies?answer=\"+postid+\"&rp=\"+next_page;\n" +
            "    \tajax_post(url,\"\",function(html){\n" +
            "    \t\t$('#PostReplies_'+postid).html(html);\n" +
            "\t\t\t\taddRepliesHoverEvent();\n" +
            "\t\t\t});\n" +
            "\t}\n" +
            "}\n" +
            "\n" +
            "function addRepliesHoverEvent(){\n" +
            "\t$(\"li [id ^= 'PostReply']\").hover(function(){\n" +
            "\t\t$(this).addClass(\"hover\");\n" +
            "\t},function(){\n" +
            "\t\t$(this).removeClass(\"hover\");\n" +
            "\t});\n" +
            "}\n" +
            "\n" +
            "function show_rp_prev(postid,current,total){\n" +
            "\tif(current > 1){\n" +
            "\t\tvar next_page = current - 1;\n" +
            "    \tvar url = \"/question/post_replies?answer=\"+postid+\"&rp=\"+next_page;\n" +
            "    \tajax_post(url,\"\",function(html){\n" +
            "    \t\t$('#PostReplies_'+postid).html(html);\n" +
            "\t\t\taddRepliesHoverEvent();\n" +
            "    \t});\n" +
            "\t}\n" +
            "}\n" +
            "function mark_as_top(qid, as_top) {\n" +
            "\tvar args = \"id=\"+qid+\"&top=\"+as_top;\n" +
            "\tajax_post(\"/action/question/mark_as_top\",args,function(html){\n" +
            "\t\talert(html);\n" +
            "\t});\n" +
            "}\n" +
            "function mark_as_best(postid, is_best){\n" +
            "\tvar args = \"id=\"+postid+\"&best=\"+(is_best?1:0);\n" +
            "\tajax_post(\"/action/question/mark_as_best\",args,function(html){\n" +
            "    \tif(html.length>0){\n" +
            "    \t\t$('#best_answer_'+postid).poshytip({\n" +
            "    \t\t\tclassName: 'tip-yellowsimple',\n" +
            "    \t\t\tcontent: html,\n" +
            "    \t\t\tshowOn: 'none',\n" +
            "    \t\t\talignTo: 'target',\n" +
            "    \t\t\talignX: 'center',\n" +
            "    \t\t\talignY: 'top',\n" +
            "    \t\t\toffsetY: 6\n" +
            "    \t\t});\n" +
            "    \t\t$('#best_answer_'+postid).poshytip('show');\n" +
            "    \t\tvar t = setTimeout(function(){\n" +
            "    \t\t\tclearTimeout(t);\n" +
            "    \t\t\t$('#best_answer_'+postid).poshytip('destroy');\n" +
            "    \t\t},2000);\n" +
            "    \t}\n" +
            "    \telse{\n" +
            "\t\t\tif(is_best)\n" +
            "\t\t\t\t$('#answer_'+postid).addClass('Best');\n" +
            "\t\t\telse\n" +
            "\t\t\t\t$('#answer_'+postid).removeClass('Best');\n" +
            "    \t}\n" +
            "\t});\n" +
            "}\n" +
            "function edit_tags(qid){\n" +
            "\tpopup(\"/question/edit_tags?question=\"+qid);\n" +
            "}\n" +
            "function edit_catalogs(qid){\n" +
            "\tpopup(\"/admin/catalog/set-catalogs?parent=1&type=2&id=\"+qid);\n" +
            "}\n" +
            "function event_apply(event_id){\t\n" +
            "\tpopup(\"/action/ajax/event_apply\",\"id=\"+event_id);\n" +
            "}\n" +
            "function cancel_apply(event_id){\n" +
            "\tif(confirm(\"您确认要取消参加此次活动吗？\")){\n" +
            "\t\tajax_post(\"/action/event/cancel\",\"event=\"+event_id,function(html){\n" +
            "\t\t\tif(html.length>0)\n" +
            "\t\t\t\talert(html);\n" +
            "\t\t\telse\n" +
            "\t\t\t\talert('已取消参加此次活动，感谢您的支持:)');\n" +
            "\t\t});\n" +
            "\t}\n" +
            "}\n" +
            "\n" +
            "var favor_ok = \"<p class='favor_ok'>已成功添加到收藏夹<br/><br/> <a href='http://my.oschina.net/flashsword/favorites?type=$DAISY_OBJ_TYPE'>我的收藏夹</a> | <a href='javascript:close_favor()'>关闭</a></p>\";\n" +
            "function delete_favor(obi_id, obj_type){\n" +
            "\tif(!confirm('确定取消收藏？')) return;\n" +
            "\t$.post(\"/action/favorite/cancel?type=\"+obj_type+\"&id=\"+obi_id+\"&user=190591\",function(html){\n" +
            "\t\t$('#favor_trigger').parent('div').removeClass('ask_collected').addClass('ask_collect');\n" +
            "\t\t$('#favor_trigger').attr('title','添加到收藏');\n" +
            "    \t$('#p_favor_count').html(html);\n" +
            "\t\t$('#favor_trigger').unbind('click');\n" +
            "\t\t$('#favor_trigger').click(function(){add_to_favor(107307,2);});\n" +
            "\t});\n" +
            "}\n" +
            "function add_to_favor(obj_id, obj_type){\n" +
            "    var dlg_favor = \"<div id='favor_form'><p>多个标签使用逗号(,)隔开，最多三个</p><form action='/action/favorite/add?user=190591' height='60px' width='200px' method='POST'>\";\n" +
            "\tdlg_favor += \"<input type='hidden' name='id' value='\"+obj_id+\"'/>\";\n" +
            "\tdlg_favor += \"<input type='hidden' name='type' value='\"+obj_type+\"'/>\";\n" +
            "\tdlg_favor += \"<input type='text' name='tags' size='25' class='_favor_input' id='_favor_tags'/>\";\n" +
            "\tdlg_favor += \"<input type='submit' value='收藏' class='_favor_button'/><input type='button' value='取消' onclick='close_favor();' class='_favor_button'/><a id='TagsSwitcher' state='off'>选取标签↓</a></form>\";\n" +
            "\tdlg_favor += \"<div id='MyTags' ></div></div>\";\n" +
            "    $('#favor_trigger').poshytip('destroy');\n" +
            "    $('#favor_trigger').poshytip({\n" +
            "    \tclassName: 'tip-yellowsimple',\n" +
            "    \tcontent: dlg_favor,\n" +
            "    \tshowOn: 'none',\n" +
            "    \talignTo: 'target',\t\n" +
            "\t\talignX: 'inner-right',\n" +
            "\t\talignY: 'bottom',\n" +
            "\t\toffsetX: -20,\n" +
            "\t\toffsetY: 15\n" +
            "    });\n" +
            "    $('#favor_trigger').poshytip('show');\n" +
            "\t$('#_favor_tags').focus();\n" +
            "\t$('#favor_form form').ajaxForm({\n" +
            "\t\tsuccess: function(html) {\n" +
            "\t\t\t$('#favor_trigger').parent('div').removeClass('ask_collect').addClass('ask_collected');\n" +
            "\t\t\t$('#favor_trigger').attr('title','取消收藏');\n" +
            "\t\t\t$('#p_favor_count').html(html);\n" +
            "\t\t\t$('#favor_trigger').unbind('click');\n" +
            "\t\t\t$('#favor_trigger').click(function(){delete_favor(107307,2);});\n" +
            "\t\t\t$('#favor_form').html(favor_ok);\n" +
            "\t\t\tsetTimeout(\"close_favor()\",3000);\n" +
            "\t\t}\n" +
            "\t});\n" +
            "\t$(\"#TagsSwitcher\").one(\"click\",function(){\n" +
            "\t\t//加载标签数据\n" +
            "\t\t$(\"#MyTags\").load('/action/ajax/get_my_tags');\n" +
            "       \t$(\"#MyTags\").toggle();\n" +
            "\t\t$(this).html(\"收起标签↑\");\n" +
            "        $(this).attr(\"state\",'on');\n" +
            "        $(this).click(function(){\n" +
            "        \t$(\"#MyTags\").toggle();\n" +
            "        \tvar state = $(this).attr(\"state\");\n" +
            "        \tif(state=='off'){\n" +
            "        \t\t$(this).html(\"收起标签↑\");\n" +
            "        \t\t$(this).attr(\"state\",'on');\n" +
            "        \t}else{\n" +
            "        \t\t$(this).html(\"选取标签↓\");\n" +
            "        \t\t$(this).attr(\"state\",'off');\n" +
            "        \t}\n" +
            "        });\n" +
            "\t});\n" +
            "\n" +
            "}\n" +
            "function close_favor(elem_id){\n" +
            "    $('#favor_trigger').poshytip('destroy');\n" +
            "}\n" +
            "function setTag(tv){\n" +
            "\tvar t = $(\"._favor_input\");\n" +
            "\tvar value = t.val();\n" +
            "\tif(value!=\"\")\n" +
            "\t\tt.val(value+\",\"+tv);\n" +
            "\telse\n" +
            "\t\tt.val(tv)\n" +
            "}\n" +
            "//-->\n" +
            "</script><div class='clear'></div></div>\n" +
            "\t<div id=\"OSC_Footer\" class='CenterDiv'><style>\n" +
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
            "</html>\n" +
            "<!-- Generated by OsChina.NET (init:1[ms],page:43[ms],ip:60.55.11.77) -->";

    @Test
    public void test(){
         String text = "\n" +
                 "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                 "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"zh-CN\" dir=\"ltr\">\n" +
                 "  <head>\n" +
                 "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\n" +
                 "    <title>jsoup 解析页面商品信息 -  - ITeye技术网站</title>\n" +
                 "    <meta name=\"description\" content=\"今天用了jsoup 解析页面商品信息，感觉比用xpath获取信息准确多了     下面就记录一下：  一、首先去 http://jsoup.org/download 下载jsoup的jar包。     二、下面记录下相关代码：              Document doc = Jsoup.connect(url).get();    //将htm转换成Document类型数据结构        ...\" />\n" +
                 "    <meta name=\"keywords\" content=\" jsoup 解析页面商品信息\" />\n" +
                 "    <link rel=\"shortcut icon\" href=\"/images/favicon.ico\" type=\"image/x-icon\" />\n" +
                 "    <link rel=\"search\" type=\"application/opensearchdescription+xml\" href=\"/open_search.xml\" title=\"ITeye\" />\n" +
                 "    <link href=\"/rss\" rel=\"alternate\" title=\"\" type=\"application/rss+xml\" />\n" +
                 "    <link href=\"http://www.iteye.com/stylesheets/blog.css?1365750118\" media=\"screen\" rel=\"stylesheet\" type=\"text/css\" />\n" +
                 "<link href=\"http://www.iteye.com/stylesheets/themes/blog/blue.css?1326191326\" media=\"screen\" rel=\"stylesheet\" type=\"text/css\" />\n" +
                 "    <script src=\"http://www.iteye.com/javascripts/application.js?1358214518\" type=\"text/javascript\"></script>\n" +
                 "    <script type=\"text/javascript\">\n" +
                 "\n" +
                 "  var _gaq = _gaq || [];\n" +
                 "  _gaq.push(['_setAccount', 'UA-535605-1']);\n" +
                 "  _gaq.push(['_setDomainName', 'iteye.com']);\n" +
                 "  _gaq.push(['_trackPageview']);\n" +
                 "\n" +
                 "  (function() {\n" +
                 "    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;\n" +
                 "    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';\n" +
                 "    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);\n" +
                 "  })();\n" +
                 "\n" +
                 "</script>\n" +
                 "\n" +
                 "\n" +
                 "      <link href=\"http://www.iteye.com/javascripts/syntaxhighlighter/SyntaxHighlighter.css?1348819953\" media=\"screen\" rel=\"stylesheet\" type=\"text/css\" />\n" +
                 "  <script src=\"http://www.iteye.com/javascripts/syntaxhighlighter/shCoreCommon.js?1325907333\" type=\"text/javascript\"></script>\n" +
                 "<script src=\"http://www.iteye.com/javascripts/hotkey.js?1324994303\" type=\"text/javascript\"></script>\n" +
                 "  <script src=\"http://www.iteye.com/javascripts/code_favorites.js?1358214518\" type=\"text/javascript\"></script>\n" +
                 "<script src=\"http://www.iteye.com/javascripts/weiboshare.js?1324994303\" type=\"text/javascript\"></script>\n" +
                 "  <link href=\"http://www.iteye.com/javascripts/editor/css/ui.css?1324994303\" media=\"screen\" rel=\"stylesheet\" type=\"text/css\" />\n" +
                 "  <script src=\"http://www.iteye.com/javascripts/editor/compress.js?1358129160\" type=\"text/javascript\"></script>\n" +
                 "  </head>\n" +
                 "  <body>\n" +
                 "    <div id=\"header\">\n" +
                 "      <div id=\"blog_site_nav\">\n" +
                 "  <a href=\"http://www.iteye.com/\" class=\"homepage\">首页</a>\n" +
                 "  <a href=\"http://www.iteye.com/news\">资讯</a>\n" +
                 "  <a href=\"http://www.iteye.com/magazines\">精华</a>\n" +
                 "  <a href=\"http://www.iteye.com/forums\">论坛</a>\n" +
                 "  <a href=\"http://www.iteye.com/ask\">问答</a>\n" +
                 "  <a href=\"http://www.iteye.com/blogs\">博客</a>\n" +
                 "  <a href=\"http://www.iteye.com/blogs/subjects\">专栏</a>\n" +
                 "  <a href=\"http://www.iteye.com/groups\">群组</a>\n" +
                 "  <a href=\"#\" onclick=\"return false;\" id=\"msna\"><u>更多</u> <small>▼</small></a>\n" +
                 "  <div class=\"quick_menu\" style=\"display:none;\">\n" +
                 "    <a target=\"_blank\" href=\"http://job.iteye.com/iteye\">招聘</a>\n" +
                 "    <a href=\"http://www.iteye.com/search\">搜索</a>\n" +
                 "  </div>\n" +
                 "</div>\n" +
                 "\n" +
                 "      <div id=\"user_nav\">\n" +
                 "  \n" +
                 "        <a href=\"http://flashsword20.iteye.com\" title=\"查看我的博客首页\" class=\"welcome\">欢迎flashsword20</a>\n" +
                 "    <a id=\"notifications_count\" href=\"http://my.iteye.com/notifications\">0</a>\n" +
                 "    \n" +
                 "      <a href=\"http://my.iteye.com/messages\" title=\"你有新的站内短信\"><img alt=\"Newpm\" src=\"http://www.iteye.com/images/newpm.gif?1324994303\" />收件箱(3)</a>\n" +
                 "    \n" +
                 "    <a href=\"http://my.iteye.com\" title=\"我的应用首页\">我的应用</a>\n" +
                 "    <div class=\"quick_menu\" style=\"display:none;\">\n" +
                 "      <a href=\"http://my.iteye.com/feed\" title=\"我关注的好友动态消息\">我的关注</a>\n" +
                 "      <a href=\"http://my.iteye.com/mygroup\" title=\"我加入的群组最新话题\">我的群组</a>\n" +
                 "      <a href=\"http://my.iteye.com/myresume\" title=\"我的个人简历\">我的简历</a>\n" +
                 "      <a href=\"http://my.iteye.com/admin/album\" title=\"我的个人简历\">我的相册</a>\n" +
                 "      <a href=\"http://my.iteye.com/admin/link\" title=\"我收藏的网络资源链接\">我的收藏</a>\n" +
                 "      <a href=\"http://my.iteye.com/admin/code\" title=\"我收藏的代码\">我的代码</a>\n" +
                 "      <a href=\"http://my.iteye.com/admin/weibo\" title=\"用微博发表简短的话题\">我的微博</a>\n" +
                 "    </div>\n" +
                 "    <a href=\"http://flashsword20.iteye.com/admin\" title=\"管理我的博客\">我的博客</a>\n" +
                 "    <a href=\"http://my.iteye.com/profile\" title=\"修改我的个人设置\">设置</a>\n" +
                 "    <a href=\"/logout\" class=\"nobg\" onclick=\"var f = document.createElement('form'); f.style.display = 'none'; this.parentNode.appendChild(f); f.method = 'POST'; f.action = this.href;var m = document.createElement('input'); m.setAttribute('type', 'hidden'); m.setAttribute('name', '_method'); m.setAttribute('value', 'put'); f.appendChild(m);var s = document.createElement('input'); s.setAttribute('type', 'hidden'); s.setAttribute('name', 'authenticity_token'); s.setAttribute('value', 'TDmn/IsWi1Aj4CXKfdMKZZzALz6jbRU/Biw0/QHnsVw='); f.appendChild(s);f.submit();return false;\">退出</a>\n" +
                 "  </div>\n" +
                 "\n" +
                 "    </div>\n" +
                 "\n" +
                 "    <div id=\"page\">\n" +
                 "      <div id=\"branding\" class=\"clearfix\">\n" +
                 "        <div id=\"blog_name\">\n" +
                 "          <h1><a href=\"/\">masong1987</a></h1>\n" +
                 "        </div>\n" +
                 "        <div id='fd'></div>\n" +
                 "        <div id=\"blog_navbar\">\n" +
                 "          <ul>\n" +
                 "            <li class='blog_navbar_for'><a href=\"http://masong1987.iteye.com\"><strong>博客</strong></a></li>\n" +
                 "            <li ><a href=\"/weibo\">微博</a></li>\n" +
                 "            <li ><a href=\"/album\">相册</a></li>\n" +
                 "            <li ><a href=\"/link\">收藏</a></li>\n" +
                 "            <li ><a href=\"/blog/guest_book\">留言</a></li>\n" +
                 "            <li ><a href=\"/blog/profile\">关于我</a></li>\n" +
                 "          </ul>\n" +
                 "    \n" +
                 "          <div class=\"search\">\n" +
                 "            <form action=\"/blog/search\" method=\"get\">\n" +
                 "              <input class=\"search_text\" id=\"query\" name=\"query\" style=\"margin-left: 10px;width: 110px;\" type=\"text\" value=\"\" />\n" +
                 "              <input class=\"submit_search\" type=\"submit\" value=\"\" />\n" +
                 "            </form>\n" +
                 "          </div> \n" +
                 "          <div id=\"fd\"></div>         \n" +
                 "        </div>\n" +
                 "      </div>\n" +
                 "      \n" +
                 "      <div id=\"content\" class=\"clearfix\">\n" +
                 "        <div id=\"main\">\n" +
                 "          \n" +
                 "\n" +
                 "\n" +
                 "\n" +
                 "          \n" +
                 "\n" +
                 "\n" +
                 "<div class=\"blog_main\">\n" +
                 "  <div class=\"blog_title\">\n" +
                 "    <h3>\n" +
                 "      <a href=\"/blog/1191016\">jsoup 解析页面商品信息</a>\n" +
                 "      <em class=\"actions\">      </em>\n" +
                 "    </h3>\n" +
                 "    <ul class='blog_categories'><strong>博客分类：</strong> <li><a href=\"/category/182324\">爬虫</a></li> </ul>\n" +
                 "        <div class='news_tag'>&nbsp;</div>\n" +
                 "  </div>\n" +
                 "\n" +
                 "  <div id=\"blog_content\" class=\"blog_content\">\n" +
                 "    <p>今天用了jsoup 解析页面商品信息，感觉比用xpath获取信息准确多了</p>\n" +
                 "<p> </p>\n" +
                 "<p>下面就记录一下：</p>\n" +
                 "<p>一、首先去 <a href=\"http://jsoup.org/download\">http://jsoup.org/download</a> 下载jsoup的jar包。</p>\n" +
                 "<p> </p>\n" +
                 "<p>二、下面记录下相关代码：</p>\n" +
                 "<p> </p>\n" +
                 "<p> </p>\n" +
                 "<p>      Document doc = Jsoup.connect(url).get();    //将htm转换成Document类型数据结构</p>\n" +
                 "<p> <br>      doc.select(\"div:has(div) div#spec-n1:has(img) img\").first().attr(\"src\"));    //查找div下含有div的标签</p>\n" +
                 "<p>      </p>\n" +
                 "<p>      并且 div的id='spec-n1'，此div第一个img标签，img里属性是src的值。</p>\n" +
                 "<p> </p>\n" +
                 "<p>      doc.select(\"div:has(div) div.crumb:has(a) a:eq(4)\").text();    //查找class='crumb'的div下第4个a标签</p>\n" +
                 "<p>      下的值。</p>\n" +
                 "<p> </p>\n" +
                 "<p>      doc.select(\"div:has(div) div#name:has(h1)\").text();     //查找id='name'的div下的h1标签的值。</p>\n" +
                 "<p> </p>\n" +
                 "<p>      doc.select(\"tbody:has(tr) td.tdTitle:contains(品牌) + td\").text();     //查找class='tdTitle'的td标签里</p>\n" +
                 "<p> </p>\n" +
                 "<p>      含有‘品牌’td的下一个td标签中内容。</p>\n" +
                 "<p> </p>\n" +
                 "<p>      doc.select(\"script[type=text/javascript]:not([src~=[a-zA-Z0-9./\\\\s]+)\");     //查找含有此&lt;script </p>\n" +
                 "<p> </p>\n" +
                 "<p>      type=\"text/javascript\"&gt;……&lt;/script&gt;内容，不含有script标签中有src属性的script，如：</p>\n" +
                 "<p> </p>\n" +
                 "<p>      &lt;script src=\"url\" type=\"text/javascript\"&gt;&lt;/script&gt;。</p>\n" +
                 "  </div>\n" +
                 "\n" +
                 "  \n" +
                 "\n" +
                 "\n" +
                 "  <IFRAME SRC=\"/iframe_ggbd/794\" SCROLLING=no WIDTH=468 HEIGHT=60 FRAMEBORDER=0></IFRAME>\n" +
                 "  \n" +
                 "  <div id=\"bottoms\" class=\"clearfix\">\n" +
                 "    \n" +
                 "    <div id=\"share_weibo\">分享到：\n" +
                 "      <a data-type='sina' href=\"javascript:;\" title=\"分享到新浪微博\"><img src=\"/images/sina.jpg\"></a>\n" +
                 "      <a data-type='qq' href=\"javascript:;\" title=\"分享到腾讯微博\"><img src=\"/images/tec.jpg\"></a>\n" +
                 "    </div>\n" +
                 "  </div>\n" +
                 "\n" +
                 "  <div class=\"blog_nav\">\n" +
                 "    <div class=\"pre_next\">\n" +
                 "      <a href=\"/blog/1310327\" class=\"next\" title=\"ibatis中书写SQL语句时使用in遇到的问题\">ibatis中书写SQL语句时使用in遇到的问题</a>\n" +
                 "      |\n" +
                 "      <a href=\"/blog/1189699\" class=\"pre\" title=\"尚未备份数据库 &quot;***&quot; 的日志尾部。如果该日志包含您不希望丢失的工作，请使用 BACKUP LOG WITH NORECOVERY 备份该日志。请使用 RE\">尚未备份数据库 &quot;***&quot; 的日志尾部。如果该 ...</a>\n" +
                 "    </div>\n" +
                 "  </div>\n" +
                 "  <div class=\"blog_bottom\">\n" +
                 "    <ul>\n" +
                 "      <li>2011-10-12 18:52</li>\n" +
                 "      <li>浏览 692</li>\n" +
                 "      <li><a href=\"#comments\">评论(0)</a></li>\n" +
                 "      \n" +
                 "      \n" +
                 "        <li><a href='/admin/link?user_favorite%5Btitle%5D=jsoup+%E8%A7%A3%E6%9E%90%E9%A1%B5%E9%9D%A2%E5%95%86%E5%93%81%E4%BF%A1%E6%81%AF&amp;user_favorite%5Burl%5D=http%3A%2F%2Fmasong1987.iteye.com%2Fblog%2F1191016' target='_blank' class='favorite' onclick=\"$$('.favorite_form_spinner')[0].show();new Ajax.Request('/admin/link/new_xhr?user_favorite%5Btitle%5D=jsoup+%E8%A7%A3%E6%9E%90%E9%A1%B5%E9%9D%A2%E5%95%86%E5%93%81%E4%BF%A1%E6%81%AF&amp;user_favorite%5Burl%5D=http%3A%2F%2Fmasong1987.iteye.com%2Fblog%2F1191016', {method: 'get', onSuccess: function(response){$(document.getElementsByTagName('body')[0]).insert({bottom:response.responseText});$$('.favorite_form_spinner')[0].hide();}});return false;\">收藏</a><img alt=\"Spinner\" class=\"favorite_form_spinner\" src=\"http://www.iteye.com/images/spinner.gif?1324994303\" style=\"vertical-align:bottom;margin-left:7px;display:none;\" /></li>\n" +
                 "      \n" +
                 "      <li>分类:<a href=\"http://www.iteye.com/blogs/category/opensource\">开源软件</a></li>      \n" +
                 "      <li class='last'><a href=\"http://www.iteye.com/wiki/blog/1191016\" target=\"_blank\" class=\"more\">相关推荐</a></li>\n" +
                 "    </ul>\n" +
                 "  </div>\n" +
                 "\n" +
                 "  <div class=\"blog_comment\">\n" +
                 "    <h5>评论</h5>\n" +
                 "    <a id=\"comments\" name=\"comments\"></a>\n" +
                 "    \n" +
                 "    \n" +
                 "    \n" +
                 "  </div>\n" +
                 "\n" +
                 "  <div class=\"blog_comment\">\n" +
                 "    <h5>发表评论</h5>\n" +
                 "            <form action=\"/blog/1191016\" id=\"comment_form\" method=\"post\" onsubmit=\"return false;\"><div style=\"margin:0;padding:0;display:inline\"><input name=\"authenticity_token\" type=\"hidden\" value=\"TDmn/IsWi1Aj4CXKfdMKZZzALz6jbRU/Biw0/QHnsVw=\" /></div>          \n" +
                 "\n" +
                 "\n" +
                 "  <input type=\"hidden\" id=\"editor_bbcode_flag\"/>\n" +
                 "\n" +
                 "\n" +
                 "\n" +
                 "<div id=\"editor_main\"><textarea class=\"validate-richeditor bad-words min-length-5\" cols=\"40\" id=\"editor_body\" name=\"comment[body]\" rows=\"20\" style=\"width: 500px; height: 350px;\"></textarea></div>\n" +
                 "\n" +
                 "\n" +
                 "<script type=\"text/javascript\">\n" +
                 "  var editor = new Control.TextArea.Editor(\"editor_body\", \"bbcode\", false);\n" +
                 "</script>\n" +
                 "\n" +
                 "          <p style=\"text-align:right;margin-right:30px;\">(快捷键 Alt+S / Ctrl+Enter) <input class=\"submit\" id=\"quick_reply_button\" name=\"commit\" type=\"submit\" value=\"提交\" /></p>\n" +
                 "       </form>\n" +
                 "        <script type=\"text/javascript\">\n" +
                 "          new HotKey(\"s\",function() {$('quick_reply_button').click();},{altKey: true, ctrlKey: false});\n" +
                 "          new HotKey(new Number(13),function() {$('quick_reply_button').click();},{altKey: false, ctrlKey: true});\n" +
                 "\n" +
                 "          new Validation(\"comment_form\", {immediate: false, onFormValidate: function(result, form){\n" +
                 "            if(result) {\n" +
                 "              new Ajax.Request('/blog/create_comment/1191016', {\n" +
                 "                onFailure:function(response){\n" +
                 "                  $('comments').insert({after:response.responseText})\n" +
                 "                  form.spinner.hide();\n" +
                 "                  Element.scrollTo($('comments'));\n" +
                 "                },\n" +
                 "                onSuccess:function(response){\n" +
                 "                  Element.scrollTo($('comments'));\n" +
                 "                  var new_comment = new Element('div', {}).update(response.responseText).firstChild;\n" +
                 "                  var comment_id = new_comment.readAttribute('id');\n" +
                 "\n" +
                 "                  $('comments').insert({after:response.responseText});\n" +
                 "                  $('editor_body').value = \"\";\n" +
                 "\n" +
                 "                  var css_rules = '#' + comment_id + ' pre';\n" +
                 "                  highlightNewAddContent(css_rules);\n" +
                 "                  processComment();\n" +
                 "                  code_favorites_init(css_rules);\n" +
                 "                  \n" +
                 "                  form.spinner.hide();\n" +
                 "                }, parameters:Form.serialize(form)\n" +
                 "              });\n" +
                 "            }\n" +
                 "        }});\n" +
                 "        </script>\n" +
                 "        </div>\n" +
                 "</div>\n" +
                 "\n" +
                 "\n" +
                 "<script type=\"text/javascript\">\n" +
                 "  dp.SyntaxHighlighter.HighlightAll('code', true, true);\n" +
                 "\n" +
                 "  $$('#main .blog_content pre[name=code]').each(function(pre, index){ // blog content\n" +
                 "    var post_id = 1191016;\n" +
                 "    var location = window.location;\n" +
                 "    source_url = location.protocol + \"//\" + location.host + location.pathname + location.search;\n" +
                 "    pre.writeAttribute('codeable_id', post_id);\n" +
                 "    pre.writeAttribute('codeable_type', \"Blog\");\n" +
                 "    pre.writeAttribute('source_url', source_url);\n" +
                 "    pre.writeAttribute('pre_index', index);\n" +
                 "    pre.writeAttribute('title', 'jsoup 解析页面商品信息');\n" +
                 "  });\n" +
                 "\n" +
                 "  fix_image_size($$('div.blog_content img'), 700);\n" +
                 "\n" +
                 "  function processComment() {\n" +
                 "    $$('#main .blog_comment > div').each(function(comment){// comment\n" +
                 "      var post_id = comment.id.substr(2);\n" +
                 "      $$(\"#\"+comment.id+\" pre[name=code]\").each(function(pre, index){\n" +
                 "        var location = window.location;\n" +
                 "        source_url = location.protocol + \"//\" + location.host + location.pathname + location.search;\n" +
                 "        source_url += \"#\" + comment.id;\n" +
                 "        pre.writeAttribute('codeable_id', post_id);\n" +
                 "        pre.writeAttribute('codeable_type', \"BlogComment\");\n" +
                 "        pre.writeAttribute('source_url', source_url);\n" +
                 "        pre.writeAttribute('pre_index', index);\n" +
                 "        pre.writeAttribute('title', 'jsoup 解析页面商品信息');\n" +
                 "      });\n" +
                 "    });\n" +
                 "  }\n" +
                 "\n" +
                 "  function quote_comment(id) {\n" +
                 "    new Ajax.Request('/editor/quote', {\n" +
                 "      parameters: {'id':id, 'type':'BlogComment'},\n" +
                 "      onSuccess:function(response){editor.bbcode_editor.textarea.insertAfterSelection(response.responseText);\n" +
                 "        Element.scrollTo(editor.bbcode_editor.textarea.element);}\n" +
                 "    });\n" +
                 "  }\n" +
                 "\n" +
                 "  code_favorites_init();\n" +
                 "  processComment();\n" +
                 "  new WeiboShare({share_buttons: $('share_weibo'), img_scope: $('blog_content')});\n" +
                 "</script>\n" +
                 "\n" +
                 "\n" +
                 "\n" +
                 "\n" +
                 "        </div>\n" +
                 "\n" +
                 "        <div id=\"local\">\n" +
                 "          <div class=\"local_top\"></div>\n" +
                 "          <div id=\"blog_owner\">\n" +
                 "  <div id=\"blog_owner_logo\"><a href='http://masong1987.iteye.com'><img alt=\"masong1987的博客\" class=\"logo\" src=\"http://www.iteye.com/images/user-logo.gif?1324994303\" title=\"masong1987的博客: \" /></a></div>\n" +
                 "  <div id=\"blog_owner_name\">masong1987</div>\n" +
                 "</div>\n" +
                 "\n" +
                 "          <div id=\"blog_actions\">\n" +
                 "            <ul>\n" +
                 "              <li>浏览: 5401 次</li>\n" +
                 "              <li>性别: <img alt=\"Icon_minigender_1\" src=\"http://www.iteye.com/images/icon_minigender_1.gif?1324994303\" title=\"男\" /></li>\n" +
                 "              <li>来自: 北京</li>\n" +
                 "              <li><img src='/images/status/offline.gif'/></li>\n" +
                 "              \n" +
                 "                <li>\n" +
                 "                  <a href=\"http://my.iteye.com/messages/new?message%5Breceiver_name%5D=masong1987\" class=\"message\" title=\"发送站内短信\">发短消息</a>\n" +
                 "                  \n" +
                 "                    <a href=\"http://my.iteye.com/feed?subscription%5Bsubscribed_user_name%5D=masong1987\" class=\"subscription\" onclick=\"var f = document.createElement('form'); f.style.display = 'none'; this.parentNode.appendChild(f); f.method = 'POST'; f.action = this.href;var s = document.createElement('input'); s.setAttribute('type', 'hidden'); s.setAttribute('name', 'authenticity_token'); s.setAttribute('value', 'TDmn/IsWi1Aj4CXKfdMKZZzALz6jbRU/Biw0/QHnsVw='); f.appendChild(s);f.submit();return false;\">关注</a>\n" +
                 "                  \n" +
                 "                </li>\n" +
                 "              \n" +
                 "            </ul>\n" +
                 "          </div>\n" +
                 "          <div id=\"user_visits\" class=\"clearfix\">\n" +
                 "            <h5>最近访客 <span style='font-weight:normal;font-size:12px;padding-left:30px;'><a href=\"/blog/user_visits\">更多访客&gt;&gt;</a></span></h5>\n" +
                 "            \n" +
                 "              <div class=\"user_visit\">\n" +
                 "                <div class=\"logo\"><a href='http://flashsword20.iteye.com' target='_blank'><img alt=\"flashsword20的博客\" class=\"logo\" src=\"http://www.iteye.com/images/user-logo-thumb.gif?1324994303\" title=\"flashsword20的博客: \" /></a></div>\n" +
                 "                <div class=\"left\"><a href='http://flashsword20.iteye.com' target='_blank' title='flashsword20'>flashsword20</a></div>\n" +
                 "              </div>\n" +
                 "            \n" +
                 "              <div class=\"user_visit\">\n" +
                 "                <div class=\"logo\"><a href='http://dylinshi126.iteye.com' target='_blank'><img alt=\"dylinshi126的博客\" class=\"logo\" src=\"http://www.iteye.com/images/user-logo-thumb.gif?1324994303\" title=\"dylinshi126的博客: \" /></a></div>\n" +
                 "                <div class=\"left\"><a href='http://dylinshi126.iteye.com' target='_blank' title='dylinshi126'>dylinshi126</a></div>\n" +
                 "              </div>\n" +
                 "            \n" +
                 "              <div class=\"user_visit\">\n" +
                 "                <div class=\"logo\"><a href='http://machoo.iteye.com' target='_blank'><img alt=\"machoo的博客\" class=\"logo\" src=\"http://www.iteye.com/upload/logo/user/590501/f3e5a6de-fa04-3ca9-92bd-378230b128c8-thumb.jpg?1321544632\" title=\"machoo的博客: 虚拟机终结者\" /></a></div>\n" +
                 "                <div class=\"left\"><a href='http://machoo.iteye.com' target='_blank' title='machoo'>machoo</a></div>\n" +
                 "              </div>\n" +
                 "            \n" +
                 "              <div class=\"user_visit\">\n" +
                 "                <div class=\"logo\"><a href='http://arson.iteye.com' target='_blank'><img alt=\"arson的博客\" class=\"logo\" src=\"http://www.iteye.com/upload/logo/user/511499/91eafa67-ebbb-32d2-a1c4-fc1c169b5c66-thumb.jpg?1310020715\" title=\"arson的博客: \" /></a></div>\n" +
                 "                <div class=\"left\"><a href='http://arson.iteye.com' target='_blank' title='arson'>arson</a></div>\n" +
                 "              </div>\n" +
                 "            \n" +
                 "          </div>\n" +
                 "\n" +
                 "          \n" +
                 "\n" +
                 "                      <div id=\"blog_menu\">\n" +
                 "              <h5>文章分类</h5>\n" +
                 "              <ul>\n" +
                 "                <li><a href=\"/\">全部博客 (10)</a></li>\n" +
                 "                \n" +
                 "                  <li><a href=\"/category/180178\">java (1)</a></li>\n" +
                 "                \n" +
                 "                  <li><a href=\"/category/178810\">JavaScript (2)</a></li>\n" +
                 "                \n" +
                 "                  <li><a href=\"/category/181978\">SQLServer (1)</a></li>\n" +
                 "                \n" +
                 "                  <li><a href=\"/category/214133\">MySQL (1)</a></li>\n" +
                 "                \n" +
                 "                  <li><a href=\"/category/182324\">爬虫 (1)</a></li>\n" +
                 "                \n" +
                 "                  <li><a href=\"/category/195652\">ibatis (2)</a></li>\n" +
                 "                \n" +
                 "                  <li><a href=\"/category/195881\">Spring (1)</a></li>\n" +
                 "                \n" +
                 "                  <li><a href=\"/category/216639\">tomcat (0)</a></li>\n" +
                 "                \n" +
                 "                  <li><a href=\"/category/217595\">数据结构 (1)</a></li>\n" +
                 "                \n" +
                 "              </ul>\n" +
                 "            </div>\n" +
                 "            <div id='month_blogs'>\n" +
                 "              <h5>社区版块</h5>\n" +
                 "              <ul>\n" +
                 "                <li><a href=\"/blog/news\">我的资讯</a> (0)</li>\n" +
                 "                <li>\n" +
                 "                  <a href=\"/blog/post\">我的论坛</a> (0)\n" +
                 "                </li>\n" +
                 "                <li><a href=\"/blog/answered_problems\">我的问答</a> (0)</li>\n" +
                 "              </ul>\n" +
                 "            </div>\n" +
                 "            <div id=\"month_blogs\">\n" +
                 "              <h5>存档分类</h5>\n" +
                 "              <ul>\n" +
                 "                \n" +
                 "                  <li><a href=\"/blog/monthblog/2012-04\">2012-04</a> (2)</li>\n" +
                 "                \n" +
                 "                  <li><a href=\"/blog/monthblog/2012-03\">2012-03</a> (1)</li>\n" +
                 "                \n" +
                 "                  <li><a href=\"/blog/monthblog/2012-02\">2012-02</a> (1)</li>\n" +
                 "                \n" +
                 "                <li><a href=\"/blog/monthblog_more\">更多存档...</a></li>\n" +
                 "              </ul>\n" +
                 "            </div>\n" +
                 "            \n" +
                 "            \n" +
                 "\n" +
                 "            <div id=\"guest_books\">\n" +
                 "              <h5>最新评论</h5>\n" +
                 "              <ul>\n" +
                 "                \n" +
                 "                <li>\n" +
                 "                  <a href='http://marrymyy.iteye.com' target='_blank' title='marrymyy'>marrymyy</a>： \n" +
                 "                  太好了，刚遇到这个问题，有用<br />\n" +
                 "                  <a href=\"/blog/1189699#bc2305339\">尚未备份数据库 &quot;***&quot; 的日志尾部。如果该日志包含您不希望丢失的工作，请使用 BACKUP LOG WITH NORECOVERY 备份该日志。请使用 RE</a>\n" +
                 "                </li>\n" +
                 "                \n" +
                 "              </ul>\n" +
                 "            </div>\n" +
                 "\n" +
                 "            <div class=\"local_bottom\"></div>\n" +
                 "          \n" +
                 "        </div>\n" +
                 "      </div>\n" +
                 "\n" +
                 "      <div id=\"footer\" class=\"clearfix\">\n" +
                 "        <div id=\"copyright\">\n" +
                 "          <hr/>\n" +
                 "          声明：ITeye文章版权属于作者，受法律保护。没有作者书面许可不得转载。若作者同意转载，必须以超链接形式标明文章原始出处和作者。<br />\n" +
                 "          &copy; 2003-2012 ITeye.com.   All rights reserved.  [ 京ICP证110151号  京公网安备110105010620 ]\n" +
                 "        </div>\n" +
                 "      </div>\n" +
                 "    </div>\n" +
                 "    <script type=\"text/javascript\">\n" +
                 "  document.write(\"<img src='http://stat.iteye.com/?url=\"+ encodeURIComponent(document.location.href) + \"&referrer=\" + encodeURIComponent(document.referrer) + \"&user_id=635408' width='0' height='0' />\");\n" +
                 "</script>\n" +
                 "\n" +
                 "    \n" +
                 "    \n" +
                 "  </body>\n" +
                 "</html>\n";
                           String text2="<div>aaa</div>";
        XpathSelector xpathSelector = new XpathSelector("//div[@id='main']/div[@class='blog_main']/div[1][@class='blog_title']/h3/a");
        String select = xpathSelector.select(text);
        Assert.assertEquals("jsoup 解析页面商品信息",select);
    }

    @Test
    public void testOschina(){
        Html html1 = new Html(html);
        Assert.assertEquals("再次吐槽easyui",html1.x(".//*[@class='QTitle']/h1/a").toString());
    }

    @Test
    public void testOschinaBlog(){
        Html html1 = new Html(blogHtml);
        System.out.println(html1.sc());
    }

    @Test
    public void testHuxiuBlog(){
        Html html1 = new Html(huxiuHtml);
        System.out.println(html1.sc());
    }
}
