package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

import controller.Inicializa;
import model.FimCam;

public class FimMultiplayer extends Tela{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FimCam inv;
	private Inicializa init ;
	private int count;
	private String msg;
	public FimMultiplayer(int largura, int altura, Inicializa init) {
		super("",largura,altura,init);
		getInit().getLampiao().setFase(this);
		this.init = init;
		inv = new FimCam(init.getLampiao(), init);
		getSom().venceu();

	}

	@Override
	public void init() {
		getInit().getInventario().getInv().setRunning(false);
		if(getInit().getInventario().getInv().getSegundos() < getInit().getConfig().getRecord()) {
			msg = "O novo recorde multiplayer é: ";
		}else {
			msg = "Seu tempo foi em multiplayer foi: ";
		}
	}

	@Override
	public void gameUpdate() {
		inv.draw(g);

	}

	@Override
	public void gameRender() {
		Font font = null;
		try {
			font = Font.createFont(Font.PLAIN,new File("Arquivos\\Fonts\\xilosa.ttf")).deriveFont(27f);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.setColor(Color.BLACK);
		g.setFont(font); 
		g.drawString(msg+getInit().getInventario().getInv().getSegundos()+" segundos", 250, 420);
		if(!(getInit().getInventario().getInv().getSegundos() <= getInit().getConfig().getRecord())) {
			g.drawString("O recorde atual é "+getInit().getConfig().getRecord()+" segundos", 320, 445);
		}
		if(getInit().getInventario().getInv().getSegundos() < getInit().getConfig().getRecord()) {
			getInit().getConfig().setRecord(getInit().getInventario().getInv().getSegundos());
			getInit().gerarConfigXlm(getInit().getConfig(), true);
		}
		inv.renderizar();
		if(count<5) {
			count++;

			System.out.println(count);
		}else if (count==5){
			count++;
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			destroier(this);
			getInit().getMaria().destroier(getInit().getMaria());

			init.getJogo().remove(init.getJogo());
			init.getJogo().dispose();
			Inicializa init = new Inicializa();		
			new IniciarJogo("Lampião e Maria Bonita", init);
		}

	}

	@Override
	public void iniciaInimigos(boolean respawna) {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroier(Tela tela) {
		if(getInit().getInventario() != null && getInit().getInventario().getInv().isRunning()) {
			getInit().getInventario().getInv().setRunning(false);
		}

		
		super.destroier(tela);
	}
}
