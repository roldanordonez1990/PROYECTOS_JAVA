package tema7_Acceso_A_Datos.gestionVentaCoches.modelo.controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tema7_Acceso_A_Datos.gestionVentaCoches.modelo.Concesionario;

public class ControladorConcesionario extends ControladorBBDD {

	public static List<Concesionario> getAll() throws ErrorBBDDException {
		List<Concesionario> concesionarios = new ArrayList<Concesionario>();

		Connection conn = null;
		try {
			conn = ConnectionManagerV2.getConexion();

			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("Select * from concesionario");

			while (rs.next()) {
				Concesionario conce = new Concesionario();
				conce.setId(rs.getInt("id"));
				conce.setCif(rs.getString("cif"));
				conce.setNombre(rs.getString("nombre"));
				conce.setLocalidad(rs.getString("localidad"));
				concesionarios.add(conce);
			}

			s.close();

		} catch (SQLException | ImposibleConectarException e) {
			throw new ErrorBBDDException(e);
		}
		return concesionarios;
	}

	/**
	 * @throws ErrorBBDDException 
	 * 
	 */
	
	public static Concesionario obtenerId(int id) throws ErrorBBDDException {
		Connection conn = null;
		Concesionario conce = null;

		try {
			conn = ConnectionManagerV2.getConexion();
			
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("Select * from concesionario where id = " + id);

			if (rs.next()) {
				conce = new Concesionario();
				conce.setId(id);
				conce.setCif(rs.getString("cif"));
				conce.setNombre(rs.getString("nombre"));
				conce.setLocalidad(rs.getNString("localidad"));
				
			}
			s.close();
			
		} catch (SQLException | ImposibleConectarException e) {			
			throw new ErrorBBDDException(e);
		} 
		return conce;
		
	}
	
/**
 * 	
 * @param conce
 * @throws ErrorBBDDException
 */
	public static void almacenarNuevo (Concesionario conce) throws ErrorBBDDException {

		Connection conn = null;

		try {
			conn = ConnectionManagerV2.getConexion();
			
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement("INSERT INTO concesionario (id, cif, nombre, localidad) "
					+ "VALUES  (?, ?, ?, ?)");
			int registrosInsertados;
			
			ps.setInt(1, nextIdEnTabla(conn, "concesionario")); 
			ps.setString(2, conce.getCif());
			ps.setString(3, conce.getNombre());
			ps.setString(4, conce.getLocalidad());
			registrosInsertados = ps.executeUpdate();
			if (registrosInsertados != 1) {
				throw new ErrorBBDDException ("No ha sido posible la inserción del nuevo registro");
			}
			ps.close();
			
		} catch (SQLException | ImposibleConectarException e) {			
			throw new ErrorBBDDException(e);
		}
	}
		
		/**
		 * 
		 */
		
		public static void eliminarConcesionario (Concesionario conce) throws ErrorBBDDException {

			Connection conn = null;

			try {
				conn = ConnectionManagerV2.getConexion();
				
				PreparedStatement ps = (PreparedStatement) conn.
						prepareStatement("delete from concesionario where id = ?");
				int registrosInsertados;
				
				ps.setInt(1, conce.getId()); 

				registrosInsertados = ps.executeUpdate();
				if (registrosInsertados != 1) {
					throw new ErrorBBDDException ("No ha sido posible la eliminación del registro");
				}
				ps.close();
				
			} catch (SQLException | ImposibleConectarException e) {			
				throw new ErrorBBDDException(e);
			}

		
		
	}
	
}
