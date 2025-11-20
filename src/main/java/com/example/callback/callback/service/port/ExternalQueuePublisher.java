package com.example.callback.callback.service.port;

import com.example.callback.infrastructure.adapter.queue.CallbackPayload;

public interface ExternalQueuePublisher {

  void publish(CallbackPayload payload);
}
