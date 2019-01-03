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
	private Status status;
	
	public Camera(Lampiao lampiao,ArrayList<Sprite> inimigos,ArrayList<TileMap> camadas,Status status) {
		this.lampiao = lampiao;
		this.inimigos = inimigos;
		this.camadas = camadas;
		this.status = status;
		tela = new BufferedImage(camadas.get(0).getLarguraTela(), camadas.get(0).getAlturaTela(), BufferedImage.TYPE_4BYTE_ABGR);
		g = tela.getGraphics();
		
	}
	
	
	public void renderizar() {
		for(TileMap t : this.camadas) {
			this.g.drawImage(t.getMapa(), 0, 0, null);
		}
		status.draw(g);
		lampiao.draw(this.g);
		if(lampiao.getX()>=(camadas.get(1).getLarguraTela()-((lampiao.getFase().getInit().getLARGURA()/2)+1510))) {
			System.out.println("PARABÉns");
		}
		for(Sprite v : inimigos) {
			if(v.getVida()>=0) {
				v.draw(this.g);
				
			}
		}

		
		
	}
	
	public void draw(Graphics g) {
		if(lampiao.getX()>lampiao.getFase().getInit().getLARGURA()/2) 
			if(lampiao.getX()<(camadas.get(1).getLarguraTela()-((lampiao.getFase().getInit().getLARGURA()/2)+1510))) {
				x=lampiao.getX()-(lampiao.getFase().getInit().getLARGURA()/2);
			}
		
		status.setX(x+20);
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
