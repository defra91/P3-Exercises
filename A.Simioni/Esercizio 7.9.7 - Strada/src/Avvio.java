
public class Avvio {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Passaggio p = new Passaggio();
		Stop s1 = new Stop(p,"DESTRA");
		Stop s2 = new Stop(p, "SINISTRA");
		s1.start();
		s2.start();

	}

}
