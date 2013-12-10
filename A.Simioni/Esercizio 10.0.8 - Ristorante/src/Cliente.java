public class Cliente extends Thread {
	Ristorante rist;
	boolean termina = false;
	boolean prenotato = false;
	static int contatore = 0;
	int num = ++contatore;

	Cliente(Ristorante r) {
		rist = r;
	}

	public void avvisa(Risposta r) {
		termina = true;
		prenotato = r.prenotato;
		synchronized (this) {
			notify();
		}

	}

	public void run() {
		try {
			Risposta rispostaCorrente = rist.prenota(this);
			termina = rispostaCorrente.prenotato;
			prenotato = termina;
			if (!prenotato) {
				System.out.println("Sono il cliente " + num
						+ " e sono in overbooking");
			}
			synchronized (this) {
				while (!termina)

					wait();

			}
			if (prenotato) {
				System.out
						.println("Sono il cliente " + num + " e ho prenotato");
				if (((int) (Math.random() * 3)) == 0) {
					rist.disdici();
					System.out.println("Sono il cliente " + num
							+ " e ho disdetto");
				}

			} else {
				System.out.println("Sono il cliente " + num
						+ " e non vado al ristorante");
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
