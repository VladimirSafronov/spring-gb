package ru.safronov.springbootbaselection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {
  private final Car car;

  @Autowired
  public CarController(Car car) {
    this.car = car;
  }

  @GetMapping("/car")
  public String carStart() {
    car.start();
    return "Авто запущен";
  }
}
