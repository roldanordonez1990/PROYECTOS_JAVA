package tema7_Acceso_A_Datos.gestionVentaCoches.modelo.controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tema7_Acceso_A_Datos.gestionVentaCoches.modelo.Coche;
import tema7_Acceso_A_Datos.gestionVentaCoches.modelo.Venta;

public class ControladorVenta extends ControladorBBDD{

	
	public static List<Venta> getAll() throws ErrorBBDDException{
		List<Venta> venta = new ArrayList<Venta>();
		
		Connection conn = null;
		
		try {
			conn = ConnectionManagerV2.getConexion();

			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("Select * from venta");

			while (rs.next()) {
				Venta vent = new Venta();
				vent.setId(rs.getInt("id"));
				vent.setIdCliente(rs.getInt("idCliente"));
				vent.setIdConcesionario(rs.getInt("idConcesionario"));
				vent.setIdCoche(rs.getInt("idCoche"));
				vent.setFecha(rs.getDate("fecha"));
				vent.setPrecioVenta(rs.getFloat("precioVenta"));
				venta.add(vent);
			}

			s.close();

		} catch (SQLException | ImposibleConectarException e) {
			throw new ErrorBBDDException(e);
		}
		return venta;
		
	}
	
	/**
	 * 
	 */
	
	public static Venta obtenerId(int id) throws ErrorBBDDException {
		Connection conn = null;
		Venta vent = null;

		try {
			conn = ConnectionManagerV2.getConexion();
			
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("Select * from venta where id = " + id);

			if (rs.next()) {
				vent = new Venta();
				vent.setId(id);
				vent.setIdCliente(rs.getInt("idCliente"));
				vent.setIdConcesionario(rs.getInt("idConcesionario"));
				vent.setIdCoche(rs.getInt("idCoche"));
				vent.setFecha(rs.getDate("fecha"));
				vent.setPrecioVenta(rs.getFloat("precioVenta"));
				
			}
			s.close();
			
		} catch (SQLException | ImposibleConectarException e) {			
			throw new ErrorBBDDException(e);
		} 
		return vent;
		
	}
	
	/**
	 * 
	 */
	public static void almacenarNuevo (Venta vent) throws ErrorBBDDException {

		Connection conn = null;

		try {
			conn = ConnectionManagerV2.getConexion();
			
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement("INSERT INTO venta (id, idCliente, idConcesionario, idCoche, fecha, precioVenta) "
					+ "VALUES  (?, ?, ?, ?, ?, ?)");
			int registrosInsertados;
			
			ps.setInt(1, nextIdEnTabla(conn, "venta")); 
			ps.setInt(2, vent.getIdCliente());
			ps.setInt(3, vent.getIdConcesionario());
			ps.setInt(4, vent.getIdCoche());
			ps.setString(5, vent.getFecha());
			ps.setFloat(6,  vent.getPrecioVenta());
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
	
	public static void almacenarModificacion(Venta vent) throws ErrorBBDDException{
		Connection conn = null;
		
		try {
			conn = ConnectionManagerV2.getConexion();
			
			PreparedStatement ps = (PreparedStatement) conn.
					prepareStatement("update venta set idCliente = ?, idConcesionario = ?, idCoche = ?, fecha = ?, precioVenta = ? where id = ?");
			int registrosInsertados;
			
			ps.setInt(1, vent.getIdCliente());
			ps.setInt(2, vent.getIdConcesionario());
			ps.setInt(3, vent.getIdCoche());
			ps.setString(4, vent.getFecha());
			ps.setFloat(5, vent.getPrecioVenta()); 
			ps.setInt(6, vent.getId());
			

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
	 * @param vent
	 * @throws ErrorBBDDException
	 */

	public static void eliminarVenta (Venta vent) throws ErrorBBDDException {

		Connection conn = null;

		try {
			conn = ConnectionManagerV2.getConexion();
			
			PreparedStatement ps = (PreparedStatement) conn.
					prepareStatement("delete from venta where id = ?");
			int registrosInsertados;
			
			ps.setInt(1, vent.getId()); 

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
