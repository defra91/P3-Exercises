class Investito extends Exception {
	
	int chi;
	int cosa;
	Investito (int i, int j) {chi=i; cosa=j;}
	void info(){ System.out.println("INCIDENTE: "+chi+" ha investito "+cosa); };
}

class Attraversamento {
	boolean[] occupato = new boolean[3];
	// 0 -> pedone sta attraversando
	// 1 -> auto da sx sta attraversando
	// 2 -> auto da dx sta attraversando

	Attraversamento() {
		occupato[0]=occupato[1]=occupato[2]=false;
	}

	public synchronized void occupa(int chi) throws Investito {
		try{				
			if(chi==0) 				
			{				
				if(occupato[1] || occupato[2])				
				 { System.out.println("Il pedone è sul ciglio della strada e aspetta"); 
				 wait(); 
				 }				
							
			}				
			else if(occupato[0]){
				System.out.println(Thread.currentThread().getName() + " aspetta il passaggio di un pedone");
				wait();
			}
				
		} catch(InterruptedException e) {  }				
		occupato[chi] = true;
		System.out.println(Thread.currentThread().getName()+" è su un incrocio");
		if (chi == 0 && occupato[1] == true) throw new Investito(0,1); // una macchina da sx investe un pedone, il quale occupava
		if (chi == 0 && occupato[2] == true) throw new Investito(0,2); // una macchina da dx investe un pedone, il quale occupava
		if (occupato[0]==true && chi != 0) throw new Investito(chi,0); // il pedone sta attraversando e una macchina occupava: chi è una macchina
	}
	public synchronized void libera(int chi) {
		occupato[chi]=false;
		notifyAll();
	}
}

class Auto extends Thread {
	Attraversamento a;
	int direzione; // 1 da sx; 2 da dx
	int distanza;

	Auto(Attraversamento att, int dir, int dist, String nome) {
		super(nome); a=att; direzione=dir; distanza=dist;
	}

	public void run () {
		try {
			for (int i=distanza; i>0; i--) {
				Thread.sleep((int)Math.random()*20);
			}
			a.occupa(direzione); // direzione è 0 oppure 1
			Thread.sleep(100);
			a.libera(direzione);

			System.out.println(getName()+" è passato ");
		}catch(InterruptedException e) {}
		catch(Investito e){e.info();}
	}
}

class Pedone extends Thread {
	Attraversamento a;
	int distanza;

	Pedone(Attraversamento att, int dist) {
		super("Pedone"); a=att; distanza=dist;
	}

	public void run(){
		try{
			for(int i=distanza; i>0; i--) {
				Thread.sleep((int)Math.random()*80);
			}
			a.occupa(0); // 0 è il pedone
			Thread.sleep(100);
			a.libera(0);
			System.out.println("Pedone è passato");
		}catch(InterruptedException e) {}
		catch(Investito e) {e.info();}
	}
}

public class IncrocioPulito {
	public static void main (String[] args) {
		Attraversamento a = new Attraversamento();
		Auto a1= new Auto(a,1,10,"Auto da sinistra");
		Auto a2= new Auto(a,2,5000,"Auto da destra");
		Pedone pedone = new Pedone(a,8);
		pedone.start();
		a1.start();
		a2.start();

	}
}