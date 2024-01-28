package ru.safronov.springbootbaselection;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Car {
//  /**
//   * Внедрение зависимости через поле
//   */
//  @Autowired
  private final Engine engine;

  /**
   * Внедрение зависимости через конструктор (предпочтительный способ)
   */
  @Autowired
  public Car(Engine engine) {
    this.engine = engine;
    engine.go();
  }

  public void start() {
    engine.go();
  }

//  public Car() {
//  }

//  /**
//   * Внедрение зависимости через сеттер
//   */
//  public void setEngine(Engine engine) {
//    this.engine = engine;
//    engine.go();
//  }
}
