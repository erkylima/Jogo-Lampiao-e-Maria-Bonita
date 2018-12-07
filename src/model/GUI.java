package model;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class GUI extends JFrame implements KeyListener{
	BufferedImage backBuffer;	
	int FPS = 30;
	int janelaW = 1280;
	int janelaH = 960;
	
	Lampiao lampiao = new Lampiao(14, 40, 700);
	ImageIcon fundo = new ImageIcon("Arquivos/BG.png");

	boolean vai,volta,pula = false;
	
	
	//ESSA VARI�VEL PODERIA ESTAR NA CLASSE Sprite
	public void mover(){
		if(vai && lampiao.x<700){  lampiao.x += 5;  vai=false;}
		if(volta && lampiao.x>40){  lampiao.x -= 5;  volta=false;}

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
		mover();
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
		
		
		bbg.drawImage(lampiao.cenas[lampiao.cena].getImage(), lampiao.x, lampiao.y, this);
		lampiao.andarMaisLento();	//AQUI CHAMEI O MÃ‰TODO ANIMAR MAIS LENTO
		
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
		
		//QUI CARREGAMOS AS IMAGENS DE NOSSA SPRITE!!!!!!!
		//PARA O VETOR DE ImageIcon[] !!!
		lampiao.cenas[0] = new ImageIcon("Arquivos/Andando/m1.png");
		lampiao.cenas[1] = new ImageIcon("Arquivos/Andando/m2.png");
		lampiao.cenas[2] = new ImageIcon("Arquivos/Andando/m3.png");
		lampiao.cenas[3] = new ImageIcon("Arquivos/Andando/m4.png");
		lampiao.cenas[4] = new ImageIcon("Arquivos/Andando/m5.png");
		lampiao.cenas[5] = new ImageIcon("Arquivos/Andando/m6.png");
		lampiao.cenas[6] = new ImageIcon("Arquivos/Andando/m7.png");
		lampiao.cenas[7] = new ImageIcon("Arquivos/Andando/m8.png");
		lampiao.cenas[8] = new ImageIcon("Arquivos/Andando/m9.png");
		lampiao.cenas[9] = new ImageIcon("Arquivos/Andando/m10.png");
		lampiao.cenas[10] = new ImageIcon("Arquivos/Andando/m11.png");
		lampiao.cenas[11] = new ImageIcon("Arquivos/Andando/m12.png");
		lampiao.cenas[12] = new ImageIcon("Arquivos/Andando/m13.png");
		lampiao.cenas[13] = new ImageIcon("Arquivos/Andando/m14.png");
		lampiao.largura = 107;	//LARGURA DO VILÃƒO
		lampiao.altura =  111;	//ALTURA DO VILÃƒO , mas nÃ£o vou usar isso agora..
		
		setVisible(true);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == e.VK_LEFT){
			volta=true;
		}
		if(e.getKeyCode() == e.VK_RIGHT){
			vai=true;
		}
		if(e.getKeyCode() == e.VK_UP){
			pula=true;
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
