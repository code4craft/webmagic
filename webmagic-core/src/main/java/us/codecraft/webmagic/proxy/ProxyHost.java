package us.codecraft.webmagic.proxy;

/**
 * @author code4crafter@gmail.com
 *         Date: 17/3/18
 *         Time: 下午12:04
 */
public class ProxyHost {

    private String host;

    private int port;

    public String getHost() {
        return host;
    }

    public ProxyHost(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
