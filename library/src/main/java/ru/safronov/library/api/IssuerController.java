package ru.safronov.library.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import ru.safronov.library.api.mappers.IssueMapper;
import ru.safronov.library.exceptions.TooManyBooksException;
import ru.safronov.library.model.Issue;
import ru.safronov.library.service.IssuerService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/issue")
@Tag(name = "Issue")
public class IssuerController {

  private final IssuerService service;

  @PutMapping(path = "/{issueId}")
  @Operation(summary = "return book", description = "Читатель возвращает книгу (заполняется поле returned_at)")
  public ResponseEntity<IssueDTO> returnBook(@PathVariable long issueId) {
    final IssueDTO issueDTO;
    final Issue issue;
    try {
      issue = service.getIssue(issueId).orElseThrow();
      issueDTO = IssueMapper.mapToIssueDto(issue);
      service.returnBook(issue);
    } catch (NoSuchElementException ex) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    issueDTO.setReturned_at(issue.getReturned_at());
    return new ResponseEntity<>(issueDTO, HttpStatus.OK);
  }

  @PostMapping
  @Operation(summary = "issue book", description = "Составление записи о выдаче книги")
  public ResponseEntity<IssueDTO> issueBook(@RequestBody Issue issue) {
    log.info("Получен запрос на выдачу: readerId = {}, bookId = {}", issue.getReaderId(),
        issue.getBookId());

    final IssueDTO issueDto;
    try {
      issueDto = IssueMapper.mapToIssueDto(service.issue(issue));
    } catch (TooManyBooksException e) {
      log.info(e.getMessage());
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
    return new ResponseEntity<>(issueDto, HttpStatus.OK);
  }

  @GetMapping(path = "/{id}")
  @Operation(summary = "get issue by id", description = "Получение информации о факте выдачи книги по ее идентификатору")
  public ResponseEntity<IssueDTO> getIssueInfo(@PathVariable long id) {
    final IssueDTO issueDTO;
    try {
      issueDTO = IssueMapper.mapToIssueDto(service.getIssue(id).orElseThrow());
    } catch (NoSuchElementException ex) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return new ResponseEntity<>(issueDTO, HttpStatus.OK);
  }
}
