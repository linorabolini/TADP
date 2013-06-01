package DI.implementacion.exceptions;

public class ErrorInstanciacionException extends RuntimeException {
	
	Throwable cause;
	
	public ErrorInstanciacionException(Throwable e){
		this.cause = e;
	}
	
	@Override 
	public Throwable getCause(){
		return this.cause;
	}

}
