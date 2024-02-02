package ru.safronov.springsecurityhw.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Класс ответственнен за сравнения пароля, переданного из формы и его сравнения с паролем,
 * хранящемся в системе
 */
@Component
public class CustomPasswordEncoder implements PasswordEncoder {

  @Override
  public String encode(CharSequence rawPassword) {
    // шифруем пароль
    return String.valueOf(rawPassword);
  }

  /**
   * Метод применяет режим шифрования к пришедшему паролю, и сравнивает его с зашифрованным из
   * хранилища
   */
  @Override
  public boolean matches(CharSequence rawPassword, String encodedPassword) {
    return encode(rawPassword).equals(encodedPassword);
  }

}
