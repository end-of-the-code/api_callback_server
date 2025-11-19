package com.example.callback.callback.service.port;

import com.example.callback.callback.event.CallbackReceivedEvent;

public interface StorageProvider {

  void save(CallbackReceivedEvent data);
}
