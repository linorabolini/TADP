package DI.implementacion;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

public class Lista implements Instanciable{
	
	Class<?> clase;
	HashMap<String,Instanciable> valores;
	
	public Lista (Class<?> _clase){
		this.clase = _clase;
		valores = new HashMap<String,Instanciable>();
	}
	
	public Object dameInstancia() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		ArrayList<Object> lista = new ArrayList<Object>();
		for (Instanciable valor: valores.values()){
			lista.add(valor.dameInstancia());
		}
		return lista;
	}
	
	public Class<?> getClase() {
		return clase;
	}


}
