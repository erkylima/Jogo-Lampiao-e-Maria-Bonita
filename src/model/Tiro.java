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
	private boolean aux, aux2;
	private Graphics graphic;
	private int velocidade;
	private boolean running;
	public Tiro(int aparencia, int colunas, int linhas, int x, int y, String endereco,Lampiao personagem,ArrayList<Sprite> alvo, int vida) throws IOException {
		super(aparencia, colunas, linhas, x, y, endereco, vida);
		this.alvo = alvo;
		this.personagem = personagem;
		this.velocidade = velocidade;
		aux = personagem.isDireita();
		if(personagem==alvo.get(0)) {
			aux2=alvo.get(1).isDireita();
		}
		Som.tiroSom();
		running = true;

		if(aux) {
			setX(getX()+50);
		}
		else {
			setX(getX()-50);
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
		if(personagem!=alvo.get(0)) { //lampiao contra os inimigos
			if(aux) {
				setX(getX()+20);
			}else {
				setX(getX()-20);
			}
			for(Sprite a : alvo) {
				if(getBounds().intersects(a.getBounds())) {
					a.setVida(a.getVida()-getVida());
					setY(790);
					setVida(0);
				}else if(isColidindo()) {
					setVida(0);
					setY(790);

				}else if(personagem.getBounds().intersects(a.getBounds())) {
					a.setVida(a.getVida()-getVida());
					setY(790);
					setVida(0);
				}
				else if(personagem.getX() > getX()+900 ||personagem.getX() < getX()-900){
					destroier(this);
				}else {
					draw(graphic);
				}
			}
		}else {
			if(!aux2) {//alvo.get(0) = Lampiao alvo.get(1) = inimigo que disparou o tiro
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
				
			}else if(alvo.get(0).getBounds().intersects(alvo.get(1).getBounds())) {
				alvo.get(0).setVida(alvo.get(0).getVida()-getVida());
				setY(790);
				setVida(0);
			}else if(alvo.get(1).getX() > getX()+900 ||alvo.get(1).getX() < getX()-900){
				destroier(this);
			}else {
				draw(graphic);
			}
			
		}
		
	}

	@Override
	public void run() {
		while(running) {
			mover();
			
			if(getVida()<=0) {
				destroier(this);
			}
			
			try {
				if(!tiro.isInterrupted()) {
					tiro.sleep(1000/(personagem.getFase().getFPS()-20));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	@SuppressWarnings("deprecation")
	public void destroier(Tiro tiro){
		tiro = null;
		running = false;
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
