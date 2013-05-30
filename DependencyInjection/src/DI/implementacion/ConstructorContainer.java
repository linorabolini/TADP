package DI.implementacion;

import java.util.ArrayList;

public class ConstructorContainer extends Container {
	
	public void registrarComponente (String id, Class<?> clase){
		
		Componente nuevoComponente = new Componente(clase, new InstanciarConstructor());
		componentes.put(id, nuevoComponente);
	}
	
	public void agregarDependencia (String id,  Object dependencia){
		
		Componente componente = componentes.get(id);
		componente.agregarDependencia("",new Valor(dependencia,dependencia.getClass()));
	}
	
	public void agregarDependenciaConfigurada (String id, String dependencia){
		
		Componente componente = componentes.get(id);
		if (componentes.containsKey(dependencia)){
			componente.agregarDependencia(dependencia, componentes.get(dependencia));
		}
	}
	
	public void agregarDependenciaLista (String id, Class<?> claseLista, Object... valores){
		Componente componente = componentes.get(id);
		ArrayList<Instanciable> instanciables = new ArrayList<Instanciable>();
		for (Object valor: valores){
			instanciables.add(new Valor(valor, valor.getClass()));
		}
		
		componente.agregarDependencia("", new Lista(claseLista, instanciables));
	}
	
	public void agregarDependenciaListaConfigurada (String id, Class<?> claseLista, String... dependencias){
		Componente componente = componentes.get(id);
		ArrayList<Instanciable> valores = new ArrayList<Instanciable>();
		for (Object dependencia: dependencias){
			if (componentes.containsKey(dependencia)){
				valores.add(componentes.get(dependencia));
			}
		}
		componente.agregarDependencia("", new Lista(claseLista, valores));
	}

}
