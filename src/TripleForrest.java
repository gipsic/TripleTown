import logic.GameManager;
import logic.IGameLogic;
import logic.MainLogic;

public class TripleForrest  {

	public static void main(String[] args){
		MainLogic gameLogic = new MainLogic();
		GameManager.runGame(gameLogic);
	}
}