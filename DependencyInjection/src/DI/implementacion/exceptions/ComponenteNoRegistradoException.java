package DI.implementacion.exceptions;

public class ComponenteNoRegistradoException extends RuntimeException {

	@Override
	public String getMessage(){
		return "No se encontr� el componente especificado en el id";
	}
	
}
