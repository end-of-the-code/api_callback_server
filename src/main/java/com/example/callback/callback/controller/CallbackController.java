package com.example.callback.callback.controller;

import com.example.callback.callback.controller.req.CallbackRequest;
import com.example.callback.callback.service.CallbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/callback")
@RestController
@RequiredArgsConstructor
public class CallbackController {
  private final CallbackService callbackService;

  @PostMapping
  public ResponseEntity<String> receive(
      @RequestBody CallbackRequest request
  ) {
    callbackService.receive(request);
    return ResponseEntity.ok("ok");
  }
}