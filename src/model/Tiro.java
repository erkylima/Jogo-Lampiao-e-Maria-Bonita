package model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;

import controller.Som;

public class Tiro extends Sprite implements Runnable{
	private ArrayList<Sprite> alvo;
	private Lampiao personagem;
	private Thread tiro;
	private boolean aux;
	private Graphics graphic;
	private int velocidade;
	private Som som;
	public Tiro(int aparencia, int colunas, int linhas, int x, int y, String endereco,Lampiao personagem,ArrayList<Sprite> alvo, int vida,int velocidade) throws IOException {
		super(aparencia, colunas, linhas, x, y, endereco, vida);
		this.alvo = alvo;
		this.personagem = personagem;
		this.velocidade = velocidade;
		aux = personagem.isDireita();
		som = new Som();
		som.tiroSom();
		if(aux) {
			setX(getX()+40);
		}
		else {
			setX(getX()-40);
		}
		
		tiro = new Thread(this);
		tiro.start();
	}

	@Override
	public void animacaoAndandoDireita() {
		// TODO Auto-generated method stub

	}

	@Override
	public void animacaoAndandoEsquerda() {
		// TODO Auto-generated method stub

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
		graphic = g;
		g.drawImage(getSprites()[getAparencia()], getX(), getY(), null);
	}

	@Override
	public void mover() {
		if(personagem!=alvo.get(0)) {
			if(aux) {
				setX(getX()+10);
			}else {
				setX(getX()-10);
			}
			for(Sprite a : alvo) {
				if(getBounds().intersects(a.getBounds())) {
					a.setVida(a.getVida()-getVida());
					setY(790);
					setVida(0);
				}else if(isColidindo()) {
					setVida(0);
					setY(790);

				}else {
					draw(graphic);
				}
			}
		}else {
			if(alvo.get(0).getX() < alvo.get(1).getX()) {
				setX(getX()-10);
			}else {
				setX(getX()+10);
			}
			if(getBounds().intersects(alvo.get(0).getBounds())) {
				alvo.get(0).setVida(alvo.get(0).getVida()-getVida());
				setY(790);
				setVida(0);
			}else if(isColidindo()) {
				setVida(0);
				setY(790);

			}else {
				draw(graphic);
			}
			
		}
		
	}

	@Override
	public void run() {
		while(true) {
			mover();
			
			if(getVida()<=0) {
				destroier(this);
				
			}
			try {
				if(!tiro.isInterrupted()) {
					Thread.sleep(1000/velocidade);}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void destroier(Tiro tiro){
		som = null;
		tiro = null;
		this.tiro.stop();
		this.tiro.interrupt();
		this.tiro = null;
		System.gc();
	}

	public boolean isColidindo() {
		for(Rectangle entidade : personagem.getFase().getInit().getTileSerra().montarColisao())
			if(getBounds().intersects(entidade)) {
				return true;				
			}		
		return false;		
	}
}
