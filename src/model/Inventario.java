package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Inicializa;
import view.InvStatus;
import view.Tela;

public class Inventario extends Tela{
	Inicializa init;
	private InvStatus inv;
	public Inventario(int largura, int altura, Inicializa init) {
		super("",largura,altura,init);
		this.init = init;
		inv = new InvStatus(init.getLampiao(), init);


	}

	@Override
	public void init() {
		
	}

	@Override
	public void gameUpdate() {
		
		inv.draw(g);
		
	}

	@Override
	public void gameRender() {
		inv.renderizar();
	}
	


}
