package kikaha.urouting.api;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines which content type a {@link Serializer}
 * or an {@link Unserializer} will handle.
 */
@Documented
@Target( ElementType.METHOD )
@Retention( RetentionPolicy.RUNTIME )
public @interface ContentType {
	String value();
}
