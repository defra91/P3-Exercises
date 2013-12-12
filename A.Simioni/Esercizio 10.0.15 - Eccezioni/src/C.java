class E1 extends Exception{}
class E2 extends Exception{}
class E3 extends E1{}
class Pieno extends Exception{}

interface Lista{boolean isEmpty();}

class Vettore implements Lista{
	private int[] a = new int[5];
	private int dim=0;
	
	public boolean isEmpty(){ return dim == 0;}
	
	public void add() throws Pieno{
		if(dim<5){
			a[dim]=dim;
			dim++;
		}
		else throw new Pieno();
	}
}

class ListaVuota implements Lista{
	public boolean isEmpty(){return true;}
}


public class C {

	/**
	 * @param args
	 */
	
	public static void m(Lista l) throws E1,E2,E3{
		if(!(l instanceof Vettore)){ throw  new E1();}
		else{
			try{
				((Vettore)l).add(); throw new E2();
			}
			catch(Pieno p){throw new E3();}
		}
	}
	public static void main(String[] args) {
		try{
			m(new Vettore());
			//m(new ListaVuota());
		}
		catch(E1 e){
			if(e instanceof E3){System.out.println("E3");}
			else{
				System.out.println("E1");
			}
		
		}
		catch(E2 e){System.out.println("E2");}
	}

}
