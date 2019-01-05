package view;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import controller.Inicializa;
import model.ButtonsMainMenu;
import model.Inventario;
import model.Lampiao;
import model.Sprite;
import model.Status;
import model.TileMap;

public class Menu {
	private Inicializa init;
	private BufferedImage tela;
	private Graphics g;
	
	private double xLampiaoVolante = -60;
	private ButtonsMainMenu jogar,multiplayer,config,sobre;
	private int selected;
	
	private F1 fase1;
	private MainMenu main;

	public Menu(int largura,int altura,Inicializa init, MainMenu main) {
		this.init = init;
		this.main = main;
		
		tela = new BufferedImage(largura, altura, BufferedImage.TYPE_4BYTE_ABGR);
		g = tela.getGraphics();
		try {
			jogar = new ButtonsMainMenu(4, 8, 1, 400, 180, "Arquivos/buttons.png", 0);
			multiplayer = new ButtonsMainMenu(1, 8, 1, 400, 250, "Arquivos/buttons.png", 0);
			config = new ButtonsMainMenu(2, 8, 1, 400, 320, "Arquivos/buttons.png", 0);
			sobre = new ButtonsMainMenu(3, 8, 1, 400, 390, "Arquivos/buttons.png", 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void renderizar() {
	
		g.drawImage(init.getBackgroundMain().getImage(),0,0,null);
		g.drawImage(init.getLampiaoVolante().getImage(),(int)xLampiaoVolante,410,null);
		while (xLampiaoVolante <60) {
			xLampiaoVolante++;
			try {
				Thread.sleep(1000/400);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		g.drawImage(init.getPassaro1().getImage(),200,350,null);
		g.drawImage(init.getPassaro2().getImage(),880,410,null);
		g.drawImage(init.getMaria().getImage(),880,10,null);
		if(selected!=0 && jogar.getAparencia()!=0) {
			jogar.setAparencia(0);
		}
		if(selected !=1 && multiplayer.getAparencia()!=1) {
			multiplayer.setAparencia(1);
		}
		if(selected != 2 && config.getAparencia()!=2) {
			config.setAparencia(2);
		}
		if(selected != 3 && sobre.getAparencia()!=3) {
			sobre.setAparencia(3);
		}
		
		jogar.draw(g);
		multiplayer.draw(g);
		config.draw(g);
		sobre.draw(g);
		
		switch (selected) {
		case 0:
			jogar.setAparencia(4);
			break;
		case 1:
			multiplayer.setAparencia(5);
			break;
		case 2:
			config.setAparencia(6);
			break;
		case 3:
			sobre.setAparencia(7);
			break;
		default:
			break;
		}
	}
	
	public int getSelected() {
		return selected;
	}


	public void setSelected(int selected) {
		this.selected = selected;
	}


	public void draw(Graphics g) {
		
		g.drawImage(tela, 0, 0, null);	

	}
	
	public void iniciarJogo() {
		fase1 = new F1("Lampião e Maria Bonita",1024,640,init);
		init.getLampiao().setFase(fase1);
		main.setVisible(false);
		init.getJogo().remove(main);
		
		init.getJogo().add(init.getInventario(),BorderLayout.PAGE_END);

		init.getJogo().add(init.getLampiao().getFase(),BorderLayout.PAGE_START);	
		init.getLampiao().getFase().requestFocus();

	}


	public Graphics getGraphics() {
		return g;
	}	
	
	
	
}
