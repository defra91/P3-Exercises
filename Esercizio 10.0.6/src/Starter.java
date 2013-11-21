import java.util.Vector;

public class Starter {
	public static void main(String[] args) {
		Vector<Risorsa> src = new Vector<Risorsa>();
		src.add(new Risorsa("Lavatrice"));
		src.add(new Risorsa("Forno"));
		src.add(new Risorsa("Frigorifero"));
		src.add(new Risorsa("Lavastoviglie"));
		src.add(new Risorsa("Microonde"));
		src.add(new Risorsa("Stufa a pellet"));
		
		Fornitore f = new Fornitore(src);
		Artigiano a1 = new Artigiano("Mario",f);
		Artigiano a2 = new Artigiano("Luigi",f);
		Artigiano a3 = new Artigiano("Tod",f);
		
		a1.start(); a2.start(); a3.start();
	}
}
