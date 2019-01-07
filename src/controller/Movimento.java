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
		while(true) {
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
			if(KeyEvent.VK_SPACE == lampiao.getAcao() && lampiao.isVivo()) {
				lampiao.pular();
				
			}
			else if(lampiao.getAcao() != KeyEvent.VK_SPACE && lampiao.isVivo()) {
				lampiao.andar();

			}
			if(!lampiao.isVivo()) {
				if(!lampiao.getFase().getInit().retornarInicio("Você morreu!", "Deseja reiniciar o jogo?")) {
					destroier(this);
				}
			}
			if(lampiao.getAcao() == KeyEvent.VK_ESCAPE) {
				lampiao.getFase().getInit().retornarMenu("Voltar","Deseja voltar ao menu?");
			
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
		
		this.t.stop();
		t.interrupt();;
		this.t = null;
		mov = null;
		System.gc();
	}

	
}
