package DI.implementacion.exceptions;

public class NoSeEncuentraPropertyException extends RuntimeException {
	
	@Override
	public String getMessage(){
		return "No se encontr� el campo dentro de la clase del componente pedido";
	}

}
