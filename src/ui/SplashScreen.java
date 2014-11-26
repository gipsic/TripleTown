package ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import utility.DrawingUtility;

public class SplashScreen extends JPanel {
	
	protected static final BufferedImage splashBG = DrawingUtility.getImage("res/SplashScreen/SplashBG.png");
	protected static final BufferedImage splashTitle = DrawingUtility.getImage("res/SplashScreen/SplashTitle.png");
	protected int opa = 0;
	protected int rotate = 0;
	protected int count = 0;
	protected int fadeSpeed = 4;
	protected boolean rotateCCW = false;
	protected boolean finished = false;
	
	
	public SplashScreen(){
		setDoubleBuffered(true);
		this.setLayout(null);
		this.setPreferredSize(new Dimension(DrawingUtility.screenWidth,DrawingUtility.screenHeight));
	}
	
	public boolean isFinished(){
		return finished;
	}
	
	public void update(){
		//140 Steps : 14 wait + 34 fadeIn + 60 rotate + 32 wait&fadeOut
		if(count < 14) {
			this.rotate = 0;
			this.opa = 0;
		} else if(count < 48){
			if(this.opa+fadeSpeed<=100)
				this.opa += fadeSpeed;
			this.rotate = 0;
		} else if(count < 108){
			if(this.rotateCCW){
				this.rotate += 2;
				if(this.rotate>=10) {
					this.rotateCCW = false;
				}
			} else {
				this.rotate -= 2;
				if(this.rotate<=-10) {
					this.rotateCCW = true;
				}
			}
		} else if(count < 140) {
			if(this.opa-fadeSpeed>=0)
				this.opa -= fadeSpeed;
			this.rotate = 0;
		} else {
			finished = true;
		}
		count++;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		DrawingUtility.drawSplashScreen(g2, opa, rotate, splashBG, splashTitle);
	}
}
