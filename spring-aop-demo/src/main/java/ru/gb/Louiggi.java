package ru.gb;

import org.slf4j.event.Level;
import org.springframework.stereotype.Component;
import ru.gb.aspect.Loggable;
import ru.gb.aspect.RecoverException;
import ru.gb.aspect.Timer;

@Component
public class Louiggi implements Brother {

  @Loggable(level = Level.WARN)
  public void method1(String arg1, int arg2) {

  }

  @Loggable(level = Level.WARN)
  @RecoverException(noRecoverFor = RuntimeException.class)
  public String method2() {
    fail();
    return "value";
  }

  @Timer
  public String method3() {
    throw new RuntimeException("runtimeexceptionmsg");
  }

  private static void fail() {
    throw new RuntimeException();
  }

}
