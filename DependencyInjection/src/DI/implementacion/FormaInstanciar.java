package DI.implementacion;

import java.util.HashMap;
import java.util.Map;

public abstract class FormaInstanciar {
	
	protected Map<Class<?>,Class<?>> clasesPrimitivas = new HashMap<Class<?>,Class<?>>();
	
	public abstract Object dameInstancia(Componente componente);
	
	public abstract void agregarDependencia (String id, Instanciable instanciable);
	
	protected void inicializarClasesPrimitivas(){
		clasesPrimitivas.put(Integer.class, int.class );
		clasesPrimitivas.put(Long.class, long.class);
		clasesPrimitivas.put(Double.class, double.class);
		clasesPrimitivas.put( Float.class, float.class);
		clasesPrimitivas.put(Boolean.class, boolean.class);
		clasesPrimitivas.put(Character.class, char.class);
		clasesPrimitivas.put(Byte.class, byte.class );
		clasesPrimitivas.put(Void.class, void.class);
		clasesPrimitivas.put(Short.class, short.class);
	}

}
