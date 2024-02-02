package ru.safronov.springsecurityhw.security;

import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.safronov.springsecurityhw.helpers.Util;
import ru.safronov.springsecurityhw.model.User;
import ru.safronov.springsecurityhw.repositoy.UserRepository;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @PostConstruct
  public void generateData() {
    User user = new User();
    user.setId(Util.ID++);
    user.setLogin("user");
    user.setPassword("user");
    user.setRole("user");
    userRepository.save(user);
  }

  /**
   * Метод принимает Entity User из хранилища (если его нет в БД, то пробрасывается спринговый
   * UsernameNotFoundException), преобразует его в спринговый класс
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByLogin(username).
        orElseThrow(() -> new UsernameNotFoundException(username));

    return new org.springframework.security.core.userdetails.User(user.getLogin(),
        user.getPassword(), List.of(new SimpleGrantedAuthority(user.getRole())));
  }
}
