package DI.implementacion;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public abstract class Container {
	
	HashMap<String,Componente> componentes;
	
	public Container(){
		componentes = new HashMap<String,Componente>();
	}
	
	public Object instanciaDe (String id) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, SecurityException, NoSuchMethodException{
		return componentes.get(id).dameInstancia();
	}
}
