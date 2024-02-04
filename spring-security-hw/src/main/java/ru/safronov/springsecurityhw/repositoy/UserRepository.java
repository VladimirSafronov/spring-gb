package ru.safronov.springsecurityhw.repositoy;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.safronov.springsecurityhw.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByLogin(String login);
}
