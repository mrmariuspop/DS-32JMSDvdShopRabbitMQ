package edu.assign3_2.consumer.service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import edu.assign3_2.consumer.util.DVDFileCreator;
import edu.assign3_2.consumer.util.Notification;

public class MessageReceiver {

	private final static String QUEUE_NAME = "DVDQueue";
	private Notification notification;
	private DVDFileCreator dvdFileCreator;
	
	public MessageReceiver() {
		notification = new Notification();
		dvdFileCreator = new DVDFileCreator();
	}
	
	public void receiveDVD() {
		try {
			ConnectionFactory factory = new ConnectionFactory();
		    factory.setHost("localhost");
		    Connection connection = factory.newConnection();
		    Channel channel = connection.createChannel();

		    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		    System.out.println("---- Consumer Started ----");

		    Consumer consumer = new DefaultConsumer(channel) {
		      @Override
		      public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
		          throws IOException {
		        String dvd = new String(body, "UTF-8");
		        
		        notification.notifySubscribers(dvd);
		        dvdFileCreator.createNewDVDFile(dvd);
		      }
		    };
		    
		    channel.basicConsume(QUEUE_NAME, true, consumer);
		} catch (IOException | TimeoutException e) {
			e.printStackTrace();
		}   
	}
	
	public static void main(String[] args) {
		MessageReceiver receiver = new MessageReceiver();
		receiver.receiveDVD();
	}
}
