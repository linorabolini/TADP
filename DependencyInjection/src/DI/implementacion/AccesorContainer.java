package DI.implementacion;

public class AccesorContainer extends Container {
	
	public void registrarComponente (String id, Class<?> clase){
		
		Componente nuevoComponente = new Componente(clase, new InstanciarAccesor());
		this.registrarComponente(id, nuevoComponente);
	}
	
	public void agregarDependencia (String id, String property, Object dependencia){
		
		String nombreAccessorProperty = "set"+property.toUpperCase().charAt(0)+property.substring(1);
		this.agregarDependencia(id, nombreAccessorProperty, new Valor(dependencia,dependencia.getClass()));
	}
	
	public void agregarDependenciaConfigurada (String id,String property, String dependencia){
	
		String nombreAccessorProperty = "set"+property.toUpperCase().charAt(0)+property.substring(1);
		super.agregarDependenciaConfigurada(id, nombreAccessorProperty, dependencia);
	}
	
	public void agregarDependenciaLista (String id, String property, Class<?> claseLista, Object... valores){
		
		String nombreAccessorProperty = "set"+property.toUpperCase().charAt(0)+property.substring(1);
		super.agregarDependenciaLista(id, nombreAccessorProperty, claseLista, valores);
	}
	
	public void agregarDependenciaListaConfigurada (String id, String property, Class<?> claseLista, String... dependencias){
		String nombreAccessorProperty = "set"+property.toUpperCase().charAt(0)+property.substring(1);
		super.agregarDependenciaListaConfigurada(id, nombreAccessorProperty, claseLista, dependencias);
	}
}
