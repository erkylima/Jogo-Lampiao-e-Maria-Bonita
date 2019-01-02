package view;

import java.awt.DisplayMode;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import controller.Movimento;
import model.Lampiao;
import model.Metralha;
import model.Sprite;
import model.Status;
import model.TileMap;


public class Fase1 extends JFrame{

	int FPS = 30;
	static int LARGURA = 1024;
	static int ALTURA = 768;
	private Lampiao lampiao;
	private TileMap tile;
	private TileMap bg;
	private Camera camera;	


	public void desenharGraficos() {
		camera.renderizar();

		
	}

	public void atualizar() {
		Graphics2D g = (Graphics2D) getGraphics();
		camera.draw(g);
	}

	public void start() {
		while (true) {
			desenharGraficos();
			atualizar();
			try {
				Thread.sleep(1000/FPS);
			} catch (Exception e) {
				System.out.println("Thread interrompida!");
			}
		}
	}

	public Fase1(String titulo) {
		super(titulo);
		setSize(LARGURA,ALTURA);
		setResizable(false);
		setLayout(null);
		setUndecorated(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

				GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
				GraphicsDevice device = env.getScreenDevices()[0];
				DisplayMode oldMode = device.getDisplayMode();
				DisplayMode newMode = new DisplayMode(1024,768,oldMode.getBitDepth(),oldMode.getRefreshRate());
				device.setFullScreenWindow(this);
				device.setDisplayMode(newMode);

		try{

			lampiao = new Lampiao(15, 48, 1, 40, 644,"Arquivos/lampiaosprite.png",this,120);
			bg = new TileMap(6, 1, 768, 1024, "Arquivos/BG.png", "Arquivos/BGSerraTile.txt");
			tile = new TileMap(160, 24, 32, 32, "Arquivos/Tile.png", "Arquivos/SerraTile.txt");
			bg.montarMapa();
			tile.montarMapa();

		}catch (IOException e) {
			e.printStackTrace();
			System.out.println("Não foi possível carregar a Sprite");
		}

		camera = new Camera(lampiao,new ArrayList<Sprite>(),bg,tile,this);

		Movimento m = new Movimento(lampiao,this);
		addKeyListener(m);

		setVisible(true);
	}

	public boolean isColidindo(Sprite player) {
		for(Rectangle entidade : tile.montarColisao())
			if(player.getBounds().intersects(entidade)) {

				return true;				
			}		
		return false;		
	}

	public boolean isTopo(Sprite player) {

		for(Rectangle entidade : tile.montarColisao())
			if(player.getBounds().intersects(entidade)) {
				if(entidade.getMaxY()>=player.getBounds().getMinY()) {
					return true;	
				}				
			}		
		return false;		
	}

	public boolean isCantoDireito(Sprite player) {

		for(Rectangle entidade : tile.montarColisao())
			if(player.getBounds().intersects(entidade)) {
				if(entidade.getMinX()-10<=player.getBounds().getBounds().getMaxX()) {					
					return true;
					
				}							
			}		
		return false;		
	}

	public boolean isCantoEsquerdo(Sprite player) {

		for(Rectangle entidade : tile.montarColisao())
			if(player.getBounds().intersects(entidade)) {
				if(entidade.getMaxX()+10>=player.getBounds().getBounds().getMinX()) {						
					return true;	
				}							
			}		
		return false;		
	}

	public Camera getCamera() {
		return camera;
	}

	public TileMap getTile() {
		return tile;
	}

	
	
}

