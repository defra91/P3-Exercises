import java.util.Vector;

public class Fornitore {
	private Vector<Risorsa> risorse;
	private boolean occupato = false;
	boolean isOccupato() { return occupato; }
	
	public Fornitore(Vector<Risorsa> v) {
		risorse = v;
	}
	
	public Risorsa produci(String a) {
		Risorsa aux = risorse.remove(risorse.size()-1);
		try {
			for (int i=0; i<5; i++) {
				System.out.println("Fornitore: sto producendo " + aux.getTipo() + " per " + a);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {}
		
		return aux;
	}
	
	public boolean risorseEsaurite() {
		if (risorse.isEmpty()) return true;
		else return false;
	}
}
