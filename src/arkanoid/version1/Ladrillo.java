package arkanoid.version1;

import java.awt.Color;
import java.awt.Graphics2D;

public class Ladrillo extends Objeto {

	// int vX = 20;
	// int vY = 20;

	// Usamos un patrón singleton para poder llamar a este objeto desde cualquier
	// clase
	private static Ladrillo instance = null;
	//private  static Color colores = new Color[] {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};
	private  Color colores = null;

	public Ladrillo() {
		// this.coordX = 10;
		// this.coordY = 10;
		// this.color = Color.GREEN;
		this.height = 33;
		this.width = 30;
		this.colores = Pantalla.COLORES[(int) Math.round(Math.random() * (Pantalla.COLORES.length-1))];

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
		
		//ladrillo
		h.setColor(this.getColores());
		h.fillRoundRect(this.coordX, this.coordY, this.height, this.width, 9, 9);
		//Cuadrado interno
		h.setColor(Color.BLACK);
		h.drawRect(this.coordX +3, this.coordY +3, this.width -3, this.height -8);
		//circulo interno
		h.setColor(Color.BLACK);
		h.drawOval(this.coordX +3, this.coordY +3, this.width -3, this.height -8);
		//Borde entre los ladrillos
		h.setColor(Color.DARK_GRAY);
		h.drawRect(this.coordX, this.coordY, this.height, this.width);
	}

	public void collisionWith(Objeto actorCollisioned) {
		super.collisionWith(actorCollisioned);
		// Debo comprobar el tipo del actor que colisiona con este
		if (actorCollisioned instanceof Pelota) {
			// Si este actor colisiona con un ladrillo, debo eliminar el ladrillo
			
			this.setMarkedForRemoval(true);
			// Creo una explosión en el lugar que antes ocupaba el monstruo
			this.createExplosion();
			SoundsRepository.getInstance().playSound("explosion.wav");
			

		}
	}
	
	/**
	 * Crear un nuevo actor de explosión en el lugar que ocupa el monstruo
	 */
	private void createExplosion () {
		Explosion explosion = new Explosion();
		explosion.setCoordX(this.coordX +8); 
		explosion.setCoordY(this.coordY +8); 
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
