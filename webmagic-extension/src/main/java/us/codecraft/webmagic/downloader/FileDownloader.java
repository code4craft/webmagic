package us.codecraft.webmagic.downloader;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.PlainText;

import java.io.*;

/**
 * 使用缓存到本地的文件来模拟下载，可以在Spider框架中仅进行抽取工作。<br>
 * @author code4crafer@gmail.com
 *         Date: 13-6-24
 *         Time: 上午7:24
 */
public class FileDownloader implements Downloader {

    private String path = "/data/temp/webmagic/";

    private Downloader downloaderWhenFileMiss;

    private Logger logger = Logger.getLogger(getClass());

    public FileDownloader() {
        this("/data/temp/webmagic/", null);
    }

    public FileDownloader(String path) {
        this(path, null);
    }

    public FileDownloader(String path, Downloader downloaderWhenFileMiss) {
        if (!path.endsWith("/")&&!path.endsWith("\\")){
            path+="/";
        }
        this.path = path;
        this.downloaderWhenFileMiss = downloaderWhenFileMiss;
    }

    @Override
    public Page download(Request request, Task task) {
        String path = this.path + "/" + task.getUUID() + "/";
        Page page = null;
        try {
            final File file = new File(path + DigestUtils.md5Hex(request.getUrl()));
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = null;
            line = bufferedReader.readLine();
            if (line.equals("url:\t" + request.getUrl())) {
                final String html = getHtml(bufferedReader);
                page = new Page();
                page.setRequest(request);
                page.setUrl(PlainText.create(request.getUrl()));
                page.setHtml(Html.create(html));
            }
        } catch (IOException e) {
            if (e instanceof FileNotFoundException) {
                logger.info("File not exist for url " + request.getUrl());
            } else {
                logger.warn("File read error for url " + request.getUrl(), e);
            }
        }
        if (page == null) {
            page = downloadWhenMiss(request, task);
        }
        return page;
    }

    @Override
    public void setThread(int thread) {

    }

    private String getHtml(BufferedReader bufferedReader) throws IOException {
        String line;
        StringBuilder htmlBuilder= new StringBuilder();
        line = bufferedReader.readLine();
        line = StringUtils.removeStart(line, "html:\t");
        htmlBuilder.append(line);
        while ((line=bufferedReader.readLine())!=null){
            htmlBuilder.append(line);
        }
        return htmlBuilder.toString();
    }

    private Page downloadWhenMiss(Request request, Task task) {
        Page page = null;
        if (downloaderWhenFileMiss != null) {
            page = downloaderWhenFileMiss.download(request, task);
        }
        return page;
    }
}
