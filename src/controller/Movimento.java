package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import model.Lampiao;
import view.IniciarJogo;
import view.Tela;

public class Movimento extends KeyAdapter implements Runnable{

	private Lampiao lampiao;
	private Tela fase;
	private Thread t;
	private boolean threadOn = true;
	
	public Movimento(Lampiao lampiao,Tela fase) {
		this.lampiao = lampiao;
		this.fase = fase;
		t = new Thread(this);
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
		while(threadOn) {
			if(fase.isTopo(lampiao))
				lampiao.setY(lampiao.getY()-8);
			
			if(fase.isCantoDireito(lampiao))
				lampiao.setX(lampiao.getX()-5);
			
			if(fase.isCantoEsquerdo(lampiao)) {
				lampiao.setX(lampiao.getX()+10);
			}
			if(!fase.isColidindo(lampiao)) {
				lampiao.cair();
			}
			if(KeyEvent.VK_SPACE == lampiao.getAcao() && lampiao.isVivo() && lampiao.getFase().isTopo(lampiao)) {
				lampiao.pular();
				
			}
			else if(lampiao.getAcao() != KeyEvent.VK_SPACE && lampiao.isVivo()) {
				lampiao.andar();

			}

			if(lampiao.getAcao() == KeyEvent.VK_ESCAPE && lampiao.getMenu()==0) {
				lampiao.getFase().getInit().getLampiao().setMenu(1);
			} else if(lampiao.getAcao() == KeyEvent.VK_ESCAPE && lampiao.getMenu()==1) {
				lampiao.setMenu(0);

			}
			if(lampiao.getMenu() != 0  && lampiao.getAcao() == KeyEvent.VK_ENTER) {
				lampiao.getFase().getInit().retornarMenu();
			}
			if(lampiao.getY()>=640 && lampiao.getVida()>0) {
				lampiao.setVida(lampiao.getVida()-10);
			}
			
			try {
				if(!t.isInterrupted()) {
					Thread.sleep(1000/(lampiao.getFase().getFPS()-10));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
        

	}
	@SuppressWarnings("deprecation")
	public void destroier(Movimento mov){
		threadOn =false;
		mov = null;
		System.gc();
	}

	
}
