package uom.tl.sound;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
//Not used
public abstract class Sound {
	
	/*public static synchronized void playSound() {
	
			  new Thread(new Runnable() {
			    public void run() {
			      try {
			        Clip clip = AudioSystem.getClip();
			        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
			          Sound.class.getResourceAsStream("/hs.wav"));
			        clip.open(inputStream);
			        clip.start(); 
			      } catch (Exception e) {
			    	  System.out.println("cant");
			      }
			    }
			  }).start();
	}*/
}
