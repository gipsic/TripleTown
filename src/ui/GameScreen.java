package ui;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import logic.Configuration;
import logic.MainLogic;

public class GameScreen extends JPanel {
	
	private int overlay = 0;
	private int ovlTick = 0;
	private int CloseButY = 0, yesButY = 0, noButY = 0;
	private boolean gameEnd = false;
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
	
	public void setOverlay(int overlay){
		this.overlay = overlay;
	}
	
	public void clearOverlay(){
		this.overlay = 0;
	}
	
	public boolean isGameEnd(){
		return this.gameEnd;
	}
	
	public void setGameEnd(boolean end){
		this.gameEnd = end;
	}
	
	public void update() {
	
		int mouseX = InputUtility.getMouseX();
		int mouseY = InputUtility.getMouseY();
		
		if(overlay == 1) {
			if(mouseX >= 540 && mouseX <= 576 && mouseY >= 35 && mouseY <= 71) {
				//On Close Button
				if(InputUtility.isMouseLeftClicked()){
					CloseButY = 0;
					ovlTick = -1;
					gameLogic.getPlayer().setPause(false);
					clearOverlay();
				} else {
					CloseButY = 36;
				}
			} else {
				CloseButY = 0;
			}
			ovlTick++;
			
		} else if(overlay == 3) {
			if(mouseX >= 560 && mouseX <= 596 && mouseY >= 130 && mouseY <= 166) {
				//On Close Button
				if(InputUtility.isMouseLeftClicked()){
					CloseButY = 0;
					ovlTick = -1;
					gameLogic.getPlayer().setPause(false);
					clearOverlay();
				} else {
					CloseButY = 36;
				}
			} else if(mouseX >= 140 && mouseX <= 278 && mouseY >= 230 && mouseY <= 274) {
				//On Yes Button
				if(InputUtility.isMouseLeftClicked()){
					yesButY = 0;
					ovlTick = -1;
					gameLogic.getPlayer().setPause(false);
					clearOverlay();
					gameEnd = true;
				} else {
					yesButY = 44;
				}
			} else if(mouseX >= 368 && mouseX <= 506 && mouseY >= 230 && mouseY <= 274) {
				//On No Button
				if(InputUtility.isMouseLeftClicked()){
					noButY = 0;
					ovlTick = -1;
					gameLogic.getPlayer().setPause(false);
					clearOverlay();
				} else {
					noButY = 44;
				}
			} else {
				CloseButY = 0;
				noButY = 0;
				yesButY = 0;
			}
			ovlTick++;
			
		} else {
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
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		BufferedImage ActButHelp = buttonHelp.getSubimage(0, buttonHelpY, 70, 40);
		BufferedImage ActButSound = buttonSound.getSubimage(buttonSoundX, buttonSoundY, 40, 40);
		BufferedImage ActButHome = buttonHome.getSubimage(0, buttonHomeY, 40, 40);
		DrawingUtility.drawGameScreen(g2, gameLogic, ActButHelp, ActButSound, ActButHome);	
		if(overlay == 1) {
			DrawingUtility.drawOverlayHelp(g2, ovlTick, CloseButY);
		} else if(overlay == 3) {
			DrawingUtility.drawOverlayBackHome(g2, ovlTick, CloseButY, noButY, yesButY);
		}
	}
	
}
