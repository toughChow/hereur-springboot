package com.toughchow.springbootcommon.redis.publisher;

import com.toughchow.springbootcommon.common.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: create by ToughChow
 * @version: v1.0
 * @description: com.toughchow.springbootcommon.redis.service
 * @date:2019/5/20
 */
@Slf4j
@Service
public class PublicsherServiceImpl implements PublisherService{

    @Autowired
    private RedisService redisService;


    @Override
    public String pushMsg(String params) {
        log.info("redis mq publishing message...");
        redisService.sendChannelMsg(Constant.CHANNEL, "RedisMQ发布消息测试");
        return "success";
    }
}
