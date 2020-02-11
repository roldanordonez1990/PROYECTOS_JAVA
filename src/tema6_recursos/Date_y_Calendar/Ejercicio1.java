package tema6_recursos.Date_y_Calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.JOptionPane;

public class Ejercicio1 {
	
			//creamos un calendar 
			private static Calendar fechaCalendar = Calendar.getInstance();
			//creamos un Date y lo inicializamos a null para luego obtener el resultado deseado
			private static Date fechaApartirDeCalendario = null;
			
//	public static void fecha() {
//		
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//		Date fecha = null;
//		
//		try {
//			//introducimos una decha de tipo String
//			fecha = sdf.parse(JOptionPane.showInputDialog("Escribe una fecha"));
//		} catch (ParseException e) {
//			System.out.println("Error al escribir la fecha parseada");
//			e.printStackTrace();
//			// TODO: handle exception
//		}
//		//formateamos la fecha al formato que hemos especificado arriba ("dd/MM/yyyy HH:mm:ss")
//		System.out.println("La fecha que hemos escrito es: " + sdf.format(fecha));
//		
//	}
	
	public static void calendar() {
		
		
		//Para pasar de un String que nos de el usuario a un Date, tenemos que hacer un Parseo
		//El orden que sigue el método set es: Año, mes, día, hora, minutos, segundos
		fechaCalendar.set(Integer.parseInt(JOptionPane.showInputDialog("Introduce un AÑO"))
				, Integer.parseInt(JOptionPane.showInputDialog("Introduce un MES"))
				, Integer.parseInt(JOptionPane.showInputDialog("Introduce un DÍA"))
				, Integer.parseInt(JOptionPane.showInputDialog("Introduce una HORA"))
				, Integer.parseInt(JOptionPane.showInputDialog("Introduce un MINUTO"))
				, Integer.parseInt(JOptionPane.showInputDialog("Introduce un SEGUNDO")));
		
		//Obtenemos un Date a partir de los millis del calendar
		fechaApartirDeCalendario = new Date(fechaCalendar.getTimeInMillis());
		
		System.out.println("La fecha a partir del calendar es: " +
		new SimpleDateFormat ("dd/MM/yyyy HH:mm:ss").format(fechaApartirDeCalendario));
	}
	
	private static void fechasPorSeparado() {
		
		
		System.out.println("Año: " + fechaCalendar.get(Calendar.YEAR)); 
		System.out.println("Año a partir del calendario: " + new SimpleDateFormat ("yyyy").format(fechaApartirDeCalendario));
		
		System.out.println("Mes: " + fechaCalendar.get(Calendar.MONTH)); 
		System.out.println("Mes a partir del calendario: " +new SimpleDateFormat ("MM").format(fechaApartirDeCalendario));
		
		System.out.println("Día: " + fechaCalendar.get(Calendar.DAY_OF_MONTH)); 
		System.out.println("Día a partir del calendario: " +new SimpleDateFormat ("dd").format(fechaApartirDeCalendario));
		
		System.out.println("Hora: " + fechaCalendar.get(Calendar.HOUR_OF_DAY)); 
		System.out.println("Hora a partir del calendario: " +new SimpleDateFormat ("HH").format(fechaApartirDeCalendario));
		
		System.out.println("Minuto: " + fechaCalendar.get(Calendar.MINUTE)); 
		System.out.println("Minuto a partir del calendario: " +new SimpleDateFormat ("mm").format(fechaApartirDeCalendario));
		
		System.out.println("Segundo: " + fechaCalendar.get(Calendar.SECOND)); 
		System.out.println("Segundo a partir del calendario: " +new SimpleDateFormat ("ss").format(fechaApartirDeCalendario));
	}
	
	public static void nuevasFechas() {
		 fechaCalendar.add(Calendar.DAY_OF_MONTH, 3);
		 System.out.println("Días indicados + 3: " + fechaCalendar.getTime());
		 
		 fechaCalendar.add(Calendar.WEEK_OF_MONTH, -2);
		 System.out.println("Semanas indicadas + 3: " + fechaCalendar.getTime());
		 
		 fechaCalendar.add(Calendar.DAY_OF_YEAR, 300);
		 System.out.println("Días indicados + 300: " + fechaCalendar.getTime());
		 
		 fechaCalendar.add(Calendar.YEAR, 4);
		 System.out.println("Años indicados + 4: " + fechaCalendar.getTime());
		 
	}
	
	
	public static void main(String[] args) {
		
		//fecha();
		calendar();
		fechasPorSeparado();
		nuevasFechas();
	}

}
