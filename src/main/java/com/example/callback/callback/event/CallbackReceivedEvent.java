package com.example.callback.callback.event;

import com.example.callback.callback.controller.req.CallbackRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 콜백 수신 이벤트를 나타내는 객체
 */
@Getter
@RequiredArgsConstructor
public class CallbackReceivedEvent {
  private final CallbackRequest request;
}
