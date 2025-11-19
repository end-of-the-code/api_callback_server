package com.example.callback.infrastructure.adapter.storage;

import com.example.callback.callback.controller.req.CallbackRequest;
import com.example.callback.callback.event.CallbackReceivedEvent;
import com.example.callback.callback.service.port.StorageProvider;
import java.io.FileWriter;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Slf4j
@Primary
@Component
public class FilStorageProvider implements StorageProvider {

  @Override
  public void save(CallbackReceivedEvent data) {
    CallbackRequest request = data.getRequest();

    try (FileWriter writer = new FileWriter("callback_log.txt", true)) {
      writer.write(request.getMessage() + "\n");
      log.info("Success: Data saved to file.");
    } catch (IOException e) {
      log.error("Error: File write failed.", e);
      throw new RuntimeException("File IO Failure", e);
    }

  }
}
