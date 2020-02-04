package formula1;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Carrera extends Canvas {

	// private static final Pista pista = null;

	public List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();

	private static int WIDTH = 800;
	private static int HEIGHT = 800;

	public static int MAX_POSICIONES_POR_PISTA = 800;
	protected float pixelsPorPosicion = WIDTH / MAX_POSICIONES_POR_PISTA;

	private static Carrera instance = null;

	public Carrera() {

		JFrame ventana = new JFrame("Fórmula uno");
		JPanel panel = (JPanel) ventana.getContentPane();
		panel.setLayout(new BorderLayout());
		panel.add(this, BorderLayout.CENTER);
		ventana.setBounds(0, 0, WIDTH, HEIGHT);

		Canvas punteroAThis = this;
		this.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				super.mouseClicked(arg0);
				punteroAThis.repaint();
			}

		});

		ventana.setResizable(false);
		ventana.setVisible(true);

	}

	public void initWorld() {

		// Creo las 4 pistas
		int countPistas = 4;
		int pixelsAltoPista = this.getHeight() / countPistas;
		for (int i = 0; i < countPistas; i++) {
			Pista pista = new Pista(0, i * pixelsAltoPista, this.getWidth(), pixelsAltoPista);

			Vehiculo vehiculo = null;

			if (i == 0) {
				vehiculo = new Coche(pista, "Calos Sainz", Color.RED);
			}
			if (i == 1) {
				vehiculo = new Moto(pista, "Marc Marquez", Color.PINK);
			}
			if (i == 2) {
				vehiculo = new Coche(pista, "Fernando Alonso", Color.BLUE);
			}
			if (i == 3) {
				vehiculo = new Moto(pista, "Rossi", Color.GREEN);
			}
			this.vehiculos.add(vehiculo);
		}

	}

	public void paint(Graphics g) {
		super.paint(g);

		// System.out.println("Ancho: " + this.getWidth() + " - Alto: " +
		// this.getHeight());
		// Vuelvo a calcular los píxeles por posición
		this.pixelsPorPosicion = ((float) this.getWidth()) / MAX_POSICIONES_POR_PISTA;

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		for (Vehiculo v : this.vehiculos) {
			v.paint(g);
		}
	}

	public void game() {

		boolean gameOver = false;
		do {
			gameOver = true;
			System.out.println("\n nueva ronda");
			for (Vehiculo v : this.vehiculos) {

				if (!v.haCompletadoCarrera()) {
					v.act();
					gameOver = false;
					System.out.println(v.toString());
				}

			}
			this.repaint();
			if (!gameOver) {
				JOptionPane.showMessageDialog(null, "Avance");
			}

		} while (!gameOver);
	}

	/**
	 * 
	 * @return
	 */

	public static Carrera getInstance() {
		if (instance == null) {
			instance = new Carrera();
		}
		return instance;
	}

	/**
	 * Hacemos el fin de partida cuando el ArrayList de vehículos está vacio
	 * 
	 * @return
	 */
	public boolean finPartida() {
		boolean estaVacio = true;
		return vehiculos.isEmpty();

	}

	/**
	 * @return the vehiculos
	 */
	public List<Vehiculo> getVehiculos() {
		return vehiculos;
	}

	/**
	 * @param vehiculos the vehiculos to set
	 */
	public void setVehiculos(List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}

	public static void main(String args[]) {
		Carrera.getInstance().initWorld();
		Carrera.getInstance().game();
	}

}
