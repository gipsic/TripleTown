package logic;

import java.awt.Dimension;
import java.awt.Toolkit;

import ui.LobbyScreen;
import ui.GameScreen;
import ui.GameWindow;
import ui.SplashScreen;
import utility.AudioUtility;
import utility.DrawingUtility;
import utility.InputUtility;

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
		LobbyScreen lobbyScreen = new LobbyScreen(gameLogic);
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
					game.switchScene(lobbyScreen);
					lobbyScreen.repaint();
					if(init){
						introSound.start();
						init = false;
					}
				}
			} else if (game.getCurrentScene() instanceof LobbyScreen) {
				lobbyScreen.update();
				lobbyScreen.repaint();

				if(InputUtility.isMouseLeftClicked() && !gameLogic.getPlayer().isPause()) {
					//System.out.println("Clicked "+mouseX+" "+mouseY);
					if (mouseX >= 585 && mouseX <= 615 && mouseY >= 425 && mouseY <= 455) {
						//Click Sound Button
						gameLogic.getPlayer().setEnableSound(!gameLogic.getPlayer().isEnableSound());
					} else if (mouseX >= 220 && mouseX <= 420 && mouseY >= 330 && mouseY <= 380) {
						//Click Play
						game.switchScene(gameScreen);
						gameScreen.repaint();
					} else if (mouseX >= 25 && mouseX <= 85 && mouseY >= 425 && mouseY <= 455) {
						//Click Help
						gameLogic.getPlayer().setPause(true);
						lobbyScreen.setOverlay(1);

					} else if (mouseX >= 265 && mouseX <= 375 && mouseY >= 405 && mouseY <= 437) {
						//Click Rank
						gameLogic.getPlayer().setPause(true);
						lobbyScreen.setOverlay(2);
					}
				}

			} else if (game.getCurrentScene() instanceof GameScreen) {
				gameScreen.update();
				gameScreen.repaint();
				
				if(gameScreen.isGameEnd()){
					gameScreen.setGameEnd(false);
					gameLogic.reset();
					game.switchScene(lobbyScreen);
					lobbyScreen.repaint();
				} else if(InputUtility.isMouseLeftClicked() && !gameLogic.getPlayer().isPause()) {

					if (mouseX >= 580 && mouseX < 620 && mouseY >= 420 && mouseY < 460) {
						//Click Sound Button
						gameLogic.getPlayer().setEnableSound(!gameLogic.getPlayer().isEnableSound());
					} else if (mouseX >= 510 && mouseX < 580 && mouseY >= 420 && mouseY < 460) {
						//Click Help
						gameLogic.getPlayer().setPause(true);
						gameScreen.setOverlay(1);

					} else if (mouseX >= 470 && mouseX < 510 && mouseY >= 420 && mouseY < 460) {
						//Click Home
						gameLogic.getPlayer().setPause(true);
						gameScreen.setOverlay(3);
					}
				}
			}
			
			InputUtility.postUpdate();

		}

	}

}
