package DI.implementacion;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import DI.dominio.personas.*;

public class ContainersTests{

	@Test
	public void testConstructorContainerCamposPrimitivos() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, SecurityException, NoSuchMethodException {
		ConstructorContainer contenedor = new ConstructorContainer();
		contenedor.registrarComponente("autito", Auto.class);
		contenedor.agregarDependencia("autito", 4);
		contenedor.agregarDependencia("autito", "Fiat 600");
		Auto auto = (Auto) contenedor.instanciaDe("autito");
		Assert.assertEquals(4, auto.getRuedas());
		Assert.assertEquals("Fiat 600", auto.getModelo());
	}
	
	@Test
	public void testAccesorContainerCamposPrimitivos() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, SecurityException, NoSuchMethodException {
		AccesorContainer contenedor = new AccesorContainer ();
		contenedor.registrarComponente("autito", Auto.class);
		contenedor.agregarDependencia("autito","ruedas",  4);
		contenedor.agregarDependencia("autito","modelo","Fiat 600");
		Auto auto = (Auto) contenedor.instanciaDe("autito");
		Assert.assertEquals(4, auto.getRuedas());
		Assert.assertEquals("Fiat 600", auto.getModelo());
	}
	
	@Test
	public void testPropertiesContainerCamposPrimitivos() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, SecurityException, NoSuchMethodException {
		PropertyContainer contenedor = new PropertyContainer ();
		contenedor.registrarComponente("autito", Auto.class);
		contenedor.agregarDependencia("autito","ruedas",  4);
		contenedor.agregarDependencia("autito","modelo","Fiat 600");
		Auto auto = (Auto) contenedor.instanciaDe("autito");
		Assert.assertEquals(4, auto.getRuedas());
		Assert.assertEquals("Fiat 600", auto.getModelo());
	}
	
	@Test
	public void testAccesorContainerSinAlgunosCampos() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, SecurityException, NoSuchMethodException{
		AccesorContainer contenedor = new AccesorContainer ();
		contenedor.registrarComponente("autito", Auto.class);
		contenedor.agregarDependencia("autito","modelo","Fiat 600");
		Auto auto = (Auto) contenedor.instanciaDe("autito");
		Assert.assertEquals("Fiat 600", auto.getModelo());
		Assert.assertEquals(0, auto.getRuedas());
	}
	
	@Test
	public void testPropertiesContainerSinAlgunosCampos() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, SecurityException, NoSuchMethodException{
		PropertyContainer contenedor = new PropertyContainer ();
		contenedor.registrarComponente("autito", Auto.class);
		contenedor.agregarDependencia("autito","ruedas",  4);
		Auto auto = (Auto) contenedor.instanciaDe("autito");
		Assert.assertEquals(4, auto.getRuedas());
		Assert.assertEquals(null, auto.getModelo());
	}
	
	@Test
	public void testAccesorContainerConDependenciaConfigurada() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, SecurityException, NoSuchMethodException{
		AccesorContainer contenedor = new AccesorContainer ();
		contenedor.registrarComponente("autito", Auto.class);
		contenedor.agregarDependencia("autito","modelo","Fiat 600");
		contenedor.agregarDependencia("autito","ruedas",4);
		contenedor.registrarComponente("personita", Persona.class);
		contenedor.agregarDependencia("personita","nombre",  "cochy");
		contenedor.agregarDependencia("personita", "edad", 121);
		contenedor.agregarDependenciaConfigurada("personita", "auto","autito");
		
		Persona persona = (Persona) contenedor.instanciaDe("personita");
		Auto auto = persona.getAuto();
		
		Assert.assertEquals(4, auto.getRuedas());
		Assert.assertEquals("Fiat 600", auto.getModelo());
		Assert.assertEquals("cochy", persona.getNombre());
		Assert.assertEquals(121, persona.getEdad());
	}
	
	@Test
	public void testConstructorContainerConDependenciaConfigurada() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, SecurityException, NoSuchMethodException{
		ConstructorContainer contenedor = new ConstructorContainer ();
		contenedor.registrarComponente("autito", Auto.class);
		contenedor.agregarDependencia("autito", 4);
		contenedor.agregarDependencia("autito", "Fiat 600");
		contenedor.registrarComponente("personita", Persona.class);
		contenedor.agregarDependencia("personita", "cochy");
		contenedor.agregarDependencia("personita", 121);
		contenedor.agregarDependenciaConfigurada("personita", "autito");
		
		Persona persona = (Persona) contenedor.instanciaDe("personita");
		Auto auto = persona.getAuto();
		
		Assert.assertEquals(4, auto.getRuedas());
		Assert.assertEquals("Fiat 600", auto.getModelo());
		Assert.assertEquals("cochy", persona.getNombre());
		Assert.assertEquals(121, persona.getEdad());
	}
	
	@Test
	public void testPropertyContainerConDependenciaConfigurada() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, SecurityException, NoSuchMethodException{
		PropertyContainer contenedor = new PropertyContainer ();
		contenedor.registrarComponente("autito", Auto.class);
		contenedor.agregarDependencia("autito","modelo","Fiat 600");
		contenedor.agregarDependencia("autito","ruedas",4);
		contenedor.registrarComponente("personita", Persona.class);
		contenedor.agregarDependencia("personita","nombre",  "cochy");
		contenedor.agregarDependencia("personita", "edad", 121);
		contenedor.agregarDependenciaConfigurada("personita", "auto","autito");
		
		Persona persona = (Persona) contenedor.instanciaDe("personita");
		Auto auto = persona.getAuto();
		
		Assert.assertEquals(4, auto.getRuedas());
		Assert.assertEquals("Fiat 600", auto.getModelo());
		Assert.assertEquals("cochy", persona.getNombre());
		Assert.assertEquals(121, persona.getEdad());
	}
	
	@Test
	public void testPropertyContainerListaValoresPrimitivos() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, SecurityException, NoSuchMethodException{
		PropertyContainer contenedor = new PropertyContainer ();
		contenedor.registrarComponente("personita", Persona.class);
		contenedor.agregarDependenciaLista("personita", "saldosTarjeta", ArrayList.class, 4,5,6,7,8);
		Persona persona = (Persona) contenedor.instanciaDe("personita");
		Assert.assertEquals(5, persona.getSaldosTarjeta().size() );
	}
	
	@Test
	public void testAccesorContainerListaDependenciasConfiguradas() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, SecurityException, NoSuchMethodException{
		AccesorContainer contenedor = new AccesorContainer ();
		contenedor.registrarComponente("personita", Persona.class);
		
		contenedor.registrarComponente("hijo1", Persona.class);
		contenedor.agregarDependencia("hijo1","edad", 10);
		contenedor.agregarDependencia("hijo1","nombre", "pipo");
		
		contenedor.registrarComponente("hijo2", Persona.class);
		contenedor.agregarDependencia("hijo2","edad", 12);
		contenedor.agregarDependencia("hijo2","nombre", "juan");
		
		contenedor.registrarComponente("hijo3", Persona.class);
		contenedor.agregarDependencia("hijo3","edad", 15);
		contenedor.agregarDependencia("hijo3","nombre", "mina");
		
		contenedor.agregarDependenciaListaConfigurada("personita", "hijos", ArrayList.class, "hijo1", "hijo2", "hijo3");
		
		Persona persona = (Persona) contenedor.instanciaDe("personita");
		Assert.assertEquals(3, persona.getHijos().size());	
		Assert.assertEquals(12, persona.getHijos().get(1).getEdad());
		Assert.assertEquals("juan", persona.getHijos().get(1).getNombre());
	}
	
	@Test
	public void testPropertyContainerObjectoSinRegistrar()  throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, SecurityException, NoSuchMethodException{
		
		PropertyContainer contenedor = new PropertyContainer();
		contenedor.registrarComponente("personita", Persona.class);
		Persona persona = (Persona) contenedor.instanciaDe("personita");
		Assert.assertEquals(null, persona.getAuto());	
		
		contenedor.agregarDependencia("personita", "auto", new Auto(4,"Fiat 600"));
		persona = (Persona) contenedor.instanciaDe("personita");
		Assert.assertEquals(4, persona.getAuto().getRuedas());	
		Assert.assertEquals("Fiat 600", persona.getAuto().getModelo());	
	
	}
	
	@Test
	public void testConstructorContainerMascotaPerro() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, SecurityException, NoSuchMethodException{
		
		ConstructorContainer contenedor = new ConstructorContainer();
		contenedor.registrarComponente("personita", Persona.class);
		contenedor.agregarDependencia("personita", 20);
		contenedor.agregarDependencia("personita", "Ricky Ricon");
		
		Persona persona = (Persona) contenedor.instanciaDe("personita");
		Assert.assertEquals(null, persona.getMascota());
		
		contenedor.registrarComponente("perrito", Perro.class);
		contenedor.agregarDependencia("perrito", "Scooby Doo");
		contenedor.agregarDependencia("perrito", true);
		contenedor.agregarDependenciaConfigurada("personita", "perrito");
		persona = (Persona) contenedor.instanciaDe("personita");
		
		Assert.assertEquals(true, ((Perro) persona.getMascota()).isTieneHambre());
		Assert.assertEquals("Scooby Doo", ((Perro) persona.getMascota()).getNombre());
	}
	
	@Test
	public void testConstructorContainerMascotaGato() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, SecurityException, NoSuchMethodException{
		
		ConstructorContainer contenedor = new ConstructorContainer();
		contenedor.registrarComponente("personita", Persona.class);
		contenedor.agregarDependencia("personita", 20);
		contenedor.agregarDependencia("personita", "Ricky Ricon");
		
		Persona persona = (Persona) contenedor.instanciaDe("personita");
		Assert.assertEquals(null, persona.getMascota());
		
		contenedor.registrarComponente("gatito", Gato.class);
		contenedor.agregarDependencia("gatito", "Felix el Gato");
		contenedor.agregarDependencia("gatito", 7);
		contenedor.agregarDependenciaConfigurada("personita", "gatito");
		persona = (Persona) contenedor.instanciaDe("personita");
		
		Assert.assertEquals(7, ((Gato) persona.getMascota()).getVidas());
		Assert.assertEquals("Felix el Gato", ((Gato) persona.getMascota()).getNombre());
	}
	
	
}
