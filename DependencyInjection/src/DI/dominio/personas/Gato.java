package DI.dominio.personas;

public class Gato implements Mascota {
	
	private String nombre;
	private int vidas;
	
	public Gato(String _nombre, int _vidas){
		this.setNombre(_nombre);
		this.setVidas(_vidas);
	}
	
	public void comer(){
		//Come como gato
	}
	
	public void saludarDuenio(){
		//Saluda como gato
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getVidas() {
		return vidas;
	}

	public void setVidas(int vidas) {
		this.vidas = vidas;
	}

}
