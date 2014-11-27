package logic;

import java.awt.event.KeyEvent;

import utility.DrawingUtility;
import utility.InputUtility;

public class MainLogic {

	private PlayerStatus player;
	private TownMap currentMap = new TownMap();
	private int[][] mergeList = new int[][]{{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1}}; 
	private int mergeCount = 0;
	
	public MainLogic(){
		player = new PlayerStatus("");
	}
	
	public PlayerStatus getPlayer() {
		return player;
	}
	
	public void resetMergeList(){
		mergeCount = 0;
		mergeList = new int[][]{{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1}}; 
	}

	public void setPlayer(PlayerStatus player) {
		this.player = player;
	}

	public TownMap getMapUtil(){
		return currentMap;
	}
	
	public boolean isMapFull(){
		int itemNum = 0;
		for(int x = 0; x < 6 ;x ++){
			for(int y = 0; y < 6 ;y ++){
				if(currentMap.getMapAt(x, y) > 0 && currentMap.getStateAt(x, y) != 2)
					itemNum++;
			}
		}
		if(itemNum!=36) return false;
		else return true;
	}
	
	public void reset(){
		player.setPause(false);
		player.clearScore();
		currentMap.clearMap();
	}
	
	public void logicUpdate(){
		
		//Pause
		if(InputUtility.getKeyTriggered(KeyEvent.VK_ENTER)){
			player.setPause(!player.isPause());
		}
		
		if(player.isPause()){
			return;
		}
		
		//Update put and merge
		currentMap.cleanMap();
		int cellX = DrawingUtility.onXcell();
		int cellY = DrawingUtility.onYcell();
		int item = player.getCurrentItem();
		if(cellX != -1 && cellY != -1){
			if(!currentMap.isCanPutAt(cellX, cellY)) {
				item = currentMap.getMapAt(cellX, cellY);
				currentMap.setMapAt(item, 3, cellX, cellY);
			} else if(InputUtility.isMouseLeftClicked()){
				currentMap.setMapAt(item, 1, cellX, cellY);
				player.increaseScore(currentMap.getItemScore(item));
				
				while(true){
					int codeMerge = Merge(cellX, cellY, item);
					if(codeMerge == -1) break;
					currentMap.setMapAt(codeMerge, 1, cellX, cellY);
					player.increaseScore(currentMap.getItemScore(codeMerge));
					item = codeMerge;
				}
				
			} else {
				currentMap.setMapAt(item, 2, cellX, cellY);
			}
		}
		
	}
	
	public void checkMerge(int x, int y, int check, int unCheck){
		pushMergeList(x,y);
		if(x<0 || x >5||y<0||y>5) return;
		//Check Above Cell
		if(currentMap.getStateAt(x, y+1) != 2 && unCheck!=1){
			int code = currentMap.getMapAt(x, y+1);
			if(code>8) code = code %7;
			if(code == check){
				pushMergeList(x,y+1);
				checkMerge(x,y+1,check,3); // Don't check below cell (this)
			}
		}
		//Check Right Cell
		if(currentMap.getStateAt(x+1, y) != 2 && unCheck!=2){
			int code = currentMap.getMapAt(x+1, y);
			if(code>8) code = code %7;
			if(code == check){
				pushMergeList(x+1,y);
				checkMerge(x+1,y,check,4); // Don't check left cell (this)
			}
		}
		//Check Below Cell
		if(currentMap.getStateAt(x, y-1) != 2 && unCheck!=3){
			int code = currentMap.getMapAt(x, y-1);
			if(code>8) code = code %7;
			if(code == check){
				pushMergeList(x,y-1);
				checkMerge(x,y-1,check,1); // Don't check left cell (this)
			}
		}
		//Check Left Cell
		if(currentMap.getStateAt(x-1, y) != 2 && unCheck!=4){
			int code = currentMap.getMapAt(x-1, y);
			if(code>8) code = code %7;
			if(code == check){
				pushMergeList(x-1,y);
				checkMerge(x-1,y,check,2); // Don't check right cell (this)
			}
		}
		
		//for(int a =0;a<5;a++)System.out.print("("+mergeList[a][0]+","+mergeList[a][1]+") ");System.out.println();
	}
	
	public void pushMergeList(int x, int y){
		for(int a =0;a<5;a++){
			if(mergeList[a][0]==x && mergeList[a][1]==y)
				return;
		}
		if(mergeCount<5){
			mergeList[mergeCount][0] = x;
			mergeList[mergeCount][1] = y;
			mergeCount++;
		}
	}
	
	public int Merge(int x, int y, int code){
		resetMergeList();
		checkMerge(x, y, code,0);
		if(mergeCount >= 3){
			for(int a =0;a<5;a++){
				currentMap.setMapAt(0, 0, mergeList[a][0], mergeList[a][1]);
			}
			if(mergeCount>3) code+=7;
			return code+1;
		}
		return -1;
	}
	
	
}
