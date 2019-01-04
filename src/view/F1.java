package view;

import java.awt.Graphics2D;
import java.io.IOException;

import controller.Inicializa;
import controller.Movimento;
import model.Lampiao;
import model.Metralha;
import model.Volante;

public class F1 extends Tela {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Camera camera;
	private Lampiao lamp;
	public F1(String titulo, int largura, int altura,Inicializa init) {
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
			getInit().getInimigosF1().add(new Volante(0, 40, 1, 1191, 500, "Arquivos/volantesprite.png", getInit().getLampiao(), 20));
			getInit().getInimigosF1().add(new Volante(0, 40, 1, 2044, 500, "Arquivos/volantesprite.png", getInit().getLampiao(), 20));
			getInit().getInimigosF1().add(new Volante(0, 40, 1, 2200, 500, "Arquivos/volantesprite.png", getInit().getLampiao(), 20));
			getInit().getInimigosF1().add(new Volante(0, 40, 1, 3000, 630, "Arquivos/volantesprite.png", getInit().getLampiao(), 20));
			getInit().getInimigosF1().add(new Metralha(0, 22, 1, 3000, 500, "Arquivos/metralhasprite.png",getInit().getLampiao(),20));
			getInit().getInimigosF1().add(new Metralha(0, 22, 1, 2800, 500, "Arquivos/metralhasprite.png",getInit().getLampiao(),20));

			getInit().getInimigosF1().add(new Volante(0, 40, 1, 3631, 390, "Arquivos/volantesprite.png", getInit().getLampiao(), 20));
			getInit().getInimigosF1().add(new Volante(0, 40, 1, 3200, 630, "Arquivos/volantesprite.png", getInit().getLampiao(), 20));
			getInit().getInimigosF1().add(new Metralha(0, 22, 1, 4427, 390, "Arquivos/metralhasprite.png",getInit().getLampiao(),20));
			getInit().getInimigosF1().add(new Metralha(0, 22, 1, 4804, 325, "Arquivos/metralhasprite.png",getInit().getLampiao(),20));
			
			getInit().getInimigosF1().add(new Volante(0, 40, 1, 6048, 520, "Arquivos/volantesprite.png", getInit().getLampiao(), 20));
			getInit().getInimigosF1().add(new Metralha(0, 22, 1, 6048, 520, "Arquivos/metralhasprite.png",getInit().getLampiao(),20));
			getInit().getInimigosF1().add(new Metralha(0, 22, 1, 6987, 320, "Arquivos/metralhasprite.png",getInit().getLampiao(),20));

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
//		if(getInit().getLampiao().getX()>=400) {
//			F2 f= new F2("Lampião e Maria Bonita",1024,868,getInit());
//			getInit().getJogo().add(f);
//			getInit().getJogo().remove(this);
//			
//		}
	}
	
}
