package controller;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Som {
	private static AudioInputStream   tiro;
    private static Clip clip; 



	public static synchronized void tiroSom(){
		 new Thread(new Runnable() { // the wrapper thread is unnecessary, unless it blocks on the Clip finishing, see comments
	            @Override
	            public void run() {
	                Clip clip = null;
	                AudioInputStream inputStream = null;
	                try{
	                    do{
	                        if(clip == null || inputStream == null)
	                                clip = AudioSystem.getClip();
	                                inputStream = AudioSystem.getAudioInputStream(new File("Arquivos/Sons/tiro.wav").getAbsoluteFile());
	                        if(clip != null && !clip.isActive())
	                                inputStream = AudioSystem.getAudioInputStream(new File("Arquivos/Sons/tiro.wav").getAbsoluteFile());
	                                clip.open(inputStream);
	                                clip.start(); 
	                    }while(clip.isActive());
	                    inputStream.close();
	                } catch (LineUnavailableException e) {
	                    e.printStackTrace();
	                } catch (UnsupportedAudioFileException e) {
	                    e.printStackTrace();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }).start();
//		try {
//			tiro = AudioSystem.getAudioInputStream(new File("Arquivos/Sons/tiro.wav").getAbsoluteFile());
//
//
//		} catch (UnsupportedAudioFileException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        try {
//			clip = AudioSystem.getClip();
//			clip.open(tiro);
//		} catch (LineUnavailableException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//		clip.start();
	}

}
