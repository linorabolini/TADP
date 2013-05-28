package DI.dominio.Autos;

public class Persona {

	private int edad;
	private String nombre;
	private Auto auto;
	

	public Persona( String _nombre, int _edad, Auto _auto ){
		this.setEdad(_edad);
		this.setNombre(_nombre);
		this.setAuto(_auto);
	}
	
	public Persona(int _edad, String _nombre){
		this.setEdad(_edad);
		this.setNombre(_nombre);
	}
	
	public Persona(){
		this.setEdad(0);
		this.setNombre("Default");
	}

	int getEdad() {
		return edad;
	}

	void setEdad(int edad) {
		this.edad = edad;
	}

	String getNombre() {
		return nombre;
	}

	void setNombre(String nombre) {
		this.nombre = nombre;
	}

	Auto getAuto() {
		return auto;
	}

	void setAuto(Auto auto) {
		this.auto = auto;
	}

}
