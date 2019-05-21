package com.toughchow.springbootweb;

import com.toughchow.springbootcommon.mq.rabbitmq.secondsKill.RabbitMQSecondsKill;
import com.toughchow.springbootcommon.redis.RedisUtil;
import com.toughchow.springbootcommon.redis.publisher.PublisherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootWebApplicationTests {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    PublisherService publisherService;

    @Autowired
    RabbitMQSecondsKill secondsKill;

    @Test
    public void contextLoads() {
        publisherService.pushMsg("hello tough");
    }

    @Test
    public void secondsKill() {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 6; i++) {
            service.execute(()->{
                secondsKill.secondsKill("1");
            });
        }
        service.shutdown();
    }

}
