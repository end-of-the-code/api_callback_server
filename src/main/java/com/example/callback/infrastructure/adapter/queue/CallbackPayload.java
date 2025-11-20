package com.example.callback.infrastructure.adapter.queue;

import com.example.callback.callback.controller.req.CallbackRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CallbackPayload {
  private Long txId;
  private String message;

  public static CallbackPayload from(CallbackRequest request) {
    return new CallbackPayload(request.getTxId(), request.getMessage());
  }
}
