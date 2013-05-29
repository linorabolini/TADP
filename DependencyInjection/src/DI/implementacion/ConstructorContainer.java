package DI.implementacion;

public class ConstructorContainer extends Container {
	
	public void registrarComponente (String id, Class<?> clase){
		
		Componente nuevoComponente = new Componente(clase, new InstanciarConstructor());
		componentes.put(id, nuevoComponente);
	}
	
	public void agregarDependencia (String id, Class<?> clase, Object dependencia){
		
		Componente componente = componentes.get(id);
		componente.agregarDependencia("",new Valor(dependencia,clase));
	}
	
	public void agregarDependencia (String id, String dependencia){
		
		Componente componente = componentes.get(id);
		if (componentes.containsKey(dependencia)){
			componente.agregarDependencia(dependencia, componentes.get(dependencia));
		}
	}

}
