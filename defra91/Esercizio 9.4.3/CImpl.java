import java.rmi.*;

public interface I extends Serializable {
	int get();
	void set(int n);
}

class IImpl implements I {
	private int n;
	IImpl(int n) { this.n = n; }
	public int get() { return n; }
	public void set(int n) { this.n = n; }
}

public interface C extends Remote {
	void aggiungi(I i) throws RemoteException;
	I get(int i) throws RemoteException;
	String stampa() throws RemoteException;
}

public class CImpl extends UnicastRemoteObject implements C {
	private I[] v = new I[10];
	private int top = 0;
	CImpl() throws RemoteException {}

	public synchronized void aggiungi(I i) throws RemoteException {
		v[top++] = i;
		if (top == v.lenght) {
			I[] temp = new I[v.lenght*2];
			for (int j=0; j<top; j++) temp[j] = v[j];
			v = temp;
		}	
	}

	public synchronized I get(int i) throws RemoteException {
		for (int j=0; j<top; j++)
			if (v[j].get() == i) return v[j];
		return null;
	}
}
