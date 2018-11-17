package edu.assign3_2.producer.service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import edu.assign3_2.entities.DVD;


public class MessageSender {

	private final static String QUEUE_NAME = "DVDQueue";
	
	public MessageSender() {
		
	}
	
	public void sendDVD(DVD dvd) {
		try {
			ConnectionFactory factory = new ConnectionFactory();
		    factory.setHost("localhost");
		    Connection connection = factory.newConnection();
		    Channel channel = connection.createChannel();

		    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		    channel.basicPublish("", QUEUE_NAME, null, dvd.toString().getBytes("UTF-8"));
		    System.out.println("---- Producer Started ----");

		    channel.close();
		    connection.close();
		} catch (IOException | TimeoutException e) {
			e.printStackTrace();
		}
	    
	}
}
