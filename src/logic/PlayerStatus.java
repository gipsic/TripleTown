package logic;

import java.awt.Graphics2D;

import ui.DrawingUtility;

public class PlayerStatus {

	private int score = 0 ;
	private int currentItem = 1;
	private boolean pause = false;
	private String playerName = "";
	private boolean enableSound = true;
	
	public PlayerStatus(String name){
		this.playerName = name;
	}
	
	//----- getter methods -----
	public int getScore(){
		return score;
	}
	
	public int getCurrentItem(){
		return currentItem;
	}
	
	public boolean isPause(){
		return pause;
	}
	
	public String getPlayerName(){
		return playerName;
	}
	
	public boolean isEnableSound(){
		return enableSound;
	}
	//----- setter methods -----
	public void setCurrentItem(int currentItem){
		this.currentItem = currentItem;
	}
	
	public void setPause(boolean pause){
		this.pause = pause ;
	}
	
	public void setPlayerName(String name){
		this.playerName = name;
	}
	
	public void setEnableSound(boolean enableSound){
		this.enableSound = enableSound;
	}
	//----- methods -----
	public void increaseScore(int score){
		this.score += score;
	}
	
	public boolean isDisplayArea(int x , int y){
		if(x<Configuration.screenHeight && y<Configuration.screenWidth)
			return true;
		return false;
	}
	
	/*
	 * interface method is not create 
	 */
	
	public void render(Graphics2D g2){
		DrawingUtility.drawStatusBar(g2, playerName, score, currentItem, pause, enableSound);
	}
}
