package kikaha.core.cdi;

import java.lang.annotation.Annotation;
import java.util.Collection;

/**
 * Object holding data about and Inject object. It is useful when producing
 * object though producer API, allowing to create a new object based on specific
 * context.
 */
public interface ProviderContext {

	/**
	 * The list of annotations present on the target.
	 *
	 * @return
	 */
	<A extends Annotation> A getAnnotation( Class<A> anntationClass );

	/**
	 * The list of annotations present on the target that should be used as
	 * filter to define which service should be injected.
	 *
	 * @return
	 */
	Collection<Class<? extends Annotation>> qualifierAnnotations();

	/**
	 * The type is expected to generate an object.
	 *
	 * @return
	 */
	Class<?> targetType();

	/**
	 * Retrieve an attribute ( identified by {@code key} ), from current
	 * context. Returns {@code null} if no object associated to the Inject
	 * {@code key} was found.
	 *
	 * @param key
	 * @return
	 */
	Object attribute( Object key );

	/**
	 * Retrieve an attribute ( identified by {@code key} ), from current
	 * context. Returns {@code null} if no object associated to the Inject
	 * {@code key} was found.
	 *
	 * @param key
	 * @param <T>
	 * @return
	 */
	<T> T attribute( Class<T> key );
}
