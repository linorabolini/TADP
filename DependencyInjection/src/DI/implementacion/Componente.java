package DI.implementacion;

public class Componente implements Instanciable{

	private Class<?> clase;
	FormaInstanciar formaInstanciar;
	
	public Componente (Class<?> _clase, FormaInstanciar forma){
		this.clase =_clase;
		this.formaInstanciar = forma;
	}
		
	public Object dameInstancia(){
		return formaInstanciar.dameInstancia(this);
	}
	
	public void agregarDependencia(String id, Instanciable instanciable){
		formaInstanciar.agregarDependencia(id, instanciable);
	}

	public Class<?> getClase() {
		return clase;
	}
}
