package model;

import java.awt.Graphics;

import com.sun.glass.events.KeyEvent;

import controller.TratamentoException;
import view.Tela;

public class Lampiao extends Sprite{
	public double controlaVelocidade = 0;
	public int velocidade = 10;
	private int menu;
	private Tela fase;
	private boolean andarMaria=false;
	private double fome;
	private Status saude;
	public Lampiao(int aparencia,int columns, int rows, int posX, int posY,String caminho,Tela fase,double vida,double fome) throws TratamentoException {
		super(aparencia, columns, rows, posX, posY, caminho,vida);
		this.fase = fase;
		this.fome = fome;
		saude = new Status(0, 14, 1, getX(), getY()-20, "Arquivos/Imagens/saude.png", getVida(), this);
		andarMaria = true;
	}
	

	
	public void animacaoAndandoDireita(){
		controlaVelocidade+=5;
		if(controlaVelocidade>velocidade && (getAparencia() >=0 && getAparencia() <=13)){
			setAparencia(getAparencia()+1);
			controlaVelocidade = 0;
			if(getAparencia() == 13){ setAparencia(0); }
		}
	}
	
	public void animacaoAndandoEsquerda(){
		controlaVelocidade+=5;
		if(controlaVelocidade>velocidade && (getAparencia() >= 23 && getAparencia() <= 36)){
			setAparencia(getAparencia()+1);
			controlaVelocidade = 0;
			if(getAparencia() == 36){ setAparencia(23); 
			}
		}
	}
	
	public void animacaoParadoDireita(){
		controlaVelocidade+=0.5;
		if(controlaVelocidade>velocidade && (getAparencia() >=14 && getAparencia() <= 22)){
			setAparencia(getAparencia()+1);
			controlaVelocidade = 0;
			if(getAparencia() == 22){ setAparencia(14);  }
		}
	}
	

	
	public void animacaoParadoEsquerda(){
		controlaVelocidade+=0.5;
		if(controlaVelocidade>velocidade && (getAparencia() >=37 && getAparencia() <=45)){
			setAparencia(getAparencia()+1);
			controlaVelocidade = 0;
			if(getAparencia() == 45){ setAparencia(37);		}
		}
	}


	@Override
	public void draw(Graphics g) {
		g.drawImage(getSprites()[getAparencia()], getX(), getY(), null);
		if(getVida()>0 && getMenu()!=0) {
			g.drawImage(getFase().getInit().getVoltarInicio(getMenu()).getImage(), getX()-(getLarguraPersonagem()), getFase().getInit().getALTURA()/3, null);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		saude.setX(getX());
		saude.setY(getY()-20);
		saude.setVida(getVida());
		saude.draw(g);
	}



	@Override
	public void mover() {
		andar();
	}
	
	public void andar(){
		switch(getAcao()) {
		case KeyEvent.VK_D:{
			setDireita(true);
			setX(getX()+4);
			if(getAparencia()>=14) {
				setAparencia(0);
			}
			animacaoAndandoDireita();
			
			if(getFome()>0)
				setFome(getFome()-0.08);
			if(getFome()<=0) {
				setVida(getVida()-getFase().getInit().getConfig().getNivel()/10);
			} else if(getFome()>60 && getVida() <= getFase().getInit().getVidaInicial()-getFase().getInit().getConfig().getNivel())
				setVida(getVida()+getFase().getInit().getConfig().getNivel()/10);
			break;
		}
		case KeyEvent.VK_A:{
			
			if(getFome()>0)
				setFome(getFome()-0.05);
			if(getFome()<10) {
				setVida(getVida()-(getFase().getInit().getConfig().getNivel()/10));
			} else if(getFome()>60 && getVida() <= getFase().getInit().getVidaInicial()-getFase().getInit().getConfig().getNivel())
				setVida(getVida()+getFase().getInit().getConfig().getNivel()/10);
			
			setDireita(false);;
			setX(getX()-4);
			if(getAparencia()<23 || getAparencia()>=37) {
				setAparencia(24);
			}
			animacaoAndandoEsquerda();		
			break;
		}
		case KeyEvent.VK_T:{
			if(isDireita()) {
				setAparencia(46);
				try {
						new Tiro(0, 2, 1, getX(), getY()+40, "Arquivos/Imagens/tiro.png", this,fase.getCamera().getInimigos(), 10).draw(fase.getCamera().getGraphics());;
					
					Thread.sleep(1000/2);	

				} catch (TratamentoException e) {
					e.printStackTrace();
				}catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				setAparencia(47);
				try {
						new Tiro(1, 2, 1, getX(), getY()+40, "Arquivos/Imagens/tiro.png", this,fase.getCamera().getInimigos(), 10).draw(fase.getCamera().getGraphics());	
					
					Thread.sleep(1000/3);				
				} catch (TratamentoException e) {
					e.printStackTrace();
				}catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
	
			break;
		}
		case 0:
			if(isDireita()) {
				if(getAparencia() >=0 && getAparencia() <=14 || getAparencia() == 46) {
					setAparencia(15);
				}
				animacaoParadoDireita();
			}else {
				if(getAparencia()>=23&&getAparencia()<37||getAparencia() == 47) {
					setAparencia(37);
				} 
				animacaoParadoEsquerda();
			}
			break;
			
		
	}
		
	}
	
	
	public void pular(){
		int anguloDoPulo = 25;
		int anguloCorrente = anguloDoPulo;
		boolean aux = isDireita();
		double dy,dx;
		if(aux) {
			if(getAparencia()>=14) {
				setAparencia(0);
			}
			animacaoAndandoDireita();
		}else {
			if(getAparencia()<23 || getAparencia()>=37) {
				setAparencia(24);
			}
			animacaoAndandoEsquerda();
		}
		setY(getY()-velocidade);
		while(anguloCorrente != 300) {
			if(anguloCorrente == 0)
				anguloCorrente = 360;
			dy = velocidade * Math.sin(Math.toRadians(anguloCorrente))+1;
			if(aux)
				dx =((velocidade * Math.cos(Math.toRadians(anguloCorrente))) * 1)-5;
			else
				dx =((velocidade * Math.cos(Math.toRadians(anguloCorrente))) * -1)+5;
			
			anguloCorrente--;

			 if(!fase.isColidindo(this)) {
				setY(getY()-((int)dy));
				setX(getX()+((int)dx));
			}
			else {
				setY(getY()+((int)dy));
				setX(getX()-((int)dx));
				break;
			}
			try {
				Thread.sleep(1000/(getFase().getFPS()-10));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		if(fase.isButtom(this)) {
			setY(getY()-getY()%velocidade);
			cair();
		}
		

	}
	
	public void cair(){
		if(!fase.isTopo(this)) {
			setY(getY()+8);
		}
	}
	
	public void parar() {
		setAcao(0);
	}

	public boolean isVivo() {
		if(getVida()>0) {
			return true;
		}
		return false;
	}
	
	public Tela getFase() {
		return fase;
	}



	public void setFase(Tela fase) {
		this.fase = fase;
	}

	public double getFome() {
		return fome;
	}



	public void setFome(double fome) {
		this.fome = fome;
	}



	public int getMenu() {
		return menu;
	}



	public void setMenu(int menu) {
		this.menu = menu;
	}
	
	public boolean isAndarMaria() {
		return andarMaria;
	}

	public boolean mariaAndaToggle() {
		if(andarMaria) {
			andarMaria = false;
			
		}else {
			andarMaria = true;
		}
		System.out.println(andarMaria);
		return andarMaria;
	}




	
}
