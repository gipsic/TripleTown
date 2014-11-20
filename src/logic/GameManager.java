package logic;

import java.awt.*;

import javax.swing.*;

import ui.GameLobby;
import ui.GameScreen;
import ui.GameWindow;
import ui.InputUtility;
import ui.SplashScreen;

public class GameManager {
	private MapUtility map = new MapUtility();
	private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	private static int opa = 0;
	
	public GameManager() {
	}

	public static void runGame(MainLogic gameLogic) {
		// TODO Auto-generated method stub
		GameWindow game = new GameWindow();
		SplashScreen splashScreen = new SplashScreen();
		GameLobby gameLobby = new GameLobby();
		GameScreen gameScreen = new GameScreen();
		
		game.setCurrentScene(splashScreen);
		game.add(game.getCurrentScene());
		game.pack();
		game.getCurrentScene().repaint();
		
		/*
		currentScene = new SplashScreen();
		currentScene.setPreferredSize(new Dimension(640, 480));
		
		gameWindow.add(currentScene);
		gameWindow.pack();
		*/
		game.setVisible(true);
		// TODO Auto-generated constructor stub
		
		while(true){
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(game.getCurrentScene() instanceof SplashScreen){
				//game.getCurrentScene().update(10,0);
				splashScreen.update();
				splashScreen.repaint();
				if(splashScreen.isFinished()){
					game.remove(splashScreen);
					game.validate();
					game.setCurrentScene(gameLobby);
					game.add(game.getCurrentScene());
					game.pack();
					gameLobby.update(gameLogic);
					game.getCurrentScene().repaint();
				}
			}
			else if (game.getCurrentScene() instanceof GameLobby){
				gameLobby.update(gameLogic);
				gameLobby.repaint();
				if(InputUtility.getMouseX() >= 585  && InputUtility.getMouseX() <= 615 &&
				   InputUtility.getMouseY() >= 425 && InputUtility.getMouseY() <= 455  &&
				   InputUtility.isMouseLeftClicked()){
					gameLogic.getPlayer().setEnableSound(!gameLogic.getPlayer().isEnableSound());
				}
				if(InputUtility.getMouseX() >= 220  && InputUtility.getMouseX() <= 420 &&
				   InputUtility.getMouseY() >= 330 && InputUtility.getMouseY() <= 380 &&
				   InputUtility.isMouseLeftClicked()){
					game.remove(gameLobby);
					game.validate();
					game.setCurrentScene(gameScreen);
					game.add(game.getCurrentScene());
					game.pack();
					gameScreen.update(gameLogic.getMapUtil());
					game.getCurrentScene().repaint();
				}
				
			}
			else if (game.getCurrentScene() instanceof GameScreen){
				gameScreen.update(gameLogic.getMapUtil());
				gameScreen.repaint();
			}
//			game.getCurrentScene().repaint();
//			if(game.getCurrentScene() instanceof GameScreen){
//				MainLogic.logicUpdate();
			ui.InputUtility.postUpdate();
//			}
		}
		
	}

}
