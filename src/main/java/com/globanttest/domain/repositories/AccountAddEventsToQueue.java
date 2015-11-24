package com.globanttest.domain.repositories;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import org.springframework.stereotype.Repository;

import com.globanttest.domain.events.AccountEvent;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@Repository
public class AccountAddEventsToQueue implements AccountEventRepository {

	private final static String QUEUE_NAME = "account-transaction";
	
	@Override
	public void persist(AccountEvent event) throws Exception{
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();

	    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
	   
	    channel.basicPublish("", QUEUE_NAME, null, eventsToByte(event));
	    System.out.println(" [x] Sent '" + event.toString() + "'");

	    channel.close();
	    connection.close();

	}
	
	private byte[] eventsToByte(AccountEvent event) throws IOException{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = null;
		try {
		  out = new ObjectOutputStream(bos);   
		  out.writeObject(event);
		  byte[] eventByte = bos.toByteArray();
		  return eventByte;
		} finally {
		  try {
		    if (out != null) {
		      out.close();
		    }
		  } catch (IOException ex) {
		    // ignore close exception
		  }
		  try {
		    bos.close();
		  } catch (IOException ex) {
		    // ignore close exception
		  }
		}
	}

}
