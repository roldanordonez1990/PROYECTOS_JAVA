package formula1;

import java.awt.Color;
import java.awt.Graphics;



public class Vehiculo extends Actor{

	protected String nombre;
	protected int posicion = 0;
	private int extension = 0;
	protected Color color;
	protected Pista pista;
	
	//private static Vehiculo instance = null;
	


	/**
	 * @param nombre
	 * @param posicion
	 * @param color
	 */
	
	public Vehiculo(Pista pista, String nombre, Color color) {
		this.pista = pista;
		this.nombre = nombre;
		this.color = color;
		this.posicion = 700;
	}
	
	public Vehiculo(int x, int y) {
		super(x,y);
	}
	
	@Override
	public void paint(Graphics g) {
		this.pista.paint(g);
		
	}			
	
	@Override
	public String toString() {
		return  "Vehiculo [nombre=" + nombre + ", posicion=" + posicion+ "]";
	}

    /**
     * Cualidad que hace que avance
     */
	
	
			
	@Override
	public void act() {
		
		this.posicion += (int) Math.round(Math.random() * (6 - 1) + 1);		
	
	}
	
	public boolean haCompletadoCarrera () {
		return this.posicion  >= Carrera.MAX_POSICIONES_POR_PISTA;
	}
	
		
	

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}



	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	/**
	 * @return the posicion
	 */
	public int getPosicion() {
		return posicion;
	}



	/**
	 * @param posicion the posicion to set
	 */
	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}



	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}



	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	
}
