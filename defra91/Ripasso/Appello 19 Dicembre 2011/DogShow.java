class Dog {
	public void bark() {
		System.out.println("woof!");
	}
}

class Hound extends Dog {
	public void sniff() {
		System.out.println("Sniff!");
	}
	
	public void bark() {
		System.out.println("Howl!");
	}
}

public class DogShow {
	public static void main(String[] args) {
		new DogShow().go();
	}
	void go() {
		new Hound().bark();
		((Dog) new Hound()).bark();
		((Dog) new Hound()).sniff();
	}
}

/** Stampa:

	Errore di compilazione alla riga 24

*/