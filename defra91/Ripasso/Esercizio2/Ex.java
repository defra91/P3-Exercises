// Esercizio 2 di ripasso

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;

public class Ex extends JFrame {
	public Ex() { super("Esempio"); setSize(100,100); }
	public void Fun(JFrame f, AbstractButton b) {
		try {
			System.out.println("Si tratta di un jbutton");
			JButton jb = (JButton) b;
			jb.setText("pippo");
			f.add(jb);
		}
		catch (ClassCastException e) {
			System.out.println("Non è un jButton!!");
			BasicArrowButton ab = (BasicArrowButton) b;
			ab.setDirection(BasicArrowButton.NORTH);
			f.add(ab);
		}
	}

	public static void main(String[] args) {
		Ex x = new Ex();
		x.setVisible(true);
		x.Fun(x,new BasicArrowButton(1));
	}
}