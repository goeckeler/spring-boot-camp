package com.goeckeler.bootcamp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.Transactional;

import com.goeckeler.bootcamp.service.boot.Catalog;

@SpringBootApplication
@EnableAspectJAutoProxy
public class Application
{
  @Value("${bootcamp.sampling}")
  private Boolean sampleData;

  @Bean
  @Transactional
  CommandLineRunner init(final Catalog catalog) {
    return (event) -> {
      if (sampleData) {
        catalog.load();
      }
    };
  }

  public static void main(final String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
