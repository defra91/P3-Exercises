import java.util.Vector;

public class Stop extends Thread {

	private Vector<Automobile> auto;
	private Passaggio p;
	String nome;

	Stop(Passaggio pass,String n) {
		auto = new Vector<Automobile>();
		p = pass;
		nome = n;
		new GeneraAuto().start();
	}

	public void run() {
		while(true){
		synchronized (auto) {
			while (auto.isEmpty()) {
				try {
					auto.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		synchronized (p) {
			synchronized (auto) {
				while (!auto.isEmpty()) {
					Automobile a= auto.remove(0);
					System.out.println("Passata l'auto num: " + a.num + " della strada: "+ a.daDove);
					try {
						sleep((int)Math.random() * 70);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("Finito il passaggio di auto da " + nome);
			}
		}
		}
	}
	
	private class GeneraAuto extends Thread{
		public void run(){
			for(int i =0; i<100; i++){
				synchronized(auto){
					auto.add(new Automobile(i,nome));
					auto.notify();
				
				}
				
				try {
					sleep((int) Math.random()*364);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	
	}
	

}
