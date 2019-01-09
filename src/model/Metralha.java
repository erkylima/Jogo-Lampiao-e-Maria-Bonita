package model;

import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Metralha extends Sprite implements Runnable{

	private int distanciaTiro,distanciaAndar;
	private Lampiao inimigo;
	private double controlaVelocidade = 0;
	private int velocidade = 10;
	private Thread metralhaThread;
	private boolean threadOn = true;
	public Metralha(int aparencia, int colunas, int linhas, int x, int y, String endereco,Lampiao inimigo,int vida)
			throws IOException {
		super(aparencia,  colunas, linhas, x, y, endereco,vida);
		this.inimigo = inimigo;
		setDireita(false);
        Random gerador = new Random();		
        distanciaTiro = gerador.nextInt(400)+100;
        distanciaAndar = gerador.nextInt(200)+800;
       
        metralhaThread = new Thread(this);
        metralhaThread.start();
        
	}

	@Override
	public void animacaoAndandoDireita() {
		controlaVelocidade+=5;
		if(controlaVelocidade>velocidade && (getAparencia() >=5 && getAparencia() <=10)&& inimigo.isVivo()){
			setAparencia(getAparencia()+1);
			controlaVelocidade = 0;
			if(getAparencia() == 10){ setAparencia(5);
			}
		}		
	}

	@Override
	public void animacaoAndandoEsquerda() {
		controlaVelocidade+=5;
		if(controlaVelocidade>velocidade && (getAparencia() >=16 && getAparencia() <=21)&& inimigo.isVivo()){
			setAparencia(getAparencia()+1);
			controlaVelocidade = 0;
			if(getAparencia() == 21){ setAparencia(16);
			}
		}		
	}

	@Override
	public void animacaoParadoDireita() {
		controlaVelocidade+=5;
		if(controlaVelocidade>velocidade && (getAparencia() <=4) && inimigo.isVivo()){
			setAparencia(getAparencia()+1);
			controlaVelocidade = 0;
			if(getAparencia() == 4){ setAparencia(0);
			}
		}			
	}

	@Override
	public void animacaoParadoEsquerda() {
		controlaVelocidade+=5;
		if(controlaVelocidade>velocidade && (getAparencia() >=11 && getAparencia() <= 15) && inimigo.isVivo()){
			setAparencia(getAparencia()+1);
			controlaVelocidade = 0;
			if(getAparencia() == 15){ setAparencia(11);
			}
		}			
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(getSprites()[getAparencia()], getX(), getY(), null);					
	}
	
	@Override
	public void mover() {
		if(inimigo.getX() < getX()-distanciaTiro && inimigo.getX()<getX()+600 ) {
			setX(getX()-15);
			if(getAparencia()<16 || getAparencia()>21) {
				setAparencia(16);
			}
			animacaoAndandoEsquerda();
			if(inimigo.getFase().isCantoDireito(this)){
				setX(getX()+15);
			}
			
		}else if(inimigo.getX() > getX()+distanciaTiro && inimigo.getX()>getX()-600) {
			setX(getX()+15);
			if(getAparencia()<5 || getAparencia() >10) {
				setAparencia(5);
			}
			animacaoAndandoDireita();
			
			if(inimigo.getFase().isCantoEsquerdo(this)){
				setX(getX()-15);
			}

		}else {
			if(isDireita()) {
				if(getAparencia()>4) {
					setAparencia(0);
				}
				animacaoParadoDireita();
				
			}else {
				if(getAparencia()<11 || getAparencia()>15) {
					setAparencia(11);
				}
				animacaoParadoEsquerda();
				
			}
			if(inimigo.getVida()>0){
				try {
					ArrayList<Sprite> alvo = new ArrayList<Sprite>();
					alvo.add(inimigo);
					alvo.add(this);
					new Tiro(0, 2, 1, getX(), getY()+40, "Arquivos/tiro.png", inimigo, alvo, 5).draw(inimigo.getFase().getCamera().getGraphics());
					Thread.sleep(1000/(inimigo.getFase().getFPS()-57));
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
			dy = velocidade * Math.sin(Math.toRadians(anguloCorrente));
			if(aux)
				dx =((velocidade * Math.cos(Math.toRadians(anguloCorrente))) * 1);
			else
				dx =((velocidade * Math.cos(Math.toRadians(anguloCorrente))) * -1);
			
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
				Thread.sleep(1000/(inimigo.getFase().getFPS()-20));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		setY(getY()-getY()%velocidade);

	}


	@Override
	public void run() {
		while(threadOn) {
			if((inimigo.getX()>getX()-distanciaAndar) || (inimigo.getX()>getX()-distanciaAndar) && inimigo.isVivo()) {
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
			if(inimigo.getFase().isPulo(this)&& inimigo.isVivo()) {
				pular();
			}
			try {
				if(!metralhaThread.isInterrupted()) 
					Thread.sleep(1000/(inimigo.getFase().getFPS()-40));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public Thread getMetralhaThread() {
		return metralhaThread;
	}

	public void destroier(Metralha metralha){
		metralha.setY(getY()+500);
		threadOn = false;
		metralha = null;
		System.gc();
	}
	
}