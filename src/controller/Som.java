package controller;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Som {
	private AudioInputStream tiro = null;
    private Clip clip; 
    private Som som;
    public Som() {
		som = this;
	}

	public void tiroSom(){
		new Thread(new Runnable() { // the wrapper thread is unnecessary, unless it blocks on the Clip finishing, see comments
            @Override
            public void run() {
                clip = null;
                try{
                    do{
                        if(clip == null || tiro == null)
                                clip = AudioSystem.getClip();
                        tiro = AudioSystem.getAudioInputStream(new File("Arquivos/Sons/tiro.wav").getAbsoluteFile());
                        if(clip != null && !clip.isActive())
                        	tiro = AudioSystem.getAudioInputStream(new File("Arquivos/Sons/tiro.wav").getAbsoluteFile());
                                clip.open(tiro);
                                clip.start(); 
                    }while(clip.isActive());
                    clip.addLineListener(new LineListener() {
						
						@Override
						public void update(LineEvent l) {
							if (l.getFramePosition()==clip.getFrameLength()){
								destroier(som);
							}					
						}
					});
            		
            		} catch (LineUnavailableException e) {
                    e.printStackTrace();
                } catch (UnsupportedAudioFileException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
//
	}

	public void destroier(Som som) {

		tiro = null;

		clip.close();
		clip = null;
		som = null;
	}
}
