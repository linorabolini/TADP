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
	

	def ultimaClaseRegistrada
	def idUltimoRegistrado
	def idUltimaListaRegistrada
	def idEntornoActual = "comun"
	def idUltimoEntorno = "comun"
	def componentesRegistrados = []
	def listasRegistradas = []
	def container
	
	def llamado(id){
		idUltimoRegistrado = id
		this
	}
	
	def nombre(id){
		idUltimoRegistrado = id
		this
	}
	
	def actual(id){
		idEntornoActual = id
		this
	}
	
	def entorno(id){
		idUltimoEntorno = id
		this
	}
	
	def registrar(id){
		idUltimoRegistrado = id
		this
	}
	
	def llamada(id){
		idUltimaListaRegistrada = id
		this
	}

	def tipo (Class<?> clase){
		ultimaClaseRegistrada = clase
		this
	}
	
	def clase(Class<?> clase){
		container.registrarComponente(idUltimoRegistrado,clase)
		componentesRegistrados.add(idUltimoRegistrado)
		this
	}
	
	def propertyMissing (String name){
		name
	}
	
	def methodMissing(String name, args) {
		this
	}
	
	def componenteYaRegistrado(lista,valor){
		lista.any { id -> id.equals(valor)}
	}
	
	def esLista(valor){
		valor instanceof List
	}
	
	def esListaDeDependenciasConfiguradas(lista){
		lista.any {valor -> componenteYaRegistrado(componentesRegistrados,valor) }
	}
	
	def especificaciones(listaDependencias){
		
		listasRegistradas.add(idUltimaListaRegistrada)
		listaDependencias.each { dependencia ->
			
			def index = listaDependencias.indexOf(dependencia)
			idUltimoRegistrado = idUltimaListaRegistrada + index.toString()
			componentesRegistrados.add(idUltimoRegistrado)
			container.registrarComponente(idUltimoRegistrado,ultimaClaseRegistrada)
			this.dependencias(dependencia)
		}
		
		container
	}
	
	abstract def dependenciaValor(dependencia)
	
	abstract def agregarDependenciaListaConfigurada(dependencia)
	
	abstract def agregarDependenciaLista(dependencia)
	
	abstract def agregarDependenciaConfigurada(dependencia)
	
	abstract def agregarDependencia(dependencia)
	
	def dependencias(listaDependencias){
				
		if(idUltimoEntorno != idEntornoActual && idUltimoEntorno != "comun") return
		
		listaDependencias.each { dependencia ->
			
			if (componenteYaRegistrado(listasRegistradas,dependenciaValor(dependencia))){
				
				try{
					dependencia.value = componentesRegistrados.findAll { it.startsWith(dependenciaValor(dependencia))}
				}
				catch (ReadOnlyPropertyException e){
					dependencia = componentesRegistrados.findAll { it.startsWith(dependenciaValor(dependencia))}
				}
				agregarDependenciaListaConfigurada(dependencia)
			}
			else{
				
				if (esLista(dependenciaValor(dependencia))){
					
					if (esListaDeDependenciasConfiguradas(dependenciaValor(dependencia))){
						agregarDependenciaListaConfigurada(dependencia)
						
					}
					else{
						agregarDependenciaLista(dependencia)
					}
					
				}
				else{
				
					if (componenteYaRegistrado(componentesRegistrados, dependenciaValor(dependencia)))
						agregarDependenciaConfigurada(dependencia)
					else
						agregarDependencia(dependencia)
				}
			}
		}
		
		container
	}
		
}

class ConstructorEspecificacion extends Especificaciones{
	
	public ConstructorEspecificacion(){
		container = new ConstructorContainer()
	}
	
	@Override
	def dependenciaValor(dependencia){
		dependencia
	}
	
	@Override
	def agregarDependenciaListaConfigurada(dependencia){
		container.agregarDependenciaListaConfigurada(idUltimoRegistrado,dependencia.class,dependencia as String[])
	}
	
	@Override
	def agregarDependenciaLista(dependencia){
		container.agregarDependenciaLista(idUltimoRegistrado,dependencia.class,dependencia as Object[])
	}
	
	@Override
	def agregarDependenciaConfigurada(dependencia){
		container.agregarDependenciaConfigurada(idUltimoRegistrado,dependencia)
	}
	
	@Override
	def agregarDependencia(dependencia){
		container.agregarDependencia(idUltimoRegistrado,dependencia)
	}
}


class AccesorsPropertiesEspecificacion extends Especificaciones{
	
	@Override
	def dependenciaValor(dependencia){
		dependencia.value
	}
	
	@Override
	def agregarDependenciaListaConfigurada(dependencia){
		container.agregarDependenciaListaConfigurada(idUltimoRegistrado,dependencia.key,dependencia.value.class,dependencia.value as String[])
	}
	
	@Override
	def agregarDependenciaLista(dependencia){
		container.agregarDependenciaLista(idUltimoRegistrado,dependencia.key,dependencia.value.class,dependencia.value as Object[])
	}
	
	@Override
	def agregarDependenciaConfigurada(dependencia){
		container.agregarDependenciaConfigurada(idUltimoRegistrado,dependencia.key,dependencia.value)
	}
	
	@Override
	def agregarDependencia(dependencia){
		container.agregarDependencia(idUltimoRegistrado,dependencia.key,dependencia.value)
	}
}

