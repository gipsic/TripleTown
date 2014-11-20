package logic;

import java.awt.event.KeyEvent;

import ui.InputUtility;

public class MainLogic implements IGameLogic {

	private PlayerStatus player;
//	private MapUtility mapUtil = new MapUtility();
	private int[][] listForMerge = new int[40][2];
	private int count = 0;
	
	public MainLogic(){
		player = new PlayerStatus("");
	}
	
	public PlayerStatus getPlayer() {
		return player;
	}

	public void setPlayer(PlayerStatus player) {
		this.player = player;
	}

//	public MapUtility getMapUtil(){
//		return mapUtil;
//	}
	
	public void logicUpdate(){
		
		
		//Pause
		if(InputUtility.getKeyTriggered(KeyEvent.VK_ENTER)){
			player.setPause(!player.isPause());
		}
		
		if(player.isPause()){
			return;
		}
		
		//Update put and merge
		if(player.isDisplayArea(InputUtility.getMouseX(), InputUtility.getMouseY())){
			if(InputUtility.isMouseLeftClicked()){
				int x=(InputUtility.getMouseX()-25)/MapUtility.getSize();
				int y=(InputUtility.getMouseY()-25)/MapUtility.getSize();
				this.checkMerge(x, y, MapUtility.getMapAt(x, y));
				if(count>=3){
					this.Merge();
				}
				else{
					this.ReverseMap(MapUtility.getMapAt(x, y));
				}
			}
		}
		
	}
	
	public void checkMerge(int x, int y, int check){
		
		if(MapUtility.getMapAt(x, y) != check){
			return;
		}
		else{
			MapUtility.setMapAt(0, x, y);
			listForMerge[count][0] = x;
			listForMerge[count][1] = y;
			count++;
		}
		
		if(x-1>=0)
			this.checkMerge(x-1, y, check);
		if(y+1<6)
			this.checkMerge(x, y+1, check);
		if(x+1<6)
			this.checkMerge(x+1, y, check);
		if(y-1>=0)
			this.checkMerge(x, y-1, check);
		
	}
	
	public void Merge(){
		//draw animation merge
	}
	
	public void ReverseMap(int set){
		for(int i=0 ; i<listForMerge.length ; i++)
			MapUtility.setMapAt(set, listForMerge[i][0], listForMerge[i][1]);
	}
	
}
