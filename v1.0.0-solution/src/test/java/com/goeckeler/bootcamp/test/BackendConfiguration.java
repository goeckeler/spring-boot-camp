package com.goeckeler.bootcamp.test;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
@ComponentScan(basePackageClasses = {
  com.goeckeler.bootcamp.domain.DomainPackage.class, com.goeckeler.bootcamp.service.ServicePackage.class,
  com.goeckeler.bootcamp.aspect.AspectPackage.class
})
@EnableAutoConfiguration
@EnableJpaRepositories(basePackageClasses = { com.goeckeler.bootcamp.domain.DomainPackage.class
})
@EntityScan(basePackageClasses = { com.goeckeler.bootcamp.domain.DomainPackage.class
})
@EnableAspectJAutoProxy
public class BackendConfiguration
{
  @Bean
  public DataSource dataSource() {
    return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).ignoreFailedDrops(true).build();
  }
}
