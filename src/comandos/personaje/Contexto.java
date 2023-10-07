package comandos.personaje;
import java.util.List;

import comandos.estructura.*;

public class Contexto {
	
	private boolean estaenventana;
	private List<Direccion> direcciones;
	
	public Contexto(boolean ventanal, List<Direccion> dir) {
		this.estaenventana = ventanal;
		this.direcciones = dir;
	}
	
	public boolean getventana() {
		return estaenventana;
	}
	
	public List<Direccion> getdirecc() {
		return direcciones;
	}
	

}
