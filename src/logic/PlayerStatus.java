package logic;

import java.awt.Graphics2D;
import java.util.Random;

import utility.DrawingUtility;

public class PlayerStatus {

	private int score = 0 ;
	private int currentItem = 1;
	private boolean pause = false;
	private boolean enableSound = true;
	private int maxLevel = 0;
	
	public PlayerStatus(){
		
	}
	
	public int getMaxLevel(){
		return maxLevel;
	}
	
	public void setMaxLevel(int maxLevel){
		if(maxLevel>8)maxLevel%=7;
		this.maxLevel = maxLevel;
	}
	
	public int getScore(){
		return score;
	}
	
	public int getCurrentItem(){
		return currentItem;
	}
	
	public boolean isPause(){
		return pause;
	}
	
	public boolean isEnableSound(){
		return enableSound;
	}

	public void setCurrentItem(int currentItem){
		this.currentItem = currentItem;
	}
	
	public void setPause(boolean pause){
		this.pause = pause ;
	}
	
	public void setEnableSound(boolean enableSound){
		this.enableSound = enableSound;
	}
	
	public void clearScore(){
		this.score = 0;
	}
	
	public void increaseScore(int score){
		currentItem = randItem();
		this.score += score;
	}
	
	public int randItem(){
		Random rand = new Random();
		int range = 10000;
		switch(maxLevel){
		case 7: range = 9990; break;
		case 6: range = 9900; break;
		case 5: range = 9800; break;
		case 4: range = 9550; break;
		case 3: range = 9150; break;
		case 2: range = 8450; break;
		case 1: range = 8450; break;
		default: range = 10000; break;
		}
		int randomNum = rand.nextInt(range) + 1;
	    if(randomNum > 9990) return 7;//10 in 10000
	    else if(randomNum > 9900) return 6;//50 in 10000
	    else if(randomNum > 9800) return 5;//100 in 10000
	    else if(randomNum > 9550) return 4;//250 in 10000
	    else if(randomNum > 9150) return 3;//400 in 10000
	    else if(randomNum > 8450) return 2;//700 in 10000
	    else return 1;//8450 in 10000    
	    
	}
	

}
