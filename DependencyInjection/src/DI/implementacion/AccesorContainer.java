package DI.implementacion;

public class AccesorContainer extends Container {
	
	public void registrarComponente (String id, Class<?> clase){
		
		Componente nuevoComponente = new Componente(clase, new InstanciarAccesor());
		this.registrarComponente(id, nuevoComponente);
	}
	
	public void agregarDependencia (String id, String property, Object dependencia){
		this.agregarDependencia(id, toSetAccessor(property), new Valor(dependencia,dependencia.getClass()));
	}

	public void agregarDependenciaConfigurada (String id,String property, String dependencia){
		super.agregarDependenciaConfigurada(id, toSetAccessor(property), dependencia);
	}
	
	public void agregarDependenciaLista (String id, String property, Class<?> claseLista, Object... valores){
		super.agregarDependenciaLista(id, toSetAccessor(property), claseLista, valores);
	}
	
	public void agregarDependenciaListaConfigurada (String id, String property, Class<?> claseLista, String... dependencias){
		super.agregarDependenciaListaConfigurada(id, toSetAccessor(property), claseLista, dependencias);
	}
	
	private String toSetAccessor(String property) {
		return "set"+property.toUpperCase().charAt(0)+property.substring(1);
	}
}
