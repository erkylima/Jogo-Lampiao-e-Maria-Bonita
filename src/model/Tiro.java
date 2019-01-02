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
	public Tiro(int aparencia, int colunas, int linhas, int x, int y, String endereco,Lampiao personagem,ArrayList<Sprite> alvo, int vida) throws IOException {
		super(aparencia, colunas, linhas, x, y, endereco, vida);
		this.alvo = alvo;
		this.personagem = personagem;
		aux = personagem.isDireita();
		new Som().tiroSom();
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

	}

	@Override
	public void run() {
		while(true) {
			if(aux && personagem!=alvo.get(0)) {
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
			if(getVida()<=0) {
				destroier(this);
				
			}
			try {

				Thread.sleep(1000/60);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void destroier(Tiro tiro){
		tiro = null;
		this.tiro.stop();
		System.gc();
	}

	public boolean isColidindo() {
		for(Rectangle entidade : personagem.getFase().getTile().montarColisao())
			if(getBounds().intersects(entidade)) {
				return true;				
			}		
		return false;		
	}
}
