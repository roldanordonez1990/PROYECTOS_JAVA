package tema7_Acceso_A_Datos.gestionVentaCoches.modelo.controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tema7_Acceso_A_Datos.gestionVentaCoches.modelo.Coche;
import tema7_Acceso_A_Datos.gestionVentaCoches.modelo.Concesionario;

public class ControladorCoche extends ControladorBBDD {
	
	public static List<Coche> getAll() throws ErrorBBDDException{
		List<Coche> coches = new ArrayList<Coche>();
		
		Connection conn = null;
		
		try {
			conn = ConnectionManagerV2.getConexion();

			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("Select * from coche");

			while (rs.next()) {
				Coche coche = new Coche();
				coche.setId(rs.getInt("id"));
				coche.setIdfabricante(rs.getInt("idfabricante"));
				coche.setBastidor(rs.getString("bastidor"));
				coche.setModelo(rs.getString("modelo"));
				coche.setColor(rs.getString("color"));
				coches.add(coche);
			}

			s.close();

		} catch (SQLException | ImposibleConectarException e) {
			throw new ErrorBBDDException(e);
		}
		return coches;
		
	}
	
	/**
	 * 
	 */
	
	public static Coche obtenerId(int id) throws ErrorBBDDException {
		Connection conn = null;
		Coche coche = null;

		try {
			conn = ConnectionManagerV2.getConexion();
			
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("Select * from coche where id = " + id);

			if (rs.next()) {
				coche = new Coche();
				coche.setId(id);
				coche.setIdfabricante(rs.getInt("idfabricante"));
				coche.setBastidor(rs.getString("bastidor"));
				coche.setModelo(rs.getNString("modelo"));
				coche.setColor(rs.getString("color"));
				
			}
			s.close();
			
		} catch (SQLException | ImposibleConectarException e) {			
			throw new ErrorBBDDException(e);
		} 
		return coche;
		
	}
	
	/**
	 * 
	 */
	
	public static void almacenarNuevo (Coche coche) throws ErrorBBDDException {

		Connection conn = null;

		try {
			conn = ConnectionManagerV2.getConexion();
			
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement("INSERT INTO coche (id, idfabricante, bastidor, modelo, color) "
					+ "VALUES  (?, ?, ?, ?, ?)");
			int registrosInsertados;
			
			ps.setInt(1, nextIdEnTabla(conn, "coche")); 
			ps.setInt(2, coche.getIdfabricante());
			ps.setString(3, coche.getBastidor());
			ps.setString(4, coche.getModelo());
			ps.setString(5, coche.getColor());
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
	 * @param conce
	 * @throws ErrorBBDDException
	 */
	
	public static void almacenarModificacion(Coche coche) throws ErrorBBDDException{
		Connection conn = null;
		
		try {
			conn = ConnectionManagerV2.getConexion();
			
			PreparedStatement ps = (PreparedStatement) conn.
					prepareStatement("update coche set idfabricante = ?, bastidor = ?, modelo = ?, color = ? where id = ?");
			int registrosInsertados;
			
			ps.setInt(1, coche.getIdfabricante());
			ps.setString(2, coche.getBastidor());
			ps.setString(3, coche.getModelo());
			ps.setString(4, coche.getColor());
			ps.setInt(5, coche.getId()); 
			

			registrosInsertados = ps.executeUpdate();
			if (registrosInsertados != 1) {
				throw new ErrorBBDDException ("No ha sido posible la modificación del registro");
			}
			ps.close();
			
		} catch (SQLException | ImposibleConectarException e) {			
			throw new ErrorBBDDException(e);
		}
		
	}
	
	/**
	 * 
	 */
	
	public static void eliminarCoche (Coche coche) throws ErrorBBDDException {

		Connection conn = null;

		try {
			conn = ConnectionManagerV2.getConexion();
			
			PreparedStatement ps = (PreparedStatement) conn.
					prepareStatement("delete from coche where id = ?");
			int registrosInsertados;
			
			ps.setInt(1, coche.getId()); 

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
