package DI.dominio.Autos;

import DI.implementacion.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;

public class asdf {
	
	public static void main (String[] args){
	
	Auto auto;
	Persona persona;
	/*ConstructorContainer contenedor = new ConstructorContainer ();
	contenedor.registrarComponente("autito", Auto.class);
	contenedor.agregarDependencia("autito", int.class, 4);
	contenedor.agregarDependencia("autito", String.class, "Fiat 600");
	contenedor.registrarComponente("personita", Persona.class);
	contenedor.agregarDependencia("personita", String.class, "cochy");
	contenedor.agregarDependencia("personita", int.class, 121);
	contenedor.agregarDependencia("personita", "autito");
	*/
	/*PropertyContainer contenedor = new PropertyContainer ();
	contenedor.registrarComponente("autito", Auto.class);
	contenedor.agregarDependencia("autito","ruedas", int.class, 4);
	contenedor.agregarDependencia("autito","modelo", String.class, "Fiat 600");
	contenedor.registrarComponente("personita", Persona.class);
	contenedor.agregarDependencia("personita","nombre", String.class, "cochy");
	contenedor.agregarDependencia("personita", "edad",int.class, 121);
	contenedor.agregarDependencia("personita", "auto","autito");*/
	AccesorContainer contenedor = new AccesorContainer ();
	contenedor.registrarComponente("autito", Auto.class);
	contenedor.agregarDependencia("autito","ruedas", int.class, 4);
	contenedor.agregarDependencia("autito","modelo", String.class, "Fiat 600");
	contenedor.registrarComponente("personita", Persona.class);
	contenedor.agregarDependencia("personita","nombre", String.class, "cochy");
	contenedor.agregarDependencia("personita", "edad",int.class, 121);
	contenedor.agregarDependencia("personita", "auto","autito");
	
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
