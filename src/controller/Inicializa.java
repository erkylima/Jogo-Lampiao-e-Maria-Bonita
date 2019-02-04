package controller;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import model.Config;
import model.Lampiao;
import model.Maria;
import model.Sprite;
import model.Status;
import model.TileMap;
import view.Fim;
import view.IniciarJogo;
import view.Inventario;

public class Inicializa {
	private Lampiao lampiao,mariamultiplayer;
	private Maria maria;
	private TileMap tile;
	private TileMap bg;
	private TileMap enteite;
	private JFrame jogo;
	private ImageIcon backgroundMain,lampiaoVolante,passaro1,passaro2,mariaimg,sobreimg,configimg,morreu, voltarInicio;
	private int xInicial = 40;
	private int yInicial = 450;
	private int vidaInicial = 120;
	private ArrayList<TileMap> camadas = new ArrayList<TileMap>();
	private ArrayList<Sprite> inimigos = new ArrayList<Sprite>();
	private Status status;
	private Status fome,sede;
	private int LARGURA = 1024;
	private int ALTURA = 768;
	private Inventario inventario;
	Config conf = new Config();

	public Inicializa() {
		try {
			lampiao = new Lampiao(15, 96, 1, getxInicial(), getyInicial(),"Arquivos/Imagens/lampiaosprite.png",null,getVidaInicial(),120,120,120);
			List<Integer> teclasPlayer1 = new ArrayList<>();
			teclasPlayer1.add(KeyEvent.VK_A );
			teclasPlayer1.add(KeyEvent.VK_D);
			teclasPlayer1.add(KeyEvent.VK_SPACE);
			teclasPlayer1.add(KeyEvent.VK_T);
			lampiao.setTeclas(teclasPlayer1);
			gerarConfigXlm(conf,false);
			maria = new Maria(10,28,1,7100,100,"Arquivos/Imagens/mariasprite.png",lampiao,this,lampiao.getVida()/2);
			status = new Status(0, 14, 1, 20, 5, "Arquivos/Imagens/status.png", lampiao.getChance(), lampiao);
			fome = new Status(0, 14, 1, 20, 5, "Arquivos/Imagens/fome.png", (int)lampiao.getFome(), lampiao);
			sede = new Status(0, 14, 1, 20, 5, "Arquivos/Imagens/sede.png", (int)lampiao.getSede(), lampiao);
			backgroundMain = new ImageIcon("Arquivos/Imagens/backgroundMain.jpg");
			lampiaoVolante = new ImageIcon("Arquivos/Imagens/lampiaoVolante.png");
			passaro1 = new ImageIcon("Arquivos/Imagens/passaro1.png");
			passaro2 = new ImageIcon("Arquivos/Imagens/passaro2.png");
			mariaimg = new ImageIcon("Arquivos/Imagens/maria.png");
			inventario = new Inventario(1024,118,this);
			 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void F1() {
		bg = new TileMap(8, 1, 1024, 1024, "Arquivos/Imagens/BG.png", "Arquivos/Camadas/BGSerraTile.txt");
		tile = new TileMap(224, 20, 32, 32, "Arquivos/Imagens/Tile.png", "Arquivos/Camadas/SerraTile.txt");
		enteite = new TileMap(240,24,32,32,"Arquivos/Imagens/objetos.png","Arquivos/Camadas/EnfeiteSerra.txt");
		camadas.add(bg);
		camadas.add(tile);
		camadas.add(enteite);
	}
	
	public void F2() {
		bg = new TileMap(8, 1, 1024, 1024, "Arquivos/Imagens/BGFloresta.png", "Arquivos/Camadas/BGFlorestaTile.txt");
		tile = new TileMap(224, 20, 32, 32, "Arquivos/Imagens/Tile.png", "Arquivos/Camadas/FlorestaTile.txt");
		enteite = new TileMap(224,20,32,32,"Arquivos/Imagens/objetos.png","Arquivos/Camadas/EnfeiteFloresta.txt");
		camadas.add(bg);
		camadas.add(tile);
		camadas.add(enteite);
	}
	
	public void multiplayer() {
		try {
			mariamultiplayer = new Lampiao(15, 48, 1, getxInicial(), getyInicial(),"Arquivos/Imagens/mariaspritemultiplayer.png",null,getVidaInicial(),120,120,120);
			mariamultiplayer.setPistola(false);
			List<Integer> teclasP2 = new ArrayList<>();
			teclasP2.add(KeyEvent.VK_LEFT);
			teclasP2.add(KeyEvent.VK_RIGHT);
			teclasP2.add(KeyEvent.VK_UP);
			teclasP2.add(KeyEvent.VK_P);
			mariamultiplayer.setTeclas(teclasP2);
		} catch (TratamentoException e) {
			e.printStackTrace();
		}
		bg = new TileMap(8, 1, 1024, 1024, "Arquivos/Imagens/BGFloresta.png", "Arquivos/Camadas/BGFlorestaTile.txt");
		tile = new TileMap(224, 20, 32, 32, "Arquivos/Imagens/Tile.png", "Arquivos/Camadas/FlorestaTile.txt");
		enteite = new TileMap(224,20,32,32,"Arquivos/Imagens/objetos.png","Arquivos/Camadas/EnfeiteFloresta.txt");
		camadas.add(bg);
		camadas.add(tile);
		camadas.add(enteite);
	}
	public Lampiao getLampiao() {
		return lampiao;
	}
	
	public Lampiao getMariamultiplayer() {
		return mariamultiplayer;
	}

	public Maria getMaria() {
		return maria;
	}

	public TileMap getTileSerra() {
		return tile;
	}

	public TileMap getBgSerra() {
		return bg;
	}
	
	public TileMap getEnteiteSerra() {
		return enteite;
	}

	public ArrayList<TileMap> getCamadasF1() {
		return camadas;
	}

	public ArrayList<Sprite> getInimigos() {
		return inimigos;
	}

	public Status getStatus() {
		return status;
	}

	public int getLARGURA() {
		return LARGURA;
	}

	public int getALTURA() {
		return ALTURA;
	}
	
	public ImageIcon getBackgroundMain() {
		return backgroundMain;
	}

	public ImageIcon getLampiaoVolante() {
		return lampiaoVolante;
	}
	
	public ImageIcon getMariaImg() {
		return mariaimg;
	}

	public ImageIcon getSobreimg(int img) {
		switch(img) {
		case 1:
			sobreimg = new ImageIcon("Arquivos/Imagens/sobre1.png");
			break;
		case 2:
			sobreimg = new ImageIcon("Arquivos/Imagens/sobre2.png");
			break;
		case 3:
			sobreimg = new ImageIcon("Arquivos/Imagens/sobre3.png");
			break;
		case 4:
			sobreimg = new ImageIcon("Arquivos/Imagens/sobre4.png");
		}

		return sobreimg;
	}
	
	public Status getFomeStatus() {
		return fome;
	}
	
	public Status getSedeStatus() {
		return sede;
	}

	public ImageIcon getConfigimg() {
		configimg = new ImageIcon("Arquivos/Imagens/config.png");
		return configimg;
	}

	public ImageIcon getMorreu(int personagem) {
		switch(personagem) {
		case 1:
			morreu = new ImageIcon("Arquivos/Imagens/morreu1.png");
			break;
		case 2:
			morreu = new ImageIcon("Arquivos/Imagens/morreu2.png");
			break;
		}
		return morreu;
	}
	
	public ImageIcon getVoltarInicio(int img) {
		switch(img) {
		case 1:
			voltarInicio = new ImageIcon("Arquivos/Imagens/voltarInicio1.png");
			break;
		case 2:
			voltarInicio = new ImageIcon("Arquivos/Imagens/voltarInicio2.png");
			break;
		}
		return voltarInicio;
	}

	public ImageIcon getPassaro1() {
		return passaro1;
	}

	public ImageIcon getPassaro2() {
		return passaro2;
	}

	public int getxInicial() {
		return xInicial;
	}

	public int getyInicial() {
		return yInicial;
	}

	public JFrame getJogo() {
		return jogo;
	}

	public void setJogo(JFrame jogo) {
		this.jogo = jogo;
	}

	public Config getConfig() {
		return conf;
	}

	public void setConfig(Config conf) {
		this.conf = conf;
	}

	public int getVidaInicial() {
		return vidaInicial;
	}

	public Inventario getInventario() {
		return inventario;
	}
	
	public void retornarMenu() {
		getLampiao().getFase().zerarInimigos();
		getLampiao().setAcao(0);
		getLampiao().getFase().setVisible(false);
		getInimigos().clear();
		getLampiao().getFase().getSom().destroier(getLampiao().getFase().getSom());
		getJogo().remove(getJogo());
		getLampiao().getFase().destroier(getLampiao().getFase());
		getInventario().getInv().setRunning(false);
		getLampiao().getFase().getInit().getMaria().destroier(getLampiao().getFase().getInit().getMaria());
		getLampiao().getFase().getInit().getJogo().dispose();
		getLampiao().setAcao(0);
		Inicializa init = new Inicializa();		
		new IniciarJogo("Lampião e Maria Bonita", init);	
	}
	
	public boolean retornarInicio() {
		if(getLampiao().getChance()>20) {
			getLampiao().getFase().zerarInimigos();
			getLampiao().setAcao(0);
			getLampiao().setX(getxInicial());
			getLampiao().setY(getyInicial());
			getLampiao().setPistola(true);
			if(getLampiao().getChance()>100) {
				getLampiao().setChance(getLampiao().getChance()-10);
			}else if(getLampiao().getChance()<=100) {
				System.out.println(getLampiao().getChance());
				getLampiao().setChance(0);
				getStatus().setVida(getLampiao().getChance());
				getStatus().setAparencia(13);
			}
			getLampiao().setFome(getVidaInicial()-getConfig().getNivel());
			getLampiao().setSede(getVidaInicial()-getConfig().getNivel());
			getMaria().setVida((getVidaInicial()-getConfig().getNivel())/2);
			getLampiao().getFase().getCamera().setX(0);
			getLampiao().setVida(getVidaInicial()-getConfig().getNivel());
			getLampiao().getFase().iniciaInimigos(true);
			return true;
		}else {
			getLampiao().getFase().zerarInimigos();
			getLampiao().setAcao(0);
			getLampiao().getFase().getCamera().destroier(getLampiao().getFase().getCamera());
			getLampiao().getFase().setVisible(false);
			getMaria().destroier(getMaria());
			zerarCamadas();
			getInimigos().clear();
			getLampiao().getFase().getSom().destroier(getLampiao().getFase().getSom());
			new Fim(1024,640,this);
			getJogo().remove(getLampiao().getFase());
			getLampiao().getFase().destroier(getLampiao().getFase());
			getJogo().add(getLampiao().getFase(),BorderLayout.PAGE_START);
		}
		return false;
	}
	
	private void lerXml() {
	    FileReader reader = null;
	    try {
	        reader = new FileReader("Arquivos/Configs/config.xml");
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	    XStream xStream = new XStream(new DomDriver());
	   
	    xStream.alias("Config", Config.class);
	    xStream.aliasField("FPS", Config.class, "fps");
	    xStream.aliasField("NIVEL", Config.class, "nivel");
	    xStream.aliasField("RECORD", Config.class, "record");
	    xStream.aliasField("RECORDMPLAYER", Config.class, "recordmplayer");

	    
	    Config config = (Config) xStream.fromXML(reader);
	    lampiao.setVida(lampiao.getVida()-config.getNivel());
	    lampiao.setFome(getVidaInicial()-getConfig().getNivel());
	    
	    conf = config;
	}
	
	public void gerarConfigXlm(Config conf,boolean muda) {	    //Criamos o objeto xStrem
	    XStream xStream = new XStream(new DomDriver());

	    xStream.alias("Config", Config.class);

	    xStream.aliasField("FPS", Config.class, "fps");
	    xStream.aliasField("NIVEL", Config.class, "nivel");
	    xStream.aliasField("RECORD", Config.class, "record");
	    xStream.aliasField("RECORDMPLAYER", Config.class, "recordmplayer");

	    @SuppressWarnings("unused")
		String documento = xStream.toXML(conf);
	    salvarConfig(xStream.toXML(conf), "config.xml",muda);
	}
	
	public boolean salvarConfig(String documento, String file, boolean muda) {
		File path = new File("Arquivos/Configs/" + file);
		if(path.exists() && !muda){
			lerXml();

		}else {
			try {
				PrintWriter writer = new PrintWriter(path);
				writer.println(
						"<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"no\"?>"
						);          
				writer.println(documento);
				writer.flush();
				writer.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}    
	public void zerarCamadas() {
		if(camadas.size()!=0) {
			for (@SuppressWarnings("unused") TileMap camada : camadas) {
				camada = null;
			}
			camadas.clear();
		}
	}
}
