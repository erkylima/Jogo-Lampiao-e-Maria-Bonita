package view;

import java.awt.BorderLayout;

import controller.Inicializa;
import controller.Movimento;
import model.Camera;
import model.Item;
import model.Metralha;
import model.Volante;

public class F2 extends Tela {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Movimento m;
	private Camera camera;
	private boolean respawna = false;
	
	public F2(String titulo, int largura, int altura, Inicializa init) {
		super(titulo, largura, altura,init);
		getSom().fundo();
		getInit().getJogo().add(this);
		getInit().getLampiao().setX(init.getxInicial());
		getInit().getLampiao().getFase().requestFocus();
		m = new Movimento(getInit().getLampiao(),this);
		addKeyListener(m);
		
		getInit().getMaria().setX(6800);
		getInit().getMaria().setY(100);
	}

	@Override
	public void init() {
		getInit().getLampiao().setFase(this);
		getInit().F2();
		camera = new Camera(getInit().getLampiao(), getInit().getInimigos(), getInit().getCamadasF1());
		getInit().getLampiao().getFase().setCamera(camera);
		
		if(getInit().getConfig().getNivel()>10) {
			respawna = true;
		}
		iniciaInimigos(respawna);
	}

	@Override
	public void gameUpdate() {
		camera.draw(g);
		

	}

	@Override
	public void gameRender() {
		camera.renderizar();
		
		if(getInit().getLampiao().getX()>=6987 && completou()) {
//			getInit().getLampiao().getFase().getInit().retornarMenu();
			
			getInit().getLampiao().getFase().zerarInimigos();
			getInit().getLampiao().setAcao(0);
			getInit().getLampiao().getFase().getCamera().destroier(getInit().getLampiao().getFase().getCamera());
			this.setVisible(false);
			removeKeyListener(m);
			m.destroier(m);
			getInit().getMaria().destroier(getInit().getMaria());
			getInit().zerarCamadas();
			getInit().getInimigos().clear();
			getSom().destroier(getSom());
			new Fim(1024,640,getInit());
			getInit().getJogo().remove(this);
			
			getInit().getJogo().add(getInit().getLampiao().getFase(),BorderLayout.PAGE_START);
			destroier(this);

		}
	}


	@Override
	public void iniciaInimigos(boolean respawna) {
		try {

			getInit().getInimigos().add(new Volante(0, 40, 1, 2090, 400, "Arquivos/Imagens/volantesprite.png", getInit().getLampiao(), 20,respawna));
			getInit().getInimigos().add(new Metralha(0, 22, 1, 2966, 335, "Arquivos/Imagens/metralhasprite.png",getInit().getLampiao(),20,respawna));
			getInit().getInimigos().add(new Metralha(0, 22, 1, 3070, 460, "Arquivos/Imagens/metralhasprite.png",getInit().getLampiao(),20,respawna));

			getInit().getInimigos().add(new Volante(0, 40, 1, 5456, 527, "Arquivos/Imagens/volantesprite.png", getInit().getLampiao(), 20,respawna));
			getInit().getInimigos().add(new Volante(0, 40, 1, 5616, 527, "Arquivos/Imagens/volantesprite.png", getInit().getLampiao(), 20,respawna));
			getInit().getInimigos().add(new Volante(0, 40, 1, 5808, 527, "Arquivos/Imagens/volantesprite.png", getInit().getLampiao(), 20,respawna));

			getInit().getInimigos().add(new Volante(0, 40, 1, 6784, 527, "Arquivos/Imagens/volantesprite.png", getInit().getLampiao(), 20,respawna));
			getInit().getInimigos().add(new Metralha(0, 22, 1, 6800, 527, "Arquivos/Imagens/metralhasprite.png",getInit().getLampiao(),20,respawna));

			getInit().getInimigos().add(new Volante(0, 40, 1, 6650, 527, "Arquivos/Imagens/volantesprite.png", getInit().getLampiao(), 20,respawna));
			getInit().getInimigos().add(new Metralha(0, 22, 1, 6830, 527, "Arquivos/Imagens/metralhasprite.png",getInit().getLampiao(),20,respawna));
			getInit().getInimigos().add(new Metralha(0, 22, 1, 6500, 527, "Arquivos/Imagens/metralhasprite.png",getInit().getLampiao(),20,respawna));
			getInit().getInimigos().add(new Metralha(0, 22, 1, 6500, 527, "Arquivos/Imagens/metralhasprite.png",getInit().getLampiao(),20,respawna));
			getInit().getInimigos().add(new Item(1, 4, 5, 1373, 460, "Arquivos/Imagens/coletaveis.png", 20, getInit(),false));
			getInit().getInimigos().add(new Item(0, 4, 5, 3525, 432, "Arquivos/Imagens/coletaveis.png", 20, getInit(),true));
			getInit().getInimigos().add(new Item(1, 4, 5, 3600, 432, "Arquivos/Imagens/coletaveis.png", 20, getInit(),false));
			getInit().getInimigos().add(new Item(1, 4, 5, 4864, 525, "Arquivos/Imagens/coletaveis.png", 20, getInit(),false));
			getInit().getInimigos().add(new Item(0, 4, 5, 4752, 533, "Arquivos/Imagens/coletaveis.png", 20, getInit(),true));
			getInit().getInimigos().add(new Item(0, 4, 5, 4188, 325, "Arquivos/Imagens/coletaveis.png", 20, getInit(),true));
			getInit().getInimigos().add(new Item(0, 4, 5, 4604, 390, "Arquivos/Imagens/coletaveis.png", 20, getInit(),true));

		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
