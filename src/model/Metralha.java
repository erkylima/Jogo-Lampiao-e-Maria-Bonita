package model;

import java.awt.Graphics;
import java.io.IOException;

public class Metralha extends Sprite{

	public double controlaVelocidade = 0;
	public int velocidade = 10;

	
	public Metralha(int aparencia, int colunas, int linhas, int x, int y, String endereco)
			throws IOException {
		super(aparencia,  colunas, linhas, x, y, endereco);
		
	}

	@Override
	public void animar(int direcao) {
		controlaVelocidade+=2;
		if(controlaVelocidade>velocidade && (getAparencia() >=0 && getAparencia() <=13)){
			setAparencia(getAparencia()+1);
			controlaVelocidade = 0;
			if(getAparencia() == 13){ setAparencia(0);
			}
		}
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int mover() {
		return 0;
		
	}

}
