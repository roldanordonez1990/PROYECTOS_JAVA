package arkanoid.version1;

import java.awt.Graphics2D;

import arkanoid.version1.Objeto;

public class Explosion extends Objeto {

	// Array con los nombres de los sprites que forman este actor
	private static String[] SPRITES = new String[] { "sprite-explosion1.png", "sprite-explosion2.png",
			"sprite-explosion3.png", "sprite-explosion4.png", "sprite-explosion5.png", "sprite-explosion6.png",
			"sprite-explosion7.png", "sprite-explosion8.png", "sprite-explosion9.png" };

	/**
	 * El constructor establece los sprites para este actor
	 */
	public Explosion() {
		super(SPRITES, 5);
	}

	@Override
	public void actor() {

		super.act(); // Necesario para controlar diferentes aspectos de los actores

		// Si el actor llega a tocar el límite inferior de la escena, desaparecerá
		if (this.getSpriteActual().equals(this.spriteNames.get(this.spriteNames.size() - 1))) {
			this.setMarkedForRemoval(true);
		}

	}

	

}
