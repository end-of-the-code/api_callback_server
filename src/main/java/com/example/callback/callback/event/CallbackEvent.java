package com.example.callback.callback.event;

import com.example.callback.infrastructure.adapter.queue.CallbackPayload;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 콜백 수신 이벤트를 나타내는 객체
 */
@Getter
@RequiredArgsConstructor
public class CallbackEvent {
  private final CallbackPayload payload;
}
