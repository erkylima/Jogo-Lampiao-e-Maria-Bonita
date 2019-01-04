package controller;

import java.util.ArrayList;

import javax.swing.JFrame;

import model.Lampiao;
import model.Metralha;
import model.Sprite;
import model.Status;
import model.TileMap;
import model.Volante;
import view.Tela;

public class Inicializa {
	private Lampiao lampiao;
	private TileMap tileSerra;
	private TileMap bgSerra;
	private TileMap enteiteSerra;
	private JFrame jogo;
	private ArrayList<TileMap> camadasF1 = new ArrayList<TileMap>();
	private ArrayList<Sprite> inimigosF1 = new ArrayList<Sprite>();
	private Status status;
	private int LARGURA = 1024;
	private int ALTURA = 768;
	
	public Inicializa() {
		try {
			lampiao = new Lampiao(15, 48, 1, 40, 450,"Arquivos/lampiaosprite.png",null,120);
			bgSerra = new TileMap(8, 1, 1024, 1024, "Arquivos/BG.png", "Arquivos/BGSerraTile.txt");
			tileSerra = new TileMap(224, 20, 32, 32, "Arquivos/Tile.png", "Arquivos/SerraTile.txt");
			enteiteSerra = new TileMap(240,24,32,32,"Arquivos/objetos.png","Arquivos/EnfeiteSerra.txt");
			status = new Status(0, 14, 1, 20, 5, "Arquivos/status.png", lampiao.getVida(), lampiao);
			
			camadasF1.add(bgSerra);
			camadasF1.add(tileSerra);
			camadasF1.add(enteiteSerra);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public Lampiao getLampiao() {
		return lampiao;
	}

	public TileMap getTileSerra() {
		return tileSerra;
	}

	public TileMap getBgSerra() {
		return bgSerra;
	}
	
	public TileMap getEnteiteSerra() {
		return enteiteSerra;
	}

	public ArrayList<TileMap> getCamadasF1() {
		return camadasF1;
	}

	public ArrayList<Sprite> getInimigosF1() {
		return inimigosF1;
	}

	public Status getStatus() {
		return status;
	}

	public int getLARGURA() {
		return LARGURA;
	}

	public int getALTURA() {
		return ALTURA;
	}

	public JFrame getJogo() {
		return jogo;
	}

	public void setJogo(JFrame jogo) {
		this.jogo = jogo;
	}
	
	
	
	
	
}
