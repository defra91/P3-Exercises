package Rimbalzi.gui;
import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/** Questa classe rappresenta l'ambiente su cui si muovono le palline. Estende il concetto di pannello come interfaccia grafica ed implementa i thread per l'esecuzione in parallelo.
 * @author Luca De Franceschi
 * @version 1.0, Ven 8 Novembre 2013
 */
public class Environment extends JPanel {
	/** Rappresenta l'array di palline contenute.*/
	private Ball balls[]; 
	
	/** Rappresenta la gravit� dell'ambiente espressa in metro su secondo quadrato. */
	private double gravity;
	
	/** Rappresenta la velocit� orizzontale del vento. */
	private double windVelX;
	
	/** Rappresenta la velocit� verticale del vento. */
	private double windVelY;
	
	/** Rappresenta il colore di sfondo dell'ambiente.*/
	private Color backgroundColor;

	/** Costruttore parametrico.
	 * @param b array di palline.
	 * @param g gravit� in m/s^2
	 * @param wx velocit� orizzontale del vento in m/s
	 * @param wy velocit� verticale del vento in m/s
	 * @param bg colore di sfondo del pannello.
	 */
	public Environment(Ball[] b, double g, double wx, double wy, Color bg) {
		balls = b;
		gravity = g;
		windVelX = wx; windVelY = wy;
		backgroundColor = bg;
		setBackground(bg);
		setSize(500,500);
		
		addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				Point p = e.getPoint();
				double x = p.getX(); double y = p.getY();
				for (Ball b : balls) {
					if ((x > b.getX() && x < (b.getX()+b.getWidth())) && (y > b.getY() && y < (b.getY()+b.getHeight()))) {
						b.setFrame(0,0,0,0);
						
					}
				}
				paint(getGraphics());
			}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}
		});
	}

	/** Restituisce l'array di palline.
	 * @return array di palline.
	 */
	public Ball[] getBalls() {
		return balls;
	}
	

	/** Imposta l'array di palline.
	 * @param b array di palline.
	 */
	public void setBalls(Ball b[]) {
		this.balls = b;
	}
	
	/** Restituisce la gravit� del sistema in m/s^2.
	 * @return gravit� del sistema.
	 */
	public double getGravity() {
		return gravity;
	}
	
	/** Imposta la gravit� del sistema.
	 * @param gravity gravit� del sistema.
	 */
	public void setGravity(double gravity) {
		this.gravity = gravity;
	}
	
	/** Restituisce la velocit� y del vento del sistema in m/s.
	 * @return velocit� y del vento.
	 */
	public double getWindoVelY() {
		return windVelY;
	}
	
	/** Imposta la velocit� y del vento sistema in m/s.
	 * @param windoVelY velocit� y vento.
	 */
	public void setWindoVelY(double windoVelY) {
		this.windVelY = windoVelY;
	}
	
	/** Restituisce la velocit� x del vento del sistema in m/s.
	 * @return velocit� x del vento.
	 */
	public double getWindVelX() {
		return windVelX;
	}


	/** Imposta la velocit� x del vento del sistema in m/s.
	 * @param windVelX velocit� x del vento.
	 */
	public void setWindVelX(double windVelX) {
		this.windVelX = windVelX;
	}

	/** Restituisce il colore di sfondo del pannello.
	 * @return colore di sfondo.
	 */
	public Color getBackgroundColor() {
		return backgroundColor;
	}
	
	/** Imposta il colore di sfondo del pannello.
	 * @param backgroundColor colore di sfondo.
	 */
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (Ball b : balls) {
			g2.fill(b);
			g2.setColor(b.getBackgroundColor());
		}	
	}
}
