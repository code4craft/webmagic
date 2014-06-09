package us.codecraft.webmagic.utils;

import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.regex.Pattern;

import org.apache.http.HttpHost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Pooled Proxy Object
 * 
 * @author yxssfxwzy@sina.com <br>
 * @since 0.5.1
 */

public class ProxyUtils {
	private static InetAddress localAddr;
	private static String networkInterface = "eth7";

	private static final Logger logger = LoggerFactory.getLogger(ProxyUtils.class);
	static {
		init();
	}

	private static void init() {
		// first way to get local IP
		try {
			localAddr = InetAddress.getLocalHost();
			logger.info("local IP:" + localAddr.getHostAddress());
		} catch (UnknownHostException e) {
			logger.info("try again\n");
		}
		if (localAddr != null) {
			return;
		}
		// other way to get local IP
		Enumeration<InetAddress> localAddrs;
		try {
			// modify your network interface name
			NetworkInterface ni = NetworkInterface.getByName(networkInterface);
			if (ni == null) {
				return;
			}
			localAddrs = ni.getInetAddresses();
			if (localAddrs == null || !localAddrs.hasMoreElements()) {
				logger.error("choose NetworkInterface\n" + getNetworkInterface());
				return;
			}
			while (localAddrs.hasMoreElements()) {
				InetAddress tmp = localAddrs.nextElement();
				if (!tmp.isLoopbackAddress() && !tmp.isLinkLocalAddress() && !(tmp instanceof Inet6Address)) {
					localAddr = tmp;
					logger.info("local IP:" + localAddr.getHostAddress());
					break;
				}
			}
		} catch (Exception e) {
			logger.error("Failure when init ProxyUtil", e);
			logger.error("choose NetworkInterface\n" + getNetworkInterface());
		}
	}

	public static boolean validateProxy(HttpHost p) {
		if (localAddr == null) {
			logger.error("cannot get local IP");
			return false;
		}
		boolean isReachable = false;
		Socket socket = null;
		try {
			socket = new Socket();
			socket.bind(new InetSocketAddress(localAddr, 0));
			InetSocketAddress endpointSocketAddr = new InetSocketAddress(p.getAddress().getHostAddress(), p.getPort());
			socket.connect(endpointSocketAddr, 3000);
			logger.debug("SUCCESS - connection established! Local: " + localAddr.getHostAddress() + " remote: " + p);
			isReachable = true;
		} catch (IOException e) {
			logger.warn("FAILRE - CAN not connect! Local: " + localAddr.getHostAddress() + " remote: " + p);
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					logger.warn("Error occurred while closing socket of validating proxy", e);
				}
			}
		}
		return isReachable;
	}

	private static String getNetworkInterface() {

		String networkInterfaceName = ">>>> modify networkInterface in us.codecraft.webmagic.utils.ProxyUtils";
		Enumeration<NetworkInterface> enumeration = null;
		try {
			enumeration = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e1) {
			e1.printStackTrace();
		}
		while (enumeration.hasMoreElements()) {
			NetworkInterface networkInterface = enumeration.nextElement();

			Enumeration<InetAddress> addr = networkInterface.getInetAddresses();
			while (addr.hasMoreElements()) {
				String s = addr.nextElement().getHostAddress();
				Pattern IPV4_PATTERN = Pattern.compile("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$");
				if (s != null && IPV4_PATTERN.matcher(s).matches()) {
					networkInterfaceName += networkInterface.toString() + "IP:" + s + "\n\n";
				}
			}
		}
		return networkInterfaceName;
	}
}
