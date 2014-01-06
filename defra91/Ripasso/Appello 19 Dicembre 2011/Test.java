class C {
	private int counter = 0;

	public synchronized void m() {
		for (int i=0; i<1000000; i++) counter++;
		for (int j=0; j<1000000; j++) counter--;
		counter++;
		notifyAll();
	}

	public synchronized void n() throws InterruptedException {
		if (counter < 9) wait();
		System.out.println(counter + " ");
	}
}

class T1 extends Thread {
	private C c;

	public T1(C c) {
		this.c = c;
	}
	public void run() {
		for (int i=0; i<1000; i++) c.m();
	}
}

class T2 extends Thread {
	private C c;

	public T2(C c) {
		this.c = c;
	}

	public void run() {
		try {
			c.n();
		}
		catch (InterruptedException e) {}
	}
}

class Test {
	public static void main(String[] array) {
		C c = new C();
		T1 r = new T1(c);
		T2 s = new T2(c);
		T2 t = new T2(c);

		t.start();
		s.start();
		r.start();
	}
}