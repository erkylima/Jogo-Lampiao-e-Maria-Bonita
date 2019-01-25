package model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import controller.Inicializa;

public class FimCam {
	private int x,y;

	private BufferedImage tela;
	private Graphics g;
	private Image icon;
	final float FACTOR  = 4f;

//	private Status status;
	Inicializa ini;
	public FimCam(Lampiao lampiao,Inicializa ini) {
		this.ini = ini;
		tela = new BufferedImage(1024, 640, BufferedImage.TYPE_4BYTE_ABGR);
		g = tela.getGraphics();
		try {
			icon = ImageIO.read(new File("Arquivos/Imagens/fim.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void renderizar() {

		Image img = icon.getScaledInstance(icon.getWidth(null), icon.getHeight(null), Image.SCALE_SMOOTH);

		g.drawImage(img, 0, 0, null);
		

		
		
	}
	
	public void draw(Graphics g) {

		g.drawImage(tela, x, y, null);	

	}





	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}


	public Graphics getGraphics() {
		return g;
	}
}