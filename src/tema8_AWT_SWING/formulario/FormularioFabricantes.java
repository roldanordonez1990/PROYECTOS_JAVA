package tema8_AWT_SWING.formulario;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FormularioFabricantes extends JFrame {

	public static String TITULO_APLICACION = "FORMULARIO FABRICANTE";
	JTextField jtfId = new JTextField(5);
	JTextField jtfCif = new JTextField(15);
	JTextField jtfNombre = new JTextField(25);

	public FormularioFabricantes() {
		super(TITULO_APLICACION);
		this.setBounds(0, 0, 800, 600);
		this.setExtendedState(JFrame.NORMAL);
		// setRealizable en true nos dejará redimensionar la ventana
		this.setResizable(true);
		// agregarGestionCierreAplicacion();
		cerrarApp();
		this.setContentPane(getPanelPrincipal());
		this.setVisible(true);
	}

	/**
	 * 
	 * @param args
	 */

	public JPanel getPanelPrincipal() {

		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);

		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
//		
//		//Inclusión del panel de navegación
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.gridx = 0;
//	    c.gridy = 0;
//	    c.gridwidth = 2;
//	    c.insets = new Insets(0, 0, 25, 0);
//	    this.add(getPanelNavegacion(), c);

		// Inclusión del campo "Identificador"
		c.fill = GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(4, 4, 4, 4); //Representa los bordes de un contenedor. Indica es espacio entre en contenedor y el borde
		panel.add(new JLabel("ID: "), c);

		c.gridx = 1;
		c.gridy = 0;
		jtfId.setEnabled(false);// esto nos permite activar/desactivar el campo de texto
		// del jtextfield
		c.anchor = GridBagConstraints.WEST;
		panel.add(jtfId, c);

		// Inclusión del campo "CIF"
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.EAST;
		panel.add(new JLabel("CIF: "), c);

		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.WEST;
		panel.add(jtfCif, c);

		// Inclusión del campo "Nombre"
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.EAST;
		panel.add(new JLabel("NOMBRE: "), c);

		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.WEST;
		
		panel.add(jtfNombre, c);

		return panel;

	}

	/**
	 * 
	 * @param args
	 */

	public void cerrarApp() {
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				String[] opciones = { "Aceptar", "Cancelar" };
				int eleccion = JOptionPane.showOptionDialog(getPanelPrincipal(), "¿Desea cerrar la aplicación?",
						"Salir de la aplicación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
						opciones, "Aceptar");
				if (eleccion == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
	}

//	private void agregarGestionCierreAplicacion() {
//		// Configuración del evento de cerrado
//		// Para más información debes estudiar Javadoc WindowListener y WindowAdapter
//		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//		this.addWindowListener(new WindowAdapter() {
//			public void windowClosing(WindowEvent e) {
//				String posiblesRespuestas[] = { "Sí", "No" };
//				// En esta opción se utiliza un showOptionDialog en el que personalizo el icono
//				// mostrado
//				int opcionElegida = JOptionPane.showOptionDialog(null, "¿Realmente desea cerrar la aplicación?",
//						TITULO_APLICACION, JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
//						posiblesRespuestas, posiblesRespuestas[1]);
//				if (opcionElegida == 0) {
//					System.exit(0);
//				}
//			}
//		});
//	}

	

	public static void main(String[] args) {

		FormularioFabricantes formu = new FormularioFabricantes();

	}

}
