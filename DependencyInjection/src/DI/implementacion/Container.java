package DI.implementacion;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import DI.implementacion.exceptions.*;

public abstract class Container {
	
	HashMap<String,Componente> componentes;
	
	public Container(){
		componentes = new HashMap<String,Componente>();
	}
	
	protected void registrarComponente (String id, Componente nuevoComponente){
			
		componentes.put(id, nuevoComponente);
	}
	
	protected void agregarDependencia (String id,  String dependenciaId, Valor dependencia){
		
		Componente componente = componentes.get(id);
		if (componente == null)
			throw new ComponenteNoRegistradoException();
		componente.agregarDependencia(dependenciaId,dependencia);
	}
	
	protected void agregarDependenciaConfigurada (String id, String dependenciaId, String dependencia){
		
		Componente componente = componentes.get(id);
		if (componente == null)
			throw new ComponenteNoRegistradoException();
		if (componentes.containsKey(dependencia)){
			componente.agregarDependencia(dependenciaId, componentes.get(dependencia));
		}
		else
			throw new ComponenteNoRegistradoException();
	}
	
	protected void agregarDependenciaLista (String id, String dependenciaId, Class<?> claseLista, Object... valores){
		if (!Collection.class.isAssignableFrom(claseLista) || (Modifier.isAbstract(claseLista.getModifiers()) || claseLista.isInterface()))
			throw new NoEsCollectionConcretoException();
		
		Componente componente = componentes.get(id);
		if (componente == null)
			throw new ComponenteNoRegistradoException();
		ArrayList<Instanciable> instanciables = new ArrayList<Instanciable>();
		for (Object valor: valores){
			instanciables.add(new Valor(valor, valor.getClass()));
		}
		
		componente.agregarDependencia(dependenciaId, new Lista(claseLista, instanciables));
	}
	
	protected void agregarDependenciaListaConfigurada (String id, String dependenciaId, Class<?> claseLista, String... dependencias){
		if (!Collection.class.isAssignableFrom(claseLista) || (Modifier.isAbstract(claseLista.getModifiers()) || claseLista.isInterface()))
			throw new NoEsCollectionConcretoException();
		
		Componente componente = componentes.get(id);
		if (componente == null)
			throw new ComponenteNoRegistradoException();
		ArrayList<Instanciable> valores = new ArrayList<Instanciable>();
		for (Object dependencia: dependencias){
			if (componentes.containsKey(dependencia)){
				valores.add(componentes.get(dependencia));
			}
			else
				throw new ComponenteNoRegistradoException();
		}
		componente.agregarDependencia(dependenciaId, new Lista(claseLista, valores));
	}
}
