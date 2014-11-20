package ui;

import java.applet.Applet;
import java.applet.AudioClip;


public class AudioUtility implements Runnable{
	
	private static AudioClip intro;
	static{
		ClassLoader loader = AudioUtility.class.getClassLoader();
		intro = Applet.newAudioClip(loader.getResource("res/intro.mp3"));
	}
	
	public static void playIntroSound(){
		intro.play();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		intro.play();		
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
