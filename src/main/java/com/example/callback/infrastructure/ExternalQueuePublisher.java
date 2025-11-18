package com.example.callback.infrastructure;

import com.example.callback.callback.controller.req.CallbackRequest;

public interface ExternalQueuePublisher {

  void publish(CallbackRequest request);
}
