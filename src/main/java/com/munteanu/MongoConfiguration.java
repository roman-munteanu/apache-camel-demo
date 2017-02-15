package com.munteanu;

import com.mongodb.MongoClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@ConfigurationProperties(prefix = "mongo")
public class MongoConfiguration {

  public String host = "localhost";
  public Integer port = 27017;
  public String database = "camel";

  @Bean
  public MongoClient mongoBean() {
    return new MongoClient(host, port);
  }

  @Bean
  public MongoTemplate mongoTemplate() {
    return new MongoTemplate(mongoBean(), database);
  }
}
