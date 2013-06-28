package DI.implementacion.dsl

import DI.implementacion.*

class ContainerEspecificacion {
	
	static def especificacionConstructor(bloque){
		new ConstructorEspecificacion().with bloque
	}
	
	static def especificacionAccesors(bloque){
		new AccesorsPropertiesEspecificacion(container: new AccesorContainer()).with bloque
	}
	
	static def especificacionProperties(bloque){
		new AccesorsPropertiesEspecificacion(container: new PropertyContainer()).with bloque
	}

}


abstract class Especificaciones{
	
	static public def objeto = "nada"
	static public def la = "nada"
	static public def las = "nada"
	
	
	def idUltimoRegistrado
	def componentesRegistrados = []
	def container
	
	def registrar(algo){
		this
	}
	
	def llamado(id){
		idUltimoRegistrado = id
		this
	}
	
	def de(algo){
		this
	}
	
	def clase(Class<?> clase){
		container.registrarComponente(idUltimoRegistrado,clase)
		componentesRegistrados.add(idUltimoRegistrado)
		this
	}
	
	def con(algo){
		this
	}
	
	
	def propertyMissing (String name){
		name
	}
	
	def componenteYaRegistrado(valor){
		componentesRegistrados.any { id -> id.equals(valor)}
	}
	
	def esLista(valor){
		valor instanceof List
	}
	
	def esListaDeDependenciasConfiguradas(lista){
		lista.any {valor -> componenteYaRegistrado(valor) }
	}
		
}

class ConstructorEspecificacion extends Especificaciones{
	
	public ConstructorEspecificacion(){
		container = new ConstructorContainer()
	}
	

	def dependencias(Object... listaDependencias){
		listaDependencias.each { dependencia ->
			
			if (esLista(dependencia)){
				
				if (esListaDeDependenciasConfiguradas(dependencia)){
					container.agregarDependenciaListaConfigurada(idUltimoRegistrado,dependencia.value.class,dependencia.value as String[])
				}
				else{
					container.agregarDependenciaLista(idUltimoRegistrado,dependencia.value.class,dependencia.value as Object[])
				}
				
			}
			else{
			
				if (componenteYaRegistrado(dependencia))
					container.agregarDependenciaConfigurada(idUltimoRegistrado,dependencia)
				else
					container.agregarDependencia(idUltimoRegistrado,dependencia)
			}
		}
		
		container
	}
}


class AccesorsPropertiesEspecificacion extends Especificaciones{
	

	def dependencias(listaDependencias){
		listaDependencias.each { dependencia ->
			
			if (esLista(dependencia.value) ){
				if (esListaDeDependenciasConfiguradas(dependencia.value)){					
					container.agregarDependenciaListaConfigurada(idUltimoRegistrado,dependencia.key,dependencia.value.class,dependencia.value as String[])
				}
				else{
					container.agregarDependenciaLista(idUltimoRegistrado,dependencia.key,dependencia.value.class,dependencia.value as Object[])
				}
			}
			else{
			
				if (componenteYaRegistrado(dependencia.value))
					container.agregarDependenciaConfigurada(idUltimoRegistrado,dependencia.key,dependencia.value)
				else
					container.agregarDependencia(idUltimoRegistrado,dependencia.key,dependencia.value)
			}
		}
		
		container
	}
}

