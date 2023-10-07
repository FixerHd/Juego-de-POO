package comandos.pruebas;
import comandos.estructura.*;


public class Pruebaposicion {

	public static void main(String[] args) {
		
		for(Direccion dire :Direccion.values()) {
			Posicion posicion1 = new Posicion();
			 posicion1.desplazar_(dire);
			 System.out.printf("(%d, %d)", posicion1.getX(), posicion1.getY());
		}

	}

}
