package utility;

public class ScoreParsingException extends Exception {

	private int errorType;
	
	public ScoreParsingException(int errorType){
		this.errorType = errorType;
	}
	
	@Override
	public String getMessage(){
		if(this.errorType == 0)
			return "No record score";
		else if(this.errorType == 1)
			return "Wrong record format";
		else
			return "";
	}
}
