
public class Fabbrica {

	public static void main(String[] args) {
		Fornitore f = new Fornitore();
		Artigiano a1 = new Artigiano(f,1);
		Artigiano a2 = new Artigiano(f,2);
		Artigiano a3 = new Artigiano(f,3);
		a1.start(); a2.start();a3.start();
		

	}

}
