package ru.safronov.library.api;

import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.safronov.library.exceptions.TooManyBooksException;
import ru.safronov.library.model.Issue;
import ru.safronov.library.service.IssuerService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/issue")
public class IssuerController {

  private final IssuerService service;

  @PutMapping(path = "/{issueId}")
  public ResponseEntity<Issue> returnBook(@PathVariable long issueId) {
    // найти в репозитории выдачу и проставить ей returned_at
    final Issue issue;
    try {
      issue = service.getIssue(issueId);
      service.returnBook(issue);
    } catch (NoSuchElementException ex) {
      return ResponseEntity.notFound().build();
    }
    return new ResponseEntity<>(issue, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Issue> issueBook(@RequestBody IssueRequest request) {
    log.info("Получен запрос на выдачу: readerId = {}, bookId = {}", request.getReaderId(),
        request.getBookId());

    final Issue issue;
    try {
      issue = service.issue(request);
    } catch (TooManyBooksException e) {
      log.info(e.getMessage());
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
    return new ResponseEntity<>(issue, HttpStatus.OK);
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<Issue> getIssueInfo(@PathVariable long id) {
    return new ResponseEntity<>(service.getIssue(id), HttpStatus.OK);
  }
}
