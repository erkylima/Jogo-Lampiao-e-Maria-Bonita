package view;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import controller.Inicializa;
import controller.Movimento;
import model.Metralha;
import model.Sprite;
import model.TileMap;
import model.Volante;


public class Fase1 extends JFrame{

	public static int FPS = 30;

	private Inicializa init;
	private Camera camera;	


	public void desenharGraficos() {
		camera.renderizar();

		
	}

	public void atualizar() {
		Graphics2D g = (Graphics2D) getGraphics();
		camera.draw(g);
	}

	public void start() {
		while (true) {
			desenharGraficos();
			atualizar();
			try {
				Thread.sleep(1000/FPS);
			} catch (Exception e) {
				System.out.println("Thread interrompida!");
			}
		}
	}

	public Fase1(String titulo, Inicializa init) {
		super(titulo);
		this.init = init;
//		this.init.getLampiao().setFase(this);
		
		setSize(this.init.getLARGURA(),this.init.getALTURA());
		setResizable(false);
		setLayout(null);
		setUndecorated(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		

//		Movimento m = new Movimento(this.init.getLampiao(),this);
//		addKeyListener(m);
		
		camera = new Camera(this.init.getLampiao(),this.init.getInimigosF1(),this.init.getCamadasF1());
		

		setVisible(true);
	}

	public boolean isColidindo(Sprite player) {
		for(Rectangle entidade : init.getTileSerra().montarColisao()) // camada de objetos
			if(player.getBounds().intersects(entidade)) {

				return true;				
			}		
		return false;		
	}

	public boolean isTopo(Sprite player) {

		for(Rectangle entidade : init.getTileSerra().montarColisao()) // camada de objetos
			if(player.getBounds().intersects(entidade)) {
				if(entidade.getMaxY()>=player.getBounds().getMinY()) {
					return true;	
				}				
			}		

		return false;		
	}

	public boolean isCantoDireito(Sprite player) {

		for(Rectangle entidade : init.getTileSerra().montarColisao()) // camada de objetos
			if(player.getBounds().intersects(entidade)) {
				if(entidade.getMinX()-10<=player.getBounds().getBounds().getMaxX()) {					
					return true;
					
				}							
			}		
		return false;		
	}

	public boolean isCantoEsquerdo(Sprite player) {

		for(Rectangle entidade : init.getTileSerra().montarColisao())
			if(player.getBounds().intersects(entidade)) {
				if(entidade.getMaxX()+10>=player.getBounds().getBounds().getMinX()) {						
					return true;	
				}							
			}		
		return false;		
	}
	
	public boolean isPulo(Sprite player) {

		for(Rectangle entidade : init.getEnteiteSerra().montarPulo())
			if(player.getBounds().intersects(entidade)) {
				if(entidade.getMaxX()+10>=player.getBounds().getBounds().getMinX()) {
					return true;	
				}							
			}		
		return false;		
	}
	

	public Camera getCamera() {
		return camera;
	}

	public Inicializa getInit() {
		return init;
	}

	
	
}

