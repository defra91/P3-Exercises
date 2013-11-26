/**
 * Questa classe consiste di un intero i che viene incrementato e decrementato tramite un ciclo
 * @author Luca De Franceschi
 * @version 1.0, 11 Novembre 2013
 *
 */
class C {
	/** Variabile indice */
	private int i=0;
	
	/** Riferimento a Object per il syncronized	 */
	public Object a;
	
	/** Costruttore ad un parametro che prende un oggetto di tipo Object
	 * @param a
	 */
	public C(Object a) { this.a = a; }
	
	/** Metodo che effettua in modo sincronizzato un incremento e un decremento */
	public void m() {
		synchronized (a) {
			for (int k=0; k<1000000; k++) i++;
			for (int k=0; k<1000000; k++) i--;
			
		}
	}
	
	/** Metodo che stampa in output la variabile i */
	public void print() { System.out.println(i + " "); }
}

/**
 * Questa classe si occupa di implementare il thread e di eseguirlo in modo sincronizzato
 * @author Luca
 * @version 1.0, 11 Novembre 2013
 */
class T extends Thread {
	/** Riferimento di tipo C */
	private C c;
	
	/** Costruttore del thread ad un parametro
	 * @param c riferimento ad un oggetto di classe C
	 */
	public T(C c) { this.c = c; }
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		for (int i=0; i<5; i++) {
			c.m(); synchronized (c.a) {
				c.print();
			}
		}
	}
}

/** Classe principale del programma che si occupa di istanziare i thread
 * @author Luca De Franceschi
 * @version 1.0, 11 Novembre 2013
 */
public class D {
	/** metodo statico main che si occupa di iniziare il programma 
	 * @param args argomenti opzionali passati al programma
	 */
	public static void main(String[] args) {
		Integer i1 = new Integer(1000);
		Integer i2 = i1;
		C x = new C(i1); T t1 = new T(x);
		C y = new C(i2); T t2 = new T(y);
		t1.start(); t2.start();
	}
}
