package DI.implementacion.exceptions;

public class NoSeEncuentraConstructorException extends RuntimeException {

	@Override
	public String getMessage(){
		return "No se encontró un constructor apropiado para las dependencias declaradas";
	}
}
