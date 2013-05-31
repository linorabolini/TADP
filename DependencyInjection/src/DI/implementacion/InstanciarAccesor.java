package DI.implementacion;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class InstanciarAccesor extends FormaInstanciar {

	HashMap<String,Instanciable> dependencias;
	
	public InstanciarAccesor(){
		dependencias = new HashMap<String,Instanciable>();
	}
	
	public Object dameInstancia(Componente componente) throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException{
	
		Object nuevoObjeto = componente.getClase().newInstance();		
		
		for (String accesor : dependencias.keySet()){
			Method metodo = encontrarAccesor (accesor, componente.getClase().getMethods());
			metodo.invoke(nuevoObjeto, dependencias.get(accesor).dameInstancia());
		}
		return nuevoObjeto;	
	}


	public void agregarDependencia(String id, Instanciable instanciable) {
		dependencias.put(id, instanciable);

	}
	
	Method encontrarAccesor (String accesor,  Method[] listaMetodos ){
		
		for (Method metodo: listaMetodos){
			if (metodo.getName().compareTo(accesor) == 0)
				return metodo;
		}
		
		return null;
	}
	
	

}
