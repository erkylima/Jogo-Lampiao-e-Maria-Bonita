package controller;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Som {
	private AudioInputStream   tiro;
    private Clip clip; 

	public Som(){
			try {
				tiro = AudioSystem.getAudioInputStream(new File("Arquivos/Sons/tiro.wav").getAbsoluteFile());


			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        try {
				clip = AudioSystem.getClip();
				clip.open(tiro);
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	}


	public void tiroSom(){
		clip.start();
	}
	public void destroier(Som som) {
		try {
			tiro.close();
			tiro = null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clip.close();
		clip = null;
		som = null;
	}
}
