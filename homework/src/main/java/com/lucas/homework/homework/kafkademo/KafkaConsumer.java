package com.lucas.homework.homework.kafkademo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics="order-test")
    public void listen(ConsumerRecord<?,?> record, Acknowledgment ack){
        System.out.println(record.value());
        ack.acknowledge();
    }
}
