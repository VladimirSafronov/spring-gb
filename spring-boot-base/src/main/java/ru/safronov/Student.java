package ru.safronov;

import lombok.Data;

@Data
public class Student {

  private long id;
  private String name;
  private String groupName;
  private static long counter = 1;

  public Student(String name, String groupName) {
    this.id = counter++;
    this.name = name;
    this.groupName = groupName;
  }
}
