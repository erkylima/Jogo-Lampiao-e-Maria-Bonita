package model;

import java.awt.Graphics;
import java.io.IOException;

public class ButtonsMainMenu extends Sprite {

	public ButtonsMainMenu(int aparencia, int colunas, int linhas, int x, int y, String endereco, int vida)
			throws IOException {
		super(aparencia, colunas, linhas, x, y, endereco, vida);
		
		
	}

	@Override
	public void animacaoAndandoDireita() {
		// TODO Auto-generated method stub

	}

	@Override
	public void animacaoAndandoEsquerda() {
		// TODO Auto-generated method stub

	}

	@Override
	public void animacaoParadoDireita() {
		// TODO Auto-generated method stub

	}

	@Override
	public void animacaoParadoEsquerda() {
		// TODO Auto-generated method stub

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
