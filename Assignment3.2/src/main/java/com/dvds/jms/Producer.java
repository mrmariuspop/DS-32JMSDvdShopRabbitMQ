package com.dvds.jms;

import org.springframework.util.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeoutException;

public class Producer extends Endpoint {

    public Producer(String exchangeName) throws IOException, TimeoutException {
        super(exchangeName);
    }

    public void sendMessage(Serializable object) throws IOException {
        channel.basicPublish("", exchangeName, null, SerializationUtils.serialize(object));
    }
}
