package us.codecraft.webmagic.proxy;

import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;

import org.apache.http.HttpHost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ClassName:ProxyUtil
 * 
 * @see
 * @author ch
 * @version Ver 1.0
 * @Date 2014-2-16 下午04:20:07
 */
public class ProxyUtil {
	// TODO 改为单例
	private static InetAddress localAddr;
	private static final Logger logger = LoggerFactory.getLogger(ProxyUtil.class);
	static {
		init();
	}

	private static void init() {
		Enumeration<InetAddress> localAddrs;
		try {
			NetworkInterface ni = NetworkInterface.getByName("eth7");
			if (ni == null) {
				logger.error("choose NetworkInterface\n" + getNetworkInterface());
			}
			localAddrs = ni.getInetAddresses();
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
			logger.error("cannot get local ip");
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
		String networkInterfaceName = "";
		Enumeration<NetworkInterface> enumeration = null;
		try {
			enumeration = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e1) {
			e1.printStackTrace();
		}
		while (enumeration.hasMoreElements()) {
			NetworkInterface networkInterface = enumeration.nextElement();
			networkInterfaceName += networkInterface.toString() + '\n';
			Enumeration<InetAddress> addr = networkInterface.getInetAddresses();
			while (addr.hasMoreElements()) {
				networkInterfaceName += "\tip:" + addr.nextElement().getHostAddress() + "\n";
			}
		}
		return networkInterfaceName;
	}
}
