package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import model.Lampiao;
import model.Tiro;
import view.Tela;

public class MovimentoMaria extends KeyAdapter implements Runnable {


	private Lampiao maria;
	private Tela fase;
	private Thread t;
	private boolean threadOn = true;
	private boolean voltarMenu = false;

	public MovimentoMaria(Lampiao maria,Tela fase) {
		this.maria = maria;
		this.fase = fase;
		t = new Thread(this);
		t.start();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//		acao = e.getKeyCode();
		switch(e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:{
			maria.setDireita(true);
			maria.setX(maria.getX()+4);

			if(maria.getAparencia()>=14) {
				maria.setAparencia(0);
			}

			maria.animacaoAndandoDireita();

			break;
		}
		case KeyEvent.VK_LEFT:{


			maria.setDireita(false);;
			maria.setX(maria.getX()-4);

			if(maria.getAparencia()<23 || maria.getAparencia()>=37) {
				maria.setAparencia(24);
			}

			maria.animacaoAndandoEsquerda();		
			break;
		}
		case KeyEvent.VK_P:{

			if(maria.isDireita()) {

				maria.setAparencia(46);
				try {
					new Tiro(0, 2, 1, maria.getX()+70, maria.getY()+40, "Arquivos/Imagens/tiro.png", maria,fase.getCamera().getInimigos(), 10).draw(fase.getCamera().getGraphics());;

					Thread.sleep(1000/2);	

				} catch (TratamentoException e1) {
					e1.printStackTrace();
				}catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else {
				maria.setAparencia(47);
				try {
					new Tiro(1, 2, 1, maria.getX(), maria.getY()+40, "Arquivos/Imagens/tiro.png", maria,maria.getFase().getCamera().getInimigos(), 10).draw(fase.getCamera().getGraphics());	

					Thread.sleep(1000/3);				
				} catch (TratamentoException e1) {
					e1.printStackTrace();
				}catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}


			break;
		}
		case 0:{
			if(maria.isDireita()) {

				if(maria.getAparencia() >=0 && maria.getAparencia() <=14 || maria.getAparencia() == 46) {
					maria.setAparencia(15);
				}

				maria.animacaoParadoDireita();
			}else {

				if(maria.getAparencia() >= 23 &&maria.getAparencia() < 37 || maria.getAparencia() == 47) {
					maria.setAparencia(37);
				} 

				maria.animacaoParadoEsquerda();
			}
			break;

		}
		}

	}

	@SuppressWarnings("static-access")
	@Override
	public void keyReleased(KeyEvent e) {
		//		acao = 0;
		switch(e.getKeyCode()) {
		case KeyEvent.VK_UP:
			maria.pular();
		default:
			maria.parar();
			break;
		}
	}

	@Override
	public void run() {
		while(threadOn) {

			if(fase.isTopo(maria))
				maria.setY(maria.getY()-8);

			if(fase.isCantoDireito(maria))
				maria.setX(maria.getX()-5);

			if(fase.isCantoEsquerdo(maria)) {
				maria.setX(maria.getX()+10);
			}
			if(!fase.isColidindo(maria)) {
				maria.cair();
			}

			try {
				if(!t.isInterrupted()) {
					Thread.sleep(1000/70);
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
