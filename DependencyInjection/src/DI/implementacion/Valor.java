package DI.implementacion;

public class Valor implements Instanciable{
	
	Object valor;
	private Class<?> clase;
	
	public Valor(Object _valor, Class<?> _clase){
		this.clase = _clase;
		this.valor = _valor;
	}
	
	public Object dameInstancia(){
		return valor;
	}

	public Class<?> getClase() {
		return clase;
	}

	

}
