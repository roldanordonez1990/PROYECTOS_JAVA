package tema6_recursos.bloque4_Excepciones.Ej2;

import java.util.Iterator;

import javax.swing.JOptionPane;

public class IntroduceUnaPalabra {

	public static String introducePalabra() throws CaracteresEnBlancoExcepcion, MenosDeTresPalabrasExcepcion,
			NoApareceLaPalabraExcepcion, PalabraOfensivaExcepcion {

		String frase = JOptionPane.showInputDialog("Introduce la palabra 'bombilla'");

		// Comprobamos si sólo hay espacios en blanco
		if (frase.trim().equals("")) {
			throw new CaracteresEnBlancoExcepcion("No has introducido ninguna palabra");
		}

		// Comprobamos si lo que introducimos tiene menos de 3 palabras
		String palabras[] = frase.split("[ ]{1,}");

		if (palabras.length < 3) {
			throw new MenosDeTresPalabrasExcepcion("Has introducido menos de 3 palabras");
		}

		// Comprobamos que en la frase no aparezca la palabra bombilla

		if (frase.toUpperCase().indexOf("BOMBILLA") == -1) {
			throw new NoApareceLaPalabraExcepcion("No aparece la palabra bombilla");
		}

		// Comprobamos que aparezcan palabras ofensivas en la frase

		String palabrasOfensivas[] = new String[] { "TONTO", "TONTA", "IDIOTA" };

		for (String palabraOfensiva : palabrasOfensivas) {
			if (frase.toUpperCase().indexOf(palabraOfensiva) == -1) {

				throw new PalabraOfensivaExcepcion("Has introducido una palabra ofensiva");

			}
		}

		return frase;

	}

	public static void main(String[] args) {

		try {
			try {
				try {
					try {
						System.out.println(introducePalabra());
					} catch (PalabraOfensivaExcepcion e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (NoApareceLaPalabraExcepcion e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (CaracteresEnBlancoExcepcion e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (MenosDeTresPalabrasExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
