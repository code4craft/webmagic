package us.codecraft.webmagic.proxy;

/**
 * 
 */

public class Proxy {

	private ProxyHost proxyHost;
	private String username;
	private String password;

	public Proxy(ProxyHost proxyHost, String username, String password) {
		this.proxyHost = proxyHost;
		this.username = username;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
