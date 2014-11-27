package utility;

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
			this.name = name;
			this.score = score;
		}
		
		public HighScoreRecord(String record) throws ScoreParsingException{
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
			return name.trim()+"\t"+score;
		}
		
		private static String[] defaultRecord(){
			return new String[]{
					"Krit:47915","Film:47850","Proud:42845","Kitty1:36150","Kitty2:34885",
					"Bone:31050","EVE:28555","Ves:25480","Poh:22995","Kin:17500",
					"Venus:15205","Donut:13865","Bank:10245","First:9750","Zack:8235",
					"Fah:6520","Fern:4355","Jab:3480","YoYo:3185","Kam:3010"
			};
		}

		@Override
		public int compareTo(HighScoreRecord o) {
			if (this.score < o.score)
				return 1;
			else if (this.score == o.score)
				return 0;
			else
				return 1;
		}
	}
	
	private static HighScoreRecord[] highScoreRecord = null;

	private static String readFileName = System.getProperty("user.home")+"\\"+"highscore";
	
	public static void recordHighScore(int score, String name){
		if(!loadHighScore() || highScoreRecord == null){
			return;
		}
		name = name.substring(0, Math.min(8, name.length()));
		int index = 20;
		for(int i=0; i<20; i++){
			if(score > highScoreRecord[i].score){
				index = i;
				break;
			}
		}
		if(index < 20){
			for(int i=19; i>=index+1; i--){
				highScoreRecord[i] = highScoreRecord[i-1];
			}
			highScoreRecord[index] = new HighScoreRecord(name, score);
			
			try {
				BufferedWriter out = new BufferedWriter(new FileWriter(readFileName));
				String str = "";
				for (HighScoreRecord record : highScoreRecord)
					str += record.name.trim() + ":" + record.score + "\n";
				str = str.trim();
				out.write(getXORed(str));
				out.close();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "Error saving high score record", "Error", JOptionPane.ERROR_MESSAGE);
				highScoreRecord = null;
				return;
			}
		}
	}
	
	public static String[] getTop20(){
		if(!loadHighScore() || highScoreRecord == null){
			assingDefaultRecords();
		}
		String[] msg = new String[20];
		int rank = 1;
		for(HighScoreRecord record : highScoreRecord){
			if(rank>20) break;
			msg[rank-1] = (rank < 10)? "  ":"";
			msg[rank-1] += rank+" "+record.getRecord()+"";
			rank++;
		}
		return msg;
	}
	
	public static int getScoreOf(int i) {
		if(!loadHighScore() || highScoreRecord == null){
			assingDefaultRecords();
		}
		int index = i-1;
		if(index>=0 && index<=highScoreRecord.length) return highScoreRecord[i-1].score;
		return 0;
	}	

	public static int calcRank(int score) {
		if(!loadHighScore() || highScoreRecord == null){
			assingDefaultRecords();
		}
		int i=0;
		for(i=0; i<highScoreRecord.length; i++){
			if(score > highScoreRecord[i].score){
				break;
			}
		}
		return i+1;
	}
	
	public static void assingDefaultRecords(){
		int a = 0;
		highScoreRecord = new HighScoreRecord[20];
		for(String s : HighScoreRecord.defaultRecord()){
			try {
				highScoreRecord[a] = new HighScoreRecord(s);
			} catch (ScoreParsingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(++a > highScoreRecord.length) break;
		}
	}

	private static boolean loadHighScore(){
		File f = new File(readFileName);
		//If no high score, create default
		if(!f.exists())
			if(!createDefaultScoreFile()) return false;
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
			highScoreRecord = new HighScoreRecord[20];
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
			BufferedWriter out = new BufferedWriter(new FileWriter(readFileName));
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
	
	private static String getXORed(String in){
		byte[] inData = in.getBytes();
		for (int index = 0; index < inData.length; index++)
			inData[index] ^= key[index % key.length];
		return new String(inData);
	}

	public static void setReadFileName(String name){
		readFileName = name;
	}

}
