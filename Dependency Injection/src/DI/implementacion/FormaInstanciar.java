package DI.implementacion;

import java.lang.reflect.InvocationTargetException;

public interface FormaInstanciar {
	
	public Object dameInstancia(Componente componente) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
	
	public void agregarDependencia (String id, Instanciable instanciable);

}
