package logic;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import ui.DrawingUtility;

public class MapUtility{

	private static int[][] map = new int[6][6];
	private static int size = 70; //px
	private static boolean canPut = false;
	
//	public MapUtility(){
//	}
//	
//	public MapUtility(int size){
//		this.size = size;
//	}
	
//	public void copyMap(MapUtility map){
//		for(int i=0;i<6;i++){
//			for(int j=0;j<6;j++){
//				this.map[i][j]=map.map[i][j];
//			}
//		}
//	}
	
	public static void clearMap(){
		for(int i=0;i<6;i++){
			for(int j=0;j<6;j++){
				MapUtility.map[i][j]=0;
			}
		}
	}
	
	//-----getter mothods -----
	public static int[][] getMap(){
		return MapUtility.map;
	}
	
	public static int getMapAt(int x,int y){
		return MapUtility.map[x][y];
	}
	
	public static int getSize(){
		return MapUtility.size;
	}
	
	public static boolean isCanPut(){
		return MapUtility.canPut;
	}
	//-----setter methods -----
	public static void setMap(int[][] map){
		MapUtility.map = map;
	}
	
	public static void setMapAt(int item, int x, int y){
		MapUtility.map[x][y] = item;
	}
	
	public static void setCanPut(boolean canPut){
		MapUtility.canPut = canPut;		
	}
	
	public static void setSize(int size){
		MapUtility.size = size;
	}
}
