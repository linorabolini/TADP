package DI.implementacion;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class Container {
	
	HashMap<String,Componente> componentes;
	
	public Container(){
		componentes = new HashMap<String,Componente>();
	}
	
	public void registrarComponente (String id, Class<?> clase){
		
		Componente nuevoComponente = new Componente(clase, new InstanciarConstructor());
		componentes.put(id, nuevoComponente);
	}
	
	public void agregarDependencia (String id, Class<?> clase, Object dependencia){
		
		Componente componente = componentes.get(id);
		componente.agregarDependencia("",new Valor(dependencia,clase));
	}
	
	public void agregarDependencia (String id, String dependencia){
		
		Componente componente = componentes.get(id);
		if (componentes.containsKey(dependencia)){
			componente.agregarDependencia(dependencia, componentes.get(dependencia));
		}
	}	
		
	public Object instanciaDe (String id) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		return componentes.get(id).dameInstancia();
	}
}
