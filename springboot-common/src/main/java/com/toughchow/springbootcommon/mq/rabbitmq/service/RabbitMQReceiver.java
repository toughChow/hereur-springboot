package com.toughchow.springbootcommon.mq.rabbitmq.service;

import com.rabbitmq.client.Channel;
import com.toughchow.springbootcommon.common.Constant;
import com.toughchow.springbootcommon.redis.RedisUtil;
import com.toughchow.springbootcommon.mybatis.mapper.ProductMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: create by ToughChow
 * @version: v1.0
 * @description: com.toughchow.springbootcommon.mq.rabbitmq.service
 * @date:2019/5/21
 */
@Slf4j
@Component
@RabbitListener(queues = Constant.QUEUE)
public class RabbitMQReceiver {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    ProductMapper productMapper;

    /**
     * 消息出列（消费消息）-和消息入列没有直接的调用关系
     * 通过注解中指定的名称进行关联
     * @param msg
     * @param channel
     * @param message
     */
    @RabbitHandler
    public void receiverDirectQueue(String msg, Channel channel, Message message) throws IOException {
        int i = 1;

        log.info("收到秒杀<<<<<<<<商品: {}",msg);
        try {
            long num = redisUtil.decr(msg,1);
            if(num < 0) {
                log.info("收到秒杀<<<<<<<<库存不足");
                throw new RuntimeException();
            }
            log.info("收到秒杀<<<<<<<<次数: {}",i++);
            Map map = new HashMap();
            map.put("id", msg);
            map.put("quantity", num);

            // 根据商品id和库存同步到MySQL
            if(productMapper.updateQuanrityByPid(map) == 0) {
                log.error("同步到商品表异常");
            }

        } catch (Exception e) {
            /*
                当消费消息出现异常时，我们需要取消确认，这时我们可以使用 Channel 的 basicReject 方法。
                其中第一个参数为指定 delivery tag，第二个参数说明如何处理这个失败消息：
                    true为将该消息重新放入队列头，false为忽略该消息。
             */
//            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            log.info("收到秒杀<<<<<<<<异常: {}",e.getMessage());
            e.printStackTrace();
        }
    }



}
