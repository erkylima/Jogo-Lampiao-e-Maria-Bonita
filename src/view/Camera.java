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
	private ArrayList<Volante> volanteA;

	private BufferedImage tela;
	private Graphics g;
	private Fase1 fase;
	
	public Camera(Lampiao lampiao,TileMap camada1,TileMap camada2,Fase1 fase) {
		this.lampiao = lampiao;
		this.camada1 = camada1;
		this.camada2 = camada2;
		this.fase = fase;
		tela = new BufferedImage(camada1.getLarguraTela(), camada1.getAlturaTela(), BufferedImage.TYPE_4BYTE_ABGR);
		g = tela.getGraphics();
		volanteA = new ArrayList<>();

		try {
			metralha = new Metralha(6, 9, 3, 1200, 620, "Arquivos/metralhasprite.png",lampiao,fase,30);
			volante = new Volante(0, 40, 1, 1600, 5, "Arquivos/volantesprite.png", lampiao, fase, 30);
			volanteA.add(new Volante(0, 40, 1, 1400, 500, "Arquivos/volantesprite.png", lampiao, fase, 30));
			volanteA.add(new Volante(0, 40, 1, 1200, 500, "Arquivos/volantesprite.png", lampiao, fase, 30));
			volanteA.add(new Volante(0, 40, 1, 1800, 630, "Arquivos/volantesprite.png", lampiao, fase, 30));
		} catch (IOException e) {
			e.printStackTrace();
		}

		camada1.montarMapa();
		camada2.montarMapa();
		
	}
	
	
	public void renderizar() {
		g.drawImage(camada1.getMapa(), 0, 0, null);
		g.drawImage(camada2.getMapa(), 0, 0, null);

//		status.animacaoAndandoDireita();
		lampiao.draw(g);
		volante.draw(g);
		for(Volante v : volanteA) {
			v.draw(g);
		}
//		metralha.draw(g);
//		metralha.animar();
		
	}
	
	public void draw(Graphics g) {
		if(lampiao.getX()>Fase1.janelaW/2) {
			if(lampiao.getX()<(camada1.getLarguraTela()-Fase1.janelaW)) {
				x=lampiao.getX()-Fase1.janelaW/2;
			}
			switch(lampiao.getAcao()) {
			case KeyEvent.VK_D:
//				status.setX(lampiao.getX()-460);	
				break;
			case 0:
//				status.setX(lampiao.getX()-Fase1.janelaW/2);	
			}
		}
		g.drawImage(tela, -x, -y, null);

	}
	
	
	
	
}
