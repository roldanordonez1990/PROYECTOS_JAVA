package tema7_Acceso_A_Datos.gestionVentaCoches;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import tema7_Acceso_A_Datos.ImposibleConectarException;

public class GestionFabricante {
	//Iniciaremos el objeto de conexión a null para poder usarlo cuando queramos
	static Connection conn = null;

	public static void menuGestionFabricantes(Connection conn) throws SQLException {
		//Al acceder a este menú, lo primero que se hará es intentar conectar con la BBDD
		try {
			//Aquí le daremos valor al objeto con la clase que conecta a la bbdd
			conn = ConnectionManagerV2.getConexion();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ImposibleConectarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner sc = new Scanner(System.in);
		int opcion = 0;

		do {

			System.out.println("\n\t\t\tGESTIÓN DE FABRICANTES");

			System.out.println("\n\t1.- Listado de fabricantes.");
			System.out.println("\t2.- Alta de fabricante.");
			System.out.println("\t3.- Modificación de fabricante.");
			System.out.println("\t4.- Baja de fabricante.");
			System.out.println("\t0.- Salir");
			System.out.println("\n\tElija una opción: ");

			opcion = sc.nextInt();

			switch (opcion) {
			case 0:
				System.out.println("Adiós");
				break;
			case 1:
				listadoDeFabricante(conn);
				break;
//			case 2: 
//				alta();
//				break;
//			case 3: 
//				modificacion();
//				break;
//			case 4: 
//				baja();
//				break;
			}

		} while (opcion != 0);
	}

	/**
	 * 
	 * @param pausafinal
	 * @throws ErrorBBDDException
	 */

	public static void listadoDeFabricante(Connection conn) throws SQLException {
		// Para poder ejecutar una consulta necesitamos utilizar un objeto de tipo
		// Statement
		Statement s = (Statement) conn.createStatement();
		// La ejecución de la consulta se realiza a través del objeto Statement y se
		// recibe en forma de objeto
		// de tipo ResultSet, que puede ser navegado para descubrir todos los registros
		// obtenidos por la consulta
		ResultSet rs = s.executeQuery("select * from fabricante");

		// Navegación del objeto ResultSet. Los get son el número de columnas que tiene cada tabla
		while (rs.next()) {
			System.out.println(rs.getInt("id") + " " + rs.getString(2) + " " + rs.getString(3));

		}
	}

}
