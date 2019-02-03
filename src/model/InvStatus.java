package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import controller.Inicializa;

public class InvStatus implements Runnable{
	private int x,y;

	private BufferedImage tela;
	private Graphics g;
	private Image icon;
	final float FACTOR  = 4f;
	private Thread t;
//	private Status status;
	private Inicializa ini; 
	private boolean running = true;
	private int segundos = 0;
	
	public InvStatus(Inicializa ini) {

		this.ini = ini;
		tela = new BufferedImage(1024, 118, BufferedImage.TYPE_4BYTE_ABGR);
		g = tela.getGraphics();
		try {
			icon = ImageIO.read(new File("Arquivos/Imagens/inventario.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t = new Thread(this);		
	}
	
	
	public void renderizar() {
		int scaleX = (int) (icon.getWidth(null));
		int scaleY = (int) (icon.getHeight(null));
		Image img = icon.getScaledInstance(scaleX, scaleY, Image.SCALE_SMOOTH);
		g.drawImage(img, 0, 0, null);
		ini.getStatus().setVida(ini.getLampiao().getChance());
		ini.getStatus().draw(this.g);
		ini.getFomeStatus().setVida((int)ini.getLampiao().getFome());
		ini.getFomeStatus().setX(ini.getStatus().getLarguraPersonagem()+30);
		ini.getFomeStatus().draw(this.g);
		ini.getSedeStatus().setVida(ini.getLampiao().getSede());
		ini.getSedeStatus().setX(ini.getStatus().getLarguraPersonagem() + ini.getFomeStatus().getLarguraPersonagem()+30);
		ini.getSedeStatus().draw(this.g);
		Font font = null;
		try {
			font = Font.createFont(Font.PLAIN,new File("Arquivos\\Fonts\\xilosa.ttf")).deriveFont(36f);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.setColor(Color.BLACK);
		g.setFont(font); 
		this.g.drawString("Tempo: " + segundos+ " s", 750, scaleY/3);
		if(ini.getLampiao().isPistola()) {
			
		}else {
			
		}
	}
	
	public void draw(Graphics g) {

		
		g.drawImage(tela, x, y, null);	

	}
	
	public Thread getThread() {
		return t;
	}


	public void setRunning(boolean running) {
		this.running= running;
	}
	public boolean isRunning() {
		return running;
	}

	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}

	public int getSegundos() {
		return segundos;
	}


	public Graphics getGraphics() {
		return g;
	}


	@Override
	public void run() {
		while(running) {
			segundos+=1;
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}	
}