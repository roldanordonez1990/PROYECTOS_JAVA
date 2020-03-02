package tema7_Acceso_A_Datos.gestionVentaCoches;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import tema7_Acceso_A_Datos.gestionVentaCoches.modelo.Cliente;
import tema7_Acceso_A_Datos.gestionVentaCoches.modelo.Coche;
import tema7_Acceso_A_Datos.gestionVentaCoches.modelo.controladores.ControladorCliente;
import tema7_Acceso_A_Datos.gestionVentaCoches.modelo.controladores.ControladorCoche;
import tema7_Acceso_A_Datos.gestionVentaCoches.modelo.controladores.ErrorBBDDException;

public class GestonCliente {
	
	// Iniciaremos el objeto de conexión a null para poder usarlo cuando queramos
		static Connection conn = null;

		public static void menuGestionCliente() throws SQLException, ErrorBBDDException, ParseException {

			Scanner sc = new Scanner(System.in);
			int option = 0;

			do {

				System.out.println("\n\t\t\tGESTIÓN DE CLIENTES");

				System.out.println("\n\t1.- Listado de clientes.");
				System.out.println("\t2.- Añadir cliente.");
				System.out.println("\t3.- Modificar cliente.");
				System.out.println("\t4.- Borrar cliente.");
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
//				case 3:
//					modificar();
//					break;
				case 4:
					baja();
					break;
				}

			} while (option != 0);
		}
		
		/**
		 * @throws ParseException 
		 * 
		 */
		
		private static void listado(boolean pausafinal) throws ErrorBBDDException, ParseException {
			List<Cliente> cli = ControladorCliente.getAll();
			System.out.println("\n\tListado de clientes: \n");
			for (Cliente cliente : cli) {
				System.out.println("\t" + cliente.toString());
			}

		}
		
		/**
		 * @throws ParseException 
		 * 
		 */
		
		private static void darDeAlta() throws ErrorBBDDException, SQLException, ParseException {
			System.out.println("\n\tAlta de cliente\n");

			Connection conn = null;

			Scanner sc = new Scanner(System.in);

			Cliente cli = new Cliente();

			System.out.print("\nIntroduzca 'Nombre' del cliente: ");
			cli.setNombre(sc.next());
			System.out.print("\nIntroduzca 'Apellidos' del cliente: ");
			cli.setApellidos(sc.next());
			System.out.print("\nIntroduzca 'Localidad' del cliente: ");
			cli.setLocalidad(sc.next());
			System.out.print("\nIntroduzca 'Dni' del cliente: ");
			cli.setDniNie(sc.next());
			System.out.print("\nIntroduzca 'Fecha de Nacimiento' del cliente: ");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			cli.setFechaNac(sdf.parse(sc.next()));
			

			System.out.println("\nIntroduzca 'Actividad' del cliente: ");
			cli.setActivo(sc.equals(sc));
			ControladorCliente.almacenarNuevo(cli);

			System.out.println("\n\tInsertado correctamente!");

		}
		
		/**
		 * @throws ParseException 
		 * 
		 */
		
		private static void baja() throws ErrorBBDDException, SQLException, ParseException {
			System.out.println("\n\tEliminación del cliente\n");
			Scanner sc = new Scanner(System.in);
			Cliente cli = seleccionPorUsuario();

			if (cli != null) {
				System.out.print("\n¿Realmente desea eliminar el registro? (S/N): ");
				String str = sc.next();
				if (str.equalsIgnoreCase("S")) {
					ControladorCliente.eliminarCliente(cli);
					System.out.println("\n\tEliminado correctamente!");

				}
			}
		}
		
		/**
		 * @throws ParseException 
		 * 
		 */

		private static Cliente seleccionPorUsuario() throws ErrorBBDDException, SQLException, ParseException {

			System.out.println("\n\tIntroduce un ID del cliente");
			Scanner sc = new Scanner(System.in);
			Cliente cli = null;
			int id = sc.nextInt();
			cli = ControladorCliente.obtenerId(id);
			return cli;

		}


}
