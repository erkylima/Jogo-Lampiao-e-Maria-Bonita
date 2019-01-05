package controller;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import model.Inventario;
import model.Lampiao;
import model.Sprite;
import model.Status;
import model.TileMap;

public class Inicializa {
	private Lampiao lampiao;
	private TileMap tileSerra;
	private TileMap bgSerra;
	private TileMap enteiteSerra;
	private JFrame jogo;
	private ImageIcon backgroundMain,lampiaoVolante,passaro1,passaro2,maria;
	
	private ArrayList<TileMap> camadasF1 = new ArrayList<TileMap>();
	private ArrayList<Sprite> inimigos = new ArrayList<Sprite>();
	private Status status;
	private int fps = 30;
	private int LARGURA = 1024;
	private int ALTURA = 768;
	private Inventario inventario;

	public Inicializa() {
		try {
			lampiao = new Lampiao(15, 48, 1, 40, 450,"Arquivos/lampiaosprite.png",null,200);
			bgSerra = new TileMap(8, 1, 1024, 1024, "Arquivos/BG.png", "Arquivos/BGSerraTile.txt");
			tileSerra = new TileMap(224, 20, 32, 32, "Arquivos/Tile.png", "Arquivos/SerraTile.txt");
			enteiteSerra = new TileMap(240,24,32,32,"Arquivos/objetos.png","Arquivos/EnfeiteSerra.txt");
			status = new Status(0, 14, 1, 20, 5, "Arquivos/status.png", lampiao.getVida(), lampiao);
			backgroundMain = new ImageIcon("Arquivos/backgroundMain.jpg");
			lampiaoVolante = new ImageIcon("Arquivos/lampiaoVolante.png");
			passaro1 = new ImageIcon("Arquivos/passaro1.png");
			passaro2 = new ImageIcon("Arquivos/passaro2.png");
			maria = new ImageIcon("Arquivos/maria.png");
			inventario = new Inventario(1024,118,this);
			
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

	public ArrayList<Sprite> getInimigos() {
		return inimigos;
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
	
	public ImageIcon getBackgroundMain() {
		return backgroundMain;
	}

	public ImageIcon getLampiaoVolante() {
		return lampiaoVolante;
	}
	
	public ImageIcon getMaria() {
		return maria;
	}

	public ImageIcon getPassaro1() {
		return passaro1;
	}

	public ImageIcon getPassaro2() {
		return passaro2;
	}

	public JFrame getJogo() {
		return jogo;
	}

	public void setJogo(JFrame jogo) {
		this.jogo = jogo;
	}

	public int getFps() {
		return fps;
	}

	public void setFps(int fps) {
		this.fps = fps;
	}

	public Inventario getInventario() {
		return inventario;
	}
	
	
	
}
