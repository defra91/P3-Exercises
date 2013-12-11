import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;



public class C2 extends UnicastRemoteObject implements C22 {

	MyLock lock;
	C2(MyLock l) throws RemoteException{lock = l;}
	
	public void m() throws RemoteException{
		for(int i = 0; i<100; i++){
			System.out.println("C");
		}
		lock.finito2 = true;
		synchronized(lock){
			if(lock.finito1 == true){ lock.notify();}
			else{
				while(lock.finito1 == false){try {
					lock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}}
			}
		}
		for(int i= 0; i<100; i++){
			System.out.println("D");
		}
		
		
	}
	
	
}