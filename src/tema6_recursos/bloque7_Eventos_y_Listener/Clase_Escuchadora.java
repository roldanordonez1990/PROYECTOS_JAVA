package tema6_recursos.bloque7_Eventos_y_Listener;

public class Clase_Escuchadora implements IntroducirDigitosMagicosListener{
	
	String nombre;
	
	public Clase_Escuchadora (String nombre) {
		this.nombre = nombre;
		Principal.addIntroducirDigitosMagicosListener(this);
	}

	@Override
	public void introducirDigitosMagicos(IntroducirDigitosMagicosEvent event) {
		System.out.println("Hola soy el escuchador, me llamo " + this.nombre + " y he escuchado esto: " + event.getDigitoIntroducido());
		
	}


}
