package arkanoid.version1;

import java.awt.Color;
import java.awt.Graphics2D;

public class Ladrillo extends Objeto {

	// int vX = 20;
	// int vY = 20;

	// Usamos un patrón singleton para poder llamar a este objeto desde cualquier
	// clase
	private static Ladrillo instance = null;

	private Color colores = null;
	public Color color = null;

	public Ladrillo() {
		// this.coordX = 10;
		// this.coordY = 10;
		this.height = 33;
		this.width = 30;
		// this.colores = Pantalla.COLORES[(int) Math.round(Math.random() *
		// (Pantalla.COLORES.length-1))];
		this.color = Color.RED;
		this.dureza = 0; // Le indicamos un nivel de dureza para la destrucción de los ladrillos

	}

	public static Ladrillo getInstance() {
		if (instance == null) {
			instance = new Ladrillo();
		}
		return instance;
	}

	/**
	 * 
	 * @param h
	 */

	// Método que pinta el ladrillo

	@Override
	public void paint(Graphics2D h) {
		// System.out.println("Estamos pintando un rectángulo para el ladrillo");

		// ladrillo
		h.setColor(this.color);
		h.fillRoundRect(this.coordX, this.coordY, this.height, this.width, 9, 9);
		// Cuadrado interno
		h.setColor(Color.BLACK);
		h.drawRect(this.coordX + 3, this.coordY + 3, this.width - 3, this.height - 8);
		// circulo interno
		h.setColor(Color.BLACK);
		h.drawOval(this.coordX + 3, this.coordY + 3, this.width - 3, this.height - 8);
		// Borde entre los ladrillos
		h.setColor(Color.DARK_GRAY);
		h.drawRect(this.coordX, this.coordY, this.height, this.width);
	}

	public void collisionWith(Objeto actorCollisioned) {
		super.collisionWith(actorCollisioned);
		// Debo comprobar el tipo del actor que colisiona con este
		if (actorCollisioned instanceof Pelota) {
			// Si el actor ladrillo colisiona con la Pelota, se restará dureza
			dureza--;
			System.out.println(dureza);
			// Si este actor colisiona con un ladrillo, debo eliminar el ladrillo
			// dependiendo de su dureza. Se eliminará cuando es 0
			if (this.dureza == 0) {
				this.setMarkedForRemoval(true);
				// Creo una explosión en el lugar que antes ocupaba el monstruo
				this.createExplosion();
				SoundsRepository.getInstance().playSound("explosion.wav");
			}

		}
	}

	/**
	 * Crear un nuevo actor de explosión en el lugar que ocupa el monstruo
	 */
	private void createExplosion() {
		Explosion explosion = new Explosion();
		explosion.setCoordX(this.coordX + 8);
		explosion.setCoordY(this.coordY + 8);
		Pantalla.getInstance().addNewActorToNextIteration(explosion);
	}

	@Override
	public void actor() {

	}

	public int getX() {
		return coordX;
	}

	public void setX(int x) {
		this.coordX = x;
	}

	public int getY() {
		return coordY;
	}

	public void setY(int y) {
		this.coordY = y;
	}

	public Color getColores() {
		return colores;
	}

	public void setColores(Color colores) {
		this.colores = colores;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	/*
	 * public int getvX() { return vX; }
	 */
	/*
	 * public void setvX(int vX) { this.vX = vX; }
	 */
	/*
	 * public int getvY() { return vY; }
	 */
	/*
	 * public void setvY(int vY) { this.vY = vY; }
	 */

}
