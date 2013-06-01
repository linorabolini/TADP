package DI.implementacion;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import DI.implementacion.exceptions.ErrorInstanciacionException;
import DI.implementacion.exceptions.NoSeEncuentraConstructorException;

public class InstanciarConstructor extends FormaInstanciar {
	
	ArrayList<Instanciable> dependencias;
		
	public InstanciarConstructor(){
		dependencias = new ArrayList<Instanciable>();
		inicializarClasesPrimitivas();
	}
	
	public Object dameInstancia(Componente componente){
		
		Constructor<?>[] constructores = componente.getClase().getConstructors();
		
		ArrayList<Object> argumentos = new ArrayList<Object>();
		
		for (Instanciable dependencia : dependencias){
			argumentos.add(dependencia.dameInstancia());
		}
		
		Constructor<?> constructor = seleccionarConstructor(constructores, dependencias);
		if ( constructor != null)
			try {
				return constructor.newInstance(argumentos.toArray());
			} catch (InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException e) {
				throw new ErrorInstanciacionException(e);
			}
		else
			throw new NoSeEncuentraConstructorException();
	}
	
	public void agregarDependencia (String id, Instanciable instanciable){
		
		dependencias.add(instanciable);
		
	}
	
	Constructor<?> seleccionarConstructor(Constructor<?>[] constructores, ArrayList<Instanciable> dependencias){
		
		for(Constructor<?> constructor : constructores){
			Class<?>[] clasesParametros = constructor.getParameterTypes();
	
			if (correspondeConstructor(clasesParametros,dependencias))
				return constructor;
		}
		return null;
	}
	
	boolean correspondeConstructor(Class<?>[] clasesParametros, ArrayList<Instanciable> argumentos){
		
		int i=0;
		
		if (clasesParametros.length != argumentos.size())
			return false;
		
		for (Class<?> clase : clasesParametros){
						
			if (clase.isPrimitive()){
				Class<?> claseArgumentoConstructor = clasesPrimitivas.get(argumentos.get(i).getClase());
				if (claseArgumentoConstructor == null ){
					return false;
				}
				else{
					if (claseArgumentoConstructor.getName().compareTo(clase.getName()) != 0)
						return false;
				}
			}
			else
				if (!clase.isInstance(argumentos.get(i).dameInstancia()))
					return false;

			i++;
		}
		
		return true;
		
	}

}
