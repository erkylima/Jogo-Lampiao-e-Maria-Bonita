package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import model.Menu;

public class NavegarMenu extends KeyAdapter {
	
	private Menu menu;
	private boolean sobre,config = false;
	
	public NavegarMenu(Menu menu) {
		this.menu = menu;
	}


	@SuppressWarnings("static-access")
	@Override
	public void keyPressed(KeyEvent e) {
		Som som = new Som();
		if(e.getKeyCode() == e.VK_UP && menu.getSelected()>0) {
			menu.setSelected(menu.getSelected()-1);
			sobre= false;
			config=false;
			menu.setSobrePage(1);
		}
		if(e.getKeyCode() == e.VK_DOWN && menu.getSelected()<3) {
			sobre= false;
			config=false;
			menu.setSelected(menu.getSelected()+1);
		}
		if(e.getKeyCode() == e.VK_SPACE && config) {
			menu.configNivel();
			som.menuConfig();
		}
		if(e.getKeyCode() == e.VK_T && config) {
			menu.configFPS();
			som.menuConfig();
		}
		if(e.getKeyCode() == e.VK_ENTER) {
			switch (menu.getSelected()) {
			case 0:
				menu.iniciarJogo();
				break;
			case 1:
				
				break;
			case 2:
				config = menu.configToggle();
				
				break;
			case 3:
				menu.setSobrePage(1);
				sobre  = menu.sobreToggle();
				break;
			}
		}
		if(e.getKeyCode() == e.VK_LEFT && menu.getSobrePage()>1 && sobre) {
			menu.setSobrePage(menu.getSobrePage()-1);
			som.pagina();
		}
		if(e.getKeyCode() == e.VK_RIGHT && menu.getSobrePage()<4 && sobre) {
			menu.setSobrePage(menu.getSobrePage()+1);
			som.pagina();
		}
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE && (!sobre && !config)) {
			System.exit(0);
		}
	}
}
