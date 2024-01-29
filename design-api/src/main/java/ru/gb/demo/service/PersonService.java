package ru.gb.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.demo.model.Person;
import ru.gb.demo.repository.PersonRepository;

@Service
@RequiredArgsConstructor
public class PersonService {

  private final PersonRepository repository;

  /**
   * Метод иллюстрирует работу транзакции (либо вступают в силу все изменения, либо изменения
   * откатываются до начала транзакции)
   *
   * @Transactional имеет настройки propagation (например, должна ли уже иметься какая-то
   * транзакция) и isolation (уровень чтеения данных, например, грязное чтение) и прочее
   */
  @Transactional
  public void updatePerson(Long id, String newName, Integer newAge) {
    // transaction[balance-- () balance++]

    Person person = repository.findById(id)
        .orElseThrow();

    person.setName(newName);
    repository.save(person);

    fail();

    person.setAge(newAge);
    repository.save(person);
  }

  private void fail() {
    throw new RuntimeException("fail");
  }

}
