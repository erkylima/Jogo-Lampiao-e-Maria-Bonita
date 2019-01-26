package model;

import java.awt.Graphics;

import controller.TratamentoException;

public class Status extends Sprite implements Runnable{

	private int controlaVelocidade = 5;
	private int velocidade = 5;
	public Status(int aparencia, int colunas, int linhas, int x, int y, String endereco, double vida,Lampiao lampiao)
			throws TratamentoException {
		super(aparencia, colunas, linhas, x, y, endereco, vida);
		setVida(vida);
		
		Thread status = new Thread(this);
		status.start();
	}

	@Override
	public void animacaoAndandoDireita() {
		
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
	public void mover() {
		//TODO mover status?
	}

	@Override
	public void run() {
		while(true) {
			if(getVida()<=120 && getVida()>100) {
				setAparencia(0);
			}else if(getVida()<=100 && getVida()>90) {
				setAparencia(1);
			}else if(getVida()<=90 && getVida()>80) {
				setAparencia(2);
			}else if(getVida()<=80 && getVida()>70) {
				setAparencia(3);
			}else if(getVida()<=70 && getVida()>60) {
				setAparencia(4);
			}else if(getVida()<=60 && getVida()>50) {
				setAparencia(5);
			}else if(getVida()<=50 && getVida()>40) {
				setAparencia(6);
			}else if(getVida()<=40 && getVida()>30) {
				setAparencia(7);
			}else if(getVida()<=30 && getVida()>20) {
				setAparencia(8);
			}else if(getVida()<=20 && getVida()>10) {
				setAparencia(9);
			}else if(getVida()<=10 && getVida()>0) {
				setAparencia(12);
				animacaoAndandoEsquerda();
			}else if(getVida()<=0) {
				setAparencia(13);
			}	
			
			try {

				Thread.sleep(1000/10);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
	}

}
