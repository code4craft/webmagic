package us.codecraft.webmagic.proxy;

/**
 * 
 */

public class Proxy {

	private ProxyHost proxyHost;
	private String user;
	private String password;

	public Proxy(ProxyHost proxyHost, String user, String password) {
		this.proxyHost = proxyHost;
		this.user = user;
		this.password = password;
	}

	public Proxy(ProxyHost proxyHost) {
		this.proxyHost = proxyHost;
	}

	public ProxyHost getProxyHost() {
		return proxyHost;
	}

	public void setProxyHost(ProxyHost proxyHost) {
		this.proxyHost = proxyHost;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
