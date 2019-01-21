package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import controller.Inicializa;
import model.Lampiao;
import model.Sprite;
import model.TileMap;

public class InvStatus {
	private Lampiao lampiao;
	private int x,y;

	private BufferedImage tela;
	private Graphics g;
	private Image icon;
	final float FACTOR  = 4f;

//	private Status status;
	Inicializa ini;
	public InvStatus(Lampiao lampiao,Inicializa ini) {
		this.lampiao = lampiao;
		this.ini = ini;
		tela = new BufferedImage(1024, 118, BufferedImage.TYPE_4BYTE_ABGR);
		g = tela.getGraphics();
		try {
			icon = ImageIO.read(new File("Arquivos/Imagens/inventario.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void renderizar() {
		int scaleX = (int) (icon.getWidth(null) * FACTOR);
		int scaleY = (int) (icon.getHeight(null) * FACTOR);
		Image img = icon.getScaledInstance(scaleX, scaleY, Image.SCALE_SMOOTH);

		g.drawImage(img, 0, 0, null);
		ini.getStatus().draw(this.g);

		
		
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