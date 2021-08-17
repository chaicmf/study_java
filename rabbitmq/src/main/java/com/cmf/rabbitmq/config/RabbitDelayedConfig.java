package com.cmf.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chaiminfang
 * @date 2021/8/13
 */
@Configuration
public class RabbitDelayedConfig {

    @Value("${mq.delay-exchange}")
    private String exchangeName;

    @Value("${mq.delay-queue-name}")
    private String queueName;

    @Value("${mq.delay-route-key}")
    private String delayRouteKey;


    @Bean("exchange")
    TopicExchange exchange(){

        Map<String,Object> args=new HashMap<>();
        args.put("x-delayed-message","topic");
        return new TopicExchange(exchangeName,true,false,args);

    }

    //队列
    @Bean("queue")
    public Queue queue(){
        return new Queue(queueName,true);
    }

    //将队列绑定到交换机
    @Bean
    public Binding binding(@Qualifier("exchange") TopicExchange exchange,@Qualifier("queue") Queue queue){
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(delayRouteKey);
    }
}
