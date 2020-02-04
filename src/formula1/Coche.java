package formula1;

import java.awt.Color;
import java.awt.Graphics;

public class Coche extends Vehiculo {

	public Coche(Pista pista, String nombre, Color color) {
		super(pista, nombre, color);
		
	}

	public Coche(int x, int y) {
		super(x, y);
	
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(color); 
		g.fillRect(this.getPosicion(), this.pista.getY() +80, 20, 20);

	}


}
