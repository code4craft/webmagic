package us.codecraft.platform.contants;

/**
 * DownloaderType 下载类型
 *
 * @author 王龙
 * @date 2017年10月30日 下午2:34:43
 */
public enum DownloaderType {
    PHANTOMJS("phantomjs渲染页面", "us.codecraft.platform.download.selenium.webdriver.phantomjs.PhantomJsSeleniumSubDownloader", 1),
    DOWNLOADER("普通页面", "us.codecraft.platform.download.http.HttpClientSubDownloader", 2),
    CDP4J("chrome浏览器", "us.codecraft.platform.download.selenium.cdp4j.CdpSeleniumSubDownloader", 3);

    private String name;
    private String className;
    private int index;

    private DownloaderType(String name, String className, int index) {
        this.name = name;
        this.className = className;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public String getClassName() {
        return className;
    }

    public int getIndex() {
        return index;
    }

    public static String getClassName(int index) {
        for (DownloaderType type : DownloaderType.values()) {
            if (type.getIndex() == index) {
                return type.getClassName();
            }
        }
        return "";
    }

    @Override
    public String toString() {
        return this.getName() + ":" + this.getClassName();
    }
}
