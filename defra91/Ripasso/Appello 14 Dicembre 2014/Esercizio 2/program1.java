package appello;
import java.rmi.*;
import java.net.MalformedURLException;

public class program1 {
	public static void main(String[] args) {
		try {
			O o = (O) Naming.lookup("pippo");
			A.print(o.m());
			// ** PUNTO 1 **
			A.print(o.m1());
			// ** PUNTO 2 **
			synchronized(o) {
				o.print();
			}
		}
		catch (RemoteException e) {}
		catch (MalformedURLException e) {}
		catch (NotBoundException e) {}
	}
}