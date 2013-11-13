import java.util.*;

public class Aereo {
	private static int n;
	private int num;
	private String direzione;
	public Aereo(String d) {
		num = n++;
		direzione = d;
	}
	
	public void stampa() {
		System.out.println("aereo num " + num + " " + direzione);
	}
}

class GeneraArrivi extends Thread {
	private Controllore contr;
	GeneraArrivi(Controllore c) { contr = c; }
	public void run() {
		while(true) {
			contr.addArrivi(new Aereo("in arrivo")); try {
				sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

class GeneraPartenze extends Thread {
	private Controllore contr;
	GeneraPartenze(Controllore c) { contr = c; }
	public void run() {
		while(true) {
			contr.addPartenze(new Aereo("in partenza")); try {
				sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} }
		}
	}

class Controllore extends Thread {
	private Vector<Aereo> codaArrivi = new Vector<Aereo>();
	private Vector<Aereo> codaPartenze = new Vector<Aereo>();
	private TS servizio = new TS(new Vector<Aereo>());
	
	// definire il metodo AddArrivi(Aereo a)
	public void addArrivi(Aereo a) {
		codaArrivi.add(a);
		servizio.getCoda().add(a);
	}
	
	public void addPartenze(Aereo a) {
		codaPartenze.add(a);
		servizio.getCoda().add(a);
	}
	
	private char proxTransito() { 
		return 'A';
	}
	
	public void run() {
		while(true) {
			char c = proxTransito();
			if (c == 'A') gestisciArrivo();
			else gestisciPartenza();
		}
	}
	
	private void gestisciArrivo() {
		if (!codaArrivi.isEmpty()) {
			Aereo tmp = codaArrivi.remove(0);
			tmp.stampa();			
		}
		else {
		}
	}
	
	private void gestisciPartenza() {
		if (!codaPartenze.isEmpty()) {
			Aereo tmp = codaPartenze.remove(0);
			tmp.stampa();
		}
		else {
		}
	}
	
	private class TS extends Thread {
		Vector<Aereo> coda;
		TS(Vector<Aereo> s) { coda = s; }
		public Vector<Aereo> getCoda() { return coda; }
		
		public void run() {
			while(true) {
				System.out.println("TS in esecuzione");
			}
		}
	}
	
	public static void main(String[] args) {
		Controllore contr = new Controllore();
		GeneraArrivi gA = new GeneraArrivi(contr);
		GeneraPartenze gP = new GeneraPartenze(contr);
		gP.start(); gA.start();  contr.start();
	}
	
}
