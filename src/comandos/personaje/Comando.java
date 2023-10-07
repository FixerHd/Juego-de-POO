package comandos.personaje;
import java.util.HashSet;
import java.util.Random;


import comandos.estructura.*;

public class Comando implements Cloneable {
	
	protected Posicion posactual;
	private String ruta;
	protected HashSet<Posicion> conjunto;
	private int numbombas;
	protected int bombasdisp;
	protected HashSet<Posicion> ventanasconoc;
	
	public Comando(String rut) {
		this.posactual=null;
		this.ruta=rut;
		this.conjunto=new HashSet<Posicion>();
		this.numbombas=3;
		this.bombasdisp=numbombas;
		this.ventanasconoc=new HashSet<Posicion>();

	}
	
	public void añade(Posicion posi){
		conjunto.add(posi);
		posactual=posi;
	}
	
	public Posicion getpos() {
		return posactual;
	}
	
	public String getruta() {
		return ruta;
	}
	public int getbombas() {
		return bombasdisp;
	}
	public void bombaliberada() {
		bombasdisp=bombasdisp-1;
	}
	
	protected boolean decidirbomba(Contexto c) {
		if(c.getventana()==true && bombasdisp!=0) {
			ventanasconoc.add(posactual);
		    return true;
		}
		else return false;
	}
	
	protected Direccion decidirmovimiento(Contexto c) {
	    Random r = new Random();
	    if(c.getdirecc().size()==0) return null;
	    int index = r.nextInt(c.getdirecc().size()+1);
	    if(index==c.getdirecc().size()) return null;
	    return c.getdirecc().get(index);
	    
	}
	
	public Decision tomadecision(Contexto c) {
		if(c.getventana()==true) {
			ventanasconoc.add(posactual);
		}
		return new Decision(decidirbomba(c), decidirmovimiento(c));
	}
	
	private Comando Copiasuperficial() {
		try {
		Comando copiaSuperficial = (Comando) super.clone();
		return copiaSuperficial;
		}
		catch (CloneNotSupportedException e) {

		System.err.println("La clase no es cloneable");
		}
		return null;
		}
	
	
	
	@Override
	public Comando clone() {
		Comando copia=Copiasuperficial();
		
		copia.bombasdisp=numbombas;
		copia.conjunto=new HashSet<Posicion>(conjunto);
		copia.ventanasconoc=new HashSet<Posicion>(ventanasconoc);
		
		
		return copia;
	}
	
	
	
}
