package view;

import java.awt.BorderLayout;

import controller.Inicializa;
import controller.Movimento;
import model.Camera;
import model.Item;
import model.Volante;

public class Multiplayer extends Tela {
	private static final long serialVersionUID = 1L;
	private Movimento m;
	private Camera camera;
	private boolean respawna = false;

	public Multiplayer(String titulo, int largura, int altura,Inicializa init) {
		super(titulo, largura, altura,init);
		getSom().fundo();

	}

	@Override
	public void init() {
		getInit().multiplayer();
		getInit().getLampiao().setMultiplayer(true);
		getInit().getMariamultiplayer().setFase(this);
		getInit().getLampiao().setFase(this);
		
		camera = new Camera(getInit().getLampiao(), getInit().getInimigos(), getInit().getCamadasF1());

		getInit().getLampiao().getFase().setCamera(camera);
		m = new Movimento(getInit().getLampiao(),this);
		addKeyListener(m);
		m = new Movimento(getInit().getMariamultiplayer(), this);
		addKeyListener(m);
		
		if(getInit().getConfig().getNivel()>10) {
			respawna=true;
		}
		iniciaInimigos(respawna);		
	}

	@Override
	public void gameUpdate() {
		if(getInit().getLampiao().getVida()>=20) {
			getInit().getMariamultiplayer().setVida(getInit().getLampiao().getVida());
		}
		if(getInit().getMariamultiplayer().getVida()<=0) {
			getInit().getMaria().setVida(0);
			getInit().getMariamultiplayer().setX(40);
			getInit().getMariamultiplayer().setY(300);
			getInit().getMariamultiplayer().setVida(120);
		}
		if(getInit().getLampiao().getVida()<=0) {
			getInit().getMariamultiplayer().setX(40);
			getInit().getMariamultiplayer().setY(300);
			getInit().getMariamultiplayer().setVida(120);
		}
		camera.draw(g);

	}

	@Override
	public void gameRender() {

		camera.renderizar();

		if(getInit().getLampiao().getX()>=6987 && completou() && getInit().getMaria().getX()>=6987) {
			
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
			new FimMultiplayer(1024,640,getInit());
			getInit().getJogo().remove(this);
			
			getInit().getJogo().add(getInit().getLampiao().getFase(),BorderLayout.PAGE_START);
			destroier(this);
		}
	}

	@Override
	public void iniciaInimigos(boolean respawna) {
		try {

			getInit().getInimigos().add(new Volante(0, 40, 1, 2044, 500, "Arquivos/Imagens/volantesprite.png", getInit().getLampiao(), 50,respawna));
			getInit().getInimigos().add(new Volante(0, 40, 1, 2200, 500, "Arquivos/Imagens/volantesprite.png", getInit().getLampiao(), 50,respawna));
			getInit().getInimigos().add(new Volante(0, 40, 1, 3000, 630, "Arquivos/Imagens/volantesprite.png", getInit().getLampiao(), 50,respawna));
			getInit().getInimigos().add(new Volante(0, 40, 1, 3000, 500, "Arquivos/Imagens/volantesprite.png",getInit().getLampiao(),20,respawna));
			getInit().getInimigos().add(new Volante(0, 40, 1, 2800, 500, "Arquivos/Imagens/volantesprite.png",getInit().getLampiao(),20,respawna));

			getInit().getInimigos().add(new Volante(0, 40, 1, 3631, 390, "Arquivos/Imagens/volantesprite.png", getInit().getLampiao(), 20,respawna));
			getInit().getInimigos().add(new Volante(0, 40, 1, 3200, 630, "Arquivos/Imagens/volantesprite.png", getInit().getLampiao(), 20,respawna));
			getInit().getInimigos().add(new Volante(0, 40, 1, 4804, 325, "Arquivos/Imagens/volantesprite.png",getInit().getLampiao(),20,respawna));
			getInit().getInimigos().add(new Volante(0, 40, 1, 4804, 325, "Arquivos/Imagens/volantesprite.png",getInit().getLampiao(),20,respawna));

			getInit().getInimigos().add(new Volante(0, 40, 1, 6048, 520, "Arquivos/Imagens/volantesprite.png", getInit().getLampiao(), 50,respawna));
			getInit().getInimigos().add(new Volante(0, 40, 1, 6048, 520, "Arquivos/Imagens/volantesprite.png",getInit().getLampiao(),50,respawna));
			getInit().getInimigos().add(new Volante(0, 40, 1, 6689, 320, "Arquivos/Imagens/volantesprite.png",getInit().getLampiao(),50,respawna));


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
