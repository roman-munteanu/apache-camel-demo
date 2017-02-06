package com.munteanu.services;

import com.munteanu.models.DemoMessage;
import com.munteanu.routes.MessageRouter;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

  @Autowired
  private ProducerTemplate producerTemplate;

  public void sendMessage(DemoMessage demoMessage) {
    producerTemplate.requestBody(MessageRouter.SEND_MESSAGE_ROUTE, demoMessage);
  }
}
