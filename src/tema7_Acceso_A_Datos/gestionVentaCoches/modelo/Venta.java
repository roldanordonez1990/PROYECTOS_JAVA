package tema7_Acceso_A_Datos.gestionVentaCoches.modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Venta {
	
	int id;
	int idCliente;
	int idConcesionario;
	int idCoche;
	String fecha;
	float precioVenta;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public Venta() {
		
	}
	
	public Venta(int id, int idCliente, int idConcesionario, int idCoche, String fecha, float precioVenta) {
		this.id = id;
		this.idCliente = idCliente;
		this.idCoche = idCoche;
		this.fecha = fecha;
		this.precioVenta = precioVenta;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCLiente) {
		this.idCliente = idCLiente;
	}

	public int getIdConcesionario() {
		return idConcesionario;
	}

	public void setIdConcesionario(int idConcesionario) {
		this.idConcesionario = idConcesionario;
	}

	public int getIdCoche() {
		return idCoche;
	}

	public void setIdCoche(int idCoche) {
		this.idCoche = idCoche;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(Date date) {
		this.fecha = sdf.format(date);
	}

	public float getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(float precioVenta) {
		this.precioVenta = precioVenta;
	}

	public SimpleDateFormat getSdf() {
		return sdf;
	}

	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}

	@Override
	public String toString() {
		return "Venta [id=" + id + ", idCliente=" + idCliente + ", idConcesionario=" + idConcesionario + ", idCoche="
				+ idCoche + ", fecha=" + fecha + ", precioVenta=" + precioVenta + "]";
	}


	
	

}
