import java.rmi.*;

public interface I extends Serializable {
	String get();
	void set(String n);
	void stampa();
}

public class IImpl implements I {
	private String s;
	IImpl(String n) { s = n; }
	public String get() { return s; }
	public void set(String n) { s = n; }
	public void stampa() { System.out.println(s); } 
}

public interface C extends Remote {
	I get() throws RemoteException;
	void set(I i) throws RemoteException;
	C g() throws RemoteException;
	void k() throws RemoteException;
	void stampa() throws RemoteException;
}

public class CImpl extends UnicastRemoteObject implements C {
	private I x = new IImpl("GIALLO");
	CImpl() throws RemoteException {}
	public synchronized I get() throws RemoteException { return x; }

	public synchronized void set(I i) throws RemoteException {
		x.set(i.get());
	}

	public synchronized C g() throws RemoteException {
		x.set("ROSSO"); return this;
	}

	public void k() { x.set("VERDE"); }

	public synchronized void stampa() { System.out.println(x.get()); }
}