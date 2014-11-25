package ui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import logic.Configuration;
import logic.GameManager;
import logic.MainLogic;

public class GameLobby extends JPanel {

	protected static final BufferedImage sky = DrawingUtility.getImage("res/LobbyScreen/Sky.png");
	protected static final BufferedImage globe = DrawingUtility.getImage("res/LobbyScreen/Globe.png");
	public static final BufferedImage preview = DrawingUtility.getImage("res/LobbyScreen/--Preview.png");
	protected static BufferedImage bear = DrawingUtility.getImage("res/LobbyScreen/Bear.png");
	protected static BufferedImage title = DrawingUtility.getImage("res/LobbyScreen/Title.png");
	protected static BufferedImage buttonHelp = DrawingUtility.getImage("res/LobbyScreen/button-help.png").getSubimage(0, 40, 70, 40);
	protected static BufferedImage buttonPlay = DrawingUtility.getImage("res/LobbyScreen/button-play.png").getSubimage(0, 60, 210, 60);
	protected static BufferedImage buttonScore = DrawingUtility.getImage("res/LobbyScreen/button-score.png").getSubimage(0, 42, 120, 42);
	protected static BufferedImage buttonSound = DrawingUtility.getImage("res/LobbyScreen/button-sound.png").getSubimage(0, 40, 40, 40);
	private JPanel optionPanel;
	private JButton newGame, viewScore;
	private int BearX = 0, BearY = 0, count = 0;
	private double BearA = 0.0;
	private MainLogic gameLogic;


	public GameLobby(MainLogic gameLogic) {
		this.gameLogic = gameLogic;
		setDoubleBuffered(true);
		this.setLayout(null);
		this.setPreferredSize(new Dimension(Configuration.screenWidth, Configuration.screenHeight));

	}

	public void update() {

		int mouseX = InputUtility.getMouseX();
		int mouseY = InputUtility.getMouseY();
		// System.out.println(mouseX +" "+ mouseY);

		buttonHelp = DrawingUtility.getImage("res/LobbyScreen/button-help.png");
		buttonSound = DrawingUtility.getImage("res/LobbyScreen/button-sound.png");
		buttonScore = DrawingUtility.getImage("res/LobbyScreen/button-score.png");
		buttonPlay = DrawingUtility.getImage("res/LobbyScreen/button-play.png");

		int soundY = (gameLogic.getPlayer().isEnableSound()) ? 0 : 40;

		if (mouseX >= 25 && mouseX <= 85 && mouseY >= 425 && mouseY <= 455)
			buttonHelp = buttonHelp.getSubimage(0, 40, 70, 40);
		else
			buttonHelp = buttonHelp.getSubimage(0, 0, 70, 40);

		if (mouseX >= 585 && mouseX <= 615 && mouseY >= 425 && mouseY <= 455)
			buttonSound = buttonSound.getSubimage(soundY, 40, 40, 40);
		else
			buttonSound = buttonSound.getSubimage(soundY, 0, 40, 40);

		if (mouseX >= 265 && mouseX <= 375 && mouseY >= 405 && mouseY <= 437)
			buttonScore = buttonScore.getSubimage(0, 42, 120, 42);
		else
			buttonScore = buttonScore.getSubimage(0, 0, 120, 42);

		if (mouseX >= 220 && mouseX <= 420 && mouseY >= 330 && mouseY <= 380)
			buttonPlay = buttonPlay.getSubimage(0, 60, 210, 60);
		else
			buttonPlay = buttonPlay.getSubimage(0, 0, 210, 60);
		
		if(count < 25) {
			BearX += count%2;
			BearY -= 2;
			BearA += 0.02;
		} else if(count < 50){
			BearX -= 2;
			BearY += 2;
			BearA -= 0.03;
		} else if(count < 75){
			BearX += 2;
			BearY -= 2;
			BearA += 0.03;
		} else if(count < 100) {
			BearX -= count%2;
			BearY += 2;
			BearA -= 0.02;
		} else {
			count = 0;
		}
		count++;		

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		DrawingUtility.drawLobbyScreen(g2, sky, globe, bear, title, buttonHelp, buttonPlay, buttonScore, buttonSound, BearX, BearY, BearA);
	}
}
