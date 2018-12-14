package view;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import controller.Som;
import model.Lampiao;
import model.Metralha;


public class Fase1 extends JFrame implements KeyListener{
	BufferedImage backBuffer;	
	int FPS = 30;
	int janelaW = 1280;
	int janelaH = 960;
	int ap;
	
	Metralha metralha;
	Lampiao lampiao;
	
	ImageIcon fundo = new ImageIcon("Arquivos/BG.png");

	boolean vai,volta,pula,tiro = false;
	boolean parouD,parouE=true;
	
	
	//ESSA VARI�VEL PODERIA ESTAR NA CLASSE Sprite
	public void mover(){
		if(lampiao.x>=10 && lampiao.x<=1100) {
			
			if(vai){
				if(lampiao.x!=1100) {
					lampiao.x += 10;					
				}
				if(lampiao.aparencia>=14) {
					lampiao.aparencia=0;
				}
				lampiao.animacaoAndandoDireita();
			}
			if(volta){ 
				if(lampiao.x!=10) {
					lampiao.x -= 10;	
				}
				if(lampiao.aparencia<=23 || lampiao.aparencia>37) {
					lampiao.aparencia=23;
				}
				lampiao.animacaoAndandoEsquerda();
			}
			
			if(!vai && !volta && !tiro) {
				if(lampiao.aparencia>23&&lampiao.aparencia<=37) {
					lampiao.aparencia=37;
				} 
				lampiao.animacaoParadoEsquerda();
			}

			if(!vai && !volta && !tiro) {
				if(lampiao.aparencia >=0 && lampiao.aparencia <=14) {
					lampiao.aparencia=15;
				}
				lampiao.animacaoParadoDireita();

			}
			
			if(!vai && !volta && tiro) {
				if(lampiao.aparencia>37 && lampiao.aparencia != 47 && lampiao.aparencia != 46 ) {
					ap = lampiao.aparencia;
					lampiao.aparencia = 47;
				}
				if(lampiao.aparencia <=37) {
					ap = lampiao.aparencia;
					lampiao.aparencia=46;
				}
				
			}
			vai=false;
			volta=false;
			tiro=false;
		}
	}
	public void pular(){

//		if(pula){
//			while ( y >= (y-100) ) {
//
//				if( lampiao.y == ( y-100 ) ) {
//
//					while ( y > ( lampiao.y ) ) {
//						lampiao.y+=0.1;
//
//					}
//					System.out.println( "HE " + y +" TO " + lampiao.y);
//
//					pula = false;
//					break;
//				}
//
//				lampiao.y-= 0.1;
//			}
//		}


	}
	
	public void atualizar() {
//		mover();
		pular();
	}
	public void desenharGraficos() {
		Graphics g = getGraphics();	//ISSO JÃ� ESTAVA AQUI
		Graphics bbg = backBuffer.getGraphics();//ISSO TAMBÃ‰M JÃ� ESTAVA AQUI...
		//AQUI VAMOS MANDAR DESENHAR ALGUNS IMAGENS NA TELA
		bbg.drawImage(fundo.getImage(),0,0,this);//QUI DESENHAMOS O FUNDO
		//AS DIMENSÃ•ES ORIGINAIS DO FUNDO SÃƒO: 500X500 QUE Ã‰ O TAMANHO DA NOSSA TELA!
		
		//AQUI TO DESENHANDO A O NOSSO PERSONAGEM
		//VEJA QUE NOSSO vilÃ£o tem tudo que agente precisa!
		//SUAS COORDENADAS, LARGURA, ALTURA, E AS IMAGENS!!!
//		bbg.drawImage(vilao.cenas[vilao.cena].getImage(), vilao.x, vilao.y, this);
//		vilao.animar();	//AQUI CHAMEI O MÃ‰TODO ANIMAR
		
		bbg.drawImage(lampiao.sprites[lampiao.aparencia], lampiao.x, lampiao.y, this);
		//bbg.drawImage(lampiao.cenas[lampiao.cena].getImage(), lampiao.x, lampiao.y, this);
		//lampiao.andarMaisLento();	//AQUI CHAMEI O MÃ‰TODO ANIMAR MAIS LENTO
		
		mover();
		if(metralha.getAparencia()!=60)
		bbg.drawImage(metralha.getSprites()[metralha.getAparencia()], metralha.getX(), metralha.getY(), this);
		if(!vai && !volta) {
//			lampiao.animacaoParado();
		}
		//==================================================================================	
		g.drawImage(backBuffer, 0, 0, this);//OBS: ISSO DEVE FICAR SEMPRE NO FINAL!
	}
	
	public void run() {
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
		addKeyListener(this);

		backBuffer = new BufferedImage(janelaW, janelaH, BufferedImage.TYPE_INT_RGB);
		try{
			lampiao = new Lampiao(new File("Arquivos/lampiaosprite.png"), 15, 12, 4, 40, 700);
			metralha = new Metralha(6, 2205, 296, 9, 2, 600, 700, "Arquivos/metralhasprite.png");
		}catch (IOException e) {
			e.printStackTrace();
			System.out.println("Não foi possível carregar a Sprite");
		}
		

		
		setVisible(true);
	}
	

	@Override
	public void keyPressed(KeyEvent e) {
		new Thread() {
			@Override
			public void run() {
				if(e.getKeyCode() == e.VK_LEFT){
					volta=true;
				}
			}
		}.start();

		new Thread() {
			@Override
			public void run() {
				if(e.getKeyCode() == e.VK_RIGHT){
					vai=true;
					
				}
			}
		}.start();

		new Thread() {
			@Override
			public void run() {
				if(e.getKeyCode() == e.VK_UP){
					pula=true;
				}
			}
		}.start();

		new Thread() {
			@Override
			public void run() {
			if(e.getKeyCode() == e.VK_SPACE){
				tiro=true;
			}
			}
		}.start();
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == e.VK_LEFT){
			parouE=true;
		}
		if(e.getKeyCode() == e.VK_RIGHT){
			parouD=true;

		}
		new Thread() {
			@Override
			public void run() {
				if(e.getKeyCode() == e.VK_SPACE) {
					new Som().tiroSom();
					lampiao.aparencia =ap;
					if(metralha.getAparencia()!=60)
							metralha.setAparencia(60);
				}
			}
		}.start();

	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
