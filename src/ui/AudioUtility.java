package ui;

import java.applet.Applet;
import java.applet.AudioClip;


public class AudioUtility implements Runnable{
	
	private static AudioClip intro;
	static{
		ClassLoader loader = AudioUtility.class.getClassLoader();
		intro = Applet.newAudioClip(loader.getResource("res/intro.wav"));
	}
	
	public static void playIntroSound(){
		intro.loop();
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		intro.loop();		
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
