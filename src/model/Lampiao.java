package model;

import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.xml.stream.events.StartDocument;

import com.sun.glass.events.KeyEvent;

import view.Fase1;

public class Lampiao extends Sprite implements Runnable{
	public BufferedImage spriteSheet;   
	public int rows, columns;
//	public int x, y;//� atributo de Personagem
//	public BufferedImage[] sprites;
	public double controlaVelocidade = 0;
	public int velocidade = 5;
	private Fase1 fase;
	
	public Lampiao(int aparencia,int columns, int rows, int posX, int posY,String caminho,Fase1 fase,int vida) throws IOException {
		super(aparencia, columns, rows, posX, posY, caminho,vida);
		this.fase = fase;		
		
	}
	

	
	public void animacaoAndandoDireita(){
		controlaVelocidade+=5;
		if(controlaVelocidade>velocidade && (getAparencia() >=0 && getAparencia() <=13)){
			setAparencia(getAparencia()+1);
			controlaVelocidade = 0;
			if(getAparencia() == 13){ setAparencia(0); }
		}
	}
	
	public void animacaoAndandoEsquerda(){
		controlaVelocidade+=5;
		if(controlaVelocidade>velocidade && (getAparencia() >= 23 && getAparencia() <= 36)){
			setAparencia(getAparencia()+1);
			controlaVelocidade = 0;
			if(getAparencia() == 36){ setAparencia(23); 
			}
		}
	}
	
	public void animacaoParadoDireita(){
		controlaVelocidade+=0.5;
		if(controlaVelocidade>velocidade && (getAparencia() >=14 && getAparencia() <= 22)){
			setAparencia(getAparencia()+1);
			controlaVelocidade = 0;
			if(getAparencia() == 22){ setAparencia(14);  }
		}
	}
	

	
	public void animacaoParadoEsquerda(){
		controlaVelocidade+=0.5;
		if(controlaVelocidade>velocidade && (getAparencia() >=37 && getAparencia() <=45)){
			setAparencia(getAparencia()+1);
			controlaVelocidade = 0;
			if(getAparencia() == 45){ setAparencia(37);		}
		}
	}


	@Override
	public void draw(Graphics g) {
		g.drawImage(getSprites()[getAparencia()], getX(), getY(), null);
	}



	@Override
	public int mover() {
		andar();
		return 918797917;
	}
	
	public void andar(){
		switch(getAcao()) {
		case KeyEvent.VK_D:{
			setDireita(true);
			setX(getX()+4);
			if(getAparencia()>=14) {
				setAparencia(0);
			}
			animacaoAndandoDireita();

			break;
		}
		case KeyEvent.VK_A:{
			setDireita(false);;
			
			setX(getX()-4);
			if(getAparencia()<23 || getAparencia()>=37) {
				setAparencia(24);
			}
			animacaoAndandoEsquerda();
			

			break;
		}
		
		case 0:
			if(isDireita()) {
				if(getAparencia() >=0 && getAparencia() <=14 || getAparencia() == 46) {
					setAparencia(15);
				}
				animacaoParadoDireita();
			}else {
				if(getAparencia()>=23&&getAparencia()<37||getAparencia() == 47) {
					setAparencia(37);
				} 
				animacaoParadoEsquerda();
			}
			break;
			
		
	}
		
		
		
		/*if(getX()>=10 && getX()<=1100) {
			switch(getAcao()){
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
				System.out.println("PULOU");
				pular();
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
		}*/
	}
	
	
	public void pular(){
		int anguloDoPulo = 45;
		int anguloCorrente = anguloDoPulo;
		boolean aux = isDireita();
		double dy,dx;
		if(aux) {
			if(getAparencia()>=14) {
				setAparencia(0);
			}
			animacaoAndandoDireita();
		}else {
			if(getAparencia()<23 || getAparencia()>=37) {
				setAparencia(24);
			}
			animacaoAndandoEsquerda();
		}
		setY(getY()-velocidade);
		while(anguloCorrente != 300) {
			if(anguloCorrente == 0)
				anguloCorrente = 360;
			dy = velocidade * Math.sin(Math.toRadians(anguloCorrente));
			if(aux)
				dx =((velocidade * Math.cos(Math.toRadians(anguloCorrente))) * 1);
			else
				dx =((velocidade * Math.cos(Math.toRadians(anguloCorrente))) * -1);
			
			anguloCorrente--;

			if(!fase.isColidindo(this)) {
				setY(getY()-(int)dy);
				setX(getX()+(int)dx);
			}else {
				setY(getY()+(int)dy);
				setX(getX()-(int)dx);
				break;
			}
			try {
				Thread.sleep(1000/30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		setY(getY()-getY()%velocidade);

	}
	
	public void cair(){
		if(!fase.isTopo(this)) {
			setY(getY()+5);
		}
	}
	
	public void parar() {
		setAcao(0);
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}


//
//	@Override
//	public void keyPressed(java.awt.event.KeyEvent e) {
//		setAcao(e.getKeyCode()) ;
//		lampiao.setAcao(e.getKeyCode());
//		mover();
//	}
//
//	@Override
//	public void keyReleased(java.awt.event.KeyEvent e) {
//		parar();
//	}
//
//
//
//	@Override
//	public void keyTyped(java.awt.event.KeyEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//	
//	@Override
//	public void run() {
//		while(true) {
//			if(KeyEvent.VK_SPACE == getAcao()) {
//				pular();
//			}
//			else if(getAcao() != KeyEvent.VK_SPACE)
//				andar();
//			if(!fase.isColidindo(this) && getY()<817) {
//				cair();
//
//			}else {
//				setY(817);
//
//			}
//			try {
//
//				Thread.sleep(1000/40);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//        
//
//	}

	
}
