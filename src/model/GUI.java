package model;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class GUI extends JFrame implements KeyListener{
	BufferedImage backBuffer;	
	int FPS = 30;
	int janelaW = 1280;
	int janelaH = 960;
	
	Lampiao lampiao = new Lampiao(14, 40, 700);
	Lampiao2 lampiao2;
	ImageIcon fundo = new ImageIcon("Arquivos/BG.png");

	boolean vai,volta,pula = false;
	boolean parouD,parouE=true;
	
	
	//ESSA VARI�VEL PODERIA ESTAR NA CLASSE Sprite
	public void mover(){
		if(lampiao2.x<=1100&& lampiao2.x>=40) {
			if(vai){
				lampiao2.x += 5;
				if(lampiao2.aparencia>=14) {
					lampiao2.aparencia=0;
				}
				lampiao2.animacaoAndandoDireita();
			}
			else if(volta){ 
				lampiao2.x -= 5;
				if(lampiao2.aparencia<=23 || lampiao2.aparencia>37) {
					lampiao2.aparencia=23;
				}
				lampiao2.animacaoAndandoEsquerda();
			}
			else if(!vai && !volta) {
				if(lampiao2.aparencia>23&&lampiao2.aparencia<=37) {
					lampiao2.aparencia=37;
				} 
				lampiao2.animacaoParadoEsquerda();
			}
			else if(!vai && !volta) {
				if(lampiao2.aparencia >=0 && lampiao2.aparencia <=14) {
					lampiao2.aparencia=15;
				}
				lampiao2.animacaoParadoDireita();

			}
			
			System.out.println(vai+" volta "+volta + " aparencia " + lampiao2.aparencia);

			vai=false;
			volta=false;
		}
	}
	public void pular(){
		int y=lampiao.y;

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
		
		bbg.drawImage(lampiao2.sprites[lampiao2.aparencia], lampiao2.x, lampiao2.y, this);
		//bbg.drawImage(lampiao.cenas[lampiao.cena].getImage(), lampiao.x, lampiao.y, this);
		//lampiao.andarMaisLento();	//AQUI CHAMEI O MÃ‰TODO ANIMAR MAIS LENTO
		mover();
		if(!vai && !volta) {
//			lampiao2.animacaoParado();
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
	public GUI(String titulo) {
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
			lampiao2 = new Lampiao2(new File("Arquivos/lampiaosprite.png"), 15, 12, 4, 40, 700);
		}catch (IOException e) {
			e.printStackTrace();
			System.out.println("Não foi possível carregar a Sprite");
		}
		

		
		setVisible(true);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == e.VK_LEFT){
			volta=true;
//			System.out.println(lampiao2.aparencia);
		}
		if(e.getKeyCode() == e.VK_RIGHT){
			vai=true;
			
		}
		if(e.getKeyCode() == e.VK_UP){
			pula=true;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == e.VK_LEFT){
			parouE=true;
		}
		if(e.getKeyCode() == e.VK_RIGHT){
			parouD=true;

		}
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
