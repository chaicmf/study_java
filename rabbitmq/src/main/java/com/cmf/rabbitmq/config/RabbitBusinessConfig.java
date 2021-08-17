package com.cmf.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author chaiminfang
 * @date 2021/8/13
 */
@Component
public class RabbitBusinessConfig {

    //配置业务队列
    @Value("${mq.queueBinding.queue}")
    private String queueName;

    @Value("${mq.queueBinding.exchange.name}")
    private String exchangeName;

    @Value("${mq.queueBinding.key}")
    private String key;

    //声明当前队列绑定的死信交换机
    @Value("${mq.queueBinding.exchange.dlTopicExchange}")
    private String dlTopicExchange;

    //声明当前队列的死信路由键
    @Value("${mq.queueBinding.dlRoutingKey}")
    private String dlRoutingKey;

    //设置队列的过期时间
    @Value("${mq.timeout}")
    private int timeOut;

    //创建业务队列
    @Bean("businessQueue")
    public Queue businessQueue(){
        return QueueBuilder
                .durable(queueName)
                .deadLetterExchange(dlTopicExchange)
                .deadLetterRoutingKey(dlRoutingKey)
                .ttl(timeOut)
                .build();
    }

    //创建业务交换机
    @Bean("businessTopicExchange")
    public TopicExchange businessTopicExchange(){
        return new TopicExchange(exchangeName,true,false);
    }

    //业务队列与业务交换机进行绑定
    @Bean
    public Binding bingingBusinessQueueAndTopicExchange(@Qualifier("businessQueue") Queue businessQueue,@Qualifier("businessTopicExchange") TopicExchange businessTopicExchange){

        return BindingBuilder
                .bind(businessQueue)
                .to(businessTopicExchange)
                .with(key);

    }


}
