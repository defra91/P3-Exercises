import java.util.Vector;

public class Stop extends Thread {
	Passaggio rif;
	Vector<Auto> coda;
	String direction;
	Generatore g;
	public Stop(Passaggio r, Vector<Auto> v, String d) {
		rif = r;
		coda = v;
		direction = d;
		g = new Generatore(this);
		g.start();
	}
	public void addAuto(Auto a) {
		coda.add(a);
	}
	public void run() {
		while (true) {
			synchronized(rif) {
				while(!rif.getLibero()) {
					try {
						rif.wait();
					} catch (InterruptedException e) {}
				}
				for (int i=0; i<coda.size(); i++) {
					System.out.println("Auto " + coda.elementAt(i).getName() + " proveniente da " + direction + " transitata correttamente.");
					try {
						this.sleep(1000);
					} catch (InterruptedException e) { System.out.println("Interrupted exeption"); }
				}
				coda.clear();
				rif.setLibero(true);
				System.out.println("Passaggio libero!");
				rif.notifyAll();
			}
		}
	}
}

class Generatore extends Thread {
	private Stop rif;
	public Generatore(Stop r) {
		rif = r;
		setDaemon(true);
	}
	public void run() {
		while (true) {
			rif.addAuto(new Auto("rand"));
			try {
				sleep(4000);			} catch (InterruptedException e) {}
		}
	}
}