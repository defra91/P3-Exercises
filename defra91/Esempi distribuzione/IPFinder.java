import java.net.*;

public class IPFinder {
	public static void main(String[] args) {
		String host = "java.sun.com";
		try {
			InetAddress localadd = InetAddress.getLocalHost();
			InetAddress remoteadd = InetAddress.getByName(host);
			System.out.println("Remote -> " + remoteadd + ", local -> " + localadd);
		} catch(UnknownHostException e) {
			System.out.println(host + " sconosciuto");
		}
	}
}
