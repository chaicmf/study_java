package com.cmf.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chaiminfang
 * @date 2021/8/13
 */
@Configuration
public class RabbitDeadLetterConfig {


    //配置死信队列
    @Value("${mq.queueBinding.exchange.dlTopicExchange}")
    private String dlTopicExchange;

    @Value("${mq.queueBinding.dlRoutingKey}")
    private String dlRoutingKey;

    @Value("${mq.queueBinding.dlQueue}")
    private String dlQueue;

    //创建死信交换机
    @Bean("dlTopicExchange")
    public TopicExchange dlTopicExchange(){
        return new TopicExchange(dlTopicExchange,true,false);
    }

    //创建死信队列
    @Bean("dlQueue")
    public Queue dlQueue(){
        return new Queue(dlQueue,true);
    }

    //死信队列与死信交换机进行绑定
    @Bean
    public Binding bindingErrorQueueAndExchange(@Qualifier("dlQueue") Queue dlQueue,@Qualifier("dlTopicExchange") TopicExchange dlTopicExchange){
        return BindingBuilder
                .bind(dlQueue)
                .to(dlTopicExchange)
                .with(dlRoutingKey);
    }









}
