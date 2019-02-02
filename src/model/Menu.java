package model;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import controller.Inicializa;
import controller.TratamentoException;
import view.F1;
import view.MainMenu;
import view.Multiplayer;

public class Menu {
	private Inicializa init;
	private BufferedImage tela;
	private Graphics g;
	
	private double xLampiaoVolante = -60;
	private ButtonsMainMenu jogar,multiplayer,config,sobre,fps,nivel;
	private int selected;
	private boolean sobreAtivo,configAtivo = false;
	private F1 fase1;
	private MainMenu main;
	private int sobrePage;
	private Multiplayer multi;
	
	public Menu(int largura,int altura,Inicializa init, MainMenu main) {
		this.init = init;
		this.main = main;
		
		tela = new BufferedImage(largura, altura, BufferedImage.TYPE_4BYTE_ABGR);
		g = tela.getGraphics();
		try {
			jogar = new ButtonsMainMenu(4, 8, 1, 400, 180, "Arquivos/Imagens/buttons.png", 0);
			multiplayer = new ButtonsMainMenu(1, 8, 1, 400, 250, "Arquivos/Imagens/buttons.png", 0);
			config = new ButtonsMainMenu(2, 8, 1, 400, 320, "Arquivos/Imagens/buttons.png", 0);
			sobre = new ButtonsMainMenu(3, 8, 1, 400, 390, "Arquivos/Imagens/buttons.png", 0);
			fps = new ButtonsMainMenu(0, 4, 1, 480, 330, "Arquivos/Imagens/configPallet.png", 0);
			nivel = new ButtonsMainMenu(0, 4, 1, 480, 440, "Arquivos/Imagens/configPallet.png", 0);
		} catch (TratamentoException e) {
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
		g.drawImage(init.getPassaro2().getImage(),880,30,null);
		g.drawImage(init.getMariaImg().getImage(),840,440,null);
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
		}
		if(sobreAtivo) {
			g.drawImage(init.getSobreimg(sobrePage).getImage(), 161, 134, null);
		}
		
		if(configAtivo) {
			switch (init.getConfig().getFPS()) {
			case 30:
				fps.setAparencia(0);
				break;
			case 35:
				fps.setAparencia(1);
				break;
			case 40:
				fps.setAparencia(2);
				break;
			case 45:
				fps.setAparencia(3);
				break;
			}
			switch ((int)init.getConfig().getNivel()) {
			case 0:
				nivel.setAparencia(0);
				break;
			case 10:
				nivel.setAparencia(1);
				break;
			case 20:
				nivel.setAparencia(2);
				break;
			case 30:
				nivel.setAparencia(3);
				break;
			}
			g.drawImage(init.getConfigimg().getImage(), 161, 134, null);
			fps.draw(g);
			nivel.draw(g);
		}
	}
	
	public int getSelected() {
		return selected;
	}


	public void setSelected(int selected) {
		if(!sobreAtivo) {
			this.selected = selected;
		}else {
			sobreAtivo = false;
		}
		if(!configAtivo) {
			this.selected = selected;
		}else {
			configAtivo = false;
		}
	}


	public int getSobrePage() {
		return sobrePage;
	}


	public void setSobrePage(int sobrePage) {
		this.sobrePage = sobrePage;
	}


	public void draw(Graphics g) {
		
		g.drawImage(tela, 0, 0, null);	

	}
	
	public void iniciarJogo() {
		fase1 = new F1("Lampião e Maria Bonita",1024,640,init);
		init.getLampiao().setFase(fase1);
		main.setVisible(false);
		init.getJogo().remove(main);
		main.destroier(main);
		init.getJogo().add(init.getInventario(),BorderLayout.PAGE_END);

		init.getJogo().add(init.getLampiao().getFase(),BorderLayout.PAGE_START);	
		init.getLampiao().getFase().requestFocus();

	}
	
	public void iniciaMultiplayer() {
		multi = new Multiplayer("Lampião e Maria Bonita",1024,640,init);
		init.getLampiao().setFase(multi);
		main.setVisible(false);
		init.getJogo().remove(main);
		main.destroier(main);
		init.getJogo().add(init.getInventario(),BorderLayout.PAGE_END);

		init.getJogo().add(init.getLampiao().getFase(),BorderLayout.PAGE_START);	
		init.getLampiao().getFase().requestFocus();

	}
	public void configFPS() {
		if(init.getConfig().getFPS()<45) {
			init.getConfig().setFPS(init.getConfig().getFPS()+5);
		}
		else {
			init.getConfig().setFPS(30);
		}
		init.gerarConfigXlm(init.getConfig(),true);

	}
	public void configNivel() {
		if(init.getConfig().getNivel()<30) {
			init.getConfig().setNivel(init.getConfig().getNivel()+10);
		}
		else {
			init.getConfig().setNivel(0);
		}
		init.gerarConfigXlm(init.getConfig(),true);

	}
	
	public boolean sobreToggle() {
		if(sobreAtivo) {
			sobreAtivo = false;
		}else {
			sobreAtivo = true;
		}
		return sobreAtivo;
	}
	
	public boolean configToggle() {
		if(configAtivo) {
			configAtivo = false;
		}else {
			configAtivo = true;
		}
		return configAtivo;
	}
	public Graphics getGraphics() {
		return g;
	}	
	
	
	
}
