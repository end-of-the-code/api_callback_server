package com.example.callback.callback.service.port;

import com.example.callback.callback.event.CallbackReceivedEvent;
import org.springframework.stereotype.Service;

@Service
public interface StorageProvider {

  void save(CallbackReceivedEvent data);
}
