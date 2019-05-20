package com.toughchow.springbootweb;

import com.toughchow.springbootcommon.redis.RedisUtil;
import com.toughchow.springbootcommon.redis.publisher.PublisherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootWebApplicationTests {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    PublisherService publisherService;

    @Test
    public void contextLoads() {
        publisherService.pushMsg("hello tough");
    }

}
