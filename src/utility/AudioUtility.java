package utility;

import java.applet.Applet;
import java.applet.AudioClip;

import javax.sound.sampled.FloatControl;

import logic.MainLogic;
import logic.PlayerStatus;


public class AudioUtility implements Runnable{
	
	private static AudioClip intro;
	private static PlayerStatus player;
	private static MainLogic gameLogic;
	
	static{
		ClassLoader loader = AudioUtility.class.getClassLoader();
		intro = Applet.newAudioClip(loader.getResource("res/intro.wav"));
	}
	
	public AudioUtility(MainLogic gameLogic) {
		// TODO Auto-generated constructor stub
		this.gameLogic = gameLogic;
		this.player = gameLogic.getPlayer();
	}
	
	public void update(MainLogic gameLogic){
		this.gameLogic = gameLogic;
		this.player = gameLogic.getPlayer();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		boolean nowPlaying = true;
		intro.loop();
		while(true){
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.update(gameLogic);
//			System.out.println(player.isEnableSound());
			if(!player.isEnableSound() && nowPlaying){
				intro.stop();
				nowPlaying = false;
			}
			else if(player.isEnableSound() && !nowPlaying){
				intro.loop();
				nowPlaying = true;
			}
		}
	}
	
//	public static void playSound(String identifier){
//		AudioClip ac;
//		/* fill code */
//		if(identifier.equalsIgnoreCase("shoot"))
//			ac = acShoot;
//		else
//			ac = acCollect;
//		////
//		ac.play();
//	}
	
}
