package com.munteanu.controllers;

import com.munteanu.models.DemoMessage;
import com.munteanu.rabbit.DemoMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/amqp")
public class DemoAMQPController {

  @Autowired
  private DemoMessageProducer producer;

  @RequestMapping(value = "/send", method = RequestMethod.POST)
  public String sendMessage(@RequestBody() DemoMessage demoMessage) {
    producer.send(demoMessage);
    return "Message has been sent";
  }

}
