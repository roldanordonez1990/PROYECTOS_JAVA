package arkanoid.version1;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class SpritesRepository {

	// El almacen de imágenes se mapeará con un objeto de tipo HashMap<String,
	// BufferedImage>
	// En este tipo de HashMap todas las claves (keys) serán de tipo String y todas
	// los objetos
	// almacenados serán de tipo BufferedImage
	private HashMap<String, BufferedImage> sprites = new HashMap<String, BufferedImage>();

	// Instanciamos el objeto con el patrón Singleton
	private static SpritesRepository instance = null;

	// Carpeta en la que se encuentran los recursos: imágenes, sonidos, etc.
	private static String RESOURCES_FOLDER = "../res/";

	/**
	 * 
	 */

	public static SpritesRepository getInstance() {

		if (instance == null) {
			instance = new SpritesRepository();

		}
		return instance;

	}

	/**
	 * 
	 *
	 */

	private BufferedImage loadImage(String resourceName) {
		// Para localizar el archivo se utiliza un objeto de tipo URL
		URL url = null;

		// Se intenta cargar el recurso del disco duro, si no se pudiera se capturará la
		// excepción y se
		// mostrará un mensaje en pantalla
		try {
			url = getClass().getResource(resourceName);
			return ImageIO.read(url);
		} catch (Exception e) {
			// Aquí dentro capturamos y tratamos el error que pueda haberse ocasionado
			System.out.println("No se pudo cargar la imagen " + resourceName + " de " + url);
			System.out.println("El error fue : " + e.getClass().getName() + " " + e.getMessage());
			System.exit(0); // Fin del programa
		}
		return null; // Sólo se llegará a esta línea si no se ha podido cargar el recurso
						// correctamente
	}

	/**
	 * Método para obtener una imagen.
	 * 
	 * @param nombre
	 * @return
	 */
	public BufferedImage getSprite(String resourceName) {
		// En primera instancia intentamos obtener el objeto BufferedImage a partir del
		// HashMap.
		BufferedImage img =sprites.get(resourceName);

		// En caso de que el objeto BufferedImage no exista dentro del HashMap, se carga
		// desde el disco duro
		if (img == null) {
			img = loadImage(RESOURCES_FOLDER + resourceName);
			// Una vez que cargo el recurso en la memoria, lo agrego al HashMap, así no
			// habrá que volver a
			// buscarlo en el disco duro. Como "clave" del objeto en el HashMap utilizo el
			// nombre del fichero
			sprites.put(resourceName, img);
		}
		return img;
	}

}
