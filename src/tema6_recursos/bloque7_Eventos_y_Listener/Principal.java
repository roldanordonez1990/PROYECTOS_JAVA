package tema6_recursos.bloque7_Eventos_y_Listener;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Principal {
	
	private static List<IntroducirDigitosMagicosListener> introducirDigitosMagicosListeter = new ArrayList<IntroducirDigitosMagicosListener>();

	public static void main(String[] args) {
		
		Clase_Escuchadora escuchador = new Clase_Escuchadora("Fran");
		
		
		
		
		do {
			int contador = 0;
			String str = new String(JOptionPane.showInputDialog("Escriba una frase, señor usuario"));
			char frase[] = str.toCharArray();
			
			for (char fraseAescribir : frase) {
				
				if(Character.isDigit(fraseAescribir)) {
					contador++;
					
					if(contador ==3) {
						fireIntroducirDigitosMagicosListeners(new IntroducirDigitosMagicosEvent(fraseAescribir));
						
					}
					
				}
				
			}
			System.out.println(frase);
			
		}
		while(true);
	
		
	}
	
	public static void addIntroducirDigitosMagicosListener(IntroducirDigitosMagicosListener Listener) {
		introducirDigitosMagicosListeter.add(Listener);
	}
	
	public static void removeIntroducirDigitosMagicosListener(IntroducirDigitosMagicosListener Listener) {
		introducirDigitosMagicosListeter.remove(Listener);
	}
	
	public static void fireIntroducirDigitosMagicosListeners(IntroducirDigitosMagicosEvent event) {
		for (IntroducirDigitosMagicosListener listener : introducirDigitosMagicosListeter) {
			listener.introducirDigitosMagicos(event);
		}
	}

}
