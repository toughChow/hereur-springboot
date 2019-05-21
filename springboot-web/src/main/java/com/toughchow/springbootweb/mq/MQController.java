package com.toughchow.springbootweb.mq;

import com.toughchow.springbootcommon.mq.rabbitmq.secondsKill.RabbitMQSecondsKill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: create by ToughChow
 * @version: v1.0
 * @description: com.toughchow.springbootweb.mq
 * @date:2019/5/21
 */
@RestController
@RequestMapping("/mq")
public class MQController {

    @Autowired
    RabbitMQSecondsKill kill;

    @RequestMapping("/rabbit")
    public synchronized Object rabbitMQSend() {
        return kill.secondsKill("1");
    }

}
