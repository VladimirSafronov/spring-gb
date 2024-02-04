package ru.safronov.springsecurityhw.security;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.safronov.springsecurityhw.model.User;
import ru.safronov.springsecurityhw.repositoy.RoleRepository;
import ru.safronov.springsecurityhw.repositoy.UserRepository;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  /**
   * Метод принимает Entity User из хранилища (если его нет в БД, то пробрасывается спринговый
   * UsernameNotFoundException), преобразует его в спринговый класс
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByLogin(username).
        orElseThrow(() -> new UsernameNotFoundException(username + " not found!"));

    return new org.springframework.security.core.userdetails.User(user.getLogin(),
        user.getPassword(), user.getRoles());
  }
}
