package com.munteanu.processors;

import com.munteanu.models.DemoMessage;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MessageProcessor {

  private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

  // @Body, @Header
  @Handler
  public void process(Exchange exchange) {
    DemoMessage demoMessage = (DemoMessage) exchange.getIn().getBody();
    LOGGER.debug(demoMessage.toString());
    exchange.getIn().setBody(demoMessage.toString());
  }
}
