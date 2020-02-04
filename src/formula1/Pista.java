package formula1;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Pista extends Actor {

	public static int MARGEN = 2;

	public Pista() {
	}

	// List<Obstaculo> tropezones = new ArrayList<Obstaculo>();

	public Pista(int x, int y, int width, int height) {
		super(x, y);
		this.width = width;
		this.height = height;
	}

	/**
	 * 
	 * @param g
	 */
	public void paint(Graphics g) {

		// Pinto un rectángulo tan grande como las dimensiones del Canvas
		// ancho = PintaCarrera.getInstance().getWidth();
		// alto = PintaCarrera.getInstance().getHeight()/4;

		g.setColor(Color.WHITE);
		g.fillRect(this.x + MARGEN, this.y + MARGEN, this.width - 2 * MARGEN, this.height - 2 * MARGEN);

	}

	@Override
	public void act() {
		// TODO Auto-generated method stub

	}

}
