package DI.implementacion;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class InstanciarAccesor implements FormaInstanciar {

	HashMap<String,Instanciable> dependencias;
	
	public InstanciarAccesor(){
		dependencias = new HashMap<String,Instanciable>();
	}
	
	public Object dameInstancia(Componente componente) throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException{
	
		Object nuevoObjeto = componente.getClase().newInstance();		
		
		for (String accesor : dependencias.keySet()){
			Method metodo = componente.getClase().getMethod(accesor,dependencias.get(accesor).getClase());
			metodo.invoke(nuevoObjeto, dependencias.get(accesor).dameInstancia());
		}
		
		return nuevoObjeto;	
		
	}


	public void agregarDependencia(String id, Instanciable instanciable) {
		dependencias.put(id, instanciable);

	}

}
