package appello;
import java.rmi.*;
import java.rmi.server.*;
import java.io.*;

interface O extends Remote {
	public String m() throws RemoteException;
	public String n() throws RemoteException;
	public O m1() throws RemoteException;
	public O n1() throws RemoteException;
	public String stampa() throws RemoteException;
	public void print() throws RemoteException;
}

public class OImpl extends UnicastRemoteObject implements O {
	String s = "NERO";

	public OImpl() throws RemoteException {}

	public String m() throws RemoteException { return s; }
	public String n() throws RemoteException { s = "BLUE"; return s; }
	public O m1() throws RemoteException { return this; }
	public O n1() throws RemoteException { s = "GIALLO"; return new OImpl(); }
	public String stampa() throws RemoteException { return s; }
	public void print() throws RemoteException { System.out.println(s); }
}

class A {
	static void print(Serializable s) { System.out.println(s); }
	static void print(O r) throws RemoteException { System.out.println(r.stampa()); }
}