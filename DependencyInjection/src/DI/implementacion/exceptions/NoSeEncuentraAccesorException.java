package DI.implementacion.exceptions;

public class NoSeEncuentraAccesorException extends RuntimeException {
	
	@Override
	public String getMessage(){
		return "No se encontró el accesor dentro de la clase del componente pedido";
	}

}
