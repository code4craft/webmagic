package us.codecraft.platform.utils;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

/**
 * 参数、配置文件 解析工具类，基于apache configuration
 *
 * @version 创建时间：2017年6月9日 下午2:48:19
 */
public class PropertiesUtil {
    private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    /**
     * 文件转配置节点信息
     *
     * @param fileName 文件名
     * @return 配置信息
     * @return: PropertiesConfiguration
     * @author: 刘太信
     * @version 创建时间：2017年6月9日 下午2:48:24
     */
    public static PropertiesConfiguration parseFile(String fileName) {
        return parseFile(fileName, "utf-8", ',');
    }

    /**
     * 文件转配置节点信息
     *
     * @param fileName 文件名
     * @param encode   编码
     * @param s        分隔符
     * @return 配置信息
     * @return: PropertiesConfiguration
     * @author: 刘太信
     * @version 创建时间：2017年6月9日 下午2:49:41
     */
    public static PropertiesConfiguration parseFile(String fileName, String encode, char s) {
        // 生成输入流
        InputStream ins = PropertiesUtil.class.getResourceAsStream("/" + fileName);
        try {
            try {
                // 生成properties对象
                PropertiesConfiguration p = new PropertiesConfiguration();
                p.setListDelimiter(s);
                p.load(ins, encode);
                return p;
            } finally {
                if (null != ins) {
                    ins.close();
                }
            }
        } catch (Exception e) {
            logger.error("解析" + fileName + "文件错误:" + e.getMessage());
        }
        return new PropertiesConfiguration();
    }
}
