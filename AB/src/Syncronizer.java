class Process extends Thread {
	private String name;
	private int num;
	private static int n = 0;
	private Syncronizer s;
	public void run() {
		while (true) {
			int l = s.getLock();
			if (l == num) {
				s.lockIncrement();
				System.out.println(name);
				if (s.getLock() > s.getNProcesses()) { s.lockReset(); }
			}
		}
	}
	
	public Process(String x, Syncronizer y) {
		name = x;
		n++;
		num = n;
		s = y;
	}
}

public class Syncronizer {
	private int lock = 1;
	private int nProcesses;
	public Syncronizer(int n) {
		nProcesses = n;
	}
	public int getLock() { return lock; }
	public int getNProcesses() { return nProcesses; }
	public void lockIncrement() { lock++; }
	public void lockReset() { lock = 1; }
	public static void main(String[] args) {
		Syncronizer s = new Syncronizer(4);
		Thread[] t = {new Process("A",s), new Process("B",s), new Process("C",s), new Process("D",s)};
		for (int i=0; i<t.length; i++) {
			t[i].start();
		}
	}
}
