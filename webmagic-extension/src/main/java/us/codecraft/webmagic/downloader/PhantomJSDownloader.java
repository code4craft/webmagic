package us.codecraft.webmagic.downloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.selector.PlainText;

import java.io.*;

/**
 * this downloader is used to download pages which need to render the javascript
 *
 * @author dolphineor@gmail.com
 * @version 0.5.3
 */
public class PhantomJSDownloader extends AbstractDownloader {
    private static final Logger logger = LoggerFactory.getLogger(PhantomJSDownloader.class);
    private static String crawlJsPath;
    private static String phantomJsCommand = "phantomjs"; // default

    public PhantomJSDownloader() {
        this.initPhantomjsCrawlPath();
    }

    /**
     * 添加新的构造函数，支持phantomjs自定义命令
     * <p>
     * example:
     * phantomjs.exe 支持windows环境
     * phantomjs --ignore-ssl-errors=yes 忽略抓取地址是https时的一些错误
     * /usr/local/bin/phantomjs 命令的绝对路径，避免因系统环境变量引起的IOException
     *
     * @param phantomJsCommand phantomJsCommand
     */
    public PhantomJSDownloader(String phantomJsCommand) {
        this.initPhantomjsCrawlPath();
        PhantomJSDownloader.phantomJsCommand = phantomJsCommand;
    }

    /**
     * 新增构造函数，支持crawl.js路径自定义，因为当其他项目依赖此jar包时，runtime.exec()执行phantomjs命令时无使用法jar包中的crawl.js
     * <pre>
     * crawl.js start --
     *
     *   var system = require('system');
     *   var url = system.args[1];
     *
     *   var page = require('webpage').create();
     *   page.settings.loadImages = false;
     *   page.settings.resourceTimeout = 5000;
     *
     *   page.open(url, function (status) {
     *       if (status != 'success') {
     *           console.log("HTTP request failed!");
     *       } else {
     *           console.log(page.content);
     *       }
     *
     *       page.close();
     *       phantom.exit();
     *   });
     *
     * -- crawl.js end
     * </pre>
     * 具体项目时可以将以上js代码复制下来使用
     * <p>
     * example:
     * new PhantomJSDownloader("/your/path/phantomjs", "/your/path/crawl.js");
     *
     * @param phantomJsCommand phantomJsCommand
     * @param crawlJsPath      crawlJsPath
     */
    public PhantomJSDownloader(String phantomJsCommand, String crawlJsPath) {
        PhantomJSDownloader.phantomJsCommand = phantomJsCommand;
        PhantomJSDownloader.crawlJsPath = crawlJsPath;
    }

    private void initPhantomjsCrawlPath() {
        PhantomJSDownloader.crawlJsPath = new File(this.getClass().getResource("/").getPath()).getPath()
                + System.getProperty("file.separator") + "crawl.js ";
    }

    @Override
    public Page download(Request request, Task task) {
        if (logger.isInfoEnabled()) {
            logger.info("downloading page: " + request.getUrl());
        }

        Page page = Page.fail(request);
        try {
            String content = getPage(request);
            if (!content.contains("HTTP request failed")) {
                page.setDownloadSuccess(true);
                page.setRawText(content);
                page.setUrl(new PlainText(request.getUrl()));
                page.setRequest(request);
                page.setStatusCode(200);
            }
            onSuccess(page, task);
        } catch (Exception e) {
            onError(page, task, e);
            logger.warn("download page {} error", request.getUrl(), e);
        }
        return page;
    }

    @Override
    public void setThread(int threadNum) {
        // ignore
    }

    protected String getPage(Request request) throws Exception {
        String url = request.getUrl();
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec(phantomJsCommand + " " + crawlJsPath + " " + url);
        InputStream is = process.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            builder.append(line).append("\n");
        }
        return builder.toString();
    }
}
