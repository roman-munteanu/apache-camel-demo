package com.munteanu.controllers;

import com.munteanu.models.DemoMessage;
import com.munteanu.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MessageController {

  private final MessageService messageService;

  @Autowired
  public MessageController(MessageService messageService) {
    this.messageService = messageService;
  }

  @RequestMapping(value = "/send", method = RequestMethod.POST)
  @ResponseBody
  public String sendMessage(@RequestBody() DemoMessage demoMessage) {
    messageService.sendMessage(demoMessage);
    return "Message sent";
  }
}
