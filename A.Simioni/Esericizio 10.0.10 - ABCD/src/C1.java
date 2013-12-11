import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class C1 extends UnicastRemoteObject implements C11 {

	MyLock lock;
	
	C1(MyLock l) throws RemoteException{lock = l;}
	
	public void m() throws RemoteException{
		for(int i = 0; i<100; i++){
			System.out.println("A");
		}
		lock.finito1=true;
		synchronized(lock){
			if(lock.finito2 == true){ lock.notify();}
			else{
				while(lock.finito2 == false){try {
					lock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}}
			}
		}
		for(int i= 0; i<100; i++){
			System.out.println("B");
		}
		
		
	}
	
	
}
