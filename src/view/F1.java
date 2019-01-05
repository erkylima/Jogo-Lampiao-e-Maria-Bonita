package view;

import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.io.IOException;

import controller.Inicializa;
import controller.Movimento;
import model.Lampiao;
import model.Metralha;
import model.Sprite;
import model.Volante;

public class F1 extends Tela {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Movimento m;
	private Camera camera;
	private Lampiao lamp;
	public F1(String titulo, int largura, int altura,Inicializa init) {
		super(titulo, largura, altura,init);
		setFPS(60);
	}

	@Override
	public void init() {
		getInit().getLampiao().setFase(this);
		camera = new Camera(getInit().getLampiao(), getInit().getInimigos(), getInit().getCamadasF1());
		getInit().getLampiao().getFase().setCamera(camera);;
		m = new Movimento(getInit().getLampiao(),this);
		addKeyListener(m);
		try {
			getInit().getInimigos().add(new Volante(0, 40, 1, 1191, 500, "Arquivos/volantesprite.png", getInit().getLampiao(), 0));
			getInit().getInimigos().add(new Volante(0, 40, 1, 2044, 500, "Arquivos/volantesprite.png", getInit().getLampiao(), 20));
			getInit().getInimigos().add(new Volante(0, 40, 1, 2200, 500, "Arquivos/volantesprite.png", getInit().getLampiao(), 20));
			getInit().getInimigos().add(new Volante(0, 40, 1, 3000, 630, "Arquivos/volantesprite.png", getInit().getLampiao(), 20));
			getInit().getInimigos().add(new Metralha(0, 22, 1, 3000, 500, "Arquivos/metralhasprite.png",getInit().getLampiao(),20));
			getInit().getInimigos().add(new Metralha(0, 22, 1, 2800, 500, "Arquivos/metralhasprite.png",getInit().getLampiao(),20));

			getInit().getInimigos().add(new Volante(0, 40, 1, 3631, 390, "Arquivos/volantesprite.png", getInit().getLampiao(), 20));
			getInit().getInimigos().add(new Volante(0, 40, 1, 3200, 630, "Arquivos/volantesprite.png", getInit().getLampiao(), 20));
			getInit().getInimigos().add(new Metralha(0, 22, 1, 4804, 325, "Arquivos/metralhasprite.png",getInit().getLampiao(),20));
			
			getInit().getInimigos().add(new Volante(0, 40, 1, 6048, 520, "Arquivos/volantesprite.png", getInit().getLampiao(), 20));
			getInit().getInimigos().add(new Metralha(0, 22, 1, 6048, 520, "Arquivos/metralhasprite.png",getInit().getLampiao(),20));
			getInit().getInimigos().add(new Metralha(0, 22, 1, 6987, 320, "Arquivos/metralhasprite.png",getInit().getLampiao(),20));

			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void gameUpdate() {
		camera.draw(g);
	}

	@Override
	public void gameRender() {
		camera.renderizar();
		
		if(getInit().getLampiao().getX()>=6987 && completou()) {
			System.out.println(getInit().getInventario());
			getInit().getLampiao().setAcao(0);
			this.setVisible(false);
			m.destroier(m);
			removeKeyListener(m);
			F2 f= new F2("Lampião e Maria Bonita",1024,640,getInit());
			
			getInit().getJogo().remove(this);
			getInit().getJogo().add(getInit().getLampiao().getFase(),BorderLayout.PAGE_START);
//			getInit().getJogo().add(getInit().getInventario(),BorderLayout.PAGE_END);
//			System.out.println(getInit().getInimigos().size());
		}
	}
	private boolean completou() {
		for (Sprite inimigo : getInit().getInimigos()) {
			if(inimigo.getVida()>0) {
				System.out.println("Ainda tem");
				return false;
			}
		}
		return true;
	}
}
