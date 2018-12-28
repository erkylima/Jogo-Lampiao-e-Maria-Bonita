package view;

import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import controller.Movimento;
import controller.Som;
import model.Lampiao;
import model.Metralha;
import model.Sprite;
import model.TileMap;


public class Fase1 extends JFrame{
	BufferedImage backBuffer;	
	int FPS = 30;
	int janelaW = 1024;
	int janelaH = 768;
	int ap;
	
	Metralha metralha;
	Lampiao lampiao;
	TileMap tile;
	TileMap bg;
	ImageIcon fundo = new ImageIcon("Arquivos/BG.png");

	private int acao;
	
	
	//ESSA VARI�VEL PODERIA ESTAR NA CLASSE Sprite

	
	public void atualizar() {
		tile.montarMapa();
	}
	public void desenharGraficos() {
		Graphics2D g = (Graphics2D) getGraphics();
		Graphics2D bbg = (Graphics2D) backBuffer.getGraphics();
		bbg.drawImage(bg.getMapa(), 0, 0,this);
		bbg.drawImage(tile.getMapa(),0,0,this);


		lampiao.draw(bbg);

		bbg.drawImage(metralha.getSprites()[metralha.getAparencia()], metralha.getX(), metralha.getY(), this);
		metralha.animar();
		//==================================================================================	
		g.drawImage(backBuffer, 0, 0, this);//OBS: ISSO DEVE FICAR SEMPRE NO FINAL!
	}
	
	public void start() {
		while (true) {
			atualizar();
			desenharGraficos();
				try {
					Thread.sleep(1000/FPS);
				} catch (Exception e) {
					System.out.println("Thread interrompida!");
				}
		}
	}
	public Fase1(String titulo) {
		super(titulo);
		setSize(janelaW,janelaH);
		setResizable(false);
		setLayout(null);
		setUndecorated(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
//		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
//		GraphicsDevice device = env.getScreenDevices()[0];
//		DisplayMode oldMode = device.getDisplayMode();
//		DisplayMode newMode = new DisplayMode(1024,768,oldMode.getBitDepth(),oldMode.getRefreshRate());
//		device.setFullScreenWindow(this);
//		device.setDisplayMode(newMode);
		
		backBuffer = new BufferedImage(janelaW, janelaH, BufferedImage.TYPE_INT_RGB);
		try{
			lampiao = new Lampiao(15, 12, 4, 40, 644,"Arquivos/lampiaosprite.png",this,100);
			metralha = new Metralha(6, 9, 2, 600, 625, "Arquivos/metralhasprite.png",30);
			tile = new TileMap(128, 24, 32, 32, "Arquivos/Tile.png", "Arquivos/SerraTile.txt");
			bg = new TileMap(4, 1, 1024, 768, "Arquivos/BG2.png", "Arquivos/BGSerraTile.txt");
			bg.montarMapa();

		}catch (IOException e) {
			e.printStackTrace();
			System.out.println("Não foi possível carregar a Sprite");
		}
		Movimento m = new Movimento(lampiao,this);
//		Thread t = new Thread(lampiao);
//		t.start();
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
	
	public boolean isCantoEsquerdo(Sprite player) {

		for(Rectangle entidade : tile.montarColisao())
			if(player.getBounds().intersects(entidade)) {
				if(entidade.getMinX()-10<=player.getBounds().getBounds().getMaxX()) {					
					return true;	
				}							
			}		
		return false;		
	}
	
	public boolean isCantoDireito(Sprite player) {

		for(Rectangle entidade : tile.montarColisao())
			if(player.getBounds().intersects(entidade)) {
				if(entidade.getMaxX()-10<=player.getBounds().getBounds().getMinX()) {					
					return true;	
				}							
			}		
		return false;		
	}
}

