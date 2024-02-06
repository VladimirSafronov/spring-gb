package ru.gb;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AspectRunner {

  /**
   * Заинжектит все бины типа Brother (но если есть бин типа List, то заинжектит его)
   */
  private final List<Brother> brothers;

  @EventListener(ApplicationReadyEvent.class)
  public void onApplicationReady() {
    System.out.println(brothers);
    for (Brother brother : brothers) {
      try {
        brother.method1("1", 2);
        brother.method2();
        brother.method3();
      } catch (Throwable e) {
        log.error("was exc");
      }
    }
  }

}
