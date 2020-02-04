package arkanoid.version1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Date;

public class Pelota extends Objeto implements KeyListener, MouseListener {

	protected int vX;
	protected int vY;
	private boolean space;
	// private int contadorSalidaPelota = 0;
	private long usedTime;
	private long startTime = System.currentTimeMillis();
	private int contadorClic = 0;
	private int contadorSalidaPelota = 0;
	PuntoAltaPrecision coordenadas = null; // los ponemos a null porque nos van a servir de bandera
	TrayectoriaRecta trayectoria = null;
	private long millisEnInicio = 0; // inicio del tiempo en millisegundos
	private float distanciaSiguienteFrame = 5; // distancia en px al siguiente punto que va a marcar la velocidad
	private float aceleracion = 1.03f; // aceleración de la bola
	private static int MAX_VELOCIDAD = 9; // límite de velocidad para que no pase de ahí
	

	// Usamos un patrón singleton para poder llamar a este objeto desde cualquier
	// clase
	public static Pelota instance = null;

	public Pelota() {
		super();
		this.width = 15;
		this.height = 15;
		// this.vX = 0;
		// this.vY = 0;
		this.millisEnInicio = new Date().getTime(); // Esto nos da el tiempo desde 0 en adelante, empieza a contar desde
													// que se crea la pelota

	}

	/**
	 * Método que nos reinicia la salida de la bola cuando sale del Canvas, reininica los millis y vuelve la trayectoria a null
	 */
	public void reiniciarMillis() {
		millisEnInicio = new Date().getTime();
		trayectoria = null;
	}
	
	/**
	 * Patrón Singleton
	 * @return
	 */

	public static Pelota getInstance() {
		if (instance == null) {
			instance = new Pelota();
		}
		return instance;
	}

	/**
	 * 
	 */

	// Método que pinta la pelota
	public void paint(Graphics2D g) {

		g.setColor(Color.WHITE);
		g.fillOval(this.coordX, this.coordY, this.width, this.height);

	}

	public void act() {

	}

	@Override
	public void collisionWith(Objeto actorCollisioned) {
		super.collisionWith(actorCollisioned);

		if (actorCollisioned instanceof Ladrillo) {
			trayectoria.reflejarVerticalmenteRespectoAPunto(this.coordenadas);

			// Aceleracion de la pelota al colisionar contra los ladrillos
			if (this.distanciaSiguienteFrame < MAX_VELOCIDAD) {
				this.distanciaSiguienteFrame *= this.aceleracion;
			}

		}
		if (actorCollisioned instanceof Nave) {
			trayectoria.reflejarVerticalmenteRespectoAPunto(this.coordenadas);
			SoundsRepository.getInstance().playSound("Arkanoid-SFX-06.wav");
		}
		// Aceleración

	}

	/*
	 * public void esperar() { usedTime = System.currentTimeMillis() - startTime; //
	 * System.out.println(usedTime);
	 * 
	 * if (usedTime >= 5000 && contadorSalidaPelota == 0 && contadorClic == 0) {
	 * contadorSalidaPelota++; contadorClic++;
	 * 
	 * vX = 4; vY = 4; } }
	 */

	@Override
	public void actor() {

		// esperar();
		
		if (trayectoria == null) { // si la trayectoria aún no se ha iniciado, la pelota está quieta en la nave
			long millisAhora = new Date().getTime();
			if (millisAhora - millisEnInicio > 5000) {
				
				this.coordenadas = new PuntoAltaPrecision(this.coordX, this.coordY);
				this.trayectoria = new TrayectoriaRecta(-1.3f, this.coordenadas, true);

			} else {
				this.coordX = Pantalla.getInstance().getNave().getCoordX() + 17;
				this.coordY = Pantalla.getInstance().getNave().getCoordY() - 18;
				// mientras no pasen 3", la pelota permanecerá pegada a la nave. Pasados 3", la
				// pelota ya no estará pegada a la nave, adjudicándose las nuevas coordenadas
			}

		} else { // ya existe una trayectoria. Rebotes

			// Le decimos que no se salga del canvas y rebote contra las paredes del canvas
			// con una nueva trayectoria
			if (this.coordX < 0 || this.coordX > Pantalla.getInstance().getWidth() - this.width) {
				this.trayectoria.reflejarHorizontalmenteRespectoAPunto(this.coordenadas);

			}

			if (this.coordY < 0) {
				this.trayectoria.reflejarVerticalmenteRespectoAPunto(this.coordenadas);

			}
			if (this.coordY > Pantalla.getInstance().getHeight()) {

			}
			// una vez asignada esa nueva trayectoria, le tenemos que indicar hacia dónde va
			// con la distancia al siguiente frame
			this.coordenadas = this.trayectoria.getPuntoADistanciaDePunto(this.coordenadas,this.distanciaSiguienteFrame);
			// Nuevos puntos asignados para esa trayectoria
			this.coordX = Math.round(this.coordenadas.x);
			this.coordY = Math.round(this.coordenadas.y);

			// this.coordX += this.vX;
			// this.coordY += this.vY;

			// Le indicamos que no se salga del canvas
			// if (this.coordX < 0 || this.coordX > (Pantalla.getInstance().getWidth() -
			// this.width)) {
			// vX = -vX;

			// }
			// if (this.coordY < 0 || this.coordY > (Pantalla.getInstance().getHeight() -
			// this.height)) {
			// vY = -vY;

			// }
		}
	}

	public void setvX(int i) {
		// TODO Auto-generated method stub

	}

	public static void add(Pelota m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_SPACE:
			space = true;
			break;
		}
		updateSpeed();
	}

	protected void updateSpeed() {

		/*if (space) {
			contadorSalidaPelota++;

			if (contadorSalidaPelota == 1) {
				//contadorClic = 2;
				this.coordenadas = new PuntoAltaPrecision(this.coordX, this.coordY);
				this.trayectoria = new TrayectoriaRecta(-1.3f, this.coordenadas, true);
				SoundsRepository.getInstance().playSound("Arkanoid-SFX-03.wav");
			}

		}
		*/
		
		if (trayectoria == null && space) {
			this.coordenadas = new PuntoAltaPrecision(this.coordX, this.coordY);
			this.trayectoria = new TrayectoriaRecta(-1.3f, this.coordenadas, true);
			SoundsRepository.getInstance().playSound("Arkanoid-SFX-03.wav");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_SPACE:
			space = false;
			break;
		}
		updateSpeed();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//contadorClic++;
		if (trayectoria == null) {
			//contadorSalidaPelota = 2;
			this.coordenadas = new PuntoAltaPrecision(this.coordX, this.coordY);
			this.trayectoria = new TrayectoriaRecta(-1.3f, this.coordenadas, true);
			SoundsRepository.getInstance().playSound("Arkanoid-SFX-03.wav");
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public int getContadorClic() {
		return contadorClic;
	}

	public void setContadorClic(int contadorClic) {
		this.contadorClic = contadorClic;
	}

	public int getContadorSalidaPelota() {
		return contadorSalidaPelota;
	}

	public void setContadorSalidaPelota(int contadorSalidaPelota) {
		this.contadorSalidaPelota = contadorSalidaPelota;
	}
	
	

}
