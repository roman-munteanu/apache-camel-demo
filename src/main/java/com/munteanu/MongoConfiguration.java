package com.munteanu;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@ConfigurationProperties(prefix = "mongo")
public class MongoConfiguration {

  @Value("${mongo.host}")
  public String host;

  @Value("${mongo.port}")
  public Integer port;

  @Value("${mongo.database}")
  public String database;

  @Bean
  public MongoClient mongoBean() {
    return new MongoClient(host, port);
  }

  @Bean
  public MongoTemplate mongoTemplate() {
    return new MongoTemplate(mongoBean(), database);
  }
}
