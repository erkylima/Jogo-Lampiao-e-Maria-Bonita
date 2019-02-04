package view;

import java.awt.BorderLayout;

import controller.Inicializa;
import controller.Movimento;
import model.Camera;
import model.Item;
import model.Volante;

public class F1 extends Tela {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Movimento m;
	private Camera camera;
	private boolean respawna = false;
	public F1(String titulo, int largura, int altura,Inicializa init) {
		super(titulo, largura, altura,init);
		getSom().fundo();

	}

	@Override
	public void init() {
		getInit().F1();
		getInit().getLampiao().setFase(this);
		camera = new Camera(getInit().getLampiao(), getInit().getInimigos(), getInit().getCamadasF1());
		getInit().getLampiao().getFase().setCamera(camera);;
		m = new Movimento(getInit().getLampiao(),this);
		addKeyListener(m);
		if(getInit().getConfig().getNivel()>10) {
			respawna=true;
		}
		iniciaInimigos(respawna);	
		getInit().getInventario().getInv().getThread().start();
	}

	@Override
	public void gameUpdate() {

		camera.draw(g);

	}

	@Override
	public void gameRender() {

		camera.renderizar();
		
		if(getInit().getLampiao().getX()>=6987 && completou() && getInit().getMaria().getX()>=6987) {
			getInit().getLampiao().setAcao(0);
			getInit().getLampiao().getFase().getCamera().destroier(getInit().getLampiao().getFase().getCamera());
			getInit().getLampiao().setFome(getInit().getLampiao().getFome()+(30-getInit().getConfig().getNivel()));
			this.setVisible(false);
			removeKeyListener(m);
			m.destroier(m);
			getInit().zerarCamadas();
			getSom().destroier(getSom());
			new F2("Lampião e Maria Bonita",1024,640,getInit());
			destroier(this);
			getInit().getJogo().remove(this);

			getInit().getJogo().add(getInit().getLampiao().getFase(),BorderLayout.PAGE_START);

		}
	}
	
	@Override
	public void iniciaInimigos(boolean respawna) {
		try {
			
			getInit().getInimigos().add(new Volante(0, 40, 1, 2044, 500, "Arquivos/Imagens/volantesprite.png", getInit().getLampiao(), 20,respawna));
			getInit().getInimigos().add(new Volante(0, 40, 1, 2200, 500, "Arquivos/Imagens/volantesprite.png", getInit().getLampiao(), 20,respawna));
			getInit().getInimigos().add(new Volante(0, 40, 1, 3000, 630, "Arquivos/Imagens/volantesprite.png", getInit().getLampiao(), 20,respawna));
			getInit().getInimigos().add(new Volante(0, 40, 1, 3000, 500, "Arquivos/Imagens/volantesprite.png",getInit().getLampiao(),50,respawna));
			getInit().getInimigos().add(new Volante(0, 40, 1, 2800, 500, "Arquivos/Imagens/volantesprite.png",getInit().getLampiao(),20,respawna));

			getInit().getInimigos().add(new Volante(0, 40, 1, 3631, 390, "Arquivos/Imagens/volantesprite.png", getInit().getLampiao(), 20,respawna));
			getInit().getInimigos().add(new Volante(0, 40, 1, 3200, 630, "Arquivos/Imagens/volantesprite.png", getInit().getLampiao(), 20,respawna));
			getInit().getInimigos().add(new Volante(0, 40, 1, 4804, 325, "Arquivos/Imagens/volantesprite.png",getInit().getLampiao(),20,respawna));
			getInit().getInimigos().add(new Volante(0, 40, 1, 4804, 325, "Arquivos/Imagens/volantesprite.png",getInit().getLampiao(),20,respawna));

			getInit().getInimigos().add(new Volante(0, 40, 1, 6048, 520, "Arquivos/Imagens/volantesprite.png", getInit().getLampiao(), 40,respawna));
			getInit().getInimigos().add(new Volante(0, 40, 1, 6048, 520, "Arquivos/Imagens/volantesprite.png",getInit().getLampiao(),40,respawna));
			getInit().getInimigos().add(new Volante(0, 40, 1, 6987, 320, "Arquivos/Imagens/volantesprite.png",getInit().getLampiao(),20,respawna));
			getInit().getInimigos().add(new Item(0, 4, 5, 6987, 320, "Arquivos/Imagens/coletaveis.png", 20, getInit(),true));
			getInit().getInimigos().add(new Item(0, 4, 5, 2678, 460, "Arquivos/Imagens/coletaveis.png", 20, getInit(),true));
			getInit().getInimigos().add(new Item(1, 4, 5, 5262, 490, "Arquivos/Imagens/coletaveis.png", 40, getInit(),false));


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
