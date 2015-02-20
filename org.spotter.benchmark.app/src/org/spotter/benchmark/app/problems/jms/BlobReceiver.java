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

public class BlobReceiver implements MessageListener {
	public static void main(String[] args) {
		String jmsServer = null;
		ConnectionFactory connectionFactory = null;
		Connection connection = null;
		Session session = null;

		if (args.length != 2) {
			System.out.println("Program takes one argument: <activemqServer>");
			System.exit(1);
		}

		jmsServer = new String(args[0]);
		int blobQueueIndex = Integer.parseInt(args[1]);

		connectionFactory = new ActiveMQConnectionFactory("failover://tcp://" + jmsServer + ":61616");
		/*
		 * Create connection. Create session from connection; false means
		 * session is not transacted. Create consumer. Register message listener
		 * (TextListener). Receive text messages from destination. When all
		 * messages have been received, type Q to quit. Close connection.
		 */
		try {
			connection = connectionFactory.createConnection();
			session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);

			BlobReceiver blobReceiver = new BlobReceiver(session, blobQueueIndex);

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

	public BlobReceiver(Session session, int index) throws JMSException {
		Destination dest = session.createQueue(Blob.BLOB_QUEUES[index]);
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
