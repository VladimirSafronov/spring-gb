package ru.safronov.library.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "readers")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Reader {

  private static long sequence = 1L;
  @Id
  @Column(name = "id")
  private Long id;
  @Column(name = "name")
  private String name;
  /**
   * Количество книг у пользователя
   */
  @Column(name = "books_count")
  private int booksCount;

  public Reader(String name) {
    this.id = sequence++;
    this.name = name;
  }

  public Reader() {
  }
}
