package appello;
import java.rmi.*;
import java.net.MalformedURLException;

class Server {
	public static void main(String[] a) throws RemoteException {
		OImpl o = new OImpl();
		try {
			Naming.rebind("rmi://localhost/pippo",o);
			System.out.println("finito server");
		} catch(RemoteException e) { System.out.println("Errore di connessione!"); }
		catch(MalformedURLException e) { System.out.println("MalformedURL!"); }
	}
}