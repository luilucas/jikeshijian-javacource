package com.lucas.homework.homework;

import com.lucas.homework.homework.kafkademo.KafkaProducer;
import com.lucas.homework.homework.kafkademo.Order;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class HomeworkApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(HomeworkApplication.class, args);
		KafkaProducer producer = context.getBean(KafkaProducer.class);
		for (int i = 0; i < 1000; i++) {
			producer.send(new Order(1000L + i,System.currentTimeMillis(),"USD2CNY", 6.5d));
			producer.send(new Order(2000L + i,System.currentTimeMillis(),"USD2CNY", 6.51d));
		}
	}
}
