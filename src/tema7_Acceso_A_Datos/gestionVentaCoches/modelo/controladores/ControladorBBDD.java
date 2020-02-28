package tema7_Acceso_A_Datos.gestionVentaCoches.modelo.controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class ControladorBBDD {

	// Iniciaremos el objeto de conexión a null para poder usarlo cuando queramos
	static Connection conn = null;

	protected static SimpleDateFormat sdfMySQL = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	// Con este méodo obtendremos un hilo abierto paralelamente el cual está
	// teniendo la conexión mientras otros métodos se ejecutan
	public static void iniciaConnectionPool() {
		Thread conexionUCP = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					ConnectionManagerV2.getConexion();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ImposibleConectarException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		conexionUCP.start();
	}

	/**
	 * 
	 */
	
	//Obtenemos el max id de la tabla
	protected static int maxIdEnTabla(Connection conn, String tabla) throws SQLException {

		PreparedStatement ps = conn.prepareStatement("select max(id) from " + tabla);

		int max = 1;
		if (ps != null) {
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				max = rs.getInt(1);
			}
			rs.close();
		}

		return max;
	}

	/**
	 * 
	 */
	
	//Obtenemos el siguiente max id de la tabla
	protected static int nextIdEnTabla(Connection conn, String tabla) throws SQLException {

		return maxIdEnTabla(conn, tabla) + 1;

	}
}
