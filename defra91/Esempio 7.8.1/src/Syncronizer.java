class T1 extends Thread {
	int i=0;
	T2 t2;
	T1(T2 t2) {
		this.t2 = t2;
	}
	
	public void run() {
		while(i<1000) {
			i++;
			if (i == 200)
				synchronized(t2) { t2.sospendi = true; }
			if (i == 600)
				synchronized(t2) {
					t2.sospendi = false;
					t2.notify();
				}
		}
	}
}

class T2 extends Thread {
	boolean sospendi = false;
	public void run() {
		try {
			while (true) {
				synchronized(this) {
					while (sospendi == true) wait();
				}
			
				System.out.println("topolino");
				Thread.sleep(1000);
			}
		}
		catch(InterruptedException e) {}
	}
}


public class Syncronizer {
	public static void main(String[] args) {
		T2 t2 = new T2();
		T1 t1 = new T1(t2);
		t1.start(); t2.start();
	}
}
