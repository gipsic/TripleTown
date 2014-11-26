package utility;

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
import javax.swing.JTextField;

import logic.MainLogic;
import logic.TownMap;

public class DrawingUtility {
	
	public static final int screenWidth = 640;
	public static final int screenHeight = 480;
	
	private static AlphaComposite opaque = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0);
	private static AffineTransform tranform;
	private static AffineTransformOp	tranformOp;
	
	private static BufferedImage Items = DrawingUtility.getImage("res/GameScreen/Items.png");
	private static BufferedImage ovl = DrawingUtility.getImage("res/GameScreen/Overlay.png");
	private static BufferedImage closeBut = DrawingUtility.getImage("res/GameScreen/button-close.png");
	private static BufferedImage yesnoBut = DrawingUtility.getImage("res/GameScreen/yes-no.png");
	private static BufferedImage HelpSceen = DrawingUtility.getImage("res/GameScreen/help-screen.png");
	private static BufferedImage BackhomeSceen = DrawingUtility.getImage("res/GameScreen/back-home.png");
	private static BufferedImage ScoreSceen = DrawingUtility.getImage("res/GameScreen/score-screen.png");
	private static BufferedImage NewRankSceen = DrawingUtility.getImage("res/GameScreen/NewRank.png");
	private static BufferedImage GameOverSceen = DrawingUtility.getImage("res/GameScreen/game-over.png");
	
	public static final JTextField townName = new JTextField("");
		
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
		for(int x = 10, i = 0; i < 6 ; i++, x += 70){
			for(int y = 50,j = 0; j < 6 ; j++, y += 70){
				if(map.getMapAt(i, j) != 0)
					if(map.getStateAt(i, j) == 3) {
						drawItems(g2, x,y,map.getMapAt(i, j), 1);
						int code = gameLogic.getPlayer().getCurrentItem();
						if(code>7) code -= 7;
						g2.drawImage(Items.getSubimage((code-1)*70, 245, 70, 70), InputUtility.getMouseX()-35, InputUtility.getMouseY()-35, null);
					} else
						drawItems(g2, x,y,map.getMapAt(i, j), map.getStateAt(i, j));
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
		
		g2.setFont(new Font("Tahoma",Font.BOLD,32));
		text = Score+"";
		padding = (6-text.length())*10;
		g2.drawString(text, 470+padding, 55);
		
		g2.setFont(new Font("Tahoma",Font.BOLD,36));
		text = Rank+"";
		padding = (2-text.length())*12;
		g2.drawString(text, 573+padding, 157);
		
		if(Rank > 1){
			g2.drawImage(panel, 460, 190, null);
			g2.drawImage(textBeat, 460, 190, null);
			g2.setFont(new Font("Tahoma",Font.BOLD,18));
			text = HighScoreUtility.getScoreOf(Rank-1)-Score+"";
			padding = (6-text.length())*6;
			g2.drawString(text, 512+padding, 210);
			text = Rank-1+"";
			padding = (2-text.length())*6;
			g2.drawString(text, 592+padding, 231);
		}
		
		g2.drawImage(buttonHelp, 510, 420, null);
		g2.drawImage(buttonSound, 580, 420, null);
		g2.drawImage(buttonHome, 470, 420, null);
	}
	
	public static void drawItems(Graphics2D g2, int x,int y,int code, int state){
		int subX, subY, w = 70, h = 70;
		if(state == 1){
			h = 105;
			if(code > 7){ subY = 140; code -=7; }
			else subY = 0;
			subX = (code-1)*70;
			BufferedImage item = Items.getSubimage(subX, subY, w, h);
			g2.drawImage(item, x, y-35, null);
		} else if(state == 2){
			subY = 245; 
			if(code>7) code -= 7;
			subX = (code-1)*70;
			BufferedImage item = Items.getSubimage(subX, subY, w, h);
			g2.drawImage(Items.getSubimage(420, 245, 70, 70), x, y, null);
			g2.drawImage(item, x, y, null);
		}
	}

	public static void drawOverlayHelp(Graphics2D g2, int tick, int closeButY){
		float opacity = 0;
		if(tick < 50)
			opacity = tick / 50;
		else 
			opacity = 1;
		
		g2.drawImage(ovl, 0, 0, null);
		if(tick >= 10) {
			g2.drawImage(HelpSceen, 0, 0, null);
			g2.drawImage(closeBut.getSubimage(0, closeButY, 36, 36), 540, 35, null);
			
		}
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_IN,opacity));
		
	}
	public static void drawOverlayScore(Graphics2D g2, int tick, int closeButY){
		float opacity = 0;
		if(tick < 50)
			opacity = tick / 50;
		else 
			opacity = 1;
		
		g2.drawImage(ovl, 0, 0, null);
		if(tick >= 10) {
			g2.drawImage(ScoreSceen, 0, 0, null);
			g2.setFont(new Font("Tahoma",Font.PLAIN,24));
			g2.setColor(new Color(132,94,55));
			String[] record = HighScoreUtility.getTop20();
			int y = 85;
			for(int a = 0;a <20;a++){
				int x = (a%2 == 0)? 75:330;
				y += (a%2 == 0)? 32:0;
				if(record[a] == null) continue;
				String c = record[a];
				g2.drawString(c.substring(0, c.indexOf("\t")), x, y);
				g2.drawString(c.substring(c.indexOf("\t")+1), x+140, y);
			}
			g2.drawImage(closeBut.getSubimage(0, closeButY, 36, 36), 540, 35, null);
			
		}
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_IN,opacity));
		
	}
	public static void drawOverlayBackHome(Graphics2D g2, int tick, int closeButY, int noButY, int yesButY){
		float opacity = 0;
		if(tick < 50)
			opacity = tick / 50;
		else 
			opacity = 1;
		
		g2.drawImage(ovl, 0, 0, null);
		if(tick >= 10) {
			g2.drawImage(BackhomeSceen, 0, 105, null);
			g2.drawImage(closeBut.getSubimage(0, closeButY, 36, 36), 560, 130, null);
			g2.drawImage(yesnoBut.getSubimage(0, yesButY, 138, 44), 140, 230, null);
			g2.drawImage(yesnoBut.getSubimage(138, noButY, 138, 44), 368, 230, null);
		}
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_IN,opacity));
		
	}
	public static void drawOverlayNewRank(Graphics2D g2, int tick, int rank, int okButY){
		float opacity = 0;
		if(tick < 50)
			opacity = tick / 50;
		else 
			opacity = 1;
		
		g2.drawImage(ovl, 0, 0, null);
		g2.setFont(new Font("Tahoma",Font.BOLD,26));
		g2.setColor(Color.black);
		if(tick >= 10) {
			g2.drawImage(NewRankSceen, 0, 105, null);
			g2.drawString(""+rank, 462, 207);
			g2.drawImage(yesnoBut.getSubimage(276, okButY, 122, 44), 260, 270, null);
			DrawingUtility.townName.setColumns(8);
			DrawingUtility.townName.setBackground(new Color(255,239,211));
			DrawingUtility.townName.setFont(new Font("Tahoma",Font.BOLD,26));
			DrawingUtility.townName.setBounds(220, 217, 170, 35);
		}
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_IN,opacity));
		
	}
	public static void drawOverlayGameOver(Graphics2D g2, int tick, int score, int okButY){
		float opacity = 0;
		if(tick < 50)
			opacity = tick / 50;
		else 
			opacity = 1;
		
		g2.drawImage(ovl, 0, 0, null);
		g2.setFont(new Font("Tahoma",Font.BOLD,22));
		g2.setColor(Color.black);
		if(tick >= 10) {
			g2.drawImage(GameOverSceen, 0, 105, null);
			String text = score + "";
			g2.drawString(text, 280+(7-text.length())*8, 257);
			g2.drawImage(yesnoBut.getSubimage(276, okButY, 122, 44), 260, 270, null);
		}
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_IN,opacity));
		
	}
	
	public static int onXcell(){
		int mouseX = InputUtility.getMouseX();
		int mouseY = InputUtility.getMouseY();
		int cellX = -1;
		int cellY = -1;
		for(int x = 10, i = 0; i < 6 ; i++, x += 70){
			for(int y = 50,j = 0; j < 6 ; j++, y += 70){
				if(mouseX>=x && mouseX <x+70 &&mouseY>=y && mouseY <y+70) {
					cellX = i;
					cellY = j;
				}
			}
		}
		return cellX;
	}
	public static int onYcell(){
		int mouseX = InputUtility.getMouseX();
		int mouseY = InputUtility.getMouseY();
		int cellX = -1;
		int cellY = -1;
		for(int x = 10, i = 0; i < 6 ; i++, x += 70){
			for(int y = 50,j = 0; j < 6 ; j++, y += 70){
				if(mouseX>=x && mouseX <x+70 &&mouseY>=y && mouseY <y+70) {
					cellX = i;
					cellY = j;
				}
			}
		}
		return cellY;
	}
	

}
