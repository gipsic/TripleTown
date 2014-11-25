package logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JOptionPane;


public class HighScoreUtility {

	public static class HighScoreRecord implements Comparable<HighScoreRecord>{
		private String name = "";
		private int score = 0;
		
		private HighScoreRecord(String name, int score) {
			//Fill code
			this.name = name;
			this.score = score;
		}
		
		/* 
		 * Parse the given string "record"
		 * record format is name:score
		 */
		public HighScoreRecord(String record) throws ScoreParsingException{
			//Fill code
			if (record == null || record.indexOf(":") == -1)
				throw new ScoreParsingException(1);
			try {
				String name = record.substring(0, record.indexOf(":"));
				int score = Integer.valueOf(record.substring(record.indexOf(":") + 1));
				this.name = name;
				this.score = score;
			} catch (ArrayIndexOutOfBoundsException e) {
				throw new ScoreParsingException(1);
			} catch (NumberFormatException e) {
				throw new ScoreParsingException(0);
			}
		}
		
		private String getRecord(){
			return name.trim()+":"+score;
		}
		
		private static String[] defaultRecord(){
			return new String[]{"Krit:47915","Wisit:47850","Proud:42845",
					"Eve:36150","Palm:34885","Pete:31050",
					"John:28555","George:25480","Michael:22995","Mike:17500"};
		}

		@Override
		public int compareTo(HighScoreRecord o) {
			//Fill code
			if (this.score < o.score)
				return 1;
			else if (this.score == o.score)
				return 0;
			else
				return 1;
		}
	}
	
	private static HighScoreRecord[] highScoreRecord = null;

	private static String readFileName = "highscore";
	
	/*
	 * Display player's score and record if the player rank is 10 or higher.
	 */
	public static void recordHighScore(int score){
		if(!loadHighScore() || highScoreRecord == null){
			//Fill code
			JOptionPane.showMessageDialog(null,
					"Error loading highscore record", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		int index=highScoreRecord.length;
		for(int i=0; i<highScoreRecord.length; i++){
			if(score > highScoreRecord[i].score){
				index = i;
				break;
			}
		}
		if(index >= highScoreRecord.length){
			//Fill code
			JOptionPane.showMessageDialog(null, "Your score is " + score,
					"Game Over", JOptionPane.INFORMATION_MESSAGE);
		}else{
			for(int i=highScoreRecord.length-1; i>=index+1; i--){
				highScoreRecord[i] = highScoreRecord[i-1];
			}
			//Fill code
			String name = JOptionPane.showInputDialog(null,
					"Congratulation, you are ranked " + (index + 1)
							+ "\nPlease enter your name.", "High Score",
					JOptionPane.INFORMATION_MESSAGE);
			highScoreRecord[index] = new HighScoreRecord(name, score);
			
			try {
				
				BufferedWriter out = new BufferedWriter(new FileWriter("highscore"));
				//Fill code
				String str = "";
				for (HighScoreRecord record : highScoreRecord)
					str += record.name.trim() + ":" + record.score + "\n";
				str = str.trim();
				out.write(getXORed(str));
				//
				out.close();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "Error saving high score record", 
						"Error", JOptionPane.ERROR_MESSAGE);
				highScoreRecord = null;
				return;
			}
		}
	}
	
	public static void displayTop10(){
		if(!loadHighScore() || highScoreRecord == null){
			JOptionPane.showMessageDialog(null,"Error loading highscore record", "Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		String msg = "======= Top 10 players ======="+System.getProperty("line.separator");
		int rank = 1;
		for(HighScoreRecord record : highScoreRecord){
			msg += rank+" "+record.getRecord()+System.getProperty("line.separator");
			rank++;
		}
		JOptionPane.showMessageDialog(null, msg.trim(), "Top 10", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static int getScoreOf(int i) {
		if(!loadHighScore() || highScoreRecord == null){
			JOptionPane.showMessageDialog(null,"Error loading highscore record", "Error",JOptionPane.ERROR_MESSAGE);
			return 0;
		}
		int index = i-1;
		if(index>=0 && index<=highScoreRecord.length) return highScoreRecord[i-1].score;
		return 0;
	}	

	public static int calcRank(int score) {
		if(!loadHighScore() || highScoreRecord == null){
			JOptionPane.showMessageDialog(null,"Error loading highscore record", "Error",JOptionPane.ERROR_MESSAGE);
			return 0;
		}
		int i=0;
		for(i=0; i<highScoreRecord.length; i++){
			if(score > highScoreRecord[i].score){
				break;
			}
		}
		return i+1;
	}	

	private static boolean loadHighScore(){
		File f = new File(readFileName);
		//If no high score, create default
		if(!f.exists()){
			if(!createDefaultScoreFile()) return false;
		}
		//Read high score -- if fail: delete the file, create default one and read it again 
		if(!readAndParseScoreFile(f)){
			f.delete();
			if(!createDefaultScoreFile()) return false;
			return readAndParseScoreFile(f);
		}
		return true;
		
	}
	
	private static boolean readAndParseScoreFile(File f){
		try {
			BufferedReader in = new BufferedReader(new FileReader(f));
			String line;
			highScoreRecord = new HighScoreRecord[10];
			String str = "";
			int c;
			while((c = in.read()) != -1){
				str += (char)c;
			}
			in.close();
			String[] records = getXORed(str).split("\n");
			for(int i=0; i<highScoreRecord.length; i++){
				try{
					highScoreRecord[i] = new HighScoreRecord(records[i]);
				}catch(ScoreParsingException e){
					System.err.println("Error parsing line "+(i+1)+", "+e.getMessage());
					highScoreRecord[i] = new HighScoreRecord("ERROR_RECORD", 0);
				}
			}
			Arrays.sort(highScoreRecord);
			return true;
		} catch (Exception e) {
			highScoreRecord = null;
		}
		return false;
	}
	
	private static boolean createDefaultScoreFile(){
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("highscore"));
			String str = "";
			for(String s : HighScoreRecord.defaultRecord()){
				str += s+"\n";
			}
			str = str.trim();
			out.write(getXORed(str));
			out.close();
			return true;
		} catch (IOException e1) {
			highScoreRecord = null;
			return false;
		}
	}
	
	private static final byte[] key = "RmAAq2b5d8fjgu9dhher".getBytes();
	
	//This method does both encryption and decryption 
	private static String getXORed(String in){
		byte[] inData = in.getBytes();
		//Fill code
		for (int index = 0; index < inData.length; index++)
			inData[index] ^= key[index % key.length];
		//
		return new String(inData);
	}

	public static void setReadFileName(String name){
		readFileName = name;
	}

}
