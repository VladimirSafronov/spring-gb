package ru.gb.demo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * Класс считывающий property из конфигурационного файла (application.yml). На его основе будет
 * создан бин, имеющий значения, считанные из файла.
 * @EnableConfigurationProperties(ReaderProperties.class) - проставить над использующим классом
 */
@Data
@ConfigurationProperties("application.reader")
public class ReaderProperties {

  /**
   * поля должны иметь геттер и сеттер
   */
  private Integer maxAllowedBook;

  private Map<String, String> tags;

}
