package com.example.callback.callback.service.port;

import com.example.callback.callback.controller.req.CallbackRequest;

public interface ExternalQueuePublisher {

  void publish(CallbackRequest request);
}
