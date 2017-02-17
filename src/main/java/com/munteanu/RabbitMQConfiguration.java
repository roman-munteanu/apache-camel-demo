package com.munteanu;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfiguration {

  public static final String DEMO_MESSAGES_QUEUE = "munteanu.demo.messages.queue";

  public static final String SEND_DEMO_MESSAGE_ROUTING_KEY = "munteanu.demo.message.send.routing.key";

  public static final String TOPIC_EXCHANGE = "munteanu.demo.topic.exchange";
  public static final String DIRECT_EXCHANGE = "munteanu.demo.direct.exchange";

  @Value("${rabbitmq.addresses}")
  private String addresses;

  @Value("${rabbitmq.username}")
  private String username;

  @Value("${rabbitmq.password}")
  private String password;

  @Value("${rabbitmq.virtualHost}")
  private String virtualHost;

  @Value("${rabbitmq.useSsl}")
  private boolean useSsl;

  @Value("${rabbitmq.maxConcurrentConsumers}")
  private int maxConcurrentConsumers;

  @Value("${rabbitmq.messageTtl}")
  private Long messageTtl;


  @Bean
  public Jackson2JsonMessageConverter jsonMessageConverter() {
    Jackson2JsonMessageConverter messageConverter = new Jackson2JsonMessageConverter();
//    messageConverter.setJsonObjectMapper(new ApiObjectMapper());
    return messageConverter;
  }

  @Bean
  public CachingConnectionFactory connectionFactory() {
    CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
    connectionFactory.setAddresses(addresses);
    connectionFactory.setUsername(username);
    connectionFactory.setPassword(password);
    connectionFactory.setVirtualHost(virtualHost);
    connectionFactory.setChannelCacheSize(maxConcurrentConsumers);
    return connectionFactory;
  }

  @Bean
  public AmqpAdmin amqpAdmin(CachingConnectionFactory connectionFactory) {
    return new RabbitAdmin(connectionFactory);
  }

  @Bean
  public TopicExchange topicExchange() {
    return new TopicExchange(TOPIC_EXCHANGE, false, true);
  }

  @Bean
  public DirectExchange directExchange() {
    return new DirectExchange(DIRECT_EXCHANGE, false, true);
  }

  @Bean
  public AmqpTemplate topicExchangeAmqpTemplate(ConnectionFactory connectionFactory, TopicExchange topicExchange, Jackson2JsonMessageConverter jsonMessageConverter) {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setExchange(topicExchange.getName());
    rabbitTemplate.setMessageConverter(jsonMessageConverter);
    return rabbitTemplate;
  }

  @Bean
  public AmqpTemplate directExchangeAmqpTemplate(ConnectionFactory connectionFactory, DirectExchange directExchange, Jackson2JsonMessageConverter jsonMessageConverter) {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setExchange(directExchange.getName());
    rabbitTemplate.setMessageConverter(jsonMessageConverter);
    return rabbitTemplate;
  }

  @Bean
  public Queue demoMessagesQueue() {
    Map<String, Object> arguments = new HashMap<>();
    arguments.put("x-message-ttl", messageTtl);
    return new Queue(DEMO_MESSAGES_QUEUE, true, false, false, arguments);
  }

  @Bean
  public Binding demoMessagesQueueBinding(Queue demoMessagesQueue, DirectExchange directExchange) {
    return BindingBuilder.bind(demoMessagesQueue).to(directExchange).with(SEND_DEMO_MESSAGE_ROUTING_KEY);
  }
}
