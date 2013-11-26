public class Distributore {
	private Object lockVino = new Object();
	private Object lockAranciata = new Object();
	private Object lockCola = new Object();

	private void riempiBicchiere() {
		Cliente c = (Cliente) Thread.currentThread();
		if (c.tipo == 'A') {
			synchronized (lockAranciata) {
				System.out.println("riempio il bicchiere del cliente n."
						+ c.num);
			}
		} else if (c.tipo == 'V') {
			synchronized (lockVino) {
				System.out.println("riempio il bicchiere del cliente n."
						+ c.num);
			}
		} else if (c.tipo == 'C') {
			synchronized (lockCola) {
				System.out.println("riempio il bicchiere del cliente n."
						+ c.num);
			}
		}
	}

	public class Cliente extends Thread {
		private int num = 0;
		private char tipo;

		Cliente(int n, char t) {
			num = n;
			tipo = t;
		}

		public void run() {
			System.out.println("Sono il cliente n " + num + " e voglio bere "
					+ tipo);
			riempiBicchiere();
			System.out.println("sono il cliente n." + num + " e ho bevuto "
					+ tipo);
		}
	}
}
