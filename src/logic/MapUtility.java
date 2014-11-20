package logic;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import ui.DrawingUtility;

public class MapUtility{

	private int[][] map = new int[6][6];
	private int size = 70; //px
	private boolean canPut = false;
	
	public MapUtility(){
	}
	
	public MapUtility(int size){
		this.size = size;
	}
	
	public void copyMap(MapUtility map){
		for(int i=0;i<6;i++){
			for(int j=0;j<6;j++){
				this.map[i][j]=map.map[i][j];
			}
		}
	}
	
	public void clearMap(){
		for(int i=0;i<6;i++){
			for(int j=0;j<6;j++){
				this.map[i][j]=0;
			}
		}
	}
	
	//-----getter mothods -----
	public int[][] getMap(){
		return map;
	}
	
	public int getMapAt(int x,int y){
		return map[x][y];
	}
	
	public int getSize(){
		return size;
	}
	
	public boolean isCanPut(){
		return canPut;
	}
	//-----setter methods -----
	public void setMap(int[][] map){
		this.map = map;
	}
	
	public void setMapAt(int item, int x, int y){
		this.map[x][y] = item;
	}
	
	public void setCanPut(boolean canPut){
		this.canPut = canPut;		
	}
}
