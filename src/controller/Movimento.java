package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.Lampiao;
import view.Fase1;

public class Movimento extends KeyAdapter implements Runnable{

	private Lampiao lampiao;
//	private int acao;
	private Fase1 fase;
	
	public Movimento(Lampiao lampiao,Fase1 fase) {
		this.lampiao = lampiao;
		this.fase = fase;
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void keyPressed(KeyEvent e) {
//		acao = e.getKeyCode();
		lampiao.setAcao(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
//		acao = 0;
		lampiao.parar();
	}

	@Override
	public void run() {
		while(true) {
			if(KeyEvent.VK_SPACE == lampiao.getAcao()) {
				lampiao.pular();
			}
			else if(lampiao.getAcao() != KeyEvent.VK_SPACE)
				lampiao.andar();
			if(!fase.isColidindo(lampiao) && lampiao.getY()<817) {
				lampiao.cair();

			}else {
				lampiao.setY(817);

			}
			try {

				Thread.sleep(1000/40);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
        

	}

}
