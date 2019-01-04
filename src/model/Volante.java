package model;

import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Volante extends Sprite implements Runnable{
	
	private int distanciaTiro,distanciaAndar;
	private Lampiao inimigo;
	private double controlaVelocidade = 0;
	private int velocidade = 10;
	private Thread volanteThread;
	
	public Volante(int aparencia, int colunas, int linhas, int x, int y, String endereco,Lampiao inimigo,int vida)
			throws IOException {
		super(aparencia,  colunas, linhas, x, y, endereco,vida);
		this.inimigo = inimigo;
		

		setDireita(false);
        Random gerador = new Random();		
        distanciaTiro = gerador.nextInt(400)+100;
        distanciaAndar = gerador.nextInt(200)+700;
        
        volanteThread = new Thread(this);
        volanteThread.start();
        
		}

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
			
			if(inimigo.getFase().isCantoDireito(this)){
				setX(getX()+6);
			}
			
		}else if(inimigo.getX() > getX()+distanciaTiro && inimigo.getX()>getX()-600) {
			setX(getX()+6);
			if(getAparencia()>5) {
				setAparencia(0);
			}
			animacaoAndandoDireita();
			
			if(inimigo.getFase().isCantoEsquerdo(this)){
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
			if(inimigo.getVida()>0){
				try {
					ArrayList<Sprite> alvo = new ArrayList<Sprite>();
					alvo.add(inimigo);
					alvo.add(this);
					new Tiro(0, 2, 1, getX(), getY()+40, "Arquivos/tiro.png", inimigo, alvo, 5,30).draw(inimigo.getFase().getCamera().getGraphics());
					Thread.sleep(1000/2);				
				} catch (IOException e) {
					e.printStackTrace();
				}catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		if(inimigo.getFase().isTopo(this)) {
			setY(getY()-8);
			if(inimigo.getFase().isTopo(this)) {
				pular();
			}
		}else {
			setY(getY()+8);
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
			dy = velocidade * Math.sin(Math.toRadians(anguloCorrente))+1;
			if(aux)
				dx =((velocidade * Math.cos(Math.toRadians(anguloCorrente))) * 1)-3;
			else
				dx =((velocidade * Math.cos(Math.toRadians(anguloCorrente))) * -1)+3;
			
			anguloCorrente--;

			if(!inimigo.getFase().isColidindo(this)) {
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
			if(getVida()<10) {
				destroier(this);
			}
			if(getY()>640) {
				setVida(getVida()-10);
			}
			if(inimigo.getFase().isPulo(this)) {
				pular();
			}
			try {
				if(!volanteThread.isInterrupted()) 
					Thread.sleep(1000/25);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	public void destroier(Volante volante){
		volante.setY(getY()+500);
		volante = null;
		this.volanteThread.stop();
		volanteThread.interrupt();;
		this.volanteThread = null;
		System.gc();
	}
	
}
