package model;

import java.awt.Graphics;
import java.io.IOException;

public class Status extends Sprite{

	private Lampiao lampiao;
	private int controlaVelocidade = 5;
	private int velocidade = 5;
	public Status(int aparencia, int colunas, int linhas, int x, int y, String endereco, int vida,Lampiao lampiao)
			throws IOException {
		super(aparencia, colunas, linhas, x, y, endereco, vida);
		this.lampiao = lampiao;
		setVida(lampiao.getVida());
	}

	@Override
	public void animacaoAndandoDireita() {
		if(lampiao.getVida()<=110 && lampiao.getVida()>100) {
			setAparencia(1);
		}else if(lampiao.getVida()<=100 && lampiao.getVida()>90) {
			setAparencia(2);
		}else if(lampiao.getVida()<=90 && lampiao.getVida()>80) {
			setAparencia(3);
		}else if(lampiao.getVida()<=80 && lampiao.getVida()>70) {
			setAparencia(4);
		}else if(lampiao.getVida()<=70 && lampiao.getVida()>60) {
			setAparencia(5);
		}else if(lampiao.getVida()<=60 && lampiao.getVida()>50) {
			setAparencia(6);
		}else if(lampiao.getVida()<=50 && lampiao.getVida()>40) {
			setAparencia(7);
		}else if(lampiao.getVida()<=40 && lampiao.getVida()>30) {
			setAparencia(8);
		}else if(lampiao.getVida()<=30 && lampiao.getVida()>20) {
			setAparencia(9);
		}else if(lampiao.getVida()<=20 && lampiao.getVida()>10) {
			setAparencia(10);
		}else if(lampiao.getVida()<=10 && lampiao.getVida()>0) {
			setAparencia(11);
			animacaoAndandoEsquerda();
		}else if(lampiao.getVida()<=0) {
			setAparencia(13);
		}
	}

	@Override
	public void animacaoAndandoEsquerda() {
		if(controlaVelocidade>velocidade && (getAparencia() >=11 && getAparencia() <=12)){
			setAparencia(getAparencia()+1);
			controlaVelocidade = 1;
			if(getAparencia() == 12){ setAparencia(11); }
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
	public void draw(Graphics g) {
		g.drawImage(getSprites()[getAparencia()], getX(), getY(), null);
		
	}

	@Override
	public int mover() {
		return 0;
	}

}
