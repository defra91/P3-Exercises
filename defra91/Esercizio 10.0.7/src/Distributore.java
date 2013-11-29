
public class Distributore {
	public void riempiBicchiere() {}
	private Cliente[] clienti;
	
	public Distributore() {
		Cliente[] aux = new Cliente[100];
		for (int i=0; i<aux.length; i++) {
			aux[1] = new Cliente();
		}
		clienti = aux;
		for (int i=0; i<clienti.length; i++) clienti[i].start();
	}
	
	class Cliente extends Thread {
		public void run() {}
	}

}
