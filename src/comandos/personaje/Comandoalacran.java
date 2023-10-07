package comandos.personaje;

import java.util.Random;

import comandos.estructura.Direccion;
import comandos.estructura.Posicion;

public class Comandoalacran extends Comando {
	public Comandoalacran(String rut) {
		super(rut);
		// TODO Auto-generated constructor stub
	}
	
	private Direccion decidirmovimientoalacran(Contexto c) {
		for(int i=0; i<4; i++) {
	    Random r = new Random();
	    if(c.getdirecc().size()==0) return null;
	    int index = r.nextInt(c.getdirecc().size());
	    Posicion aux = new Posicion(posactual);
	    aux.desplazar_(c.getdirecc().get(index));
	    if(!conjunto.contains(aux)) {
	    	return c.getdirecc().get(index);
	    }
		}
		Random r = new Random();
		int index = r.nextInt(c.getdirecc().size());
	    return c.getdirecc().get(index);		
	}
	
	public Decision tomadecision(Contexto c) {
		if(c.getventana()==true) {
			ventanasconoc.add(posactual);
		}
		return new Decision(decidirbomba(c), decidirmovimientoalacran(c));
	}
	
}
