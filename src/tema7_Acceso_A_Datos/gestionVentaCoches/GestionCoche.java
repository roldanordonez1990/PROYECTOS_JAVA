package tema7_Acceso_A_Datos.gestionVentaCoches;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

import tema7_Acceso_A_Datos.gestionVentaCoches.modelo.Coche;
import tema7_Acceso_A_Datos.gestionVentaCoches.modelo.Concesionario;
import tema7_Acceso_A_Datos.gestionVentaCoches.modelo.controladores.ControladorCoche;
import tema7_Acceso_A_Datos.gestionVentaCoches.modelo.controladores.ControladorConcesionario;
import tema7_Acceso_A_Datos.gestionVentaCoches.modelo.controladores.ControladorFabricante;
import tema7_Acceso_A_Datos.gestionVentaCoches.modelo.controladores.ErrorBBDDException;

public class GestionCoche {

	// Iniciaremos el objeto de conexión a null para poder usarlo cuando queramos
	static Connection conn = null;

	public static void menuGestionCoche() throws SQLException, ErrorBBDDException {

		Scanner sc = new Scanner(System.in);
		int option = 0;

		do {

			System.out.println("\n\t\t\tGESTIÓN DE COCHES");

			System.out.println("\n\t1.- Listado de coches.");
			System.out.println("\t2.- Añadir coche.");
			System.out.println("\t3.- Modificar coche.");
			System.out.println("\t4.- Borrar coche.");
			System.out.println("\t0.- Salir");
			System.out.println("\n\tElija una opción: ");

			option = sc.nextInt();

			switch (option) {
			case 0:
				System.out.println("Adiós");
				break;
			case 1:
				listado(true);
				break;
			case 2:
				darDeAlta();
				break;
			case 3:
				modificar();
				break;
			case 4:
				baja();
				break;
			}

		} while (option != 0);
	}

	/**
	 * 
	 */

	public static void listado(boolean pausafinal) throws ErrorBBDDException {
		List<Coche> coches = ControladorCoche.getAll();
		System.out.println("\n\tListado de coches: \n");
		for (Coche coche : coches) {
			System.out.println("\t" + coche.toString());
		}

	}

	/**
	 * @throws SQLException
	 * 
	 */

	private static void darDeAlta() throws ErrorBBDDException, SQLException {
		System.out.println("\n\tAlta de coche\n");

		Connection conn = null;

		Scanner sc = new Scanner(System.in);

		Coche coche = new Coche();

		System.out.println("\n\tPulsa S para ver la lista de fabricantes");
		String str = sc.next();
		if (str.equalsIgnoreCase("S")) {
			GestionFabricante.listado(true);
		}
		System.out.print("\nIntroduzca 'idfabricante' del fabricante: ");
		coche.setIdfabricante(sc.nextInt());
		System.out.print("\nIntroduzca 'bastidor' del coche: ");
		coche.setBastidor(sc.nextLine());
		System.out.print("\nIntroduzca 'modelo' del coche: ");
		coche.setModelo(sc.nextLine());
		System.out.print("\nIntroduzca 'color' del coche: ");
		coche.setColor(sc.nextLine());

		ControladorCoche.almacenarNuevo(coche);

		System.out.println("\n\tInsertado correctamente!");

	}

	/**
	 * 
	 */
	
	public static void modificar() throws ErrorBBDDException, SQLException {
		System.out.println("\n\tModificación del coche: ");
		
		Coche coche = seleccionPorUsuario();
		
		if(coche != null) {
			
			System.out.println("\n\tIntroduce un idfabricante nuevo para el coche: ");
			Scanner sc = new Scanner(System.in);
			String str;
			str = sc.next();
			int idfabricante = Integer.parseInt(str);
			if(!str.equals("")) 
				coche.setIdfabricante(idfabricante);
			
			
			System.out.println("\n\tIntroduce un bastidor nuevo para el coche: ");
			sc = new Scanner(System.in);
			String bastidor;
			bastidor = sc.nextLine();
			if(!bastidor.equals("")) 
				coche.setBastidor(bastidor);
			
			System.out.println("\n\tIntroduce un Modelo nuevo para el coche: ");
			String modelo;
			modelo = sc.nextLine();
			if(!modelo.equals("")) 
				coche.setModelo(modelo);
			
			System.out.println("\n\tIntroduce un Color nuevo para el coche: ");
			String color;
			color = sc.nextLine();
			if(!color.equals("")) {
				coche.setColor(color);
			}
			
			ControladorCoche.almacenarModificacion(coche);
			System.out.println("\n\tHas modificado el coche correctamente");
		}
		
	}

	private static void baja() throws ErrorBBDDException, SQLException {
		System.out.println("\n\tEliminación del coche\n");
		Scanner sc = new Scanner(System.in);
		Coche coche = seleccionPorUsuario();

		if (coche != null) {
			System.out.print("\n¿Realmente desea eliminar el registro? (S/N): ");
			String str = sc.next();
			if (str.equalsIgnoreCase("S")) {
				ControladorCoche.eliminarCoche(coche);
				System.out.println("\n\tEliminado correctamente!");

			}
		}
	}

	/**
	 * 
	 */

	private static Coche seleccionPorUsuario() throws ErrorBBDDException, SQLException {

		System.out.println("\n\tIntroduce un ID del coche");
		Scanner sc = new Scanner(System.in);
		Coche coche = null;
		int id = sc.nextInt();
		coche = ControladorCoche.obtenerId(id);
		return coche;

	}

}
