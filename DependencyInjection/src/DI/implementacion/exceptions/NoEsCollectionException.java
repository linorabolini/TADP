package DI.implementacion.exceptions;

public class NoEsCollectionException extends RuntimeException {
	
	@Override
	public String getMessage(){
		return "El parametro tipo Clase pasado no corresponde a una implementación de la interfaz Collection";
	}

}
