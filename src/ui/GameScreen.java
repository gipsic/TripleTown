package ui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Map;

import logic.Configuration;
import logic.GameManager;
import logic.MainLogic;
import logic.TownMap;

public class GameScreen extends JPanel {
	
	private JPanel optionPanel;
	private JPanel mapPanel;
	private JButton HighScore, Homebutton, enableSoundButon;
	private MainLogic gameLogic;
	private int buttonHomeY = 0, buttonHelpY = 0, buttonSoundY = 0, buttonSoundX = 0;
	BufferedImage buttonHelp = DrawingUtility.getImage("res/GameScreen/button-help.png");
	BufferedImage buttonSound = DrawingUtility.getImage("res/GameScreen/button-sound.png");
	BufferedImage buttonHome = DrawingUtility.getImage("res/GameScreen/button-home.png");


	public GameScreen(MainLogic gameLogic) {
		this.gameLogic = gameLogic;
		setDoubleBuffered(true);
		this.setLayout(null);
		this.setPreferredSize(new Dimension(Configuration.screenWidth,Configuration.screenHeight));
	}
	
	public void update() {
	
		int mouseX = InputUtility.getMouseX();
		int mouseY = InputUtility.getMouseY();
		
		buttonHomeY = 0;
		buttonHelpY = 0;
		buttonSoundY = 0;

		if (mouseX >= 470 && mouseX < 510 && mouseY >= 420 && mouseY <= 460)
			buttonHomeY = 40;
		if (mouseX >= 510 && mouseX < 580 && mouseY >= 420 && mouseY <= 460)
			buttonHelpY = 40;
		if (mouseX >= 580 && mouseX < 620 && mouseY >= 420 && mouseY <= 460)
			buttonSoundY = 40;
		
		buttonSoundX = (gameLogic.getPlayer().isEnableSound()) ? 0 : 40;
				
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		BufferedImage ActButHelp = buttonHelp.getSubimage(0, buttonHelpY, 70, 40);
		BufferedImage ActButSound = buttonSound.getSubimage(buttonSoundX, buttonSoundY, 40, 40);
		BufferedImage ActButHome = buttonHome.getSubimage(0, buttonHomeY, 40, 40);
		DrawingUtility.drawGameScreen(g2, gameLogic, ActButHelp, ActButSound, ActButHome);	
	}
	
}
