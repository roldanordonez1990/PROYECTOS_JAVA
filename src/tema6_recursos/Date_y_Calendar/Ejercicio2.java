package tema6_recursos.Date_y_Calendar;

import java.util.Calendar;
import java.util.TimeZone;

public class Ejercicio2 {

	public static void diferencaHoraria() {
		Calendar ahoraEnRoma = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"));
		Calendar ahoraEnSydney = Calendar.getInstance(TimeZone.getTimeZone("Australia/Sydney"));
		
		System.out.println("Hora actual de Roma: " + ahoraEnRoma.get(Calendar.HOUR_OF_DAY));
		System.out.println("Hora actual de Sydney: " + ahoraEnSydney.get(Calendar.HOUR_OF_DAY));
		
		//Todas las zonas horarias las podemos buscar con: TimeZone.getAvailableIds
		
		int diferenciaHoraria = ahoraEnRoma.get(Calendar.HOUR_OF_DAY) - ahoraEnSydney.get(Calendar.HOUR_OF_DAY);
		System.out.println("La diferencia horaria entre Roma y Sydney es: " + diferenciaHoraria);
	}
	
	
	public static void main(String[] args) {
		diferencaHoraria();

	}

}
