package model;

import java.awt.Graphics;
import java.io.IOException;
import java.util.Random;

import view.Fase1;

public class Metralha extends Sprite implements Runnable{

	public double controlaVelocidade = 0;
	public int velocidade = 10;
	private Sprite inimigo;
	private int distancia;
	private Fase1 fase;
	
	public Metralha(int aparencia, int colunas, int linhas, int x, int y, String endereco,Sprite inimigo,Fase1 fase,int vida)
			throws IOException {
		super(aparencia,  colunas, linhas, x, y, endereco,vida);
		this.inimigo = inimigo;
		this.fase = fase;
		Thread metralha = new Thread(this);
		metralha.start();
        Random gerador = new Random();
        distancia = gerador.nextInt(70)+280;
	}

	public void animar() {
		controlaVelocidade+=5;
		if(controlaVelocidade>velocidade && (getAparencia() >=0 && getAparencia() <=13)){
			setAparencia(getAparencia()+1);
			controlaVelocidade = 3;
			if(getAparencia() == 13){ setAparencia(0);
			}
		}
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(getSprites()[getAparencia()], getX(), getY(), null);	
	}

	@Override
	public void mover() {
		if(getX()-distancia>inimigo.getX() && inimigo.getX()>getX()-800) {
			if(!fase.isCantoDireito(this)) {

				setX(getX()-3);
				if(getAparencia()<18 || getAparencia()>24) {
					setAparencia(18);
				}
				animacaoAndandoEsquerda();
			}else {
				setX(getX()+5);

			}


		}else if(getX()+distancia<inimigo.getX() && inimigo.getX()<getX()+800) {
			if(!fase.isCantoEsquerdo(this)) {

				setX(getX()+3);
				if(getAparencia()<5 || getAparencia()>10) {
					setAparencia(5);
				}
				animacaoAndandoDireita();
			}else {
				setX(getX()-6);
			}
		}
		
		
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
		if(controlaVelocidade>velocidade && (getAparencia() >=18 && getAparencia() <=24)){
			setAparencia(getAparencia()+1);
			controlaVelocidade = 0;
			if(getAparencia() == 24){ setAparencia(18);
			}
		}		
	}

	@Override
	public void animacaoParadoDireita() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void animacaoParadoEsquerda() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		while(true) {
			mover();
			try {

				Thread.sleep(1000/25);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
