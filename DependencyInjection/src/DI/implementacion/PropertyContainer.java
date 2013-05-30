package DI.implementacion;

import java.util.ArrayList;

public class PropertyContainer extends Container {

	public void registrarComponente (String id, Class<?> clase){
		
		Componente nuevoComponente = new Componente(clase, new InstanciarProperty());
		componentes.put(id, nuevoComponente);
	}
	
	public void agregarDependencia (String id, String property,  Object dependencia){
		
		Componente componente = componentes.get(id);
		componente.agregarDependencia(property,new Valor(dependencia,dependencia.getClass()));
	}
	
	public void agregarDependenciaConfigurada (String id,String property, String dependencia){
		
		Componente componente = componentes.get(id);
		if (componentes.containsKey(dependencia)){
			componente.agregarDependencia(property, componentes.get(dependencia));
		}
	}
	
	public void agregarDependenciaLista (String id, String property, Class<?> claseLista, Object... valores){
		Componente componente = componentes.get(id);
		ArrayList<Instanciable> instanciables = new ArrayList<Instanciable>();
		for (Object valor: valores){
			instanciables.add(new Valor(valor, valor.getClass()));
		}
		
		componente.agregarDependencia(property, new Lista(claseLista, instanciables));
	}
	
	public void agregarDependenciaListaConfigurada (String id, String property, Class<?> claseLista, String... dependencias){
		Componente componente = componentes.get(id);
		ArrayList<Instanciable> valores = new ArrayList<Instanciable>();
		for (Object dependencia: dependencias){
			if (componentes.containsKey(dependencia)){
				valores.add(componentes.get(dependencia));
			}
		}
		componente.agregarDependencia(property, new Lista(claseLista, valores));
	}
	
}
