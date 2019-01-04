package app;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.DisplayMode;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import controller.Inicializa;
import model.Inventario;
import view.F1;
     
public class App {
	@SuppressWarnings("unused")
	private static F1 gui;

	public static void main(String[] args) {
		Inicializa init = new Inicializa();
		
		gui = new F1("Lampião e Maria Bonita",1024,640,init);
		init.getLampiao().setFase(gui);
		
		JFrame frame=new JFrame();
		init.setJogo(frame);
		frame.setSize(init.getLARGURA(),init.getALTURA());
		frame.setResizable(false);
		frame.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		BorderLayout s = new BorderLayout();

		frame.setLayout(s);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		Inventario i = new Inventario(1024,118,init);
		
		frame.add(i,BorderLayout.PAGE_END);

		frame.add(init.getLampiao().getFase(),BorderLayout.PAGE_START);
		

		
		
		

		
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
        