package model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Config {
	@XStreamAlias("FPS")
	private int fps = 30;
	@XStreamAlias("NIVEL")
	private int nivel=0;

	public int getFPS() {
		return fps;
	}

	public void setFPS(int fps) {
		this.fps = fps;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	
	
}
