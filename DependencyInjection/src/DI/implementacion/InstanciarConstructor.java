package DI.implementacion;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class InstanciarConstructor extends FormaInstanciar {
	
	ArrayList<Instanciable> dependencias;
		
	public InstanciarConstructor(){
		dependencias = new ArrayList<Instanciable>();
		inicializarClasesPrimitivas();
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
	
	Constructor<?> seleccionarConstructor(Constructor<?>[] constructores, ArrayList<Instanciable> dependencias) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, SecurityException, NoSuchMethodException{
		
		for(Constructor<?> constructor : constructores){
			Class<?>[] clasesParametros = constructor.getParameterTypes();
	
			if (correspondeConstructor(clasesParametros,dependencias))
				return constructor;
		}
		return null;
	}
	
	boolean correspondeConstructor(Class<?>[] clasesParametros, ArrayList<Instanciable> argumentos) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, SecurityException, NoSuchMethodException{
		
		int i=0;
		
		if (clasesParametros.length != argumentos.size())
			return false;
		
		for (Class<?> clase : clasesParametros){
						
			if (clase.isPrimitive()){
				if (!(clasesPrimitivas.get(argumentos.get(i).getClase()).getName() == clase.getName()))
					return false; 
			}
			else
				if (!clase.isInstance(argumentos.get(i).dameInstancia()))
					return false;

			i++;
		}
		
		return true;
		
	}

}
