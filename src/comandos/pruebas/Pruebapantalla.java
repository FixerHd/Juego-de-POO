package comandos.pruebas;
import comandos.estructura.*;
import comandos.vista.Pantalla;
import java.awt.Color;



public class Pruebapantalla {
	
	private static final String TECLA_ARRIBA = "i";
	private static final String TECLA_ABAJO = "k";
	private static final String TECLA_IZQDA = "j";
	private static final String TECLA_DER = "l";
	private static final String TECLA_FIN = "x";

	public static void main(String[] args) {
		Pantalla pantalla = new Pantalla(5, 5, 75, Color.BLUE);
		
		Posicion objetivo = new Posicion(0, 0);
		
		pantalla.addImagen(objetivo.getX(), objetivo.getY(), "imagenes/objetivo.png");
		
		pantalla.dibujar();
		
		boolean fin = false;
		
		while(!fin) {
			pantalla.resetear();
			
			
			if(pantalla.hayTecla()) {
				String tecla = pantalla.leerTecla();
				
				
				switch (tecla) {
				case TECLA_ARRIBA: objetivo.desplazar_(Direccion.ARRIBA);
									break;
				case TECLA_ABAJO: objetivo.desplazar_(Direccion.ARRIBA);
				break;
				case TECLA_IZQDA: objetivo.desplazar_(Direccion.ARRIBA);
				break;
				case TECLA_DER: objetivo.desplazar_(Direccion.ARRIBA);
				break;
				case TECLA_FIN: fin = true;
				break;
				}
			}
			
			
		}
	}

}
