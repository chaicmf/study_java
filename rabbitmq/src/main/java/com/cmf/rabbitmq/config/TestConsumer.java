package com.cmf.rabbitmq.config;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;



/**
 * 消费者
 * @author chaiminfang
 * @date 2021/8/13
 */
@Component
public class TestConsumer {

    //消费死信队列的消息
    @RabbitListener(queues = "${mq.queueBinding.dlQueue}")
    public void infoConsumption(Message message, Channel channel) throws IOException {
        String data=new String(message.getBody());
        System.out.println("死信队列收到的消息:"+data);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }


    //消费业务队列中的消息

    @RabbitListener(queues = "${mq.queueBinding.queue}")
    public void business(Message message, Channel channel) throws Exception {
        String data=new String(message.getBody());
        System.out.println("接收到业务消息"+data);

        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }

    @RabbitListener(queues = "${mq.delay-queue-name}")
    public void business2(String data){
        System.out.println("延时队列接收到了消息"+data);

    }

}
