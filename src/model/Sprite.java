package model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import controller.TratamentoException;

/**
 * Classe responsavel por recortar a imagem,
 *  e deixar sua sprite pronta para ser utilizada 
 *
 */
public abstract class Sprite {

	/**
	 * Largura e altura somente de uma imagem da sprite 
	 */
	private int larguraPersonagem, alturaPersonagem;

	/**
	 * Imagem de toda a sua Sprite
	 */
	protected BufferedImage personagem;
	/**
	 * largura e altura total de sua imagem personagem
	 */
	protected int largura, altura;
	/**
	 * Corresponde a quantidade de linhas e colunas de sua imagem personagem
	 */
	private boolean direita = true;

	protected int linhas, colunas;
	/**
	 * Localização x e y da tela 
	 */
	private int x, y;
	/**
	 * Array de todos as imagens de sua Sprite
	 * Cada valor é um estado(aparencia) diferente da Sprite 
	 */
	private BufferedImage[] sprites;
	/**
	 * Aparencia atual de sua Sprite.
	 *  Utilizada para saber qual valor do array das sprites usar 
	 */
	private int aparencia;

	private int acao;
	
	private double vida;
	

	/**
	 * @param aparencia
	 * @param largura
	 * @param altura
	 * @param colunas
	 * @param linhas
	 * @param x
	 * @param y
	 * @param endereco
	 * @throws IOException
	 */
	protected Sprite(int aparencia, int colunas, int linhas, int x, int y, String endereco,double vida) throws TratamentoException {

		try {

			this.personagem = ImageIO.read(new File(endereco));
			this.aparencia = aparencia;
			//this.largura = largura;
			//this.altura = altura;
			
			this.largura = personagem.getWidth()/colunas;
			this.altura = personagem.getHeight()/linhas;
			this.linhas = colunas;
			this.colunas = linhas;
			this.x = x;
			this.y = y;
			this.vida = vida;
			
			sprites = new BufferedImage[colunas * linhas];

			/*
			 * Recorta sua imagem em varias,
			 * cada recorte significa um estado da Sprite 
			 * 
			 */
			sprites = new BufferedImage[colunas * linhas];
			for(int i = 0; i < colunas; i++) {
			for(int j = 0; j < linhas; j++) {
				sprites[(j * colunas) + i] = personagem.getSubimage(i * largura, j * altura, largura, altura);
			}
		}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Nao foi possivel carregar a Sprite");
		}

		larguraPersonagem = sprites[0].getWidth();
		alturaPersonagem = sprites[0].getHeight();
	}

	/**
	 * Metodo abstrato resposavel por definir como sera a animação de sua Sprite, 
	 * Toda Sprite tem uma animação diferente dependendo da imagem.
	 * @param direcao
	 */
	public abstract void animacaoAndandoDireita();
	public abstract void animacaoAndandoEsquerda();
	public abstract void animacaoParadoDireita();
	public abstract void animacaoParadoEsquerda();


	/**
	 * Metodo abstrato responsavel por desenhar a Sprite na tela,
	 * @param g
	 */
	public abstract void draw(Graphics g);
	
	/**
	 * Metodo abstrato reponsavel por definir como sera o movimento da Sprite
	 * @param direcao
	 * @return 
	 */
	public abstract void mover();

	/*
	 * Metodos Getters e Setters
	 */
	public void setLocale(int x, int y) {

		this.x = x;
		this.y = y;
	}

	public BufferedImage getPersonagem() {
		return personagem;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getAparencia() {
		return aparencia;
	}

	public void setAparencia(int aparencia) {
		this.aparencia = aparencia;
	}

	public BufferedImage[] getSprites() {
		return sprites;
	}

	public int getLarguraPersonagem() {
		return larguraPersonagem;
	}

	public int getAlturaPersonagem() {
		return alturaPersonagem;
	}
	
	public int getAcao() {
		return acao;
	}

	public void setAcao(int acao) {
		this.acao = acao;
	}
	
	public double getVida() {
		return vida;
	}

	public void setVida(double vida) {
		this.vida = vida;
	}
	
	public boolean isDireita() {
		return direita;
	}

	public void setDireita(boolean direita) {
		this.direita = direita;
	}

	/**
	 * @return Rectangle 
	 */
	public Rectangle getBounds()
	{
		return new Rectangle(x, y, larguraPersonagem, alturaPersonagem);
	}
	
	public void destroier(Sprite sprite) {
		sprite = null;
		System.gc();
	}

}