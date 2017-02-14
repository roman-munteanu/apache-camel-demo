package com.munteanu.processors;

import com.munteanu.models.DemoMessage;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class MessageHeadersProcessor implements Processor {

  private static final String ORIGINAL_MESSAGE = "original_message";
  private static final String DEMO_MESSAGE_CHANNEL = "DEMO_MESSAGE_CHANNEL";

  @Override
  public void process(Exchange exchange) throws Exception {

    DemoMessage demoMessage = (DemoMessage) exchange.getIn().getBody();
    exchange.getIn().setHeader(ORIGINAL_MESSAGE, demoMessage);
    exchange.getIn().setHeader(DEMO_MESSAGE_CHANNEL, demoMessage.getChannel());
  }
}
