package DI.implementacion.dsl

import static org.junit.Assert.*
import static ContainerEspecificacion.*
import junit.framework.Assert;

import org.junit.Test

import DI.dominio.personas.Auto;
import DI.dominio.personas.Gato;
import DI.dominio.personas.Perro;
import DI.dominio.personas.Persona;
import DI.implementacion.ConstructorContainer;
import DI.implementacion.exceptions.ComponenteNoRegistradoException;
import DI.implementacion.exceptions.NoSeEncuentraAccesorException;
import DI.implementacion.exceptions.NoSeEncuentraConstructorException;
import DI.implementacion.exceptions.NoSeEncuentraPropertyException;

class DSLtests {
	
	
	@Test
	void testConstructorContainerCamposPrimitivos() {
		
		def contenedor = especificacionConstructor {
			
			registrar el objeto con nombre autito de la clase Auto.class con las dependencias ([4,"Fiat 600"])
			
		}
		
		def auto = (Auto) contenedor.instanciaDe("autito")
		assertEquals 4, auto.getRuedas()
		assertEquals "Fiat 600", auto.getModelo()
	}
	
	@Test
	void testConstructorContainerConDependenciaConfigurada() {
		
		def contenedor = especificacionConstructor {
			
			registrar objeto llamado autito de la clase Auto.class con las dependencias ([4,"Fiat 600"])
			
			registrar objeto llamado personita de la clase Persona.class con las dependencias (["cochy",121,autito])
			
		}
		
		def persona = (Persona) contenedor.instanciaDe("personita")
		def auto = persona.getAuto()
		
		assertEquals 4, auto.getRuedas()
		assertEquals "Fiat 600", auto.getModelo()
		assertEquals "cochy", persona.getNombre()
		assertEquals 121, persona.getEdad()
		
	}
	
	@Test
	void testConstructorContainerMascotaPerro(){
		def contenedor = especificacionConstructor {
			
			registrar objeto llamado perrito de la clase Perro.class con las dependencias (["Scooby Doo",true])
			
			registrar objeto llamado personita de la clase Persona.class con las dependencias ([20,"Ricky Ricon",perrito])
		}
		
		def persona = (Persona) contenedor.instanciaDe("personita")
		assertEquals(true, ((Perro) persona.getMascota()).isTieneHambre())
		assertEquals("Scooby Doo", ((Perro) persona.getMascota()).getNombre())
	}
	
	@Test
	void testConstructorContainerMascotaGato(){
		
		def contenedor = especificacionConstructor {
			
			registrar objeto llamado gatito de la clase Gato.class con las dependencias (["Felix el Gato",7])
			
			registrar objeto llamado personita de la clase Persona.class con las dependencias ([20,"Ricky Ricon",gatito])
		}
		
		def persona = (Persona) contenedor.instanciaDe("personita")
		assertEquals(7, ((Gato) persona.getMascota()).getVidas())
		assertEquals("Felix el Gato", ((Gato) persona.getMascota()).getNombre())
	}
	
	@Test
	void testConstructorContainerListaPrimitivos(){
		
		def contenedor = especificacionConstructor {
			
			registrar objeto llamado personita de la clase Persona.class con las dependencias ([20,"Ricky Ricon",[4,8,9,10]])
		}
		
		def persona = (Persona) contenedor.instanciaDe("personita")
		assertEquals(20, persona.getEdad())
		assertEquals(4, persona.getSaldosTarjeta().size())
	}
	
	@Test (expected=NoSeEncuentraConstructorException.class)
	void testConstructorContainerNoSeEncuentraConstructorException(){
		
		def contenedor = especificacionConstructor{
			
			registrar objeto llamado autito de la clase Autito.class con las dependencias ([4.5,"Fiat 600"]) //EN EL CONSTRUCTOR NO HAY NINGUN PARAMETRO DOUBLE
			
			//registrar autito de la clase Autito.class con las dependencias ([4.5,"Fiat 600"])
		}
		
		def auto = (Auto) contenedor.instanciaDe("autito")
		assertEquals(4, auto.getRuedas())
		assertEquals("Fiat 600", auto.getModelo())
	}
	
	@Test
	void testPropertiesContainerCamposPrimitivos(){
		
		def contenedor = especificacionProperties{
			
			registrar objeto llamado autito de la clase Auto.class con las dependencias ([
			    ruedas : 4,
				modelo : "Fiat 600"
			])
		}
		
		def auto = (Auto) contenedor.instanciaDe("autito")
		assertEquals(4, auto.getRuedas())
		assertEquals("Fiat 600", auto.getModelo())
		
	}
	
	@Test
	void testPropertiesContainerSinAlgunosCampos(){
		
		def contenedor = especificacionProperties{
			
			registrar objeto llamado autito de la clase Auto.class con las dependencias ([
				ruedas : 4
			])
		}
		
		def auto = (Auto) contenedor.instanciaDe("autito")
		assertEquals(4, auto.getRuedas())
		assertEquals(null, auto.getModelo())
	}
	
	@Test
	void testPropertyContainerConDependenciaConfigurada(){
		
		def contenedor = especificacionProperties{
			
			registrar objeto llamado autito de la clase Auto.class con las dependencias ([
				ruedas : 4,
				modelo : "Fiat 600"
			])
			
			registrar objeto llamado personita de la clase Persona.class con las dependencias ([
				nombre : cochy,
				edad : 121,
				auto : autito
			])
		}
		
		def persona = (Persona) contenedor.instanciaDe("personita")
		def auto = persona.getAuto()
		
		assertEquals(4, auto.getRuedas());
		assertEquals("Fiat 600", auto.getModelo())
		assertEquals("cochy", persona.getNombre())
		assertEquals(121, persona.getEdad())
		
	}
	
	@Test
	void testPropertyContainerObjectoSinRegistrar(){
		
		def contenedor = especificacionProperties{
			
			registrar objeto llamado personita de la clase Persona.class con las dependencias ([
				auto : new Auto(4,"Fiat 600")
			])
		}
		
		def persona = (Persona) contenedor.instanciaDe("personita")
		assertEquals(4, persona.getAuto().getRuedas())
		assertEquals("Fiat 600", persona.getAuto().getModelo())
	}
	
	@Test
	void testPropertyContainerListaValoresPrimitivos(){
		
		def contenedor = especificacionProperties{
			
			registrar objeto llamado personita de la clase Persona.class con las dependencias ([
				saldosTarjeta : [4,5,6,7,8],
				nombre : ricardo
			])
		}
		
		def persona = (Persona) contenedor.instanciaDe("personita");
		assertEquals(5, persona.getSaldosTarjeta().size());
		assertEquals(7, persona.getSaldosTarjeta().get(3));
	}
	
	@Test (expected=NoSeEncuentraPropertyException.class)
	void testPropertyContainerConDependenciaConfiguradaPropiedadNoExiste(){
		
		def contenedor = especificacionProperties{
			
			registrar objeto llamado autito de la clase Auto.class con las dependencias ([
				ruedas : 4,
				modelo : "Fiat 600"
			])
			
			registrar objeto llamado personita de la clase Persona.class con las dependencias ([
				nombre : cochy,
				edshdfad : 121,    //LINEA QUE ESTA MAL (NO EXISTE ESA PROPERTY)
				auto : autito
			])
		}
		
		def persona = (Persona) contenedor.instanciaDe("personita")
		def auto = persona.getAuto()
		
		assertEquals(4, auto.getRuedas())
		assertEquals("Fiat 600", auto.getModelo())
		assertEquals("cochy", persona.getNombre())
		assertEquals(121, persona.getEdad())
	}
	
	@Test
	void testAccesorContainerCamposPrimitivos(){
		
		def contenedor = especificacionAccesors{
			
			registrar objeto llamado autito de la clase Auto.class con las dependencias ([
				modelo : "Fiat 600",
				ruedas : 4
			])
			
		}
		
		def auto = (Auto) contenedor.instanciaDe("autito")
		assertEquals(4, auto.getRuedas())
		assertEquals("Fiat 600", auto.getModelo())
		
	}

	@Test
	void testAccesorContainerSinAlgunosCampos(){
		def contenedor = especificacionAccesors{
			
			registrar objeto llamado autito de la clase Auto.class con las dependencias ([
				modelo : "Fiat 600",
			])
			
		}
		
		def auto = (Auto) contenedor.instanciaDe("autito")
		assertEquals(0, auto.getRuedas())
		assertEquals("Fiat 600", auto.getModelo())
	}
	
	@Test
	void testAccesorContainerConDependenciaConfigurada(){
		
		def contenedor = especificacionAccesors{
			
			registrar objeto llamado autito de la clase Auto.class con las dependencias ([
				modelo : "Fiat 600",
				ruedas : 4
			])
			
			registrar objeto llamado personita de la clase Persona.class con las dependencias ([
				nombre : cochy,
				edad : 121,
				auto : autito
			])
			
		}
		
		def persona = (Persona) contenedor.instanciaDe("personita")
		def auto = persona.getAuto()
		
		assertEquals(4, auto.getRuedas())
		assertEquals("Fiat 600", auto.getModelo())
		assertEquals("cochy", persona.getNombre())
		assertEquals(121, persona.getEdad())
		
	}
	
	@Test (expected=NoSeEncuentraAccesorException.class)
	void testAccesorContainerNoSeEncuentraSccesorException(){
		
		def contenedor = especificacionAccesors{
			
			registrar objeto llamado personita de la clase Persona.class con las dependencias ([
				nombre : rocky,
				aaaa : 78 //LINEA QUE ESTA MAL (NO EXISTE EL ACCESOR aaaa)
			])
			
		}
		
		def persona = (Persona) contenedor.instanciaDe("personita")
		
	}
	
	@Test
	void testAccesorContainerListaValoresPrimitivos(){
		
		def contenedor = especificacionAccesors{
			
			registrar objeto llamado personita de la clase Persona.class con las dependencias ([
				saldosTarjeta : [4,5,6,7,8],
				nombre : ricardo
			])
		}
		
		def persona = (Persona) contenedor.instanciaDe("personita")
		assertEquals(5, persona.getSaldosTarjeta().size())
		assertEquals(7, persona.getSaldosTarjeta().get(3))
		assertEquals("ricardo",persona.getNombre())
	}
	
	@Test
	void testConstructorContainerListaDependenciasConfiguradas(){
		
		def contenedor = especificacionConstructor{
			
			registrar lista de objetos tipo Persona.class llamada hijos con las especificaciones([
				[10, pipo],
				[12, juan],
				[15, mina]
			])
			
			registrar objeto llamado personita de la clase Persona.class con las dependencias ([40,papa,[4,5,6,7],hijos])
			
		}
		
		def persona = (Persona) contenedor.instanciaDe("personita")
		
		assertEquals(40,persona.getEdad())
		assertEquals(3, persona.getHijos().size())
		assertEquals(12, persona.getHijos().get(1).getEdad())
		assertEquals("juan", persona.getHijos().get(1).getNombre())
	}
	
	@Test
	void testAccesorContainerListaDependenciasConfiguradas(){
		
		def contenedor = especificacionAccesors{
			
			registrar lista de objetos tipo Persona.class llamada hijos con las especificaciones([
				[edad : 10, nombre : pipo],
				[edad : 12, nombre : juan],
				[edad : 15, nombre : mina]
			])
			
			registrar objeto llamado personita de la clase Persona.class con las dependencias([
				nombre : papa,
				edad : 40,
				hijos : hijos
			])
		}
		
		def persona = (Persona) contenedor.instanciaDe("personita")
		
		assertEquals(40,persona.getEdad())
		assertEquals(3, persona.getHijos().size())
		assertEquals(12, persona.getHijos().get(1).getEdad())
		assertEquals("juan", persona.getHijos().get(1).getNombre())
		
	}
	
	@Test
	void testConstructorContainerCamposPrimitivosConEntornoCopado() {
		
		def contenedor = especificacionConstructor {
			
			Definir entorno actual copado
			
			
			entorno copado
			
				registrar autito de la clase Auto.class con las dependencias ([4,"Fiat 600"])
				
				
			entorno noCopado
				
					registrar autitoNoCopado de la clase Auto.class con las dependencias ([4,"Fiat 400"])
					
					
			entorno comun
			
				registrar autitoComun de la clase Auto.class con las dependencias ([4,"Fiat Uno"])
			
		}
		
		
		
		def auto = (Auto) contenedor.instanciaDe("autito")
		assertEquals 4, auto.getRuedas()
		assertEquals "Fiat 600", auto.getModelo()
		
		auto = (Auto) contenedor.instanciaDe("autitoComun")
		assertEquals 4, auto.getRuedas()
		assertEquals "Fiat Uno", auto.getModelo()
		
		auto = (Auto) contenedor.instanciaDe("autitoNoCopado")
		assertEquals 0, auto.getRuedas()
		assertEquals null, auto.getModelo()
	}
	
	@Test
	void testConstructorContainerCamposPrimitivosConEntornnullComun() {
		
		def contenedor = especificacionConstructor {
						
			
			entorno copado
			
				registrar autito de la clase Auto.class con las dependencias ([4,"Fiat 600"])
				
				
			entorno noCopado
				
					registrar autitoNoCopado de la clase Auto.class con las dependencias ([4,"Fiat 400"])
					
					
			entorno comun
			
				registrar autitoComun de la clase Auto.class con las dependencias ([4,"Fiat Uno"])
			
		}
		
		
		
		def auto = (Auto) contenedor.instanciaDe("autito")
		assertEquals 0, auto.getRuedas()
		assertEquals null, auto.getModelo()
		
		auto = (Auto) contenedor.instanciaDe("autitoComun")
		assertEquals 4, auto.getRuedas()
		assertEquals "Fiat Uno", auto.getModelo()
		
		auto = (Auto) contenedor.instanciaDe("autitoNoCopado")
		assertEquals 0, auto.getRuedas()
		assertEquals null, auto.getModelo()
	}
	
}
