package DI.implementacion.exceptions;

public class NoSeEncuentraConstructorException extends RuntimeException {

	@Override
	public String getMessage(){
		return "No se encontr� un constructor apropiado para las dependencias declaradas";
	}
}
