package com.rb.test.Listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @Autowired
    static Environment env;

    @KafkaListener(topics = "${kafka.topic1}", groupId = "${consumer1.groupId}")
    public void consume(String msg){
        System.out.println("Consumed message: " + msg);
    }

    @KafkaListener(topics = "${kafka.battle-topic}", groupId = "${gaming_consumer.groupId}")
    public void gamingConsume(String gameEvent){
        System.out.println("Consume a gaming event");
    }
}
