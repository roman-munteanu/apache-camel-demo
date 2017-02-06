package com.munteanu.processors;

import com.munteanu.models.DemoMessage;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.springframework.stereotype.Component;

@Component
public class MessageProcessor {

  @Handler
  public void process(Exchange exchange) {
    DemoMessage demoMessage = (DemoMessage) exchange.getIn().getBody();
    System.out.println(demoMessage);
    exchange.getIn().setBody(demoMessage.toString());
  }
}
