package view;

import java.io.IOException;

import javax.swing.JFrame;

import controller.Inicializa;
import controller.NavegarMenu;
import model.ButtonsMainMenu;
import model.Volante;

public class MainMenu extends Tela {
	
	private Menu menu;
	private JFrame frame;
	public MainMenu(String titulo, int largura, int altura, Inicializa init) {
		super(titulo, largura, altura, init);
		this.frame = frame;
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
	
	public void destroier(MainMenu main){
		main = null;
		System.gc();
	}

	
	
}
