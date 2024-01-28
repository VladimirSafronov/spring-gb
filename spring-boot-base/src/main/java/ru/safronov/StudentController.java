package ru.safronov;

import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
 * 3.1 GET /student/{id} - получить студента по ID
 * 3.2 GET /student - получить всех студентов
 * 3.3 GET /student/search?name='studentName' - получить список студентов, чье имя содержит подстроку studentName
 * 3.4 GET /group/{groupName}/student - получить всех студентов группы
 * 3.5 POST /student - создать студента (принимает JSON) (отладиться можно с помощью Postman)
 * 3.6 DELETE /student/{id} - удалить студента
 */

/**
 * Класс контролллер
 */
@RestController
public class StudentController {

  private final StudentRepository repository;

  public StudentController(StudentRepository repository) {
    this.repository = repository;
  }

  @GetMapping(path = "/student/{id}")
  public Student getStudentById(@PathVariable long id) {
    return repository.getById(id);
  }

  @GetMapping(path = "/student")
  public List<Student> getAllStudents() {
    return repository.getStudents();
  }

  @GetMapping(path = "/student/search")
  public List<Student> getStudentByName(@RequestParam("name") String studentName) {
    return repository.getStudentsByName(studentName);
  }

  @GetMapping(path = "/group/{groupName}/student")
  public List<Student> getStudentsByGroupName(@PathVariable String groupName) {
    return repository.getStudentsByGroupName(groupName);
  }

  @PostMapping(path = "/student", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Student addStudent(@RequestBody Student student) {
    return repository.addNewStudent(student);
  }

  @DeleteMapping(path = "/student/{id}")
  public List<Student> deleteStudent(@PathVariable long id) {
    repository.deleteStudent(id);
    return repository.getStudents();
  }
}
