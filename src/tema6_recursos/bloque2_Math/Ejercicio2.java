package tema6_recursos.bloque2_Math;

public class Ejercicio2 {

	private static float y1;
	private static float y2;
	private static float x;
	private static int contador = 1;

	public static void puntoDeCorte() {

		do {

			y1 = (float) Math.sqrt(x);
			y2 = (float) -Math.log(x);
			x += 0.001;

		} while (!(Math.abs(y1 - y2) < 0.001));
		System.out.println("x: " + x + " y1: " + y1 + " y2: " + y2);
	}
	
	public static void aproximarNumeroPi() {
		
		y1 = 0; //este va a ser el dividendo
		x = 1; //este va a ser el divisor
		
		do {
			contador++;
			if(contador % 2 == 0) {
				y1 += 4/x;
			}else {
				y1 -= 4/x;
			}
			
			x = x+2;
			
		} while (!(Math.abs(Math.PI - y1) < 0.00001));
		
		System.out.println("El valor aproximado de PI con una precisión de 0.0001 es :" + y1);
		
	}
	
	public static void hipotenusa() {
		double cateto1 = 4;
		double cateto2 = 5;
		
		System.out.println("La hipotenusa de los dos catetos es: " + Math.hypot(cateto1, cateto2));
		
	}
	
	public static void ejemplos() {
		System.out.println("Valor del número PI: " + Math.PI);
		System.out.println("El valor máximo es: " + Math.max(23, 23.5));
		System.out.println("El valor mínimo es: " + Math.min(23, 23.5));
		System.out.println("El valor absoluto es: " + Math.abs(-32.45));
		System.out.println("La raíz cuadrada de 9 es: " + Math.sqrt(9));
	}

	public static void main(String[] args) {
		//puntoDeCorte();
		//aproximarNumeroPi();
		//ejemplos();
		hipotenusa();
		
	}

}
