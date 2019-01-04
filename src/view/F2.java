package view;

import controller.Inicializa;
import controller.Movimento;
import model.Lampiao;
import model.Volante;

public class F2 extends Tela implements Runnable {
	private Camera camera;
	private Lampiao lamp;
	public F2(String titulo, int largura, int altura, Inicializa init) {
		super(titulo, largura, altura,init);



	}

	@Override
	public void init() {
		getInit().getLampiao().setFase(this);
		camera = new Camera(getInit().getLampiao(), getInit().getInimigosF1(), getInit().getCamadasF1());
		getInit().getLampiao().getFase().setCamera(camera);;
		Movimento m = new Movimento(getInit().getLampiao(),this);
		addKeyListener(m);
		try {
			//				getInit().getInimigosF1().add(new Metralha(0, 22, 1, 3000, 500, "Arquivos/metralhasprite.png",getInit().getLampiao(),20));
			//				getInit().getInimigosF1().add(new Volante(0, 40, 1, 2000, 500, "Arquivos/volantesprite.png", getInit().getLampiao(), 20));
			//				getInit().getInimigosF1().add(new Volante(0, 40, 1, 1600, 500, "Arquivos/volantesprite.png", getInit().getLampiao(), 20));
			//				getInit().getInimigosF1().add(new Volante(0, 40, 1, 2200, 500, "Arquivos/volantesprite.png", getInit().getLampiao(), 20));
			//				getInit().getInimigosF1().add(new Volante(0, 40, 1, 3000, 630, "Arquivos/volantesprite.png", getInit().getLampiao(), 20));
			//				getInit().getInimigosF1().add(new Volante(0, 40, 1, 3400, 630, "Arquivos/volantesprite.png", getInit().getLampiao(), 20));
			//				getInit().getInimigosF1().add(new Volante(0, 40, 1, 3200, 630, "Arquivos/volantesprite.png", getInit().getLampiao(), 20));
			//				getInit().getInimigosF1().add(new Metralha(0, 22, 1, 3600, 500, "Arquivos/metralhasprite.png",getInit().getLampiao(),20));
			//				getInit().getInimigosF1().add(new Metralha(0, 22, 1, 4000, 500, "Arquivos/metralhasprite.png",getInit().getLampiao(),20));
			getInit().getInimigosF1().add(new Volante(0, 40, 1, 4600, 630, "Arquivos/volantesprite.png", getInit().getLampiao(), 20));
			//				getInit().getInimigosF1().add(new Metralha(0, 22, 1, 5000, 600, "Arquivos/metralhasprite.png",getInit().getLampiao(),20));

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

	}

}
