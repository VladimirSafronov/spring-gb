package ru.safronov.springsecurityhw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.safronov.springsecurityhw.model.Role;
import ru.safronov.springsecurityhw.model.User;
import ru.safronov.springsecurityhw.repositoy.UserRepository;

@SpringBootApplication
public class SpringSecurityHwApplication {

  static Long id = 1L;

  public static void main(String[] args) {
    UserRepository userRepository = SpringApplication.run(SpringSecurityHwApplication.class, args)
        .getBean(UserRepository.class);

    saveUser(userRepository, "admin");
    saveUser(userRepository, "user");
    saveUser(userRepository, "auth");
  }

  private static void saveUser(UserRepository repository, String login) {
    User user = new User();
    user.setId(id++);
    user.setLogin(login);
    user.setPassword(login);
    user.addRole(new Role(login));
    repository.save(user);
  }

}
