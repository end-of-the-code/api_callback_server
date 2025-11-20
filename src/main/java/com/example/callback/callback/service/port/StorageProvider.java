package com.example.callback.callback.service.port;

import com.example.callback.callback.event.CallbackEvent;

public interface StorageProvider {

  void save(CallbackEvent data);
}
