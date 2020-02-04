package tresEnRaya.datos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
* 
*
* Esta clase representa el almacén de imágenes de todo el videojuego
*/
public class Cuadrados {
	// Coordenadas de este cuadro sobre el tablero, por ejemplo: 0,0 - 0,1 - 0,2 forman la primera fila de cuadros
	private int xEnTablero, yEnTablero;
	// Coordenadas del pixel superior izquierdo del cuadro representado por cada cuadro, a partir de ese píxel se pintará el cuadro
	private int esquinaSuperiorIzquierdaX, esquinaSuperiorIzquierdaY;
	// Ancho y alto de este cuadro
	private int ancho, alto;
	// Jugador que ha hecho clic sobre este cuadro, pasando el cuadro a ser de su propiedad
	// El valor 0 indica que el cuadro no pertenece a ningún jugador
	private int jugadorPropietario = 0;

	/**
	 * @param coordenadaX
	 * @param coordenadaY
	 */
	public Cuadrados(int xEnTablero, int yEnTablero) {
		super();
		this.xEnTablero = xEnTablero;
		this.yEnTablero = yEnTablero;
	}

	/**
	 * Utilizo este método para que cada cuadro pueda pintarse a sí mismo.
	 * Necesitamos un objeto de tipo Graphics, que nos permita acceder a la memoria de vídeo 
	 * @param g
	 */
	public void paint (Graphics g) {
		// Obtengo el ancho y el alto de cada cuadro, obteniendo el alto y el ancho
		// del Canvas principal del juego y diviendo estos valores entre tres
		ancho = TresEnRaya.getInstance().getWidth() / 3;
		alto = TresEnRaya.getInstance().getHeight() / 3;
		esquinaSuperiorIzquierdaX = this.xEnTablero * ancho;
		esquinaSuperiorIzquierdaY = this.yEnTablero * alto;
		
		// Pinto el borde 
		g.setColor(Color.WHITE);
		g.drawRect(esquinaSuperiorIzquierdaX, esquinaSuperiorIzquierdaY, ancho, alto);
		
		// Para cada jugador, pinto imágenes vectoriales o imágenes de mapas de bits, a tu elección
		// De las dos líneas siguientes puedes comentar la que no quieras utilizar
//		pintaImagenesVectoriales(g);
		pintaImagenesMapasDeBits(g);
	}
	
	/**
	 * Dependiendo del jugador propietario de este cuadro, se pintará una cruz o un círculo
	 * @param g
	 */
	private void pintaImagenesVectoriales (Graphics g) {
		// Ahora, dependiendo del jugador propietario de este cuadro, pinto algo diferente
		if (this.jugadorPropietario == TresEnRaya.JUGADOR_1) { // Comprueba jugador 1 - pinta una cruz
			// Para pintar una cruz pinto dos líneas que se cruzan
			g.drawLine(this.esquinaSuperiorIzquierdaX, this.esquinaSuperiorIzquierdaY, 
					this.esquinaSuperiorIzquierdaX + this.ancho, this.esquinaSuperiorIzquierdaY + alto);
			g.drawLine(this.esquinaSuperiorIzquierdaX, this.esquinaSuperiorIzquierdaY + alto, 
					this.esquinaSuperiorIzquierdaX + this.ancho, this.esquinaSuperiorIzquierdaY);
		} 
		if (this.jugadorPropietario == TresEnRaya.JUGADOR_2) { // En este caso el jugador 2
			g.drawOval(this.esquinaSuperiorIzquierdaX, this.esquinaSuperiorIzquierdaY, this.ancho, this.alto);
		}
	}
	
	/**
	 * Dependiendo del jugador propietario de este cuadro, pinto una imagen diferente
	 * @param g
	 */
	private void pintaImagenesMapasDeBits (Graphics g) {
		// Ahora, dependiendo del jugador propietario de este cuadro, pinto algo diferente
		BufferedImage imagenAPintar = null;
		if (this.jugadorPropietario == TresEnRaya.JUGADOR_1) { // Comprueba jugador 1 - Pinto el escudo del Real Madrid CF
			imagenAPintar = SpritesRepository.getInstance().getSprite(SpritesRepository.IMAGEN_JUGADOR_1);
		} 
		if (this.jugadorPropietario == TresEnRaya.JUGADOR_2) { // En este caso el jugador 2 - Pinto el escudo del FCB 
			imagenAPintar = SpritesRepository.getInstance().getSprite(SpritesRepository.IMAGEN_JUGADOR_2);
		}
		
		// Una vez que sé qué imagen quiero pintar, la muestro en pantalla
		if (imagenAPintar != null) {
			int x = this.esquinaSuperiorIzquierdaX + this.ancho / 2 - imagenAPintar.getWidth() / 2;
			int y = this.esquinaSuperiorIzquierdaY + this.alto / 2 - imagenAPintar.getHeight() / 2;
			g.drawImage(imagenAPintar, x, y, null);
		}
	}

	
	/**
	 * Este método detecta si unas coordenadas sobre el canvas están dentro del cuadro actual. Esto
	 * permite detectar si nos han hecho clic sobre el cuadro representado en cada objeto.
	 * @param xClic
	 * @param yClic
	 * @return
	 */
	public boolean seHaHechoclicSobreCuadro (int xClic, int yClic) {
		// Compruebo si las coordenas del clic están dentro del espacio que ocupa mi cuadro
		if (xClic > this.esquinaSuperiorIzquierdaX && xClic < (esquinaSuperiorIzquierdaX + ancho) // Coordenada x dentro del ancho
				&&
			yClic > this.esquinaSuperiorIzquierdaY && yClic < (esquinaSuperiorIzquierdaY + alto)) { // Coordenada y dentro del alto
			return true;
		}
		return false;
	}
	
	/**
	 * Al hacernos clic sobre este cuadro necesitamos el jugador que ha hecho clic, para determinar el 
	 * propietario del mismo
	 * @param jugador
	 */
	public void clic (int jugador) {
		if (this.jugadorPropietario == 0) {
			this.jugadorPropietario = jugador;
		}
		
		// Actualizo la matriz de jugadas de este juego para reflejar la nueva jugada llevada a 
		// cabo sobre este cuadro
		TresEnRaya.getInstance().getMatrizJugadas()[this.yEnTablero][this.xEnTablero] = jugador;
		
		// Obligo a repintar el objeto Canvas
		TresEnRaya.getInstance().repaint();
		TresEnRaya.getInstance().revalidate();
		
		// Sólo por depurar el programa, imprimo en consola la matriz de jugadas
		System.out.println("Estado del juego");
		for (int i = 0; i < TresEnRaya.getInstance().getMatrizJugadas().length; i++) {
			for (int j = 0; j < TresEnRaya.getInstance().getMatrizJugadas()[i].length; j++) {
				System.out.print(TresEnRaya.getInstance().getMatrizJugadas()[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "Cuadro [xEnTablero=" + xEnTablero + ", yEnTablero=" + yEnTablero + ", jugadorPropietario="
				+ jugadorPropietario + "]";
	}

	// Setters y getters	
	public int getxEnTablero() {
		return xEnTablero;
	}

	public void setxEnTablero(int xEnTablero) {
		this.xEnTablero = xEnTablero;
	}

	public int getyEnTablero() {
		return yEnTablero;
	}

	public void setyEnTablero(int yEnTablero) {
		this.yEnTablero = yEnTablero;
	}

	
}
