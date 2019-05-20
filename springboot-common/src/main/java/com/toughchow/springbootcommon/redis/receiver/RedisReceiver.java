package com.toughchow.springbootcommon.redis.receiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * @author: create by ToughChow
 * @version: v1.0
 * @description: com.toughchow.springbootcommon.redis.service
 * @date:2019/5/20
 */
@Slf4j
@Component
public class RedisReceiver {

    private CountDownLatch latch;

    public RedisReceiver(CountDownLatch latch) {
        this.latch = latch;
    }

    public void receiveMessage(String message) {
        log.info("Redis mq received message: {}", message);
        latch.countDown();
    }
}
