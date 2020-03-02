package tema7_Acceso_A_Datos.gestionVentaCoches.modelo;

public class Fabricante {

	int id;
	String cif;
	String nombre;

	public Fabricante() {
	}
  
	public Fabricante(int id, String cif, String nombre) {
		super();
		this.id = id;
		this.cif = cif;
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "id=" + id + ", cif=" + cif + ", nombre=" + nombre;
	}	
	
}
