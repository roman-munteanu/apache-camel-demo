package com.munteanu.processors;

import com.munteanu.models.DemoMessage;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.apache.camel.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.munteanu.processors.MessageHeadersProcessor.ORIGINAL_MESSAGE;

@Component
public class DemoMessageToCsvProcessor {

  private static final Logger LOGGER = LoggerFactory.getLogger(DemoMessageToCsvProcessor.class);

  @Handler
  public void process(Exchange exchange, @Headers Map<String, Object> headers) {

    DemoMessage demoMessage = (DemoMessage) headers.get(ORIGINAL_MESSAGE);

    StringBuilder csv = new StringBuilder();
    csv.append(demoMessage.getChannel())
      .append(",")
      .append(demoMessage.getText());

    LOGGER.debug(csv.toString());

    exchange.getIn().setBody(csv.toString());
  }
}
