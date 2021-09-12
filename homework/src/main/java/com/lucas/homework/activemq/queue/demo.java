package com.lucas.homework.activemq.queue;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.*;
import java.util.concurrent.atomic.AtomicInteger;

public class demo {
    public static void main(String[] args) {
        final Destination destination = new ActiveMQQueue("test.queue");

        try {
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
            ActiveMQConnection conn = (ActiveMQConnection) factory.createConnection();
            conn.start();
            Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            final AtomicInteger count = new AtomicInteger(0);
            MessageConsumer customer1 = session.createConsumer(destination);
            customer1.setMessageListener(new MessageListener() {

                public void onMessage(Message message) {
                    try {
                        System.out.println("customer1 consume the " + count.incrementAndGet() + " => receive from " + destination.toString() + ": " + message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            MessageConsumer customer2 = session.createConsumer(destination);
            customer2.setMessageListener(new MessageListener() {

                public void onMessage(Message message) {
                    try {
                        System.out.println("customer2 consume the " + count.incrementAndGet() + " => receive from " + destination.toString() + ": " + message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            MessageProducer producer = session.createProducer(destination);
            int index = 0;
            while (index++ < 100) {
                TextMessage message = session.createTextMessage(index + " message.");
                producer.send(message);
            }

            Thread.sleep(2000);
            session.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
