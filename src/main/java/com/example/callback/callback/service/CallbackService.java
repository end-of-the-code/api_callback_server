package com.example.callback.callback.service;

import com.example.callback.callback.controller.req.CallbackRequest;
import com.example.callback.callback.event.CallbackReceivedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CallbackService {

  private final ApplicationEventPublisher eventPublisher;

  public void receive(CallbackRequest request) {
    eventPublisher.publishEvent(new CallbackReceivedEvent(request));
  }
}