package DI.implementacion;


public class PropertyContainer extends Container {

	public void registrarComponente (String id, Class<?> clase){
		
		Componente nuevoComponente = new Componente(clase, new InstanciarProperty());
		this.registrarComponente(id, nuevoComponente);
	}
	
	public void agregarDependencia (String id, String property,  Object dependencia){
		
		this.agregarDependencia(id, property, new Valor(dependencia,dependencia.getClass()));
	}
	
	public void agregarDependenciaConfigurada (String id,String property, String dependencia){
		
		super.agregarDependenciaConfigurada(id, property, dependencia);
	}
	
	public void agregarDependenciaLista (String id, String property, Class<?> claseLista, Object... valores){
		super.agregarDependenciaLista(id, property, claseLista, valores);
	}
	
	public void agregarDependenciaListaConfigurada (String id, String property, Class<?> claseLista, String... dependencias){
		
		super.agregarDependenciaListaConfigurada(id, property, claseLista, dependencias);
	}
}
