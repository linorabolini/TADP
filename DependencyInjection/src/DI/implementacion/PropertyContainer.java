package DI.implementacion;

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
	
}
