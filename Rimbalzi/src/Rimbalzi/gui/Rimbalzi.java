package Rimbalzi.gui;
import javax.swing.*;

import java.awt.Color;

/** Classe principale del progetto Rimbalzi da cui partirà l'effettiva esecuzione.
 * @author Luca De Franceschi
 * @version 1.0, Ven 8 Novembre 2013
 *
 */
public class Rimbalzi {
	/** Metodo statico main invocato dalla JVM.
	 * @param args argomenti opzionali per il programma.
	 */
	public static void main(String[] args) {
		MainFrame g = new MainFrame(0,0,1000,1000,Color.red,"Rimbalzi");
		g.setVisible(true);
	}
}
