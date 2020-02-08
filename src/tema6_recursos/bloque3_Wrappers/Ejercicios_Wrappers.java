package tema6_recursos.bloque3_Wrappers;

public class Ejercicios_Wrappers {
	
	//Correspondencia de valores
	//tipos primitivos que NO son objetos (int, float, double, long, char)
	//Clases que permiten crear objetos (Integer, Float, Double, Long, Character)
	
//	int -------> Integer
//	float -----> Float
//	double ----> Double
//	long ------> Long
//	char ------> Character
//	boolean ---> Boolean
	
	public static void ejercicio01() {

		
		System.out.println("El MÁXIMO para un byte es: " + Byte.MAX_VALUE);
		System.out.println("El MÍNIMO para un byte es: " + Byte.MIN_VALUE);
		System.out.println("El MÁXIMO para un int es: " +  Integer.MAX_VALUE);
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
 
	public static void main(String[] args) {
		ejercicio01();
		

	}

}
