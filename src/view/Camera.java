package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;

import model.Metralha;
import model.Sprite;
import model.Status;
import model.TileMap;

public class Camera {
	private TileMap camada1;
	private TileMap camada2;
	private Sprite lampiao;
	private int x,y;
	private Metralha metralha;
	private BufferedImage tela;
	private Graphics g;
	
	public Camera(Sprite lampiao,TileMap camada1,TileMap camada2) {
		this.lampiao = lampiao;
		this.camada1 = camada1;
		this.camada2 = camada2;
		tela = new BufferedImage(camada1.getLarguraTela(), camada1.getAlturaTela(), BufferedImage.TYPE_4BYTE_ABGR);
		g = tela.getGraphics();
		try {
			metralha = new Metralha(6, 9, 2, 600, 625, "Arquivos/metralhasprite.png",30);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		camada1.montarMapa();
		camada2.montarMapa();
		
	}
	
	
	public void renderizar() {
		g.drawImage(camada1.getMapa(), 0, 0, null);
		g.drawImage(camada2.getMapa(), 0, 0, null);
		lampiao.draw(g);
		metralha.draw(g);
		metralha.animar();
		
	}
	
	public void draw(Graphics g) {
		if(lampiao.getX()>Fase1.janelaW/2) {
			if(lampiao.getX()<(camada1.getLarguraTela()-Fase1.janelaW)) {
				x=lampiao.getX()-Fase1.janelaW/2;
			}
		}
		g.drawImage(tela, -x, -y, null);
	}
	
	
	
	
}
