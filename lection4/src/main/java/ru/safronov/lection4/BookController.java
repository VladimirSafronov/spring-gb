package ru.safronov.lection4;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

  private final BookService service;

  @GetMapping
  public List<Book> getAllBooks() {
    return service.getAllBooks();
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<Book> getBookById(@PathVariable Long id) {
    Optional<Book> optional = service.findById(id);
    return optional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public Book createBook(@RequestBody Book book) {
    return service.save(book);
  }

  @PutMapping(path = "/{id}")
  public Book update (@RequestBody Book book, @PathVariable Long id) {
    book.setId(id);
    return service.save(book);
  }

  @DeleteMapping(path = "/{id}")
  public void delete(@PathVariable Long id) {
    service.deleteById(id);
  }

}
