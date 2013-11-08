package Rimbalzi.gui;
import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Environment extends JPanel implements Runnable {
	private Ball balls[]; 
	private double gravity;
	private double windVelX;
	private double windVelY;
	private Color backgroundColor;

	public Environment(Ball[] b, double g, double wx, double wy, Color bg) {
		balls = b;
		gravity = g;
		windVelX = wx; windVelY = wy;
		backgroundColor = bg;
		setBackground(bg);
		
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
	
	/* Definizione dei metodi getter e setter */
	
	public Ball[] getBalls() {
		return balls;
	}
	public void setBalls(Ball b[]) {
		this.balls = b;
	}
	public double getGravity() {
		return gravity;
	}
	public void setGravity(double gravity) {
		this.gravity = gravity;
	}
	public double getWindoVelY() {
		return windVelY;
	}
	public void setWindoVelY(double windoVelY) {
		this.windVelY = windoVelY;
	}
	public double getWindVelX() {
		return windVelX;
	}
	public void setWindVelX(double windVelX) {
		this.windVelX = windVelX;
	}
	public Color getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (Ball b : balls) {
			g2.fill(b);
			g2.setColor(b.getBackgroundColor());
		}	
	}
	
	public void run() {
		try {
			while (!Thread.currentThread().isInterrupted()) {
				for (int i=0; ; i++) {
					for (Ball b : balls) {
						b.move(getBounds());
					}
					paint(getGraphics());
					Thread.sleep(3);
				}
			}
		} 
		catch(InterruptedException e) { 
			System.out.println("Thread interrotto durante l'esecuzione"); 
		}
		finally {
			
		}
	}
}
