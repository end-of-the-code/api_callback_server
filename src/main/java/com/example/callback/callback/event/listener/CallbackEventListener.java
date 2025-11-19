package com.example.callback.callback.event.listener;

import com.example.callback.callback.event.CallbackReceivedEvent;
import com.example.callback.callback.service.port.StorageProvider;
import com.example.callback.callback.service.port.ExternalQueuePublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component // 이벤트 리스너는 반드시 Spring Bean이어야 합니다.
@RequiredArgsConstructor
public class CallbackEventListener {

  // 추상화된 큐 발행 인터페이스를 주입받습니다.
  private final ExternalQueuePublisher externalQueuePublisher;
  private final StorageProvider storageProvider;

  /**
   * todo 1: 요청 데이터 파일 저장 이벤트 처리기
   * @Async를 통해 이 메서드는 Controller의 응답과 관계없이 별도 스레드에서 실행됩니다.
   */

  @Async("fileTaskExecutor")
  @EventListener
  public void saveCallbackToFile(CallbackReceivedEvent event) {

    try {
      storageProvider.save(event);
      log.info("Callback data saved to file.");

    } catch (Exception e) {
      log.error("Failed to write callback to file", e);
      // todo: 파일 쓰기 실패 시 예외 처리 (e.g., DLQ, 에러 로그)

    }
  }

  /**
   * todo 2: 외부 큐를 위한 이벤트 발행기
   * 동일한 이벤트를 받아, 큐 발행 로직을 비동기로 처리합니다.
   */
  @Async("queueTaskExecutor")
  @EventListener
  public void publishToExternalQueue(CallbackReceivedEvent event) {
    log.info("Publishing event to external queue...");
    // 실제 로직은 주입받은 구현체(ExternalQueuePublisher)가 수행합니다.
    externalQueuePublisher.publish(event.getRequest());
  }
}