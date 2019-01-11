package model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Config {
	@XStreamAlias("FPS")
	private int fps = 30;

	public int getFPS() {
		return fps;
	}

	public void setFPS(int fps) {
		this.fps = fps;
	}
}
