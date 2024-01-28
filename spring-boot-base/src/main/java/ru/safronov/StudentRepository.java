package ru.safronov;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 * Класс-хранилище
 */
@Component
public class StudentRepository {

  List<Student> students;

  public StudentRepository() {
    this.students = new ArrayList<>();
    students.add(new Student("Olga Ivanova", "geography"));
    students.add(new Student("Nastia Petrova", "IT"));
    students.add(new Student("Elena Ivanova", "physical_education"));
    students.add(new Student("Egor Petrov", "geography"));
    students.add(new Student("Makar Sidorov", "IT"));
    students.add(new Student("Bobby Stivenson", "physical_education"));
  }

  /**
   * Метод получает всех студентов (копию)
   */
  public List<Student> getStudents() {
    return List.copyOf(students);
  }

  /**
   * Метод получает студента по его id
   */
  public Student getById(long id) {
    return students.stream()
        .filter(it -> Objects.equals(it.getId(), id))
        .findFirst()
        .orElse(null);
  }

  /**
   * Метод получает студентов, чье имя содержит подстроку studentName
   */
  public List<Student> getStudentsByName(String studentName) {
    return students.stream()
        .filter(it -> it.getName().contains(studentName))
        .collect(Collectors.toList());
  }

  /**
   * Метод получает всех студентов группы
   */
  public List<Student> getStudentsByGroupName(String groupName) {
    return students.stream()
        .filter(it -> Objects.equals(it.getGroupName(), groupName))
        .collect(Collectors.toList());
  }

  /**
   * Метод добавляет нового студента
   */
  public Student addNewStudent(
      Student student) {
    students.add(student);
    return student;
  }

  /**
   * Метод удаляет студента
   */
  public void deleteStudent(long id) {
    Student removeStudent = getById(id);
    if (removeStudent == null) {
      //по правильному нужно возвращать 404
      throw new IllegalStateException();
    }
    students.remove(removeStudent);
  }
}
