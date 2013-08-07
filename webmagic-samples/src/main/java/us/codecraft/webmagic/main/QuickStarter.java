package us.codecraft.webmagic.main;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.samples.IteyeBlog;
import us.codecraft.webmagic.model.samples.News163;
import us.codecraft.webmagic.model.samples.OschinaBlog;
import us.codecraft.webmagic.pipeline.ConsolePipeline;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author code4crafter@gmail.com <br>
 * @date: 13-8-7 <br>
 * Time: 下午9:24 <br>
 */
public class QuickStarter {

    public static void main(String[] args) {
        Map<String, Class> clazzMap = new LinkedHashMap<String, Class>();
        clazzMap.put("1", OschinaBlog.class);
        clazzMap.put("2", IteyeBlog.class);
        clazzMap.put("3", News163.class);
        Map<String, String> urlMap = new LinkedHashMap<String, String>();
        urlMap.put("1", "http://my.oschina.net/flashsword/blog");
        urlMap.put("2", "http://flashsword20.iteye.com/");
        urlMap.put("3", "http://news.163.com/");
        Scanner stdin = new Scanner(System.in);
        String key = null;
        System.out.println("Choose a Spider demo:");
        for (Map.Entry<String, Class> classEntry : clazzMap.entrySet()) {
            System.out.println(classEntry.getKey()+"\t" + classEntry.getValue() + "\t" + urlMap.get(classEntry.getKey()));
        }
        while (key == null) {
            key = new String(stdin.nextLine());
            if (clazzMap.get(key) == null) {
                System.out.println("Invalid choice!");
                key = null;
            }
        }
        System.out.println("The demo started and will last 60 seconds...");

        //Start spider
        OOSpider.create(Site.me().addStartUrl(urlMap.get(key)), clazzMap.get(key)).pipeline(new ConsolePipeline()).runAsync();


        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("The demo stopped!");
        System.exit(0);
    }
}
