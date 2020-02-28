package tema7_Acceso_A_Datos.gestionVentaCoches;

import java.io.ObjectInputStream.GetField;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import tema7_Acceso_A_Datos.gestionVentaCoches.modelo.controladores.ConnectionManagerV2;
import tema7_Acceso_A_Datos.gestionVentaCoches.modelo.controladores.ImposibleConectarException;

public class GestionFabricante {
	// Iniciaremos el objeto de conexión a null para poder usarlo cuando queramos
	static Connection conn = null;

	public static void menuGestionFabricantes(Connection conn) throws SQLException {
		// Al acceder a este menú, lo primero que se hará es intentar conectar con la
		// BBDD
		try {
			// Aquí le daremos valor al objeto con la clase que conecta con la bbdd
			conn = ConnectionManagerV2.getConexion();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ImposibleConectarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner sc = new Scanner(System.in);
		int option = 0;

		do {

			System.out.println("\n\t\t\tGESTIÓN DE FABRICANTES");

			System.out.println("\n\t1.- Listado de fabricantes.");
			System.out.println("\t2.- Añadir fabricante.");
			System.out.println("\t3.- Optener un id.");
			System.out.println("\t4.- Borrar fabricante.");
			System.out.println("\t0.- Salir");
			System.out.println("\n\tElija una opción: ");

			option = sc.nextInt();

			switch (option) {
			case 0:
				System.out.println("Adiós");
				break;
			case 1:
				listadoDeFabricante(conn);
				break;
			case 2:
				crearDatosFabricante(conn);
				break;
			case 3:
				obtenerId(conn);
				break;
			case 4:
				borradoDelFabricante(conn);
				break;
			}

		} while (option != 0);
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

		// Navegación del objeto ResultSet. Los get son el número de columnas que tiene
		// cada tabla
		while (rs.next()) {
			System.out.println(rs.getInt("id") + " " + rs.getString(2) + " " + rs.getString(3));

		}
	}

	public static void crearDatosFabricante(Connection conn) throws SQLException {
		// Ejecutamos la consulta
		Statement s = (Statement) conn.createStatement();

		Scanner sc = new Scanner(System.in);
		// Creamos el objeto scanner e introducimos los datos que queramos en cada campo
		System.out.println("\n\tIntroduce un ID");
		int id;
		id = sc.nextInt();
		
		//pequeña parte de código para que no nos deje introducir un id que ya exista
		boolean si = true;
		do {

			ResultSet rs = s.executeQuery("select id from tutorialjavacoches.fabricante order by id");
			while (rs.next()) {
				if (id == rs.getInt(1)) {
					si = false;
					System.out.println("\n\tEl id introducido ya existe, vuelve a introducir un id que no exista");
					id = sc.nextInt();
				}
				if (id != rs.getInt(1)) {
					si = true;
				}
			}

		} while (!si);
		//
		System.out.println("\n\tIntroduce un CIF");
		String cif = sc.next();
		//
		System.out.println("\n\tIntroduce un Nombre");
		String nombre = sc.next();
		// Introducimos valores con INSERT INTO, como en sql
		String sql = "INSERT INTO tutorialjavacoches.fabricante (id, cif, nombre) " + "VALUES  (" + id + ", '" + cif
				+ "', '" + nombre + "')";
		// para la modificación(Update)
		s.executeUpdate(sql);

		s.close();

	}

	/**
	 * 
	 * @param conn
	 * @throws SQLException
	 */
	public static void borradoDelFabricante(Connection conn) throws SQLException {
		// Ejecutamos la consulta
		Statement s = (Statement) conn.createStatement();

		// Nos pedirá que introduzcamos un id procedente del método obtenerId
		// anteriormente creado
		int id = obtenerId(conn);

		// Hacemos la consulta con sql
		String sql = "DELETE FROM tutorialjavacoches.fabricante WHERE id=" + id + ";";

		// ejecutamos los cambios con Update
		s.executeUpdate(sql);

		System.out.println("El fabricante con id: " + id + " ha sido eliminado");

		s.close();
	}

	/**
	 * 
	 */

	public static int obtenerId(Connection conn) throws SQLException {
		// Ejecutamos consulta y en este caso tenemos que tener el objeto resultset para
		// que nos devuelva dicha consulta
		Statement s = (Statement) conn.createStatement();
		ResultSet rs;

		Scanner sc = new Scanner(System.in);

		System.out.println("Escribe un id que aparezca en la tabla");

		int escribeId= sc.nextInt();
		boolean correcto = true;

		do {
			// hacemos la consulta. Queremos obtener el id de los fabricantes
			rs = s.executeQuery("select id from tutorialjavacoches.fabricante order by id");

			while (rs.next()) {
				// Mientras haya id, nos dirá si el id que hemos introducido existe en la
				// columna de los id. getint(1) el 1 se refiere a la columna 1 que coindice con
				// los id
				if (escribeId == rs.getInt(1)) {
					System.out.println("El id existe en la tabla");
					// indicamos al boolean que sea correcto para que se salga del bucle
					correcto = true;
					// Guardamos el id como entero para que nos lo devuelva luego
					escribeId = rs.getInt(1);

				}
			}

			// Mientras que el boolean no sea correcto, se ejecutará el do
		} while (!correcto);

		rs.close();
		s.close();
		// Nos devueve el id
		return escribeId;

	}

}
