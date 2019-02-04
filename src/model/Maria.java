package model;

import java.awt.Graphics;

import com.sun.glass.events.KeyEvent;

import controller.Inicializa;
import controller.TratamentoException;

public class Maria extends Sprite implements Runnable{
	public double controlaVelocidade = 1;
	public int velocidade = 10;
	private Thread mariaThread;
	private boolean threadOn = true;
	private Lampiao lampiao;
	private Inicializa init;
	private boolean libertou=false;

	public Maria(int aparencia, int colunas, int linhas, int x, int y, String endereco,Lampiao lampiao,Inicializa init, double vida)
			throws TratamentoException {
		super(aparencia, colunas, linhas, x, y, endereco, vida);
		this.init = init;
		this.lampiao = lampiao;
		setDireita(true);
		mariaThread = new Thread(this);
		mariaThread.start();
	}


	@Override
	public void animacaoAndandoDireita() {
		controlaVelocidade+=2;
		if(controlaVelocidade>velocidade && (getAparencia() >=20 && getAparencia() <= 28)){
			setAparencia(getAparencia()+1);
			controlaVelocidade = 5;
			if(getAparencia() == 28){ setAparencia(20);
			}
		}
	}

	@Override
	public void animacaoAndandoEsquerda() {
		controlaVelocidade+=2;
		if(controlaVelocidade>velocidade && (getAparencia() >=10 && getAparencia() <= 19)){
			setAparencia(getAparencia()+1);
			controlaVelocidade = 5;
			if(getAparencia() == 19){ setAparencia(10);
			}
		}
	}

	@Override
	public void animacaoParadoDireita() {

	}

	@Override
	public void animacaoParadoEsquerda() {
		controlaVelocidade+=2;
		if(controlaVelocidade>velocidade && (getAparencia() <= 8)){
			setAparencia(getAparencia()+1);
			controlaVelocidade = 5;
		}

	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(getSprites()[getAparencia()], getX(), getY(), null);

	}

	@Override
	public void mover() {
		if(getAparencia()<20 ) {
			setAparencia(20);
		}
		animacaoAndandoDireita();
		setX(getX()+9);

		if(lampiao.getFase().isTopo(this)) {
			setY(getY()-8);
			if(lampiao.getFase().isTopo(this)) {
				pular();
			}
		}else {
			setY(getY()+8);
		}
	}
	public void pular(){
		int anguloDoPulo = 25;
		int anguloCorrente = anguloDoPulo;
		boolean aux = isDireita();
		double dy,dx;
		if(aux) {
			if(getAparencia()<20 ) {
				setAparencia(20);
			}
			animacaoAndandoDireita();
		}else {
			if(getAparencia()<12 || getAparencia()>17) {
				setAparencia(12);
			}
			animacaoAndandoEsquerda();
		}
		setY(getY()-velocidade);
		while(anguloCorrente != 300) {
			if(anguloCorrente == 0)
				anguloCorrente = 360;
			dy = velocidade * Math.sin(Math.toRadians(anguloCorrente))+1;
			if(aux)
				dx =((velocidade * Math.cos(Math.toRadians(anguloCorrente))) * 1)-4;
			else
				dx =((velocidade * Math.cos(Math.toRadians(anguloCorrente))) * -1)+4;

			anguloCorrente--;

			if(!lampiao.getFase().isColidindo(this)) {
				setY(getY()-((int)dy));
				setX(getX()+((int)dx));
			}else {
				setY(getY()+((int)dy));
				setX(getX()-((int)dx));
				break;
			}
			try {
				Thread.sleep(1000/(lampiao.getFase().getFPS()-30));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

		setY(getY()-getY()%velocidade);

	}
	
	public void cair(){
		if(!lampiao.getFase().isTopo(this)) {
			setY(getY()+8);
		}
	}
	@Override
	public void run() {
		while(threadOn ) {
			if(init.getLampiao().getBounds().intersects(getBounds()) && init.getLampiao().getAcao() == KeyEvent.VK_E || libertou == true) {
				libertou = true;
				if(getAparencia()<20 ) {
					setY(getY()+10);
					setAparencia(20);
				}
				animacaoAndandoDireita();
			}
			if(lampiao.getFase()!=null && lampiao.getFase().getInit().getCamadasF1().size()>1 && !libertou) {
				if(getAparencia() <10 || getAparencia()>19)
					setAparencia(10);
				animacaoAndandoEsquerda();
				
				if(getVida()<10) {
					destroier(this);
				}
				if(getY()>640) {
					setVida(getVida()-10);
				}
			}
			if(lampiao.getFase()!=null && !libertou) {
				try {
					cair();

				} catch (Exception e) {
					System.out.println("Sem fase definida");
				}
			}
			try {
				if(!mariaThread.isInterrupted()) 
					Thread.sleep(1000/(init.getConfig().getFPS()-5));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
	}

	public boolean isLibertou() {
		return libertou;
	}


	public void setThreadOn(boolean threadOn) {
		this.threadOn = threadOn;
	}


	public void destroier(Volante volante){
		volante.setY(getY()+500);
		threadOn  = false;
		volante = null;

		System.gc();
	}
}
