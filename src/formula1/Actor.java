package formula1;

import java.awt.Graphics;

public abstract class Actor {

	protected int x, y; // Coordenadas en las que se encuentra el actor
	protected int width, height; // Ancho y alto que ocupa, imprescindible para detectar colisiones

	/**
	 * Constructor por defecto, inicializa la propiedad "image" a null, indicando
	 * que no hay imagen
	 */
	public Actor() {
	}

	/**
	 * @param x
	 * @param y
	 */
	public Actor(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	/**
	 * Método para representar este actor en pantalla
	 * 
	 * @param g
	 */
	public abstract void paint(Graphics g);

	/**
	 * Método abstracto que debe implementar cada subtipo de Actor. Lógicamente no
	 * será lo mismo la actuación que llevará a cabo un Player que un Monster.
	 */
	public abstract void act();

	// Setters y Getters

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
