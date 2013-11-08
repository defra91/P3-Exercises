package Rimbalzi.gui;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import java.lang.Math;



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
	int time = 0;
	
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
		double x = posX + velX*(time/100);
		double y = posY - (velY*(time/100) - (0.5*9.81*Math.pow((time/100),2)));
		
		time += 10;
		
		if (y+ySize > bounds.getMaxY()) {
			time = 0;
			posX = x;
			velY -= 5;
			
		}
		setFrame(x,y,xSize,ySize);
	}
}
