package controller;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Lampiao;
import model.Maria;
import model.Sprite;
import model.Status;
import model.TileMap;
import model.Volante;
import view.IniciarJogo;
import view.Inventario;

public class Inicializa {
	private Lampiao lampiao;
	private Maria maria;
	private TileMap tileSerra;
	private TileMap bgSerra;
	private TileMap enteiteSerra;
	private JFrame jogo;
	private ImageIcon backgroundMain,lampiaoVolante,passaro1,passaro2,mariaimg,sobreimg,morreu;
	private int xInicial = 40;
	private int yInicial = 450;
	private int vidaInicial = 200;
	private ArrayList<TileMap> camadasF1 = new ArrayList<TileMap>();
	private ArrayList<Sprite> inimigos = new ArrayList<Sprite>();
	private Status status;
	private int fps = 30;
	private int LARGURA = 1024;
	private int ALTURA = 768;
	private Inventario inventario;

	public Inicializa() {
		try {
			lampiao = new Lampiao(15, 48, 1, getxInicial(), getyInicial(),"Arquivos/lampiaosprite.png",null,getVidaInicial());
//			maria = new Maria(0,28,1,40,345,"Arquivos/mariasprite.png",lampiao,200);
			status = new Status(0, 14, 1, 20, 5, "Arquivos/status.png", lampiao.getVida(), lampiao);
			backgroundMain = new ImageIcon("Arquivos/backgroundMain.jpg");
			lampiaoVolante = new ImageIcon("Arquivos/lampiaoVolante.png");
			passaro1 = new ImageIcon("Arquivos/passaro1.png");
			passaro2 = new ImageIcon("Arquivos/passaro2.png");
			mariaimg = new ImageIcon("Arquivos/maria.png");
			sobreimg = new ImageIcon("Arquivos/sobre.png");
			morreu = new ImageIcon("Arquivos/morreu.png");
			inventario = new Inventario(1024,118,this);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void F1() {
		bgSerra = new TileMap(8, 1, 1024, 1024, "Arquivos/BG.png", "Arquivos/BGSerraTile.txt");
		tileSerra = new TileMap(224, 20, 32, 32, "Arquivos/Tile.png", "Arquivos/SerraTile.txt");
		enteiteSerra = new TileMap(240,24,32,32,"Arquivos/objetos.png","Arquivos/EnfeiteSerra.txt");
		camadasF1.add(bgSerra);
		camadasF1.add(tileSerra);
		camadasF1.add(enteiteSerra);
	}
	
	public Lampiao getLampiao() {
		return lampiao;
	}

	public Maria getMaria() {
		return maria;
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
	
	public ImageIcon getMariaImg() {
		return mariaimg;
	}

	public ImageIcon getSobreimg() {
		return sobreimg;
	}

	public ImageIcon getMorreu() {
		return morreu;
	}

	public ImageIcon getPassaro1() {
		return passaro1;
	}

	public ImageIcon getPassaro2() {
		return passaro2;
	}

	public int getxInicial() {
		return xInicial;
	}

	public int getyInicial() {
		return yInicial;
	}

	public JFrame getJogo() {
		return jogo;
	}

	public void setJogo(JFrame jogo) {
		this.jogo = jogo;
	}

	public int getFPS() {
		return fps;
	}

	public void setFPS(int fps) {
		this.fps = fps;
	}

	public int getVidaInicial() {
		return vidaInicial;
	}

	public Inventario getInventario() {
		return inventario;
	}
	public void retornarMenu(String titulo, String msg) {
		int input = JOptionPane.showOptionDialog(null, msg, titulo, JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

		if(input == JOptionPane.OK_OPTION)
		{
			getLampiao().getFase().zerarInimigos();
			getLampiao().getFase().destroier(getLampiao().getFase());
//			getLampiao().getFase().getInit().getMaria().destroier(getLampiao().getFase().getInit().getMaria());
			getLampiao().getFase().getInit().getJogo().dispose();
			getLampiao().setAcao(0);
			Inicializa init = new Inicializa();		
			new IniciarJogo("Lampião e Maria Bonita", init);
		}
		else if(input == JOptionPane.CANCEL_OPTION) {
			getLampiao().setAcao(0);
		}
	}
	public boolean retornarInicio() {
			getLampiao().setAcao(0);
			getLampiao().setX(getxInicial());
			getLampiao().setY(getyInicial());
			getLampiao().getFase().getCamera().setX(0);
			getLampiao().setVida(getVidaInicial()/2);
			return true;
	}
}
