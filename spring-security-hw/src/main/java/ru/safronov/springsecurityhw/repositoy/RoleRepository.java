package ru.safronov.springsecurityhw.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.safronov.springsecurityhw.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
