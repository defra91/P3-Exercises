/*
Esercizio di ripasso preso da Mega
*/

import java.awt.event.*;
import javax.swing.*;

public class Ex {
	private JButton jb = new JButton();
	// Definizione di classe interna
	private class MyFrame extends JFrame implements ActionListener {
		MyFrame() {
			super("PADOVA");	// definisce il titolo della finestra
			getContentPane().add(jb);	// aggiunge il bottone
			// definisce l'azione da compiere al chiudersi della finestra
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) { }
			} );
			jb.addActionListener(this);
		}
		public void actionPerformed(ActionEvent e) { }
	}
	// Definizione di classe interna statica
	private static class MyListener extends MouseAdapter {
		public void mouseEntered(MouseEvent e) { }
	}

	// funzione main
	public static void main(String[] args) {
		Ex e = new Ex();	// definisce un'istanza di ex 
		Ex.MyFrame f = e.new MyFrame();	// definisce un'istanza della classe interna frame
		f.addMouseListener(new MyListener()); // aggiunge un listener alla classe
	}
}

/*
Rispondere alle seguenti domande:

	1) Ogni istanza di Ex è un ActionListener?
		Ex non implementa ActionListener nè estende classi che a loro volta la estendono. Contiene però delle classi interne che lo implementano, ma le due entità sono distinte.

	2) La classe Ex ha tra i suoi membri una classe interna anonima? 
		No, non contiene definizioni di classi interne anonime tra i suoi membri.

	3) L’invocazione super(PADOVA); provoca la chiamata del costruttore di ActionListener? 
		No, un'interfaccia non può contenere costruttori. L'invocazione effettua la chiamata al costruttore della superclasse di MyFrame, ovvero JFrame.

	4) Vi è un WindowListener registrato sull’oggetto f? 
		Sì, f è un oggetto della classe Ex.MyFrame, nel cui costruttore vi è un'istruzione che registra un windowlistener sulla classe.

	5) f è un sottooggetto di e? 
		No, f condivide i campi dati di e ma non eredita da Ex.

	6) Vi è un MouseListener registrato sul bottone jb? 
		No, sul bottone è registrato un ActionListener.

	7) Se la classe MyFrame è dichiarata statica allora la classe Ex non compila?
		Non compila perchè la classe non può accedere ai campi privati della classe esterna ed inoltre non può essere istanziata con la new.

	8) Se la classe MyListener è dichiarata non statica allora la classe Ex non compila?
		Siamo all'interno di main, un contesto statico e non possiamo riferirci alla classe esterna.

	9) Un oggetto MyListener è un MouseListener?
		MyListener estende MouseAdapter, il quale implementa MouseListener, quindi sì.
*/