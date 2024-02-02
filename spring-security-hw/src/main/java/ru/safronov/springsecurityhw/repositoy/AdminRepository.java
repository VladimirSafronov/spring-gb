package ru.safronov.springsecurityhw.repositoy;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.safronov.springsecurityhw.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

  Optional<Admin> findByLogin(String login);
}
