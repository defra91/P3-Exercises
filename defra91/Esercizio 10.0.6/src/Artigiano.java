
public class Artigiano extends Thread {
	Fornitore rif;
	public String nomeArtigiano;
	boolean attesa = false;
	public Artigiano(String n, Fornitore f) {
		nomeArtigiano = n;
		rif = f;
	}
	
	private class attendi extends Thread {
		public void run() {
			attesa = true;
			Risorsa r = rif.produci(nomeArtigiano);
			attesa = false;
			System.out.println("Il fornitore ha fornito correttamente la risorsa " + r.getTipo() + " all'artigiano " + nomeArtigiano); 
			synchronized(rif) {
				rif.notifyAll();
			}
			
		}
	}
	
	private class lavora extends Thread {
		public void run() {
			if(attesa == true) {
				for (int i=0; i<100; i++) {
					 System.out.println(nomeArtigiano + " dice: \"Sto lavorando...\" " + i);
					 try {
						 this.sleep(10);
					 } catch(InterruptedException e) {}
				 }
			}
			synchronized(rif) {
				while (attesa == true) {
					try {
						rif.wait();
					} catch(InterruptedException e) {}
				}
			}
		}
	}
	
	public void run() {
		while (!rif.risorseEsaurite()) {
			attendi a = new attendi();
			lavora l = new  lavora();
			a.start(); l.start();
		}
	}
}
