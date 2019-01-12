package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.sun.org.apache.xml.internal.security.Init;

import model.Lampiao;
import model.Maria;
import model.Sprite;
import model.Status;
import model.TileMap;

public class Camera {
	private ArrayList<TileMap> camadas;
	private Lampiao lampiao;
	private int x,y,xDialog,yDialog;
	private int segundos = 5;
	private ArrayList<Sprite> inimigos;
	private BufferedImage tela;
	private Graphics g;
	public Camera(Lampiao lampiao,ArrayList<Sprite> inimigos,ArrayList<TileMap> camadas) {
		this.lampiao = lampiao;
		this.inimigos = inimigos;
		this.camadas = camadas;
		tela = new BufferedImage(camadas.get(0).getLarguraTela(), camadas.get(0).getAlturaTela(), BufferedImage.TYPE_4BYTE_ABGR);
		g = tela.getGraphics();
		xDialog=(lampiao.getFase().getInit().getALTURA()/2);


	}
	
	
	public void renderizar() {
		for(TileMap t : this.camadas) {
			this.g.drawImage(t.getMapa(), 0, 0, null);
		}
		lampiao.draw(this.g);
		
		lampiao.getFase().getInit().getMaria().draw(g);
		printarMorreu();
		
		for(Sprite v : inimigos) {
			if(v.getVida()>=0) {
				v.draw(this.g);
				
			}
		}
		
	}
	
	public void draw(Graphics g) {
		if(lampiao.getX()>lampiao.getFase().getInit().getLARGURA()/2) 
			if(lampiao.getX()<(camadas.get(1).getLarguraTela()-((lampiao.getFase().getInit().getLARGURA()/2)))) {
				x=lampiao.getX()-(lampiao.getFase().getInit().getLARGURA()/2);
				xDialog=lampiao.getX()-(lampiao.getLarguraPersonagem());
			}
		
		g.drawImage(tela, -x, -y, null);	

	}

	private void printarMorreu() {
		if(lampiao.getVida()<=0 && segundos > 0) {
			g.drawImage(lampiao.getFase().getInit().getMorreu(1).getImage(), xDialog, lampiao.getFase().getInit().getALTURA()/3, null);
			try {
				Font font = Font.createFont(Font.PLAIN,new File("Arquivos\\Fonts\\xilosa.ttf")).deriveFont(40f);
				g.setColor(Color.BLACK);
				g.setFont(font); 
			} catch (FontFormatException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}   
			g.drawString(segundos+"", xDialog+lampiao.getFase().getInit().getMorreu(1).getIconWidth()/2-15, lampiao.getFase().getInit().getALTURA()/3+lampiao.getFase().getInit().getMorreu(1).getIconHeight()/2+30);

			try {
				Thread.sleep(1400);
				segundos-=1;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(segundos==0){
			lampiao.getFase().getInit().retornarInicio();
			segundos = 5;
		}
		if(lampiao.getFase().getInit().getMaria().getVida()<=0 && segundos > 0) {
			g.drawImage(lampiao.getFase().getInit().getMorreu(2).getImage(), xDialog, lampiao.getFase().getInit().getALTURA()/3, null);
			try {
				Font font = Font.createFont(Font.PLAIN,new File("Arquivos\\Fonts\\xilosa.ttf")).deriveFont(40f);
				g.setColor(Color.BLACK);
				g.setFont(font); 
			} catch (FontFormatException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}   
			g.drawString(segundos+"", xDialog+lampiao.getFase().getInit().getMorreu(1).getIconWidth()/2-15, lampiao.getFase().getInit().getALTURA()/3+lampiao.getFase().getInit().getMorreu(1).getIconHeight()/2+30);

			try {
				Thread.sleep(1400);
				segundos-=1;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(segundos==0){
			lampiao.getFase().getInit().retornarInicio();
			segundos = 5;
		}
	}
	
	public ArrayList<Sprite> getInimigos() {
		return inimigos;
	}


	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}


	public void setX(int x) {
		this.x = x;
	}


	public Graphics getGraphics() {
		return g;
	}	
	
	
	
}
