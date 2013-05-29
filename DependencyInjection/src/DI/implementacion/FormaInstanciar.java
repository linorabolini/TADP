package DI.implementacion;

import java.lang.reflect.InvocationTargetException;

public interface FormaInstanciar {
	
	public Object dameInstancia(Componente componente) throws InstantiationException, IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException, InvocationTargetException, NoSuchMethodException;
	
	public void agregarDependencia (String id, Instanciable instanciable);

}
