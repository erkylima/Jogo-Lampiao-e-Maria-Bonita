package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import view.Menu;

public class NavegarMenu extends KeyAdapter {
	
	private Menu menu;
	
	public NavegarMenu(Menu menu) {
		this.menu = menu;
	}


	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == e.VK_UP && menu.getSelected()>0) {
			menu.setSelected(menu.getSelected()-1);
		}
		if(e.getKeyCode() == e.VK_DOWN && menu.getSelected()<3) {
			menu.setSelected(menu.getSelected()+1);
		}
		if(e.getKeyCode() == e.VK_ENTER) {
			switch (menu.getSelected()) {
			case 0:
				menu.iniciarJogo();
				break;
			case 1:
				
				break;
			case 2:
				menu.config();
				break;
			case 3:
				menu.sobre();
				break;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
	}
}
