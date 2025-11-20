package com.example.callback.infrastructure.adapter.queue;

import com.example.callback.callback.service.port.ExternalQueuePublisher;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaEventPublisher implements ExternalQueuePublisher {

  private final KafkaTemplate<String, Object> kafkaTemplate;
  private static final String TOPIC = "callback-topic";

  @Override
  public void publish(CallbackPayload payload) {
    // 지금은 단순히 로그만 찍습니다.
    log.info("KafakaPublisher send : {}", payload.toString());

    log.info("Sending data to Kafka topic: {}", TOPIC);

    // 1. 비동기 전송 (Async)
    // kafkaTemplate.send()는 Future를 반환하므로 메인 스레드를 차단하지 않습니다.
    CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(TOPIC, payload);

    // 2. 결과 콜백 처리 (성공/실패 여부 확인)
    future.whenComplete((result, ex) -> {
      if (ex == null) {
        // 성공
        log.info("Kafka send success: offset=[{}]", result.getRecordMetadata().offset());
      } else {
        // ★★★ 장애 대응: 카프카 전송 실패 시 ★★★
        // 여기서 예외가 발생했다는 것은 Kafka가 죽었거나 네트워크 문제라는 뜻입니다.
        log.error("Kafka send failed! Data: {}", payload, ex);

        // TODO: 이전에 설계한 '비상 파일 저장' 로직을 여기서 호출하거나,
        // 별도의 fallback 처리를 해야 합니다.
        handleFallback(payload);
      }
    });
  }

  // 카프카 이벤트 발행 후 콜백 응답 실패 시
  private void handleFallback(CallbackPayload payload) {
    // TODO: Kafka 장애 시 로컬 파일로 백업하는 로직 (구현 필요)
    log.warn("Saving to emergency local file instead...");

  }
}
