package ru.gb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import ru.gb.model.User;
import ru.gb.repository.UserRepository;
import ru.gb.security.SecurityConfiguration;

@SpringBootApplication
public class Application {

  // SecurityContext - класс, содержащий данные о сессии (потокобезопасный)
  // (SecurityContextHolder.getContext() - отдаст данные текущего потока)

  // Authentication - объект описывающий текущую авторизацию. Очень абстрактная сущность, возвращает
  // Object, имеет логин, пароль, права.
  // Principle (грубо говоря это login).
  // GrantedAuthority - право на какие-то действия List<GrantedAuthority> roles]
  // В SecurityContextHolder находится мапа, хранящая сессию и данные о ней (cookie с паролем и
  // ролью) - Map<String, SecurityContext> (это можно выключить)


  // UserDetails - интерфейс, описывающий учетную запись (пароль, список привилегий)
  // UserDetailsService - умеет по UserName загружать UserDetails. При хранении User в БД, реализовать
  // класс от UserDetailsService, и реализовать собственное хранение
  // PasswordEncoder - сравнивает введенный пароль и хранимый пароль

  // SecurityFilterChain - цепочка из разных фильтров, проверяющих входящий вызов (каждый фильтр
  // отвечает за свое). Можно сконфигурировать, чтобы была различная обработка запроса. То есть
  // здесь прописываются права доступа для различных ролей.

  // Authorization: Basic base64(username+login) - старая и ненадежная форма авторизации
  // Authorization: Bearer - современный способ, более бзопасный
  // поднять Keycloak в docker:
  // docker run --name keycloak -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:20.0.3 start-dev

  static long id = 1L;

  public static void main(String[] args) {
    UserRepository userRepository = SpringApplication.run(Application.class, args)
        .getBean(UserRepository.class);

    saveUser(userRepository, "admin");
    saveUser(userRepository, "user");
    saveUser(userRepository, "auth");
    saveUser(userRepository, "simple");
  }

  private static void saveUser(UserRepository repository, String login) {
    User user = new User();
    user.setId(id++);
    user.setLogin(login);
    user.setPassword(login);
    user.setRole(login);
    repository.save(user);
  }

}
