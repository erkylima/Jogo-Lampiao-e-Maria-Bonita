package model;

import java.awt.Graphics;
import java.io.IOException;
import java.util.Random;

import view.Fase1;

public class Volante extends Sprite implements Runnable{
	private int distanciaTiro,distanciaAndar;
	private Sprite inimigo;
	private Fase1 fase;
	public double controlaVelocidade = 0;
	public int velocidade = 10;
	
	public Volante(int aparencia, int colunas, int linhas, int x, int y, String endereco,Sprite inimigo,Fase1 fase,int vida)
			throws IOException {
		super(aparencia,  colunas, linhas, x, y, endereco,vida);
		this.inimigo = inimigo;
		this.fase = fase;		
		setDireita(false);
        Random gerador = new Random();		
        distanciaTiro = gerador.nextInt(400)+100;
        distanciaAndar = gerador.nextInt(200)+800;
		Thread volante = new Thread(this);
		volante.start();	}

	@Override
	public void animacaoAndandoDireita() {
		controlaVelocidade+=5;
		if(controlaVelocidade>velocidade && (getAparencia() <=5)){
			setAparencia(getAparencia()+1);
			controlaVelocidade = 0;
			if(getAparencia() == 5){ setAparencia(0);
			}
		}		
	}

	@Override
	public void animacaoAndandoEsquerda() {
		controlaVelocidade+=5;
		if(controlaVelocidade>velocidade && (getAparencia() >=12 && getAparencia() <=17)){
			setAparencia(getAparencia()+1);
			controlaVelocidade = 0;
			if(getAparencia() == 17){ setAparencia(12);
			}
		}		
	}

	@Override
	public void animacaoParadoDireita() {
		controlaVelocidade+=5;
		if(controlaVelocidade>velocidade && (getAparencia() >=6 && getAparencia() <=11)){
			setAparencia(getAparencia()+1);
			controlaVelocidade = 0;
			if(getAparencia() == 11){ setAparencia(6);
			}
		}			
	}

	@Override
	public void animacaoParadoEsquerda() {
		controlaVelocidade+=5;
		if(controlaVelocidade>velocidade && (getAparencia() >=18 && getAparencia() <= 24)){
			setAparencia(getAparencia()+1);
			controlaVelocidade = 0;
			if(getAparencia() == 24){ setAparencia(18);
			}
		}			
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(getSprites()[getAparencia()], getX(), getY(), null);					
	}

	@Override
	public void mover() {
		
		if(inimigo.getX() < getX()-distanciaTiro && inimigo.getX()<getX()+600) {
			setX(getX()-6);
			if(getAparencia()<12 || getAparencia()>17) {
				setAparencia(12);
			}
			animacaoAndandoEsquerda();
			if(fase.isCantoDireito(this)){
				setX(getX()+6);
			}
			
		}else if(inimigo.getX() > getX()+distanciaTiro && inimigo.getX()>getX()-600) {
			setX(getX()+6);
			if(getAparencia()>5) {
				setAparencia(0);
			}
			animacaoAndandoDireita();
			
			if(fase.isCantoEsquerdo(this)){
				setX(getX()-6);
			}

		}else {
			if(isDireita()) {
				if(getAparencia()<6 || getAparencia()>11) {
					setAparencia(6);
				}
				animacaoParadoDireita();
			}else {
				if(getAparencia()<18 || getAparencia()>24) {
					setAparencia(18);
				}
				animacaoParadoEsquerda();
			}
		}
		if(fase.isTopo(this)) {
			setY(getY()-4);
			if(fase.isTopo(this)) {
				pular();
			}
		}else {
			setY(getY()+4);
		}
	}
	
	public void pular(){
		int anguloDoPulo = 25;
		int anguloCorrente = anguloDoPulo;
		boolean aux = isDireita();
		double dy,dx;
		if(aux) {
			if(getAparencia()>=6) {
				setAparencia(0);
			}
			animacaoAndandoDireita();
		}else {
			if(getAparencia()<12 || getAparencia()>17) {
				setAparencia(12);
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
				setY(getY()-((int)dy));
				setX(getX()+((int)dx));
			}else {
				setY(getY()+((int)dy));
				setX(getX()-((int)dx));
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
	
	@Override
	public void run() {
		while(true) {
			if((inimigo.getX()>getX()-distanciaAndar) || (inimigo.getX()>getX()-distanciaAndar) ) {
				mover();
			}
			if(inimigo.getX() < getX()) {
				setDireita(false);
				
			}else if(inimigo.getX() > getX()) {
				setDireita(true);
			}
			try {

				Thread.sleep(1000/25);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
