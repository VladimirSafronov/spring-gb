package com.gb;

import lombok.Data;
import org.slf4j.event.Level;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Класс-конфигурация. Нужен для того, чтобы стартер был гибким (его можно было настраивать из
 * клиента).
 */
@Data // getter + setter
@ConfigurationProperties("http.logging")
public class LoggingProperties {

  /**
   * Включить\выключить логиирование тела запроса
   */
  private boolean logBody = false;

  /**
   * Уровень логирования
   */
  private Level logLevel = Level.DEBUG;

}
