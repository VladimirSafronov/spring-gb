package ru.gb.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class TimerAspect {

  /**
   * within пишется под тип
   */
  @Pointcut("within(@ru.gb.aspect.Timer *)")
  public void beansAnnotatedWith() {
  }

  /**
   * annotation пишется под метод
   */
  @Pointcut("@annotation(ru.gb.aspect.Timer)")
  public void methodAnnotatedWith() {
  }

  @Around("beansAnnotatedWith() || methodAnnotatedWith()")
  public Object timerAspect(ProceedingJoinPoint joinPoint) {
    final long start = System.nanoTime();
    Object returnValue = null;
    try {
      returnValue = joinPoint.proceed();
    } catch (Throwable ex) {
      log.info("Exception in timerAspect() {}", ex.getMessage());
    }
    final long finish = System.nanoTime();
    log.info(joinPoint.getClass().getName() + " - " + joinPoint.getSignature().getName() + " #{}",
        (finish - start) / 1_000_000_000);
    return returnValue;
  }
}
