package view;

import controller.Inicializa;
import controller.Movimento;
import model.Lampiao;
import model.Metralha;
import model.Sprite;
import model.Volante;

public class F2 extends Tela {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Camera camera;
	public F2(String titulo, int largura, int altura, Inicializa init) {
		super(titulo, largura, altura,init);
		getInit().getJogo().add(this);
		getInit().getLampiao().setX(40);
		getInit().getLampiao().getFase().requestFocus();
		Movimento m = new Movimento(getInit().getLampiao(),this);
		addKeyListener(m);
		

	}

	@Override
	public void init() {
		getInit().getLampiao().setFase(this);

		camera = new Camera(getInit().getLampiao(), getInit().getInimigos(), getInit().getCamadasF1());
		getInit().getLampiao().getFase().setCamera(camera);
		
		try {
			getInit().getInimigos().add(new Metralha(0, 22, 1, 3000, 500, "Arquivos/metralhasprite.png",getInit().getLampiao(),20));
			getInit().getInimigos().add(new Volante(0, 40, 1, 2000, 500, "Arquivos/volantesprite.png", getInit().getLampiao(), 20));
			getInit().getInimigos().add(new Volante(0, 40, 1, 1600, 500, "Arquivos/volantesprite.png", getInit().getLampiao(), 20));
			getInit().getInimigos().add(new Volante(0, 40, 1, 2200, 500, "Arquivos/volantesprite.png", getInit().getLampiao(), 20));
			getInit().getInimigos().add(new Volante(0, 40, 1, 3000, 630, "Arquivos/volantesprite.png", getInit().getLampiao(), 20));
			getInit().getInimigos().add(new Volante(0, 40, 1, 3400, 630, "Arquivos/volantesprite.png", getInit().getLampiao(), 20));
			getInit().getInimigos().add(new Volante(0, 40, 1, 3200, 630, "Arquivos/volantesprite.png", getInit().getLampiao(), 20));
			getInit().getInimigos().add(new Metralha(0, 22, 1, 4000, 500, "Arquivos/metralhasprite.png",getInit().getLampiao(),20));
			getInit().getInimigos().add(new Volante(0, 40, 1, 4600, 630, "Arquivos/volantesprite.png", getInit().getLampiao(), 20));
			getInit().getInimigos().add(new Metralha(0, 22, 1, 5000, 600, "Arquivos/metralhasprite.png",getInit().getLampiao(),20));

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
			getInit().getLampiao().setAcao(0);
			getInit().getInimigos().clear();
			getInit().retornarMenu("Voc� conseguiu! Conquistou Maria Bonita!","Deseja voltar ao menu principal?");
		}
	}


	@Override
	public void iniciaInimigos() {
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
}
