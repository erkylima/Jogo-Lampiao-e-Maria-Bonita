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
	private double fome,sede;
	private Status saude;
	private double chance;
	private boolean pistola,multiplayer;
	private int arma=48; //distância entre a aparencia pistola 38 e aparencia espingarda
	private int dano;

	public Lampiao(int aparencia,int columns, int rows, int posX, int posY,String caminho,Tela fase,double vida,double fome,double sede,double chance) throws TratamentoException {
		super(aparencia, columns, rows, posX, posY, caminho,vida);
		this.chance = chance;
		this.fase = fase;
		this.sede = sede;
		this.fome = fome;
		pistola = false;
		saude = new Status(0, 14, 1, getX(), getY()-20, "Arquivos/Imagens/saude.png", getVida(), this);
	}



	public void animacaoAndandoDireita(){
		controlaVelocidade+=5;
		if(pistola) {
			if(controlaVelocidade>velocidade && (getAparencia() >=arma+0 && getAparencia() <=arma+13)){
				setAparencia(getAparencia()+1);
				controlaVelocidade = 0;				
				if(getAparencia() == arma+13){
					setAparencia(arma+0);
				}
			}
		}
		else {
			if(controlaVelocidade>velocidade && (getAparencia() >=0 && getAparencia() <=13)){
				setAparencia(getAparencia()+1);
				controlaVelocidade = 0;
				if(getAparencia() == 13){
					setAparencia(0);
				}
			}
		}
	}

	public void animacaoAndandoEsquerda(){
		controlaVelocidade+=5;
		if(pistola) {
			if(controlaVelocidade>velocidade && (getAparencia() >= arma+23 && getAparencia() <= arma+36)){
				setAparencia(getAparencia()+1);
				controlaVelocidade = 0;
				if(getAparencia() == arma+36){ setAparencia(arma+23); 
				}
			}
		}else {
			if(controlaVelocidade>velocidade && (getAparencia() >= 23 && getAparencia() <= 36)){
				setAparencia(getAparencia()+1);
				controlaVelocidade = 0;
				if(getAparencia() == 36){ setAparencia(23); 
				}
			}
		}
	}

	public void animacaoParadoDireita(){
		controlaVelocidade+=0.5;
		if(pistola) {
			if(controlaVelocidade>velocidade && (getAparencia() >=arma+14 && getAparencia() <= arma+22)){
				setAparencia(getAparencia()+1);
				controlaVelocidade = 0;
				if(getAparencia() == arma+22){ setAparencia(arma+14);  }
			}
		}else {
			if(controlaVelocidade>velocidade && (getAparencia() >=14 && getAparencia() <= 22)){
				setAparencia(getAparencia()+1);
				controlaVelocidade = 0;
				if(getAparencia() == 22){ setAparencia(14);  }
			}
		}
	}



	public void animacaoParadoEsquerda(){
		controlaVelocidade+=0.5;
		if(pistola) {
			if(controlaVelocidade>velocidade && (getAparencia() >=arma+37 && getAparencia() <=arma+45)){
				setAparencia(getAparencia()+1);
				controlaVelocidade = 0;
				if(getAparencia() == arma+45){ setAparencia(arma+37);		}
			}
		}else {
			if(controlaVelocidade>velocidade && (getAparencia() >=37 && getAparencia() <=45)){
				setAparencia(getAparencia()+1);
				controlaVelocidade = 0;
				if(getAparencia() == 45){ setAparencia(37);		}
			}
		}
	}


	@Override
	public void draw(Graphics g) {
		g.drawImage(getSprites()[getAparencia()], getX(), getY(), null);
		saude.setX(getX()+10);
		saude.setY(getY()-20);
		saude.setVida(getVida());
		saude.draw(g);
		if(getVida()>0 && getMenu()!=0 && getChance()>10) {
			g.drawImage(getFase().getInit().getVoltarInicio(getMenu()).getImage(), getX()-(getLarguraPersonagem()), getFase().getInit().getALTURA()/3, null);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

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
			if(pistola) {
				if(getAparencia()<=arma ||getAparencia()>=arma+14) {
					setAparencia(arma);
				}
			}else {
				if(getAparencia()>=14) {
					setAparencia(0);
				}
			}
			animacaoAndandoDireita();

			if(getFome()>0)
				setFome(getFome()-0.08);
			if(getSede()>0)
				setSede(getSede()-0.09);
			
			if(getFome()<10 || getSede() <15) {
				setVida(getVida()-(getFase().getInit().getConfig().getNivel()+1)/15);
			} else if(getFome()>60 && getVida() <= getFase().getInit().getVidaInicial()-getFase().getInit().getConfig().getNivel())
				if(getFase().getInit().getConfig().getNivel() <10) {
					setVida(getVida()+(getFase().getInit().getConfig().getNivel()+1)/14);

				}else if(getFase().getInit().getConfig().getNivel() > 10) {
					setVida(getVida()+(getFase().getInit().getConfig().getNivel()+1)/300);
				}
			break;
		}
		case KeyEvent.VK_A:{

			if(getFome()>0)
				setFome(getFome()-0.03);
			if(getSede()>0)
				setSede(getSede()-0.04);

			if(getFome()<10 || getSede() <15) {
				setVida(getVida()-((getFase().getInit().getConfig().getNivel()+1)/15));
			} else if((getFome()>60 && getSede() > 65) && getVida() <= (getFase().getInit().getVidaInicial()-getFase().getInit().getConfig().getNivel())) {
				if(getFase().getInit().getConfig().getNivel() <10) {
					setVida(getVida()+(getFase().getInit().getConfig().getNivel()+1)/5);

				}else if(getFase().getInit().getConfig().getNivel() > 10) {

					setVida(getVida()+(getFase().getInit().getConfig().getNivel())/150);

				}
			}
			setDireita(false);;
			setX(getX()-4);
			if(pistola) {
				if(getAparencia()< arma + 23 || getAparencia()>= arma + 37) {
					setAparencia( arma + 24);
				}
			}else {
				if(getAparencia()<23 || getAparencia()>=37) {
					setAparencia(24);
				}
			}
			animacaoAndandoEsquerda();		
			break;
		}
		case KeyEvent.VK_T:{
			if(isPistola()) {
				setDano(10);
			}else {
				setDano(18);
			}
			if(isDireita()) {
				if(pistola)
					setAparencia(arma+46);
				else 
					setAparencia(46);
				try {
					new Tiro(0, 2, 1, getX()+70, getY()+40, "Arquivos/Imagens/tiro.png", this,fase.getCamera().getInimigos(), dano).draw(fase.getCamera().getGraphics());;

					Thread.sleep(1000/2);	

				} catch (TratamentoException e) {
					e.printStackTrace();
				}catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				if(pistola)
					setAparencia(arma+47);
				else
					setAparencia(47);
				try {
					new Tiro(1, 2, 1, getX(), getY()+40, "Arquivos/Imagens/tiro.png", this,fase.getCamera().getInimigos(), dano).draw(fase.getCamera().getGraphics());	

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
		case 0:{
			if(isDireita()) {
				if(pistola) {
					if(getAparencia() >=arma+0 && getAparencia() <=arma+14 || getAparencia() == arma+46) {
						setAparencia(arma+15);
					}
				}else {
					if(getAparencia() >=0 && getAparencia() <=14 || getAparencia() == 46) {
						setAparencia(15);
					}
				}
				animacaoParadoDireita();
			}else {
				if(pistola) {
					if(getAparencia() >= arma + 23 && getAparencia() < arma + 37|| getAparencia() ==  arma + 47) {
						setAparencia( arma + 37);
					} 
				}else {
					if(getAparencia() >= 23 &&getAparencia() < 37 || getAparencia() == 47) {
						setAparencia(37);
					} 
				}
				animacaoParadoEsquerda();
			}
			break;

		}
		}

	}

	public void pular(){
		int anguloDoPulo = 25;
		int anguloCorrente = anguloDoPulo;
		boolean aux = isDireita();
		double dy,dx;

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

			if(!getFase().isColidindo(this)) {
				setY(getY()-((int)dy));
				setX(getX()+((int)dx));
			}
			else {
				setY(getY()+((int)dy));
				setX(getX()-((int)dx));
				break;
			}
			if(aux) {
				if(pistola) {
					if(getAparencia()>=arma+14 || getAparencia() <=arma+0 ) {
						setAparencia(arma+0);
					}
				}else {
					if(getAparencia()>=14) {
						setAparencia(0);
					}
				}
				animacaoAndandoDireita();
			}else {
				if(pistola) {
					if(getAparencia()<arma+23 || getAparencia()>=arma+37) {
						setAparencia(arma+24);
					}
				}else {
					if(getAparencia()<23 || getAparencia()>=37) {
						setAparencia(24);
					}
				}
				animacaoAndandoEsquerda();
			}
			try {
				Thread.sleep(1000/(getFase().getFPS()-10));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

		if(getFase().isButtom(this)) {
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

	public double getSede() {
		return sede;
	}



	public double getChance() {
		return chance;
	}



	public void setChance(double chance) {
		this.chance = chance;
	}



	public void setSede(double sede) {
		this.sede = sede;
	}

	public int getMenu() {
		return menu;
	}



	public void setMenu(int menu) {
		this.menu = menu;
	}



	public boolean isPistola() {
		return pistola;
	}



	public void setPistola(boolean pistola) {
		this.pistola = pistola;
	}



	public int getDano() {
		return dano;
	}



	public void setDano(int dano) {
		this.dano = dano;
	}



	public boolean isMultiplayer() {
		return multiplayer;
	}



	public void setMultiplayer(boolean multiplayer) {
		this.multiplayer = multiplayer;
	}









}
