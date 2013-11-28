package us.codecraft.webmagic.example;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;

/**
 * @author code4crafter@gmail.com
 * @since 0.4.1
 */
public class AppStore {

    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "$..trackName")
    private String trackName;

    @ExtractBy(type = ExtractBy.Type.JsonPath, value = "$..description")
    private String description;

    public static void main(String[] args) {
        AppStore appStore = OOSpider.create(Site.me(), AppStore.class).<AppStore>get("http://itunes.apple.com/lookup?id=653350791&country=cn&entity=software");
        System.out.println(appStore.trackName);
        System.out.println(appStore.description);
    }
}
