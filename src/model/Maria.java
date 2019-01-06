package model;

import java.awt.Graphics;
import java.io.IOException;

public class Maria extends Sprite{
	public double controlaVelocidade = 1;
	public int velocidade = 10;
	
	public Maria(int aparencia, int colunas, int linhas, int x, int y, String endereco, int vida)
			throws IOException {
		super(aparencia, colunas, linhas, x, y, endereco, vida);

	}


	@Override
	public void animacaoAndandoDireita() {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

}
