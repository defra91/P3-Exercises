class Process extends Thread {
	private String name;
	private int num;
	Syncronizer rif;
	public Process(String s, Syncronizer o, int n) {
		name = s;
		rif = o;
		num = n;
	}
	
	public void run() {
		synchronized(rif) {
			while (num != rif.getTurn()) {
				try {
					rif.wait();
				} catch (InterruptedException e) {}
			}
			System.out.println(name);
			if (num == rif.getNProcesses()) { rif.resetTurn(); }
			else rif.incrementTurn();
			rif.notify();
		}
	}
}

public class Syncronizer {
	int turn = 1;
	int nProcesses;
	public Syncronizer(int n) { nProcesses = n; }
	public int getTurn() { return turn; }
	public int getNProcesses() { return nProcesses; }
	public void incrementTurn() { turn++; }
	public void resetTurn() { turn = 1; }
	public static void main(String[] args) {
		Syncronizer lock = new Syncronizer(4);
		Thread[] t = {new Process("A",lock,1), new Process("B",lock,2), new Process("C",lock,3), new Process("D",lock,4)};
		for (int i=0; i<t.length; i++) {
			t[i].start();
		}
	}
}
