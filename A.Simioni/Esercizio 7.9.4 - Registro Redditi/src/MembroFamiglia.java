
abstract public class MembroFamiglia extends Thread {
	abstract public void run();
	protected RegistroRedditi registro;
	MembroFamiglia(RegistroRedditi r){ registro = r;}
}
