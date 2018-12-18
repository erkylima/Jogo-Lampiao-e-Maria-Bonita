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


public class Fase1 extends JFrame implements Runnable, KeyListener{
	BufferedImage backBuffer;	
	int FPS = 30;
	int janelaW = 1280;
	int janelaH = 960;
	int ap;
	
	Metralha metralha;
	Lampiao lampiao;
	
	ImageIcon fundo = new ImageIcon("Arquivos/BG.png");

	private int acao;
	
	
	//ESSA VARI�VEL PODERIA ESTAR NA CLASSE Sprite

	
	public void atualizar() {

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
		
		bbg.drawImage(lampiao.getSprites()[lampiao.getAparencia()], lampiao.getX(), lampiao.getY(), this);
		//bbg.drawImage(lampiao.cenas[lampiao.cena].getImage(), lampiao.x, lampiao.y, this);
		//lampiao.andarMaisLento();	//AQUI CHAMEI O MÃ‰TODO ANIMAR MAIS LENTO
		
		acao = lampiao.mover(acao);
//		if(metralha.getAparencia()!=60)
//		bbg.drawImage(metralha.getSprites()[metralha.getAparencia()], metralha.getX(), metralha.getY(), this);

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
		addKeyListener(this);

		backBuffer = new BufferedImage(janelaW, janelaH, BufferedImage.TYPE_INT_RGB);
		try{
			lampiao = new Lampiao(15, 12, 4, 40, 700,"Arquivos/lampiaosprite.png");
			metralha = new Metralha(6, 9, 2, 600, 700, "Arquivos/metralhasprite.png");
		}catch (IOException e) {
			e.printStackTrace();
			System.out.println("Não foi possível carregar a Sprite");
		}
		

		
		setVisible(true);
	}
	

	@Override
	public void keyPressed(KeyEvent e) {

		if(e.getKeyCode() == e.VK_LEFT){
			acao=e.VK_LEFT;
		}


		if(e.getKeyCode() == e.VK_RIGHT){
			acao=e.VK_RIGHT;
		}


		if(e.getKeyCode() == e.VK_UP){
			acao=e.VK_UP;
		}

		if(e.getKeyCode() == e.VK_SPACE){
			acao=e.VK_SPACE;

		}
	}
			
	@Override
	public void keyReleased(KeyEvent e) {

	}


	
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
