package comandos.estructura;
import comandos.juego.*;
import comandos.personaje.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class Escenario {
	private String nombre;
	private int alto;
	private int ancho;
	private HashSet<Posicion> ventanas;
	private Posicion objetivo;
	private boolean [][] zonas;
	private LocalDateTime inicio;
	private HashSet<Posicion> conjuntobomb;
	private HashSet<Comando> conjuntocom;
	private Estado state;
	private LocalDateTime ultactualizacion;
	
	public Escenario(String n, int ancho, int alto, HashSet<Posicion> ventanas ){
		this.nombre = n;
		this.alto = alto;
		this.ancho = ancho;
		this.ventanas = ventanas;
		objetivo=new Posicion();
		conjuntobomb=new HashSet<Posicion>();
		conjuntocom=new HashSet<Comando>();
		zonas = new boolean[ancho][alto];
		for (int i = 0; i<this.ancho; i++) {
			for (int j = 0; j<this.alto; j++) {
				this.zonas[i][j] = true;
			}
		}
		inicio=LocalDateTime.now();
		ultactualizacion=LocalDateTime.now();
		state=null;
	}
	
	public Escenario(String n, int ancho, int alto) {
		this.nombre = n;
		this.alto = alto;
		this.ancho = ancho;
		conjuntobomb=new HashSet<Posicion>();
		conjuntocom=new HashSet<Comando>();
		objetivo=new Posicion();
		zonas = new boolean[alto][ancho];
		for(int i = 0; i<this.alto;i++) {
			if(i == this.alto-1) {
				for (int j = 0; j<this.ancho; j++) {
					 this.zonas[i][j] = true;
				 }
			}
			else if(i % 2 == 0) {
				zonas[i][0] = true;
			 for (int j = 1; j<this.ancho; j++) {
				 this.zonas[i][j] = false;
			 }
			}else {
				for (int j = 0; j<this.ancho; j++) {
					 this.zonas[i][j] = true;
				 }
			}
	
	}
	ventanas = new HashSet<Posicion>();
	for(int i = 0; i<this.alto;i++) {
		for (int j = 0; j<this.ancho; j++) {
			if(i % 2 == 1 && j % 2 == 0) ventanas.add(new Posicion(j, i));
		}
	state=null;
	}
	inicio=LocalDateTime.now();
	ultactualizacion=LocalDateTime.now();
	}
	
	public int getalto() {
		return alto;
	}
	
	public int getancho() {
		return ancho;
	}
	
	public String getnombre() {
		return nombre;
	}
	
	public Posicion getobjetivo() {
		return objetivo;
	}
	
	public boolean escerrada(Posicion posi) {
		if(zonas[posi.getY()][posi.getX()] == false) return true;
		else return false;
	}
	
	public boolean hayventana(Posicion posi) {
		return ventanas.contains(posi);
	}
	
	private boolean estaenmapa(Posicion posi) {
		if(posi.getX() >= 0 && posi.getX() < this.ancho && posi.getY() >= 0 && posi.getY() < this.alto) return true;
		else return false;
	}
	
	public List <Direccion> acceder(Posicion posi){
	    List <Direccion> accedible = new ArrayList<Direccion>();
		for(Direccion dir :Direccion.values()) {
			Posicion Posicion1 = new Posicion(posi);
			Posicion1.desplazar_(dir);
			if(estaenmapa(Posicion1) && !escerrada(Posicion1) && consultacomando(Posicion1)==null){
				accedible.add(dir);
			}
		}
		
		return accedible;
	}
	
	public ArrayList <Imagen> getImagenes(){
		ArrayList <Imagen> imagene_ = new ArrayList<Imagen>();
		for (Comando elemento : conjuntocom) {
			imagene_.add(new Imagen(elemento.getruta(), elemento.getpos().getX(), elemento.getpos().getY()));
			}
		for(int i=0; i<alto; i++) {
			for(int j=0; j<ancho; j++) {
				if(escerrada(new Posicion(j, i))) {
					imagene_.add(new Imagen("imagenes/zona-cerrada.png", j, i));
				}
			}
		}
		for(int i=0; i<alto; i++) {
			for(int j=0; j<ancho; j++) {
				if(hayventana(new Posicion(j, i))) {
					imagene_.add(new Imagen("imagenes/ventana.png", j, i));
				}else {
					imagene_.add(new Imagen("imagenes/ladrillos.png", j, i));
				}
			}
			for (Posicion posi : conjuntobomb) {
				imagene_.add(new Imagen("imagenes/bomba.png", posi.getX(), posi.getY()));
				}
			imagene_.add(new Imagen("imagenes/objetivo.png", objetivo.getX(), objetivo.getY()));
		}
		return imagene_;
	}
	public long segundos() {
		LocalDateTime fin=LocalDateTime.now();
		return ChronoUnit.SECONDS.between(inicio, fin);
	}


	public Comando consultacomando(Posicion posi) {
		for (Comando elemento : conjuntocom) {
			if(elemento.getpos().equals(posi)) return elemento;
			}
		return null;
	}
	
	public HashSet<Posicion> consultacomandos(){
		HashSet<Posicion> consulta = new HashSet<Posicion>();
		for (Comando elemento : conjuntocom) {
			consulta.add(elemento.getpos());
			}
		return consulta;
	}
	
	public void añadircomando(Comando com) {
		boolean add=false;
		while(add==false) {
		Random r = new Random();
		Posicion posi = new Posicion(r.nextInt(ancho), alto-1);
		if(escerrada(posi)) {
			
		}
		else if(consultacomando(posi)!=null) {
			
		}else {
			com.añade(posi);
			conjuntocom.add(com);
			add=true;
		}
		}
	}
	
	public void disparo(Posicion posi) {
		if(conjuntobomb.contains(posi)) {
		conjuntobomb.remove(posi);
		}
		if(hayventana(posi)) {
		HashSet<Comando> conjuntoaux = new HashSet<Comando>(conjuntocom);
		
			for (Comando elemento : conjuntoaux) {
				if(elemento.getpos().equals(posi)) {
					añadircomando(elemento.clone());
					conjuntocom.remove(elemento);
				}
			}
				
		}
	}
	public void iniciarpartida() {
		state=Estado.ENJUEGO;
		
	}
	
	public void actualizar() {
		if(ChronoUnit.SECONDS.between(ultactualizacion, LocalDateTime.now())>1) {
			ultactualizacion=LocalDateTime.now();
			for (Posicion posi : conjuntobomb) {
				if(posi.getY()==0) state=Estado.FINBOMBA;
			}
			for (Comando elemento : conjuntocom) {
				if(elemento.getpos().getY()==0) state=Estado.FINCONTROL;
			}
			for (Comando elemento : conjuntocom) {
				Decision d = elemento.tomadecision(new Contexto(hayventana(elemento.getpos()),acceder(elemento.getpos())));
				if(d.gettirarbomb()==true) {elemento.bombaliberada();
				conjuntobomb.add(new Posicion(elemento.getpos()));}
				Posicion posaux=elemento.getpos();
				posaux.desplazar_(d.getdest());
				if(consultacomando(posaux)==null) {
					elemento.añade(posaux);
				}
				if(elemento.getpos().getY()==0) state=Estado.FINCONTROL;
				}
			HashSet<Posicion> conjuntobombaux=new HashSet<Posicion>(conjuntobomb);
			for (Posicion posi : conjuntobombaux) {
				conjuntobomb.remove(posi);
				posi.desplazar_(Direccion.ABAJO);
				conjuntobomb.add(posi);
				if(posi.getY()==0) state=Estado.FINBOMBA;
			}
			
			}
		}
		
		
	
	public Estado getstate() {
		return state;
	}
}	
