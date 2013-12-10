public class Tempo extends Thread {
	private Ristorante r;

	Tempo(Ristorante rr) {
		r = rr;
	}

	public void run() {
		for (int i = 1; i < 29; i++) {
			try {
				sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		r.stopPrenotazioni();
	}
}
