package com.dvds.jms;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

public abstract class Endpoint {

    protected Channel channel;
    protected Connection connection;
    protected String exchangeName;

    public Endpoint(String exchangeName) throws IOException, TimeoutException {
        this.exchangeName = exchangeName;

        final ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");

        connection = factory.newConnection();
        channel = connection.createChannel();
        channel.exchangeDeclare(exchangeName, "fanout");
    }

    static class Main {

        Main() throws IOException, TimeoutException {

            MessageConsumer consumer = new MessageConsumer("queue");
            Thread consumerThread = new Thread(consumer);
            consumerThread.start();

            Producer producer = new Producer("queue");

            for (int i = 1; i <= 10; i++) {
                HashMap message = new HashMap();
                message.put("message number", i);
                producer.sendMessage(message);
                System.out.println("Message Number " + i + " sent.");
            }
        }
    }


    public static void main(String[] args) throws Exception {
        new Main();
    }
}
