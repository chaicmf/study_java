package com.cmf.rabbitmq.config;


import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


/**
 * 生产者
 * @author chaiminfang
 * @date 2021/8/13
 */
@Configuration
public class TestProducer {

    @Value("${mq.queueBinding.exchange.name}")
    private String exchangeName;

    @Value("${mq.queueBinding.key}")
    private String key;

    @Value("${mq.delay-exchange}")
    private String exchangeDelayName;

    @Value("${mq.delay-queue-name}")
    private String queueName;

    @Value("${mq.delay-route-key}")
    private String delayRouteKey;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sender(String msg){
        Message message=new Message(msg.getBytes());
        message.getMessageProperties().setExpiration("1000");
        rabbitTemplate.convertSendAndReceive(exchangeName,key,message);
    }

    public void senderDelayed(String msg,int delay){
        System.out.println("延时队列发送消息");
        rabbitTemplate.convertAndSend(exchangeDelayName,delayRouteKey,msg,message -> {
            message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            message.getMessageProperties().setDelay(delay*1000);
            return message;
        });
    }


}
