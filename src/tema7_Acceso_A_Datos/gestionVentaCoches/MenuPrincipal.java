package tema7_Acceso_A_Datos.gestionVentaCoches;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

import tema7_Acceso_A_Datos.gestionVentaCoches.modelo.controladores.ControladorBBDD;
import tema7_Acceso_A_Datos.gestionVentaCoches.modelo.controladores.ErrorBBDDException;

public class MenuPrincipal {
	
	static Connection conn = null;

	public static void menuPrincipal() throws SQLException, ErrorBBDDException, ParseException {

		//creamos un objeto escanner donde vamos a guardar lo que introduzcamos
		Scanner sc = new Scanner(System.in);
		//creamos una variable primitiva para convertirla en el objeto scanner
		int opcionElegida = 0;

		do {
			
			//Esto va a ser lo primero que se va a ejecutar, el men� principal
			System.out.println("\n\t\t\tGESTI�N DE VENTAS DE COCHES");

			System.out.println("\n\t1.- Gesti�n de fabricantes.");
			System.out.println("\t2.- Gesti�n de concesionarios.");
			System.out.println("\t3.- Gesti�n de coches.");
			System.out.println("\t4.- Gesti�n de clientes.");
			System.out.println("\t5.- Gesti�n de ventas.");
			System.out.println("\t0.- Salir");
			System.out.println("\n\tElija una opci�n: ");
			
			opcionElegida = sc.nextInt();
			
			switch (opcionElegida) {
			case 0:
				System.out.println("\nHasta luego Lucas");
				break;
				
			case 1:
				//Al pulsar 1, accederemos al siguiente men� de fabricantes
				GestionFabricante.menuGestion();
				break;
				
			case 2:
				GestionConcesionario.menuGestionConcesionario();
				break;
				
			case 3:
				GestionCoche.menuGestionCoche();
				break;
				
			case 4:
				GestonCliente.menuGestionCliente();
				break;
				
			case 5:
				GestionVenta.menuGestionVenta();
				break;
				
			}
			
				

		} while (opcionElegida != 0);

	}
	


	public static void main(String[] args) throws SQLException, ParseException {
		ControladorBBDD.iniciaConnectionPool();
		try {
			menuPrincipal();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ErrorBBDDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
