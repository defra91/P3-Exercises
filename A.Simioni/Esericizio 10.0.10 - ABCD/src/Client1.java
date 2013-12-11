import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


public class Client1 {
	private static final String Host = "localhost";
	/**
	 * @param args
	 * @throws NotBoundException 
	 * @throws RemoteException 
	 * @throws MalformedURLException 
	 */
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		C11 c = (C11)Naming.lookup("rmi://"+Host+"/PIPPO");
		c.m();

	}

}
 	