package comandos.estructura;

import java.util.Objects;

public class Posicion {
	private int x;
	private int y;
	
	public Posicion() {
		this.x=0;
		this.y=0;
	}
	
	public Posicion(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public Posicion(Posicion posi) {
		x=posi.getX();
		y=posi.getY();
	}
	public void desplazar(int incX, int incY) {
		x=x+incX;
		y=y+incY;
	}
	
	public void desplazar_(Direccion Direccion) {
		if(Direccion==null) {
			return;
		}
		switch (Direccion) {
		case DERECHA:
			desplazar(1,0);
			break;
		case IZQUIERDA:
			desplazar(-1,0);
			break;
		case ARRIBA:
			desplazar(0,1);
			break;
		case ABAJO:
			desplazar(0,-1);
			break;	
		default:
			break;
		}
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Posicion other = (Posicion) obj;
		return x == other.x && y == other.y;
	}
}
