package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.Lampiao;

public class Movimento extends KeyAdapter implements Runnable{

	private Lampiao lampiao;
	private int acao;
	
	public Movimento(Lampiao lampiao) {
		this.lampiao = lampiao;
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		acao = e.getKeyCode();
//		lampiao.setAcao(e.getKeyCode());
//		lampiao.mover();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		acao = 0;
		lampiao.parar();
	}

	@Override
	public void run() {
		while(true) {
			if(KeyEvent.VK_SPACE == acao) {
				lampiao.pular();
				lampiao.setAcao(acao);
			}
			else if(acao != 0)
				lampiao.setAcao(acao);
				lampiao.andar();
			lampiao.cair();
			try {
				Thread.sleep(1000/10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
        

	}

}
