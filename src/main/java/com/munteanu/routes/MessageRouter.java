package com.munteanu.routes;

import com.munteanu.processors.DemoMessageToCsvProcessor;
import com.munteanu.processors.MessageHeadersProcessor;
import com.munteanu.processors.DemoMessageToStringProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.munteanu.enums.Channel.AMQP;
import static com.munteanu.enums.Channel.CSV;
import static com.munteanu.enums.Channel.DATABASE;
import static com.munteanu.enums.Channel.TXT;
import static com.munteanu.processors.MessageHeadersProcessor.DEMO_MESSAGE_CHANNEL;

@Component
public class MessageRouter extends RouteBuilder {

  public static final String SEND_MESSAGE_ROUTE = "direct:send-message";

  @Autowired
  private DemoMessageToCsvProcessor demoMessageToCsvProcessor;

  @Override
  public void configure() throws Exception {
    from(SEND_MESSAGE_ROUTE)
      .routeId("SEND_MESSAGE_ROUTE_ID")
      .bean(new MessageHeadersProcessor())
      .choice()
        .when(header(DEMO_MESSAGE_CHANNEL).isEqualTo(TXT))
          .process(new DemoMessageToStringProcessor())
          .to("file:output/?fileName=demo_messages.txt&fileExist=append")
        .when(header(DEMO_MESSAGE_CHANNEL).isEqualTo(CSV))
          .bean(demoMessageToCsvProcessor)
          .to("file:output/?fileName=demo_messages.csv&fileExist=append")
        .when(header(DEMO_MESSAGE_CHANNEL).isEqualTo(DATABASE))
              .to("mongodb:mongoBean?database=camel&collection=messages&operation=insert");
//        .when(header(DEMO_MESSAGE_CHANNEL).isEqualTo(AMQP))
//          .process(new DemoMessageToStringProcessor())
//          .to("rabbitmq://localhost:5672/munteanu.demo.direct.exchange?routingKey=munteanu.demo.message.send.routing.key");
//        .otherwise()
//          .to();
  }
}
