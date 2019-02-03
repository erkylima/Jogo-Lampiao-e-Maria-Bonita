package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import model.Lampiao;
import view.Tela;

public class Movimento extends KeyAdapter implements Runnable{

	private Lampiao lampiao;
	private Tela fase;
	private Thread t;
	private boolean threadOn = true;
	private boolean voltarMenu = false;
	
	public Movimento(Lampiao lampiao,Tela fase) {
		this.lampiao = lampiao;
		this.fase = fase;
		fase.addKeyListener(this);
		t = new Thread(this);
		t.start();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(lampiao.getTeclas().contains(e.getKeyCode()) || 
				e.getKeyCode() == KeyEvent.VK_T ||
				e.getKeyCode() == KeyEvent.VK_ESCAPE ||
				e.getKeyCode() == KeyEvent.VK_ENTER) {
			lampiao.setAcao(e.getKeyCode());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(!lampiao.getFase().isRequestFocusEnabled() || lampiao.getFase().getInit().getJogo().getMostRecentFocusOwner() != lampiao.getFase()) {
			lampiao.getFase().requestFocus();
		}
		switch(e.getKeyCode()) {
		case KeyEvent.VK_X:
			lampiao.getFase().getInit().getInventario().requestFocus();
			System.out.println(lampiao.getX() + " X Y " +lampiao.getY());
			break;

			
		}
		if(lampiao.getTeclas().contains(e.getKeyCode())) {
			lampiao.setAcao(-1);
		}
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

			if(lampiao.isVivo() && !voltarMenu) {
				lampiao.andar();
			}

			if(lampiao.getAcao() == KeyEvent.VK_ESCAPE && lampiao.getMenu()==0 && !voltarMenu) {
				lampiao.getFase().getInit().getLampiao().setMenu(1);
				voltarMenu = true;
			} else if(lampiao.getAcao() == KeyEvent.VK_T && lampiao.getMenu()==1) {
				lampiao.setMenu(0);
				voltarMenu = false;
			}
			if(lampiao.getMenu() != 0  && lampiao.getAcao() == KeyEvent.VK_ENTER) {
				lampiao.getFase().getInit().retornarMenu();
				voltarMenu = false;
			}
			if(lampiao.getY()>=640 && lampiao.getVida()>0) {
				lampiao.setVida(lampiao.getVida()-10);
			}
			
			try {
				if(!t.isInterrupted()) {
					Thread.sleep(1000/(lampiao.getFase().getFPS()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
        

	}
	public void destroier(Movimento mov){
		threadOn =false;
		mov = null;
		System.gc();
	}

	
}
