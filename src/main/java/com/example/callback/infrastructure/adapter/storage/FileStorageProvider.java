package com.example.callback.infrastructure.adapter.storage;

import com.example.callback.callback.event.CallbackEvent;
import com.example.callback.callback.service.port.StorageProvider;
import com.example.callback.infrastructure.adapter.queue.CallbackPayload;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileWriter;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Slf4j
@Primary
@Component
@RequiredArgsConstructor
public class FileStorageProvider implements StorageProvider {

  private final ObjectMapper objectMapper;

  @Override
  public void save(CallbackEvent data) {
    CallbackPayload request = data.getPayload();



    try (FileWriter writer = new FileWriter("callback_log.txt", true)) {
      writer.write(objectMapper.writeValueAsString(data.getPayload()) + System.lineSeparator());
      log.info("Success: Data saved to file.");
    } catch (IOException e) {
      log.error("Error: File write failed.", e);
      throw new RuntimeException("File IO Failure", e);
    }

  }
}
