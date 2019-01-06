package app;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;

import javax.swing.JFrame;

import controller.Inicializa;
import view.MainMenu;
     
public class App {
	@SuppressWarnings("unused")

	public static void main(String[] args) {
		Inicializa init = new Inicializa();		
		
		JFrame frame=new JFrame("Lampiao e Maria Bonita");
		init.setJogo(frame);
		frame.setSize(init.getLARGURA(),init.getALTURA());
		frame.setResizable(false);
		frame.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		BorderLayout s = new BorderLayout();

		frame.setLayout(s);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		MainMenu menu = new MainMenu("Lampiao e Maria Bonita",1024,768,init);
		frame.add(menu);
		
		
		

		
//		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
//		GraphicsDevice device = env.getScreenDevices()[0];
//
//		DisplayMode oldMode = device.getDisplayMode();
//		DisplayMode newMode = new DisplayMode(1024,768,oldMode.getBitDepth(),oldMode.getRefreshRate());
//		device.setFullScreenWindow(frame);
//		device.setDisplayMode(newMode);
		frame.setVisible(true);
	}       
	
	
}
        