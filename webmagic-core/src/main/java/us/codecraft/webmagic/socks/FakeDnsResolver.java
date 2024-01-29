package us.codecraft.webmagic.socks;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.http.conn.DnsResolver;

public class FakeDnsResolver implements DnsResolver{
	
	@Override
	public InetAddress[] resolve(String host) throws UnknownHostException {
		// Return some fake DNS record for every request, we won't be using it
		return new InetAddress[] { InetAddress.getByAddress(new byte[] { 1, 1, 1, 1 }) };
	}

}
