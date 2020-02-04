package formula1;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Obstaculo {

	protected String nombre;
	private int posicion = 0;
	private int dimension = 0;
	private int espacioOcupado = posicion + dimension;
	protected int impulso;
	protected Color color;
	protected int y;

	/**
	 * @param posicion
	 * @param dimension
	 * @param manchurron
	 */
	public Obstaculo(String nombre) {
		super();
		this.nombre = nombre;
		this.posicion = posicion;
		this.dimension = dimension;
		this.espacioOcupado = espacioOcupado;
		this.impulso = impulso;
		this.y = y;
	}

	/**
	 * 
	 */
	public abstract void paint(Graphics g);

	@Override
	public String toString() {
		return "Obstaculo [posicion=" + posicion + ", dimension=" + dimension + ", espacioOcupado=" + espacioOcupado
				+ ", impulso=" + impulso + "]";
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
	 * @return the dimension
	 */
	public int getDimension() {
		return dimension;
	}

	/**
	 * @param dimension the dimension to set
	 */
	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	/**
	 * @return the manchurron
	 */
	public int getEspacioOcupado() {
		return espacioOcupado;
	}

	/**
	 * @param manchurron the espacioOcupado to set
	 */
	public void setManchurron(int espacioOcupado) {
		this.espacioOcupado = espacioOcupado;
	}

	/**
	 * @return the impulso
	 */
	public int getImpulso() {
		return impulso;
	}

	/**
	 * @param impulso the impulso to set
	 */
	public void setImpulso(int impulso) {
		this.impulso = impulso;
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
	 * @param espacioOcupado the espacioOcupado to set
	 */
	public void setEspacioOcupado(int espacioOcupado) {
		this.espacioOcupado = espacioOcupado;
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

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

}
