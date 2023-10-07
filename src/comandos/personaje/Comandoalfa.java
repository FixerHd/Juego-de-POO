package comandos.personaje;

import java.util.Random;

import comandos.estructura.Direccion;
import comandos.estructura.Posicion;

public class Comandoalfa extends Comando {
	private int altura;
	public Comandoalfa(String rut) {
		super(rut);
		altura=0;
		// TODO Auto-generated constructor stub
	}
	
	public void añade(Posicion posi){
		if(conjunto.isEmpty()) {
			altura=posi.getY();
		}
		conjunto.add(posi);
		posactual=posi;
	}
	
	private boolean decidirbombaalfa(Contexto c) {
		if(c.getventana()==true && bombasdisp!=0 && posactual.getY()<=altura/2) {
			ventanasconoc.add(posactual);
		    return true;
		}
		else return false;
	}

	private Direccion decidirmovimientoalfa(Contexto c) {
		if(c.getdirecc().contains(Direccion.ABAJO)) {
			return Direccion.ABAJO;
		}
		else {
	    Random r = new Random();
	    if(c.getdirecc().size()==0) return null;
	    int index = r.nextInt(c.getdirecc().size()+1);
	    if(index==c.getdirecc().size()) return null;
	    return c.getdirecc().get(index);
		}
	}
	
	public Decision tomadecision(Contexto c) {
		if(c.getventana()==true) {
			ventanasconoc.add(posactual);
		}
		return new Decision(decidirbombaalfa(c), decidirmovimientoalfa(c));
	}
	
}
