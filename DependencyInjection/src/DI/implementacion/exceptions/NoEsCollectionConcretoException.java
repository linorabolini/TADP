package DI.implementacion.exceptions;

public class NoEsCollectionConcretoException extends RuntimeException {
	
	@Override
	public String getMessage(){
		return "El parametro tipo Clase pasado no corresponde a una implementaci�n de la interfaz Collection";
	}

}
