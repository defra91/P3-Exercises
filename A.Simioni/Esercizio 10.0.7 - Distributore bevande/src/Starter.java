
public class Starter {

	public static void main(String[] args) {
		Distributore d = new Distributore();
		int A=0;
		int V=0;
		int C=0;
		for (int i = 1; i<=100;i++){
			double aux = Math.random();
			if (aux>=0 &&aux< 0.33){ d.new Cliente(i,'A').start();++A;}
			if (aux>=0.33 &&aux< 0.66){ d.new Cliente(i,'V').start();++V;}
			if (aux>=0.66 &&aux<= 1){ d.new Cliente(i,'C').start();++C;}
		}
		System.out.println("creati: "+A+" acqua, "+V+" vino, "+C+" cola.");
	}

}
