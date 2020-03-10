package tema8_AWT_SWING;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PrimeraVentana extends JFrame {

	public PrimeraVentana() {
		this.setBounds(0, 0, 800, 600);

		this.setContentPane(getPanelPrincipal());
		this.setVisible(true);
	}

	/**
	 * 
	 * @return
	 */
	public JPanel getPanelPrincipal() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.GREEN);

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		// Panel para el nombre
		JPanel pnlNombre = new JPanel();
		pnlNombre.add(new JLabel("Nombre:"));
		JTextField jtfNombre = new JTextField(40);
		pnlNombre.add(jtfNombre);
		// Inserto el pnlNombre en el panel principal
		panel.add(pnlNombre);

		// Panel para el primer Apellido
		JPanel pnlPrimerAp = new JPanel();
		pnlPrimerAp.add(new JLabel("Primer Apellido:"));
		JTextField jtfPrimerAp = new JTextField(40);
		pnlPrimerAp.add(jtfPrimerAp);
		// Inserto el pnlPrimerAp en el panel principal
		panel.add(pnlPrimerAp);

		// Panel para el segundo apellido
		JPanel pnlSegAp = new JPanel();
		pnlSegAp.add(new JLabel("Segundo Apellido:"));
		JTextField jtfSegAp = new JTextField(40);
		pnlSegAp.add(jtfSegAp);
		// Inserto el pnlSegAp en el panel principal
		panel.add(pnlSegAp);

		// Panel para el DNI
		JPanel pnldni = new JPanel();
		pnldni.add(new JLabel("DNI:"));
		JTextField jtfdni = new JTextField(9);
		pnldni.add(jtfdni);
		// Inserto el pnldni en el panel principal
		panel.add(pnldni);

		// Panel para la Localidad
		JPanel pnlLocal = new JPanel();
		pnlLocal.add(new JLabel("Localidad:"));
		JTextField jtfLocal = new JTextField(40);
		pnlLocal.add(jtfLocal);
		// Inserto el pnlLocal en el panel principal
		panel.add(pnlLocal);
		
		//Botón
		JButton bt = new JButton();
		
		bt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "Botón presionado: ENVIADO");
				
			}
		});
		
		panel.add(bt);

		return panel;
	}

	public static void main(String[] args) {
		PrimeraVentana v = new PrimeraVentana();

	}

}
