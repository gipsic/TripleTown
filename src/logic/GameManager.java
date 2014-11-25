package logic;

import java.awt.Dimension;
import java.awt.Toolkit;

import ui.AudioUtility;
import ui.DrawingUtility;
import ui.GameLobby;
import ui.GameScreen;
import ui.GameWindow;
import ui.InputUtility;
import ui.SplashScreen;

public class GameManager {
	private TownMap map = new TownMap();
	private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	private static int opa = 0;

	public GameManager() {
	}

	public static void runGame(MainLogic gameLogic) {
		// TODO Auto-generated method stub
		GameWindow game = new GameWindow();
		SplashScreen splashScreen = new SplashScreen();
		GameLobby gameLobby = new GameLobby(gameLogic);
		GameScreen gameScreen = new GameScreen(gameLogic);

		game.setCurrentScene(splashScreen);
		game.add(game.getCurrentScene());
		game.pack();
		

		game.setVisible(true);
		
		Thread introSound = new Thread(new AudioUtility(gameLogic));
		boolean init = true;

		while (true) {
			
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			int mouseX = InputUtility.getMouseX();
			int mouseY = InputUtility.getMouseY();
			
			if (game.getCurrentScene() instanceof SplashScreen) {
				splashScreen.update();
				splashScreen.repaint();

				if (splashScreen.isFinished()) {
					game.switchScene(gameLobby);
					gameLobby.update();
					gameLobby.repaint();
					if(init){
						introSound.start();
						init = false;
					}
				}
			} else if (game.getCurrentScene() instanceof GameLobby) {
				gameLobby.update();
				gameLobby.repaint();

				if(InputUtility.isMouseLeftClicked()) {
					//System.out.println("Clicked "+mouseX+" "+mouseY);
					if (mouseX >= 585 && mouseX <= 615 && mouseY >= 425 && mouseY <= 455) {
						//Click Sound Button
						gameLogic.getPlayer().setEnableSound(!gameLogic.getPlayer().isEnableSound());
					} else if (mouseX >= 220 && mouseX <= 420 && mouseY >= 330 && mouseY <= 380) {
						//Click Play
						game.switchScene(gameScreen);
						gameScreen.update();
						gameScreen.repaint();
					} else if (mouseX >= 25 && mouseX <= 85 && mouseY >= 425 && mouseY <= 455) {
						//Click Help


					} else if (mouseX >= 265 && mouseX <= 375 && mouseY >= 405 && mouseY <= 437) {
						//Click Rank


					}
				}

			} else if (game.getCurrentScene() instanceof GameScreen) {
				gameScreen.update();
				gameScreen.repaint();

				if(InputUtility.isMouseLeftClicked() && !gameLogic.getPlayer().isPause()) {
					if (mouseX >= 585 && mouseX <= 615 && mouseY >= 425 && mouseY <= 455) {
						//Click Sound Button
						gameLogic.getPlayer().setEnableSound(!gameLogic.getPlayer().isEnableSound());
					} else if (mouseX >= 25 && mouseX <= 85 && mouseY >= 425 && mouseY <= 455) {
						//Click Help

					} else if (mouseX >= 265 && mouseX <= 375 && mouseY >= 405 && mouseY <= 437) {
						//Click Home
						game.switchScene(gameLobby);
						gameLobby.update();
						gameLobby.repaint();
					}
				}
			}
			
			InputUtility.postUpdate();

		}

	}

}
