public class Genitore extends MembroFamiglia {
	static int counter;
	int num = ++counter;

	public Genitore(RegistroRedditi r) {
		super(r);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		float reddito = (float) Math.random() * 100000;
		System.out.println("Sono il genitore" + num + " e il mio reddito è "
				+ reddito);
		/*
		 * il genitore deve aspettare che i tutti figli abbiano finito i loro
		 * inserimenti, altrimenti non può calcolare l'ammontare del bonus.
		 */
		synchronized (registro) {
			while (!registro.figliFinito()) {
				try {
					registro.wait();
				} catch (InterruptedException e) {
				}
			}
		}
		if (registro.prendiBonus()) {
			reddito = reddito - (float) 0.05 * registro.riassunto();
			System.out.println("Sono il genitore" + num
					+ " e ho preso il bonus ");
		}
		registro.aggiungi(reddito);

	}

}
