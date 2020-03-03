package tema7_Acceso_A_Datos.gestionVentaCoches;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import tema7_Acceso_A_Datos.gestionVentaCoches.modelo.Coche;
import tema7_Acceso_A_Datos.gestionVentaCoches.modelo.Venta;
import tema7_Acceso_A_Datos.gestionVentaCoches.modelo.controladores.ControladorCoche;
import tema7_Acceso_A_Datos.gestionVentaCoches.modelo.controladores.ControladorVenta;
import tema7_Acceso_A_Datos.gestionVentaCoches.modelo.controladores.ErrorBBDDException;

public class GestionVenta {

	
	// Iniciaremos el objeto de conexión a null para poder usarlo cuando queramos
		static Connection conn = null;

		public static void menuGestionVenta() throws SQLException, ErrorBBDDException, ParseException {

			Scanner sc = new Scanner(System.in);
			int option = 0;

			do {

				System.out.println("\n\t\t\tGESTIÓN DE VENTAS");

				System.out.println("\n\t1.- Listado de ventas.");
				System.out.println("\t2.- Añadir venta.");
				System.out.println("\t3.- Modificar venta.");
				System.out.println("\t4.- Borrar venta.");
				System.out.println("\t0.- Salir");
				System.out.println("\n\tElija una opción: ");

				option = sc.nextInt();

				switch (option) {
				case 0:
					System.out.println("Adiós");
					break;
				case 1:
					listado(true);
					break;
				case 2:
					darDeAlta();
					break;
				case 3:
					modificar();
					break;
				case 4:
					baja();
					break;
				}

			} while (option != 0);
		}
		
		/**
		 * 
		 */
		
		private static void listado(boolean pausafinal) throws ErrorBBDDException {
			List<Venta> venta = ControladorVenta.getAll();
			System.out.println("\n\tListado de ventas: \n");
			for (Venta vent : venta) {
				System.out.println("\t" + vent.toString());
			}

		}
		
		/**
		 * @throws ParseException 
		 * 
		 */
		
		private static void darDeAlta() throws ErrorBBDDException, SQLException, ParseException {
			System.out.println("\n\tAlta de venta\n");

			Connection conn = null;

			Scanner sc = new Scanner(System.in);

			Venta vent = new Venta();

			System.out.println("\n\tPulsa S para ver la lista de clientes");
			String str = sc.next();
			if (str.equalsIgnoreCase("S")) {
				GestonCliente.listado(true);
			}
			System.out.print("\nIntroduzca 'idCliente' del cliente: ");
			vent.setIdCliente(sc.nextInt());
			
			System.out.println("\n\tPulsa S para ver la lista de concesionarios");
			str = sc.next();
			if (str.equalsIgnoreCase("S")) {
				GestionConcesionario.listado(true);
			}
			System.out.print("\nIntroduzca 'idConcesionario' del concesionario: ");
			vent.setIdConcesionario(sc.nextInt());
			
			System.out.println("\n\tPulsa S para ver la lista de coches");
			str = sc.next();
			if (str.equalsIgnoreCase("S")) {
				GestionCoche.listado(true);
			}
			System.out.print("\nIntroduzca 'idCoche' del coche: ");
			vent.setIdCoche(sc.nextInt());
			
			
			System.out.print("\nIntroduzca 'Fecha' de la venta: ");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			vent.setFecha(sdf.parse(sc.next()));
			
			System.out.print("\nIntroduzca 'Precio de Venta' de la venta: ");
			str = sc.next();
			
			vent.setPrecioVenta(Float.parseFloat(str));
			
			ControladorVenta.almacenarNuevo(vent);

			System.out.println("\n\tInsertado correctamente!");

		}
		
		/**
		 * 
		 * @return
		 * @throws ErrorBBDDException
		 * @throws SQLException
		 * @throws ParseException 
		 */
		
		public static void modificar() throws ErrorBBDDException, SQLException, ParseException {
			System.out.println("\n\tModificación del coche: ");
			Scanner sc = new Scanner(System.in);
			Venta vent = seleccionPorUsuario();
			
			if(vent != null) {
				
				System.out.println("\n\tPulsa S para ver la lista de clientes");
				String str = sc.next();
				if (str.equalsIgnoreCase("S")) {
					GestonCliente.listado(true);
				}
				
				System.out.println("\n\tIntroduce un 'idCliente' nuevo para la venta: ");
				sc = new Scanner(System.in);
				str = sc.next();
				int idCliente = Integer.parseInt(str);
				if(!str.equals("")) 
					vent.setIdCliente(idCliente);
				
				System.out.println("\n\tPulsa S para ver la lista de concesionarios");
				str = sc.next();
				if (str.equalsIgnoreCase("S")) {
					GestionConcesionario.listado(true);
				}
				
				System.out.println("\n\tIntroduce un 'idConcesionario' nuevo para la venta: ");
				sc = new Scanner(System.in);
				str = sc.next();
				int idConcesionario = Integer.parseInt(str);
				if(!str.equals("")) 
					vent.setIdConcesionario(idConcesionario);
				
				System.out.println("\n\tPulsa S para ver la lista de coches");
				str = sc.next();
				if (str.equalsIgnoreCase("S")) {
					GestionCoche.listado(true);
				}
				
				System.out.println("\n\tIntroduce un 'idCoche' nuevo para la venta ");
				str = sc.next();
				int idCoche = Integer.parseInt(str);
				if(!str.equals("")) 
					vent.setIdCoche(idCoche);
				
				System.out.println("\n\tIntroduce una 'Fecha' nueva para la venta: ");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String fecha;
				fecha = sc.next();
				if(!fecha.equals("")) {
					vent.setFecha(sdf.parse(fecha));
				}
				
				System.out.println("\n\tIntroduce un 'Precio de Venta' nuevo para la venta: ");
				str = sc.next();
				float precio = Float.parseFloat(str);
				if(!str.equals("")) {
					vent.setPrecioVenta(precio);
				}
				
				ControladorVenta.almacenarModificacion(vent);
				System.out.println("\n\tHas modificado el coche correctamente");
			}
			
		}
		
		/**
		 * 
		 * @throws ErrorBBDDException
		 * @throws SQLException
		 */
		
		private static void baja() throws ErrorBBDDException, SQLException {
			System.out.println("\n\tEliminación de la venta\n");
			Scanner sc = new Scanner(System.in);
			Venta vent = seleccionPorUsuario();

			if (vent != null) {
				System.out.print("\n¿Realmente desea eliminar el registro? (S/N): ");
				String str = sc.next();
				if (str.equalsIgnoreCase("S")) {
					ControladorVenta.eliminarVenta(vent);
					System.out.println("\n\tEliminado correctamente!");

				}
			}
		}
		
		private static Venta seleccionPorUsuario() throws ErrorBBDDException, SQLException {

			System.out.println("\n\tIntroduce un ID de la venta");
			Scanner sc = new Scanner(System.in);
			Venta vent = null;
			int id = sc.nextInt();
			vent = ControladorVenta.obtenerId(id);
			return vent;

		}
}
