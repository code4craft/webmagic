package us.codecraft.webmagic.worker;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import us.codecraft.express.WebServer;

/**
 * @author code4crafter@gmail.com
 */
public class Bootstrap {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(
                new String[]{"classpath*:/spring/applicationContext-*.xml"}
        );
        WebServer webServer = classPathXmlApplicationContext.getBean(WebServer.class);
        webServer.port(11111).start();
    }

}
