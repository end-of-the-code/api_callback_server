package com.example.callback.infrastructure.queue;

import com.example.callback.callback.controller.req.CallbackRequest;

public interface ExternalQueuePublisher {

  void publish(CallbackRequest request);
}
