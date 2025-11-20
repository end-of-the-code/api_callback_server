package com.example.callback.callback.service;

import com.example.callback.callback.controller.req.CallbackRequest;
import com.example.callback.callback.event.CallbackEvent;
import com.example.callback.infrastructure.adapter.queue.CallbackPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CallbackService {

  private final ApplicationEventPublisher eventPublisher;

  public void receive(CallbackRequest request) {
    eventPublisher.publishEvent(new CallbackEvent(CallbackPayload.from(request)));
  }
}