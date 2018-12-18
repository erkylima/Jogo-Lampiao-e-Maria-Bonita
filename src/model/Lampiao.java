package model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.glass.events.KeyEvent;

import view.Fase1;

public class Lampiao extends Sprite implements Runnable{
	public BufferedImage spriteSheet;   
	public int rows, columns;
//	public int x, y;//é atributo de Personagem
//	public BufferedImage[] sprites;
	public int controlaVelocidade = 0;
	public int velocidade = 5;
	private boolean direita,parado;
	
	public Lampiao(int aparencia,int columns, int rows, int posX, int posY,String caminho) throws IOException {
		super(aparencia, columns, rows, posX, posY, caminho);
		
	}
	

	
	public void animacaoAndandoDireita(){
		controlaVelocidade+=5;
		if(controlaVelocidade>velocidade && (getAparencia() >=0 && getAparencia() <=14)){
			setAparencia(getAparencia()+1);
			controlaVelocidade = 0;
			if(getAparencia() == 14){ setAparencia(0); }
		}
	}
	
	public void animacaoParadoDireita(){
		controlaVelocidade+=1;
		if(controlaVelocidade>velocidade && (getAparencia() >=15 && getAparencia() <= 22)){
			setAparencia(getAparencia()+1);
			controlaVelocidade = 0;
			if(getAparencia() == 22){ setAparencia(15);  }
		}
	}

	
	public void animacaoAndandoEsquerda(){
		controlaVelocidade+=5;
//		System.out.println(aparencia);
		if(controlaVelocidade>velocidade && (getAparencia() >= 23 && getAparencia()<=36)){
			setAparencia(getAparencia()+1);
			controlaVelocidade = 0;
			if(getAparencia() == 36){ setAparencia(23); 
			}
		}
	}	
	
	public void animacaoParadoEsquerda(){
		controlaVelocidade+=1;
		if(controlaVelocidade>velocidade && (getAparencia() >=37 && getAparencia() <=45)){
			setAparencia(getAparencia()+1);
			controlaVelocidade = 0;
			if(getAparencia() == 45){ setAparencia(37);
			}
		}
	}



	@Override
	public void animar(int direcao) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public int mover(int acao) {
		andar(acao);
		return 918797917;
	}
	
	public void andar(int acao){
		if(getX()>=10 && getX()<=1100) {
			switch(acao){
			case KeyEvent.VK_RIGHT:
				if(getX()!=1100) {
					setX(getX()+10);					
				}
				if(getAparencia()>=14) {
					setAparencia(0);
				}
				direita=true;
				parado=false;
				animacaoAndandoDireita();
				
				break;
			case KeyEvent.VK_LEFT: 
				if(getX()!=10) {
					setX(getX()-10);	
				}
				if(getAparencia()<=23 || getAparencia()>37) {
					setAparencia(23);
				}
				direita=false;
				parado=false;
				animacaoAndandoEsquerda();
				
				break;
			case KeyEvent.VK_UP:

				break;
			case KeyEvent.VK_SPACE:
				if(parado){
					if(getAparencia()>37 && getAparencia() != 47 && getAparencia() != 46 ) {

						setAparencia(47); 
					}
					if(getAparencia() <=37) {
						setAparencia(46);
					}
				}
				break;
			default:
				if(direita){
					if(getAparencia() >=0 && getAparencia() <=14 || getAparencia() == 46) {
						setAparencia(15);
					}
					animacaoParadoDireita();
				}else{
					if(getAparencia()>23&&getAparencia()<=37||getAparencia() == 47) {
						setAparencia(37);
					} 
					animacaoParadoEsquerda();
				}
				parado=true;
				break;
			}	
		}
	}
//	public void pular(){



	@Override
	public void run() {
		
		
	}

//		if(pula){
//			while ( y >= (y-100) ) {
//
//				if( y == ( y-100 ) ) {
//
//					while ( y > ( y ) ) {
//						y+=0.1;
//
//					}
//					System.out.println( "HE " + y +" TO " + y);
//
//					pula = false;
//					break;
//				}
//
//				y-= 0.1;
//			}
//		}


//	}
}
