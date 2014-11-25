import ui.ICombinable;
import logic.GameManager;
import logic.MainLogic;

public class TripleTown  {

	public static void main(String[] args){
		MainLogic gameLogic = new MainLogic();
		GameManager.runGame(gameLogic);
	}
}