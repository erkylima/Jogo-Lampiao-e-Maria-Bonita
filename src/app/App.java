package app;

import controller.Inicializa;
import model.Config;
import view.IniciarJogo;
     
public class App {

	public static void main(String[] args) {
		
		Inicializa init = new Inicializa();
		
		new IniciarJogo("Lampi�o e Maria Bonita", init);

	}       
	
	
}
        