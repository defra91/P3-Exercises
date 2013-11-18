import java.util.Vector;

public class Avvio {
	public static void main(String[] args) {
		Passaggio p = new Passaggio();
		Vector<Auto> codaDx = new Vector<Auto>();
		Vector<Auto> codaSx = new Vector<Auto>();
		codaDx.add(new Auto("A")); codaDx.add(new Auto("B")); codaDx.add(new Auto("C")); codaDx.add(new Auto("D")); codaDx.add(new Auto("E"));	
		codaSx.add(new Auto("F")); codaSx.add(new Auto("G")); codaSx.add(new Auto("H")); codaSx.add(new Auto("I")); codaSx.add(new Auto("L"));
		
		Stop dx = new Stop(p,codaDx,"Destra"); Stop sx = new Stop(p,codaSx,"Sinistra");
		dx.start(); sx.start();
	}
}
