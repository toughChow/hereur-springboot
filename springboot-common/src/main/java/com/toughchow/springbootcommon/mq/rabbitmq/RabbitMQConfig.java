package com.toughchow.springbootcommon.mq.rabbitmq;

import com.toughchow.springbootcommon.common.Constant;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author: create by ToughChow
 * @version: v1.0
 * @description: com.toughchow.springbootcommon.mq.rabbitmq
 * @date:2019/5/21
 */
@Configuration
public class RabbitMQConfig {

    /**
     * Direct 模式
     * @return
     */
    @Bean
    public Queue directQueue() {
        // 新队列
        return new Queue(Constant.QUEUE, true);
    }

    /*@Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses("127.0.0.1:5672");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPublisherConfirms(true); //必须要设置
        return connectionFactory;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    //必须是prototype类型
    public RabbitTemplate rabbitTemplatecallback() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;
    }*/
}
