package comandos.juego;

public class Imagen {

	private String ruta;
	private int x;
	private int y;
	
	public Imagen(String _ruta, int _x, int _y) {
		this.x = _x;
		this.y = _y;
		this.ruta = _ruta;
	}
	
	public int GetX() {
		return x;
	}
	public int GetY() {
		return y;
	}
	public String GetRuta() {
		return ruta;
	}

}
