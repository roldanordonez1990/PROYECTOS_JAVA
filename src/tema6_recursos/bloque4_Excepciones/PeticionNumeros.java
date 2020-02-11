package tema6_recursos.bloque4_Excepciones;

import java.io.ObjectInputStream.GetField;

import javax.swing.JOptionPane;

public class PeticionNumeros {
	
	public static int pideNumeroPar() throws NumParException{
		
		Integer numeroPar = new Integer(JOptionPane.showInputDialog("Introduce un n�mero par"));
		
		if(numeroPar % 2 == 0) {
			System.out.println("El n�mero es PAR");
			return numeroPar;
		}
		
		else {
			throw new NumParException("El n�mero es IMPAR");
			
		}
		
	}

	public static void main(String[] args) {
		//pideNumeroPar();
		try {
			pideNumeroPar();
		} catch (NumParException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
