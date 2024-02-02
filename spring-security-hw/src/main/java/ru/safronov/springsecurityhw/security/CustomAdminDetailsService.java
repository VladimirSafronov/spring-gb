package ru.safronov.springsecurityhw.security;

import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.safronov.springsecurityhw.helpers.Util;
import ru.safronov.springsecurityhw.model.Admin;
import ru.safronov.springsecurityhw.repositoy.AdminRepository;

@Component
@RequiredArgsConstructor
public class CustomAdminDetailsService implements UserDetailsService {

  private final AdminRepository adminRepository;

  @PostConstruct
  public void generateData() {
    Admin admin = new Admin();
    admin.setId(Util.ID++);
    admin.setLogin("admin");
    admin.setPassword("admin");
    admin.setRole("admin");
    adminRepository.save(admin);
  }

  /**
   * Метод принимает Entity Admin из хранилища (если его нет в БД, то пробрасывается спринговый
   * UsernameNotFoundException), преобразует его в спринговый класс
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Admin admin = adminRepository.findByLogin(username)
        .orElseThrow(
            () -> new UsernameNotFoundException("Admin with name " + username + " didn't find"));

    return new User(admin.getLogin(), admin.getPassword(), List.of(new SimpleGrantedAuthority(
        admin.getRole())));
  }
}
