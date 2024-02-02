package ru.safronov.springsecurityhw.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Класс-контроллер. Содержит в себе контроллеры, вызываемые в зависимости от роли пользователя
 */
@RestController
@RequestMapping("/api/resource")
public class ResourceController {

  @GetMapping
  public String resource() {
    return "Resource";
  }

  @GetMapping("/auth")
  public String auth() {
    return "Authorized";
  }

  @GetMapping("/user")
  public String user() {
    return "User";
  }

  @GetMapping("/admin")
  public String admin() {
    return "Admin";
  }

}
