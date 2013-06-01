package DI.implementacion;

import java.lang.reflect.InvocationTargetException;

import DI.implementacion.exceptions.ErrorInstanciacionException;
import DI.implementacion.exceptions.NoSeEncuentraAccesorException;

public interface Instanciable {
	
	public Object dameInstancia();
	
	public Class<?> getClase();
}
