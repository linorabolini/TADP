package DI.implementacion;

import java.lang.reflect.InvocationTargetException;

import DI.dominio.Autos.*;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

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
	
	
}
