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
		getSom().fundo();
		getInit().getJogo().add(this);
		getInit().getLampiao().setX(40);
		getInit().getMaria().setX(init.getxInicial());
		getInit().getMaria().setY(init.getyInicial());
		getInit().getLampiao().getFase().requestFocus();
		Movimento m = new Movimento(getInit().getLampiao(),this);
		addKeyListener(m);
		

	}

	@Override
	public void init() {
		getInit().getLampiao().setFase(this);
		getInit().F2();
		camera = new Camera(getInit().getLampiao(), getInit().getInimigos(), getInit().getCamadasF1());
		getInit().getLampiao().getFase().setCamera(camera);
		iniciaInimigos();
	}

	@Override
	public void gameUpdate() {
		camera.draw(g);
		

	}

	@Override
	public void gameRender() {
		camera.renderizar();
		
		if(getInit().getLampiao().getX()>=6987 && completou()) {
			getInit().getLampiao().getFase().getInit().retornarMenu();
		}
	}


	@Override
	public void iniciaInimigos() {
		try {

			getInit().getInimigos().add(new Volante(0, 40, 1, 1191, 500, "Arquivos/Imagens/volantesprite.png", getInit().getLampiao(), 0));
			getInit().getInimigos().add(new Volante(0, 40, 1, 2044, 500, "Arquivos/Imagens/volantesprite.png", getInit().getLampiao(), 20));
			getInit().getInimigos().add(new Volante(0, 40, 1, 2200, 500, "Arquivos/Imagens/volantesprite.png", getInit().getLampiao(), 20));
			getInit().getInimigos().add(new Volante(0, 40, 1, 3000, 630, "Arquivos/Imagens/volantesprite.png", getInit().getLampiao(), 20));
			getInit().getInimigos().add(new Metralha(0, 22, 1, 3000, 500, "Arquivos/Imagens/metralhasprite.png",getInit().getLampiao(),20));
			getInit().getInimigos().add(new Metralha(0, 22, 1, 2800, 500, "Arquivos/Imagens/metralhasprite.png",getInit().getLampiao(),20));

			getInit().getInimigos().add(new Volante(0, 40, 1, 3631, 390, "Arquivos/Imagens/volantesprite.png", getInit().getLampiao(), 20));
			getInit().getInimigos().add(new Volante(0, 40, 1, 3200, 630, "Arquivos/Imagens/volantesprite.png", getInit().getLampiao(), 20));
			getInit().getInimigos().add(new Metralha(0, 22, 1, 4804, 325, "Arquivos/Imagens/metralhasprite.png",getInit().getLampiao(),20));

			getInit().getInimigos().add(new Volante(0, 40, 1, 6048, 520, "Arquivos/Imagens/volantesprite.png", getInit().getLampiao(), 20));
			getInit().getInimigos().add(new Metralha(0, 22, 1, 6048, 520, "Arquivos/Imagens/metralhasprite.png",getInit().getLampiao(),20));
			getInit().getInimigos().add(new Metralha(0, 22, 1, 6987, 320, "Arquivos/Imagens/metralhasprite.png",getInit().getLampiao(),20));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
