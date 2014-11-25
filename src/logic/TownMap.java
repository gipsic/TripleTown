package logic;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import ui.DrawingUtility;

public class TownMap{

	private int[][] map;
	private BufferedImage[] Ground = new BufferedImage[40];
	private String[] GroundLogic = new String[40];
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
			for(int a = 1;a<=Ground.length;a++){
				if(logicCompare(around,GroundLogic[a-1])){
					file = a;
					break;
				}
			}
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
		for(int i =1; i<=33;i++){
			String a = "";
			if(i<10) a = "0";
			Ground[i-1] = DrawingUtility.getImage("res/GameScreen/ground-"+a+i+".png");
		}
		GroundLogic[0] = "X0X111X0";
		GroundLogic[1] = "X0X11111";
		GroundLogic[2] = "X0X0X111";
		GroundLogic[3] = "X11111X0";
		GroundLogic[4] = "11111111";
		GroundLogic[5] = "11X0X111";
		GroundLogic[6] = "X111X0X0";
		GroundLogic[7] = "1111X0X1";
		GroundLogic[8] = "11X000X1";
		GroundLogic[9] = "00X101X0";
		GroundLogic[10] = "X000X101";
		GroundLogic[11] = "X101X0X0";
		GroundLogic[12] = "01X000X1";
		GroundLogic[13] = "X0X1X0X0";
		GroundLogic[14] = "X0X0X0X1";
		GroundLogic[15] = "X0X0X1X0";
		GroundLogic[16] = "X1X0X0X0";
		GroundLogic[17] = "00000000";
		GroundLogic[18] = "01010101";
		GroundLogic[19] = "01111111";
		GroundLogic[20] = "11011111";
		GroundLogic[21] = "11110111";
		GroundLogic[22] = "11111101";
		GroundLogic[23] = "X11101X0";
		GroundLogic[24] = "X0X10111";
		GroundLogic[25] = "11X0X101";
		GroundLogic[26] = "X0X11101";
		GroundLogic[27] = "1101X0X1";
		GroundLogic[28] = "X10111X0";
		GroundLogic[29] = "0111X0X1";
		GroundLogic[30] = "01X0X111";
		GroundLogic[31] = "X1X0X1X0";
		GroundLogic[32] = "X0X1X0X1";
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
