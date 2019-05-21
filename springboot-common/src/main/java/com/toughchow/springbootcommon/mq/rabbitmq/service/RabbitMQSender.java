package com.toughchow.springbootcommon.mq.rabbitmq.service;

import com.toughchow.springbootcommon.common.Constant;
import com.toughchow.springbootcommon.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author: create by ToughChow
 * @version: v1.0
 * @description: com.toughchow.springbootcommon.mq.rabbitmq.service
 * @date:2019/5/21
 */
@Slf4j
@Component
public class RabbitMQSender implements RabbitTemplate.ConfirmCallback {

    @Autowired
    AmqpTemplate amqpTemplate;

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    RedisUtil redisUtil;

    /*@Autowired
    public RabbitMQSender(RabbitTemplate rabbitTemplateCallback) {
        this.rabbitTemplate = rabbitTemplateCallback;
        rabbitTemplate.setConfirmCallback(this);
    }*/

    int i = 1;

    AtomicBoolean atomicBoolean = new AtomicBoolean(true);
    private static AtomicBoolean initialized = new AtomicBoolean(true);

    public boolean sendDirectQueue(String msg) {
        log.info("秒杀>>>>>>>>商品: {}",msg);
        try {
            amqpTemplate.convertAndSend(Constant.QUEUE, msg);
//            CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
//            rabbitTemplate.convertAndSend("product-exchange",Constant.QUEUE,msg,correlationData);
            /*while (atomicBoolean.get()) {
            }*/
            String num = redisUtil.get(msg).toString();
            log.info("redis>>>>>>数量:",num);
            if(new Long(num) < 0) {
                log.error("redis>>>>>>已售罄");
                initialized.set(false);
            }
            log.info("秒杀>>>>>>>>次数: {}",i++);
        } catch (Exception e) {
            log.info("秒杀>>>>>>>>异常: {}",e.getMessage());
        }
        return initialized.get();
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        if(b) {
            log.info("ssssssssss");
        } else {
            log.info("ffffffffff");
        }
        atomicBoolean.set(false);
        log.info("<<<<<<<<{}>>>>>>>>",correlationData.getId());
    }
}
