package org.spotter.benchmark.app.problems.jms;

import java.util.Random;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class BlurredBlobReceiver implements MessageListener {
	private static BlurredBlobReceiver instance;
	private static final Random RAND = new Random(System.currentTimeMillis());

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
		BlurredBlobReceiver blurredBlobReceiver = new BlurredBlobReceiver(jmsServer, blobQueueIndex);

		while (true) {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				break;
			}
		}

	}

	private Session session;
	private Producer[] producers;
	private Connection connection = null;

	private BlurredBlobReceiver(String jmsServer, int index) {

		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("failover://tcp://" + jmsServer + ":61616");
		/*
		 * Create connection. Create session from connection; false means
		 * session is not transacted. Create consumer. Register message listener
		 * (TextListener). Receive text messages from destination. When all
		 * messages have been received, type Q to quit. Close connection.
		 */

		try {
			connection = connectionFactory.createConnection();
			session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
			producers = new Producer[Blob.BLOB_QUEUES.length - 1];

			int j = 0;
			for (int i = 0; i < Blob.BLOB_QUEUES.length; i++) {
				if (i == index) {
					continue;
				}
				producers[j] = new Producer(session.createProducer(session.createQueue(Blob.BLOB_QUEUES[i])));
				j++;
			}

			Destination dest = session.createQueue(Blob.BLOB_QUEUES[index]);
			MessageConsumer consumer = session.createConsumer(dest);
			consumer.setMessageListener(this);

			connection.start();

		} catch (JMSException e) {
			System.out.println("Exception occurred: " + e.toString());
			if (connection != null) {
				try {
					connection.close();
				} catch (JMSException e1) {
				}
			}
		}

	}

	@Override
	protected void finalize() throws Throwable {
		if (connection != null) {
			try {
				connection.close();
			} catch (JMSException e) {
			}
		}
	}

	private void send(Message msg) throws JMSException {
		double d = RAND.nextDouble();
		if (d < 0.333) {
			producers[0].send(msg);
		} else if (d < 0.666) {
			producers[1].send(msg);
		} else {
			producers[2].send(msg);
		}
	}

	@Override
	public void onMessage(Message message) {
		try {
			message.acknowledge();
			if (RAND.nextDouble()<0.1) {
				Message msg = session.createTextMessage(((TextMessage) message).getText());
				send(msg);
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}
}
