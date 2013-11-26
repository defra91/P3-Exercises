package Rimbalzi.gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/** Questa classe rappresenta la finestra principale della GUI del progetto. Contiene il pannello dove si muovono le palline più i vari bottoni per interagire su di esse.
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
		final Ball[] b = new Ball[50];
		e = new Environment(b, 9.81, 0, 0, bg);
		for (int i=0; i<b.length; i++) {
			Color c1 = new Color(rg.nextInt(255),rg.nextInt(255),rg.nextInt(255));
			Color c2 = new Color(rg.nextInt(255),rg.nextInt(255),rg.nextInt(255));
			double s = 50;
			b[i] = new Ball(rg.nextInt(this.getWidth() - 25), rg.nextInt(this.getHeight() - 25), 200, 200,s,s,50,c1,c2,e);
		}
		
		add(e,BorderLayout.CENTER);
		JPanel tools = new JPanel();
		add(tools,BorderLayout.SOUTH);
		start = new JButton("Start");
		tools.add(start);
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) { 
				for (Ball balls : b) {
					Thread t = new Thread(balls);
					t.start();
				}
			}
		});
		stop = new JButton("Stop");
		tools.add(stop);
		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
			}
		});
		
		resume = new JButton("Resume");
		tools.add(resume);
		resume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
