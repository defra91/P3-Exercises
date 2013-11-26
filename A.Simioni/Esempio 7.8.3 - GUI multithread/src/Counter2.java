/* 
 * Consegna: togliere attesa attiva nel ciclo che fa
 *  aumentare il contatore
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Counter2 extends JFrame {

	private class T extends Thread {
		private int count = 0;
		private boolean runFlag = true;

		T() {
			start();
		}

		void invertiFlag() {
			runFlag = (!runFlag);
		}

		boolean getFlag() {
			return runFlag;
		}

		public void run() {
			while (true) {
				try {
					sleep(100);
				} catch (InterruptedException e) {
					System.err.println("Interrupted");
				}
				synchronized (this) {
					if (runFlag)
						tf.setText(Integer.toString(count++));
					else
						try {
							wait();
						} catch (InterruptedException I) {
						}
				}
			}

		}
	} // Fine della classe T

	private T t = null;
	private JTextField tf = new JTextField(10);
	private JButton start = new JButton("Start");
	private JButton ferma = new JButton("Ferma");

	class Lstart implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (t == null)
				t = new T();
		}
	}

	class Lferma implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			synchronized (t) {
				if (t != null) {
					if (t.getFlag() == false) {

						t.invertiFlag();
						t.notify();

					} else
						t.invertiFlag();

				}
			}
		}
	}

	public Counter2() {
		super("MultiThread GUI");
		setLayout(new FlowLayout());
		add(tf);
		start.addActionListener(new Lstart());
		add(start);
		ferma.addActionListener(new Lferma());
		add(ferma);
		setSize(300, 100);
		setVisible(true);

	}

	public static void main(String[] args) {
		Counter2 r = new Counter2();
	}
}
