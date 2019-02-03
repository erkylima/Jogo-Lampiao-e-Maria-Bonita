package view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import controller.Inicializa;
import model.InvStatus;

public class Inventario extends Tela implements KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private InvStatus inv;
	
	public Inventario(int largura, int altura, Inicializa init) {
		super("",largura,altura,init);
		inv = new InvStatus(init);
		this.addKeyListener(this);

	}

	@Override
	public void init() {
		
	}

	public InvStatus getInv() {
		return inv;
	}


	@Override
	public void gameUpdate() {
		
		inv.draw(g);
		
	}

	@Override
	public void gameRender() {
		inv.renderizar();
	}

	@Override
	public void iniciaInimigos(boolean respawna) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		getInit().getLampiao().getFase().requestFocus();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	

}
