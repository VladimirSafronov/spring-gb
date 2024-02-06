package ru.gb.demo;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
//@Aspect
@Component
public class DemoAspect {

  /**
   * Объявляем Pointcut отдельно от метода. Pointcut - строка с определенным синтаксисом, которая
   * подбирает методы, подходящие под аспект. // Позволяет описать любые варианты.
   */
  @Pointcut("execution(* ru.gb.demo.MyServiceBean.*(..))")
  public void myServiceBeanMethodsPointcut() {
  }

  /**
   * Advice
   * 1. Before - срабатывает до метода
   * 2. AfterReturning - после удачного ззавершения метода
   * 3. AfterThrowing - после метода закончившегося исключением
   * 4. After - после метода (срабатывает
   * независимо, завершился ли метод с Exception или без)
   * 5. Around - до и после метода. Возвращает Object, может использовать ProceedingJoinPoint -
   * вызывает оригинальный метод. Если этого не сделать, то и все вызовы с остальными Advice-ами не
   * отработают
   */

  @Before("myServiceBeanMethodsPointcut()")
  public void before(JoinPoint joinPoint) {
    log.info("before sign = {}, args = {}", joinPoint.getSignature(), joinPoint.getArgs()[0]);
  }

//  @Before("myServiceBeanMethodsPointcut()")
//  private void beforeMyServiceBean(JoinPoint joinPoint) {
//    Signature signature = joinPoint.getSignature();
//    log.info("signature: {}", signature);
//    log.info("Argument name: {}", joinPoint.getArgs()[0]);
//  }
//
//  @Around("myServiceBeanMethodsPointcut()")
//  public Object aroundMyServiceBeanMethodsPointcut(ProceedingJoinPoint joinPoint) {
//    try {
//      Object proceed = joinPoint.proceed();
//      return proceed;
//    } catch (Throwable e) {
//      return "exception was thrown: [" + e.getMessage() + "]";
//    }
//  }
//
//  @AfterReturning(value = "myServiceBeanMethodsPointcut()", returning = "result")
//  public void afterReturningMyServiceBean(JoinPoint joinPoint, Object result) {
//    log.info("Result: {}", result);
//  }
//
//  @AfterThrowing(value = "myServiceBeanMethodsPointcut()", throwing = "e")
//  public void afterThrowingMyServiceBean(Throwable e) {
//    log.error("Exception!!! {} - {}", e.getClass(), e.getMessage());
//  }

}
