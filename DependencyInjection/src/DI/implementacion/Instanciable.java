package DI.implementacion;

import java.lang.reflect.InvocationTargetException;

public interface Instanciable {
	
	public Object dameInstancia() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, SecurityException, NoSuchMethodException;
	
	public Class<?> getClase();
}
