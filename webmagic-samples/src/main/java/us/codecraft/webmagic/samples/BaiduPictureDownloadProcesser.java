package us.codecraft.webmagic.samples;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BaiduPictureDownloadProcesser implements PageProcessor {
    ExecutorService executorService = Executors.newFixedThreadPool(10);

    private Site site = Site.me().setCycleRetryTimes(5).setRetryTimes(5).setSleepTime(500).setTimeOut(3 * 60 * 1000)
            .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0")
            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
            .setCharset("UTF-8");
    private final static Map<String, String> KEY_WORDS = new HashMap<>();

    static {
        //奶制品
        KEY_WORDS.put("牛奶", "牛奶");
        KEY_WORDS.put("奶酪", "奶酪");
        KEY_WORDS.put("酸奶", "酸奶");
        //哺乳动物肉
        KEY_WORDS.put("羊肉", "肉");
        KEY_WORDS.put("牛肉", "肉");
        KEY_WORDS.put("狗肉", "肉");
        KEY_WORDS.put("驴肉", "肉");
        KEY_WORDS.put("猪肉", "肉");
        //家禽肉
        KEY_WORDS.put("鸡", "家禽肉");
        KEY_WORDS.put("鸭", "家禽肉");
        KEY_WORDS.put("鹅", "家禽肉");
        //蛋类
        KEY_WORDS.put("鸡蛋", "蛋");
        KEY_WORDS.put("鸭蛋", "蛋");
        KEY_WORDS.put("鸽子蛋", "蛋");
        //蔬菜
        KEY_WORDS.put("冬瓜", "冬瓜");
        KEY_WORDS.put("西红柿", "西红柿");
        KEY_WORDS.put("苦瓜", "苦瓜");
        KEY_WORDS.put("青椒", "青椒");
        KEY_WORDS.put("胡萝卜", "胡萝卜");
        KEY_WORDS.put("南瓜", "南瓜");
        KEY_WORDS.put("玉米", "玉米");
        KEY_WORDS.put("秋葵", "秋葵");
        KEY_WORDS.put("西兰花", "西兰花");
        KEY_WORDS.put("生姜", "生姜");
        //水果
        KEY_WORDS.put("苹果", "苹果");
        KEY_WORDS.put("梨", "梨");
        KEY_WORDS.put("香蕉", "香蕉");
        KEY_WORDS.put("葡萄", "葡萄");
        KEY_WORDS.put("榴莲", "榴莲");
        KEY_WORDS.put("猕猴桃", "猕猴桃");
        KEY_WORDS.put("哈密瓜", "哈密瓜");
        KEY_WORDS.put("草莓", "草莓");
        KEY_WORDS.put("橘子", "橘子");
        KEY_WORDS.put("菠萝", "菠萝");
        KEY_WORDS.put("山楂", "山楂");
        KEY_WORDS.put("桂圆", "桂圆");
        //水产品
        KEY_WORDS.put("虾", "虾");
        KEY_WORDS.put("蟹", "蟹");
        KEY_WORDS.put("鱼", "鱼");
        KEY_WORDS.put("贝类", "贝类");
        KEY_WORDS.put("螺类", "螺类");
        KEY_WORDS.put("海参类", "海参类");
        //
        KEY_WORDS.put("豆皮", "豆皮");
        KEY_WORDS.put("豆腐脑", "豆腐脑");
        KEY_WORDS.put("豆干", "豆干");
        KEY_WORDS.put("豆腐", "豆腐");
        //坚果
        KEY_WORDS.put("腰果", "腰果");
        KEY_WORDS.put("开心果", "开心果");
        KEY_WORDS.put("核桃", "核桃");
        KEY_WORDS.put("葡萄干", "葡萄干");
        KEY_WORDS.put("夏威夷果", "夏威夷果");
    }

    @Override
    public void process(Page page) {
        List<String> url_list = new ArrayList<>();
        List<String> name_list = new ArrayList<>();
        JSONObject jsonObject = (JSONObject) JSONObject.parse(page.getRawText());
        JSONArray data = (JSONArray) jsonObject.get("data");
        for (int i = 0; i < data.size(); i++) {
            String url = (String) data.getJSONObject(i).get("thumbURL");
            String name = (String) data.getJSONObject(i).get("fromPageTitleEnc");
            if (url != null) {
                url_list.add(url);
                name_list.add(name);
            }
        }
        setUrls(url_list);
        setNames(name_list);

    }

    @Override
    public Site getSite() {
        return this.site;
    }

    private void downloadPicture(List<String> urlList, String key, String keyName) {
        URL url = null;
        for (int i = 0; i < urlList.size(); i++) {
            try {
                url = new URL(urlList.get(i));
                DataInputStream dataInputStream = new DataInputStream(url.openStream());
                String imageName = i + ".jpg";
                createDir("d:\\pic\\" + keyName);
                File file = new File("d:\\pic\\" + keyName);    //设置下载路径
                if (!file.isDirectory()) {
                    file.mkdirs();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(new File("d:\\pic\\" + keyName + "\\" + imageName.trim()));
                byte[] buffer = new byte[1024];
                int length;
                while ((length = dataInputStream.read(buffer)) > 0) {
                    fileOutputStream.write(buffer, 0, length);
                }
                dataInputStream.close();
                fileOutputStream.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        BaiduPictureDownloadProcesser downloadPicture = new BaiduPictureDownloadProcesser();
        for (Map.Entry<String, String> entry : KEY_WORDS.entrySet()) {
            List<String> urlList = new CopyOnWriteArrayList<String>();
            for (int i = 0; i < 2; i++) {   //控制爬取页数，一页10张图片
                String url = "https://image.baidu.com/search/acjson?tn=resultjson_com&ipn=rj&queryWord=" + entry.getKey() + "&word=" + entry.getKey() + "&pn=" + i * 10 + "0";
                Spider.create(new BaiduPictureDownloadProcesser())
                        .addUrl(url)
                        .run();
                urlList.addAll(urls);
            }
            downloadPicture.downloadPicture(urlList, entry.getKey(), entry.getValue());
        }

    }

    static List<String> urls;
    static List<String> names;

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    /**
     * 创建文件夹
     *
     * @param dir 当前文件夹
     */
    private void createDir(String dir) throws IOException {
        Path path = Paths.get(dir);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

    }
}
