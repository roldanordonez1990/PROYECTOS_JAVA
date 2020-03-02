package tema7_Acceso_A_Datos.gestionVentaCoches.modelo.controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import tema7_Acceso_A_Datos.gestionVentaCoches.modelo.Cliente;
import tema7_Acceso_A_Datos.gestionVentaCoches.modelo.Coche;


public class ControladorCliente extends ControladorBBDD{
	
	public static List<Cliente> getAll() throws ErrorBBDDException, ParseException{
		List<Cliente> clientes = new ArrayList<Cliente>();
		
		Connection conn = null;
		
		try {
			conn = ConnectionManagerV2.getConexion();

			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("Select * from cliente");

			while (rs.next()) {
				Cliente cli = new Cliente();
				cli.setId(rs.getInt("id"));
				cli.setNombre(rs.getString("nombre"));
				cli.setApellidos(rs.getString("apellidos"));
				cli.setLocalidad(rs.getString("localidad"));
				cli.setDniNie(rs.getString("dniNie"));
				cli.setFechaNac(rs.getDate("fechaNac"));
				cli.setActivo(rs.getBoolean("activo"));
				clientes.add(cli);
			}

			s.close();

		} catch (SQLException | ImposibleConectarException e) {
			throw new ErrorBBDDException(e);
		}
		return clientes;
		
	}
	
	/**
	 * @throws ParseException 
	 * 
	 */
	
	public static Cliente obtenerId(int id) throws ErrorBBDDException, ParseException {
		Connection conn = null;
		Cliente cli = null;

		try {
			conn = ConnectionManagerV2.getConexion();
			
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("Select * cliente where id = " + id);

			if (rs.next()) {
				cli = new Cliente();
				cli.setId(id);
				cli.setNombre(rs.getString("nombre"));
				cli.setApellidos(rs.getString("apellidos"));
				cli.setLocalidad(rs.getNString("localidad"));
				cli.setDniNie(rs.getString("dniNie"));
				cli.setFechaNac(rs.getDate("fechaNac"));
				cli.setActivo(true);
				
			}
			s.close();
			
		} catch (SQLException | ImposibleConectarException e) {			
			throw new ErrorBBDDException(e);
		} 
		return cli;
		
	}
	
	public static void almacenarNuevo (Cliente cliente) throws ErrorBBDDException {

		Connection conn = null;

		try {
			conn = ConnectionManagerV2.getConexion();
			
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement("INSERT INTO cliente (id, nombre, apellidos, localidad, dniNie, fechaNac, activo) "
					+ "VALUES  (?, ?, ?, ?, ?, ?, ?)");
			int registrosInsertados;
			
			ps.setInt(1, nextIdEnTabla(conn, "cliente")); 
			ps.setString(2, cliente.getNombre());
			ps.setString(3, cliente.getApellidos());
			ps.setString(4, cliente.getLocalidad());
			ps.setString(5, cliente.getDniNie());
			ps.setString(6, cliente.getFechaNac());
			ps.setBoolean(7, true);
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
	
	public static void eliminarCliente (Cliente cli) throws ErrorBBDDException {

		Connection conn = null;

		try {
			conn = ConnectionManagerV2.getConexion();
			
			PreparedStatement ps = (PreparedStatement) conn.
					prepareStatement("delete from cliente where id = ?");
			int registrosInsertados;
			
			ps.setInt(1, cli.getId()); 

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
