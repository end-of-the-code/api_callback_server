package com.example.callback.infrastructure;

import com.example.callback.callback.controller.req.CallbackRequest;

public interface ExternalQueuePublisher {

  public void publish(CallbackRequest request);
}
