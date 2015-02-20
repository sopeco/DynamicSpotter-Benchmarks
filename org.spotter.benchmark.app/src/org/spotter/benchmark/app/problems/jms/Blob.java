package org.spotter.benchmark.app.problems.jms;

import java.util.Random;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.spotter.benchmark.app.BenchmarkAppLauncher;

public class Blob {
	private static Blob instance;
	private static final Random RAND = new Random(System.currentTimeMillis());
	private static String[] textSnippets = {
			"alsdkfjhaksdfhlaksdjhfklajsdhfklsajfhkliervbdhbfhvbdjfhvvdfvalsdkfjhaksdfhlaksdjhfklajsdhfklsajfhkliervbdhbfhvbdjfhvvdfv",
			"mfdnsiuruiebegkdlfvjbhgaorejntnobvduizhfhbwleklfjksasdfhbajsmfdnsiuruiebegkdlfvjbhgaorejntnobvduizhfhbwleklfjksasdfhbajs",
			"sdfvjjboweivbwioeubrvowhibfvoihwrvsakjdnioewihbwoubvhurebvuhsdfvjjboweivbwioeubrvowhibfvoihwrvsakjdnioewihbwoubvhurebvuh",
			"daökfhbvowiejbvioshvoiwjebnpvihbuodshbfvowhbewuirebvpwfdvwwedaökfhbvowiejbvioshvoiwjebnpvihbuodshbfvowhbewuirebvpwfdvwwe",
			"asdkjbfiezrbvkdjbvjksijazsgfouhbvskjbfvoueohibfvjhdbfvdjhgfeasdkjbfiezrbvkdjbvjksijazsgfouhbvskjbfvoueohibfvjhdbfvdjhgfe" };
	
	public static final String[] BLOB_QUEUES = { "BlobQueue0", "BlobQueue1", "BlobQueue2", "BlobQueue3" };

	public static Blob getInstance() {
		if (instance == null) {
			instance = new Blob();
		}
		return instance;
	}

	private Session session;
	private Producer[] producers;
	private Connection connection = null;

	private Blob() {
		String jmsServer = BenchmarkAppLauncher.JMS_HOST;

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
			producers = new Producer[BLOB_QUEUES.length - 1];
			for (int i = 0; i < BLOB_QUEUES.length - 1; i++) {
				producers[i] = new Producer(session.createProducer(session.createQueue(BLOB_QUEUES[i + 1])));
			}

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

	public void sendMessage(int size) {
		try {
			Message msg = session.createTextMessage(getDummyMessage(size));
			double d = RAND.nextDouble();
			if (d < 0.333) {
				producers[0].send(msg);
			} else if (d < 0.666) {
				producers[1].send(msg);
			} else {
				producers[2].send(msg);
			}

		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	private String getDummyMessage(int size) {
		StringBuilder strBuilder = new StringBuilder();
		for (int i = 0; i < size; i++) {
			strBuilder.append(textSnippets[RAND.nextInt(5)]);
		}
		return strBuilder.toString();
	}
}
