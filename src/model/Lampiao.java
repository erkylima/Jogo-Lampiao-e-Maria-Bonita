package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Lampiao {
	public BufferedImage spriteSheet;   
	public int width, height;
	public int rows, columns;
	public int x, y;//� atributo de Personagem
	public BufferedImage[] sprites;
	public int aparencia;
	public int controlaVelocidade = 0;
	public int velocidade = 5;
	
	public Lampiao(File file, int aparencia, int columns, int rows, int posX, int posY) throws IOException {
		spriteSheet = ImageIO.read(file);
		this.aparencia=aparencia;
//		this.width = width;
//		this.height = height;
		
		this.width = spriteSheet.getWidth()/columns;
		this.height = spriteSheet.getHeight()/rows;

		this.rows = columns;
		this.columns = rows;
		this.x=posX;
		this.y=posY;

		sprites = new BufferedImage[columns * rows];
			for(int i = 0; i < columns; i++) {
			for(int j = 0; j < rows; j++) {
				sprites[(j * columns) + i] = spriteSheet.getSubimage(i * width, j * height, width, height);
			}
		}
	}
	

	
	public void animacaoAndandoDireita(){
		controlaVelocidade+=5;
		if(controlaVelocidade>velocidade && (aparencia >=0 && aparencia <=14)){
			aparencia += 1;
			controlaVelocidade = 0;
			if(aparencia == 14){ aparencia = 0; }
		}
	}
	
	public void animacaoParadoDireita(){
		controlaVelocidade+=1;
		if(controlaVelocidade>velocidade && (aparencia >=15 && aparencia <= 22)){
			aparencia += 1;
			controlaVelocidade = 0;
			if(aparencia == 22){ aparencia = 15; }
		}
	}

	
	public void animacaoAndandoEsquerda(){
		controlaVelocidade+=5;
//		System.out.println(aparencia);
		if(controlaVelocidade>velocidade && (aparencia >= 23 && aparencia<=36)){
			aparencia += 1;
			controlaVelocidade = 0;
			if(aparencia == 36){ aparencia = 23; }
		}
	}	
	
	public void animacaoParadoEsquerda(){
		controlaVelocidade+=1;
		if(controlaVelocidade>velocidade && (aparencia >=37 && aparencia <=45)){
			aparencia += 1;
			controlaVelocidade = 0;
			if(aparencia == 45){ aparencia = 37; }
		}
	}
}
