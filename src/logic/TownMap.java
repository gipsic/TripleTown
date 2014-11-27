package logic;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import utility.DrawingUtility;

public class TownMap{

	private int[][] map;
	private int[][] mapState = new int[6][6];
	private int[] itemScore = new int[]{0,5,20,100,500,1500,5000,15000,50000,40,200,1000,3000,10000,30000};
	private BufferedImage[] Ground = new BufferedImage[47];
	private String[] GroundLogic = new String[47];
	
	public TownMap(){
		this.map = new int[6][6];
		clearMap();
		loadGround();
	}

	public void clearMap(){
		for(int i=0;i<6;i++){
			for(int j=0;j<6;j++){
				this.map[i][j]=0;
				this.mapState[i][j]=0;
			}
		}
	}
	
	public void cleanMap(){
		for(int i=0;i<6;i++){
			for(int j=0;j<6;j++){
				if(this.mapState[i][j]==3) {
					this.mapState[i][j] = 1;
				} else if(this.mapState[i][j]==2) {
					this.map[i][j]=0;
					this.mapState[i][j] = 0;
				}
			}
		}
	}
	
	public BufferedImage calcBG(int x, int y){
		BufferedImage ground;
		int file = 5;
		if(!isCanPutAt(x,y)) return null;
		else {
			String around = aroundCell(x,y);
			for(int a = 1;a<=GroundLogic.length;a++){
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
		for(int i =1; i<=GroundLogic.length;i++){
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
		GroundLogic[8] = "11X0X0X1";
		GroundLogic[9] = "X0X101X0";
		GroundLogic[10] = "X0X0X101";
		GroundLogic[11] = "X101X0X0";
		GroundLogic[12] = "01X0X0X1";
		GroundLogic[13] = "X0X1X0X0";
		GroundLogic[14] = "X0X0X0X1";
		GroundLogic[15] = "X0X0X1X0";
		GroundLogic[16] = "X1X0X0X0";
		GroundLogic[17] = "X0X0X0X0";
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
		GroundLogic[33] = "X0X10101";
		GroundLogic[34] = "01X0X101";
		GroundLogic[35] = "X10101X0";
		GroundLogic[36] = "0101X0X1";
		GroundLogic[37] = "11110101";
		GroundLogic[38] = "01111101";
		GroundLogic[39] = "11010111";
		GroundLogic[40] = "01011111";
		GroundLogic[41] = "01110111";
		GroundLogic[42] = "11011101";
		GroundLogic[43] = "01010111";
		GroundLogic[44] = "01011101";
		GroundLogic[45] = "01110101";
		GroundLogic[46] = "11010101";
	}
	
	public int getMapAt(int x,int y){
		if(x<0 || x >5||y<0||y>5) return 0;
		return this.map[x][y];
	}
	public int getStateAt(int x,int y){
		if(x<0 || x >5||y<0||y>5) return 0;
		return this.mapState[x][y];
	}

	public boolean isCanPutAt(int x,int y){
		if(x<0 || x >5||y<0||y>5||(map[x][y] != 0 && (mapState[x][y]==1 || mapState[x][y]==3))) return false;
		else return true;
	}

	public void setMapAt(int item, int state, int x, int y){
		if(x<0 || x >5||y<0||y>5) return;
		this.map[x][y] = item;
		this.mapState[x][y] = state;
	}	
	
	public int getItemScore(int code) {
		if(code >= 0 && code < itemScore.length) return itemScore[code];
		else return 0;
	}
	
}
