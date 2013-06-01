package DI.implementacion;

import java.util.ArrayList;
import java.util.Collection;

import DI.implementacion.exceptions.ErrorInstanciacionException;

public class Lista implements Instanciable{
	
	Class<?> clase;
	 ArrayList<Instanciable> valores;
	
	public Lista (Class<?> _clase, ArrayList<Instanciable> _valores){
		this.clase = _clase;
		valores = _valores;
	}
	
	public Object dameInstancia(){
		
		Collection lista;
		try {
			lista = (Collection) clase.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new ErrorInstanciacionException(e);
		}
		for (Instanciable valor: valores){
			lista.add(valor.dameInstancia());
		}
		return lista;
}
	
	public Class<?> getClase() {
		return clase;
	}


}
