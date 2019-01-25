package model;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import controller.TratamentoException;

public class Volante extends Sprite implements Runnable{
	
	private int distanciaTiro,distanciaAndar;
	private Lampiao inimigo;
	private double controlaVelocidade = 0;
	private int velocidade = 10;
	private Thread volanteThread;
	private boolean threadOn = true;
	
	public Volante(int aparencia, int colunas, int linhas, int x, int y, String endereco,Lampiao inimigo,int vida)
			throws TratamentoException {
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
		if(controlaVelocidade>velocidade && (getAparencia() <=5) && inimigo.isVivo()){
			setAparencia(getAparencia()+1);
			controlaVelocidade = 0;
			if(getAparencia() == 5){ setAparencia(0);
			}
		}		
	}

	@Override
	public void animacaoAndandoEsquerda() {
		controlaVelocidade+=5;
		if(controlaVelocidade>velocidade && (getAparencia() >=12 && getAparencia() <=17) && inimigo.isVivo()){
			setAparencia(getAparencia()+1);
			controlaVelocidade = 0;
			if(getAparencia() == 17){ setAparencia(12);
			}
		}		
	}

	@Override
	public void animacaoParadoDireita() {
		controlaVelocidade+=5;
		if(controlaVelocidade>velocidade && (getAparencia() >=6 && getAparencia() <=11) && inimigo.isVivo()){
			setAparencia(getAparencia()+1);
			controlaVelocidade = 0;
			if(getAparencia() == 11){ setAparencia(6);
			}
		}			
	}

	@Override
	public void animacaoParadoEsquerda() {
		controlaVelocidade+=5;
		if(controlaVelocidade>velocidade && (getAparencia() >=18 && getAparencia() <= 24) && inimigo.isVivo()){
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
			if(inimigo.getVida()>0 && !inimigo.getFase().isTopo(this)){
				try {
					ArrayList<Sprite> alvo = new ArrayList<Sprite>();
					alvo.add(inimigo);
					alvo.add(this);
					alvo.add(inimigo.getFase().getInit().getMaria());
					new Tiro(0, 2, 1, getX(), getY()+40, "Arquivos/Imagens/tiro.png", inimigo, alvo, 5).draw(inimigo.getFase().getCamera().getGraphics());
					Thread.sleep(1000/2);				
				} catch (TratamentoException e) {
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
				Thread.sleep(1000/(inimigo.getFase().getFPS()-30));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		if(inimigo.getFase().isButtom(this)) {
			setY(getY()-getY()%velocidade);
			cair();
		}
	}
	
	public void cair(){
		if(!inimigo.getFase().isTopo(this)) {
			setY(getY()+8);
		}
	}
	
	@Override
	public void run() {
		while(threadOn && inimigo.isVivo()) {
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
				setVida(getVida()-20);
			}
			if(inimigo.getFase().isPulo(this) && inimigo.isVivo() &&
					!((inimigo.getX()>getX()-distanciaTiro) || (inimigo.getX()>getX()-distanciaTiro))) {
				pular();
			}
			try {
				if(!volanteThread.isInterrupted()) 
					Thread.sleep(1000/(inimigo.getFase().getFPS()-35));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	public void destroier(Volante volante){
		threadOn  = false;
		volante.setY(getY()+500);
		volante = null;
		System.gc();
	}
	
}
