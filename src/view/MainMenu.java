package view;

import controller.Inicializa;
import controller.NavegarMenu;

public class MainMenu extends Tela {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Menu menu;
	public MainMenu(String titulo, int largura, int altura, Inicializa init) {
		super(titulo, largura, altura, init);
		setFPS(15);
		menu = new Menu(largura, altura, init,this);
		addKeyListener(new NavegarMenu(menu));

		
	}

	@Override
	public void init() {

	}

	@Override
	public void gameUpdate() {
		menu.draw(g);

		
	}

	@Override
	public void gameRender() {
		menu.renderizar();

	}
	
	

	
	
}
