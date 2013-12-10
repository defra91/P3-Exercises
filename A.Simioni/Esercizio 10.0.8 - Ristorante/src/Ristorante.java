import java.util.Vector;

public class Ristorante {
	private int nPrenotati;
	private final int maxPrenotazioni = 60;
	private int nOverbooked;
	private Vector<Cliente> overbooked = new Vector<Cliente>();
	private boolean stopPrenotazioni = false;

	public synchronized Risposta prenota(Cliente c) {
		if (stopPrenotazioni == true)
			return new Risposta(0, false, false);

		if (nPrenotati < maxPrenotazioni) {
			nPrenotati++;
			return new Risposta(nPrenotati, true, false);
		}
		nOverbooked++;
		overbooked.add(c);
		return new Risposta(0, false, true);
	}

	public synchronized void disdici() {
		nPrenotati--;
		if (nOverbooked > 0) {
			Cliente c = overbooked.remove(0);
			nOverbooked--;
			nPrenotati++;
			c.avvisa(new Risposta(nPrenotati, true, false));
		}

	}

	public synchronized void stopPrenotazioni() {
		stopPrenotazioni = true;
		System.out.println("CHIUDONO LE PRENOTAZIONI, tot " + nPrenotati);
		for (int i = 0; i < nOverbooked; i++) {
			Cliente c = overbooked.remove(0);
			c.avvisa(new Risposta(0, false, false));
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final Ristorante r = new Ristorante();

		new Thread(new Runnable() {
			public void run() {
				Tempo t = new Tempo(r);
				t.start();
				try {
					t.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Mancano 2 giorni a capodanno");
			}
		}).start();

		for (int i = 1; i < 100; i++) {
			new Cliente(r).start();
		}

	}

}
