package org.spotter.benchmark.app.problems.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ESTReceiver implements MessageListener {

	public static final String EST_RECEIVER_QUEUE = "EST_RECEIVER";

	public static void main(String[] args) {
		String jmsServer = null;
		ConnectionFactory connectionFactory = null;
		Connection connection = null;
		Session session = null;

		if (args.length != 1) {
			System.out.println("Program takes one argument: <activemqServer>");
			System.exit(1);
		}

		jmsServer = new String(args[0]);

		connectionFactory = new ActiveMQConnectionFactory("failover://udp://" + jmsServer + ":61616");
		/*
		 * Create connection. Create session from connection; false means
		 * session is not transacted. Create consumer. Register message listener
		 * (TextListener). Receive text messages from destination. When all
		 * messages have been received, type Q to quit. Close connection.
		 */
		try {
			connection = connectionFactory.createConnection();
			session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
			
			ESTReceiver estReceiver = new ESTReceiver(session);

			connection.start();

			while (true) {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					break;
				}
			}

		} catch (JMSException e) {
			System.out.println("Exception occurred: " + e.toString());
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
				}
			}
		}
	}

	public ESTReceiver(Session session) throws JMSException {
		Destination dest = session.createQueue(EST_RECEIVER_QUEUE);
		MessageConsumer consumer = session.createConsumer(dest);
		consumer.setMessageListener(this);
	}

	@Override
	public void onMessage(Message message) {
		try {
			message.acknowledge();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}

}
