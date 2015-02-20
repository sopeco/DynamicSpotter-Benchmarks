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

public class ESTSender {

	private static ESTSender instance;
	private static final Random RAND = new Random(System.currentTimeMillis());
	private static String[] textSnippets = {
			"alsdkfjhaksdfhlaksdjhfklajsdhfklsajfhkliervbdhbfhvbdjfhvvdfv",
			"mfdnsiuruiebegkdlfvjbhgaorejntnobvduizhfhbwleklfjksasdfhbajs",
			"sdfvjjboweivbwioeubrvowhibfvoihwrvsakjdnioewihbwoubvhurebvuhh",
			"daökfhbvowiejbvioshvoiwjebnpvihbuodshbfvowhbewuirebvpwfdvwwe",
			"asdkjbfiezrbvkdjbvjksijazsgfouhbvskjbfvoueohibfvjhdbfvdjhgfe" };

	public static ESTSender getInstance() {
		if (instance == null) {
			instance = new ESTSender();
		}
		return instance;
	}

	private Session session;
	private Producer producer;
	private Connection connection = null;

	private ESTSender() {
		String jmsServer = BenchmarkAppLauncher.JMS_HOST;

		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("failover://udp://" + jmsServer + ":61615");
		/*
		 * Create connection. Create session from connection; false means
		 * session is not transacted. Create consumer. Register message listener
		 * (TextListener). Receive text messages from destination. When all
		 * messages have been received, type Q to quit. Close connection.
		 */

		try {
			connection = connectionFactory.createConnection();
			session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
			producer = new Producer(session.createProducer(session.createQueue(ESTReceiver.EST_RECEIVER_QUEUE)));

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
			producer.send(msg);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	public void sendMessage(int size, int headerSize) {
		try {
			Message msg = session.createTextMessage(getDummyMessage(size));
			msg.setStringProperty("header", getDummyMessage(headerSize));
			producer.send(msg);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public void send(Message msg) throws JMSException {
			producer.send(msg);
	}

	private String getDummyMessage(int size) {
		StringBuilder strBuilder = new StringBuilder();
		for (int i = 0; i < 50 * size; i++) {
			strBuilder.append(RAND.nextInt(5));
		}
		return strBuilder.toString();
	}
}
