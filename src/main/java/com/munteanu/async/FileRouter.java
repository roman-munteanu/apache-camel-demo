package com.munteanu.async;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.stream.CachedOutputStream;
import org.springframework.stereotype.Component;

import static org.apache.camel.builder.ProcessorBuilder.setHeader;

@Component
public class FileRouter extends RouteBuilder {

  public static final String READ_FILE_ROUTE = "direct:read-file";

  @Override
  public void configure() throws Exception {
    from(READ_FILE_ROUTE).routeId("READ_FILE_ROUTE_ID")
        .toF("file:samples?fileName=${in.body}");
//        .setHeader(Exchange.HTTP_URI, simple("http://${in.body}"))
//        .enrich("http://dummyhost?throwExceptionOnFailure=false");
  }
}
