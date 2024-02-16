package com.gb;

import com.gb.http.logging.LoggingFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Класс-конфигурация бина стартера. Нужен для того, чтобы Spring нашел бин стартера независимо от
 * названия пакетов. Чтобы Spring нашел данную конфигурацию, нужно прописать путь к классу в файле
 * resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
 * (начиная с версии 2.7)
 * Не забыть про dependency - spring-boot-configuration-processor
 */
@Configuration
//подключение класса конфигурации
@EnableConfigurationProperties(LoggingProperties.class)
//если в приложении имеется настройка 'http.logging.enabled' со значением true, данная конфигурация
// сработает. Можно применять к бину
@ConditionalOnProperty(value = "http.logging.enabled", havingValue = "true")
public class HttpLoggingAutoConfiguration {

  @Bean
  LoggingFilter loggingFilter(LoggingProperties properties) {
    return new LoggingFilter(properties);
  }

}
