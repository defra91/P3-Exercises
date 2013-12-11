import java.rmi.*;
public class Server {
	private static final String HOST ="localhost";
	public static void main(String args[])throws Exception{
		MyLock l = new MyLock();
		C1 cuno = new C1(l);
		C2 cdue = new C2(l);
		String rmiObjName = "rmi://" +HOST+"/PIPPO";
		String rmiObjName2 = "rmi://" +HOST+"/PLUTO";
		
		
		Naming.rebind(rmiObjName, cuno);
		Naming.rebind(rmiObjName2, cdue);
		
	}
}
