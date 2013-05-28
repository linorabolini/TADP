package DI.implementacion;

import java.lang.reflect.InvocationTargetException;

public class Componente implements Instanciable{

	private Class<?> clase;
	FormaInstanciar formaInstanciar;
	
	public Componente (Class<?> _clase, FormaInstanciar forma){
		this.clase =_clase;
		this.formaInstanciar = forma;
	}
		
	public Object dameInstancia() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		return formaInstanciar.dameInstancia(this);	
	}
	
	public void agregarDependencia(String id, Instanciable instanciable){
		formaInstanciar.agregarDependencia(id, instanciable);
	}

	public Class<?> getClase() {
		return clase;
	}
}
