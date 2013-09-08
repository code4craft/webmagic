package us.codecraft.webmagic;

import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.PlainText;

/**
 * @author code4crafter@gmail.com
 */
public class MockDownloader implements Downloader{

    private String html = "\n" +
            "\n" +
            "\n" +
            "\n" +
            "<!DOCTYPE html>\n" +
            "<html>\n" +
            "  <head prefix=\"og: http://ogp.me/ns# fb: http://ogp.me/ns/fb# githubog: http://ogp.me/ns/fb/githubog#\">\n" +
            "    <meta charset='utf-8'>\n" +
            "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
            "        <title>code4craft/webmagic</title>\n" +
            "    <link rel=\"search\" type=\"application/opensearchdescription+xml\" href=\"/opensearch.xml\" title=\"GitHub\" />\n" +
            "    <link rel=\"fluid-icon\" href=\"https://github.com/fluidicon.png\" title=\"GitHub\" />\n" +
            "    <link rel=\"apple-touch-icon\" sizes=\"57x57\" href=\"/apple-touch-icon-114.png\" />\n" +
            "    <link rel=\"apple-touch-icon\" sizes=\"114x114\" href=\"/apple-touch-icon-114.png\" />\n" +
            "    <link rel=\"apple-touch-icon\" sizes=\"72x72\" href=\"/apple-touch-icon-144.png\" />\n" +
            "    <link rel=\"apple-touch-icon\" sizes=\"144x144\" href=\"/apple-touch-icon-144.png\" />\n" +
            "    <link rel=\"logo\" type=\"image/svg\" href=\"https://github-media-downloads.s3.amazonaws.com/github-logo.svg\" />\n" +
            "    <meta property=\"og:image\" content=\"https://github.global.ssl.fastly.net/images/modules/logos_page/Octocat.png\">\n" +
            "    <meta name=\"hostname\" content=\"github-fe120-cp1-prd.iad.github.net\">\n" +
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
            "    <meta content=\"collector.githubapp.com\" name=\"octolytics-host\" /><meta content=\"github\" name=\"octolytics-app-id\" /><meta content=\"d70ff776-e041-43ec-9e11-6fff09ae6117\" name=\"octolytics-dimension-request_id\" /><meta content=\"1351884\" name=\"octolytics-actor-id\" /><meta content=\"code4craft\" name=\"octolytics-actor-login\" /><meta content=\"6ba594fdd7b6075190d470f5284075cfe97dcb1f80883d29c3d79d927e87ac85\" name=\"octolytics-actor-hash\" />\n" +
            "    \n" +
            "\n" +
            "    \n" +
            "    \n" +
            "    <link rel=\"icon\" type=\"image/x-icon\" href=\"/favicon.ico\" />\n" +
            "\n" +
            "    <meta content=\"authenticity_token\" name=\"csrf-param\" />\n" +
            "<meta content=\"i4/tXwrpqoMtPPKJTN4eSSPnFfrSzZkuIkeP//SUW34=\" name=\"csrf-token\" />\n" +
            "\n" +
            "    <link href=\"https://github.global.ssl.fastly.net/assets/github-8d13b140cf7e2873c4dd1e0f589136f0e71bd381.css\" media=\"all\" rel=\"stylesheet\" type=\"text/css\" />\n" +
            "    <link href=\"https://github.global.ssl.fastly.net/assets/github2-d75c750a6b14571dc070b6570d9224acd7b6795e.css\" media=\"all\" rel=\"stylesheet\" type=\"text/css\" />\n" +
            "    \n" +
            "\n" +
            "\n" +
            "      <script src=\"https://github.global.ssl.fastly.net/assets/frameworks-f86a2975a82dceee28e5afe598d1ebbfd7109d79.js\" type=\"text/javascript\"></script>\n" +
            "      <script src=\"https://github.global.ssl.fastly.net/assets/github-5289a6d6f7dbb5c517007827e10db51fd3ea0251.js\" type=\"text/javascript\"></script>\n" +
            "      \n" +
            "      <meta http-equiv=\"x-pjax-version\" content=\"119d1d5ab0189c49025edd294a6b79f2\">\n" +
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
            "  <body class=\"logged_in  env-production macintosh vis-public\">\n" +
            "    <div class=\"wrapper\">\n" +
            "      \n" +
            "      \n" +
            "      \n" +
            "\n" +
            "\n" +
            "      <div class=\"header header-logged-in true\">\n" +
            "  <div class=\"container clearfix\">\n" +
            "\n" +
            "    <a class=\"header-logo-invertocat\" href=\"https://github.com/\">\n" +
            "  <span class=\"mega-octicon octicon-mark-github\"></span>\n" +
            "</a>\n" +
            "\n" +
            "    <div class=\"divider-vertical\"></div>\n" +
            "\n" +
            "    \n" +
            "    <a href=\"/notifications\" class=\"notification-indicator tooltipped downwards\" data-gotokey=\"n\" title=\"You have no unread notifications\">\n" +
            "        <span class=\"mail-status all-read\"></span>\n" +
            "</a>    <div class=\"divider-vertical\"></div>\n" +
            "\n" +
            "\n" +
            "      <div class=\"command-bar js-command-bar  in-repository\">\n" +
            "          <form accept-charset=\"UTF-8\" action=\"/search\" class=\"command-bar-form\" id=\"top_search_form\" method=\"get\">\n" +
            "\n" +
            "<input type=\"text\" data-hotkey=\"/ s\" name=\"q\" id=\"js-command-bar-field\" placeholder=\"Search or type a command\" tabindex=\"1\" autocapitalize=\"off\"\n" +
            "    \n" +
            "    data-username=\"code4craft\"\n" +
            "      data-repo=\"code4craft/webmagic\"\n" +
            "      data-branch=\"master\"\n" +
            "      data-sha=\"e4a0a442b4476c547e95db5cdaa06e2274cac38f\"\n" +
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
            "        <ul class=\"top-nav\">\n" +
            "          <li class=\"explore\"><a href=\"/explore\">Explore</a></li>\n" +
            "            <li><a href=\"https://gist.github.com\">Gist</a></li>\n" +
            "            <li><a href=\"/blog\">Blog</a></li>\n" +
            "          <li><a href=\"https://help.github.com\">Help</a></li>\n" +
            "        </ul>\n" +
            "      </div>\n" +
            "\n" +
            "    \n" +
            "\n" +
            "\n" +
            "  <ul id=\"user-links\">\n" +
            "    <li>\n" +
            "      <a href=\"/code4craft\" class=\"name\">\n" +
            "        <img height=\"20\" src=\"https://2.gravatar.com/avatar/4ce9123a05ae222d71d2857316cbe699?d=https%3A%2F%2Fidenticons.github.com%2F19ef9dc10e8399f81a8944a399812d77.png&amp;s=140\" width=\"20\" /> code4craft\n" +
            "      </a>\n" +
            "    </li>\n" +
            "\n" +
            "      <li>\n" +
            "        <a href=\"/new\" id=\"new_repo\" class=\"tooltipped downwards\" title=\"Create a new repo\" aria-label=\"Create a new repo\">\n" +
            "          <span class=\"octicon octicon-repo-create\"></span>\n" +
            "        </a>\n" +
            "      </li>\n" +
            "\n" +
            "      <li>\n" +
            "        <a href=\"/settings/profile\" id=\"account_settings\"\n" +
            "          class=\"tooltipped downwards\"\n" +
            "          aria-label=\"Account settings \"\n" +
            "          title=\"Account settings \">\n" +
            "          <span class=\"octicon octicon-tools\"></span>\n" +
            "        </a>\n" +
            "      </li>\n" +
            "      <li>\n" +
            "        <a class=\"tooltipped downwards\" href=\"/logout\" data-method=\"post\" id=\"logout\" title=\"Sign out\" aria-label=\"Sign out\">\n" +
            "          <span class=\"octicon octicon-log-out\"></span>\n" +
            "        </a>\n" +
            "      </li>\n" +
            "\n" +
            "  </ul>\n" +
            "\n" +
            "<div class=\"js-new-dropdown-contents hidden\">\n" +
            "  \n" +
            "\n" +
            "<ul class=\"dropdown-menu\">\n" +
            "  <li>\n" +
            "    <a href=\"/new\"><span class=\"octicon octicon-repo-create\"></span> New repository</a>\n" +
            "  </li>\n" +
            "  <li>\n" +
            "    <a href=\"/organizations/new\"><span class=\"octicon octicon-organization\"></span> New organization</a>\n" +
            "  </li>\n" +
            "\n" +
            "\n" +
            "\n" +
            "    <li class=\"section-title\">\n" +
            "      <span title=\"code4craft/webmagic\">This repository</span>\n" +
            "    </li>\n" +
            "    <li>\n" +
            "      <a href=\"/code4craft/webmagic/issues/new\"><span class=\"octicon octicon-issue-opened\"></span> New issue</a>\n" +
            "    </li>\n" +
            "      <li>\n" +
            "        <a href=\"/code4craft/webmagic/settings/collaboration\"><span class=\"octicon octicon-person-add\"></span> New collaborator</a>\n" +
            "      </li>\n" +
            "</ul>\n" +
            "\n" +
            "</div>\n" +
            "\n" +
            "\n" +
            "    \n" +
            "  </div>\n" +
            "</div>\n" +
            "\n" +
            "      \n" +
            "\n" +
            "      \n" +
            "\n" +
            "\n" +
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
            "    <li class=\"subscription\">\n" +
            "      <form accept-charset=\"UTF-8\" action=\"/notifications/subscribe\" class=\"js-social-container\" data-autosubmit=\"true\" data-remote=\"true\" method=\"post\"><div style=\"margin:0;padding:0;display:inline\"><input name=\"authenticity_token\" type=\"hidden\" value=\"i4/tXwrpqoMtPPKJTN4eSSPnFfrSzZkuIkeP//SUW34=\" /></div>  <input id=\"repository_id\" name=\"repository_id\" type=\"hidden\" value=\"9623064\" />\n" +
            "\n" +
            "    <div class=\"select-menu js-menu-container js-select-menu\">\n" +
            "        <a class=\"social-count js-social-count\" href=\"/code4craft/webmagic/watchers\">\n" +
            "          23\n" +
            "        </a>\n" +
            "      <span class=\"minibutton select-menu-button with-count js-menu-target\">\n" +
            "        <span class=\"js-select-button\">\n" +
            "          <span class=\"octicon octicon-eye-unwatch\"></span>\n" +
            "          Unwatch\n" +
            "        </span>\n" +
            "      </span>\n" +
            "\n" +
            "      <div class=\"select-menu-modal-holder\">\n" +
            "        <div class=\"select-menu-modal subscription-menu-modal js-menu-content\">\n" +
            "          <div class=\"select-menu-header\">\n" +
            "            <span class=\"select-menu-title\">Notification status</span>\n" +
            "            <span class=\"octicon octicon-remove-close js-menu-close\"></span>\n" +
            "          </div> <!-- /.select-menu-header -->\n" +
            "\n" +
            "          <div class=\"select-menu-list js-navigation-container\">\n" +
            "\n" +
            "            <div class=\"select-menu-item js-navigation-item \">\n" +
            "              <span class=\"select-menu-item-icon octicon octicon-check\"></span>\n" +
            "              <div class=\"select-menu-item-text\">\n" +
            "                <input id=\"do_included\" name=\"do\" type=\"radio\" value=\"included\" />\n" +
            "                <h4>Not watching</h4>\n" +
            "                <span class=\"description\">You only receive notifications for discussions in which you participate or are @mentioned.</span>\n" +
            "                <span class=\"js-select-button-text hidden-select-button-text\">\n" +
            "                  <span class=\"octicon octicon-eye-watch\"></span>\n" +
            "                  Watch\n" +
            "                </span>\n" +
            "              </div>\n" +
            "            </div> <!-- /.select-menu-item -->\n" +
            "\n" +
            "            <div class=\"select-menu-item js-navigation-item selected\">\n" +
            "              <span class=\"select-menu-item-icon octicon octicon octicon-check\"></span>\n" +
            "              <div class=\"select-menu-item-text\">\n" +
            "                <input checked=\"checked\" id=\"do_subscribed\" name=\"do\" type=\"radio\" value=\"subscribed\" />\n" +
            "                <h4>Watching</h4>\n" +
            "                <span class=\"description\">You receive notifications for all discussions in this repository.</span>\n" +
            "                <span class=\"js-select-button-text hidden-select-button-text\">\n" +
            "                  <span class=\"octicon octicon-eye-unwatch\"></span>\n" +
            "                  Unwatch\n" +
            "                </span>\n" +
            "              </div>\n" +
            "            </div> <!-- /.select-menu-item -->\n" +
            "\n" +
            "            <div class=\"select-menu-item js-navigation-item \">\n" +
            "              <span class=\"select-menu-item-icon octicon octicon-check\"></span>\n" +
            "              <div class=\"select-menu-item-text\">\n" +
            "                <input id=\"do_ignore\" name=\"do\" type=\"radio\" value=\"ignore\" />\n" +
            "                <h4>Ignoring</h4>\n" +
            "                <span class=\"description\">You do not receive any notifications for discussions in this repository.</span>\n" +
            "                <span class=\"js-select-button-text hidden-select-button-text\">\n" +
            "                  <span class=\"octicon octicon-mute\"></span>\n" +
            "                  Stop ignoring\n" +
            "                </span>\n" +
            "              </div>\n" +
            "            </div> <!-- /.select-menu-item -->\n" +
            "\n" +
            "          </div> <!-- /.select-menu-list -->\n" +
            "\n" +
            "        </div> <!-- /.select-menu-modal -->\n" +
            "      </div> <!-- /.select-menu-modal-holder -->\n" +
            "    </div> <!-- /.select-menu -->\n" +
            "\n" +
            "</form>\n" +
            "    </li>\n" +
            "\n" +
            "  <li>\n" +
            "  \n" +
            "<div class=\"js-toggler-container js-social-container starring-container \">\n" +
            "  <a href=\"/code4craft/webmagic/unstar\" class=\"minibutton with-count js-toggler-target star-button starred upwards\" title=\"Unstar this repo\" data-remote=\"true\" data-method=\"post\" rel=\"nofollow\">\n" +
            "    <span class=\"octicon octicon-star-delete\"></span><span class=\"text\">Unstar</span>\n" +
            "  </a>\n" +
            "  <a href=\"/code4craft/webmagic/star\" class=\"minibutton with-count js-toggler-target star-button unstarred upwards\" title=\"Star this repo\" data-remote=\"true\" data-method=\"post\" rel=\"nofollow\">\n" +
            "    <span class=\"octicon octicon-star\"></span><span class=\"text\">Star</span>\n" +
            "  </a>\n" +
            "  <a class=\"social-count js-social-count\" href=\"/code4craft/webmagic/stargazers\">78</a>\n" +
            "</div>\n" +
            "\n" +
            "  </li>\n" +
            "\n" +
            "\n" +
            "        <li>\n" +
            "          <a href=\"/code4craft/webmagic/fork\" class=\"minibutton with-count js-toggler-target fork-button lighter upwards\" title=\"Fork this repo\" rel=\"nofollow\" data-method=\"post\">\n" +
            "            <span class=\"octicon octicon-git-branch-create\"></span><span class=\"text\">Fork</span>\n" +
            "          </a>\n" +
            "          <a href=\"/code4craft/webmagic/network\" class=\"social-count\">65</a>\n" +
            "        </li>\n" +
            "\n" +
            "\n" +
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
            "            <span class='counter'>7</span>\n" +
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
            "      <div class=\"repo-menu-separator\"></div>\n" +
            "      <ul class=\"repo-menu\">\n" +
            "        <li class=\"tooltipped leftwards\" title=\"Settings\">\n" +
            "          <a href=\"/code4craft/webmagic/settings\" data-pjax aria-label=\"Settings\">\n" +
            "            <span class=\"octicon octicon-tools\"></span> <span class=\"full-word\">Settings</span>\n" +
            "            <img alt=\"Octocat-spinner-32\" class=\"mini-loader\" height=\"16\" src=\"https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif\" width=\"16\" />\n" +
            "          </a>\n" +
            "        </li>\n" +
            "      </ul>\n" +
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
            "  data-url=\"/users/set_protocol?protocol_selector=http&amp;protocol_type=push\">\n" +
            "  <h3><strong>HTTPS</strong> clone URL</h3>\n" +
            "\n" +
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
            "  data-protocol-type=\"ssh\"\n" +
            "  data-url=\"/users/set_protocol?protocol_selector=ssh&amp;protocol_type=push\">\n" +
            "  <h3><strong>SSH</strong> clone URL</h3>\n" +
            "\n" +
            "  <div class=\"clone-url-box\">\n" +
            "    <input type=\"text\" class=\"clone js-url-field\"\n" +
            "           value=\"git@github.com:code4craft/webmagic.git\" readonly=\"readonly\">\n" +
            "\n" +
            "    <span class=\"js-zeroclipboard url-box-clippy minibutton zeroclipboard-button\" data-clipboard-text=\"git@github.com:code4craft/webmagic.git\" data-copied-hint=\"copied!\" title=\"copy to clipboard\"><span class=\"octicon octicon-clippy\"></span></span>\n" +
            "  </div>\n" +
            "</div>\n" +
            "\n" +
            "  \n" +
            "\n" +
            "<div class=\"clone-url \"\n" +
            "  data-protocol-type=\"subversion\"\n" +
            "  data-url=\"/users/set_protocol?protocol_selector=subversion&amp;protocol_type=push\">\n" +
            "  <h3><strong>Subversion</strong> checkout URL</h3>\n" +
            "\n" +
            "  <div class=\"clone-url-box\">\n" +
            "    <input type=\"text\" class=\"clone js-url-field\"\n" +
            "           value=\"https://github.com/code4craft/webmagic\" readonly=\"readonly\">\n" +
            "\n" +
            "    <span class=\"js-zeroclipboard url-box-clippy minibutton zeroclipboard-button\" data-clipboard-text=\"https://github.com/code4craft/webmagic\" data-copied-hint=\"copied!\" title=\"copy to clipboard\"><span class=\"octicon octicon-clippy\"></span></span>\n" +
            "  </div>\n" +
            "</div>\n" +
            "\n" +
            "\n" +
            "\n" +
            "<p class=\"clone-options\">You can clone with\n" +
            "    <a href=\"#\" class=\"js-clone-selector\" data-protocol=\"http\">HTTPS</a>,\n" +
            "    <a href=\"#\" class=\"js-clone-selector\" data-protocol=\"ssh\">SSH</a>,\n" +
            "    <a href=\"#\" class=\"js-clone-selector\" data-protocol=\"subversion\">Subversion</a>,\n" +
            "  and <a href=\"https://help.github.com/articles/which-remote-url-should-i-use\">other methods.</a>\n" +
            "</p>\n" +
            "\n" +
            "  <a href=\"github-mac://openRepo/https://github.com/code4craft/webmagic\" class=\"minibutton sidebar-button\">\n" +
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
            "    <span class=\"edit-link js-details-show js-details-target\">— <a href=\"#\">Edit</a></span>\n" +
            "    <form accept-charset=\"UTF-8\" action=\"/code4craft/webmagic/settings/update_meta\" class=\"edit-repository-meta js-details-edit\" method=\"post\"><div style=\"margin:0;padding:0;display:inline\"><input name=\"_method\" type=\"hidden\" value=\"put\" /><input name=\"authenticity_token\" type=\"hidden\" value=\"i4/tXwrpqoMtPPKJTN4eSSPnFfrSzZkuIkeP//SUW34=\" /></div>\n" +
            "\n" +
            "      <div class=\"field description-field\">\n" +
            "        <label for=\"repo_description\">Description</label>\n" +
            "        <input type=\"text\" name=\"repo_description\" value=\"A scalable web crawler framework.\" placeholder=\"Short description of this repository\" />\n" +
            "      </div>\n" +
            "\n" +
            "      <div class=\"field website-field\" >\n" +
            "        <label for=\"repo_homepage\">Website</label>\n" +
            "        <input type=\"text\" name=\"repo_homepage\" value=\"\" placeholder=\"Website for this repository (optional)\" />\n" +
            "      </div>\n" +
            "\n" +
            "      <button class=\"button classy\">Save</button>\n" +
            "      <span class=\"cancel\">or <a href=\"#\" class=\"js-details-target\">cancel</a></a>\n" +
            "</form>\n" +
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
            "            299\n" +
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
            "            4\n" +
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
            "                <span class=\"percent\">100.0%</span>\n" +
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
            "  <span class=\"language-color\" style=\"width:100.0%; background-color:#b07219;\" itemprop=\"keywords\">Java</span>\n" +
            "  </a>\n" +
            "\n" +
            "\n" +
            "  <div\n" +
            "    >\n" +
            "  </div>\n" +
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
            "    data-ref=\"master\" role=\"button\" aria-label=\"Switch branches or tags\">\n" +
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
            "          <input type=\"text\" aria-label=\"Find or create a branch…\" id=\"context-commitish-filter-field\" class=\"js-filterable-field js-navigation-enable\" placeholder=\"Find or create a branch…\">\n" +
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
            "          <form accept-charset=\"UTF-8\" action=\"/code4craft/webmagic/branches\" class=\"js-create-branch select-menu-item select-menu-new-item-form js-navigation-item js-new-item-form\" method=\"post\"><div style=\"margin:0;padding:0;display:inline\"><input name=\"authenticity_token\" type=\"hidden\" value=\"i4/tXwrpqoMtPPKJTN4eSSPnFfrSzZkuIkeP//SUW34=\" /></div>\n" +
            "            <span class=\"octicon octicon-git-branch-create select-menu-item-icon\"></span>\n" +
            "            <div class=\"select-menu-item-text\">\n" +
            "              <h4>Create branch: <span class=\"js-new-item-name\"></span></h4>\n" +
            "              <span class=\"description\">from ‘master’</span>\n" +
            "            </div>\n" +
            "            <input type=\"hidden\" name=\"name\" id=\"name\" class=\"js-new-item-value\">\n" +
            "            <input type=\"hidden\" name=\"branch\" id=\"branch\" value=\"master\" />\n" +
            "            <input type=\"hidden\" name=\"path\" id=\"branch\" value=\"\" />\n" +
            "          </form> <!-- /.select-menu-item -->\n" +
            "\n" +
            "      </div> <!-- /.select-menu-list -->\n" +
            "\n" +
            "      <div class=\"select-menu-list select-menu-tab-bucket js-select-menu-tab-bucket\" data-tab-filter=\"tags\">\n" +
            "        <div data-filterable-for=\"context-commitish-filter-field\" data-filterable-type=\"substring\">\n" +
            "\n" +
            "\n" +
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
            "  <div class=\"breadcrumb\"><span class='repo-root js-repo-root'><span itemscope=\"\" itemtype=\"http://data-vocabulary.org/Breadcrumb\"><a href=\"/code4craft/webmagic\" data-branch=\"master\" data-direction=\"back\" data-pjax=\"true\" itemscope=\"url\"><span itemprop=\"title\">webmagic</span></a></span></span><span class=\"separator\"> / </span><form action=\"/code4craft/webmagic/new/master\" class=\"js-new-blob-form tooltipped rightwards new-file-link\" method=\"post\" title=\"Create a new file here\"><span aria-label=\"Create a new file here\" class=\"js-new-blob-submit octicon octicon-file-add\" data-test-id=\"create-new-git-file\" role=\"button\"></span></form></div>\n" +
            "</div>\n" +
            "\n" +
            "\n" +
            "\n" +
            "<a href=\"/code4craft/webmagic/find/master\"\n" +
            "  data-hotkey=\"t\" style=\"display:none\" data-pjax>Show File Finder</a>\n" +
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
            "        <span class=\"css-truncate css-truncate-target\"><a href=\"/code4craft/webmagic/tree/master/webmagic-core\" class=\"js-directory-link\" id=\"39809e13bc65c3873f79570b81852d62-947dff73c2eda51ae629fa42d6ace984fa044db6\" title=\"webmagic-core\">webmagic-core</a></span>\n" +
            "      </td>\n" +
            "      <td class=\"message\">\n" +
            "        <span class=\"css-truncate css-truncate-target\"><a href=\"/code4craft/webmagic/commit/c17a31a21d342ddc4349417557bc8b63aba0ba07\" class=\"message\" data-pjax=\"true\" title=\"fix null pointe exception #26\">fix null pointe exception</a> <a href=\"https://github.com/code4craft/webmagic/issues/26\" class=\"issue-link\" title=\"Annotation extactor does not work\">#26</a></span>\n" +
            "      </td>\n" +
            "      <td class=\"age\"><span class=\"css-truncate css-truncate-target\"><time class=\"js-relative-date\" datetime=\"2013-09-08T06:09:49-07:00\" title=\"2013-09-08 06:09:49\">September 08, 2013</time></span></td>\n" +
            "    </tr>\n" +
            "    <tr class=\"alt\">\n" +
            "      <td class=\"icon\">\n" +
            "        <span class=\"octicon octicon-file-directory\"></span>\n" +
            "        <img alt=\"Octocat-spinner-32\" class=\"spinner\" height=\"16\" src=\"https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif\" width=\"16\" />\n" +
            "      </td>\n" +
            "      <td class=\"content\">\n" +
            "        <span class=\"css-truncate css-truncate-target\"><a href=\"/code4craft/webmagic/tree/master/webmagic-extension\" class=\"js-directory-link\" id=\"dc82c79bcb262e1942088502bb426876-5dd5a5a2f7e9aa32848ac323e26fb29e35117bce\" title=\"webmagic-extension\">webmagic-extension</a></span>\n" +
            "      </td>\n" +
            "      <td class=\"message\">\n" +
            "        <span class=\"css-truncate css-truncate-target\"><a href=\"/code4craft/webmagic/commit/c17a31a21d342ddc4349417557bc8b63aba0ba07\" class=\"message\" data-pjax=\"true\" title=\"fix null pointe exception #26\">fix null pointe exception</a> <a href=\"https://github.com/code4craft/webmagic/issues/26\" class=\"issue-link\" title=\"Annotation extactor does not work\">#26</a></span>\n" +
            "      </td>\n" +
            "      <td class=\"age\"><span class=\"css-truncate css-truncate-target\"><time class=\"js-relative-date\" datetime=\"2013-09-08T06:09:49-07:00\" title=\"2013-09-08 06:09:49\">September 08, 2013</time></span></td>\n" +
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
            "        <span class=\"css-truncate css-truncate-target\"><a href=\"/code4craft/webmagic/tree/master/webmagic-samples\" class=\"js-directory-link\" id=\"4284b70d4c5e11003fb292b0d0f7539f-3567f90bdc95fbfe3f18913c7c22c9cce3fe6798\" title=\"webmagic-samples\">webmagic-samples</a></span>\n" +
            "      </td>\n" +
            "      <td class=\"message\">\n" +
            "        <span class=\"css-truncate css-truncate-target\"><a href=\"/code4craft/webmagic/commit/e1b6b54097a6657cfe1c43bb99ba8b47518c455f\" class=\"message\" data-pjax=\"true\" title=\"update version for samples\">update version for samples</a></span>\n" +
            "      </td>\n" +
            "      <td class=\"age\"><span class=\"css-truncate css-truncate-target\"><time class=\"js-relative-date\" datetime=\"2013-09-03T20:07:28-07:00\" title=\"2013-09-03 20:07:28\">September 03, 2013</time></span></td>\n" +
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
            "        <span class=\"css-truncate css-truncate-target\"><a href=\"/code4craft/webmagic/tree/master/zh_docs\" class=\"js-directory-link\" id=\"bec3b859688b0bbdb94899b1a5b56441-66254ea2ec85e8cf79182bcfe540b699e7e4d206\" title=\"zh_docs\">zh_docs</a></span>\n" +
            "      </td>\n" +
            "      <td class=\"message\">\n" +
            "        <span class=\"css-truncate css-truncate-target\"><a href=\"/code4craft/webmagic/commit/ac4cd391707da1190744a3891af7c62424fd8d37\" class=\"message\" data-pjax=\"true\" title=\"update version\">update version</a></span>\n" +
            "      </td>\n" +
            "      <td class=\"age\"><span class=\"css-truncate css-truncate-target\"><time class=\"js-relative-date\" datetime=\"2013-09-04T05:37:42-07:00\" title=\"2013-09-04 05:37:42\">September 04, 2013</time></span></td>\n" +
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
            "        <span class=\"css-truncate css-truncate-target\"><a href=\"/code4craft/webmagic/blob/master/README.md\" class=\"js-directory-link\" id=\"04c6e90faac2675aa89e2176d2eec7d8-5624019f9b5112a3b9d061551c82bf610fbaad7a\" title=\"README.md\">README.md</a></span>\n" +
            "      </td>\n" +
            "      <td class=\"message\">\n" +
            "        <span class=\"css-truncate css-truncate-target\"><a href=\"/code4craft/webmagic/commit/ac4cd391707da1190744a3891af7c62424fd8d37\" class=\"message\" data-pjax=\"true\" title=\"update version\">update version</a></span>\n" +
            "      </td>\n" +
            "      <td class=\"age\"><span class=\"css-truncate css-truncate-target\"><time class=\"js-relative-date\" datetime=\"2013-09-04T05:37:42-07:00\" title=\"2013-09-04 05:37:42\">September 04, 2013</time></span></td>\n" +
            "    </tr>\n" +
            "    <tr class=\"\">\n" +
            "      <td class=\"icon\">\n" +
            "        <span class=\"octicon octicon-file-text\"></span>\n" +
            "        <img alt=\"Octocat-spinner-32\" class=\"spinner\" height=\"16\" src=\"https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif\" width=\"16\" />\n" +
            "      </td>\n" +
            "      <td class=\"content\">\n" +
            "        <span class=\"css-truncate css-truncate-target\"><a href=\"/code4craft/webmagic/blob/master/pom.xml\" class=\"js-directory-link\" id=\"600376dffeb79835ede4a0b285078036-4fdfeee1be6d6430c6e402b036df6c6947f0d4da\" title=\"pom.xml\">pom.xml</a></span>\n" +
            "      </td>\n" +
            "      <td class=\"message\">\n" +
            "        <span class=\"css-truncate css-truncate-target\"><a href=\"/code4craft/webmagic/commit/2e8cf0a3dd27503423afe0bc8f3600bcf8ac832b\" class=\"message\" data-pjax=\"true\" title=\"将单元测试fork独立的JVM来跑。避免少数情况默认maven开的JVM堆太小。\">将单元测试fork独立的JVM来跑。避免少数情况默认maven开的JVM堆太小。</a></span>\n" +
            "      </td>\n" +
            "      <td class=\"age\"><span class=\"css-truncate css-truncate-target\"><time class=\"js-relative-date\" datetime=\"2013-09-04T09:30:10-07:00\" title=\"2013-09-04 09:30:10\">September 04, 2013</time></span></td>\n" +
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
            "        &lt;version&gt;0.3.0&lt;/version&gt;\n" +
            "    &lt;/dependency&gt;\n" +
            "    &lt;dependency&gt;\n" +
            "        &lt;groupId&gt;us.codecraft&lt;/groupId&gt;\n" +
            "        &lt;artifactId&gt;webmagic-extension&lt;/artifactId&gt;\n" +
            "        &lt;version&gt;0.3.0&lt;/version&gt;\n" +
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
            "<div class=\"highlight\"><pre>    <span class=\"kd\">public</span> <span class=\"kd\">class</span> <span class=\"nc\">OschinaBlogPageProcesser</span> <span class=\"kd\">implements</span> <span class=\"n\">PageProcessor</span> <span class=\"o\">{</span>\n" +
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
            "            <span class=\"n\">Spider</span><span class=\"o\">.</span><span class=\"na\">create</span><span class=\"o\">(</span><span class=\"k\">new</span> <span class=\"n\">OschinaBlogPageProcesser</span><span class=\"o\">())</span>\n" +
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
            "<div class=\"highlight\"><pre>    <span class=\"nd\">@TargetUrl</span><span class=\"o\">(</span><span class=\"s\">\"http://my.oschina.net/flashsword/blog/\\\\d+\"</span><span class=\"o\">)</span>\n" +
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
            "      <li>&copy; 2013 <span title=\"0.08765s from github-fe120-cp1-prd.iad.github.net\">GitHub</span>, Inc.</li>\n" +
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
            "    \n" +
            "  </body>\n" +
            "</html>\n" +
            "\n";
    @Override
    public Page download(Request request, Task task) {
        Page page = new Page();
        page.setHtml(new Html(html));
        page.setRequest(new Request("https://github.com/code4craft/webmagic"));
        page.setUrl(new PlainText("https://github.com/code4craft/webmagic"));
        return page;
    }

    @Override
    public void setThread(int threadNum) {
    }
}
