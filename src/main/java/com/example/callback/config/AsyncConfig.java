package com.example.callback.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {

  /**
   * 콜백 요청 파일 저장을 위한 전용 스레드 풀 (I/O-intensive)
   */
  @Bean(name = "fileTaskExecutor")
  public ThreadPoolTaskExecutor fileTaskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(10); // 기본 스레드 수
    executor.setMaxPoolSize(30);  // 최대 스레드 수
    executor.setQueueCapacity(500); // 대기 큐
    executor.setThreadNamePrefix("Callback-File-IO-");
    // todo: 추후 백프레셔 등 정책 수립
    executor.initialize();
    return executor;
  }

  /**
   * 외부 큐(Kafka, RabbitMQ 등) 발행을 위한 전용 스레드 풀 (Network I/O-intensive)
   */
  @Bean(name = "queueTaskExecutor")
  public ThreadPoolTaskExecutor queueTaskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(10); // 기본 스레드 수
    executor.setMaxPoolSize(30);  // 최대 스레드 수
    executor.setQueueCapacity(500); // 대기 큐
    executor.setThreadNamePrefix("Callback-Queue-");
    // todo: 추후 백프레셔 등 정책 수립
    executor.initialize();
    return executor;
  }
}