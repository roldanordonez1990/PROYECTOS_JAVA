package arkanoid.version1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class Objeto {

	public Color color = null;
	public String nombre;
	public int width, height;
	public int coordX;
	public int coordY;
	public int vX;
	public int vY;
	protected BufferedImage spriteActual = null; // Sprite que representa actualmente a este actor
	protected int velocidadDeCambioDeSprite = 0; // Esta propiedad indica cada cu�ntas "unidades de tiempo" debemos mostrar el siguiente sprite del actor
	protected boolean markedForRemoval = false; // Pondremos a true esta bandera cuando el actor deba ser eliminado de la siguiente iteraci�n del juego
	protected int unidadDeTiempo = 0; // La unidad de tiempo aumenta cada vez que se llama al m�todo "act()" del Actor
	protected BufferedImage image;
	protected int dureza = 0;

	protected List<BufferedImage> spriteNames = new ArrayList<BufferedImage>(); // Lista de archivos de imagen utilizado para representarse en panta el siguiente sprite del actor 

	public Objeto() {

	}

	/**
	 * Constructor usado cuando el actor s�lo tiene un �nico sprite
	 * 
	 * @param spriteNames
	 */
	public Objeto(String spriteNames) {
		this.velocidadDeCambioDeSprite = 1;
		cargarImagenesDesdeSpriteNames(new String[] { spriteNames });
	}

	/**
	 * Constructor ampl�amente utilizado, indicando los nombres de los sprites a
	 * utilizar para mostrar este actor
	 * 
	 * @param spriteName
	 */
	public Objeto(String spriteNames[]) {
		this.velocidadDeCambioDeSprite = 1;
		cargarImagenesDesdeSpriteNames(spriteNames);
	}

	/**
	 * 
	 * 
	 * @param spriteName
	 */
	public Objeto(String spriteNames[], int velocidadDeCambioDeSprite) {
		this.velocidadDeCambioDeSprite = 7;
		cargarImagenesDesdeSpriteNames(spriteNames);
	}

	/**
	 * M�todo que lleva el control de las unidades de tiempo y el sprite que
	 * representa en cada momento al actor. Los subtipos deber�n incorporar este
	 * m�todo y realizar la llamada "super.act()".
	 */
	public void act() {
		// En el caso de que exista un array de sprites el actor actual se tratar� de
		// una animaci�n, para eso llevaremos a cabo los siguientes pasos
		if (this.spriteNames != null && this.spriteNames.size() > 1) {
			// cada vez que llaman a "act()" se incrementar� esta unidad, siempre que
			// existan sprites
			unidadDeTiempo++;
			// Si la unidad de tiemplo coincide o es m�ltiplo de la velocidad de cambio de
			// sprite, entramos al if
			if (unidadDeTiempo % velocidadDeCambioDeSprite == 0) {
				// Reiniciamos la unidad de tiempo
				unidadDeTiempo = 0;
				// Obtengo el �ndice del spriteActual, dentro de la lista de �ndices
				int indiceSpriteActual = spriteNames.indexOf(this.spriteActual);
				// Obtengo el siguiente �ndice de sprite, teniendo en cuenta que los sprites
				// cambian de uno a otro en ciclo
				int indiceSiguienteSprite = (indiceSpriteActual + 1) % spriteNames.size();
				// Se selecciona el nuevo spriteActual
				this.spriteActual = spriteNames.get(indiceSiguienteSprite);
			}
		}

	}

	/**
	 * A partir de un array de String, cargamos en memoria la lista de im�genes que
	 * constituyen los sprites del actor
	 * 
	 * @param spriteNames
	 */
	private void cargarImagenesDesdeSpriteNames(String spriteNames[]) {
		// Obtengo las im�genes de este actor, a partir del patr�n de dise�o Singleton
		// con el que se encuentra
		// el spritesRepository
		for (String sprite : spriteNames) {
			this.spriteNames.add(SpritesRepository.getInstance().getSprite(sprite));
		}
		// ajusto el primer sprite del actor
		if (this.spriteNames.size() > 0) {
			this.spriteActual = this.spriteNames.get(0);
		}
		adjustHeightAndWidth();
	}

	/**
	 * Actualiza los valores de width y height del Actor, a partir una BufferedImage
	 * que lo representar� en pantalla
	 */
	private void adjustHeightAndWidth() {
		// Una vez que tengo las im�genes que representa a este actor, obtengo el ancho
		// y el alto m�ximos de las mismas y se
		// los traspaso a objeto actual.
		if (this.spriteNames.size() > 0) {
			this.height = this.spriteNames.get(0).getHeight();
			this.width = this.spriteNames.get(0).getWidth();
		}
		for (BufferedImage sprite : this.spriteNames) {
			// Ajusto el m�ximo width como el width del actor
			if (sprite.getWidth() > this.width) {
				this.width = sprite.getWidth();
			}
			// Lo mismo que el anterior, pero con el m�ximo height
			if (sprite.getHeight() > this.height) {
				this.height = sprite.getHeight();
			}
		}
	}

	/**
	 * 
	 */

	public void AjustarImagen() {
		// Una vez que tengo la imagen que representa a este actor, obtengo el ancho y
		// el alto de la misma y se
		// los traspaso a objeto actual.
		height = this.image.getHeight();
		width = this.image.getWidth();
	}

	public abstract void actor();

	public void paint(Graphics2D g) {
		g.drawImage( this.spriteActual, this.coordX, this.coordY, null);
	}

	public void collisionWith(Objeto actorCollisioned) {

	}

	/**
	 * M�todo que lleva el control de las unidades de tiempo y el sprite que
	 * representa en cada momento al actor. Los subtipos deber�n incorporar este
	 * m�todo y realizar la llamada "super.act()".
	 */

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

	public int getCoordX() {
		return coordX;
	}

	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}

	public int getCoordY() {
		return coordY;
	}

	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}

	public BufferedImage getImage() {
		return image;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	

	public int getDureza() {
		return dureza;
	}

	public void setDureza(int dureza) {
		this.dureza = dureza;
	}

	public boolean isMarkedForRemoval() {
		return markedForRemoval;
	}

	public void setMarkedForRemoval(boolean markedForRemoval) {
		this.markedForRemoval = markedForRemoval;
	}

	public BufferedImage getSpriteActual() {
		return spriteActual;
	}

	public void setSpriteActual(BufferedImage spriteActual) {
		this.spriteActual = spriteActual;
	}

}
