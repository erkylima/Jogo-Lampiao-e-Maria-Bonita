package app;

import controller.Inicializa;
import view.Fase1;
     
public class App {
	public static void main(String[] args) {
		Inicializa init = new Inicializa();
		Fase1 gui = new Fase1("Lampião e Maria Bonita",init);
		gui.start();             
	}       
	
	
}
        