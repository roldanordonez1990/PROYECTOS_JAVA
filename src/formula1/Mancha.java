package formula1;

import java.awt.Color;
import java.awt.Graphics;

public class Mancha extends Obstaculo{

	public Mancha(String nombre, int yCoord) {
		super(nombre);
		this.impulso -= (int) Math.round(Math.random() * (10-2) + 2);
		this.color = Color.black;
		this.y = yCoord;
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(color);
		//g.fillOval(getPosicion()*PintaCarrera.getJframeWidth()/100, y, getDimension()*PintaCarrera.getJframeWidth()/50, PintaCarrera.getJframeHeight()/4 );

	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	public int getPosicion() {
		// TODO Auto-generated method stub
		return super.getPosicion();
	}

	@Override
	public void setPosicion(int posicion) {
		// TODO Auto-generated method stub
		super.setPosicion(posicion);
	}

	@Override
	public int getDimension() {
		// TODO Auto-generated method stub
		return super.getDimension();
	}

	@Override
	public void setDimension(int dimension) {
		// TODO Auto-generated method stub
		super.setDimension(dimension);
	}

	@Override
	public int getEspacioOcupado() {
		// TODO Auto-generated method stub
		return super.getEspacioOcupado();
	}

	@Override
	public void setManchurron(int espacioOcupado) {
		// TODO Auto-generated method stub
		super.setManchurron(espacioOcupado);
	}

	@Override
	public int getImpulso() {
		// TODO Auto-generated method stub
		return super.getImpulso();
	}

	@Override
	public void setImpulso(int impulso) {
		// TODO Auto-generated method stub
		super.setImpulso(impulso);
	}

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return super.getNombre();
	}

	@Override
	public void setNombre(String nombre) {
		// TODO Auto-generated method stub
		super.setNombre(nombre);
	}

	@Override
	public void setEspacioOcupado(int espacioOcupado) {
		// TODO Auto-generated method stub
		super.setEspacioOcupado(espacioOcupado);
	}

	

}
