package DI.implementacion;


public class ConstructorContainer extends Container {
	
	public void registrarComponente (String id, Class<?> clase){
		
		Componente nuevoComponente = new Componente(clase, new InstanciarConstructor());
		this.registrarComponente(id, nuevoComponente);
	}
	
	public void agregarDependencia (String id,  Object dependencia){
		
		this.agregarDependencia(id, "", new Valor(dependencia,dependencia.getClass()));
	}
	
	public void agregarDependenciaConfigurada (String id, String dependencia){
		
		super.agregarDependenciaConfigurada(id, "", dependencia);
	}
	
	public void agregarDependenciaLista (String id, Class<?> claseLista, Object... valores){
		
		this.agregarDependenciaLista(id, "", claseLista, valores);
	}
	
	public void agregarDependenciaListaConfigurada (String id, Class<?> claseLista, String... dependencias){
		
		this.agregarDependenciaListaConfigurada(id, "", claseLista, dependencias);
	}

}
