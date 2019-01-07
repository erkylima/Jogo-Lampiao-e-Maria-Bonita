package view;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

import controller.Inicializa;

public class IniciarJogo extends JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IniciarJogo(String titulo,Inicializa init)  {
		super(titulo);
		init.setJogo(this);
		setSize(init.getLARGURA(),init.getALTURA());
		setResizable(false);
		setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		BorderLayout s = new BorderLayout();

		setLayout(s);
		setUndecorated(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		MainMenu menu = new MainMenu("Lampiao e Maria Bonita",1024,768,init);
		add(menu);		
		
//		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
//		GraphicsDevice device = env.getScreenDevices()[0];
//
//		DisplayMode oldMode = device.getDisplayMode();
//		DisplayMode newMode = new DisplayMode(1024,768,oldMode.getBitDepth(),oldMode.getRefreshRate());
//		device.setFullScreenWindow(this);
//		device.setDisplayMode(newMode);
        pack();

		setVisible(true);
	}
	
	public void destroier(IniciarJogo jogo) {
		jogo = null;
		System.gc();
	}
	
}
