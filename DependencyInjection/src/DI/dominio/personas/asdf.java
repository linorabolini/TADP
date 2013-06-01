package DI.dominio.personas;

import DI.implementacion.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;

import junit.framework.Assert;

public class asdf {
	
	public static void main (String[] args){
	
	Auto auto;
	Persona persona;
	ConstructorContainer contenedor = new ConstructorContainer ();
	contenedor.registrarComponente("autito", Auto.class);
	contenedor.agregarDependencia("autito", 4);
	contenedor.agregarDependencia("autito", "Fiat 600");
	
	contenedor.registrarComponente("hijo1", Persona.class);
	contenedor.agregarDependencia("hijo1",10);
	contenedor.agregarDependencia("hijo1","pipo");
	
	contenedor.registrarComponente("hijo2", Persona.class);
	contenedor.agregarDependencia("hijo2",12);
	contenedor.agregarDependencia("hijo2","juan");
	
	contenedor.registrarComponente("hijo3", Persona.class);
	contenedor.agregarDependencia("hijo3",15);
	contenedor.agregarDependencia("hijo3","mina");
	
	contenedor.registrarComponente("personita", Persona.class);
	contenedor.agregarDependencia("personita", 121);
	contenedor.agregarDependencia("personita", "cochy");
	contenedor.agregarDependenciaLista("personita", ArrayList.class, 4,5,6,7,8);
	contenedor.agregarDependenciaListaConfigurada("personita", ArrayList.class, "hijo1", "hijo2", "hijo3");
	
	/*PropertyContainer contenedor = new PropertyContainer ();
	contenedor.registrarComponente("autito", Auto.class);
	contenedor.agregarDependencia("autito","ruedas", 4);
	contenedor.agregarDependencia("autito","modelo", "Fiat 600");
	contenedor.registrarComponente("personita", Persona.class);
	contenedor.agregarDependencia("personita","nombre", "cochy");
	contenedor.agregarDependencia("personita", "edad",121);
	contenedor.agregarDependenciaConfigurada("personita", "auto","autito");
	
	contenedor.registrarComponente("hijo1", Persona.class);
	contenedor.agregarDependencia("hijo1","edad", 10);
	contenedor.agregarDependencia("hijo1","nombre", "pipo");
	
	contenedor.registrarComponente("hijo2", Persona.class);
	contenedor.agregarDependencia("hijo2","edad", 12);
	contenedor.agregarDependencia("hijo2","nombre", "juan");
	
	contenedor.registrarComponente("hijo3", Persona.class);
	contenedor.agregarDependencia("hijo3","edad", 15);
	contenedor.agregarDependencia("hijo3","nombre", "mina");
	contenedor.agregarDependenciaLista("personita", "saldosTarjeta", ArrayList.class, 4,5,6,7,8);
	contenedor.agregarDependenciaListaConfigurada("personita", "hijos", ArrayList.class, "hijo1", "hijo2", "hijo3");*/
	
	/*AccesorContainer contenedor = new AccesorContainer ();
	contenedor.registrarComponente("autito", Auto.class);
	contenedor.agregarDependencia("autito","ruedas",  4);
	contenedor.agregarDependencia("autito","modelo","Fiat 600");
	contenedor.registrarComponente("personita", Persona.class);
	contenedor.agregarDependencia("personita","nombre",  "cochy");
	contenedor.agregarDependencia("personita", "edad", 121);
	contenedor.agregarDependenciaConfigurada("personita", "auto","autito");
	contenedor.agregarDependenciaLista("personita", "saldosTarjeta", ArrayList.class, 4,5,6,7,8);
	contenedor.registrarComponente("hijo1", Persona.class);
	contenedor.agregarDependencia("hijo1","edad", 10);
	contenedor.agregarDependencia("hijo1","nombre", "pipo");
	
	contenedor.registrarComponente("hijo2", Persona.class);
	contenedor.agregarDependencia("hijo2","edad", 12);
	contenedor.agregarDependencia("hijo2","nombre", "juan");
	
	contenedor.registrarComponente("hijo3", Persona.class);
	contenedor.agregarDependencia("hijo3","edad", 15);
	contenedor.agregarDependencia("hijo3","nombre", "mina");
	
	contenedor.agregarDependenciaListaConfigurada("personita", "hijos", ArrayList.class, "hijo1", "hijo2", "hijo3");*/
	
	try {
		
		auto = (Auto) contenedor.instanciaDe("autito");
		System.out.println("Ruedas: "+auto.getRuedas()+"\nModelo: "+auto.getModelo());
		persona = (Persona) contenedor.instanciaDe("personita");
		System.out.println("Nombre: "+persona.getNombre()+"\nEdad: "+persona.getEdad());
	
	} catch (InstantiationException | IllegalAccessException
			| IllegalArgumentException | InvocationTargetException | NoSuchFieldException | SecurityException | NoSuchMethodException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	}
	
}
