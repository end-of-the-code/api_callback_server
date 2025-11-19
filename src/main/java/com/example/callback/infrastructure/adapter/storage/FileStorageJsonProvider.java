package com.example.callback.infrastructure.adapter.storage;

import com.example.callback.callback.controller.req.CallbackRequest;
import com.example.callback.callback.event.CallbackReceivedEvent;
import com.example.callback.callback.service.port.StorageProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileWriter;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
//@Primary
@Component
@RequiredArgsConstructor
public class FileStorageJsonProvider implements StorageProvider {

  // Spring Boot가 자동으로 구성해준 ObjectMapper를 주입받습니다.
  private final ObjectMapper objectMapper;

  @Override
  public void save(CallbackReceivedEvent data) {
    CallbackRequest request = data.getRequest();

    try (FileWriter writer = new FileWriter("callback_log.txt", true)) {

      // 1. 객체를 JSON 문자열로 변환 (Serialization)
      String json = objectMapper.writeValueAsString(request);

      // 2. JSON 문자열을 파일에 기록 (가독성을 위해 줄바꿈 문자 추가)
      writer.write(json + System.lineSeparator());

      log.info("Success: Data saved to file as JSON.");

    } catch (JsonProcessingException e) {
      log.error("Error: JSON conversion failed.", e);
      throw new RuntimeException("JSON Conversion Failure", e);
    } catch (IOException e) {
      log.error("Error: File write failed.", e);
      throw new RuntimeException("File IO Failure", e);
    }
  }
}

