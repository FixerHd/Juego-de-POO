package comandos.juego;
import java.awt.Color;

import comandos.estructura.*;
import comandos.personaje.Comando;
import comandos.personaje.Comandoalacran;
import comandos.personaje.Comandoalfa;
import comandos.personaje.Comandohalcon;
import comandos.vista.Alarma;
import comandos.vista.Pantalla;

import java.util.ArrayList;

public class Programa {
	public static final int alto = 9;
	public static final int ancho = 5;
	
	private static final String TECLA_ARRIBA = "i";
	private static final String TECLA_ABAJO = "k";
	private static final String TECLA_IZQDA = "j";
	private static final String TECLA_DER = "l";
	private static final String TECLA_FIN = "x";
	private static final String DISPARA = "d";
	
	public static void main(String[] args) {
		
		Escenario esce = new Escenario("amai", ancho, alto); 
		
		Pantalla pantalla = new Pantalla(ancho, alto, 75, Color.BLACK);
		
		esce.añadircomando(new Comando("imagenes/comando.png"));
		esce.añadircomando(new Comando("imagenes/comando.png"));
		esce.añadircomando(new Comandoalfa("imagenes/comando-alfa.png"));
		esce.añadircomando(new Comandoalacran("imagenes/comando-alacran.png"));
		esce.añadircomando(new Comandohalcon("imagenes/comando-halcon.png"));
		
		ArrayList <Imagen> imagenes = esce.getImagenes();
		for (Imagen imagen : imagenes) {
				pantalla.addImagen(imagen.GetX(), imagen.GetY(), imagen.GetRuta());
			
		}
		
		esce.iniciarpartida();
		
		pantalla.dibujar();
		
		boolean fin = false;
		
		while(!fin) {
			
			
			esce.actualizar();
			
			if(pantalla.hayTecla()) {
				String tecla = pantalla.leerTecla();
				switch (tecla) {
				case TECLA_ARRIBA: 
					if(esce.getobjetivo().getY()!=alto-1) {
					esce.getobjetivo().desplazar_(Direccion.ARRIBA);
					}
									break;
				case TECLA_ABAJO: 
					if(esce.getobjetivo().getY()!=0) {
						esce.getobjetivo().desplazar_(Direccion.ABAJO);
						}
									break;
				case TECLA_IZQDA: 
					if(esce.getobjetivo().getX()!=0) {
						esce.getobjetivo().desplazar_(Direccion.IZQUIERDA);
						}
									break;
				case TECLA_DER: 
					if(esce.getobjetivo().getX()!=ancho-1) {
						esce.getobjetivo().desplazar_(Direccion.DERECHA);
						}
									break;
				case DISPARA: esce.disparo(esce.getobjetivo());
									break;					
				case TECLA_FIN: fin = true;
									break;
				}
				
				
					}
				
			pantalla.resetear();
	imagenes = esce.getImagenes();
	for (Imagen imagen : imagenes) {

			pantalla.addImagen(imagen.GetX(), imagen.GetY(), imagen.GetRuta());
		
	}
			pantalla.dibujar();
			
			Alarma.dormir(125);
			
			
			pantalla.setBarraEstado("Tiempo: " + esce.segundos() + " segundos");
			
			if(esce.getstate()!=Estado.ENJUEGO) fin=true;
			
		}
	}
}
