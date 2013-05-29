package DI.dominio.Autos;

public class Persona {

	int edad;
	String nombre;
	Auto auto;
	

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

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Auto getAuto() {
		return auto;
	}

	public void setAuto(Auto auto) {
		this.auto = auto;
	}

}
