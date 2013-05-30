package DI.dominio.Autos;

import java.util.ArrayList;

public class Persona {

	int edad;
	String nombre;
	Auto auto;
	private ArrayList<Persona> hijos;
	private ArrayList<Integer> saldosTarjeta;
	
	public Persona(int _edad, String _nombre){
		this.setEdad(_edad);
		this.setNombre(_nombre);
		setHijos(new ArrayList<Persona>());
		setSaldosTarjeta(new ArrayList<Integer>());
	}
	
	public Persona(int _edad, String _nombre, ArrayList<Integer> _saldosTarjeta, ArrayList<Persona> _hijos){
		this.setEdad(_edad);
		this.setNombre(_nombre);
		setHijos(_hijos);
		setSaldosTarjeta(_saldosTarjeta);
	}
	
	public Persona( String _nombre, int _edad, Auto _auto ){
		this.setEdad(_edad);
		this.setNombre(_nombre);
		this.setAuto(_auto);
		setHijos(new ArrayList<Persona>());
		setSaldosTarjeta(new ArrayList<Integer>());
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

	public ArrayList<Persona> getHijos() {
		return hijos;
	}

	public void setHijos(ArrayList<Persona> hijos) {
		this.hijos = hijos;
	}

	public ArrayList<Integer> getSaldosTarjeta() {
		return saldosTarjeta;
	}

	public void setSaldosTarjeta(ArrayList<Integer> saldosTarjeta) {
		this.saldosTarjeta = saldosTarjeta;
	}

}
