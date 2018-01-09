package us.codecraft.webmagic.main;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.samples.IteyeBlog;
import us.codecraft.webmagic.model.samples.News163;
import us.codecraft.webmagic.model.samples.OschinaBlog;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.MultiPagePipeline;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author code4crafter@gmail.com <br>
 */
public class QuickStarter {

    private static Map<String, Class> clazzMap;

    private static Map<String, String> urlMap;

    private static void init(){
        clazzMap = new LinkedHashMap<String, Class>();
        clazzMap.put("1", OschinaBlog.class);
        clazzMap.put("2", IteyeBlog.class);
        clazzMap.put("3", News163.class);
        urlMap = new LinkedHashMap<String, String>();
        urlMap.put("1", "http://my.oschina.net/flashsword/blog");
        urlMap.put("2", "http://flashsword20.iteye.com/");
        urlMap.put("3", "http://news.163.com/");
    }

    public static void main(String[] args) {
        init();
        String key = null;
        key = readKey(key);
        System.out.println("The demo started and will last 20 seconds...");
        //Start spider
        OOSpider.create(Site.me(), clazzMap.get(key)).addUrl(urlMap.get(key)).addPipeline(new MultiPagePipeline()).addPipeline(new ConsolePipeline()).runAsync();

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("The demo stopped!");
        System.out.println("To more usage, try to customize your own Spider!");
        System.exit(0);
    }

    private static String readKey(String key) {
        Scanner stdin = new Scanner(System.in);
        System.out.println("Choose a Spider demo:");
        for (Map.Entry<String, Class> classEntry : clazzMap.entrySet()) {
            System.out.println(classEntry.getKey()+"\t" + classEntry.getValue() + "\t" + urlMap.get(classEntry.getKey()));
        }
        while (key == null) {
            key = stdin.nextLine();
            if (clazzMap.get(key) == null) {
                System.out.println("Invalid choice!");
                key = null;
            }
        }
        return key;
    }
}
