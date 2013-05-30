package DI.implementacion;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;

public class Lista implements Instanciable{
	
	Class<?> clase;
	 ArrayList<Instanciable> valores;
	
	public Lista (Class<?> _clase, ArrayList<Instanciable> _valores){
		this.clase = _clase;
		valores = _valores;
	}
	
	public Object dameInstancia() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, SecurityException, NoSuchMethodException {
		
		Collection lista = (Collection) clase.newInstance();
		for (Instanciable valor: valores){
			lista.add(valor.dameInstancia());
		}
		return lista;
}
	
	public Class<?> getClase() {
		return clase;
	}


}
