package co.gv.aspects;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import co.gv.dao.DAOException;

@Aspect
@Component
public class MyCustomAspects {

//	@Before("execution(* co.gv.dao.ProductDAO.count(..))")
	@Before("execution(* co.gv.dao.ProductDAO.*(..))")
	// aspectj pointcut expression syntax
	// execution(modifiers-pattern? return-type-pattern declaring-type-pattern?
	// name-pattern(param-name) throws-pattern?)
	public void logBefore(JoinPoint jp) {
		System.out.println("before executing " + jp.getSignature().getName());
		System.out.println("arguments are " + Arrays.toString(jp.getArgs()));
	}

	@AfterThrowing(throwing = "t", pointcut = "execution(* co.gv..*DAO.*(..))")
	public void convertToDAOException(Throwable t) throws DAOException {
		throw new DAOException(t);
	}

	@Around("execution(* co.gv.dao.ProductDAO.get*(Double, Double))")
	// ProceedingJoinPoint must be used with @Around annotation only and wont work
	// for others.
	public Object swapInputs(ProceedingJoinPoint pjp) throws Throwable {
		Object[] args = pjp.getArgs();
		Double min = (Double) args[0];
		Double max = (Double) args[1];
		if (min > max) {
			args = new Object[] { max, min };
		}
		return pjp.proceed(args);
	}
}
