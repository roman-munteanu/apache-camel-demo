package com.munteanu.routes;

import com.munteanu.processors.MessageProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageRouter extends RouteBuilder {

  public static final String SEND_MESSAGE_ROUTE = "direct:send-message";

  @Autowired
  private MessageProcessor messageProcessor;

  @Override
  public void configure() throws Exception {
    from(SEND_MESSAGE_ROUTE).routeId("SEND_MESSAGE_ROUTE")
      .bean(messageProcessor)
//        .transform(body().toString())
        .to("file:output/?fileName=messages_report.txt&fileExist=append");
  }
}
