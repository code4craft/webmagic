package us.codecraft.webmagic.utils;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @author code4crafer@gmail.com
 * @since 0.5.0
 */
public abstract class IPUtils {

    public static String getFirstNoLoopbackIPAddresses() throws SocketException {

        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();

        InetAddress localAddress = null;
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
            while (inetAddresses.hasMoreElements()) {
                InetAddress address = inetAddresses.nextElement();
                if (!address.isLoopbackAddress() && !Inet6Address.class.isInstance(address)) {
                    return address.getHostAddress();
                } else if (!address.isLoopbackAddress()) {
                    localAddress = address;
                }
            }
        }

        return localAddress.getHostAddress();
    }

}
