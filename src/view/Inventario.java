package view;

import controller.Inicializa;

public class Inventario extends Tela{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private InvStatus inv;
	
	public Inventario(int largura, int altura, Inicializa init) {
		super("",largura,altura,init);
		inv = new InvStatus(init.getLampiao(), init);


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
	}
	
	

}
