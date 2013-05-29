package DI.implementacion;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class InstanciarConstructor implements FormaInstanciar {
	
	ArrayList<Instanciable> dependencias;
	
	public InstanciarConstructor(){
		dependencias = new ArrayList<Instanciable>();
	}
	
	public Object dameInstancia(Componente componente) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, SecurityException, NoSuchMethodException{
		
		Constructor<?>[] constructores = componente.getClase().getConstructors();
		
		ArrayList<Object> argumentos = new ArrayList<Object>();
		
		for (Instanciable dependencia : dependencias){
			argumentos.add(dependencia.dameInstancia());
		}
		
		Constructor<?> constructor = seleccionarConstructor(constructores, dependencias);
		if ( constructor != null)
			return constructor.newInstance(argumentos.toArray());
		else
			return null;
	}
	
	public void agregarDependencia (String id, Instanciable instanciable){
		
		dependencias.add(instanciable);
		
	}
	
	Constructor<?> seleccionarConstructor(Constructor<?>[] constructores, ArrayList<Instanciable> argumentos){
		
		for(Constructor<?> constructor : constructores){
			Class<?>[] clasesParametros = constructor.getParameterTypes();
	
			if (correspondeConstructor(clasesParametros,argumentos))
				return constructor;
		}
		return null;
	}
	
	boolean correspondeConstructor(Class<?>[] clasesParametros, ArrayList<Instanciable> argumentos){
		
		int i=0;
		
		for (Class<?> clase : clasesParametros){
						
			if (i<argumentos.size())
			
			if (argumentos.get(i).getClase().getName() != clase.getName())
				return false; 
			
			i++;
		}
		
		if (clasesParametros.length == argumentos.size())
			return true;
		else
			return false;
	}

}
