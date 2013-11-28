
public class Figlio extends MembroFamiglia {
	static int count;
	int num = ++count;
	public Figlio(RegistroRedditi r) {
		super(r);
	}

	@Override
	public void run() {
		float reddito = (float)Math.random() * 100000;
		System.out.println("sono il figlio "+num+" e il mio reddito è "+reddito);
		registro.aggiungi(reddito);
	}
}
