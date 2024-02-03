package ru.safronov.springsecurityhw.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Data
@Table(name = "roles")
public class Role implements GrantedAuthority {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * формат 'USER_ROLE'
   */
  @Column(name = "name")
  private String name;

  @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Set<User> users = new HashSet<>();

  public Role() {
  }

  public Role(String name) {
    this.name = convertName(name);
  }

  @Override
  public String getAuthority() {
    return name;
  }

  public static String convertName(String str) {
    return switch (str) {
      case "user" -> "USER_ROLE";
      case "admin" -> "ADMIN_ROLE";
      case "auth" -> "AUTH_ROLE";
      default -> "NOT_AUTH_ROLE";
    };
  }
}
