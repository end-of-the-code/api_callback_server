package com.example.callback.infrastructure;

import com.example.callback.callback.controller.req.CallbackRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaEventPublisher implements ExternalQueuePublisher {

  @Override
  public void publish(CallbackRequest request) {
    // 지금은 단순히 로그만 찍습니다.
    log.info("KafakaPublisher send : {}", request.toString());

    // --- 나중에 실제 Kafka 구현 예시 ---
    // kafkaTemplate.send("callback-topic", request);
  }
}
