package com.toughchow.springbootcommon.mq.rabbitmq.listener;

import com.toughchow.springbootcommon.mybatis.entity.ProductEntity;
import com.toughchow.springbootcommon.mybatis.mapper.ProductMapper;
import com.toughchow.springbootcommon.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * @author: create by ToughChow
 * @version: v1.0
 * @description: com.toughchow.springbootcommon.mq.rabbitmq.listener
 * @date:2019/5/21
 */
@Slf4j
@Service
@Scope(value = "singleton")
public class RabbitMQApplicationInitListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    ProductMapper productMapper;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(contextRefreshedEvent.getApplicationContext().getParent() == null) {
            log.info(">>>>>>项目初始化,执行监听<<<<<<");
            List<ProductEntity> list = productMapper.selectTimerTask();
            Iterator<ProductEntity> it = list.iterator();
            while (it.hasNext()) {
                ProductEntity productEntity = it.next();
                log.info("商品id：{}，商品名称：{}，商品数量：{}",productEntity.getId(), productEntity.getName(),
                        productEntity.getQuantity());
                try {
                    redisUtil.set(productEntity.getId(), productEntity.getQuantity());
                } catch (Exception e) {
                    log.info("商品id：{}，商品名称：{}，商品数量：{}",productEntity.getId(), productEntity.getName(),
                            productEntity.getQuantity());
                    log.error(e.getMessage());
                }
            }
        }
    }
}
