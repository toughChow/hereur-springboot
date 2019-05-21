package com.toughchow.springbootcommon.mq.rabbitmq.secondsKill;

import com.toughchow.springbootcommon.mq.rabbitmq.service.RabbitMQSender;
import com.toughchow.springbootcommon.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author: create by ToughChow
 * @version: v1.0
 * @description: com.toughchow.springbootcommon.mq.rabbitmq.secondsKill
 * @date:2019/5/21
 */
@Slf4j
@Service
public class RabbitMQSecondsKill {

    @Autowired
    RabbitMQSender rabbitMQSender;

    boolean  result = true;


    public boolean secondsKill(String pid) {
        int i = 1;
        boolean b = rabbitMQSender.sendDirectQueue(pid);
        log.info("service>>>>>>{}", i++);
        return b;
    }
}
