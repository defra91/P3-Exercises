/* Gui Multithreaded */
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Counter2 extends JFrame {
	
	private class T extends Thread {
		private int count = 0;
		private boolean runFlag = true;
		private JTextField rif;
		int delay = 0;
		T(JTextField t, int d) { rif = t; delay = d; start(); }
		
		void invertiFlag() { runFlag = !runFlag; }
		
		public void run() {	// Il metodo se runFlag è true stampa ogni 100 ms il contatore incrementato
			while (true) {
				try {
					sleep(delay);
				}
				catch(InterruptedException e) {
					System.err.println("Interrupted");
				}
				if (runFlag) rif.setText(Integer.toString(count++));
			}
		}
	}
	
	
	private JTextField tf = new JTextField(0);
	private JTextField tf1 = new JTextField(0);
	private T t1 = null;	// Thread che gestisce il primo contatore
	private T t2 = null;	// Thread che gestisce il secondo contatore
	private JButton start = new JButton("Start");
	private JButton ferma = new JButton("Stop");
	private JButton resume = new JButton("Resume");
	
	class LStart implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (t1==null && t2==null) {
				t1 = new T(tf,100);
				t2 = new T(tf1,50);
			}
		}
	}
	class LFerma implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (t1!=null && t2!=null) {
				t1.invertiFlag();
				t2.invertiFlag();
			}
			resume.setEnabled(true);
		}
	}
	class LResume implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (t1!=null && t2!=null) {
				t1.invertiFlag();
				t2.invertiFlag();
			}
			resume.setEnabled(false);
		}
	}
	
	public Counter2() {
		super("MultiThreaded GUI");
		setLayout(new GridLayout()); add(tf); add(tf1);
		start.addActionListener(new LStart()); add(start);
		ferma.addActionListener(new LFerma()); add(ferma);
		resume.addActionListener(new LResume()); add(resume);
		resume.setEnabled(false);
		setSize(300,200); setVisible(true);
	}
	
	public static void main(String[] args) {
		Counter2 r = new Counter2();
	}
}
