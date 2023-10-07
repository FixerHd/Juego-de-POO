package comandos.personaje;
import comandos.estructura.*;

public class Decision {
	
	private boolean tirarbomb;
	private Direccion dest;
	
    public Decision(boolean t, Direccion d) {
    	this.tirarbomb=t;
    	this.dest=d;
    }
    
    public boolean gettirarbomb() {
    	return tirarbomb;
    }
    
    public Direccion getdest() {
    	return dest;
    }

}
