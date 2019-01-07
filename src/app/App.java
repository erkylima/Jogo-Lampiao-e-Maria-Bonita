package app;

import java.io.IOException;

import controller.Inicializa;
import view.IniciarJogo;
     
public class App {

	public static void main(String[] args) {
		Inicializa init = new Inicializa();		
		new IniciarJogo("Lampião e Maria Bonita", init);

	}       
	
	
}
        