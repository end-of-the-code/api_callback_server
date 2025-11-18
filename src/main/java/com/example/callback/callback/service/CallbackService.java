package com.example.callback.callback.service;

import com.example.callback.callback.controller.req.CallbackRequest;
import com.example.callback.callback.event.CallbackReceivedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CallbackService {

  // Spring의 표준 이벤트 발행기
  private final ApplicationEventPublisher eventPublisher;

  public void receive(CallbackRequest request) {
    // 단지 "콜백이 수신되었다"는 사실만 발행합니다.
    eventPublisher.publishEvent(new CallbackReceivedEvent(request));
  }
}