package ca.ibu.api.api.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

// java
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

// javax
import javax.ws.rs.NameBinding;

// ibu
import ca.ibu.api.api.Enum.RoleEnum;



@Documented
@NameBinding
@Retention(RUNTIME)
@Target({ TYPE, METHOD })
public @interface Secured {
    RoleEnum[] value() default {};
}
