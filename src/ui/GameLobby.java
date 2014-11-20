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
	protected static final BufferedImage bear = DrawingUtility.getImage("res/LobbyScreen/Bear.png");
	protected static final BufferedImage title = DrawingUtility.getImage("res/LobbyScreen/Title.png");
	protected static BufferedImage buttonHelp = DrawingUtility.getImage("res/LobbyScreen/button-help.png").getSubimage(0, 40, 70, 40);
	protected static BufferedImage buttonPlay = DrawingUtility.getImage("res/LobbyScreen/button-play.png").getSubimage(0, 60, 210, 60);
	protected static BufferedImage buttonScore = DrawingUtility.getImage("res/LobbyScreen/button-score.png").getSubimage(0, 42, 120, 42);
	protected static BufferedImage buttonSound = DrawingUtility.getImage("res/LobbyScreen/button-sound.png").getSubimage(0, 40, 40, 40);
	private JPanel optionPanel;
	private JButton newGame, viewScore;

	public GameLobby() {
		setDoubleBuffered(true);
		this.setLayout(null);
		this.setPreferredSize(new Dimension(Configuration.screenWidth,Configuration.screenHeight));
		
		
	}
	
	public void update(MainLogic gameLogic){
//		System.out.println(InputUtility.getMouseX() +" "+ InputUtility.getMouseY());
		buttonHelp = DrawingUtility.getImage("res/LobbyScreen/button-help.png");
		if(InputUtility.getMouseX() >= 25  && InputUtility.getMouseX() <= 85 &&
		   InputUtility.getMouseY() >= 425 && InputUtility.getMouseY() <= 455	)
			buttonHelp = buttonHelp.getSubimage(0, 40, 70, 40);
		else
			buttonHelp = buttonHelp.getSubimage(0, 0, 70, 40);
		
		buttonSound = DrawingUtility.getImage("res/LobbyScreen/button-sound.png");
		if(InputUtility.getMouseX() >= 585  && InputUtility.getMouseX() <= 615 &&
		   InputUtility.getMouseY() >= 425 && InputUtility.getMouseY() <= 455	){
			if(gameLogic.getPlayer().isEnableSound())
				buttonSound = buttonSound.getSubimage(0, 40, 40, 40);
			else
				buttonSound = buttonSound.getSubimage(40, 40, 40, 40);
		}
		else{
			if(gameLogic.getPlayer().isEnableSound())
				buttonSound = buttonSound.getSubimage(0, 0, 40, 40);
			else
				buttonSound = buttonSound.getSubimage(40, 0, 40, 40);
		}
		
		buttonScore = DrawingUtility.getImage("res/LobbyScreen/button-score.png");
		if(InputUtility.getMouseX() >= 265  && InputUtility.getMouseX() <= 375 &&
		   InputUtility.getMouseY() >= 405 && InputUtility.getMouseY() <= 437	)
			buttonScore = buttonScore.getSubimage(0, 42, 120, 42);
		else
			buttonScore = buttonScore.getSubimage(0, 0, 120, 42);
		
		buttonPlay = DrawingUtility.getImage("res/LobbyScreen/button-play.png");
		if(InputUtility.getMouseX() >= 220  && InputUtility.getMouseX() <= 420 &&
		   InputUtility.getMouseY() >= 330 && InputUtility.getMouseY() <= 380	)
			buttonPlay = buttonPlay.getSubimage(0, 60, 210, 60);
		else
			buttonPlay = buttonPlay.getSubimage(0, 0, 210, 60);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		DrawingUtility.drawLobbyScreen(g2, sky, globe, bear, title, buttonHelp, buttonPlay, buttonScore, buttonSound);
	}
}

//this.setLayout(new BorderLayout());
//this.setPreferredSize(new Dimension(Configuration.screenWidth,
//		Configuration.screenHeight));
//JLabel title = new JLabel("Shoot the bullet", SwingConstants.CENTER);
//title.setFont(new Font("Tahoma", Font.BOLD + Font.ITALIC, 30));
//title.setBackground(Color.BLUE);
//title.setOpaque(true);
//
//JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,
//		Configuration.screenWidth / 8, 5));
//buttonPanel.setBackground(Color.GREEN);
//newGame = new JButton("New Game");
//viewScore = new JButton("High Score");
//
//buttonPanel.add(newGame);
//buttonPanel.add(viewScore);
//
//optionPanel = new JPanel();
//optionPanel.setLayout(new BorderLayout());
//
//JPanel resolutionSetting = new JPanel();
//resolutionSetting.setLayout(new FlowLayout());
//
//JLabel resWidthL = new JLabel("WIDTH");
//JLabel resHeightL = new JLabel("HEIGHT");
//
//final JTextField resWidthF = new JTextField(
//		Configuration.screenWidth + "");
//resWidthF.setPreferredSize(new Dimension(100, 20));
//final JTextField resHeightF = new JTextField(
//		Configuration.screenHeight + "");
//resHeightF.setPreferredSize(new Dimension(100, 20));
//
//JButton resApply = new JButton("Apply");
//
//JPanel space1 = new JPanel();
//JPanel space2 = new JPanel();
//space1.setPreferredSize(new Dimension(40, 20));
//space2.setPreferredSize(new Dimension(40, 20));
//
//resolutionSetting.add(resWidthL);
//resolutionSetting.add(resWidthF);
//resolutionSetting.add(space1);
//resolutionSetting.add(resHeightL);
//resolutionSetting.add(resHeightF);
//resolutionSetting.add(space2);
//resolutionSetting.add(resApply);
//
//JPanel otherSetting = new JPanel();
//otherSetting.setLayout(new GridLayout(3, 2));
//
//JPanel minCrtPanel = new JPanel();
//minCrtPanel.add(new JLabel("Creation min delay"));
//final JSpinner minCrtF = new JSpinner(new SpinnerNumberModel(Configuration.objectCreationMinDelay, 0, 1000, 10));
//minCrtPanel.add(minCrtF);
//
//JPanel maxCrtPanel = new JPanel();
//maxCrtPanel.add(new JLabel("Creation max delay"));
//final JSpinner maxCrtF = new JSpinner(new SpinnerNumberModel(Configuration.objectCreationMaxDelay, 0, 1000, 10));
//maxCrtPanel.add(maxCrtF);
//
//JPanel minDurPanel = new JPanel();
//minDurPanel.add(new JLabel("Object min duration"));
//final JSpinner minDurF = new JSpinner(new SpinnerNumberModel(Configuration.objectCreationMinDelay, 0, 1000, 10));
//minDurPanel.add(minDurF);
//
//JPanel maxDurPanel = new JPanel();
//maxDurPanel.add(new JLabel("Object max duration"));
//final JSpinner maxDurF = new JSpinner(new SpinnerNumberModel(Configuration.objectMaxDuration, 0, 1000, 10));
//maxDurPanel.add(maxDurF);
//
//JPanel timeLimPanel = new JPanel();
//timeLimPanel.add(new JLabel("Time limit (sec)"));
//final JSpinner timeLimF = new JSpinner(new SpinnerNumberModel(Configuration.timelimit, 0, 1000, 1));
//timeLimPanel.add(timeLimF);
//
//otherSetting.add(minCrtPanel);
//otherSetting.add(maxCrtPanel);
//otherSetting.add(minDurPanel);
//otherSetting.add(maxDurPanel);
//otherSetting.add(timeLimPanel);
//optionPanel.add(resolutionSetting, BorderLayout.NORTH);
//optionPanel.add(otherSetting, BorderLayout.CENTER);
//
//newGame.addActionListener(new ActionListener() {
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//		try {
//			Configuration.objectCreationMinDelay = (Integer) minCrtF.getValue();
//			Configuration.objectCreationMaxDelay = (Integer) maxCrtF.getValue();
//			Configuration.objectMinDuration = (Integer) minDurF.getValue();
//			Configuration.objectMaxDuration = (Integer) maxDurF.getValue();
//			Configuration.timelimit = (Integer) timeLimF.getValue();
//		} catch (Exception ex) {
//
//		} finally {
//			/*JOptionPane.showMessageDialog(null, "New Game" + "\n"
//			+ "objectCreationMinDelay = "
//			+ ConfigurableOption.objectCreationMinDelay + "\n"
//			+ "objectCreationMaxDelay = "
//			+ ConfigurableOption.objectCreationMaxDelay + "\n"
//			+ "objectMinDuration = "
//			+ ConfigurableOption.objectMinDuration + "\n"
//			+ "objectMaxDuration = "
//			+ ConfigurableOption.objectMaxDuration + "\n"
//			+ "timelimit = " + ConfigurableOption.timelimit
//			+ "\n");*/
//		}
//		GameManager.runGame();
//	}
//});
//
//this.add(title, BorderLayout.NORTH);
//this.add(buttonPanel, BorderLayout.SOUTH);
//this.add(optionPanel, BorderLayout.CENTER);