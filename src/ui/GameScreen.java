package ui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Map;

import logic.Configuration;
import logic.GameManager;
import logic.MapUtility;

public class GameScreen extends JPanel {
	
	private JPanel optionPanel;
	private JPanel mapPanel;
	private JButton HighScore, Homebutton, enableSoundButon;
//	private MapUtility mapUtil;
	
	public GameScreen(){
		applyResize();
		setDoubleBuffered(true);
		this.setLayout(null);
		this.setPreferredSize(new Dimension(Configuration.screenWidth,Configuration.screenHeight));
	}
	
	public void update() {
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		DrawingUtility.drawGameScreen(g2);
		DrawingUtility.drawMap(g2);
	}
	
	protected void applyResize(){
		int height = Configuration.screenHeight;
		int width = Configuration.screenWidth;
		this.setPreferredSize(new Dimension(width, height));
		this.validate();
	}
	
	
	
//	private JPanel optionPanel;
//	private JButton newGame, viewScore;
//
//	public GameScreen() {
//		this.setLayout(new BorderLayout());
//		this.setPreferredSize(new Dimension(Configuration.screenWidth,
//				Configuration.screenHeight));
//		JLabel title = new JLabel("Shoot the bullet", SwingConstants.CENTER);
//		title.setFont(new Font("Tahoma", Font.BOLD + Font.ITALIC, 30));
//		title.setBackground(Color.BLUE);
//		title.setOpaque(true);
//
//		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,
//				Configuration.screenWidth / 8, 5));
//		buttonPanel.setBackground(Color.GREEN);
//		newGame = new JButton("New Game");
//		viewScore = new JButton("High Score");
//
//		buttonPanel.add(newGame);
//		buttonPanel.add(viewScore);
//
//		optionPanel = new JPanel();
//		optionPanel.setLayout(new BorderLayout());
//
//		JPanel resolutionSetting = new JPanel();
//		resolutionSetting.setLayout(new FlowLayout());
//
//		JLabel resWidthL = new JLabel("WIDTH");
//		JLabel resHeightL = new JLabel("HEIGHT");
//
//		final JTextField resWidthF = new JTextField(
//				Configuration.screenWidth + "");
//		resWidthF.setPreferredSize(new Dimension(100, 20));
//		final JTextField resHeightF = new JTextField(
//				Configuration.screenHeight + "");
//		resHeightF.setPreferredSize(new Dimension(100, 20));
//
//		JButton resApply = new JButton("Apply");
//
//		JPanel space1 = new JPanel();
//		JPanel space2 = new JPanel();
//		space1.setPreferredSize(new Dimension(40, 20));
//		space2.setPreferredSize(new Dimension(40, 20));
//
//		resolutionSetting.add(resWidthL);
//		resolutionSetting.add(resWidthF);
//		resolutionSetting.add(space1);
//		resolutionSetting.add(resHeightL);
//		resolutionSetting.add(resHeightF);
//		resolutionSetting.add(space2);
//		resolutionSetting.add(resApply);
//
//		JPanel otherSetting = new JPanel();
//		otherSetting.setLayout(new GridLayout(3, 2));
//
//		JPanel minCrtPanel = new JPanel();
//		minCrtPanel.add(new JLabel("Creation min delay"));
//		final JSpinner minCrtF = new JSpinner(new SpinnerNumberModel(Configuration.objectCreationMinDelay, 0, 1000, 10));
//		minCrtPanel.add(minCrtF);
//
//		JPanel maxCrtPanel = new JPanel();
//		maxCrtPanel.add(new JLabel("Creation max delay"));
//		final JSpinner maxCrtF = new JSpinner(new SpinnerNumberModel(Configuration.objectCreationMaxDelay, 0, 1000, 10));
//		maxCrtPanel.add(maxCrtF);
//
//		JPanel minDurPanel = new JPanel();
//		minDurPanel.add(new JLabel("Object min duration"));
//		final JSpinner minDurF = new JSpinner(new SpinnerNumberModel(Configuration.objectCreationMinDelay, 0, 1000, 10));
//		minDurPanel.add(minDurF);
//
//		JPanel maxDurPanel = new JPanel();
//		maxDurPanel.add(new JLabel("Object max duration"));
//		final JSpinner maxDurF = new JSpinner(new SpinnerNumberModel(Configuration.objectMaxDuration, 0, 1000, 10));
//		maxDurPanel.add(maxDurF);
//
//		JPanel timeLimPanel = new JPanel();
//		timeLimPanel.add(new JLabel("Time limit (sec)"));
//		final JSpinner timeLimF = new JSpinner(new SpinnerNumberModel(Configuration.timelimit, 0, 1000, 1));
//		timeLimPanel.add(timeLimF);
//
//		otherSetting.add(minCrtPanel);
//		otherSetting.add(maxCrtPanel);
//		otherSetting.add(minDurPanel);
//		otherSetting.add(maxDurPanel);
//		otherSetting.add(timeLimPanel);
//		optionPanel.add(resolutionSetting, BorderLayout.NORTH);
//		optionPanel.add(otherSetting, BorderLayout.CENTER);
//
//		newGame.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				try {
//					Configuration.objectCreationMinDelay = (Integer) minCrtF.getValue();
//					Configuration.objectCreationMaxDelay = (Integer) maxCrtF.getValue();
//					Configuration.objectMinDuration = (Integer) minDurF.getValue();
//					Configuration.objectMaxDuration = (Integer) maxDurF.getValue();
//					Configuration.timelimit = (Integer) timeLimF.getValue();
//				} catch (Exception ex) {
//
//				} finally {
//					/*JOptionPane.showMessageDialog(null, "New Game" + "\n"
//					+ "objectCreationMinDelay = "
//					+ ConfigurableOption.objectCreationMinDelay + "\n"
//					+ "objectCreationMaxDelay = "
//					+ ConfigurableOption.objectCreationMaxDelay + "\n"
//					+ "objectMinDuration = "
//					+ ConfigurableOption.objectMinDuration + "\n"
//					+ "objectMaxDuration = "
//					+ ConfigurableOption.objectMaxDuration + "\n"
//					+ "timelimit = " + ConfigurableOption.timelimit
//					+ "\n");*/
//				}
//				GameManager.runGame();
//			}
//		});
//
//		this.add(title, BorderLayout.NORTH);
//		this.add(buttonPanel, BorderLayout.SOUTH);
//		this.add(optionPanel, BorderLayout.CENTER);
//	}
}
