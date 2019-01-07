package view;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import controller.Inicializa;
import model.Sprite;

public abstract class Tela extends JPanel implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private String titulo;
	private int largura;
	private int altura;
	private Thread thread;
	private boolean running;
	private BufferedImage image;
	protected Graphics2D g;
	private int FPS = 30;
	@SuppressWarnings("unused")
	private Inicializa init;
	private Camera camera;	
	
	
	@SuppressWarnings("unused")
	private double averageFPS;

	
	
	public Tela(String titulo, int largura, int altura,Inicializa init) {
		super();
		this.titulo = titulo;
		this.largura = largura;
		this.altura = altura;
		this.init = init;
		setBounds(0, 0, largura, altura);
		setFPS(init.getFPS()+30);
		setPreferredSize(new Dimension(largura,altura));
		setSize(largura,altura);
		setLayout(null);
		setFocusable(true);
		requestFocus();
		init();
	}


	public void addNotify() {
		super.addNotify();

		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}

	public abstract void init();

	
	@Override
	public void run() {
		running = true;

		image = new BufferedImage(largura,altura, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();

		long startTime;
		long URDTimeMillis;
		long waitTime;
		long totalTime = 0;

		int frameCount = 0;
		int maxFrameCount = 30;

		long tragetTime = 1000 / FPS;

		// Looping do Jogo
		while (running) {

			startTime = System.nanoTime();

			gameUpdate();
			gameRender();
			gameDraw();

			URDTimeMillis = (System.nanoTime() - startTime) / 1000000;
			waitTime = tragetTime - URDTimeMillis;

			try {
				Thread.sleep(waitTime);
			} catch (Exception e) {
			}

			totalTime += System.nanoTime() - startTime;
			frameCount++;

			if (frameCount == maxFrameCount) {
				averageFPS = 1000.0 / ((totalTime / frameCount) / 1000000);
				frameCount = 0;
				totalTime = 0;
			}
		}
		
		gameDraw();
	}

	public abstract void gameUpdate();
	
	public abstract void gameRender();

	private void gameDraw() {
		Graphics2D g2 = (Graphics2D) this.getGraphics();

		try{
			g2.drawImage(image, 0, 0, null);
			g2.dispose();
		}catch (NullPointerException e) {
			
		}

	}
	
	public void destroier(Tela tela){
		if(!init.getInimigos().isEmpty()) {
			for (Sprite g : init.getInimigos()) {
				System.out.println("PEgou o :"+ g);
				g.destroier(g);
			}
		}
		running = false;
		thread.stop();
		tela = null;
		System.gc();
	}
	
	public Inicializa getInit() {
		return init;
	}
	
	public Camera getCamera() {
		return camera;
	}
	
	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public int getFPS() {
		return FPS;
	}


	public void setFPS(int fPS) {
		FPS = fPS;
	}


	public boolean isColidindo(Sprite player) {
		for(Rectangle entidade : init.getCamadasF1().get(1).montarColisao()) // camada de objetos
			if(player.getBounds().intersects(entidade)) {

				return true;				
			}		
		return false;		
	}

	public boolean isTopo(Sprite player) {

		for(Rectangle entidade : init.getCamadasF1().get(1).montarColisao()) // camada de objetos
			if(player.getBounds().intersects(entidade)) {
				if(entidade.getMaxY()>=player.getBounds().getMinY()) {
					return true;	
				}				
			}		

		return false;		
	}

	public boolean isCantoDireito(Sprite player) {

		for(Rectangle entidade : init.getCamadasF1().get(1).montarColisao()) // camada de objetos
			if(player.getBounds().intersects(entidade)) {
				if(entidade.getMinX()-10<=player.getBounds().getBounds().getMaxX()) {					
					return true;
					
				}							
			}		
		return false;		
	}

	public boolean isCantoEsquerdo(Sprite player) {

		for(Rectangle entidade : init.getCamadasF1().get(1).montarColisao())
			if(player.getBounds().intersects(entidade)) {
				if(entidade.getMaxX()+10>=player.getBounds().getBounds().getMinX()) {						
					return true;	
				}							
			}		
		return false;		
	}
	
	public boolean isPulo(Sprite player) {

		for(Rectangle entidade : init.getCamadasF1().get(2).montarPulo())
			if(player.getBounds().intersects(entidade)) {
				if(entidade.getMaxX()+10>=player.getBounds().getBounds().getMinX()) {
					return true;	
				}							
			}		
		return false;		
	}
}
