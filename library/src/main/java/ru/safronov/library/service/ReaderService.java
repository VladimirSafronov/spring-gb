package ru.safronov.library.service;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.safronov.library.model.Issue;
import ru.safronov.library.model.Reader;
import ru.safronov.library.repository.BookRepository;
import ru.safronov.library.repository.IssueRepository;
import ru.safronov.library.repository.ReaderRepository;
import ru.safronov.library.repository.mappers.IssueJpaMapper;
import ru.safronov.library.repository.mappers.ReaderJpaMapper;

@Service
@RequiredArgsConstructor
public class ReaderService {

  private final ReaderRepository readerRepository;
  private final BookRepository bookRepository;
  private final IssueRepository issueRepository;

  /**
   * Аннотация @PostConstruct позволяет произвести какие-то действия после создания бина
   */
  @PostConstruct
  public void generateData() {
    createReader("Игорь");
    createReader("Федор");
    createReader("Ольга");
  }

  public Optional<Reader> getReader(long id) {
    return Optional.of(ReaderJpaMapper.mapToReader(readerRepository.findById(id).orElseThrow()));
  }

  public Reader createReader(String name) {
    Reader reader = new Reader(name);
    readerRepository.save(ReaderJpaMapper.mapToReaderEntity(reader));
    return reader;
  }

  public List<Reader> deleteReader(long id) {
    readerRepository.delete(readerRepository.findById(id).orElseThrow());
    return ReaderJpaMapper.mapToReaderList(readerRepository.findAll());
  }

  public Optional<List<Issue>> getIssueList(long id) {
    return Optional.of(
        IssueJpaMapper.mapToIssueList(issueRepository.findAllById(Collections.singleton(id))));
  }

  /**
   * Метод создает и возвращает список книг, которые у читателя на руках
   */
  public List<String> getReaderBooks(Reader reader) {
    List<String> books = new ArrayList<>();
    for (Issue issue : getIssueList(reader.getId()).orElseThrow()) {
      if (issue.getReturned_at() == null) {
        books.add(bookRepository.findById(issue.getBookId()).orElseThrow().getName());
      }
    }
    return books;
  }

  public List<Reader> getAllReaders() {
    return ReaderJpaMapper.mapToReaderList(readerRepository.findAll());
  }
}
