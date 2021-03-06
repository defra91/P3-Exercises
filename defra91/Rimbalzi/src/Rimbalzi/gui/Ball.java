package Rimbalzi.gui;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import java.lang.Math;


/** La classe Ball descrive una pallina che verr� visualizzata nello schermo come un'ellisse in due dimensione. Essa estende appunto il concetto di ellisse con tutte le sue propriet� geometriche. Possiede inoltre un colore, una massa
 * espressa in kg, le sue componenti orizzontale e verticale della velocit� e il tempo di volo (nel caso in cui ci sia gravit� nel sistema).
 * @author Luca De Franceschi
 * @version 1.0, Ven 8 Novembre 2013
 */
public class Ball extends Ellipse2D.Double implements Runnable {	
	/** Descrive la posizione in pixel nell'asse x della finestra. */
	private double posX = 0;
	
	/** Descrive la posizione in pixel nell'asse y della finestra. */
	private double posY = 0;
	
	/** Esprime in metri al secondo la componente orizzontale della velocit�. */
	private double velX = 0;
	
	/** Esprime in metri al secondo la componete verticale della velocit�. */
	private double velY = 0;	
	
	/** Esprime la larghezza in pixel dell'ellisse.*/
	private double xSize = 0;
	
	/** Esprime l'altezza in pixel dell'ellisse.*/
	private double ySize = 0;
	
	/** Esprime la massa della pallina espressa in kilogrammi */
	private double weight = 0;
	
	/** Rappresenta il colore del bordo dell'ellisse. */
	private Color borderColor;
	
	/** Rappresenta il colore di sfondo dell'ellisse. */
	private Color backgroundColor;
	
	/** Rappresenta il tempo espresso in secondi in cui la pallina � in aria. */
	double time = 0;
	
	/** Rappresenta il rettangolo entro cui � contenuta la pallina */
	private Environment env;
	
	
	/** Costruttore di default della pallina con tutti i campi dati settati a zero e colore impostato a nero. */
	public Ball() {
		borderColor = new Color(0,0,0);
		backgroundColor = new Color(0,0,0);
	}
	
	/** Costruttore parametrico della classe.
	 * @param x	Indica la posizione x.
	 * @param y Indica la posizione y.
	 * @param vx Indica la velocit� iniziale orizzontale.
	 * @param vy Indica la velocit� iniziale verticale.
	 * @param xs Indica la larghezza della pallina.
	 * @param ys Indica l'altezza della pallina.
	 * @param w Indica la massa della pallina.
	 * @param b1 Indica il colore del bordo della pallina.
	 * @param b2 Indica il colore dello sfondo della pallina.
	 * @param r Indica il rettangolo entro cui � contenuta la pallina.
	 */
	public Ball(double x, double y, double vx, double vy, double xs, double ys, double w, Color b1, Color b2, Environment e) {
		super(x,y,xs,ys);
		posX = x; posY = y;
		velX = vx; velY = vy;
		xSize = xs; ySize = ys;
		weight = w;
		borderColor = b1; backgroundColor = b2;
		env = e;
	}
	
	/** Imposta la posizione x della pallina espressa in pixel.
	 * @param d Posizione x della pallina espressa in pixel.
	 */
	void setPosX(double d) { posX = d; }
	
	/** Imposta la posizione y della pallina espressa in pixel
	 * @param d Posizione y della pallina espressa in pixel.
	 */
	void setPosY(double d) { posY = d; }
	
	/** Imposta la velocit� iniziale orizzontale della pallina espressa in metri al secondo.
	 * @param d Velocit� x della pallina espressa in metri al secondo.
	 */
	void setVelX(double d) { velX = d; }
	
	/** Imposta la velocit� iniziale verticale della pallina espressa in metri al secondo.
	 * @param d Velocit� y della pallina espressa in metri al secondo.
	 */
	void setVelY(double d) { velY = d; }
	
	/** Imposta la larghezza in pixel della pallina.
	 * @param d Larghezza in pixel della pallina.
	 */
	void setXSize(double d) { xSize = d; }
	
	/** Imposta l'altezza in pixel della pallina.
	 * @param d Altezza in pixel della pallina.
	 */
	void setYSize(double d) { ySize = d; }
	
	/** Imposta la massa della pallina espressa in kilogrammi.
	 * @param d Massa della pallina in kg.
	 */
	void setWeight(double d) { weight = d; }
	
	/** Imposta il colore del bordo della pallina.
	 * @param c Colore del bordo.
	 */
	void setBorderColor(Color c) { borderColor = c; }
	
	/** Imposta il colore dello sfondo della pallina.
	 * @param c Colore dello sfondo.
	 */
	void setBackgroundColor(Color c) { backgroundColor = c; }
	
	/** Restituisce la posizione x della pallina in pixel.
	 * @return posizione x della pallina.
	 */
	double getPosX() { return posX; }
	
	/** Restituisce la posizione y della pallina in pixel.
	 * @return posizione y della pallina.
	 */
	double getPosY() { return posY; }
	
	/** Restituisce la velocit� x della pallina in metri al secondo.
	 * @return velocit� x.
	 */
	double getVelX() { return velX; }
	
	/** Restituisce la velocit� y della pallina in metri al secondo.
	 * @return velocit� y.
	 */
	double getVelY() { return velY; }
	
	/** Restituisce la larghezza della pallina in pixel.
	 * @return larghezza della pallina.
	 */
	double getXSize() { return xSize; }
	
	/** Restituisce l'altezza della pallina in pixel.
	 * @return altezza della pallina.
	 */
	double getYSize() { return ySize; }
	
	/** Restituisce la massa della pallina in kg.
	 * @return massa della pallina.
	 */
	double getWeight() { return weight; }
	
	/** Restituisce il colore del bordo della pallina.
	 * @return colore bordo.
	 */
	Color getBorderColor() { return borderColor; }
	
	/** Restituisce il colore dello sfondo della pallina.
	 * @return colore sfondo.
	 */
	Color getBackgroundColor() { return backgroundColor; }
	
	/** Restituisce il centro della pallina sotto forma di punto bidimensionale.
	 * @return centro della pallina.
	 */
	Point2D.Double getCenter() { return new Point2D.Double(posX+xSize/2,posY+ySize/2); }
	
	/** Si occupa di far muovere la pallina nel pannello secondo le leggi fisiche del moto parabolico uniformemente accelerato.
	 * @param bounds pannello di riferimento sui cui si muove la pallina espresso come rettangolo bidimensionale.
	 */
	public void move() {
		double x = 0, y = 0;
		Rectangle2D bounds = env.getBounds();
		x = posX + velX*(time/100);
		if (velY != 0) y = posY -(velY*(time/100) - (0.5*9.81*Math.pow((time/100),2)));
		else y = bounds.getMaxY() - ySize;
		
		time += 1;
		
		if (y+ySize > bounds.getMaxY()) {	// Sto rimbalzando sul pavimento
			time = 0;
			posX = x;
			velY -= 1;
			posY = bounds.getMaxY() - ySize;
			if (velY < 0) velY = 0;
			
		}
		if (x+xSize >= bounds.getMaxX()) {	// Sto rimbalzando sulla parete destra
			velX = -velX +0.1;
			posX = x-xSize;
		}
		if (x <= bounds.getMinX()) {	// Sto rimbalzando sulla parete sinistra
			velX = -velX -0.1;
			posX = x;
		}
		setFrame(x,y,xSize,ySize);
		env.paint(env.getGraphics());
	
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.move();
		}
		
	}
}
