package com.toughchow.springbootcommon.redis.publisher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
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
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private CountDownLatch latch;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    public void sendData(byte[] channel, byte[] msg) {
        RedisConnection redisConnection = redisConnectionFactory.getConnection();

        redisConnection.publish(msg, channel);
        redisConnection.close();
    }

    public void sendChannelMsg(String channel, String message) {
        try {
            redisTemplate.convertAndSend(channel, message);
            latch.await();
        } catch (Exception e) {
            log.error("send redis mq error occur: {}", e.getMessage());
        }
    }
}
