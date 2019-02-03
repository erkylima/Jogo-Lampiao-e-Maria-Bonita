package model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Config {
	@XStreamAlias("FPS")
	private int fps = 30;
	@XStreamAlias("NIVEL")
	private double nivel=0;
	@XStreamAlias("RECORD")
	private double record=0;
	@XStreamAlias("RECORDMPLAYER")
	private double recordmplayer=0;
	
	public int getFPS() {
		return fps;
	}

	public void setFPS(int fps) {
		this.fps = fps;
	}

	public double getNivel() {
		return nivel;
	}

	public void setNivel(double nivel) {
		this.nivel = nivel;
	}

	public double getRecord() {
		return record;
	}

	public void setRecord(double record) {
		this.record = record;
	}

	public double getRecordmplayer() {
		return recordmplayer;
	}

	public void setRecordmplayer(double recordmplayer) {
		this.recordmplayer = recordmplayer;
	}
	
	
}
