package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.sun.glass.events.KeyEvent;

import model.Lampiao;
import model.Metralha;
import model.Sprite;
import model.Status;
import model.TileMap;
import model.Volante;

public class Camera {
	private TileMap camada1;
	private TileMap camada2;
	private Lampiao lampiao;
	private Status status;
	private int x,y;
	private Metralha metralha;
	private Volante volante;
	private ArrayList<Sprite> volanteA;

	private BufferedImage tela;
	private Graphics g;
	private Fase1 fase;
	
	public Camera(Lampiao lampiao,ArrayList<Sprite> inimigos,TileMap camada1,TileMap camada2,Fase1 fase) {
		this.lampiao = lampiao;
		this.camada1 = camada1;
		this.camada2 = camada2;
		this.fase = fase;
		tela = new BufferedImage(camada2.getLarguraTela(), camada2.getAlturaTela(), BufferedImage.TYPE_4BYTE_ABGR);
		g = tela.getGraphics();
		volanteA = inimigos;

		try {
			volanteA.add(new Metralha(0, 22, 1, 1200, 500, "Arquivos/metralhasprite.png",lampiao,fase,20));
			volanteA.add(new Volante(0, 40, 1, 2000, 500, "Arquivos/volantesprite.png", lampiao, fase, 20));
			volanteA.add(new Volante(0, 40, 1, 1600, 500, "Arquivos/volantesprite.png", lampiao, fase, 20));
			volanteA.add(new Volante(0, 40, 1, 2200, 500, "Arquivos/volantesprite.png", lampiao, fase, 20));
			volanteA.add(new Volante(0, 40, 1, 3000, 630, "Arquivos/volantesprite.png", lampiao, fase, 20));
			status = new Status(0, 7, 2, x, 20, "Arquivos/status.png", lampiao.getVida(), lampiao);
		} catch (IOException e) {
			e.printStackTrace();
		}

		camada1.montarMapa();
		camada2.montarMapa();
		
	}
	
	
	public void renderizar() {
		this.g.drawImage(camada1.getMapa(), 0, 0, null);
		this.g.drawImage(camada2.getMapa(), 0, 0, null);
		status.draw(g);


		lampiao.draw(this.g);

		for(Sprite v : volanteA) {
			if(v.getVida()>=0) {
				v.draw(this.g);
				
			}
		}

		
		
	}
	
	public void draw(Graphics g) {
		if(lampiao.getX()>Fase1.LARGURA/2) 
			if(lampiao.getX()<(camada1.getLarguraTela()-Fase1.LARGURA/2))
				x=lampiao.getX()-(Fase1.LARGURA/2);
			
		
		status.setX(x);
		g.drawImage(tela, -x, -y, null);	

	}


	public ArrayList<Sprite> getVolanteA() {
		return volanteA;
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
