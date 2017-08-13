package com.munteanu.async;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.util.stream.Collectors.toList;

@Service
public class AsyncFileReaderService {

  @Autowired
  private ProducerTemplate producerTemplate;

  public CompletableFuture<List<Object>> read(List<String> filenames) {

    List<CompletableFuture<Object>> futureList = filenames.stream()
        .map(filename -> producerTemplate.asyncRequestBody(FileRouter.READ_FILE_ROUTE, filename))
        .collect(toList());

    return CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()]))
      .thenApply(v -> futureList.stream()
        .map(CompletableFuture::join)
        .collect(toList())
      );
  }
}
