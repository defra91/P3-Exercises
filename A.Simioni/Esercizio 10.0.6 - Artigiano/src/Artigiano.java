
//Esercizio svolto usando wait e notify, (era più semplice usando join)

public class Artigiano extends Thread {
	private Fornitore f;
	private int num;

	Artigiano(Fornitore f2, int n) {
		f = f2;
		num = n;
	}

	public void run() {
		Risorsa r;
		GestoreFornitore g = new GestoreFornitore(num);
		new Thread(g).start();

		for (int i = 0; i < 100; i++) {
			System.out.println("procedo con il lavoro " + num);
		}
		synchronized (g) {
			while (g.sospendi == true) {
				try {
					g.wait();
				} catch (InterruptedException I) {
				}
			}
			r = g.getRisorsa();
			System.out.println("lavoro finito " + num);
		}

	}

	private class GestoreFornitore implements Runnable {
		private boolean sospendi = true;
		Risorsa r;
		private int num;

		GestoreFornitore(int n) {
			num = n;
		}

		public void run() {
			r = f.produci();
			synchronized (this) {
				sospendi = false;
				notify();
				System.out.println("Risorsa prodotta per l'artigiano " + num);
			}
		}

		public Risorsa getRisorsa() {
			return r;
		}
	}
}
