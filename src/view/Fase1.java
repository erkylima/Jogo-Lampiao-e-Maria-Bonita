package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
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
	int janelaW = 1280;
	int janelaH = 960;
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
		bg.montarMapa();
	}
	public void desenharGraficos() {
		Graphics2D g = (Graphics2D) getGraphics();	//ISSO JÃ� ESTAVA AQUI
		Graphics2D bbg = (Graphics2D) backBuffer.getGraphics();//ISSO TAMBÃ‰M JÃ� ESTAVA AQUI...
		bbg.drawImage(bg.getMapa(), 0, 0,this);
		bbg.drawImage(tile.getMapa(),0,0,this);//QUI DESENHAMOS O FUNDO

		
		lampiao.draw(bbg);
		//bbg.drawImage(lampiao.cenas[lampiao.cena].getImage(), lampiao.x, lampiao.y, this);
		//lampiao.andarMaisLento();	//AQUI CHAMEI O MÃ‰TODO ANIMAR MAIS LENTO
		
		//acao = lampiao.mover();
//		if(metralha.getAparencia()!=60)
		bbg.drawImage(metralha.getSprites()[metralha.getAparencia()], metralha.getX(), metralha.getY(), this);
		metralha.animar(1);
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
//		setUndecorated(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		
		backBuffer = new BufferedImage(janelaW, janelaH, BufferedImage.TYPE_INT_RGB);
		try{
			lampiao = new Lampiao(15, 12, 4, 40, 820,"Arquivos/lampiaosprite.png",this);
			metralha = new Metralha(6, 9, 2, 600, 820, "Arquivos/metralhasprite.png");
			tile = new TileMap(120, 30, 32, 32, "Arquivos/Tile.png", "Arquivos/SerraTile.txt");
			bg = new TileMap(3, 1, 1280, 960, "Arquivos/BG.png", "Arquivos/BGSerraTile.txt");

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
//		Rectangle playerDirecionado = new Rectangle(player.getX() + x,
//				player.getY() + y, player.getLarguraPersonagem(), player.getAlturaPersonagem());
		
				for(Rectangle entidade : tile.montarColisao())
					if(player.getBounds().intersects(entidade)) {
						System.out.println("Player\nMin Y: "+player.getBounds().getMinY()
								+ "\nMin X: " + player.getBounds().getMinX()
								+ "\nMax Y: " + player.getBounds().getMaxY()
								+ "\nMax X: " + player.getBounds().getMaxX()
								+ "\nPos Y: " + player.getBounds().y);
						
						System.out.println("Entidade\nMin Y: "+entidade.getMinY()
								+ "\nMin X: " + entidade.getMinX()
								+ "\nMax Y: " + entidade.getMaxY()
								+ "\nMax X: " + entidade.getMaxX());
						System.out.println("Colidiu");
						return true;
					}
		return false;
	}
//	public boolean isColidindo(Sprite player,int X,int Y) {
//		Rectangle playerDirecionado = new Rectangle(player.getX() + X,
//				player.getY() + Y, player.getLarguraPersonagem(), player.getAlturaPersonagem());
//		
//				for(Rectangle entidade : tile.montarColisao())
//					if(playerDirecionado.getBounds().intersects(entidade)) {
//						System.out.println("Player\nMin Y: "+playerDirecionado.getBounds().getMinY()
//								+ "\nMin X: " + playerDirecionado.getBounds().getMinX()
//								+ "\nMax Y: " + playerDirecionado.getBounds().getMaxY()
//								+ "\nMax X: " + playerDirecionado.getBounds().getMaxX()
//								+ "\nPos Y: " + playerDirecionado.getBounds().y);
//						
//						System.out.println("Entidade\nMin Y: "+entidade.getMinY()
//								+ "\nMin X: " + entidade.getMinX()
//								+ "\nMax Y: " + entidade.getMaxY()
//								+ "\nMax X: " + entidade.getMaxX());
//						System.out.println("Colidiu");
//						return true;
//					}
//		return false;
//	}
}

