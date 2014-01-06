package appello;
import java.rmi.*;
import java.net.MalformedURLException;

public class program2 {
	public static void main(String[] args) {
		try {
			O o = (O) Naming.lookup("pippo");
			A.print(o.n());
			// ** PUNTO 1 **
			A.print(o.n1());
			synchronized(o) {
				o.print();
			}
		}
		catch (RemoteException e) {}
		catch (MalformedURLException e) {}
		catch (NotBoundException e) {}
	}
}