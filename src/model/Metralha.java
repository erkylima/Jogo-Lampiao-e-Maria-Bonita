package model;

import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import view.Fase1;

public class Metralha extends Sprite implements Runnable{

	private int distanciaTiro,distanciaAndar;
	private Lampiao inimigo;
	private Fase1 fase;
	private double controlaVelocidade = 0;
	private int velocidade = 5;
	private Thread metralhaThread;
	public Metralha(int aparencia, int colunas, int linhas, int x, int y, String endereco,Lampiao inimigo,Fase1 fase,int vida)
			throws IOException {
		super(aparencia,  colunas, linhas, x, y, endereco,vida);
		this.inimigo = inimigo;
		this.fase = fase;
		
		setDireita(false);
        Random gerador = new Random();
        distanciaTiro = gerador.nextInt(400)+150;
        distanciaAndar = gerador.nextInt(200)+800;
        metralhaThread = new Thread(this);
        metralhaThread.start();
        
	}


	@Override
	public void draw(Graphics g) {
		g.drawImage(getSprites()[getAparencia()], getX(), getY(), null);					
	}

	@Override
	public void animacaoAndandoDireita() {
		controlaVelocidade+=5;
		if(controlaVelocidade>velocidade && (getAparencia() >=5 && getAparencia() <=10)){
			setAparencia(getAparencia()+1);
			controlaVelocidade = 0;
			if(getAparencia() == 10){ setAparencia(5);
			}
		}		
	}

	@Override
	public void animacaoAndandoEsquerda() {
		controlaVelocidade+=5;
		if(controlaVelocidade>velocidade && (getAparencia() >=16 && getAparencia() <=21)){
			setAparencia(getAparencia()+1);
			controlaVelocidade = 0;
			if(getAparencia() == 21){ setAparencia(16);
			}
		}		
	}

	@Override
	public void animacaoParadoDireita() {
		controlaVelocidade+=5;
		if(controlaVelocidade>velocidade && (getAparencia() <=4)){
			setAparencia(getAparencia()+1);
			controlaVelocidade = 0;
			if(getAparencia() == 4){ setAparencia(0);
			}
		}			
	}

	@Override
	public void animacaoParadoEsquerda() {
		controlaVelocidade+=5;
		if(controlaVelocidade>velocidade && (getAparencia() >=11 && getAparencia() <= 15)){
			setAparencia(getAparencia()+1);
			controlaVelocidade = 0;
			if(getAparencia() == 15){ setAparencia(11);
			}
		}			
	}
	
	@Override
	public void mover() {
		if(inimigo.getX() < getX()-distanciaTiro && inimigo.getX()<getX()+600) {
			setX(getX()-11);
			if(getAparencia()<16 || getAparencia()>21) {
				setAparencia(16);
			}
			animacaoAndandoEsquerda();
			if(fase.isCantoDireito(this)){
				setX(getX()+11);
			}
			
		}else if(inimigo.getX() > getX()+distanciaTiro && inimigo.getX()>getX()-600) {
			setX(getX()+11);
			if(getAparencia()<5 || getAparencia() >10) {
				setAparencia(5);
			}
			animacaoAndandoDireita();
			
			if(fase.isCantoEsquerdo(this)){
				setX(getX()-11);
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
					new Tiro(0, 2, 1, getX()+60, getY()+40, "Arquivos/tiro.png", inimigo, alvo, 10).draw(fase.getCamera().getGraphics());;
					Thread.sleep(1000/2);				
				} catch (IOException e) {
					e.printStackTrace();
				}catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
		while(getVida()>=0) {
			mover();
			if((inimigo.getX()>getX()-distanciaAndar) || (inimigo.getX()>getX()-distanciaAndar) ) {
				mover();
			}
			if(inimigo.getX() < getX()) {
				setDireita(false);
				
			}else if(inimigo.getX() > getX()) {
				setDireita(true);
			}
			if(getY()>750) {
				setVida(getVida()-10);
			}
			try {

				Thread.sleep(1000/7);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		destroir(this);

	}

	public void destroir(Metralha metralha){
		metralha.setY(getY()+500);
		metralha = null;
		metralhaThread.stop();
		System.gc();
	}
	
}