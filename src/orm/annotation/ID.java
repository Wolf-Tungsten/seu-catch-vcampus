package orm.annotation;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ID
{
	String name();

//	String type() default "String";
//
//	int length() default 20;
//
//	int increment() default 1;
}
