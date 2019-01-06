package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import model.Lampiao;
import model.Sprite;
import model.Status;
import model.TileMap;

public class Camera {
	private ArrayList<TileMap> camadas;
	private Lampiao lampiao;
	private int x,y;
	private ArrayList<Sprite> inimigos;
	private BufferedImage tela;
	private Graphics g;
	
	public Camera(Lampiao lampiao,ArrayList<Sprite> inimigos,ArrayList<TileMap> camadas) {
		this.lampiao = lampiao;
		this.inimigos = inimigos;
		this.camadas = camadas;
		tela = new BufferedImage(camadas.get(0).getLarguraTela(), camadas.get(0).getAlturaTela(), BufferedImage.TYPE_4BYTE_ABGR);
		g = tela.getGraphics();
		
	}
	
	
	public void renderizar() {
		for(TileMap t : this.camadas) {
			this.g.drawImage(t.getMapa(), 0, 0, null);
		}
		lampiao.draw(this.g);
		lampiao.getFase().getInit().getMaria().draw(g);
		
		
		if(lampiao.getX()>6600) {
			if(lampiao.getFase().getInit().getMaria().getAparencia()>9) {
				lampiao.getFase().getInit().getMaria().setAcao(0);
			}
			lampiao.getFase().getInit().getMaria().animacaoParadoEsquerda();
		}
		for(Sprite v : inimigos) {
			if(v.getVida()>=0) {
				v.draw(this.g);
				
			}
		}
		
	}
	
	public void draw(Graphics g) {
		if(lampiao.getX()>lampiao.getFase().getInit().getLARGURA()/2) 
			if(lampiao.getX()<(camadas.get(1).getLarguraTela()-((lampiao.getFase().getInit().getLARGURA()/2)))) {
				x=lampiao.getX()-(lampiao.getFase().getInit().getLARGURA()/2);
			}
		
		g.drawImage(tela, -x, -y, null);	

	}


	public ArrayList<Sprite> getInimigos() {
		return inimigos;
	}


	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}


	public Graphics getGraphics() {
		return g;
	}	
	
	
	
}
