package Rimbalzi.gui;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;



public class Ball extends Ellipse2D.Double {
	private double posX = 0;
	private double posY = 0;
	private double velX = 0;	// velocità espressa in m/s
	private double velY = 0;	// velocità espressa in m/s
	private double xSize = 0;
	private double ySize = 0;
	private double weight = 0;		// massa espressa in kg
	private Color borderColor;
	private Color backgroundColor;
	
	public Ball() {
		borderColor = new Color(0,0,0);
		backgroundColor = new Color(0,0,0);
	}
	public Ball(double x, double y, double vx, double vy, double xs, double ys, double w, Color b1, Color b2) {
		super(x,y,xs,ys);
		posX = x; posY = y;
		velX = vx; velY = vy;
		xSize = xs; ySize = ys;
		weight = w;
		borderColor = b1; backgroundColor = b2;
	}
	
	/* definizione dei metodi setter */
	void setPosX(double d) { posX = d; }
	void setPosY(double d) { posY = d; }
	void setVelX(double d) { velX = d; }
	void setVelY(double d) { velY = d; }
	void setXSize(double d) { xSize = d; }
	void setYSize(double d) { ySize = d; }
	void setWeight(double d) { weight = d; }
	void setBorderColor(Color c) { borderColor = c; }
	void setBackgroundColor(Color c) { backgroundColor = c; }
	
	/* definizione dei metodi getter */
	double getPosX() { return posX; }
	double getPosY() { return posY; }
	double getVelX() { return velX; }
	double getVelY() { return velY; }
	double getXSize() { return xSize; }
	double getYSize() { return ySize; }
	double getWeight() { return weight; }
	Color getBorderColor() { return borderColor; }
	Color getBackgroundColor() { return backgroundColor; }
	
	Point2D.Double getCenter() { return new Point2D.Double(posX+xSize/2,posY+ySize/2); }
	
	public void move(Rectangle2D bounds) {
		posX += velX;
		posY += velY;
		
		if (posX < bounds.getMinX()) { x = bounds.getMinX(); velX = -velX; }
		if (posX + xSize >= bounds.getMaxX()) {
			posX = bounds.getMaxX() - xSize; velX = -velX;
		}
		if (posY < bounds.getMinY()) { 
			posY = bounds.getMinY(); velY = -velY;
		}
		if (posY + ySize >= bounds.getMaxY()) {
			posY = bounds.getMaxY() - ySize;
			velY = -velY;
		}
		setFrame(posX,posY,xSize,ySize);
	}
}
