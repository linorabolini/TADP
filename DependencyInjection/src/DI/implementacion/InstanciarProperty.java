package DI.implementacion;

import java.lang.reflect.Field;
import java.util.HashMap;

import DI.implementacion.exceptions.ErrorInstanciacionException;
import DI.implementacion.exceptions.NoSeEncuentraPropertyException;

public class InstanciarProperty extends FormaInstanciar {
	
	HashMap<String,Instanciable> dependencias;
	
	public InstanciarProperty(){
		dependencias = new HashMap<String,Instanciable>();
	}
	
	public Object dameInstancia(Componente componente){
		
		Object nuevoObjeto = null;
		try {
			nuevoObjeto = componente.getClase().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new ErrorInstanciacionException(e);
		}		
		
		for (String propiedad : dependencias.keySet()){
			
			Field campo;
			try {
				campo = componente.getClase().getDeclaredField(propiedad);
			} catch (NoSuchFieldException | SecurityException e) {
				throw new NoSeEncuentraPropertyException();
			}
			campo.setAccessible(true);
			try {
				campo.set(nuevoObjeto, dependencias.get(propiedad).dameInstancia());
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new ErrorInstanciacionException(e);
			}
			campo.setAccessible(false);
		}
		
		return nuevoObjeto;
		
	}
	
	public void agregarDependencia (String id, Instanciable instanciable){
		
		dependencias.put(id, instanciable);
		
	}
}
