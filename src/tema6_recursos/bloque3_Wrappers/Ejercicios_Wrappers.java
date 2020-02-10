package tema6_recursos.bloque3_Wrappers;

import java.util.stream.Collector.Characteristics;

import javax.swing.JOptionPane;

public class Ejercicios_Wrappers {

	// Correspondencia de valores
	// tipos primitivos que NO son objetos (int, float, double, long, char)
	// Clases que permiten crear objetos (Integer, Float, Double, Long, Character)

//	int -------> Integer
//	float -----> Float
//	double ----> Double
//	long ------> Long
//	char ------> Character
//	boolean ---> Boolean

	public static void ejercicio01() {

		System.out.println("El MÁXIMO para un byte es: " + Byte.MAX_VALUE);
		System.out.println("El MÍNIMO para un byte es: " + Byte.MIN_VALUE);
		System.out.println("El MÁXIMO para un int es: " + Integer.MAX_VALUE);
		System.out.println("El MÍNIMO para un int es: " + Integer.MIN_VALUE);
		System.out.println("El MÁXIMO para un short es: " + Short.MAX_VALUE);
		System.out.println("El MÍNIMO para un short es: " + Short.MIN_VALUE);
		System.out.println("El MÁXIMO para un long es: " + Long.MAX_VALUE);
		System.out.println("El MÍNIMO para un long es: " + Long.MIN_VALUE);
		System.out.println("El MÁXIMO para un float es: " + Float.MAX_VALUE);
		System.out.println("El MÍNIMO para un float es: " + Float.MIN_VALUE);
		System.out.println("El MÁXIMO para un double es: " + Double.MAX_VALUE);
		System.out.println("El MÍNIMO para un double es: " + Double.MIN_VALUE);

		Byte Byte = new Byte((byte) 127);
		System.out.println("El número de byte que se usa para representarlo es: " + Byte.BYTES);
		Integer integer = new Integer(2147483647);
		System.out.println("El número de byte que se usa para representarlo es: " + integer.BYTES);
		Short Short = new Short((short) 32767);
		System.out.println("El número de byte que se usa para representarlo es: " + Short.BYTES);
		Long Long = new Long((long) 922337203);
		System.out.println("El número de byte que se usa para representarlo es: " + Long.BYTES);
		Float Float = new Float(3.4028235E38);
		System.out.println("El número de byte que se usa para representarlo es: " + Float.BYTES);
		Double Double = new Double(1.7976931348623157E308);
		System.out.println("El número de byte que se usa para representarlo es: " + Double.BYTES);

	}

	public static void ejercicio2contrasenaUno() {

		String password = "";
		int intentos = 0;

		do {

			password = JOptionPane.showInputDialog("Introduce la contraseña: ");
			if (password.equals("Fran1234!")) {
				JOptionPane.showMessageDialog(null, "Correcto");
				intentos++;
				break;

			} else {
				JOptionPane.showMessageDialog(null, "Incorrecto");
				intentos++;
			}
			if (intentos == 3) {
				JOptionPane.showMessageDialog(null, "Has bloqueado la cuenta, feo");
				break;
			}

		} while (password.equals("Fran12345!") == false);

	}

	public static void contrasena() {

		//Creamos un srt con un mensaje para el usuario para introducir los datos
		String srt = JOptionPane.showInputDialog("Introduce una contraseña que contenga: "
				+ "\n-Una mayúscula\n-Una minúscula\n-Un dígito\n-Un caracter no alfanumérico");

		//Incicializamos los requisitos booleanos en false
		boolean mayuscula = false;
		boolean minuscula = false;
		boolean digito = false;
		boolean noAlfanumerico = false;
		int i;
		int intentos = 0;

		//Hacemos un Do While indicando que mientras los requisitos no sean true, vuelva a repetir la contraseña
		do {

			// creamos una cadena de caracteres que va a ser los datos que ingresemos en el mensaje principal
			char password[] = new char[srt.length()];
			for (i = 0; i < password.length; i++) {
				//Este método devuelve datos de tipo de caracteres basados ​​en la entrada de índice
				password[i] = srt.charAt(i);
			}

			//recorremos el array de caracteres comprobando los requisitos y cambiamos la condición a true
			for (i = 0; i < password.length; i++) {

				if (Character.isUpperCase(password[i])) {
					mayuscula = true;

				}
				if (Character.isLowerCase(password[i])) {
					minuscula = true;

				}
				if (Character.isDigit(password[i])) {
					digito = true;

				}
				if (!Character.isLetterOrDigit(password[i])) {
					noAlfanumerico = true;

				}

			}
			//si se han introducido los datos requeridos, la contraseña es correcta
			if (mayuscula && minuscula && digito && noAlfanumerico && intentos < 4) {
				JOptionPane.showMessageDialog(null, "CORRECTOO");
				System.out.println("Correcto, la contraseña es: " + srt);
				
			} 
			else {
				srt = JOptionPane.showInputDialog("ERROR\nIntroduce una contraseña que contenga: "
						+ "\n-Una mayúscula\n-Una minúscula\n-Un dígito\n-Un caracter no alfanumérico");
				intentos++;
			}
			if (intentos == 4) {
				JOptionPane.showMessageDialog(null, "HAS BLOQUEADO LA CUENTA, PAYASO");
				break;
			}

		} while (!(mayuscula && minuscula && digito && noAlfanumerico));

	}
	
	public static void hexadecimal() {
		
		Integer numero[] = new Integer[100];
		
		for (int i = 1; i <= numero.length; i++) {
			System.out.println(Integer.toHexString(i));
		}
		
	}
	

	public static void main(String[] args) {
		// ejercicio01();
		contrasena();
		//hexadecimal();

	}

}
