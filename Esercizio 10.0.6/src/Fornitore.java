import java.util.Vector;

public class Fornitore {
	private Vector<Risorsa> risorse;
	
	public Fornitore(Vector<Risorsa> v) {
		risorse = v;
	}
	
	public Risorsa produci() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {}
		Risorsa aux = risorse.remove(risorse.size()-1);
		return aux;
	}
}
