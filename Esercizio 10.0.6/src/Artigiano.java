
public class Artigiano extends Thread {
	Fornitore rif;
	String name;
	public Artigiano(String n, Fornitore f) {
		name = n;
		rif = f;
	}
}
