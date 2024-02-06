package ru.gb.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RecoverExceptionAspect {

  @Pointcut("@annotation(ru.gb.aspect.RecoverException)")
  public void methodAnnotatedWith() {
  }

  /**
   * Если во время исполнения метода возникает exception, он не прокидывается выше и возвращает
   * null. При этом, если тип исключения входит в список перечисленных в noRecoverFor исключений, то
   * исключение НЕ прерывается и прокидывается выше
   */
  @Around("methodAnnotatedWith()")
  public Object exceptionHandlerAspect(ProceedingJoinPoint joinPoint) throws Throwable {
    Class<? extends RuntimeException>[] exceptions = extractException(joinPoint);
    Object returnObject;
    try {
      returnObject = joinPoint.proceed();
      return returnObject;
    } catch (Throwable ex) {
      if (containsExc(exceptions, ex)) {
        throw ex;
      }
      return null;
    }
  }

  /**
   * Получение аннотации (ее данных) из JoinPoint
   */
  private static Class<? extends RuntimeException>[] extractException(
      ProceedingJoinPoint joinPoint) {
    MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
    RecoverException annotation = methodSignature.getMethod().getAnnotation(RecoverException.class);
    return annotation.noRecoverFor();
  }

  /**
   * Метод проверяет содержится ли Throwable ex в Class<?>[] exceptions
   */
  private static boolean containsExc(Class<?>[] exceptions, Throwable ex) {
    for (Class<?> e : exceptions) {
      if (e.equals(ex.getClass())) {
        return true;
      }
    }
    return false;
  }
}
