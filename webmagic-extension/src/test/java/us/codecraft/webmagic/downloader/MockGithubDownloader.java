package us.codecraft.webmagic.downloader;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.selector.PlainText;

/**
 * @author code4crafter@gmail.com
 */
public class MockGithubDownloader implements Downloader{

    private String html = "\n" +
            "\n" +
            "\n" +
            "\n" +
            "<!DOCTYPE html>\n" +
            "<html>\n" +
            "  <head prefix=\"og: http://ogp.me/ns# fb: http://ogp.me/ns/fb# githubog: http://ogp.me/ns/fb/githubog#\">\n" +
            "    <meta charset='utf-8'>\n" +
            "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
            "        <title>code4craft/webmagic · GitHub</title>\n" +
            "    <link rel=\"search\" type=\"application/opensearchdescription+xml\" href=\"/opensearch.xml\" title=\"GitHub\" />\n" +
            "    <link rel=\"fluid-icon\" href=\"https://github.com/fluidicon.png\" title=\"GitHub\" />\n" +
            "    <link rel=\"apple-touch-icon\" sizes=\"57x57\" href=\"/apple-touch-icon-114.png\" />\n" +
            "    <link rel=\"apple-touch-icon\" sizes=\"114x114\" href=\"/apple-touch-icon-114.png\" />\n" +
            "    <link rel=\"apple-touch-icon\" sizes=\"72x72\" href=\"/apple-touch-icon-144.png\" />\n" +
            "    <link rel=\"apple-touch-icon\" sizes=\"144x144\" href=\"/apple-touch-icon-144.png\" />\n" +
            "    <link rel=\"logo\" type=\"image/svg\" href=\"https://github-media-downloads.s3.amazonaws.com/github-logo.svg\" />\n" +
            "    <meta property=\"og:image\" content=\"https://github.global.ssl.fastly.net/images/modules/logos_page/Octocat.png\">\n" +
            "    <meta name=\"hostname\" content=\"github-fe114-cp1-prd.iad.github.net\">\n" +
            "    <meta name=\"ruby\" content=\"ruby 1.9.3p194-tcs-github-tcmalloc (2012-05-25, TCS patched 2012-05-27, GitHub v1.0.36) [x86_64-linux]\">\n" +
            "    <link rel=\"assets\" href=\"https://github.global.ssl.fastly.net/\">\n" +
            "    <link rel=\"xhr-socket\" href=\"/_sockets\" />\n" +
            "    \n" +
            "    \n" +
            "\n" +
            "\n" +
            "    <meta name=\"msapplication-TileImage\" content=\"/windows-tile.png\" />\n" +
            "    <meta name=\"msapplication-TileColor\" content=\"#ffffff\" />\n" +
            "    <meta name=\"selected-link\" value=\"repo_source\" data-pjax-transient />\n" +
            "    <meta content=\"collector.githubapp.com\" name=\"octolytics-host\" /><meta content=\"github\" name=\"octolytics-app-id\" /><meta content=\"D2167A02:4E87:89497A:523FCC67\" name=\"octolytics-dimension-request_id\" />\n" +
            "    \n" +
            "\n" +
            "    \n" +
            "    \n" +
            "    <link rel=\"icon\" type=\"image/x-icon\" href=\"/favicon.ico\" />\n" +
            "\n" +
            "    <meta content=\"authenticity_token\" name=\"csrf-param\" />\n" +
            "<meta content=\"i4/tXwrpqoMtPPKJTN4eSSPnFfrSzZkuIkeP//SUW34=\" name=\"csrf-token\" />\n" +
            "\n" +
            "    <link href=\"https://github.global.ssl.fastly.net/assets/github-4d622651f87d0cfd8c33f1c020455121d2af0be0.css\" media=\"all\" rel=\"stylesheet\" type=\"text/css\" />\n" +
            "    <link href=\"https://github.global.ssl.fastly.net/assets/github2-2c867c2081830b4a942703b9d3d565bf90f6046d.css\" media=\"all\" rel=\"stylesheet\" type=\"text/css\" />\n" +
            "    \n" +
            "\n" +
            "    \n" +
            "\n" +
            "      <script src=\"https://github.global.ssl.fastly.net/assets/frameworks-8db79d6d3d61c3bdec72ede901c2b6dbd4a79dad.js\" type=\"text/javascript\"></script>\n" +
            "      <script src=\"https://github.global.ssl.fastly.net/assets/github-0053cb56d6961482e50d72f8e19dc915009ce6b7.js\" type=\"text/javascript\"></script>\n" +
            "      \n" +
            "      <meta http-equiv=\"x-pjax-version\" content=\"b5479068af2118811ca4dcd8c0c29e66\">\n" +
            "\n" +
            "        <meta property=\"og:title\" content=\"webmagic\"/>\n" +
            "  <meta property=\"og:type\" content=\"githubog:gitrepository\"/>\n" +
            "  <meta property=\"og:url\" content=\"https://github.com/code4craft/webmagic\"/>\n" +
            "  <meta property=\"og:image\" content=\"https://github.global.ssl.fastly.net/images/gravatars/gravatar-user-420.png\"/>\n" +
            "  <meta property=\"og:site_name\" content=\"GitHub\"/>\n" +
            "  <meta property=\"og:description\" content=\"webmagic - A scalable web crawler framework.\"/>\n" +
            "\n" +
            "  <meta name=\"description\" content=\"webmagic - A scalable web crawler framework.\" />\n" +
            "\n" +
            "  <meta content=\"1351884\" name=\"octolytics-dimension-user_id\" /><meta content=\"code4craft\" name=\"octolytics-dimension-user_login\" /><meta content=\"9623064\" name=\"octolytics-dimension-repository_id\" /><meta content=\"code4craft/webmagic\" name=\"octolytics-dimension-repository_nwo\" /><meta content=\"true\" name=\"octolytics-dimension-repository_public\" /><meta content=\"false\" name=\"octolytics-dimension-repository_is_fork\" /><meta content=\"9623064\" name=\"octolytics-dimension-repository_network_root_id\" /><meta content=\"code4craft/webmagic\" name=\"octolytics-dimension-repository_network_root_nwo\" />\n" +
            "  <link href=\"https://github.com/code4craft/webmagic/commits/master.atom\" rel=\"alternate\" title=\"Recent Commits to webmagic:master\" type=\"application/atom+xml\" />\n" +
            "\n" +
            "  </head>\n" +
            "\n" +
            "\n" +
            "  <body class=\"logged_out  env-production macintosh vis-public\">\n" +
            "    <div class=\"wrapper\">\n" +
            "      \n" +
            "      \n" +
            "      \n" +
            "\n" +
            "\n" +
            "      \n" +
            "      <div class=\"header header-logged-out\">\n" +
            "  <div class=\"container clearfix\">\n" +
            "\n" +
            "    <a class=\"header-logo-wordmark\" href=\"https://github.com/\">\n" +
            "      <span class=\"mega-octicon octicon-logo-github\"></span>\n" +
            "    </a>\n" +
            "\n" +
            "    <div class=\"header-actions\">\n" +
            "        <a class=\"button primary\" href=\"/signup\">Sign up</a>\n" +
            "      <a class=\"button signin\" href=\"/login?return_to=%2Fcode4craft%2Fwebmagic\">Sign in</a>\n" +
            "    </div>\n" +
            "\n" +
            "    <div class=\"command-bar js-command-bar  in-repository\">\n" +
            "\n" +
            "      <ul class=\"top-nav\">\n" +
            "          <li class=\"explore\"><a href=\"/explore\">Explore</a></li>\n" +
            "        <li class=\"features\"><a href=\"/features\">Features</a></li>\n" +
            "          <li class=\"enterprise\"><a href=\"https://enterprise.github.com/\">Enterprise</a></li>\n" +
            "          <li class=\"blog\"><a href=\"/blog\">Blog</a></li>\n" +
            "      </ul>\n" +
            "        <form accept-charset=\"UTF-8\" action=\"/search\" class=\"command-bar-form\" id=\"top_search_form\" method=\"get\">\n" +
            "\n" +
            "<input type=\"text\" data-hotkey=\"/ s\" name=\"q\" id=\"js-command-bar-field\" placeholder=\"Search or type a command\" tabindex=\"1\" autocapitalize=\"off\"\n" +
            "    \n" +
            "    \n" +
            "      data-repo=\"code4craft/webmagic\"\n" +
            "      data-branch=\"master\"\n" +
            "      data-sha=\"c5ed5916d20b96963d906dde8bccc3627568e486\"\n" +
            "  >\n" +
            "\n" +
            "    <input type=\"hidden\" name=\"nwo\" value=\"code4craft/webmagic\" />\n" +
            "\n" +
            "    <div class=\"select-menu js-menu-container js-select-menu search-context-select-menu\">\n" +
            "      <span class=\"minibutton select-menu-button js-menu-target\">\n" +
            "        <span class=\"js-select-button\">This repository</span>\n" +
            "      </span>\n" +
            "\n" +
            "      <div class=\"select-menu-modal-holder js-menu-content js-navigation-container\">\n" +
            "        <div class=\"select-menu-modal\">\n" +
            "\n" +
            "          <div class=\"select-menu-item js-navigation-item js-this-repository-navigation-item selected\">\n" +
            "            <span class=\"select-menu-item-icon octicon octicon-check\"></span>\n" +
            "            <input type=\"radio\" class=\"js-search-this-repository\" name=\"search_target\" value=\"repository\" checked=\"checked\" />\n" +
            "            <div class=\"select-menu-item-text js-select-button-text\">This repository</div>\n" +
            "          </div> <!-- /.select-menu-item -->\n" +
            "\n" +
            "          <div class=\"select-menu-item js-navigation-item js-all-repositories-navigation-item\">\n" +
            "            <span class=\"select-menu-item-icon octicon octicon-check\"></span>\n" +
            "            <input type=\"radio\" name=\"search_target\" value=\"global\" />\n" +
            "            <div class=\"select-menu-item-text js-select-button-text\">All repositories</div>\n" +
            "          </div> <!-- /.select-menu-item -->\n" +
            "\n" +
            "        </div>\n" +
            "      </div>\n" +
            "    </div>\n" +
            "\n" +
            "  <span class=\"octicon help tooltipped downwards\" title=\"Show command bar help\">\n" +
            "    <span class=\"octicon octicon-question\"></span>\n" +
            "  </span>\n" +
            "\n" +
            "\n" +
            "  <input type=\"hidden\" name=\"ref\" value=\"cmdform\">\n" +
            "\n" +
            "</form>\n" +
            "    </div>\n" +
            "\n" +
            "  </div>\n" +
            "</div>\n" +
            "\n" +
            "\n" +
            "      \n" +
            "\n" +
            "\n" +
            "          <div class=\"site\" itemscope itemtype=\"http://schema.org/WebPage\">\n" +
            "    \n" +
            "    <div class=\"pagehead repohead instapaper_ignore readability-menu\">\n" +
            "      <div class=\"container\">\n" +
            "        \n" +
            "\n" +
            "<ul class=\"pagehead-actions\">\n" +
            "\n" +
            "\n" +
            "  <li>\n" +
            "  <a href=\"/login?return_to=%2Fcode4craft%2Fwebmagic\"\n" +
            "  class=\"minibutton with-count js-toggler-target star-button entice tooltipped upwards\"\n" +
            "  title=\"You must be signed in to use this feature\" rel=\"nofollow\">\n" +
            "  <span class=\"octicon octicon-star\"></span>Star\n" +
            "</a>\n" +
            "<a class=\"social-count js-social-count\" href=\"/code4craft/webmagic/stargazers\">\n" +
            "  86\n" +
            "</a>\n" +
            "\n" +
            "  </li>\n" +
            "\n" +
            "    <li>\n" +
            "      <a href=\"/login?return_to=%2Fcode4craft%2Fwebmagic\"\n" +
            "        class=\"minibutton with-count js-toggler-target fork-button entice tooltipped upwards\"\n" +
            "        title=\"You must be signed in to fork a repository\" rel=\"nofollow\">\n" +
            "        <span class=\"octicon octicon-git-branch\"></span>Fork\n" +
            "      </a>\n" +
            "      <a href=\"/code4craft/webmagic/network\" class=\"social-count\">\n" +
            "        70\n" +
            "      </a>\n" +
            "    </li>\n" +
            "</ul>\n" +
            "\n" +
            "        <h1 itemscope itemtype=\"http://data-vocabulary.org/Breadcrumb\" class=\"entry-title public\">\n" +
            "          <span class=\"repo-label\"><span>public</span></span>\n" +
            "          <span class=\"mega-octicon octicon-repo\"></span>\n" +
            "          <span class=\"author\">\n" +
            "            <a href=\"/code4craft\" class=\"url fn\" itemprop=\"url\" rel=\"author\"><span itemprop=\"title\">code4craft</span></a></span\n" +
            "          ><span class=\"repohead-name-divider\">/</span><strong\n" +
            "          ><a href=\"/code4craft/webmagic\" class=\"js-current-repository js-repo-home-link\">webmagic</a></strong>\n" +
            "\n" +
            "          <span class=\"page-context-loader\">\n" +
            "            <img alt=\"Octocat-spinner-32\" height=\"16\" src=\"https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif\" width=\"16\" />\n" +
            "          </span>\n" +
            "\n" +
            "        </h1>\n" +
            "      </div><!-- /.container -->\n" +
            "    </div><!-- /.repohead -->\n" +
            "\n" +
            "    <div class=\"container\">\n" +
            "\n" +
            "      <div class=\"repository-with-sidebar repo-container with-full-navigation\">\n" +
            "\n" +
            "        <div class=\"repository-sidebar\">\n" +
            "            \n" +
            "\n" +
            "<div class=\"repo-nav repo-nav-full js-repository-container-pjax js-octicon-loaders\">\n" +
            "  <div class=\"repo-nav-contents\">\n" +
            "    <ul class=\"repo-menu\">\n" +
            "      <li class=\"tooltipped leftwards\" title=\"Code\">\n" +
            "        <a href=\"/code4craft/webmagic\" aria-label=\"Code\" class=\"js-selected-navigation-item selected\" data-gotokey=\"c\" data-pjax=\"true\" data-selected-links=\"repo_source repo_downloads repo_commits repo_tags repo_branches /code4craft/webmagic\">\n" +
            "          <span class=\"octicon octicon-code\"></span> <span class=\"full-word\">Code</span>\n" +
            "          <img alt=\"Octocat-spinner-32\" class=\"mini-loader\" height=\"16\" src=\"https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif\" width=\"16\" />\n" +
            "</a>      </li>\n" +
            "\n" +
            "        <li class=\"tooltipped leftwards\" title=\"Issues\">\n" +
            "          <a href=\"/code4craft/webmagic/issues\" aria-label=\"Issues\" class=\"js-selected-navigation-item js-disable-pjax\" data-gotokey=\"i\" data-selected-links=\"repo_issues /code4craft/webmagic/issues\">\n" +
            "            <span class=\"octicon octicon-issue-opened\"></span> <span class=\"full-word\">Issues</span>\n" +
            "            <span class='counter'>2</span>\n" +
            "            <img alt=\"Octocat-spinner-32\" class=\"mini-loader\" height=\"16\" src=\"https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif\" width=\"16\" />\n" +
            "</a>        </li>\n" +
            "\n" +
            "      <li class=\"tooltipped leftwards\" title=\"Pull Requests\"><a href=\"/code4craft/webmagic/pulls\" aria-label=\"Pull Requests\" class=\"js-selected-navigation-item js-disable-pjax\" data-gotokey=\"p\" data-selected-links=\"repo_pulls /code4craft/webmagic/pulls\">\n" +
            "            <span class=\"octicon octicon-git-pull-request\"></span> <span class=\"full-word\">Pull Requests</span>\n" +
            "            <span class='counter'>0</span>\n" +
            "            <img alt=\"Octocat-spinner-32\" class=\"mini-loader\" height=\"16\" src=\"https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif\" width=\"16\" />\n" +
            "</a>      </li>\n" +
            "\n" +
            "\n" +
            "        <li class=\"tooltipped leftwards\" title=\"Wiki\">\n" +
            "          <a href=\"/code4craft/webmagic/wiki\" aria-label=\"Wiki\" class=\"js-selected-navigation-item \" data-pjax=\"true\" data-selected-links=\"repo_wiki /code4craft/webmagic/wiki\">\n" +
            "            <span class=\"octicon octicon-book\"></span> <span class=\"full-word\">Wiki</span>\n" +
            "            <img alt=\"Octocat-spinner-32\" class=\"mini-loader\" height=\"16\" src=\"https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif\" width=\"16\" />\n" +
            "</a>        </li>\n" +
            "    </ul>\n" +
            "    <div class=\"repo-menu-separator\"></div>\n" +
            "    <ul class=\"repo-menu\">\n" +
            "\n" +
            "      <li class=\"tooltipped leftwards\" title=\"Pulse\">\n" +
            "        <a href=\"/code4craft/webmagic/pulse\" aria-label=\"Pulse\" class=\"js-selected-navigation-item \" data-pjax=\"true\" data-selected-links=\"pulse /code4craft/webmagic/pulse\">\n" +
            "          <span class=\"octicon octicon-pulse\"></span> <span class=\"full-word\">Pulse</span>\n" +
            "          <img alt=\"Octocat-spinner-32\" class=\"mini-loader\" height=\"16\" src=\"https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif\" width=\"16\" />\n" +
            "</a>      </li>\n" +
            "\n" +
            "      <li class=\"tooltipped leftwards\" title=\"Graphs\">\n" +
            "        <a href=\"/code4craft/webmagic/graphs\" aria-label=\"Graphs\" class=\"js-selected-navigation-item \" data-pjax=\"true\" data-selected-links=\"repo_graphs repo_contributors /code4craft/webmagic/graphs\">\n" +
            "          <span class=\"octicon octicon-graph\"></span> <span class=\"full-word\">Graphs</span>\n" +
            "          <img alt=\"Octocat-spinner-32\" class=\"mini-loader\" height=\"16\" src=\"https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif\" width=\"16\" />\n" +
            "</a>      </li>\n" +
            "\n" +
            "      <li class=\"tooltipped leftwards\" title=\"Network\">\n" +
            "        <a href=\"/code4craft/webmagic/network\" aria-label=\"Network\" class=\"js-selected-navigation-item js-disable-pjax\" data-selected-links=\"repo_network /code4craft/webmagic/network\">\n" +
            "          <span class=\"octicon octicon-git-branch\"></span> <span class=\"full-word\">Network</span>\n" +
            "          <img alt=\"Octocat-spinner-32\" class=\"mini-loader\" height=\"16\" src=\"https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif\" width=\"16\" />\n" +
            "</a>      </li>\n" +
            "    </ul>\n" +
            "\n" +
            "\n" +
            "  </div>\n" +
            "</div>\n" +
            "\n" +
            "            <div class=\"only-with-full-nav\">\n" +
            "              \n" +
            "\n" +
            "  \n" +
            "\n" +
            "<div class=\"clone-url open\"\n" +
            "  data-protocol-type=\"http\"\n" +
            "  data-url=\"/users/set_protocol?protocol_selector=http&amp;protocol_type=clone\">\n" +
            "  <h3><strong>HTTPS</strong> clone URL</h3>\n" +
            "  <div class=\"clone-url-box\">\n" +
            "    <input type=\"text\" class=\"clone js-url-field\"\n" +
            "           value=\"https://github.com/code4craft/webmagic.git\" readonly=\"readonly\">\n" +
            "\n" +
            "    <span class=\"js-zeroclipboard url-box-clippy minibutton zeroclipboard-button\" data-clipboard-text=\"https://github.com/code4craft/webmagic.git\" data-copied-hint=\"copied!\" title=\"copy to clipboard\"><span class=\"octicon octicon-clippy\"></span></span>\n" +
            "  </div>\n" +
            "</div>\n" +
            "\n" +
            "  \n" +
            "\n" +
            "<div class=\"clone-url \"\n" +
            "  data-protocol-type=\"subversion\"\n" +
            "  data-url=\"/users/set_protocol?protocol_selector=subversion&amp;protocol_type=clone\">\n" +
            "  <h3><strong>Subversion</strong> checkout URL</h3>\n" +
            "  <div class=\"clone-url-box\">\n" +
            "    <input type=\"text\" class=\"clone js-url-field\"\n" +
            "           value=\"https://github.com/code4craft/webmagic\" readonly=\"readonly\">\n" +
            "\n" +
            "    <span class=\"js-zeroclipboard url-box-clippy minibutton zeroclipboard-button\" data-clipboard-text=\"https://github.com/code4craft/webmagic\" data-copied-hint=\"copied!\" title=\"copy to clipboard\"><span class=\"octicon octicon-clippy\"></span></span>\n" +
            "  </div>\n" +
            "</div>\n" +
            "\n" +
            "\n" +
            "<p class=\"clone-options\">You can clone with\n" +
            "      <a href=\"#\" class=\"js-clone-selector\" data-protocol=\"http\">HTTPS</a>,\n" +
            "      or <a href=\"#\" class=\"js-clone-selector\" data-protocol=\"subversion\">Subversion</a>.\n" +
            "  <span class=\"octicon help tooltipped upwards\" title=\"Get help on which URL is right for you.\">\n" +
            "    <a href=\"https://help.github.com/articles/which-remote-url-should-i-use\">\n" +
            "    <span class=\"octicon octicon-question\"></span>\n" +
            "    </a>\n" +
            "  </span>\n" +
            "</p>\n" +
            "\n" +
            "  <a href=\"http://mac.github.com\" class=\"minibutton sidebar-button\">\n" +
            "    <span class=\"octicon octicon-device-desktop\"></span>\n" +
            "    Clone in Desktop\n" +
            "  </a>\n" +
            "\n" +
            "\n" +
            "                <a href=\"/code4craft/webmagic/archive/master.zip\"\n" +
            "                   class=\"minibutton sidebar-button\"\n" +
            "                   title=\"Download this repository as a zip file\"\n" +
            "                   rel=\"nofollow\">\n" +
            "                  <span class=\"octicon octicon-cloud-download\"></span>\n" +
            "                  Download ZIP\n" +
            "                </a>\n" +
            "            </div>\n" +
            "        </div><!-- /.repository-sidebar -->\n" +
            "\n" +
            "        <div id=\"js-repo-pjax-container\" class=\"repository-content context-loader-container\" data-pjax-container>\n" +
            "          \n" +
            "<div class=\"js-info-carrier\" data-show-full-navigation=\"yes\"></div>\n" +
            "\n" +
            "<div class=\"repository-meta js-details-container \">\n" +
            "    <div class=\"repository-description js-details-show\">\n" +
            "      <p>A scalable web crawler framework.</p>\n" +
            "    </div>\n" +
            "\n" +
            "\n" +
            "\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"capped-box overall-summary \">\n" +
            "\n" +
            "  <div class=\"stats-switcher-viewport js-stats-switcher-viewport\">\n" +
            "\n" +
            "    <ul class=\"numbers-summary\">\n" +
            "      <li class=\"commits\">\n" +
            "        <a data-pjax href=\"/code4craft/webmagic/commits/master\">\n" +
            "          <span class=\"num\">\n" +
            "            <span class=\"octicon octicon-history\"></span>\n" +
            "            311\n" +
            "          </span>\n" +
            "          commits\n" +
            "        </a>\n" +
            "      </li>\n" +
            "      <li>\n" +
            "        <a data-pjax href=\"/code4craft/webmagic/branches\">\n" +
            "          <span class=\"num\">\n" +
            "            <span class=\"octicon octicon-git-branch\"></span>\n" +
            "            4\n" +
            "          </span>\n" +
            "          branches\n" +
            "        </a>\n" +
            "      </li>\n" +
            "\n" +
            "      <li>\n" +
            "        <a data-pjax href=\"/code4craft/webmagic/releases\">\n" +
            "          <span class=\"num\">\n" +
            "            <span class=\"octicon octicon-tag\"></span>\n" +
            "            5\n" +
            "          </span>\n" +
            "          releases\n" +
            "        </a>\n" +
            "      </li>\n" +
            "\n" +
            "      <li>\n" +
            "        <a href=\"/code4craft/webmagic/contributors\">\n" +
            "          <span class=\"num\">\n" +
            "            <span class=\"octicon octicon-organization\"></span>\n" +
            "            3\n" +
            "          </span>\n" +
            "          contributors\n" +
            "        </a>\n" +
            "      </li>\n" +
            "    </ul>\n" +
            "\n" +
            "      <div class=\"repository-lang-stats\">\n" +
            "        <ol class=\"repository-lang-stats-numbers\">\n" +
            "          <li>\n" +
            "              <a href=\"/code4craft/webmagic/search?l=java\">\n" +
            "                <span class=\"color-block language-color\" style=\"background-color:#b07219;\"></span>\n" +
            "                <span class=\"lang\">Java</span>\n" +
            "                <span class=\"percent\">100%</span>\n" +
            "              </a>\n" +
            "          </li>\n" +
            "        </ol>\n" +
            "      </div>\n" +
            "  </div>\n" +
            "\n" +
            "</div>\n" +
            "\n" +
            "  <a href=\"#\"\n" +
            "     class=\"repository-lang-stats-graph js-toggle-lang-stats tooltipped downwards\"\n" +
            "     title=\"Show language statistics\"\n" +
            "     style=\"background-color:#b07219\">\n" +
            "  <span class=\"language-color\" style=\"width:100%; background-color:#b07219;\" itemprop=\"keywords\">Java</span>\n" +
            "  </a>\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "<div class=\"file-navigation in-mid-page\">\n" +
            "    <a href=\"/code4craft/webmagic/compare\" aria-label=\"Compare, review, create a pull request\" class=\"minibutton compact primary tooltipped downwards\" title=\"Compare &amp; review\" data-pjax>\n" +
            "      <span class=\"octicon octicon-git-compare\"></span>\n" +
            "    </a>\n" +
            "\n" +
            "  \n" +
            "\n" +
            "\n" +
            "<div class=\"select-menu js-menu-container js-select-menu\" >\n" +
            "  <span class=\"minibutton select-menu-button js-menu-target\" data-hotkey=\"w\"\n" +
            "    data-master-branch=\"master\"\n" +
            "    data-ref=\"master\"\n" +
            "    role=\"button\" aria-label=\"Switch branches or tags\" tabindex=\"0\">\n" +
            "    <span class=\"octicon octicon-git-branch\"></span>\n" +
            "    <i>branch:</i>\n" +
            "    <span class=\"js-select-button\">master</span>\n" +
            "  </span>\n" +
            "\n" +
            "  <div class=\"select-menu-modal-holder js-menu-content js-navigation-container\" data-pjax>\n" +
            "\n" +
            "    <div class=\"select-menu-modal\">\n" +
            "      <div class=\"select-menu-header\">\n" +
            "        <span class=\"select-menu-title\">Switch branches/tags</span>\n" +
            "        <span class=\"octicon octicon-remove-close js-menu-close\"></span>\n" +
            "      </div> <!-- /.select-menu-header -->\n" +
            "\n" +
            "      <div class=\"select-menu-filters\">\n" +
            "        <div class=\"select-menu-text-filter\">\n" +
            "          <input type=\"text\" aria-label=\"Filter branches/tags\" id=\"context-commitish-filter-field\" class=\"js-filterable-field js-navigation-enable\" placeholder=\"Filter branches/tags\">\n" +
            "        </div>\n" +
            "        <div class=\"select-menu-tabs\">\n" +
            "          <ul>\n" +
            "            <li class=\"select-menu-tab\">\n" +
            "              <a href=\"#\" data-tab-filter=\"branches\" class=\"js-select-menu-tab\">Branches</a>\n" +
            "            </li>\n" +
            "            <li class=\"select-menu-tab\">\n" +
            "              <a href=\"#\" data-tab-filter=\"tags\" class=\"js-select-menu-tab\">Tags</a>\n" +
            "            </li>\n" +
            "          </ul>\n" +
            "        </div><!-- /.select-menu-tabs -->\n" +
            "      </div><!-- /.select-menu-filters -->\n" +
            "\n" +
            "      <div class=\"select-menu-list select-menu-tab-bucket js-select-menu-tab-bucket\" data-tab-filter=\"branches\">\n" +
            "\n" +
            "        <div data-filterable-for=\"context-commitish-filter-field\" data-filterable-type=\"substring\">\n" +
            "\n" +
            "\n" +
            "            <div class=\"select-menu-item js-navigation-item \">\n" +
            "              <span class=\"select-menu-item-icon octicon octicon-check\"></span>\n" +
            "              <a href=\"/code4craft/webmagic/tree/en-webmagic\" class=\"js-navigation-open select-menu-item-text js-select-button-text css-truncate-target\" data-name=\"en-webmagic\" data-skip-pjax=\"true\" rel=\"nofollow\" title=\"en-webmagic\">en-webmagic</a>\n" +
            "            </div> <!-- /.select-menu-item -->\n" +
            "            <div class=\"select-menu-item js-navigation-item \">\n" +
            "              <span class=\"select-menu-item-icon octicon octicon-check\"></span>\n" +
            "              <a href=\"/code4craft/webmagic/tree/gh-pages\" class=\"js-navigation-open select-menu-item-text js-select-button-text css-truncate-target\" data-name=\"gh-pages\" data-skip-pjax=\"true\" rel=\"nofollow\" title=\"gh-pages\">gh-pages</a>\n" +
            "            </div> <!-- /.select-menu-item -->\n" +
            "            <div class=\"select-menu-item js-navigation-item selected\">\n" +
            "              <span class=\"select-menu-item-icon octicon octicon-check\"></span>\n" +
            "              <a href=\"/code4craft/webmagic/tree/master\" class=\"js-navigation-open select-menu-item-text js-select-button-text css-truncate-target\" data-name=\"master\" data-skip-pjax=\"true\" rel=\"nofollow\" title=\"master\">master</a>\n" +
            "            </div> <!-- /.select-menu-item -->\n" +
            "            <div class=\"select-menu-item js-navigation-item \">\n" +
            "              <span class=\"select-menu-item-icon octicon octicon-check\"></span>\n" +
            "              <a href=\"/code4craft/webmagic/tree/xsoup\" class=\"js-navigation-open select-menu-item-text js-select-button-text css-truncate-target\" data-name=\"xsoup\" data-skip-pjax=\"true\" rel=\"nofollow\" title=\"xsoup\">xsoup</a>\n" +
            "            </div> <!-- /.select-menu-item -->\n" +
            "        </div>\n" +
            "\n" +
            "          <div class=\"select-menu-no-results\">Nothing to show</div>\n" +
            "      </div> <!-- /.select-menu-list -->\n" +
            "\n" +
            "      <div class=\"select-menu-list select-menu-tab-bucket js-select-menu-tab-bucket\" data-tab-filter=\"tags\">\n" +
            "        <div data-filterable-for=\"context-commitish-filter-field\" data-filterable-type=\"substring\">\n" +
            "\n" +
            "\n" +
            "            <div class=\"select-menu-item js-navigation-item \">\n" +
            "              <span class=\"select-menu-item-icon octicon octicon-check\"></span>\n" +
            "              <a href=\"/code4craft/webmagic/tree/webmagic-parent-0.3.1\" class=\"js-navigation-open select-menu-item-text js-select-button-text css-truncate-target\" data-name=\"webmagic-parent-0.3.1\" data-skip-pjax=\"true\" rel=\"nofollow\" title=\"webmagic-parent-0.3.1\">webmagic-parent-0.3.1</a>\n" +
            "            </div> <!-- /.select-menu-item -->\n" +
            "            <div class=\"select-menu-item js-navigation-item \">\n" +
            "              <span class=\"select-menu-item-icon octicon octicon-check\"></span>\n" +
            "              <a href=\"/code4craft/webmagic/tree/webmagic-parent-0.2.1\" class=\"js-navigation-open select-menu-item-text js-select-button-text css-truncate-target\" data-name=\"webmagic-parent-0.2.1\" data-skip-pjax=\"true\" rel=\"nofollow\" title=\"webmagic-parent-0.2.1\">webmagic-parent-0.2.1</a>\n" +
            "            </div> <!-- /.select-menu-item -->\n" +
            "            <div class=\"select-menu-item js-navigation-item \">\n" +
            "              <span class=\"select-menu-item-icon octicon octicon-check\"></span>\n" +
            "              <a href=\"/code4craft/webmagic/tree/webmagic-0.3.0\" class=\"js-navigation-open select-menu-item-text js-select-button-text css-truncate-target\" data-name=\"webmagic-0.3.0\" data-skip-pjax=\"true\" rel=\"nofollow\" title=\"webmagic-0.3.0\">webmagic-0.3.0</a>\n" +
            "            </div> <!-- /.select-menu-item -->\n" +
            "            <div class=\"select-menu-item js-navigation-item \">\n" +
            "              <span class=\"select-menu-item-icon octicon octicon-check\"></span>\n" +
            "              <a href=\"/code4craft/webmagic/tree/version-0.2.0\" class=\"js-navigation-open select-menu-item-text js-select-button-text css-truncate-target\" data-name=\"version-0.2.0\" data-skip-pjax=\"true\" rel=\"nofollow\" title=\"version-0.2.0\">version-0.2.0</a>\n" +
            "            </div> <!-- /.select-menu-item -->\n" +
            "            <div class=\"select-menu-item js-navigation-item \">\n" +
            "              <span class=\"select-menu-item-icon octicon octicon-check\"></span>\n" +
            "              <a href=\"/code4craft/webmagic/tree/version-0.1.0\" class=\"js-navigation-open select-menu-item-text js-select-button-text css-truncate-target\" data-name=\"version-0.1.0\" data-skip-pjax=\"true\" rel=\"nofollow\" title=\"version-0.1.0\">version-0.1.0</a>\n" +
            "            </div> <!-- /.select-menu-item -->\n" +
            "        </div>\n" +
            "\n" +
            "        <div class=\"select-menu-no-results\">Nothing to show</div>\n" +
            "      </div> <!-- /.select-menu-list -->\n" +
            "\n" +
            "    </div> <!-- /.select-menu-modal -->\n" +
            "  </div> <!-- /.select-menu-modal-holder -->\n" +
            "</div> <!-- /.select-menu -->\n" +
            "\n" +
            "\n" +
            "  <div class=\"breadcrumb\"><span class='repo-root js-repo-root'><span itemscope=\"\" itemtype=\"http://data-vocabulary.org/Breadcrumb\"><a href=\"/code4craft/webmagic\" data-branch=\"master\" data-direction=\"back\" data-pjax=\"true\" itemscope=\"url\"><span itemprop=\"title\">webmagic</span></a></span></span><span class=\"separator\"> / </span><form action=\"/login?return_to=%2Fcode4craft%2Fwebmagic\" class=\"js-new-blob-form tooltipped rightwards new-file-link\" method=\"post\" title=\"Sign in to make or propose changes\"><span aria-label=\"Sign in to make or propose changes\" class=\"js-new-blob-submit octicon octicon-file-add\" data-test-id=\"create-new-git-file\" role=\"button\"></span></form></div>\n" +
            "</div>\n" +
            "\n" +
            "\n" +
            "\n" +
            "<a href=\"/code4craft/webmagic/find/master\"\n" +
            "  data-hotkey=\"t\" class=\"js-show-file-finder\" style=\"display:none\" data-pjax>Show File Finder</a>\n" +
            "<div class=\"bubble files-bubble\">\n" +
            "  <table class=\"files\" data-pjax>\n" +
            "    <thead>\n" +
            "\n" +
            "        <div class=\"commit commit-loader commit-tease js-details-container js-deferred-content\" data-url=\"/code4craft/webmagic/tree-commit/master\">\n" +
            "          <p class=\"commit-title blank\">\n" +
            "            Fetching latest commit…\n" +
            "          </p>\n" +
            "          <div class=\"commit-meta\">\n" +
            "            <p class=\"loader-loading\"><img alt=\"Octocat-spinner-32-eaf2f5\" height=\"16\" src=\"https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32-EAF2F5.gif\" width=\"16\" /></p>\n" +
            "            <p class=\"loader-error\">Cannot retrieve the latest commit at this time</p>\n" +
            "          </div>\n" +
            "        </div>\n" +
            "    </thead>\n" +
            "\n" +
            "    \n" +
            "<tbody class=\"\"\n" +
            "  data-url=\"/code4craft/webmagic/file-list/master\">\n" +
            "    <tr class=\"alt\">\n" +
            "      <td class=\"icon\">\n" +
            "        <span class=\"octicon octicon-file-directory\"></span>\n" +
            "        <img alt=\"Octocat-spinner-32\" class=\"spinner\" height=\"16\" src=\"https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif\" width=\"16\" />\n" +
            "      </td>\n" +
            "      <td class=\"content\">\n" +
            "        <span class=\"css-truncate css-truncate-target\"><a href=\"/code4craft/webmagic/tree/master/en_docs\" class=\"js-directory-link\" id=\"025516923597c2d7f987828ad6657c14-6f7a9bdb73f0e5e26cbde50c2fbf780c2a4ad4b2\" title=\"en_docs\">en_docs</a></span>\n" +
            "      </td>\n" +
            "      <td class=\"message\">\n" +
            "        <span class=\"css-truncate css-truncate-target\"><a href=\"/code4craft/webmagic/commit/dcc5d790e4bcbebaa2a1168fd5f9919936bcb831\" class=\"message\" data-pjax=\"true\" title=\"update readme\">update readme</a></span>\n" +
            "      </td>\n" +
            "      <td class=\"age\"><span class=\"css-truncate css-truncate-target\"><time class=\"js-relative-date\" datetime=\"2013-08-17T16:16:33-07:00\" title=\"2013-08-17 16:16:33\">August 17, 2013</time></span></td>\n" +
            "    </tr>\n" +
            "    <tr class=\"\">\n" +
            "      <td class=\"icon\">\n" +
            "        <span class=\"octicon octicon-file-directory\"></span>\n" +
            "        <img alt=\"Octocat-spinner-32\" class=\"spinner\" height=\"16\" src=\"https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif\" width=\"16\" />\n" +
            "      </td>\n" +
            "      <td class=\"content\">\n" +
            "        <span class=\"css-truncate css-truncate-target\"><a href=\"/code4craft/webmagic/tree/master/webmagic-core\" class=\"js-directory-link\" id=\"39809e13bc65c3873f79570b81852d62-e96da9edd9329cf8448fed332294dd4575549495\" title=\"webmagic-core\">webmagic-core</a></span>\n" +
            "      </td>\n" +
            "      <td class=\"message\">\n" +
            "        <span class=\"css-truncate css-truncate-target\"><a href=\"/code4craft/webmagic/commit/b131878123cb90f6123255bbd21e71bc70a480b7\" class=\"message\" data-pjax=\"true\" title=\"add example\">add example</a></span>\n" +
            "      </td>\n" +
            "      <td class=\"age\"><span class=\"css-truncate css-truncate-target\"><time class=\"js-relative-date\" datetime=\"2013-09-22T22:01:28-07:00\" title=\"2013-09-22 22:01:28\">September 22, 2013</time></span></td>\n" +
            "    </tr>\n" +
            "    <tr class=\"alt\">\n" +
            "      <td class=\"icon\">\n" +
            "        <span class=\"octicon octicon-file-directory\"></span>\n" +
            "        <img alt=\"Octocat-spinner-32\" class=\"spinner\" height=\"16\" src=\"https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif\" width=\"16\" />\n" +
            "      </td>\n" +
            "      <td class=\"content\">\n" +
            "        <span class=\"css-truncate css-truncate-target\"><a href=\"/code4craft/webmagic/tree/master/webmagic-extension\" class=\"js-directory-link\" id=\"dc82c79bcb262e1942088502bb426876-6f4453065d5b11429731e2a3e71e10f944da2180\" title=\"webmagic-extension\">webmagic-extension</a></span>\n" +
            "      </td>\n" +
            "      <td class=\"message\">\n" +
            "        <span class=\"css-truncate css-truncate-target\"><a href=\"/code4craft/webmagic/commit/b131878123cb90f6123255bbd21e71bc70a480b7\" class=\"message\" data-pjax=\"true\" title=\"add example\">add example</a></span>\n" +
            "      </td>\n" +
            "      <td class=\"age\"><span class=\"css-truncate css-truncate-target\"><time class=\"js-relative-date\" datetime=\"2013-09-22T22:01:28-07:00\" title=\"2013-09-22 22:01:28\">September 22, 2013</time></span></td>\n" +
            "    </tr>\n" +
            "    <tr class=\"\">\n" +
            "      <td class=\"icon\">\n" +
            "        <span class=\"octicon octicon-file-directory\"></span>\n" +
            "        <img alt=\"Octocat-spinner-32\" class=\"spinner\" height=\"16\" src=\"https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif\" width=\"16\" />\n" +
            "      </td>\n" +
            "      <td class=\"content\">\n" +
            "        <span class=\"css-truncate css-truncate-target\"><a href=\"/code4craft/webmagic/tree/master/webmagic-lucene\" class=\"js-directory-link\" id=\"e686efe9e2cd770dcf86d93b9ddb2036-e16df360eb86bf0c21be610105981182a5e2ac05\" title=\"webmagic-lucene\">webmagic-lucene</a></span>\n" +
            "      </td>\n" +
            "      <td class=\"message\">\n" +
            "        <span class=\"css-truncate css-truncate-target\"><a href=\"/code4craft/webmagic/commit/7003426898ec684194a67130914c17c1566ed233\" class=\"message\" data-pjax=\"true\" title=\"update pom\">update pom</a></span>\n" +
            "      </td>\n" +
            "      <td class=\"age\"><span class=\"css-truncate css-truncate-target\"><time class=\"js-relative-date\" datetime=\"2013-08-20T06:52:39-07:00\" title=\"2013-08-20 06:52:39\">August 20, 2013</time></span></td>\n" +
            "    </tr>\n" +
            "    <tr class=\"alt\">\n" +
            "      <td class=\"icon\">\n" +
            "        <span class=\"octicon octicon-file-directory\"></span>\n" +
            "        <img alt=\"Octocat-spinner-32\" class=\"spinner\" height=\"16\" src=\"https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif\" width=\"16\" />\n" +
            "      </td>\n" +
            "      <td class=\"content\">\n" +
            "        <span class=\"css-truncate css-truncate-target\"><a href=\"/code4craft/webmagic/tree/master/webmagic-samples\" class=\"js-directory-link\" id=\"4284b70d4c5e11003fb292b0d0f7539f-55f538835cd8b15fb4e34c8a0d6491dc9559e610\" title=\"webmagic-samples\">webmagic-samples</a></span>\n" +
            "      </td>\n" +
            "      <td class=\"message\">\n" +
            "        <span class=\"css-truncate css-truncate-target\"><a href=\"/code4craft/webmagic/commit/95ab4edec3daca3353395909a13085079ff8606b\" class=\"message\" data-pjax=\"true\" title=\"some bugfix\">some bugfix</a></span>\n" +
            "      </td>\n" +
            "      <td class=\"age\"><span class=\"css-truncate css-truncate-target\"><time class=\"js-relative-date\" datetime=\"2013-09-22T17:38:54-07:00\" title=\"2013-09-22 17:38:54\">September 22, 2013</time></span></td>\n" +
            "    </tr>\n" +
            "    <tr class=\"\">\n" +
            "      <td class=\"icon\">\n" +
            "        <span class=\"octicon octicon-file-directory\"></span>\n" +
            "        <img alt=\"Octocat-spinner-32\" class=\"spinner\" height=\"16\" src=\"https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif\" width=\"16\" />\n" +
            "      </td>\n" +
            "      <td class=\"content\">\n" +
            "        <span class=\"css-truncate css-truncate-target\"><a href=\"/code4craft/webmagic/tree/master/webmagic-saxon\" class=\"js-directory-link\" id=\"5ee0de5b970664e15f6805d957403c63-c498acdbb391d3ae9ee0088ff086312c11aad18d\" title=\"webmagic-saxon\">webmagic-saxon</a></span>\n" +
            "      </td>\n" +
            "      <td class=\"message\">\n" +
            "        <span class=\"css-truncate css-truncate-target\"><a href=\"/code4craft/webmagic/commit/b1cba78bd6930bbbc3d44b4825fcc752932ca02c\" class=\"message\" data-pjax=\"true\" title=\"xsoup test\">xsoup test</a></span>\n" +
            "      </td>\n" +
            "      <td class=\"age\"><span class=\"css-truncate css-truncate-target\"><time class=\"js-relative-date\" datetime=\"2013-09-01T16:30:31-07:00\" title=\"2013-09-01 16:30:31\">September 01, 2013</time></span></td>\n" +
            "    </tr>\n" +
            "    <tr class=\"alt\">\n" +
            "      <td class=\"icon\">\n" +
            "        <span class=\"octicon octicon-file-directory\"></span>\n" +
            "        <img alt=\"Octocat-spinner-32\" class=\"spinner\" height=\"16\" src=\"https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif\" width=\"16\" />\n" +
            "      </td>\n" +
            "      <td class=\"content\">\n" +
            "        <span class=\"css-truncate css-truncate-target\"><a href=\"/code4craft/webmagic/tree/master/webmagic-selenium\" class=\"js-directory-link\" id=\"988c197af393f3198711cebacce7fd65-210d6b3ddaf0bc962553f1244495b6960fbd8994\" title=\"webmagic-selenium\">webmagic-selenium</a></span>\n" +
            "      </td>\n" +
            "      <td class=\"message\">\n" +
            "        <span class=\"css-truncate css-truncate-target\"><a href=\"/code4craft/webmagic/commit/7003426898ec684194a67130914c17c1566ed233\" class=\"message\" data-pjax=\"true\" title=\"update pom\">update pom</a></span>\n" +
            "      </td>\n" +
            "      <td class=\"age\"><span class=\"css-truncate css-truncate-target\"><time class=\"js-relative-date\" datetime=\"2013-08-20T06:52:39-07:00\" title=\"2013-08-20 06:52:39\">August 20, 2013</time></span></td>\n" +
            "    </tr>\n" +
            "    <tr class=\"\">\n" +
            "      <td class=\"icon\">\n" +
            "        <span class=\"octicon octicon-file-directory\"></span>\n" +
            "        <img alt=\"Octocat-spinner-32\" class=\"spinner\" height=\"16\" src=\"https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif\" width=\"16\" />\n" +
            "      </td>\n" +
            "      <td class=\"content\">\n" +
            "        <span class=\"css-truncate css-truncate-target\"><a href=\"/code4craft/webmagic/tree/master/zh_docs\" class=\"js-directory-link\" id=\"bec3b859688b0bbdb94899b1a5b56441-2cf0c7c178e3e0280b023f54e3ef21e9b7b9e3b3\" title=\"zh_docs\">zh_docs</a></span>\n" +
            "      </td>\n" +
            "      <td class=\"message\">\n" +
            "        <span class=\"css-truncate css-truncate-target\"><a href=\"/code4craft/webmagic/commit/81f75347573f70a39a83afd5d2f7d626b3b305bd\" class=\"message\" data-pjax=\"true\" title=\"update version\">update version</a></span>\n" +
            "      </td>\n" +
            "      <td class=\"age\"><span class=\"css-truncate css-truncate-target\"><time class=\"js-relative-date\" datetime=\"2013-09-12T06:28:42-07:00\" title=\"2013-09-12 06:28:42\">September 12, 2013</time></span></td>\n" +
            "    </tr>\n" +
            "    <tr class=\"alt\">\n" +
            "      <td class=\"icon\">\n" +
            "        <span class=\"octicon octicon-file-text\"></span>\n" +
            "        <img alt=\"Octocat-spinner-32\" class=\"spinner\" height=\"16\" src=\"https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif\" width=\"16\" />\n" +
            "      </td>\n" +
            "      <td class=\"content\">\n" +
            "        <span class=\"css-truncate css-truncate-target\"><a href=\"/code4craft/webmagic/blob/master/.gitignore\" class=\"js-directory-link\" id=\"a084b794bc0759e7a6b77810e01874f2-8e88e25dbf702e915d3d4839cbbca007859874b2\" title=\".gitignore\">.gitignore</a></span>\n" +
            "      </td>\n" +
            "      <td class=\"message\">\n" +
            "        <span class=\"css-truncate css-truncate-target\"><a href=\"/code4craft/webmagic/commit/4d023b3666cc2101e92540004e5630dd2aa01319\" class=\"message\" data-pjax=\"true\" title=\"增加剔除文件\">增加剔除文件</a></span>\n" +
            "      </td>\n" +
            "      <td class=\"age\"><span class=\"css-truncate css-truncate-target\"><time class=\"js-relative-date\" datetime=\"2013-09-04T09:30:52-07:00\" title=\"2013-09-04 09:30:52\">September 04, 2013</time></span></td>\n" +
            "    </tr>\n" +
            "    <tr class=\"\">\n" +
            "      <td class=\"icon\">\n" +
            "        <span class=\"octicon octicon-file-text\"></span>\n" +
            "        <img alt=\"Octocat-spinner-32\" class=\"spinner\" height=\"16\" src=\"https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif\" width=\"16\" />\n" +
            "      </td>\n" +
            "      <td class=\"content\">\n" +
            "        <span class=\"css-truncate css-truncate-target\"><a href=\"/code4craft/webmagic/blob/master/.travis.yml\" class=\"js-directory-link\" id=\"354f30a63fb0907d4ad57269548329e3-c7c99f406eb2b126614aacf99fb9e103ce30ce00\" title=\".travis.yml\">.travis.yml</a></span>\n" +
            "      </td>\n" +
            "      <td class=\"message\">\n" +
            "        <span class=\"css-truncate css-truncate-target\"><a href=\"/code4craft/webmagic/commit/95c8b2a8a44262fc20c4d5eddb9cf7ba18cfb753\" class=\"message\" data-pjax=\"true\" title=\"add jdk\">add jdk</a></span>\n" +
            "      </td>\n" +
            "      <td class=\"age\"><span class=\"css-truncate css-truncate-target\"><time class=\"js-relative-date\" datetime=\"2013-06-20T02:54:46-07:00\" title=\"2013-06-20 02:54:46\">June 20, 2013</time></span></td>\n" +
            "    </tr>\n" +
            "    <tr class=\"alt\">\n" +
            "      <td class=\"icon\">\n" +
            "        <span class=\"octicon octicon-file-text\"></span>\n" +
            "        <img alt=\"Octocat-spinner-32\" class=\"spinner\" height=\"16\" src=\"https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif\" width=\"16\" />\n" +
            "      </td>\n" +
            "      <td class=\"content\">\n" +
            "        <span class=\"css-truncate css-truncate-target\"><a href=\"/code4craft/webmagic/blob/master/README.md\" class=\"js-directory-link\" id=\"04c6e90faac2675aa89e2176d2eec7d8-01a868db17802ce7915cc2bcfad10244ef4de064\" title=\"README.md\">README.md</a></span>\n" +
            "      </td>\n" +
            "      <td class=\"message\">\n" +
            "        <span class=\"css-truncate css-truncate-target\"><a href=\"/code4craft/webmagic/commit/a0d64b76357a449386755b9867163c91d04a2426\" class=\"message\" data-pjax=\"true\" title=\"update version\">update version</a></span>\n" +
            "      </td>\n" +
            "      <td class=\"age\"><span class=\"css-truncate css-truncate-target\"><time class=\"js-relative-date\" datetime=\"2013-09-12T06:06:05-07:00\" title=\"2013-09-12 06:06:05\">September 12, 2013</time></span></td>\n" +
            "    </tr>\n" +
            "    <tr class=\"\">\n" +
            "      <td class=\"icon\">\n" +
            "        <span class=\"octicon octicon-file-text\"></span>\n" +
            "        <img alt=\"Octocat-spinner-32\" class=\"spinner\" height=\"16\" src=\"https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif\" width=\"16\" />\n" +
            "      </td>\n" +
            "      <td class=\"content\">\n" +
            "        <span class=\"css-truncate css-truncate-target\"><a href=\"/code4craft/webmagic/blob/master/pom.xml\" class=\"js-directory-link\" id=\"600376dffeb79835ede4a0b285078036-e2685a8ad6dbce1421232fced6e46ed3c8c3efa2\" title=\"pom.xml\">pom.xml</a></span>\n" +
            "      </td>\n" +
            "      <td class=\"message\">\n" +
            "        <span class=\"css-truncate css-truncate-target\"><a href=\"/code4craft/webmagic/commit/fb693a4ac41667ba70f2d7c11c73b364fa569e67\" class=\"message\" data-pjax=\"true\" title=\"[maven-release-plugin] prepare for next development iteration\">[maven-release-plugin] prepare for next development iteration</a></span>\n" +
            "      </td>\n" +
            "      <td class=\"age\"><span class=\"css-truncate css-truncate-target\"><time class=\"js-relative-date\" datetime=\"2013-09-08T07:25:07-07:00\" title=\"2013-09-08 07:25:07\">September 08, 2013</time></span></td>\n" +
            "    </tr>\n" +
            "    <tr class=\"alt\">\n" +
            "      <td class=\"icon\">\n" +
            "        <span class=\"octicon octicon-file-text\"></span>\n" +
            "        <img alt=\"Octocat-spinner-32\" class=\"spinner\" height=\"16\" src=\"https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif\" width=\"16\" />\n" +
            "      </td>\n" +
            "      <td class=\"content\">\n" +
            "        <span class=\"css-truncate css-truncate-target\"><a href=\"/code4craft/webmagic/blob/master/release-note.md\" class=\"js-directory-link\" id=\"d59c2d5d8d04d144da5f1cd251c384ad-001568be91dd7d90d2d26c06c192725af5ddd25e\" title=\"release-note.md\">release-note.md</a></span>\n" +
            "      </td>\n" +
            "      <td class=\"message\">\n" +
            "        <span class=\"css-truncate css-truncate-target\"><a href=\"/code4craft/webmagic/commit/a9fc06a916008e9763dec67d240e84d81e94185d\" class=\"message\" data-pjax=\"true\" title=\"release note\">release note</a></span>\n" +
            "      </td>\n" +
            "      <td class=\"age\"><span class=\"css-truncate css-truncate-target\"><time class=\"js-relative-date\" datetime=\"2013-09-03T20:04:36-07:00\" title=\"2013-09-03 20:04:36\">September 03, 2013</time></span></td>\n" +
            "    </tr>\n" +
            "    <tr class=\"\">\n" +
            "      <td class=\"icon\">\n" +
            "        <span class=\"octicon octicon-file-text\"></span>\n" +
            "        <img alt=\"Octocat-spinner-32\" class=\"spinner\" height=\"16\" src=\"https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif\" width=\"16\" />\n" +
            "      </td>\n" +
            "      <td class=\"content\">\n" +
            "        <span class=\"css-truncate css-truncate-target\"><a href=\"/code4craft/webmagic/blob/master/webmagic%20manual.md\" class=\"js-directory-link\" id=\"7a8cd261f7c7be5bd05d8f2ce23a818c-dc09b907d8873ba1c34c38b8cea2062af53ef625\" title=\"webmagic manual.md\">webmagic manual.md</a></span>\n" +
            "      </td>\n" +
            "      <td class=\"message\">\n" +
            "        <span class=\"css-truncate css-truncate-target\"><a href=\"/code4craft/webmagic/commit/4fa82aad20b0d208c8c2b17af2644f82b26c1b75\" class=\"message\" data-pjax=\"true\" title=\"readme\">readme</a></span>\n" +
            "      </td>\n" +
            "      <td class=\"age\"><span class=\"css-truncate css-truncate-target\"><time class=\"js-relative-date\" datetime=\"2013-08-20T16:44:39-07:00\" title=\"2013-08-20 16:44:39\">August 20, 2013</time></span></td>\n" +
            "    </tr>\n" +
            "</tbody>\n" +
            "\n" +
            "  </table>\n" +
            "</div>\n" +
            "\n" +
            "  <div id=\"readme\" class=\"clearfix announce instapaper_body md\">\n" +
            "    <span class=\"name\"><span class=\"octicon octicon-book\"></span> README.md</span><article class=\"markdown-body entry-content\" itemprop=\"mainContentOfPage\"><h2>\n" +
            "<a name=\"webmagic\" class=\"anchor\" href=\"#webmagic\"><span class=\"octicon octicon-link\"></span></a>webmagic</h2>\n" +
            "\n" +
            "<p><a href=\"https://github.com/code4craft/webmagic/tree/master/zh_docs\">Readme in Chinese</a></p>\n" +
            "\n" +
            "<p><a href=\"https://travis-ci.org/code4craft/webmagic\"><img src=\"https://travis-ci.org/code4craft/webmagic.png?branch=master\" alt=\"Build Status\" style=\"max-width:100%;\"></a></p>\n" +
            "\n" +
            "<blockquote>\n" +
            "<p>A scalable crawler framework. It covers the whole lifecycle of crawler: downloading, url management, content extraction and persistent. It can simplify the development of a  specific crawler.</p>\n" +
            "</blockquote>\n" +
            "\n" +
            "<h2>\n" +
            "<a name=\"features\" class=\"anchor\" href=\"#features\"><span class=\"octicon octicon-link\"></span></a>Features:</h2>\n" +
            "\n" +
            "<ul>\n" +
            "<li>Simple core with high flexibility.</li>\n" +
            "<li>Simple API for html extracting.</li>\n" +
            "<li>Annotation with POJO to customize a crawler, no configuration.</li>\n" +
            "<li>Multi-thread and Distribution support.</li>\n" +
            "<li>Easy to be integrated.</li>\n" +
            "</ul><h2>\n" +
            "<a name=\"install\" class=\"anchor\" href=\"#install\"><span class=\"octicon octicon-link\"></span></a>Install:</h2>\n" +
            "\n" +
            "<p>Add dependencies to your pom.xml:</p>\n" +
            "\n" +
            "<pre><code>    &lt;dependency&gt;\n" +
            "        &lt;groupId&gt;us.codecraft&lt;/groupId&gt;\n" +
            "        &lt;artifactId&gt;webmagic-core&lt;/artifactId&gt;\n" +
            "        &lt;version&gt;0.3.1&lt;/version&gt;\n" +
            "    &lt;/dependency&gt;\n" +
            "    &lt;dependency&gt;\n" +
            "        &lt;groupId&gt;us.codecraft&lt;/groupId&gt;\n" +
            "        &lt;artifactId&gt;webmagic-extension&lt;/artifactId&gt;\n" +
            "        &lt;version&gt;0.3.1&lt;/version&gt;\n" +
            "    &lt;/dependency&gt;\n" +
            "</code></pre>\n" +
            "\n" +
            "<h2>\n" +
            "<a name=\"get-started\" class=\"anchor\" href=\"#get-started\"><span class=\"octicon octicon-link\"></span></a>Get Started:</h2>\n" +
            "\n" +
            "<h3>\n" +
            "<a name=\"first-crawler\" class=\"anchor\" href=\"#first-crawler\"><span class=\"octicon octicon-link\"></span></a>First crawler:</h3>\n" +
            "\n" +
            "<p>Write a class implements PageProcessor：</p>\n" +
            "\n" +
            "<div class=\"highlight highlight-java\"><pre>    <span class=\"kd\">public</span> <span class=\"kd\">class</span> <span class=\"nc\">OschinaBlogPageProcessor</span> <span class=\"kd\">implements</span> <span class=\"n\">PageProcessor</span> <span class=\"o\">{</span>\n" +
            "\n" +
            "        <span class=\"kd\">private</span> <span class=\"n\">Site</span> <span class=\"n\">site</span> <span class=\"o\">=</span> <span class=\"n\">Site</span><span class=\"o\">.</span><span class=\"na\">me</span><span class=\"o\">().</span><span class=\"na\">setDomain</span><span class=\"o\">(</span><span class=\"s\">\"my.oschina.net\"</span><span class=\"o\">)</span>\n" +
            "           <span class=\"o\">.</span><span class=\"na\">addStartUrl</span><span class=\"o\">(</span><span class=\"s\">\"http://my.oschina.net/flashsword/blog\"</span><span class=\"o\">);</span>\n" +
            "\n" +
            "        <span class=\"nd\">@Override</span>\n" +
            "        <span class=\"kd\">public</span> <span class=\"kt\">void</span> <span class=\"nf\">process</span><span class=\"o\">(</span><span class=\"n\">Page</span> <span class=\"n\">page</span><span class=\"o\">)</span> <span class=\"o\">{</span>\n" +
            "            <span class=\"n\">List</span><span class=\"o\">&lt;</span><span class=\"n\">String</span><span class=\"o\">&gt;</span> <span class=\"n\">links</span> <span class=\"o\">=</span> <span class=\"n\">page</span><span class=\"o\">.</span><span class=\"na\">getHtml</span><span class=\"o\">().</span><span class=\"na\">links</span><span class=\"o\">().</span><span class=\"na\">regex</span><span class=\"o\">(</span><span class=\"s\">\"http://my\\\\.oschina\\\\.net/flashsword/blog/\\\\d+\"</span><span class=\"o\">).</span><span class=\"na\">all</span><span class=\"o\">();</span>\n" +
            "            <span class=\"n\">page</span><span class=\"o\">.</span><span class=\"na\">addTargetRequests</span><span class=\"o\">(</span><span class=\"n\">links</span><span class=\"o\">);</span>\n" +
            "            <span class=\"n\">page</span><span class=\"o\">.</span><span class=\"na\">putField</span><span class=\"o\">(</span><span class=\"s\">\"title\"</span><span class=\"o\">,</span> <span class=\"n\">page</span><span class=\"o\">.</span><span class=\"na\">getHtml</span><span class=\"o\">().</span><span class=\"na\">xpath</span><span class=\"o\">(</span><span class=\"s\">\"//div[@class='BlogEntity']/div[@class='BlogTitle']/h1\"</span><span class=\"o\">).</span><span class=\"na\">toString</span><span class=\"o\">());</span>\n" +
            "            <span class=\"n\">page</span><span class=\"o\">.</span><span class=\"na\">putField</span><span class=\"o\">(</span><span class=\"s\">\"content\"</span><span class=\"o\">,</span> <span class=\"n\">page</span><span class=\"o\">.</span><span class=\"na\">getHtml</span><span class=\"o\">().</span><span class=\"n\">$</span><span class=\"o\">(</span><span class=\"s\">\"div.content\"</span><span class=\"o\">).</span><span class=\"na\">toString</span><span class=\"o\">());</span>\n" +
            "            <span class=\"n\">page</span><span class=\"o\">.</span><span class=\"na\">putField</span><span class=\"o\">(</span><span class=\"s\">\"tags\"</span><span class=\"o\">,</span><span class=\"n\">page</span><span class=\"o\">.</span><span class=\"na\">getHtml</span><span class=\"o\">().</span><span class=\"na\">xpath</span><span class=\"o\">(</span><span class=\"s\">\"//div[@class='BlogTags']/a/text()\"</span><span class=\"o\">).</span><span class=\"na\">all</span><span class=\"o\">());</span>\n" +
            "        <span class=\"o\">}</span>\n" +
            "\n" +
            "        <span class=\"nd\">@Override</span>\n" +
            "        <span class=\"kd\">public</span> <span class=\"n\">Site</span> <span class=\"nf\">getSite</span><span class=\"o\">()</span> <span class=\"o\">{</span>\n" +
            "            <span class=\"k\">return</span> <span class=\"n\">site</span><span class=\"o\">;</span>\n" +
            "\n" +
            "        <span class=\"o\">}</span>\n" +
            "\n" +
            "        <span class=\"kd\">public</span> <span class=\"kd\">static</span> <span class=\"kt\">void</span> <span class=\"nf\">main</span><span class=\"o\">(</span><span class=\"n\">String</span><span class=\"o\">[]</span> <span class=\"n\">args</span><span class=\"o\">)</span> <span class=\"o\">{</span>\n" +
            "            <span class=\"n\">Spider</span><span class=\"o\">.</span><span class=\"na\">create</span><span class=\"o\">(</span><span class=\"k\">new</span> <span class=\"n\">OschinaBlogPageProcessor</span><span class=\"o\">())</span>\n" +
            "                 <span class=\"o\">.</span><span class=\"na\">pipeline</span><span class=\"o\">(</span><span class=\"k\">new</span> <span class=\"n\">ConsolePipeline</span><span class=\"o\">()).</span><span class=\"na\">run</span><span class=\"o\">();</span>\n" +
            "        <span class=\"o\">}</span>\n" +
            "    <span class=\"o\">}</span>\n" +
            "</pre></div>\n" +
            "\n" +
            "<ul>\n" +
            "<li>\n" +
            "<p><code>page.addTargetRequests(links)</code></p>\n" +
            "\n" +
            "<p>Add urls for crawling.</p>\n" +
            "</li>\n" +
            "</ul><p>You can also use annotation way:</p>\n" +
            "\n" +
            "<div class=\"highlight highlight-java\"><pre>    <span class=\"nd\">@TargetUrl</span><span class=\"o\">(</span><span class=\"s\">\"http://my.oschina.net/flashsword/blog/\\\\d+\"</span><span class=\"o\">)</span>\n" +
            "    <span class=\"kd\">public</span> <span class=\"kd\">class</span> <span class=\"nc\">OschinaBlog</span> <span class=\"o\">{</span>\n" +
            "\n" +
            "        <span class=\"nd\">@ExtractBy</span><span class=\"o\">(</span><span class=\"s\">\"//title\"</span><span class=\"o\">)</span>\n" +
            "        <span class=\"kd\">private</span> <span class=\"n\">String</span> <span class=\"n\">title</span><span class=\"o\">;</span>\n" +
            "\n" +
            "        <span class=\"nd\">@ExtractBy</span><span class=\"o\">(</span><span class=\"n\">value</span> <span class=\"o\">=</span> <span class=\"s\">\"div.BlogContent\"</span><span class=\"o\">,</span><span class=\"n\">type</span> <span class=\"o\">=</span> <span class=\"n\">ExtractBy</span><span class=\"o\">.</span><span class=\"na\">Type</span><span class=\"o\">.</span><span class=\"na\">Css</span><span class=\"o\">)</span>\n" +
            "        <span class=\"kd\">private</span> <span class=\"n\">String</span> <span class=\"n\">content</span><span class=\"o\">;</span>\n" +
            "\n" +
            "        <span class=\"nd\">@ExtractBy</span><span class=\"o\">(</span><span class=\"n\">value</span> <span class=\"o\">=</span> <span class=\"s\">\"//div[@class='BlogTags']/a/text()\"</span><span class=\"o\">,</span> <span class=\"n\">multi</span> <span class=\"o\">=</span> <span class=\"kc\">true</span><span class=\"o\">)</span>\n" +
            "        <span class=\"kd\">private</span> <span class=\"n\">List</span><span class=\"o\">&lt;</span><span class=\"n\">String</span><span class=\"o\">&gt;</span> <span class=\"n\">tags</span><span class=\"o\">;</span>\n" +
            "\n" +
            "        <span class=\"kd\">public</span> <span class=\"kd\">static</span> <span class=\"kt\">void</span> <span class=\"nf\">main</span><span class=\"o\">(</span><span class=\"n\">String</span><span class=\"o\">[]</span> <span class=\"n\">args</span><span class=\"o\">)</span> <span class=\"o\">{</span>\n" +
            "            <span class=\"n\">OOSpider</span><span class=\"o\">.</span><span class=\"na\">create</span><span class=\"o\">(</span>\n" +
            "                <span class=\"n\">Site</span><span class=\"o\">.</span><span class=\"na\">me</span><span class=\"o\">().</span><span class=\"na\">addStartUrl</span><span class=\"o\">(</span><span class=\"s\">\"http://my.oschina.net/flashsword/blog\"</span><span class=\"o\">),</span>\n" +
            "                <span class=\"k\">new</span> <span class=\"nf\">ConsolePageModelPipeline</span><span class=\"o\">(),</span> <span class=\"n\">OschinaBlog</span><span class=\"o\">.</span><span class=\"na\">class</span><span class=\"o\">).</span><span class=\"na\">run</span><span class=\"o\">();</span>\n" +
            "        <span class=\"o\">}</span>\n" +
            "    <span class=\"o\">}</span>\n" +
            "</pre></div>\n" +
            "\n" +
            "<h3>\n" +
            "<a name=\"docs-and-samples\" class=\"anchor\" href=\"#docs-and-samples\"><span class=\"octicon octicon-link\"></span></a>Docs and samples:</h3>\n" +
            "\n" +
            "<p>The architecture of webmagic (refered to <a href=\"http://scrapy.org/\">Scrapy</a>)</p>\n" +
            "\n" +
            "<p><a href=\"https://github-camo.global.ssl.fastly.net/06cb8227231a6adf6d2a57b14b60a25389a25fe9/687474703a2f2f636f64653463726166742e6769746875622e696f2f696d616765732f706f7374732f7765626d616769632e706e67\" target=\"_blank\"><img src=\"https://github-camo.global.ssl.fastly.net/06cb8227231a6adf6d2a57b14b60a25389a25fe9/687474703a2f2f636f64653463726166742e6769746875622e696f2f696d616765732f706f7374732f7765626d616769632e706e67\" alt=\"image\" style=\"max-width:100%;\"></a></p>\n" +
            "\n" +
            "<p>Javadocs: <a href=\"http://code4craft.github.io/webmagic/docs/en/\">http://code4craft.github.io/webmagic/docs/en/</a></p>\n" +
            "\n" +
            "<p>There are some samples in <code>webmagic-samples</code> package.</p>\n" +
            "\n" +
            "<h3>\n" +
            "<a name=\"lisence\" class=\"anchor\" href=\"#lisence\"><span class=\"octicon octicon-link\"></span></a>Lisence:</h3>\n" +
            "\n" +
            "<p>Lisenced under <a href=\"http://opensource.org/licenses/Apache-2.0\">Apache 2.0 lisence</a></p>\n" +
            "\n" +
            "<h3>\n" +
            "<a name=\"thanks\" class=\"anchor\" href=\"#thanks\"><span class=\"octicon octicon-link\"></span></a>Thanks:</h3>\n" +
            "\n" +
            "<p>To write webmagic, I refered to the projects below :</p>\n" +
            "\n" +
            "<ul>\n" +
            "<li>\n" +
            "<p><strong>Scrapy</strong></p>\n" +
            "\n" +
            "<p>A crawler framework in Python.</p>\n" +
            "\n" +
            "<p><a href=\"http://scrapy.org/\">http://scrapy.org/</a></p>\n" +
            "</li>\n" +
            "<li>\n" +
            "<p><strong>Spiderman</strong></p>\n" +
            "\n" +
            "<p>Another crawler framework in Java.</p>\n" +
            "\n" +
            "<p><a href=\"https://gitcafe.com/laiweiwei/Spiderman\">https://gitcafe.com/laiweiwei/Spiderman</a></p>\n" +
            "</li>\n" +
            "</ul></article>\n" +
            "  </div>\n" +
            "\n" +
            "\n" +
            "        </div>\n" +
            "\n" +
            "      </div><!-- /.repo-container -->\n" +
            "      <div class=\"modal-backdrop\"></div>\n" +
            "    </div><!-- /.container -->\n" +
            "  </div><!-- /.site -->\n" +
            "\n" +
            "\n" +
            "    </div><!-- /.wrapper -->\n" +
            "\n" +
            "      <div class=\"container\">\n" +
            "  <div class=\"site-footer\">\n" +
            "    <ul class=\"site-footer-links right\">\n" +
            "      <li><a href=\"https://status.github.com/\">Status</a></li>\n" +
            "      <li><a href=\"http://developer.github.com\">API</a></li>\n" +
            "      <li><a href=\"http://training.github.com\">Training</a></li>\n" +
            "      <li><a href=\"http://shop.github.com\">Shop</a></li>\n" +
            "      <li><a href=\"/blog\">Blog</a></li>\n" +
            "      <li><a href=\"/about\">About</a></li>\n" +
            "\n" +
            "    </ul>\n" +
            "\n" +
            "    <a href=\"/\">\n" +
            "      <span class=\"mega-octicon octicon-mark-github\"></span>\n" +
            "    </a>\n" +
            "\n" +
            "    <ul class=\"site-footer-links\">\n" +
            "      <li>&copy; 2013 <span title=\"0.04752s from github-fe114-cp1-prd.iad.github.net\">GitHub</span>, Inc.</li>\n" +
            "        <li><a href=\"/site/terms\">Terms</a></li>\n" +
            "        <li><a href=\"/site/privacy\">Privacy</a></li>\n" +
            "        <li><a href=\"/security\">Security</a></li>\n" +
            "        <li><a href=\"/contact\">Contact</a></li>\n" +
            "    </ul>\n" +
            "  </div><!-- /.site-footer -->\n" +
            "</div><!-- /.container -->\n" +
            "\n" +
            "\n" +
            "    <div class=\"fullscreen-overlay js-fullscreen-overlay\" id=\"fullscreen_overlay\">\n" +
            "  <div class=\"fullscreen-container js-fullscreen-container\">\n" +
            "    <div class=\"textarea-wrap\">\n" +
            "      <textarea name=\"fullscreen-contents\" id=\"fullscreen-contents\" class=\"js-fullscreen-contents\" placeholder=\"\" data-suggester=\"fullscreen_suggester\"></textarea>\n" +
            "          <div class=\"suggester-container\">\n" +
            "              <div class=\"suggester fullscreen-suggester js-navigation-container\" id=\"fullscreen_suggester\"\n" +
            "                 data-url=\"/code4craft/webmagic/suggestions/commit\">\n" +
            "              </div>\n" +
            "          </div>\n" +
            "    </div>\n" +
            "  </div>\n" +
            "  <div class=\"fullscreen-sidebar\">\n" +
            "    <a href=\"#\" class=\"exit-fullscreen js-exit-fullscreen tooltipped leftwards\" title=\"Exit Zen Mode\">\n" +
            "      <span class=\"mega-octicon octicon-screen-normal\"></span>\n" +
            "    </a>\n" +
            "    <a href=\"#\" class=\"theme-switcher js-theme-switcher tooltipped leftwards\"\n" +
            "      title=\"Switch themes\">\n" +
            "      <span class=\"octicon octicon-color-mode\"></span>\n" +
            "    </a>\n" +
            "  </div>\n" +
            "</div>\n" +
            "\n" +
            "\n" +
            "\n" +
            "    <div id=\"ajax-error-message\" class=\"flash flash-error\">\n" +
            "      <span class=\"octicon octicon-alert\"></span>\n" +
            "      <a href=\"#\" class=\"octicon octicon-remove-close close ajax-error-dismiss\"></a>\n" +
            "      Something went wrong with that request. Please try again.\n" +
            "    </div>\n" +
            "\n" +
            "  </body>\n" +
            "</html>\n" +
            "\n";
    @Override
    public Page download(Request request, Task task) {
        Page page = new Page();
        page.setRawText(html);
        page.setStatusCode(200);
        page.setRequest(new Request("https://github.com/code4craft/webmagic"));
        page.setUrl(new PlainText("https://github.com/code4craft/webmagic"));
        return page;
    }

    @Override
    public void setThread(int threadNum) {
    }
}
