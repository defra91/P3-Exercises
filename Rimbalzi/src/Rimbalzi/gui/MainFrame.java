package Rimbalzi.gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;

/** Questa classe rappresenta la finestra principale della GUI del progetto. Contiene il pannello dove si muovono le palline pi� i vari bottoni per interagire su di esse.
 * @author Luca De Franceschi
 * @version 1.0, Ven 8 Novembre 2013
 */
public class MainFrame extends JFrame {
	/** Riferimento al pannello (e quindi all'ambiente) contenuto in questo frame */
	private Environment e;
	
	/** Pulsante per iniziare la simulazione. */
	private JButton start;
	
	/** Pulsante per interrompere la simulazione. */
	private JButton stop;
	
	/** Pulsante per riesumare la simulazione. */
	private JButton resume;
	
	/** Riferimento al thread che si occupa di far muovere le palline. */
	Thread t;
	
	/** Costruttore parametrico della classe.
	 * @param x Posizione x della finestra in pixel.
	 * @param y Posizione y della finestra in pixel.
	 * @param w Larghezza della finestra in pixel.
	 * @param h Altezza della finestra in pixel.
	 * @param bg Colore di sfondo del pannello.
	 * @param title Titolo della finestra
	 */
	public MainFrame(int x, int y, int w, int h, Color bg, String title) {
		super(title);
		setSize(w,h);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Random rg = new Random();
		Ball[] b = new Ball[1];
		for (int i=0; i<b.length; i++) {
			Color c1 = new Color(rg.nextInt(255),rg.nextInt(255),rg.nextInt(255));
			Color c2 = new Color(rg.nextInt(255),rg.nextInt(255),rg.nextInt(255));
			double s = 50;
			b[i] = new Ball(0, 400, 20, 100 ,s,s,50,c1,c2);
		}
		e = new Environment(b, 9.81, 0, 0, bg);
		add(e,BorderLayout.CENTER);
		e.paint(e.getGraphics());
		t = new Thread(e);
		start = new JButton("Start");
		e.add(start);
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) { 
				t.start();
				start.setEnabled(false);
			}
		});
		stop = new JButton("Stop");
		e.add(stop);
		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				while (true) {
					try {
						t.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		});
		
		resume = new JButton("Resume");
		e.add(resume);
		resume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t.notify();
			}
		});
		
		/*this.addComponentListener(new ComponentListener() {
			public void componentShown(ComponentEvent arg0) {}			
			public void componentResized(ComponentEvent arg0) { 
				//e.setSize(getSize());
			}
			public void componentMoved(ComponentEvent arg0) {}
			public void componentHidden(ComponentEvent arg0) {}
		});*/
	}
}
