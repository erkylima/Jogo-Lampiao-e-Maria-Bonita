package model;

import java.awt.Graphics;
import java.io.IOException;

public class Metralha extends Sprite{

	public Metralha(int aparencia, int colunas, int linhas, int x, int y, String endereco)
			throws IOException {
		super(aparencia,  colunas, linhas, x, y, endereco);
		
	}

	@Override
	public void animar(int direcao) {
		
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int mover(int direcao) {
		return 0;
		
	}

}
