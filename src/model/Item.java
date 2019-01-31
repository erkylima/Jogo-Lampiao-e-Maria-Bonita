package model;

import java.awt.Graphics;

import com.sun.glass.events.KeyEvent;

import controller.Inicializa;
import controller.TratamentoException;

public class Item extends Sprite implements Runnable{

	
	
	private Thread t;
	private boolean running = true;
	private Inicializa init;
	private boolean rapadura;
	
	public Item(int aparencia, int colunas, int linhas, int x, int y, String endereco, double vida, Inicializa init,boolean rapadura)
			throws TratamentoException {
		super(aparencia, colunas, linhas, x, y, endereco, vida);
		this.rapadura = rapadura;
		this.init = init;
		t = new Thread(this);
		t.start();
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
		g.drawImage(getSprites()[getAparencia()], getX(), getY(), null);
	}

	@Override
	public void mover() {
		cair();
		if(init.getLampiao().getBounds().intersects(getBounds()) && init.getLampiao().getAcao() == KeyEvent.VK_E) {
			if(rapadura) {
				init.getLampiao().setFome(init.getLampiao().getFome()+getVida());
				setVida(0);
			}else {
				init.getLampiao().setSede(init.getLampiao().getSede()+getVida());
				setVida(0);
			}
		}
	}

	private void cair(){
		if(!init.getLampiao().getFase().isTopo(this)) {
			setY(getY()+8);
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
				Thread.sleep(60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
	
	public void destroier(Item item) {
		setAparencia(6);
		running = false;
		item = null;
		System.gc();
	}

}
