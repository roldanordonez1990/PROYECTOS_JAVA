package arkanoid.version1;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;
import java.util.HashMap;

public class SoundsRepository {
	
	// Almacén de sonidos, se trata de un HashMap que tiene String como "claves" de los objetos y "AudioClip" como objetos
		private HashMap<String, AudioClip> sounds = new HashMap<String, AudioClip>();

		// Variable principal del patrón Singleton
		private static SoundsRepository instance = null;
		
		// Carpeta en la que se encuentran los recursos: imágenes, sonidos, etc.
		private static String RESOURCES_FOLDER = "../res/";
		
		// Recursos de sonido que puede utilizarse en el juego
		public static String FONDO = "Arkanoid-start-of-stage.wav";
		public static String CHOQUE_LADRILLO = "Arkanoid-SFX-01.wav";
		public static String EXPLOSION = "explosion.wav";
		public static String CHOQUE_NAVE = "Arkanoid-SFX-06.wav";
		
		/**
		 * Default constructor
		 */
		public SoundsRepository() {
			// Carga de recursos en memoria
			this.getAudioClip(FONDO);
			this.getAudioClip(CHOQUE_NAVE);
			this.getAudioClip(EXPLOSION);
		}
		
		/**
		 * Método principal del patrón Singleton
		 * @return
		 */
		public static SoundsRepository getInstance() {
			if (instance == null) {
				instance = new SoundsRepository();
			}
			return instance;
		}
		
		/**
		 * Carga un sonido, en forma de AudioClip, desde el disco duro
		 * @param name
		 * @return
		 */
		private AudioClip loadResource(String name) {
			URL url=null;
			url = getClass().getResource(name);
			return Applet.newAudioClip(url);
		}
		
		/**
		 * Obtiene el AudioClip desde el almacén HashMap, si no existe lo carga desde el disco duro.
		 * @param resourceName
		 * @return
		 */
		private AudioClip getAudioClip(String resourceName) {
			// En primera instancia intentamos obtener el objeto AudioClip a partir del HashMap.
			AudioClip clip = sounds.get(resourceName);
			
			// En caso de que el objeto AudioClip no exista dentro del HashMap, se carga desde el disco duro
			if (clip == null) {
				clip = loadResource(RESOURCES_FOLDER + resourceName);
				// Una vez que cargo el recurso en la memoria, lo agrego al HashMap, así no habrá que volver a 
				// buscarlo en el disco duro. Como "clave" del objeto en el HashMap utilizo el nombre del fichero
				sounds.put(resourceName, clip);
			}
			return clip;	
		}
		
		/**
		 * Lanza un sonido, el sonido sólo se escuchará una vez
		 * @param name
		 */
		public void playSound(final String name) {
			getAudioClip(name).play();
		}
		
		/**
		 * Lanza un sonido en bucle.
		 * @param name
		 */
		public void loopSound(final String name) {
			getAudioClip(name).loop();
		}

}
