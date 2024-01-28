package ru.safronov.library.api;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.safronov.library.model.Issue;
import ru.safronov.library.model.Reader;
import ru.safronov.library.service.ReaderService;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/reader")
public class ReaderController {

  private final ReaderService service;

  @GetMapping(path = "/{id}")
  public ResponseEntity<Reader> getReaderById(@PathVariable long id) {
    return new ResponseEntity<>(service.getReader(id), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Reader> createReader(@RequestParam String name) {
    return new ResponseEntity<>(service.createReader(name), HttpStatus.CREATED);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<List<Reader>> deleteReader(@PathVariable long id) {
    return new ResponseEntity<>(service.deleteReader(id), HttpStatus.OK);
  }

  @GetMapping(path = "/{id}/issue")
  public ResponseEntity<List<Issue>> getReaderIssueList(@PathVariable long id) {
    return new ResponseEntity<>(service.getIssueList(id), HttpStatus.OK);
  }
}
