package DI.dominio.personas;

public class Perro implements Mascota{
	
	private String nombre;
	private boolean tieneHambre;
	
	public Perro (String _nombre, boolean _tieneHambre){
		this.setNombre(_nombre);
		this.setTieneHambre(_tieneHambre);
	}
	
	
	public void comer(){
		//Come como perro
	}
	
	public void saludarDuenio(){
		//Saluda como perro
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isTieneHambre() {
		return tieneHambre;
	}

	public void setTieneHambre(boolean tieneHambre) {
		this.tieneHambre = tieneHambre;
	}

}
