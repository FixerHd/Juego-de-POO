package comandos.personaje;


public class Comandohalcon extends Comando {
	private int ventanasvisitadas;
	public Comandohalcon(String rut) {
		super(rut);
		ventanasvisitadas=0;
		// TODO Auto-generated constructor stub
	}
	
	private boolean decidirBombahalcon(Contexto c) {
		
		if(bombasdisp!=0 && ventanasvisitadas>3 && !ventanasconoc.contains(posactual)) return true;
		else return false;
		}

	
	public Decision tomadecision(Contexto c) {
		if(c.getventana()==true) {
			ventanasconoc.add(posactual);
			ventanasvisitadas=ventanasconoc.size();
		}
		return new Decision(decidirBombahalcon(c), decidirmovimiento(c));
	}
	
}
