package DI.dominio.Autos;

public class Auto {

	int ruedas;
	String modelo;
	
	public Auto(int _ruedas,String _modelo){
		this.setRuedas(_ruedas);
		this.setModelo(_modelo);
	}

	public Auto() {
		// TODO Auto-generated constructor stub
	}

	public int getRuedas() {
		return ruedas;
	}

	public void setRuedas(int ruedas) {
		this.ruedas = ruedas;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
}
