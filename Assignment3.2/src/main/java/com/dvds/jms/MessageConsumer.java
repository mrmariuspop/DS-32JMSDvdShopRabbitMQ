package com.dvds.jms;

import com.dvds.MailService;
import com.dvds.entities.DVD;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import org.springframework.util.SerializationUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MessageConsumer extends Endpoint implements Runnable, Consumer {

    private MailService mailService;

    public MessageConsumer(String exchangeName) throws IOException, TimeoutException {
        super(exchangeName);
        mailService = new MailService("user", "pass");
    }

    public void run() {
        try {
            channel.basicConsume(exchangeName, true, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleConsumeOk(String consumerTag) {
        System.out.println("MessageConsumer " + consumerTag + " registered");
    }

    public void handleDelivery(String consumerTag, Envelope env,
                               AMQP.BasicProperties props, byte[] body) throws IOException {
        DVD dvd = (DVD) SerializationUtils.deserialize(body);
        writeToFile(dvd.toString());
        System.out.println("Wrote " + dvd + " to file");
        mailService.sendMail("user", "Test", dvd.toString());
        System.out.println("Sent mail with " + dvd);

    }

    public void handleCancel(String consumerTag) {
    }

    public void handleCancelOk(String consumerTag) {
    }

    public void handleRecoverOk(String consumerTag) {
    }

    public void handleShutdownSignal(String consumerTag, ShutdownSignalException arg1) {
    }

    private static void writeToFile(String message) {
        try (FileWriter writer = new FileWriter("messages.txt", true)) {
            writer.write(message + "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, TimeoutException {
        final MessageConsumer consumer = new MessageConsumer("queue");
        final Thread consumerThread = new Thread(consumer);
        consumerThread.start();

        final MessageConsumer consumer2 = new MessageConsumer("queue2");
        final Thread consumerThread2 = new Thread(consumer2);
        consumerThread2.start();
    }
}
