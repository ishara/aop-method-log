package li.barlog.asjex;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SystemChangeLogable {

	String prefix() default "SYSTEM_CHANGE";
	String name();
	String message() default "";
	String[] fieldsToLog() default {};
	boolean toStringLog() default false;


}
