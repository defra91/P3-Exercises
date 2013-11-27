import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {
	private JButton avvia;
	private JButton pausa;
	private JButton stop;
	private ChangeBGColorThread t = null;
	
	private Boolean sospendi = false;

	MyFrame(String s) {

		super(s);
		setSize(500, 500);
		avvia = new JButton("Avvia");
		pausa = new JButton("Pausa");
		stop = new JButton("Stop");

		avvia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (t == null) {
					t = new ChangeBGColorThread();
					t.start();
				} else if (sospendi == true && (!t.isInterrupted())) {
					synchronized (t) {
						sospendi = false;
						t.notify();
					}
				}

			}
		});

		pausa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (t != null && sospendi == false) {
					sospendi = true;
				}
			}
		});

		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (t != null) {
					t.interrupt();
				}
			}
		});
		setLayout(new FlowLayout());
		add(avvia);
		add(pausa);
		add(stop);

	}

	private class ChangeBGColorThread extends Thread {

		public void run() {
			try {
				
				while (!isInterrupted()) {   
					synchronized (this) {

						while (sospendi == true) {
							wait();

						}
					}
					sleep(30);
					getContentPane().setBackground(
							new Color((int) (Math.random() * 256), (int) (Math
									.random() * 256),
									(int) (Math.random() * 256)));
					}
			} catch (InterruptedException I) {
				Thread.currentThread().interrupt();
				System.out.println( "Thread interrotto durante lo sleep");

			}
			System.out.println("Thread interrotto mentre era in esecuzione");
		}
	}
	

	public static void main(String[] args) {
		MyFrame f = new MyFrame("GUI");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);

	}

}
