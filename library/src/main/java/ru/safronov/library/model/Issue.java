package ru.safronov.library.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * Запись о факте выдачи книги (в БД)
 */
@Data
@Entity
@Table(name = "issues")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Issue {

  private static long sequence = 1L;

  @Id
  @Column(name = "id")
  private Long id;
  @Column(name = "book_id")
  private Long bookId;
  @Column(name = "reader_id")
  private Long readerId;

  /**
   * Дата выдачи
   */
  @Column(name = "issued_at")
  private LocalDateTime issued_at;
  @Column(name = "returned_at")
  private LocalDateTime returned_at;

  public Issue(Long bookId, Long readerId) {
    this.id = sequence++;
    this.bookId = bookId;
    this.readerId = readerId;
    this.issued_at = LocalDateTime.now();
  }

  public Issue() {
  }
}
