package com.munteanu.rabbit;

import com.munteanu.models.DemoMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.munteanu.RabbitMQConfiguration.SEND_DEMO_MESSAGE_ROUTING_KEY;

@Component
public class DemoMessageProducer {

  private static final Logger LOGGER = LoggerFactory.getLogger(DemoMessageProducer.class);

  @Autowired
  private AmqpTemplate directExchangeAmqpTemplate;

  public void send(DemoMessage demoMessage) {
    LOGGER.debug("Sending demo message: " + demoMessage.toString() + " with routing key: " + SEND_DEMO_MESSAGE_ROUTING_KEY);
    directExchangeAmqpTemplate.convertAndSend(SEND_DEMO_MESSAGE_ROUTING_KEY, demoMessage);
  }
}
