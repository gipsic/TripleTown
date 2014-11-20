package ui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import logic.MapUtility;

public class DrawingUtility {
	
	protected static AlphaComposite opaque = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0);
	protected static AffineTransform tranform;
	protected static AffineTransformOp	tranformOp;
	
	public static BufferedImage getImage(String directory){
		BufferedImage image = null;
		try {
			//image = ImageIO.read(new File("bin/"+directory));
			image = ImageIO.read(DrawingUtility.class.getClassLoader().getResource(directory));
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Can't load "+directory, "Error loading image file.", JOptionPane.ERROR_MESSAGE);
		}
		return image;
	}
	
	public static void drawSplashScreen(Graphics2D g2, int opa,int rotate,BufferedImage splashBG, BufferedImage splashTitle){
		g2.drawImage(splashBG,0,0,null);
		opaque = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)(opa/100.0));
		g2.setComposite(opaque);
		//tranform.setToRotation(-180, 0);
		tranform = new AffineTransform();
		tranform.rotate((double)(rotate/100.0), 250, 65); 
		tranformOp = new AffineTransformOp(tranform, AffineTransformOp.TYPE_BICUBIC);
		//g2.drawImage(splashTitle, 70, 320, null);
		g2.drawImage(splashTitle, tranformOp, 70,320);
	}
	
	public static void drawLobbyScreen(Graphics2D g2,BufferedImage sky,BufferedImage globe,
			BufferedImage bear,BufferedImage title,BufferedImage buttonHelp,
			BufferedImage buttonPlay,BufferedImage buttonScore,BufferedImage buttonSound){
		g2.drawImage(sky, 0, 0, null);
		g2.drawImage(globe, -96, 120, null);
		g2.drawImage(GameLobby.preview, 0, 0, null);
		g2.drawImage(title, 120, 25, null);
		g2.drawImage(buttonHelp, 20, 420, null);
		g2.drawImage(buttonSound, 580, 420, null);
		g2.drawImage(buttonScore, 260, 403, null);
		g2.drawImage(buttonPlay, 215, 325, null);
	}
	
	public static void drawGameScreen(Graphics2D g2){
		g2.drawRect(20, 20, 420, 440);
		g2.drawRect(450, 20, 170, 440);
		
		
	}
	
	public static void drawMap(Graphics2D g2){
		int[][] map = MapUtility.getMap();
		int size = MapUtility.getSize();
		for(int i=0;i<map.length;i++){
			for(int j=0;j<map[0].length;j++){
				g2.drawRect(i*size+20, j*size+20, size, size);
			}
		}
	}
	
	public static void drawStatusBar(Graphics2D g2, String playerName, int score, int currentItem,boolean pause,boolean enableSound){
		
	}

}
