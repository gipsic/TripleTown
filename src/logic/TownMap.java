package logic;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import ui.DrawingUtility;

public class TownMap{

	private int[][] map;
	private BufferedImage[] Ground = new BufferedImage[19];
	private boolean canPut = false;
	
	public TownMap(int[][] map){
		this.map = map;
		loadGround();
	}
	public TownMap(){
		this.map = new int[6][6];
		clearMap();
		loadGround();
	}

	public void clearMap(){
		for(int i=0;i<6;i++){
			for(int j=0;j<6;j++){
				this.map[i][j]=0;
			}
		}
	}
	
	public BufferedImage calcBG(int x, int y){
		BufferedImage ground;
		int file = 18;
		if(!isCanPutAt(x,y)) return null;
		else {
			String around = aroundCell(x,y);
			if(logicCompare(around,"00X111X0")){
				file = 1;
			} else if(logicCompare(around,"X0X11111")){
				file = 2;
			} else if(logicCompare(around,"X000X111")){
				file = 3;
			} else if(logicCompare(around,"X11111X0")){
				file = 4;
			} else if(logicCompare(around,"11111111")){
				file = 5;
			} else if(logicCompare(around,"11X0X111")){
				file = 6;
			} else if(logicCompare(around,"X111X000")){
				file = 7;
			} else if(logicCompare(around,"1111X0X1")){
				file = 8;
			} else if(logicCompare(around,"11X000X1")){
				file = 9;
			} else if(logicCompare(around,"00X101X0")){
				file = 10;
			} else if(logicCompare(around,"X000X101")){
				file = 11;
			} else if(logicCompare(around,"X101X000")){
				file = 12;
			} else if(logicCompare(around,"01X000X1")){
				file = 13;
			} else if(logicCompare(around,"00X1X000")){
				file = 14;
			} else if(logicCompare(around,"X00000X1")){
				file = 15;
			} else if(logicCompare(around,"0000X1X0")){
				file = 16;
			} else if(logicCompare(around,"X1X00000")){
				file = 17;
			} else if(logicCompare(around,"00000000")){
				file = 18;
			} else if(logicCompare(around,"01010101")){
				file = 19;
			} else file = 18;
		}
		
		return Ground[file-1];
	}
	
	public String aroundCell(int x,int y){
		String out = "";
		out += bool2bit(isCanPutAt(x-1,y-1)); //top left
		out += bool2bit(isCanPutAt(x+0,y-1)); //top
		out += bool2bit(isCanPutAt(x+1,y-1)); //top right
		out += bool2bit(isCanPutAt(x+1,y+0)); //right
		out += bool2bit(isCanPutAt(x+1,y+1)); //bottom right
		out += bool2bit(isCanPutAt(x+0,y+1)); //bottom
		out += bool2bit(isCanPutAt(x-1,y+1)); //bottom left
		out += bool2bit(isCanPutAt(x-1,y+0)); //left
		return out;
	}
	
	public String bool2bit(boolean a){
		if(a) return "1";
		else return "0";
	}
	public boolean logicCompare(String a,String check){
		for(int i=0;i<check.length();i++){
			if(!a.substring(i, i+1).equalsIgnoreCase(check.substring(i, i+1)) && !check.substring(i, i+1).equalsIgnoreCase("x"))
				return false;
		}
		return true;
	}
	
	public void loadGround(){
		for(int i =1; i<=19;i++){
			String a = "";
			if(i<10) a = "0";
			Ground[i-1] = DrawingUtility.getImage("res/GameScreen/ground-"+a+i+".png");
		}
	}
	
	//-----getter mothods -----
	public int[][] getMap(){
		return this.map;
	}
	
	public int getMapAt(int x,int y){
		return this.map[x][y];
	}

	public boolean isCanPut(){
		return this.canPut;
	}

	public boolean isCanPutAt(int x,int y){
		if(x<0 || x >5||y<0||y>5||map[x][y] != 0) return false;
		else return true;
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
