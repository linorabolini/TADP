package DI.implementacion;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Container {
	
	HashMap<String,Componente> componentes;
	
	public Container(){
		componentes = new HashMap<String,Componente>();
	}
	
	public Object instanciaDe (String id) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, SecurityException, NoSuchMethodException{
		return componentes.get(id).dameInstancia();
	}
	
	protected void registrarComponente (String id, Componente nuevoComponente){
			
		componentes.put(id, nuevoComponente);
	}
	
	protected void agregarDependencia (String id,  String dependenciaId, Valor dependencia){
		
		Componente componente = componentes.get(id);
		componente.agregarDependencia(dependenciaId,dependencia);
	}
	
	protected void agregarDependenciaConfigurada (String id, String dependenciaId, String dependencia){
		
		Componente componente = componentes.get(id);
		if (componentes.containsKey(dependencia)){
			componente.agregarDependencia(dependenciaId, componentes.get(dependencia));
		}
	}
	
	protected void agregarDependenciaLista (String id, String dependenciaId, Class<?> claseLista, Object... valores){
		Componente componente = componentes.get(id);
		ArrayList<Instanciable> instanciables = new ArrayList<Instanciable>();
		for (Object valor: valores){
			instanciables.add(new Valor(valor, valor.getClass()));
		}
		
		componente.agregarDependencia(dependenciaId, new Lista(claseLista, instanciables));
	}
	
	protected void agregarDependenciaListaConfigurada (String id, String dependenciaId, Class<?> claseLista, String... dependencias){
		Componente componente = componentes.get(id);
		ArrayList<Instanciable> valores = new ArrayList<Instanciable>();
		for (Object dependencia: dependencias){
			if (componentes.containsKey(dependencia)){
				valores.add(componentes.get(dependencia));
			}
		}
		componente.agregarDependencia(dependenciaId, new Lista(claseLista, valores));
	}
}
