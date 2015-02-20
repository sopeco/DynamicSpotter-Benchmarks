package org.spotter.benchmark.app.problems;

import java.util.Random;

import org.spotter.benchmark.app.problems.jms.ESTSender;

public final class TC_16_JMSFileTransfer extends Problem {

	private static final Random RAND = new Random(System.nanoTime());

	private static TC_16_JMSFileTransfer instance;

	/**
	 * @return the singleton instance
	 */
	public static synchronized TC_16_JMSFileTransfer getInstance() {
		if (instance == null) {
			instance = new TC_16_JMSFileTransfer();
		}
		return instance;
	}

	@Override
	public void test() {
		ESTSender.getInstance().sendMessage(7000 + RAND.nextInt(4000));
	}

}
