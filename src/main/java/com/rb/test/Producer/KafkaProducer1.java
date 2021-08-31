package com.rb.test.Producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafkaProducer1 {

    @Autowired
    public Environment env;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topicName, String msg) {
        ListenableFuture<SendResult<String, String>> future =
                kafkaTemplate.send(topicName, msg);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(org.springframework.kafka.support.SendResult<String, String> result) {
                System.out.println("Sent message=[" + msg + "] at offset[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Message could mpot be sent, error : " + ex.getMessage());
            }
        });
    }
}
