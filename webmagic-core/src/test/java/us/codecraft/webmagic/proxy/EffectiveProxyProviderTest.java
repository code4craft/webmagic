package us.codecraft.webmagic.proxy;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import org.junit.Test;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;

/**
 * Created by evan on 2018/8/6.
 */
public class EffectiveProxyProviderTest {

    public static final Task TASK = Site.me().toTask();

    public static final Page page = new Page();

    @Test
    public void test_get_proxy_process() throws Exception {
        while (true) {
            Proxy proxy = effectiveProxyProvider.getProxy(TASK);
            System.out.println("get proxy :"+proxy.getHost());
            effectiveProxyProvider.returnProxy(proxy, page, TASK);
            Thread.sleep(2000);
        }
    }

    private ProxyPageValidator proxyPageValidator = new ProxyPageValidator() {
        @Override public boolean proxyValid(Proxy proxy, Page page, Task task) {

            Random random = new Random();
            if (random.nextInt(10) < 5) {
                System.out.println("===remove===" + proxy.getHost());
                return false;
            }
            return true;
        }
    };

    private EffectiveProxyProvider effectiveProxyProvider = new EffectiveProxyProvider(proxyPageValidator, Lists.newArrayList(new Proxy("127.0.0.1", 1121))) {
        @Override public List<Proxy> addProxies() {
            System.out.println("===Expand===");
            return Lists.newArrayList(getRandomProxy(3));
        }
    };

    private List<Proxy> getRandomProxy(int count){
        Random random = new Random();
        List<Proxy> temp = Lists.newArrayList();
        for (int i = 0;i<count;i++) {
            temp.add(new Proxy("127.0.0." + random.nextInt(255), 123));
        }
        return temp;
    }

}