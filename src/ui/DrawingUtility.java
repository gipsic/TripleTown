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

import logic.HighScoreUtility;
import logic.MainLogic;
import logic.TownMap;

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
		g2.drawImage(splashTitle, tranformOp, 70,320);
	}
	
	public static void drawLobbyScreen(Graphics2D g2,BufferedImage sky,BufferedImage globe,
			BufferedImage bear,BufferedImage title,BufferedImage buttonHelp,
			BufferedImage buttonPlay,BufferedImage buttonScore,BufferedImage buttonSound,int BearX, int BearY, double BearA){
		g2.drawImage(sky, 0, 0, null);
		tranform = new AffineTransform();
		tranform.rotate(BearA, 45, 53); 
		tranformOp = new AffineTransformOp(tranform, AffineTransformOp.TYPE_BICUBIC);			
		g2.drawImage(bear, tranformOp, 350+BearX, 230+BearY);
		g2.drawImage(globe, -96, 120, null);
		g2.drawImage(title, 120, 25, null);
		g2.drawImage(buttonHelp, 20, 420, null);
		g2.drawImage(buttonSound, 580, 420, null);
		g2.drawImage(buttonScore, 260, 403, null);
		g2.drawImage(buttonPlay, 215, 325, null);
	}
	
	public static void drawGameScreen(Graphics2D g2, MainLogic gameLogic, BufferedImage buttonHelp,BufferedImage buttonSound,BufferedImage buttonHome){

		BufferedImage field = DrawingUtility.getImage("res/GameScreen/fieldPattern.png");
		for(int x = -60; x <= 640 ; x += 70){
			for(int y = -20; y <= 480; y += 70){
				g2.drawImage(field, x, y, null);
			}
		}
		
		TownMap map = gameLogic.getMapUtil();
		for(int x = 10, i = 0; i < 6 ; i++, x += 70){
			for(int y = 50,j = 0; j < 6 ; j++, y += 70){
				BufferedImage ground = map.calcBG(i,j);
				if(ground != null) g2.drawImage(ground, x, y, null);
			}
		}
		
		
		BufferedImage panel = DrawingUtility.getImage("res/GameScreen/panel-score.png");
		BufferedImage panelRank = DrawingUtility.getImage("res/GameScreen/panel-rank.png");

		BufferedImage textScore = DrawingUtility.getImage("res/GameScreen/text-score.png");
		BufferedImage textBeat = DrawingUtility.getImage("res/GameScreen/text-beat.png");
		BufferedImage textRank = DrawingUtility.getImage("res/GameScreen/text-rank.png");
		BufferedImage textYourRank = DrawingUtility.getImage("res/GameScreen/text-yourrank.png");
		
		g2.setColor(Color.WHITE);
		
		g2.drawImage(panel, 460, 20, null);
		g2.drawImage(panelRank, 460, 90, null);
		
		
		g2.drawImage(textScore, 460, 20, null);
		g2.drawImage(textYourRank, 460, 90, null);
		g2.drawImage(textRank, 460, 90, null);
		
		
		int padding = 0;
		String text = "";
		
		int Score = gameLogic.getPlayer().getScore();
		int Rank = HighScoreUtility.calcRank(Score);
		
		g2.setFont(new Font("Tahoma",Font.BOLD,36));
		text = Score+"";
		padding = (5-text.length())*12;
		g2.drawString(text, 470+padding, 55);
		
		text = Rank+"";
		padding = (2-text.length())*12;
		g2.drawString(text, 573+padding, 157);
		
		if(Rank > 1){
			g2.drawImage(panel, 460, 190, null);
			g2.drawImage(textBeat, 460, 190, null);
			g2.setFont(new Font("Tahoma",Font.BOLD,18));
			text = HighScoreUtility.getScoreOf(Rank-1)-Score+"";
			padding = (5-text.length())*6;
			g2.drawString(text, 517+padding, 210);
			text = Rank-1+"";
			padding = (2-text.length())*6;
			g2.drawString(text, 592+padding, 231);
		}
		
		g2.drawImage(buttonHelp, 510, 420, null);
		g2.drawImage(buttonSound, 580, 420, null);
		g2.drawImage(buttonHome, 470, 420, null);
	}


}
