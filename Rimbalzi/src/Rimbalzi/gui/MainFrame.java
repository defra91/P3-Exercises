package Rimbalzi.gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainFrame extends JFrame {
	private Environment e;
	private JButton start;
	private JButton stop;
	private JButton resume;
	Thread t;
	
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
