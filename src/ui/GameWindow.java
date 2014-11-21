package ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import logic.Configuration;

public class GameWindow extends JFrame{

	private JPanel currentScene;
	private JPanel nextScene;
	private JFrame gameWindow;
	private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	
	public GameWindow(){
		super("Triple Town");
		currentScene = null;
		nextScene = null;
		this.addListener();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(screen.width / 2 - 320 -3, screen.height / 2 - 240 -26);
		this.setResizable(false);
		this.setFocusable(true);
//		this.setUndecorated(true);
		this.getContentPane().setPreferredSize(new Dimension(Configuration.screenWidth,Configuration.screenHeight));
	}
	
	public void switchScene(JPanel scene){
		getContentPane().remove(currentScene);
		this.currentScene = scene;
		getContentPane().add(currentScene);
		getContentPane().validate();
		pack();
		currentScene.requestFocus();
	}

	private void addListener(){
		
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				InputUtility.setMouseLeftDown(false);
				InputUtility.setMouseLeftTriggered(true);
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				InputUtility.setMouseLeftDown(true);
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				InputUtility.setMouseOnScreen(false);	
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				InputUtility.setMouseOnScreen(true);
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		
		this.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				InputUtility.setMouseX(arg0.getX()-3);
				InputUtility.setMouseY(arg0.getY()-26);
			}
			
			@Override
			public void mouseDragged(MouseEvent arg0) {
				InputUtility.setMouseX(arg0.getX()-3);
				InputUtility.setMouseY(arg0.getY()-26);				
			}
		});
		
		this.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				InputUtility.setKeyPressed(arg0.getKeyCode(), false);
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				InputUtility.setKeyPressed(arg0.getKeyCode(), true);
				InputUtility.setKeyTriggered(arg0.getKeyCode(), true);
			}
		});
	}

	//-----getter mothods -----
	public JPanel getCurrentScene() {
		return currentScene;
	}

	public JPanel getNextScene() {
		return nextScene;
	}

	public JFrame getGameWindow() {
		return gameWindow;
	}

	public Dimension getScreen() {
		return screen;
	}

	//-----setter mothods -----
	public void setCurrentScene(JPanel currentScene) {
		this.currentScene = currentScene;
	}

	public void setNextScene(JPanel nextScene) {
		this.nextScene = nextScene;
	}

	public void setGameWindow(JFrame gameWindow) {
		this.gameWindow = gameWindow;
	}

	public void setScreen(Dimension screen) {
		this.screen = screen;
	}
		
}
