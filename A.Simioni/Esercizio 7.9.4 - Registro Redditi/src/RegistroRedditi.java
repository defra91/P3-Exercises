public class RegistroRedditi {
	private float[] redditi;
	private int numRedditiInseriti = 0;
	private int length;
	boolean bonusUsato =false;

	public RegistroRedditi(int num) {
		redditi = new float[num];
		length = num;

	}

	synchronized public float riassunto() {
		float somma = 0;
		for (float iter : redditi) {
			somma += iter;

		}
		return somma;
	}

	synchronized void aggiungi(float r) {
		redditi[numRedditiInseriti] = r;
		numRedditiInseriti++;
		System.out.println("inserito un reddito di "+ r);
		if(length - numRedditiInseriti == 2) notifyAll();

	}

	synchronized boolean figliFinito() {
		if((length- numRedditiInseriti) <=2) return true; 
		else return false;
	}
	
	synchronized boolean prendiBonus(){
		if(!bonusUsato){bonusUsato=true; return true;}
		else return false;
		
		
	}
	
	
	public static void main(String[] args){
		RegistroRedditi r = new RegistroRedditi(6);
		new Figlio(r).start();
		Genitore g1 = new Genitore(r);
		g1.start();
		new Figlio(r).start();
		new Figlio(r).start();
		new Figlio(r).start();
		Genitore g2 = new Genitore(r);
		g2.start();
		try {
			g1.join();
			g2.join();
		}
		catch(InterruptedException e) {}
		System.out.println(r.riassunto());
			
	}

}
