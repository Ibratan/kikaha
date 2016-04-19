package kikaha.urouting;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class Reflection {

	public static Class<?> getFirstGenericTypeFrom( Object object, Class<?> forInterface ) {
		return getFirstGenericFrom( object.getClass(), forInterface );
	}

	public static Class<?> getFirstGenericFrom( Class<?> clazz, Class<?> forInterface ) {
		final Type[] genericInterfaces = clazz.getGenericInterfaces();
		for ( Type genericInterface : genericInterfaces )
			if ( ParameterizedType.class.isInstance( genericInterface )
					&& forInterface.equals( ( (ParameterizedType)genericInterface ).getRawType() ) )
				return (Class<?>)( (ParameterizedType)genericInterface ).getActualTypeArguments()[0];
		return null;
	}
}
