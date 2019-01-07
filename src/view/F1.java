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
	
	public F1(String titulo, int largura, int altura,Inicializa init) {
		super(titulo, largura, altura,init);
		
	}

	@Override
	public void init() {
		getInit().F1();
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
		if(getInit().getLampiao().getX()>6600) {
			if(getInit().getLampiao().getFase().getInit().getMaria().getAparencia()>9) {
				getInit().getLampiao().getFase().getInit().getMaria().setAcao(0);
			}
			getInit().getLampiao().getFase().getInit().getMaria().animacaoParadoEsquerda();
		}
		if(getInit().getLampiao().getX()>=6987 && completou()) {
			getInit().getLampiao().setAcao(0);
			this.setVisible(false);
			removeKeyListener(m);
			m.destroier(m);
			getInit().getInimigos().clear();
			new F2("Lampião e Maria Bonita",1024,640,getInit());
			destroier(this);
			getInit().getJogo().remove(this);

			getInit().getJogo().add(getInit().getLampiao().getFase(),BorderLayout.PAGE_START);

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
