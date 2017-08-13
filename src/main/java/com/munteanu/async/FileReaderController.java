package com.munteanu.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Controller
public class FileReaderController {

  @Autowired
  AsyncFileReaderService asyncFileReaderService;

  @RequestMapping(value = "/read", method = RequestMethod.POST)
  @ResponseBody
  public List<Object> sendMessage(@RequestBody() StringList filenames) throws ExecutionException, InterruptedException {
    CompletableFuture<List<Object>> futureContent = asyncFileReaderService.read(filenames);
    return futureContent.get();
  }
}
