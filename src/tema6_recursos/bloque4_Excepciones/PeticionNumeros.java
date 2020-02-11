package tema6_recursos.bloque4_Excepciones;

import java.io.ObjectInputStream.GetField;

import javax.swing.JOptionPane;

public class PeticionNumeros {
	
	public static void pideNumeroPar() throws NumParException{
		
		Integer numeroPar = new Integer(JOptionPane.showInputDialog("Introduce un n�mero par"));
		
		try {
			if(numeroPar%2 != 0) {
				
				throw new NumParException("El n�mero es impar");
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			
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
