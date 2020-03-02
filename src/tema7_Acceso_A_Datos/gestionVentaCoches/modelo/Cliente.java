package tema7_Acceso_A_Datos.gestionVentaCoches.modelo;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Cliente {

	int id;
	String nombre;
	String apellidos;
	String localidad;
	String dniNie;
	String fechaNac;
	boolean activo;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public Cliente() {
		
	}
	
	public Cliente(int id, String nombre, String apellidos, String localidad, String dniNie, String fechaNac, boolean activo) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.localidad = localidad;
		this.dniNie = dniNie;
		this.fechaNac = fechaNac;
		this.activo = activo;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getDniNie() {
		return dniNie;
	}

	public void setDniNie(String dniNie) {
		this.dniNie = dniNie;
	}

	public String getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date date) throws ParseException{
		this.fechaNac = sdf.format(date);
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", localidad=" + localidad
				+ ", dniNie=" + dniNie + ", fechaNac=" + fechaNac + ", activo=" + activo + "]";
	}
	
	
}
