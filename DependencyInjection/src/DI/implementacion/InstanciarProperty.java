package DI.implementacion;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class InstanciarProperty extends FormaInstanciar {
	
	HashMap<String,Instanciable> dependencias;
	
	public InstanciarProperty(){
		dependencias = new HashMap<String,Instanciable>();
	}
	
	public Object dameInstancia(Componente componente) throws InstantiationException, IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException, InvocationTargetException, NoSuchMethodException{
		
		Object nuevoObjeto = componente.getClase().newInstance();		
		
		for (String propiedad : dependencias.keySet()){
			Field campo = componente.getClase().getDeclaredField(propiedad);
			campo.setAccessible(true);
			campo.set(nuevoObjeto, dependencias.get(propiedad).dameInstancia());
			campo.setAccessible(false);
		}
		
		return nuevoObjeto;
		
	}
	
	public void agregarDependencia (String id, Instanciable instanciable){
		
		dependencias.put(id, instanciable);
		
	}
}
