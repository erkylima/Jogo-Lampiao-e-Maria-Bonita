package view;

import controller.Inicializa;
import model.FimCam;


public class Fim extends Tela{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FimCam inv;
	private Inicializa init ;
	private int count;
	public Fim(int largura, int altura, Inicializa init) {
		super("",largura,altura,init);
		getInit().getLampiao().setFase(this);
		this.init = init;
		inv = new FimCam(init.getLampiao(), init);


	}

	@Override
	public void init() {
		
	}

	@Override
	public void gameUpdate() {
		
		inv.draw(g);
		
	}

	@Override
	public void gameRender() {
		inv.renderizar();
		if(count<5) {
			count++;
			System.out.println(count);
		}else if (count==5){
			count++;
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			destroier(this);
			init.getJogo().remove(init.getJogo());
			init.getJogo().dispose();
			Inicializa init = new Inicializa();		
			new IniciarJogo("Lampião e Maria Bonita", init);
		}
		
	}

	@Override
	public void iniciaInimigos(boolean respawna) {
		// TODO Auto-generated method stub
		
	}
}