/** Classe demone di esempio che semplicemente prende un intero e lo incrementa
 * @author Luca De Franceschi
 * @version 1.0, Mar 12 November 2013
 *
 */
class Daemon extends Thread {
	/** Intero su cui operare */
	public int i = 0;
	
	/** Costruttore della classe che setta la classe a demone. */
	public Daemon() { setDaemon(true); }
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		// ciclo infinito
		while(true) { ++i; if (i>1000000) i=0; }
	}
}

/** Classe di prova che estende Thread e richiama un demone
 * @author Luca De Franceschi
 * @version 1.0, Mar 12 November 2013
 *
 */
public class D extends Thread {
	/** Demone interno alla classe. */
	Daemon dm = new Daemon();
	
	/** Variabile intera da incrementare */
	private int j = 0;
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run() {	// start del daemon
		dm.start();
		while (j<100) {
			++j;
			System.out.print(dm.i + " ");	// servizio fornito dal demone
		}
	}
	/** Metodo main che crea il demone e lo fa partire.s
	 * @param args argomenti opzionali
	 */
	public static void main(String[] args) {
		D d = new D(); d.start();
	}

}
