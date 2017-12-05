package us.codecraft.platform.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author  :王龙
 * @Description 网络服务工具类
 * @Data 2017/11/15 11:01
 * @Modified By：
 */
public class HttpProviderUtils {
    static Logger logger = LoggerFactory.getLogger(HttpProviderUtils.class);
    static String CHINAZ= "http://ip.chinaz.com";
    static Pattern IPV4_PATTERN = Pattern.compile("\\<dd class\\=\"fz24\">(.*?)\\<\\/dd>");

    /**
     * 获取本地网络的外网IP
     *
     * @return ：java.lang.String
     * @author : 王龙
     * @Description ：
     * @params ：[]
     * @Date ：2017/11/15 11:03
     */
    public static String getIPV4() {
        String ip = "";
        StringBuilder inputLine = new StringBuilder();
        String read = "";
        URL url = null;
        HttpURLConnection urlConnection = null;
        BufferedReader in = null;
        try {
            url = new URL(CHINAZ);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            while ((read = in.readLine()) != null) {
                inputLine.append(read + "\r\n");
            }
        } catch (MalformedURLException e) {
            logger.error("url地址有误:{}",url,e);
        } catch (IOException e) {
            logger.error("url请求失败：{}",url,e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Matcher m = IPV4_PATTERN.matcher(inputLine.toString());
        if (m.find()) {
            String ipstr = m.group(1);
            ip = ipstr;
        }
        return ip;
    }
}
