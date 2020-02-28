package tema7_Acceso_A_Datos.gestionVentaCoches;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import tema7_Acceso_A_Datos.gestionVentaCoches.modelo.Concesionario;
import tema7_Acceso_A_Datos.gestionVentaCoches.modelo.controladores.ControladorConcesionario;
import tema7_Acceso_A_Datos.gestionVentaCoches.modelo.controladores.ErrorBBDDException;


public class GestionConcesionario {

	// Iniciaremos el objeto de conexi�n a null para poder usarlo cuando queramos
	static Connection conn = null;

	public static void menuGestionConcesionario() throws SQLException, ErrorBBDDException {
//		// Al acceder a este men�, lo primero que se har� es intentar conectar con la
//		// BBDD
//		try {
//			// Aqu� le daremos valor al objeto con la clase que conecta con la bbdd
//			conn = ConnectionManagerV2.getConexion();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ImposibleConectarException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		Scanner sc = new Scanner(System.in);
		int option = 0;

		do {

			System.out.println("\n\t\t\tGESTI�N DE FABRICANTES");

			System.out.println("\n\t1.- Listado de concesionario.");
			System.out.println("\t2.- A�adir concesionario.");
			System.out.println("\t3.- Optener un id.");
			System.out.println("\t4.- Borrar concesionario.");
			System.out.println("\t0.- Salir");
			System.out.println("\n\tElija una opci�n: ");

			option = sc.nextInt();

			switch (option) {
			case 0:
				System.out.println("Adi�s");
				break;
			case 1:
				listado(true);
				break;
			case 2:
				darDeAlta();
				break;
//			case 3:
//				obtenerId(conn);
//				break;
			case 4:
				baja();
				break;
			}

		} while (option != 0);
	}

	/**
	 * 
	 */

	private static void listado(boolean pausafinal) throws ErrorBBDDException {
		List<Concesionario> concesionarios = ControladorConcesionario.getAll();
		System.out.println("\n\tListado de concesionarios: \n");
		for (Concesionario conce : concesionarios) {
			System.out.println("\t" + conce.toString());
		}
//		if (pausafinal) {
//			System.out.println("\n\tPulse 'Intro' tecla para continuar");
//			Utils.pausa();
//		}
	}
	
	/**
	 * 
	 * @param conn
	 * @throws SQLException
	 */
	
	private static void darDeAlta () throws ErrorBBDDException {
		System.out.println("\n\tAlta de fabricante\n");
		
		Scanner sc = new Scanner(System.in);
		
		Concesionario conce = new Concesionario();
		System.out.print("\nIntroduzca 'CIF' del fabricante: ");
		conce.setCif(sc.next());
		System.out.print("\nIntroduzca 'Nombre' del fabricante: ");
		conce.setNombre(sc.next());
		System.out.print("\nIntroduzca 'Localidad' del fabricante: ");
		conce.setLocalidad(sc.next());
		
		ControladorConcesionario.almacenarNuevo(conce);  

		System.out.println("\n\tInsertado correctamente!");
		
	}
	
	/**
	 * 
	 * @throws ErrorBBDDException
	 * @throws SQLException 
	 */
	
	private static void baja () throws ErrorBBDDException, SQLException {
		System.out.println("\n\tEliminaci�n del concesionario\n");
		Scanner sc = new Scanner(System.in);
		Concesionario conce = seleccionPorUsuario();
		
		if (conce != null) {		
			System.out.print("\n�Realmente desea eliminar el registro? (S/N): ");
			String str = sc.next();
			if (str.equalsIgnoreCase("S")) { 		 
				ControladorConcesionario.eliminarConcesionario(conce);
				System.out.println("\n\tEliminado correctamente!");
				
			}
		}
	}
	/**
	 * @throws SQLException 
	 * 
	 */
	
	private static Concesionario seleccionPorUsuario () throws ErrorBBDDException, SQLException {
		
		System.out.println("\n\tIntroduce un ID del concesionario");
		Scanner sc = new Scanner(System.in);
		Concesionario conce = null;
		int id = sc.nextInt();
		conce = ControladorConcesionario.obtenerId(id);
		return conce;
		
	}
	
//	public static void listadoDeConcesionario(Connection conn) throws SQLException {
//		// Para poder ejecutar una consulta necesitamos utilizar un objeto de tipo
//		// Statement
//		Statement s = (Statement) conn.createStatement();
//		// La ejecuci�n de la consulta se realiza a trav�s del objeto Statement y se
//		// recibe en forma de objeto
//		// de tipo ResultSet, que puede ser navegado para descubrir todos los registros
//		// obtenidos por la consulta
//		ResultSet rs = s.executeQuery("select * from concesionario");
//
//		// Navegaci�n del objeto ResultSet. Los get son el n�mero de columnas que tiene
//		// cada tabla
//		while (rs.next()) {
//			System.out.println(rs.getInt("id") + " " + rs.getString(2) + " " + rs.getString(3));
//
//		}
//	}
//	
	/**
	 * 
	 */
	
	
}
