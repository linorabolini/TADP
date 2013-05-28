package DI.dominio.Autos;

public class Auto {

	private int ruedas;
	private String modelo;
	
	public Auto(int _ruedas,String _modelo){
		this.setRuedas(_ruedas);
		this.setModelo(_modelo);
	}

	public Auto() {
		// TODO Auto-generated constructor stub
	}

	int getRuedas() {
		return ruedas;
	}

	void setRuedas(int ruedas) {
		this.ruedas = ruedas;
	}

	String getModelo() {
		return modelo;
	}

	void setModelo(String modelo) {
		this.modelo = modelo;
	}
}
