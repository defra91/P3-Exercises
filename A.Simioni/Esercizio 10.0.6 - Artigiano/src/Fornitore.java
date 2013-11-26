
public class Fornitore {
	public Risorsa produci(){ try{
		Thread.sleep((int)(Math.random()*300));
	}catch(InterruptedException I){}
	return new Risorsa();
	}
}
