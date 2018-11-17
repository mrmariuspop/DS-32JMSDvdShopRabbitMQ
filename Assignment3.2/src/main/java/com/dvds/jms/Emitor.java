package com.dvds.jms;

import com.dvds.entities.DVD;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Emitor {

	  private static final String EXCHANGE_NAME = "dvds";

	  public void sendDvd(DVD d) throws Exception {
	    ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();

	    channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

	    channel.basicPublish(EXCHANGE_NAME, "", null, d.toString().getBytes("UTF-8"));
	    System.out.println(" [x] Sent '" + d.toString() + "'");

	    channel.close();
	    connection.close();
	  }

}
