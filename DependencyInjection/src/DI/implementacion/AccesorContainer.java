package DI.implementacion;

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

}
