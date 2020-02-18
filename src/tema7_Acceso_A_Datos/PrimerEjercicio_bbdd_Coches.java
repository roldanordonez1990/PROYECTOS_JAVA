package tema7_Acceso_A_Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PrimerEjercicio_bbdd_Coches {
	
	private static void pruebaConsultaBasica () {
		try {
			// A trav�s de la siguiente l�nea comprobamos si tenemos acceso al driver MySQL, si no fuera as�
			// no podemos trabajar con esa BBDD.
			Class.forName("com.mysql.cj.jdbc.Driver");
		   
			// Necesitamos obtener un acceso a la BBDD, eso se materializa en un objeto de tipo Connection, al cual
			// le tenemos que pasar los par�metros de conexi�n.
			Connection conexion = (Connection) DriverManager.getConnection ("jdbc:mysql://localhost/bbdd_GestionCochescoches?serverTimezone=UTC","java", "1234");
		   
			
			// Para poder ejecutar una consulta necesitamos utilizar un objeto de tipo Statement
			Statement s = (Statement) conexion.createStatement(); 
			
			// La ejecuci�n de la consulta se realiza a trav�s del objeto Statement y se recibe en forma de objeto
			// de tipo ResultSet, que puede ser navegado para descubrir todos los registros obtenidos por la consulta
			ResultSet rs = s.executeQuery ("select * from cliente");
		   
			// Navegaci�n del objeto ResultSet
			while (rs.next()) { 
				System.out.println (rs.getInt("id") + " " + rs.getString (2)+ 
						" " + rs.getString(3) + " " + rs.getString(4) + 
						" " + rs.getString(5)); 
			}
			// Cierre de los elementos
			rs.close();
			s.close();
			conexion.close();
		}
		catch (ClassNotFoundException ex) {
			System.out.println("Imposible acceder al driver Mysql");
			ex.printStackTrace();
		}
		catch (SQLException ex) {
			System.out.println("Error en la ejecuci�n SQL: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
	

	public static void main(String[] args) {
		pruebaConsultaBasica();

	}

}
