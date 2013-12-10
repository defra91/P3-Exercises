public class Torre {
	Coda _coda = new Coda();
	Cima _cima = new Cima();

	final static int MAX=10;
	

	private class Coda {
		int n_InEntrata = 0;
	}

	private class Cima {
		int n_InAlto = 0;
	}

	private class GuardiaEntrata extends Thread {
		public void run() {
			while (true) {
				int numPersone=0;
				synchronized (_coda) {

					while (_coda.n_InEntrata == 0) {
						try {
							_coda.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if (_coda.n_InEntrata <= MAX) {
						numPersone = _coda.n_InEntrata;
						_coda.n_InEntrata = 0;
					} else {
						numPersone = MAX;
						_coda.n_InEntrata -= MAX;
					}		
				}
				synchronized (_cima) {
					_cima.n_InAlto += numPersone;
					_cima.notify();
					//System.out.println("SALITE: " +numPersone+" Persone in coda: "+_coda.n_InEntrata
						//	+" Persone in cima: "+_cima.n_InAlto);
				}
				
			}
		}
	}

	private class GuardiaCima extends Thread {
		public void run() {
			while(true){
				int numPersone=0;
				synchronized (_cima) {
					while (_cima.n_InAlto == 0) {
						try {
							_cima.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if(_cima.n_InAlto <=10){numPersone = _cima.n_InAlto; _cima.n_InAlto =0;}
					else{numPersone = MAX; _cima.n_InAlto -=MAX;}
					//System.out.println("SCESE: " +numPersone+" Persone in coda: "+_coda.n_InEntrata
						//	+" Persone in cima: "+_cima.n_InAlto);
				}
			}

		}
	}

	private class GeneraArrivi extends Thread {
		public void run() {
			for (int i = 0; i < 15; i++) {
				
				synchronized(_coda){
					int persone = (int)(Math.random()*15);
					_coda.n_InEntrata += persone;
					//System.out.println("ARRIVATE: " +persone+" Persone in coda: "+_coda.n_InEntrata
						//	+" Persone in cima: "+_cima.n_InAlto);
					_coda.notify();
				}
				try {
					sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			

		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Torre t = new Torre();
		GeneraArrivi g = t.new GeneraArrivi();
		GuardiaCima gci = t.new GuardiaCima();
		GuardiaEntrata ge = t.new GuardiaEntrata();
		g.start();
		gci.start();
		ge.start();

	}

}
