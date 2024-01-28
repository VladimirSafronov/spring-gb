package ru.safronov.library.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "books")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Book {

  private static long sequence = 1L;

  @Id
  @Column(name = "id")
  private Long id;
  @Column(name = "name")
  private String name;

  public Book(String name) {
    this.id = sequence++;
    this.name = name;
  }

  public Book() {
  }
}
