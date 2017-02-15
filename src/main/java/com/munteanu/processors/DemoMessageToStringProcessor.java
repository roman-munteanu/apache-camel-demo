package com.munteanu.processors;

import com.munteanu.models.DemoMessage;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoMessageToStringProcessor implements Processor {

  private static final Logger LOGGER = LoggerFactory.getLogger(DemoMessageToStringProcessor.class);

  @Override
  public void process(Exchange exchange) throws Exception {
    DemoMessage demoMessage = (DemoMessage) exchange.getIn().getBody();
    LOGGER.debug(demoMessage.toString());
    exchange.getIn().setBody(demoMessage.toString());
  }
}
