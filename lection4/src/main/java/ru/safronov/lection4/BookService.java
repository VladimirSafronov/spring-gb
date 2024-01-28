package ru.safronov.lection4;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

  private final BookRepository bookRepository;

  public List<Book> getAllBooks() {
    return bookRepository.findAll();
  }

  public Optional<Book> findById(Long id) {
    return bookRepository.findById(id);
  }

  public Book save(Book book) {
    return bookRepository.save(book);
  }

  public void deleteById(Long id) {
    bookRepository.deleteById(id);
  }
}
