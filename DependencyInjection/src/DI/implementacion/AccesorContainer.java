package DI.implementacion;

import java.util.ArrayList;

public class AccesorContainer extends Container {
	
	public void registrarComponente (String id, Class<?> clase){
		
		Componente nuevoComponente = new Componente(clase, new InstanciarAccesor());
		componentes.put(id, nuevoComponente);
	}
	
	public void agregarDependencia (String id, String property, Object dependencia){
		
		Componente componente = componentes.get(id);
		String nombreAccessorProperty = "set"+property.toUpperCase().charAt(0)+property.substring(1);
		componente.agregarDependencia(nombreAccessorProperty,new Valor(dependencia,dependencia.getClass()));
	}
	
	public void agregarDependenciaConfigurada (String id,String property, String dependencia){
		
		Componente componente = componentes.get(id);
		if (componentes.containsKey(dependencia)){
			String nombreAccessorProperty = "set"+property.toUpperCase().charAt(0)+property.substring(1);
			componente.agregarDependencia(nombreAccessorProperty, componentes.get(dependencia));
		}
	}
	
	public void agregarDependenciaLista (String id, String property, Class<?> claseLista, Object... valores){
		Componente componente = componentes.get(id);
		String nombreAccessorProperty = "set"+property.toUpperCase().charAt(0)+property.substring(1);
		ArrayList<Instanciable> instanciables = new ArrayList<Instanciable>();
		for (Object valor: valores){
			instanciables.add(new Valor(valor, valor.getClass()));
		}
		
		componente.agregarDependencia(nombreAccessorProperty, new Lista(claseLista, instanciables));
	}
	
	public void agregarDependenciaListaConfigurada (String id, String property, Class<?> claseLista, String... dependencias){
		String nombreAccessorProperty = "set"+property.toUpperCase().charAt(0)+property.substring(1);
		Componente componente = componentes.get(id);
		ArrayList<Instanciable> valores = new ArrayList<Instanciable>();
		for (Object dependencia: dependencias){
			if (componentes.containsKey(dependencia)){
				valores.add(componentes.get(dependencia));
			}
		}
		componente.agregarDependencia(nombreAccessorProperty, new Lista(claseLista, valores));
	}

}
