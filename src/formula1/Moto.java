package formula1;

import java.awt.Color;
import java.awt.Graphics;

public class Moto extends Vehiculo {

	public Moto(Pista pista, String nombre, Color color) {
		super(pista, nombre, color);
		
	}

	public Moto(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(color); 
		g.fillOval(this.getPosicion(), this.pista.getY() + 80, 20, 20);
		

	}
}
