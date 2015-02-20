package org.spotter.benchmark.app.problems.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;

public class Producer {
	MessageProducer producer;
	
	public Producer(MessageProducer producer ) {
		this.producer =producer;
	}
	
	public void send(Message msg) throws JMSException{
		producer.send(msg);
	}
}
