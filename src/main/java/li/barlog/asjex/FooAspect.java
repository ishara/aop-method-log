package li.barlog.asjex;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.StringJoiner;

@Aspect
public class FooAspect {
	@Pointcut("execution(* *(..))")
	public static void anyMethod() { }

	@Pointcut("execution(* li.barlog.asjex.Foo.foo(..))")
	public void methodFooFromTypeFoo() {}
//
	@Before("methodFooFromTypeFoo()")
	public void before(JoinPoint joinPoint) {
		System.out.println("before " + joinPoint.toLongString());
	}

	@After("anyMethod() && @annotation(systemChangeLogable)")
	public void logBeforeEntries(SystemChangeLogable systemChangeLogable, JoinPoint joinPoint) {
		String fielsLog  ="";
		if (systemChangeLogable.fieldsToLog().length>0) {
			final Class<?> aClass = joinPoint.getTarget().getClass();
			StringJoiner stringJoiner =new StringJoiner(", ", "[", "]");
			for(String fiels :systemChangeLogable.fieldsToLog())
			{
				try {
					Object o =	aClass.getDeclaredField(fiels).get(joinPoint.getTarget());
					stringJoiner.add(fiels+"="+o);
				} catch (IllegalAccessException | NoSuchFieldException e) {
					e.printStackTrace();
				}
			}
			fielsLog =stringJoiner.toString();
		}
		else if(systemChangeLogable.toStringLog())
		{
			fielsLog = joinPoint.getTarget().toString();
		}
		System.out.println(" after - " +systemChangeLogable.name()+"  - "+ fielsLog);

	}
}
