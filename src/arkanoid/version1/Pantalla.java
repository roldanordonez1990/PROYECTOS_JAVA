package arkanoid.version1;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Pantalla extends Canvas {

	// Ventana principal del juego
	JFrame ventana = new JFrame("Arkanoid");

	// Indicamos alto y ancho del objeto tipo Canvas
	private static final int JFRAME_WIDTH = 450;
	private static final int JFRAME_HEIGHT = 550;

	// Variable para establecer la instancia del patrón singleton
	private static Pantalla instance = null;
	public int posX, posY, vX, vY;
	Pelota ball = null;
	Nave nave = null;

	// variable booleana para la segunda fase
	private boolean finDeFase = false;

	// Para establecer el lugar que ocupa la barra de estado, necesitamos tener una
	// referencia de hasta dónde llega
	// la zona vertical de juego y dónde empieza la barra de estado
	private int YforStatusBar = 0;

	// Lista de colores
//	public static Color[] COLORES = new Color[] { Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.PINK,
//			Color.ORANGE, Color.WHITE, Color.CYAN, Color.MAGENTA };
	public static Color color = null;

	private long usedTime; // Tiempo usado en cada iteración del bucle principal del juego
	// Velocidad de los fotogramas, en concreto este indica que el proceso de
	// redibujado dormirá 10 millis
	// tras haber repintado la escena
	public static final int SPEED_FPS = 70;

	// Lista con todos los actores que intervienen en el videojuego
	private List<Objeto> actors = new ArrayList<Objeto>();
	// private List<Ladrillo> ladrillos = new ArrayList<Ladrillo>();
	// Lista con actores que deben incorporarse en la siguiente iteración del juego
	private List<Objeto> newActorsForNextIteration = new ArrayList<Objeto>();

	// BufferStrategy usado para conseguir la técnica de doble búffer
	private BufferStrategy strategy;
	// private long usedTime; // Tiempo usado en cada iteración del bucle principal
	// del juego.
	// Referencia que guardaremos apuntando al objeto de tipo Player
	Nave player = null;

	public Pantalla() {

		// Lo primero que hago en el juego es una precarga de todos los archivos de
		// sonido
		SoundsRepository.getInstance();
		SoundsRepository.getInstance().playSound(SoundsRepository.FONDO);
		// actors.add(ladrillo);

		posX = WIDTH / 2;
		posY = HEIGHT / 2;

		// Obtengo referencia al panel principal de la ventana
		JPanel panel = (JPanel) ventana.getContentPane();
		// Establezco una plantilla en el panel, para poder incorporar el Canvas
		panel.setLayout(new BorderLayout());
		// Agrego el Canvas al panel
		panel.add(this, BorderLayout.CENTER);
		// Desactivo el comportamiento por defecto al pulsar el botón de cierre de la
		// ventana
		ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		// Agrego un escuchador a la ventana, para detectar el evento de cierre de la
		// misma Dimensiono la ventana
		ventana.setBounds(100, 100, JFRAME_WIDTH, JFRAME_HEIGHT);
		ventana.setVisible(true);
		// Con esto hacemos que la ventana no se mueva
		ventana.setResizable(false);

		// Con windowsAdapter lo que hacemos es instanciar sólo algunos métodos del
		// windowsListener en vez de todos
		ventana.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cerrarApp();
			}

		});

		// Con ignoreRepaint le decimos al JFrame que no debe repintarse cuando el
		// Sistema Operativo se lo indique, nosotros nos ocupamos totalmente del
		// refresco de la pantalla
		ventana.setIgnoreRepaint(true);
		// El Canvas se dibujará en pantalla con una estrategia de doble búffer
		this.createBufferStrategy(2);
		// Obtengo una referencia a la estrategia de doble búffer.
		strategy = getBufferStrategy();
		// El foco de Windows irá al Canvas, para que directamente podamos controlar
		// este juego, si utilizáramos el teclado
		this.requestFocus();
		// Lanzo la música de fondo, en modo bucle

	}

	/**
	 * 
	 * @param args
	 */

	// generamos el método para cerrar la ventada dándo opciones
	public void cerrarApp() {
		String[] opciones = { "Aceptar", "Cancelar" };
		int eleccion = JOptionPane.showOptionDialog(ventana, "¿Desea cerrar la aplicación?", "Salir de la aplicación",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, "Aceptar");
		if (eleccion == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	/**
	 * 
	 */

	// Se intenta cargar el recurso del disco duro, si no se pudiera se capturará la
	// excepción y se mostrará un mensaje en pantalla
	public BufferedImage loadImage(String nombre) {
		URL url = null;
		try {
			url = getClass().getResource(nombre);
			return ImageIO.read(url);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("No se pudo cargar la imagen " + nombre + " de " + url);
			System.out.println("El error fue : " + e.getClass().getName() + " " + e.getMessage());
			System.exit(0);
			return null;
		}
	}

	public void paintWorld() {
		// Resuelve un problema de sincronización de memoria de vídeo en Linux
		Toolkit.getDefaultToolkit().sync();

		// Obtengo el objeto gráfico que me permita pintar en el doble búffer
		Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
		// El rectángulo que estamos pintamos tiene las mismas dimensiones que el Canvas

		//BufferedImage fondo = SpritesRepository.getInstance().getSprite(SpritesRepository.FONDO);
		//g.drawImage(SpritesRepository.getInstance().getSprite(SpritesRepository.FONDO),0,0,this);
		g.drawImage(SpritesRepository.getInstance().getSprite(SpritesRepository.getProperty("FONDO")), 0,0, JFRAME_HEIGHT, JFRAME_WIDTH, this);
		//g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		// BufferedImage nave = loadImage("../res/nave-50x15.png");
		// g.drawImage(nave, 185, 485, this);
		// Ladrillo.getInstance().paint(g);

		for (Objeto actores : actors) {
			actores.paint(g);

		}

		// Pinto la barra de estado, lo que necesito conocer el alto del juego
		YforStatusBar = this.getHeight() - 22; // le doy la altura del camvas - 12 para dejarl las vidas abajo a la
												// izquierda
		this.pintaVidas(g);

		// Si nos quedamos sin vidas, pintamos el Game Over
		if (Nave.getInstance().VIDAS == 0) {
			BufferedImage fin = SpritesRepository.getInstance().getSprite("game-over.png");
			g.drawImage(fin, 50, 200, this);

		}

		this.pintaTexto(g);
		// Muestro la imagen de búffer que acabo de crear
		strategy.show();

	}
	
	public void pintaTexto(Graphics2D g) {
		g.setFont(new Font ("Arial", Font.BOLD,18));
		g.setPaint(Color.WHITE);
		g.drawString("Arkanoid", 360, 520);
	}

	/**
	 * Método principal del juego, con el bucle contínuo que refresca el mismo en
	 * cada FPS
	 */
	public void game() {
		// Inicialización del juego
		initWorld();

		// El bucle se ejecutará mientras el objeto Canvas sea visible
		while (isVisible()) {
			long startTime = System.currentTimeMillis(); // Tomo el tiempo, en millis, antes de crear el siguiente Frame
															// del juego
			// actualizo y pinto la escena
			updateWorld();

			paintWorld();
			// Calculo el tiempo que se ha tardado en la ejecución
			usedTime = System.currentTimeMillis() - startTime;

			// Con esta condición le indicamos a la bola que cuando salga del Canvas por
			// abajo, actúe el método de restar vidas y reiniciar la bola
			if (getBall().getCoordY() > Pantalla.getInstance().JFRAME_HEIGHT) {
				restarVida();

			}

			// System.out.println(usedTime);
			// Hago que el bucle pare una serie de millis, antes de generar el siguiente FPS
			// El cálculo hecho "duerme" el proceso para no generar más de los SPEED_FPS que
			// se haya específicado
			try {
				int millisToSleep = (int) (1000 / SPEED_FPS - usedTime);
				if (millisToSleep < 0) {
					millisToSleep = 0;
				}
				Thread.sleep(millisToSleep);

			} catch (InterruptedException e) {
				e.printStackTrace();

			}
		}

	}

	public void primeraFase() {
		int posX = 7;
		int posy = 0;
		for (int i = 0; i <= 1; i++) {
			Ladrillo ladrillo = new Ladrillo();
			ladrillo.setColor(Color.GREEN);
			ladrillo.setCoordX(posX);
			ladrillo.setCoordY(posY);
			ladrillo.setDureza(1);
			System.out.println(ladrillo.dureza);
			// Añadimos los ladrillos a la lista de objetos para que aparezcan
			actors.add(ladrillo);
			posX += ladrillo.getWidth() + 6;

		}
//		posX = 7;
//		posY = 35;
//		for (int i = 0; i <= 11; i++) {
//			Ladrillo ladrillo = new Ladrillo();
//			ladrillo.setColor(Color.RED);
//			ladrillo.setCoordX(posX);
//			ladrillo.setCoordY(posY);
//			ladrillo.setDureza(1);
//			actors.add(ladrillo);
//			posX += ladrillo.getWidth() + 6;
//		}
//		posX = 7;
//		posY = 70;
//		for (int i = 0; i <= 11; i++) {
//			Ladrillo ladrillo = new Ladrillo();
//			ladrillo.setColor(Color.BLUE);
//			ladrillo.setCoordX(posX);
//			ladrillo.setCoordY(posY);
//			ladrillo.setDureza(1);
//			actors.add(ladrillo);
//			posX += ladrillo.getWidth() + 6;
//		}
//		posX = 7;
//		posY = 105;
//		for (int i = 0; i <= 11; i++) {
//			Ladrillo ladrillo = new Ladrillo();
//			ladrillo.setColor(Color.YELLOW);
//			ladrillo.setCoordX(posX);
//			ladrillo.setCoordY(posY);
//			ladrillo.setDureza(1);
//			actors.add(ladrillo);
//			posX += ladrillo.getWidth() + 6;
//		}
	}

	public void initWorld() {

		primeraFase();

		ball = new Pelota();
		// Añadimos la bola a la lista de objetos para que aparezca
		actors.add(ball);
		nave = new Nave();
		nave.setCoordX(185);
		nave.setCoordY(485);
		// Añadimos la nave a la lista de objetos para que aparezca
		actors.add(nave);

		// actors.add(nave);
		// Agrego a la lista de jugadores al actor de tipo Player

		// Mantengo una referencia al Player
		// Agrego un listener para eventos de teclado y, cuando se produzcan, los derivo
		// al objeto de tipo Player
		this.addKeyListener(nave);
		this.addMouseMotionListener(nave);
		this.addKeyListener(ball);
		this.addMouseListener(ball);
	}

	public void segundaFase() {

		int posX = 7;
		int posy = 0;
		for (int i = 0; i <= 11; i++) {
			Ladrillo ladrillo = new Ladrillo();
			ladrillo.setColor(Color.GRAY);
			ladrillo.setCoordX(posX);
			ladrillo.setCoordY(posY);
			// Añadimos los ladrillos a la lista de objetos para que aparezcan
			ladrillo.setDureza(2);
			actors.add(ladrillo);
			posX += ladrillo.getWidth() + 6;

		}
		posX = 7;
		posY = 35;
		for (int i = 0; i <= 11; i++) {
			Ladrillo ladrillo = new Ladrillo();
			ladrillo.setColor(Color.ORANGE);
			ladrillo.setCoordX(posX);
			ladrillo.setCoordY(posY);
			ladrillo.setDureza(1);
			actors.add(ladrillo);
			posX += ladrillo.getWidth() + 6;
		}
		posX = 7;
		posY = 70;
		for (int i = 0; i <= 11; i++) {
			Ladrillo ladrillo = new Ladrillo();
			ladrillo.setColor(Color.ORANGE);
			ladrillo.setCoordX(posX);
			ladrillo.setCoordY(posY);
			ladrillo.setDureza(1);
			actors.add(ladrillo);
			posX += ladrillo.getWidth() + 6;
		}
		posX = 7;
		posY = 105;
		for (int i = 0; i <= 11; i++) {
			Ladrillo ladrillo = new Ladrillo();
			ladrillo.setColor(Color.GRAY);
			ladrillo.setCoordX(posX);
			ladrillo.setCoordY(posY);
			ladrillo.setDureza(2);
			actors.add(ladrillo);
			posX += ladrillo.getWidth() + 6;
		}

	}

	public void updateWorld() {

		// Puede ocurrir que existan actores que se deben eliminar para el siguiente
		// pintado de escena.
		// Cuando estoy recorriendo una lista no puedo eliminar elementos sin
		// arriesgarme a provocar un problema de
		// concurrencia de acceso. Por ello lo que hago es crear una nueva lista con los
		// elementos a eliminar. Después
		// se recorre esa lista eliminando los elementos de la lista principal y, por
		// último, limpio la lista
		List<Objeto> actorsForRemoval = new ArrayList<Objeto>();
		for (Objeto actores : this.actors) {
			if (actores.isMarkedForRemoval()) {
				actorsForRemoval.add(actores);
			}
		}
		// Elimino los actores marcados para su eliminación
		for (Objeto actores : actorsForRemoval) {
			this.actors.remove(actores);
		}
		// Limpio la lista de actores para eliminar
		actorsForRemoval.clear();
		// Además de eliminar actores, también puede haber actores nuevos que se deban
		// insertar en la siguiente iteración.
		// Se insertan y después se limpia la lista de nuevos actores a insertar
		this.actors.addAll(newActorsForNextIteration);
		this.newActorsForNextIteration.clear();

		// Aquí van a aparecer todos los movimientos que tengan cada uno de los actores.
		// Todos con su método actor propio
		for (Objeto actores : this.actors) {
			actores.actor();
		}

		if (actors.size() > 2) {
			finDeFase = false;

		} else {

			finDeFase = true;
			segundaFase();
			getBall().reiniciarMillis();

		}

		// Una vez que cada actor ha actuado, intento detectar colisiones entre los
		// actores y notificarlas. Para detectar
		// estas colisiones, no nos queda más remedio que intentar detectar la colisión
		// de cualquier actor con cualquier otro
		// sólo con la excepción de no comparar un actor consigo mismo.
		// La detección de colisiones se va a baser en formar un rectángulo con las
		// medidas que ocupa cada actor en pantalla,
		// De esa manera, las colisiones se traducirán en intersecciones entre
		// rectángulos.
		for (Objeto actor1 : this.actors) {
			// Creo un rectángulo para este actor.
			Rectangle rect1 = new Rectangle(actor1.getCoordX(), actor1.getCoordY(), actor1.getWidth(),
					actor1.getHeight());
			// Compruebo un actor con cualquier otro actor
			for (Objeto actor2 : this.actors) {
				// Evito comparar un actor consigo mismo, ya que eso siempre provocaría una
				// colisión y no tiene sentido
				if (!(actor2 instanceof Pelota)) {
					// Formo el rectángulo del actor 2
					Rectangle rect2 = new Rectangle(actor2.getCoordX(), actor2.getCoordY(), actor2.getWidth(),
							actor2.getHeight());
					// Si los dos rectángulos tienen alguna intersección, notifico una colisión en
					// los dos actores
					if (rect1.intersects(rect2)) {
						actor1.collisionWith(actor2); // El actor 1 colisiona con el actor 2
						actor2.collisionWith(actor1); // El actor 2 colisiona con el actor 1
						break;
					}
				}
			}
		}

	}

	/**
	 * Método en el cual pintamos las vidas
	 * 
	 * @param g
	 */
	public void pintaVidas(Graphics2D g) {
		int xBase = 5 + Nave.VIDAS; // posición de la coordenada X de cada vida (4 en nuestro caso)
		for (int i = 0; i < nave.getVidas(); i++) {
			BufferedImage vidas = SpritesRepository.getInstance().getSprite("vida.png");
			g.drawImage(vidas, xBase + i * vidas.getWidth(), YforStatusBar, this); // pintado de las vidas

		}
	}

	/**
	 * Método en el cual incluimos el método anterior para luego llamarlo en el
	 * paintWorld principal
	 * 
	 * @param g
	 */

	public void paintStatus(Graphics2D g) {
		pintaVidas(g);
	}

	/**
	 * Con este método restamos las vidas y devolvemos la bola al incio pegada a la
	 * nave
	 */
	public void restarVida() {
		if (Nave.getInstance().VIDAS > 0) {
			Nave.getInstance().VIDAS--;
			// método que está en clase pelota con el que reiniciamos la bola desde el
			// inicio
			getBall().reiniciarMillis();

		}
		if (Nave.getInstance().VIDAS == 0) {
			ball.setMarkedForRemoval(true);
		}

	}

	/**
	 * 
	 * @param explosion
	 */

	public void addNewActorToNextIteration(Objeto explosion) {
		this.newActorsForNextIteration.add(explosion);

	}

	public static Pantalla getInstance() {
		if (instance == null) {
			instance = new Pantalla();
		}
		return instance;
	}

	/**
	 * 
	 * @return
	 */
	// hola

	public static int getJframeWidth() {
		return JFRAME_WIDTH;
	}

	public static int getJframeHeight() {
		return JFRAME_HEIGHT;
	}

	public static void setInstance(Pantalla instance) {
		Pantalla.instance = instance;
	}

	public Nave getNave() {
		return nave;
	}

	public void setNave(Nave nave) {
		this.nave = nave;
	}

	public Pelota getBall() {
		return ball;
	}

	public void setBall(Pelota ball) {
		this.ball = ball;
	}

	public static void main(String[] args) {

		Pantalla.getInstance().game();
	}

}
